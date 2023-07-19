<div style="text-align: center;font-size: 40px;">firewald 防火墙策略</div>

> [Arch wiki](https://wiki.archlinux.org/title/Firewalld) \
> 安装 `pacman -S firewalld` \
> 启动 firewalld 服务,使用 `firewall-cmd` 配置规则 \
> 不启动 firewalld 服务可以使用 `firewall-offline-cmd` 配置规则
>

### 选项 `--permanent` 与 `--timeout` 互斥

- 不使用 `--permanent` 选项配置规则，会直接修改运行时配置，但是重启服务后会被还原
    - 使用 `firewall-cmd --runtime-to-permanent` 可将运行时规则持久化，重启后依然生效
- 使用 `--permanent` 选项配置规则，当前运行时的配置不会被修改，可以使用下面任意操作应用规则
    - 重启服务`systemctl restart firewalld`
    - 重载规则`firewall-cmd --reload`

### 选项 `--timeout` 与 `--permanent` 互斥

- 在有限的时间内添加服务或端口
- `--timeout=value` 
    - `--timeout=3h` 3 hours
    - `--timeout=3m` 3 minutes
    - `--timeout=3s` 3 seconds
- 例如：开放 ssh服务 3小时 `firewall-cmd --add-service ssh --timeout=3h`


### Services

```shell
# 查看开放服务
firewall-cmd --list-service

# To get a list of available services
firewall-cmd --get-services

# query information about a particular service
firewall-cmd --info-service samba

# To add a service to a zone
firewall-cmd --zone=zone_name --add-service service_name

# To add a service to a zone --permanent need firewall-cmd --reload
firewall-cmd --zone=zone_name --add-service service_name --permanent
firewall-cmd --reload

# Removing a service
firewall-cmd --zone=zone_name --remove-service service_name

# To add a service to a zone --permanent need firewall-cmd --reload
firewall-cmd --zone=zone_name --remove-service service_name --permanent
firewall-cmd --reload
```

### ports

> `firewall-cmd --zone=zone_name --add-port port_num/protocol` \
> There `protocol` is either `tcp` or `udp`

```shell
# 查看开放端口
firewall-cmd --list-ports

# 开放端口 There protocol is either tcp or udp.
firewall-cmd --zone=zone_name --add-port port_num/protocol

# 开放端口
firewall-cmd --zone=home --add-port=66/tcp

# 开放端口 --permanent
firewall-cmd --zone=home --add-port=66/tcp --permanent
firewall-cmd --reload

# 关闭端口
firewall-cmd --zone=zone_name --remove-port port_num/protocol

# 关闭端口
firewall-cmd --zone=home --remove-port=66/tcp

# 关闭端口 --permanent
firewall-cmd --zone=home --remove-port=66/tcp --permanent
firewall-cmd --reload

```

### Zones

> Zone is a collection of rules that can be applied to a specific interface.\
> Some commands (such as adding/removing ports/services) require a zone to specified. \
> Zone can be specified by name by passing `--zone=zone_name` parameter. \
> If no zone is specified `default zone` is assumed.


```shell
# To have an overview of the current zones and interfaces
firewall-cmd --get-active-zones

# list all the zones with entirety their configuration
firewall-cmd --list-all-zones

# just a specific zone configuration
firewall-cmd --info-zone=zone_name

# Changing zone of an interface
firewall-cmd --zone=new_zone --change-interface=interface_name

# List connection profiles
nmcli connection show

# Assign the "myssid" profile to the "home" zone
nmcli connection modify myssid connection.zone home

# query the name of the default zone
firewall-cmd --get-default-zone

# put the name of the default zone
firewall-cmd --set-default-zone=new_zone
```