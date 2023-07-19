<div style="text-align: center;font-size: 40px;">opensuse 自启动脚本</div>

> 旧电脑启动后需要 `systemctl restart wicked` 才可以ping 通

## opensuse 启动后执行脚本

```shell
# micro os 测试可行
mkdir -p /etc/init.d
touch /etc/init.d/after.local
vim /etc/init.d/after.local
chmod +x /etc/init.d/after.local
reboot
```

### after.local

```shell
#!/usr/bin/sh
/usr/bin/systemctl restart wicked
/usr/bin/echo "测试是否执行脚本" > /root/test.log
```