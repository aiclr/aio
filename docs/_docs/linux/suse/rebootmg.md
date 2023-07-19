<div style="text-align: center;font-size: 40px;">opensuse Kubic 默认凌晨三点六重启系统</div>

> [opensuse wiki](https://en.opensuse.org/Kubic:Update_and_Reboot) \
> [rebootmgrctl](https://kubic.opensuse.org/documentation/man-pages/rebootmgrctl.1.html) 
> 
> Disable Automatic Updates
> > `systemctl --now disable transactional-update.timer`
> 
> Disable Rebootmgr
> > `systemctl --now disable rebootmgr` \
> > or \
> > `rebootmgrctl set-strategy off`

## /etc/rebootmgr.conf A default configuration file would be:

```text
[rebootmgr]
window-start=03:30
window-duration=1h30m
strategy=best-effort
lock-group=default
```