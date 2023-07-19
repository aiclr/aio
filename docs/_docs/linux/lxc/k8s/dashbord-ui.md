<div style="text-align: center;font-size: 40px;">Dashboard UI</div>

## 外部访问

- 下载 recommended.yaml `https://raw.githubusercontent.com/kubernetes/dashboard/v2.4.0/aio/deploy/recommended.yaml`
- 改造 [recommended.yaml](recommended.yaml) to  [dashboard.yaml](dashboard.yaml)文件
- 部署 `kubectl apply -f dashboard.yaml`
- https 访问 `https://192.168.1.100:30003`
- 获取 ServiceAccount kubernetes-dashboard token 登录
    1. 查看所有 secrets `kubectl get secrets -n kubernetes-dashboard`
    2. 查看 secrets token `kubectl describe secrets/kubernetes-dashboard-token-f6pcf -n kubernetes-dashboard`

#### 改动 service 使用 NodePort 直接暴露服务

```yaml
kind: Service
apiVersion: v1
metadata:
  labels:
    k8s-app: kubernetes-dashboard
  name: kubernetes-dashboard
  namespace: kubernetes-dashboard
spec:
  type: NodePort # 暴露 服务
  ports:
    - port: 443
      targetPort: 8443
      nodePort: 30003
  selector:
    k8s-app: kubernetes-dashboard
```

#### 改动 ServiceAccount 权限为 cluster-admin

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: kubernetes-dashboard
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin # 管理员才有权限 查看 资源 与 服务
subjects:
  - kind: ServiceAccount
    name: kubernetes-dashboard
    namespace: kubernetes-dashboard
```

---

## localhost

### [Dashboard UI](https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/)

> https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/ \
> https://github.com/kubernetes/dashboard/blob/master/docs/user/accessing-dashboard/README.md

```shell
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.4.0/aio/deploy/recommended.yaml

slave:~ # kubectl get namespaces
NAME                   STATUS   AGE
default                Active   3h36m
kube-node-lease        Active   3h36m
kube-public            Active   3h36m
kube-system            Active   3h36m
kubernetes-dashboard   Active   36m
slave:~ # kubectl get pods -n kubernetes-dashboard
NAME                                        READY   STATUS    RESTARTS   AGE
dashboard-metrics-scraper-c45b7869d-vrc6t   1/1     Running   0          43m
kubernetes-dashboard-576cb95f94-87lz5       1/1     Running   0          43m
slave:~ # kubectl get svc -n kubernetes-dashboard
NAME                        TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)    AGE
dashboard-metrics-scraper   ClusterIP   10.100.244.14    <none>        8000/TCP   43m
kubernetes-dashboard        ClusterIP   10.107.123.253   <none>        443/TCP    43m
```

- 在新 terminal 开启代理 `kubectl proxy`之后保持窗口
- 在本机浏览器访问 `http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/`
- 获取 ServiceAccount kubernetes-dashboard token 登录
    1. 查看所有 secrets `kubectl get secrets -n kubernetes-dashboard`
    2. 查看 secrets token `kubectl describe secrets/kubernetes-dashboard-token-f6pcf -n kubernetes-dashboard`
    3. 此时的用户 非 cluster-admin 无权限查看资源 参考上面 改动 ServiceAccount 权限为 cluster-admin

---

#### 删除

```shell
kubectl delete -f dashboard.yaml
```

#### deployment、pods、services

```shell
kubectl get deploy -n kubernetes-dashboard
kubectl delete deploy/kubernetes-dashboard -n kubernetes-dashboard

kubectl get po -n kubernetes-dashboard
kubectl delete po/kubernetes-dashboard -n kubernetes-dashboard

kubectl get svc -n kubernetes-dashboard
kubectl delete svc/kubernetes-dashboard -n kubernetes-dashboard
```

### cluster role、ServiceAccount、ClusterRoleBinding、RoleBinding

```shell
kubectl get clusterrole -n kubernetes-dashboard
kubectl delete clusterrole/kubernetes-dashboard -n kubernetes-dashboard


kubectl get serviceaccount -n kubernetes-dashboard
kubectl delete serviceaccount/kubernetes-dashboard -n kubernetes-dashboard


kubectl get ClusterRoleBinding -n kubernetes-dashboard
master:~ # kubectl get ClusterRoleBinding -n kubernetes-dashboard
NAME                                                   ROLE                                                                               AGE
kubernetes-dashboard                                   ClusterRole/cluster-admin                                                          92m
# 删除
kubectl delete ClusterRoleBinding/kubernetes-dashboard -n kubernetes-dashboard


kubectl get RoleBinding -n kubernetes-dashboard
master:~ # kubectl get RoleBinding -n kubernetes-dashboard
NAME                   ROLE                        AGE
kubernetes-dashboard   Role/kubernetes-dashboard   93m
kubectl delete RoleBinding/kubernetes-dashboard -n kubernetes-dashboard
```

## [HOME](../../../index.md)