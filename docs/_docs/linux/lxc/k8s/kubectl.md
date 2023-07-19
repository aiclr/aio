<div style="text-align: center;font-size: 40px;">kubectl</div>

## [reference](https://kubernetes.io/docs/reference/kubectl/overview/)

## API Server 监听8001端口

```shell
master:~ # kubectl proxy
Starting to serve on 127.0.0.1:8001

http://localhost:8001
```

#### kubectl 版本

```shell
kubectl version
curl http://localhost:8001/version
```

## [namespace](https://kubernetes.io/docs/concepts/overview/working-with-objects/namespaces/)

> Not All Objects are in a Namespace
> > Most Kubernetes resources (e.g. pods, services, replication controllers, and others) are in some namespaces. However, namespace resources are not themselves in a namespace. And low-level resources, such as nodes and persistentVolumes, are not in any namespace
>
> > flag --namespace

#### example

```shell
master:~ # kubectl get namespace
NAME                   STATUS   AGE
default                Active   6d9h
kube-node-lease        Active   6d9h
kube-public            Active   6d9h
kube-system            Active   6d9h
kubernetes-dashboard   Active   6d6h
master:~ # kubectl run nginx --image=nginx --namespace=<insert-namespace-name-here>
master:~ # kubectl get pods --namespace=<insert-namespace-name-here>
```

## [nodes/no](https://kubernetes.io/docs/concepts/architecture/nodes/)

```shell
kubectl get nodes
kubectl get no
kubectl describe no/master-dell

master-dell:~/yaml/fly # kubectl describe no/master-dell
Name:               master-dell
Roles:              control-plane,master
# 省略 内容
Taints:             node-role.kubernetes.io/master:NoSchedule
# 省略 内容
```

### taints node 调度策略 `kubectl taint nodes master-dell node-role.kubernetes.io/master=:PreferNoSchedule`

> taints node 调度策略
> >不能被调度，当只有一个 master 时要将 master 设置为可调度
> > >Taints:             node-role.kubernetes.io/master:NoSchedule 
> 
> >不仅不会调度，还会驱逐Node上的Pod
> > >Taints:             node-role.kubernetes.io/master:NoExecute 
> 
> >尽量不要调度到此Node
> > >Taints:             node-role.kubernetes.io/master:PreferNoSchedule

## deployments/deploy

```shell
kubectl get deployments
kubectl get deploy
kubectl describe deployment
kubectl create deployment rabbitmq --image=rabbitmq:3.9-management-alpine
kubectl get po/rabbitmq-66b64b6c68-tnnc2 deploy/rabbitmq
kubectl delete deploy rabbitmq
```

## pods/po

```shell
kubectl get po
kubectl get pods
kubectl get pods -l app=rabbitmq
kubectl get pods -l version=v1
kubectl get pods -o wide
kubectl get pods -o json

kubectl describe pods
kubectl describe pods rabbitmq-66b64b6c68-lwbfn
kubectl describe pods -l app=rabbitmq
curl http://localhost:8001/api/v1/namespaces/default/pods/rabbitmq-66b64b6c68-lwbfn

kubectl logs rabbitmq-66b64b6c68-lwbfn
kubectl logs -l app=rabbitmq

kubectl exec -ti rabbitmq-66b64b6c68-lwbfn -- bash
```

## services/svc

```shell
kubectl get svc
kubectl get services -l app=rabbitmq

kubectl expose deployment/rabbitmq --type="NodePort" --port 15672

kubectl describe services/rabbitmq

kubectl delete services -l app=rabbitmq
kubectl delete service rabbitmq-66b64b6c68-lwbfn
```

## label

```shell
kubectl label pods rabbitmq-66b64b6c68-lwbfn version=v1
kubectl get pods -l version=v1
```

## scale

```shell
kubectl scale deployments/kubernetes-bootcamp --replicas=4
kubectl get pods -o wide
```

## update

```shell
kubectl set image deployments rabbitmq rabbitmq=rabbitmq:3.8.26-management-alpine
kubectl rollout status deployments rabbitmq
kubectl rollout undo deploy rabbitmq
```

## [HOME](../../../index.md)