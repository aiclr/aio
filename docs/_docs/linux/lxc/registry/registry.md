<div style="text-align: center;font-size: 40px;">Docker registry</div>

## registry server

### self-signed certificates

- [reference](https://docs.docker.com/registry/insecure/)

#### 创建 自签证书 和 htpasswd

```shell
mkdir -p certs
openssl req -newkey rsa:4096 -nodes -sha256 -keyout certs/domain.key -addext "subjectAltName = DNS:myregistry.domain.com" -x509 -days 365 -out certs/domain.crt

slave:~ # openssl req -newkey rsa:4096 -nodes -sha256 -keyout certs/domain.key -addext "subjectAltName = DNS:myregistry.domain.com" -x509 -days 365 -out certs/domain.crt
Generating a RSA private key
...................................................................................................................................................................................................................................................................................................................................++++
.............................................++++
writing new private key to 'certs/domain.key'
-----
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [AU]:CN
State or Province Name (full name) [Some-State]:Beijing
Locality Name (eg, city) []:Beijing
Organization Name (eg, company) [Internet Widgits Pty Ltd]:bougainvilleas
Organizational Unit Name (eg, section) []:main
Common Name (e.g. server FQDN or YOUR name) []:myregistry.domain.com
Email Address []:caddyren@qq.com

# Create a password file with one entry for the user testuser, with password testpassword
mkdir -p auth
docker run --entrypoint htpasswd httpd:2 -Bbn user password > auth/htpasswd
```

### docker-compose.yml for docker registry

```yaml
version: "3.9"
services:
  registry:
    image: registry:2
    ports:
      - "443:443"
    environment:
      REGISTRY_HTTP_ADDR: 0.0.0.0:443
      REGISTRY_HTTP_TLS_CERTIFICATE: /certs/domain.crt
      REGISTRY_HTTP_TLS_KEY: /certs/domain.key
      REGISTRY_AUTH: htpasswd
      REGISTRY_AUTH_HTPASSWD_PATH: /auth/htpasswd
      REGISTRY_AUTH_HTPASSWD_REALM: Registrt Realm
    volumes:
      - /home/caddy/registry:/var/lib/registry
      - /home/caddy/certs:/certs
      - /home/caddy/auth:/auth
      - /etc/localtime:/etc/localtime:ro # 同步宿主机和容器时间
```

---

## client

### add hosts

```shell
echo "192.168.1.162	    myregistry.domain.com" >> /etc/hosts
ping myregistry.domain.com
```

### 客户端所在机器信任自签证书 Certificate Authorities

#### [archlinux](https://wiki.archlinux.org/title/Transport_Layer_Security#Certificate_authorities)

```shell
trust anchor domain.crt
systemctl restart docker.service
docker login -u=test -p=test myregistry.domain.com
curl -u test:test -X GET https://myregistry.domain.com/v2/_catalog
```

#### [opensuse](https://en.opensuse.org/Portal:FreeIPA/Installation#Certificate_Authority)

```shell
scp certs/domain.crt root@192.168.1.100:/etc/pki/trust/anchors/
update-ca-certificates
systemctl restart docker.service
docker login -u=test -p=test myregistry.domain.com
curl -u test:test -X GET https://myregistry.domain.com/v2/_catalog
```

#### [other linux](https://docs.docker.com/registry/insecure/)

### k8s

#### [kubectl 生成 secret](https://kubernetes.io/docs/concepts/containers/images/)

```shell
kubectl create secret docker-registry reg --docker-server=myregistry.domain.com --docker-username=test --docker-password=test
```

#### 测试验证用脚本 rabbitmq.yaml

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mq
  labels:
    app: mq
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mq
  template:
    metadata:
      labels:
        app: mq
    spec:
      imagePullSecrets:
        - name: reg #################################### 认证
      containers:
        - name: rabbitmq
          image: myregistry.domain.com/mq:v1
          imagePullPolicy: IfNotPresent # IfNotPresent Always Never
          ports:
            - containerPort: 15672
            - containerPort: 5672
          env:
            - name: RABBITMQ_DEFAULT_USER
              value: user
            - name: RABBITMQ_DEFAULT_PASS
              value: "123456"
          volumeMounts:
            - name: mq
              mountPath: /etc/localtime
              readOnly: true
      volumes:
        - name: mq
          hostPath:
            path: /etc/localtime
---
apiVersion: v1
kind: Service
metadata:
  name: mq
spec:
  type: NodePort
  selector:
    app: mq
  ports:
    - name: manager
      port: 15672
      targetPort: 15672
      nodePort: 30001
    - name: http
      port: 5672
      targetPort: 5672
      nodePort: 30002
```

#### example for kubectl

```shell
kubectl apply -f rabbitmq.yaml
kubectl get po
kubectl describe po -l app=mq
```

### docker

```shell
# Pull the alpine:3.14 image from docker hub.
docker pull alpine:3.14
docker tag alpine:3.14 myregistry.domain.com/alpine:t1
# Tag the image as myregistry.domain.com/alpine:t1. This creates an additional tag for the existing image. When the first part of the tag is a hostname and port, Docker interprets this as the location of a registry, when pushing
# need login
docker login -u=test -p=test myregistry.domain.com
docker push myregistry.domain.com/alpine:t1
# 删除宿主机 images
docker rmi myregistry.domain.com/alpine:t1
docker images
# Pull the myregistry.domain.com/alpine:t1 image from your local registry.
docker pull myregistry.domain.com/alpine:t1
docker images
```

## [HOME](../../../index.md)