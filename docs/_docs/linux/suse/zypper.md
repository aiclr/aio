<div style="text-align: center;font-size: 40px;">zypper</div>

## 原子更新

```shell
transactional-update shell
zypper ref #刷新
zypper dup #更新
exit
transactional-update cleanup #清理未使用的系统快照
```

## zypper

### repo

```shell
Repository Management:
  repos, lr             List all defined repositories.
  addrepo, ar           Add a new repository.
  removerepo, rr        Remove specified repository.
  renamerepo, nr        Rename specified repository.
  modifyrepo, mr        Modify specified repository.
  refresh, ref          Refresh all repositories.
  clean, cc             Clean local caches.
# 例子
zypper repos -d # 查看源详细信息
zypper mr -da # 禁用全部源
# 添加国内源
zypper addrepo -f https://mirrors.bfsu.edu.cn/opensuse/tumbleweed/repo/non-oss bfsu-non-oss
zypper addrepo -f https://mirrors.bfsu.edu.cn/opensuse/tumbleweed/repo/oss/ bfsu-oss
zypper ar -f https://mirrors.bfsu.edu.cn/opensuse/tumbleweed/repo/non-oss bfsu-non-oss
zypper ar -f https://mirrors.bfsu.edu.cn/opensuse/tumbleweed/repo/oss/ bfsu-oss
 
zypper refresh # 刷新
zypper ref # 刷新
zypper dup # 更新
```

### package

```shell
zypper in docker # 安装docker

master:~/parking # cat /etc/docker/daemon.json
{
  "registry-mirrors":["https://rjm3pmfv.mirror.aliyuncs.com"]
}
zypper in docker-compose # 安装docker-compose
zypper in htop # htop
zypper in tmux # tmux
zypper in lrzsz # lrzsz
zypper in zsh # zsh
zypper in git # git
# 安装 ohmyzsh
sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

zypper in podman # 安装 podman
zypper in nfs-kernel-server # 安装 NFS
```