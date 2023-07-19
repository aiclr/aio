<div style="text-align: center;font-size: 40px;">kubic</div>

> https://en.opensuse.org/Kubic:Installation 
> 
> 国内外国语大学开源软件镜像站
> > https://mirrors.bfsu.edu.cn/opensuse/tumbleweed/iso/openSUSE-Kubic-DVD-x86_64-Current.iso 
> 
> 国内外国语大学开源软件镜像仓库
> > https://mirrors.bfsu.edu.cn/opensuse/tumbleweed/repo/ \
> > https://mirrors.bfsu.edu.cn/opensuse/tumbleweed/repo/oss \
> > https://mirrors.bfsu.edu.cn/opensuse/tumbleweed/repo/non-oss
> 
> 注意
> > [openSUSE-Kubic 默认有定时重启系统和滚动更新系统的服务](https://en.opensuse.org/Kubic:Update_and_Reboot)
> > > `systemctl status transactional-update.timer` 自动滚动更新 \
> > > `systemctl status rebootmgr.service` [重启系统](../../suse/rebootmg.md) \
> > > `rebootmgrctl get-window` 查看维护开始时间和持续时间 默认 window-start=03:30 window-duration=1h30m

## [kubeadm](https://en.opensuse.org/Kubic:kubeadm)

1. 安装选择 kubeadm Node
2. 设置时区 Asia/Shanghai
3. 设置 hostname & ip & dns & gateway`virtualbox nat 10.0.0.1`

### network

```shell
ip link
# 激活网卡
ip link set wlp2s0 up
# 关闭网卡
ip link set wlp2s0 down
# 查看网卡状态
ifstatus wlp2s0
# 连接网络
ifup wlp2s0
# 断开
ifdown wlp2s0
```

#### 网络配置

```
BOOTPROTO='dhcp'
STARTMODE='auto'
WIRELESS_ESSID='fiy'
WIRELESS_AUTH_MODE='PSK'
WIRELESS_MODE='managed'
WIRELESS_WPA_PSK='fiy123456'
WIRELESS_AP_SCANMODE='1'
WIRELESS_NWID=''

IPADDR='192.168.1.10/24'
BOOTPROTO='static'
STARTMODE='auto'
WIRELESS_ESSID='fiy'
WIRELESS_AUTH_MODE='PSK'
WIRELESS_MODE='managed'
WIRELESS_WPA_PSK='fiy123456'
WIRELESS_AP_SCANMODE='1'
WIRELESS_NWID=''
```

### ssh

```shell
echo "PermitRootLogin yes" >> /etc/ssh/sshd_config
# 重启 ssh
systemctl restart sshd.service
service sshd restart
```

### master

```shell
kubeadm init --image-repository registry.aliyuncs.com/google_containers
# 根据提示 复制配置文件
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
# Setup weave
kubectl apply -f /usr/share/k8s-yaml/weave/weave.yaml
#You may need to reboot the master after applying this change if you see runtime network not ready: NetworkReady=false reason:NetworkPluginNotReady message:Network plugin returns error: Missing CNI default network when runnnig kubectl describe nodes.
# 输出join信息
kubeadm token create --print-join-command
```

### slave

```shell
mkdir .kube
scp root@10.0.0.6:/etc/kubernetes/admin.conf $HOME/.kube/config
# 在 master 输出join信息
kubeadm token create --print-join-command
kubeadm join 10.0.0.6:6443 --token 7eagfo.s93tp0nxirady7w7 \
	--discovery-token-ca-cert-hash sha256:82b4363e47a2e37d0a40298c37d6f9129fdae982cc403b94585bfabdd4351afb
# 设置nodes roles
master:~ # kubectl label no slave kubernetes.io/role=slave
node/slave labeled
```

### check

```shell
master:~ # kubectl get nodes
NAME     STATUS     ROLES                  AGE   VERSION
master   NotReady   control-plane,master   2m    v1.22.2
master:~ # kubectl get pod -n kube-system
NAME                             READY   STATUS     RESTARTS   AGE
coredns-7f6cbbb7b8-bwd6h         0/1     Pending    0          2m29s
coredns-7f6cbbb7b8-tx95n         0/1     Pending    0          2m29s
etcd-master                      1/1     Running    0          2m36s
kube-apiserver-master            1/1     Running    0          2m37s
kube-controller-manager-master   1/1     Running    0          2m28s
kube-proxy-7wsmt                 1/1     Running    0          2m29s
kube-scheduler-master            1/1     Running    0          2m34s
weave-net-5ksh4                  0/2     Init:0/1   0          48s
master:~ # kubectl get pods -n kube-system -l name=weave-net -o wide
NAME              READY   STATUS    RESTARTS      AGE   IP         NODE     NOMINATED NODE   READINESS GATES
weave-net-5ksh4   2/2     Running   1 (14m ago)   78m   10.0.0.6   master   <none>           <none>
master:~ # reboot

master:~ # kubectl get pod -n kube-system
NAME                             READY   STATUS    RESTARTS      AGE
coredns-7f6cbbb7b8-bwd6h         1/1     Running   0             80m
coredns-7f6cbbb7b8-tx95n         1/1     Running   0             80m
etcd-master                      1/1     Running   1             80m
kube-apiserver-master            1/1     Running   1             80m
kube-controller-manager-master   1/1     Running   1             80m
kube-proxy-7wsmt                 1/1     Running   1             80m
kube-scheduler-master            1/1     Running   1             80m
weave-net-5ksh4                  2/2     Running   1 (15m ago)   78m
```

### k8s一些指令

```shell
kubeadm reset
kubectl get nodes
kubectl get namespaces
kubectl get pods -n kube-system
kubectl get pod -n kube-system
kubectl get pod --all-namespaces
kubectl get pods
kubectl get pods -n kube-system -l name=weave-net -o yaml | grep IP
kubectl drain slave --delete-local-data --ignore-daemonsets
kubectl delete node slave
kubectl cluster-info

kubectl describe deployment nginx-deployment
kubectl describe pod nginx-deployment-66b6c48dd5-p9kbk
```

### [包管理工具 zypper](../../suse/zypper.md)