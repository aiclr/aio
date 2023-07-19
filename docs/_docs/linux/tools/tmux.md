<div style="text-align: center;font-size: 40px;">tmux 终端复用</div>

- archlinux 安装`pacman -S tmux`
- 帮助`ctrl+b`激活控制台等待输入指令`ctrl+b ?`
- 查看所有 tmux session`tmux ls`或`ctrl+b s`
- 新建 tmux session`tmux`或`tmux new -s <session-name>`
- 重命名 session`tmux rename-session -t <old-name> <new-name>`或`ctrl+b $`
- 退出单不关闭session`tmux detach`或`ctrl+b d`
- 重连 detach session`tmux attach -t <session-name>`或`tmux at -t <session-name>`
- 退出当前session并关闭当前session`exit`
- kill session`tmux kill-session -t <session-name>`
- switch session`tmux switch -t <session-name>`
- 平铺当前 session(再次执行该命令恢复)`ctrl+b z`
- 上下划分两个窗格`ctrl+b “`
- 左右划分两个窗格`ctrl+b %`
- 窗格内切换光标 `ctrl+b 方向键`
    - 上`ctrl+b 方向键上`或`tmux select-pane -U`
    - 下`ctrl+b 方向键下`或`tmux select-pane -D`
    - 左`ctrl+b 方向键左`或`tmux select-pane -L`
    - 右`ctrl+b 方向键右`或`tmux select-pane -R`