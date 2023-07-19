# git

| [home](index.md)  | [reference](https://git-scm.com/docs/) | [frequently](#frequently) |
| :---------------- | :------------------------------------- | :------------------------ |
| [clone](#clone)   | [查看其他分支文件](#show)              | [ssh](#ssh)               |
| [branch](#branch) | [初始化仓库](#init)                    | [gpg](#gpg)               |
| [commit](#commit) | [gitlab新建仓库](#gitlab)              | [config](#config)         |
| [merge](#merge)   | [git-flow](#git-flow)                  |
| [rebase](#rebase) | [reflog](#reflog)                      |
| [push](#push)     |
| [tag](#tag)       |

## frequently

| [top](#git)                                              | [home](index.md)                          |
| :------------------------------------------------------- | :---------------------------------------- |
| 提交记录                                                 | `git log --pretty=oneline`                |
| 仓库状态                                                 | `git status`                              |
| 追加修改到仓库                                           | `git add .`                               |
| 提交commit                                               | `git commit -m "feat: init"`              |
| fetch 更新 origin 仓库 的指定分支                        | `git fetch origin remote:local`           |
| push 提交 本地 develop 分支 到 origin 仓库 develop2 分支 | `git push origin develop:develop2`        |
| 查看其他分支文件                                         | `git show develop:README.md`              |
| 刷新以应用新的配置文件                                   | `git rm --cached -r` & `git reset --hard` |
| 远程分支删除后，本地同步清理                              | `git remote prune origin` |

[top](#git) | [home](index.md)

## git-flow

> [推荐阅读git-flow](https://www.cnblogs.com/wish123/p/9785101.html)\
> 分支名称规范
> > `master` or `main` 主分支
> >
> > `hotfix`
> > > 生产环境版本紧急缺陷修复发布分支\
> > > 由 master 分支 fork\
> > > 可 merge 到 develop、master,完全 merge 后可删除分支
> >
> > `release`
> > > 灰度发布分支、公测发布分支\
> > > 由 develop fork\
> > > 可 merge 到 master，完全merge后可移除当前分支\
> > > 除了修复 bug 提交外不可 commit 其他内容，
> >
> > `develop`
> > > 开发主轴\
> > > 不建议直接 commit\
> > > 由 master 或 hotfix 分支 fork\
> > > 可 merge 到 release\
> > > 可从 release、hotfix、feature 分支merge
> >
> > `feature`
> > > 新功能分支
> > > 可 merge 到 develop,完全 merge 后可删除分支
>
> commit message type
> > `feat` 新功能\
> > `fixBug` 修复bug\
> > `docs` 文档相关\
> > `style` 代码格式化相关\
> > `refactor` 重构\
> > `perf` 性能优化\
> > `test` 测试相关\
> > `build` 构建系统或包依赖相关\
> > `ci` CI配置，脚本文件相关\
> > `chore` c库或测试文件相关\
> > `revert` commit 回退

[top](#git) | [home](index.md)

## config

> 说明：
> > 全局配置目录 windows10 `C:\Users\xxx\.gitconfig`\
> > 当前工作目录文件 `workdir/.git/config`

> example `git config -h`
> > 查看 `global` 全部 配置 `git config -l` or `git config -l --global` \
> > 查看 `local` 全部 配置  `git config -l --local`

> user & email
> > .git/config
> > ```
> > [user]
> > 	name = xxx
> > 	email = xxx@xx.com
> > ```
> >
> > 全局 git user
> > > `git config --global user.name xxx`\
> > > `git config --global user.email xxx@xx.com`
> >
> > 单库 git user
> > > `git config user.name xxx` or `git config --local user.name xxx`\
> > > `git config user.email xxx@xx.com` or `git config --local user.email xxx@xx.com`

> crlf & lf
> > [GitHub reference](https://docs.github.com/en/get-started/getting-started-with-git/configuring-git-to-handle-line-endings)\
> > [git reference](https://git-scm.com/book/en/v2/Customizing-Git-Git-Configuration)
>
> core.autocrlf false
> > If you’re a Windows programmer doing a Windows-only project, then you can turn off this functionality, \
> > recording the carriage returns in the repository by setting the config value to false
>
>core.autocrlf true
>> If you’re on a Windows machine, set it to true this converts LF endings into CRLF when you check out code:
>
>core.autocrlf input
>> If you’re on a Linux or macOS system that uses LF line endings, \
> > then you don’t want Git to automatically convert them when you check out files; \
> > however, if a file with CRLF endings accidentally gets introduced, then you may want Git to fix it. \
> > You can tell Git to convert CRLF to LF on commit but not the other way around by setting core.autocrlf to input: \
> > This setup should leave you with CRLF endings in Windows checkouts, \
> > but LF endings on macOS and Linux systems and in the repository.

***主要应对 shell 脚本、bat 批处理文件等对换行符敏感的语言***

***不建议直接 copy 文件；手动创建文件 copy 文件内容相对稳妥***

***当添加配置文件或使用参数配置后，并不会生效。配置文件方式重新clone代码即可生效，`git config` 删掉除.git的全部文件,手动下载仓库代码，然后解压把代码放进去即可***

> 配置 core.autocrlf

- 全局
    - linux & mac `git config --global core.autocrlf input`
    - windows `git config --global core.autocrlf true` 安装时默认设置为 true ![image](img/crlf_lf.gif)
- 单库
    - linux & mac `git config --local core.autocrlf input`
    - windows `git config --local core.autocrlf true`

> .git/config file 配置
> ```text
> # 全局 windows C:\Users\xxx\.gitconfig
> [core]
> 	autocrlf = true
> 
> # 单库 .git/config
> [core]
> 	autocrlf = true
> ```

***The .gitattributes file must be created in the root of the repository and committed like any other file.***

> .gitattributes
> ```properties
> #
> # https://help.github.com/articles/dealing-with-line-endings/
> #
> # Set the default behavior, in case people don't have core.autocrlf set.
> # text=auto Git will handle the files in whatever way it thinks is best. This is a good default option
> # * text=auto
> # text eol=crlf Git will always convert line endings to CRLF on checkout. You should use this for files that must keep CRLF endings, even on OSX or Linux.
> # * text eol=crlf
> # text eol=lf Git will always convert line endings to LF on checkout. You should use this for files that must keep LF endings, even on Windows.
> * text eol=lf
> # Explicitly declare text files you want to always be normalized and converted
> # to native line endings on checkout.
> *.c text
> *.h text
> *.cpp text
> *.hpp text
> *.cmake text
> *.sh text
> *.md text
> *.java text
> *.py text
> *.sql text
> *.xml text
> *.yml text
> *.yaml text
> *.properties text
> *.gradle text
> *.pom text
> # Declare files that will always have CRLF line endings on checkout.
> *.sln text eol=crlf
> *.bat text eol=crlf
> # Denote all files that are truly binary and should not be modified.
> *.png binary
> *.jpg binary
> *.jpeg binary
> ```

> 刷新以应用新的配置文件
> ```shell
> git rm --cached -r
> git reset --hard
> ```


[top](#git) | [home](index.md)

## tag

> **tag name 与 branch name 不能相同** \
> [参考文档](https://git-scm.com/book/en/v2/Git-Basics-Tagging) \
> `git tag` 列出标签 \
> `git tag -l "0.0.*"` 查找标签0.0.开头的标签，需要-l或者--list \
> `git tag 0.0.1` 创建轻量标签 不需要-a,-s,-m选项，只需要提供标签名字 \
> `git tag -a 0.0.2 -m "0.0.2版本"` 创建附注标签 可以被校验，包含打标签者的`name`，`email`，日期地址，标签信息，并且可以使用GNU Privacy Guard（GPG）签名验证 \
> `git tag -a 0.0.3 e37da745df4102b68b81d0ec681ba28e910d2344` 对某一提交码打标签 \
> `git show 0.0.1` 查看信息 \
> `git push origin 0.0.1` 推送单个tag到远程仓库 \
> `git push origin --tags` 推送全部标签到远程仓库 \
> `git tag -d 0.0.1` 删除标签 \
> `git push origin --delete 0.0.1` 删除远程仓库上的标签

[top](#git) | [home](index.md)

## push

> `gitlab` 默认将 `master` 分支设置为 `protect` 不允许强制 `push -f`

> [参考文档](https://git-scm.com/docs/git-push)
> > `git push` 当前分支与远程分支已关联\
> > `git push origin HEAD` 推送 HEAD 当前分支 到远程已关联的分支\
> > `git push origin local_develop` 推送 local_develop 分支到已关联的分支\
> > `git push origin HEAD:remote` 推送 HEAD 当前分支到远程 remote 分支\
> > `git push origin local_develop:remote_develop` 推送 local_develop 分支到远程 remote_develop 分支\
> > `git push -f origin develop` 强制提交 风险极高 本地commit会强制覆盖远程commit

[top](#git) | [home](index.md)

## gpg

> 新增 gpg
> > `gpg --full-generate-key` 根据提示输入`用户名`，`邮箱`，`密钥长度4096`,`过期时间`，`是否信任`，`github` 要注意邮箱要是 github 验证过的邮箱\
> > `gpg --list-keys` 列出本地存储的所有gpg密钥信息\
> > `gpg --armor --export xxxx(pub key)` 打印公钥字符串，复制添加到git仓库 \
> > `git config --global user.signingkey {key_id}` 全局使用此gpg \
> > `git commit -S -m "xxx"` `-S` 表示这次提交需要使用GPG密钥签名 \
> > `git config --local commit.gpgsign true` 当前仓库每次commit 时自动要求签名

> gpg 过期处理
> > `gpg --list-keys` 列出本地存储的所有gpg密钥信息 \
> > `gpg --edit-key xxxxxxx` 进入编辑模式 
> > > `gpg>expire`  更新过期日期\
> > > `gpg>trust`   添加信任模式\
> > > `gpg>save`   保存
> >
> > `gpg --armor --export xxxx(pub key)` 打印公钥字符串，删除旧的，复制添加到git仓库即可

[top](#git) | [home](index.md)

## ssh

> 添加 ssh 私钥 **github 需要设置密码**[参考](https://docs.github.com/en/authentication/connecting-to-github-with-ssh)

> win10 powershell
> > `ssh-keygen -t rsa -b 2048  -C "xxx@xx.com"`
> > `clip` 复制到粘贴板 
> > `cat ~/.ssh/caddyRen_rsa.pub | clip`
>
> linux
> > `ssh-keygen -t rsa -b 2048  -C "caddyRen@qq.com"`
> > `cat ~/.ssh/caddyRen_rsa.pub`

> 多 ssh 配置 `vim ~/.ssh/config`
> ```text
> Host git.parkere.cn
> HostName git.parkere.cn
>   PreferredAuthentications publickey
>   IdentityFile ~/.ssh/work_rsa
> Host bougainvilleas
>   HostName github.com
>   PreferredAuthentications publickey
>   IdentityFile ~/.ssh/bougainvilleas_rsa
> Host caddyRen
>   HostName github.com
>   PreferredAuthentications publickey
>   IdentityFile ~/.ssh/caddyRen_rsa
> ```

> `Host` 替换掉真实域名 `git clone git@bougainvilleas:bougainvilleas/lotus.git` \
> `HostName` 真实域名 `git clone git@github.com:bougainvilleas/lotus.git`

> 检测
> ```shell
> ssh git@git.parkere.cn
> ssh git@caddyRen
> ssh git@bougainvilleas
> ```

[top](#git) | [home](index.md)

## reflog

> [参考文档](https://git-scm.com/docs/git-reflog)\
> 查看本地git操作日志 `git reflog`\
> 回滚到02a3260 `git reset --hard 02a3260`\
> 远程提交回退 `git push origin HEAD --force`

[top](#git) | [home](index.md)

## rebase

> [参考文档](https://git-scm.com/docs/git-rebase)
> 
> 将b1的基线变更为b2
> > 当前在b1分支 `git rebase b2`\
> > 当前不在b1分支 `git rebase b2 b1`
> 
> ***仅推荐在本地分支push前合并未提交到远程的 commit，不熟悉rebase机制 易造成 git 提交顺序日期混乱***\
> ***有冲突，解决冲突（建议使用idea解决冲突）,将解决冲突后的文件 add 加入仓库，rebase 每个 commit 都可能会有冲突，依次解决所有 commit 的冲突***
> > 冲突
> > > ```shell
> > > git add .
> > > git rebase --continue #(继续)
> > > git rebase --skip #(跳过)
> > > git rebase --abort #(取消 rebase)
> > > ```
> >
> > 合并多个未提交远程 commit\
> > 注意: \
> > ***仅限本地分支进行***\
> > ***切勿对已提交的commit合并***\
> > ***可使用 [reflog](#reflog) 进行回退***
> > > 1. 在当前分支的起始点（如提交码为12345678），rebase时以此节点为基础 rebase `git rebase -i 12345678`
> > > 2. 自动进入 vim 编辑模式，可以看到 12345678 到当前的所有 commit 信息
> > > 3. 第一行pick commit，其余行squash，wq保存
> > > 4. 编辑 git message 作为合并后的 commit message，wq保存
> > > 5. 有冲突手动解决后执行`git add .`和`git --continue`,直到所有冲突解决完成，则选择的commit会合并为一个commit

[top](#git) | [home](index.md)

## merge

> [参考文档](https://git-scm.com/docs/git-merge)\
> ***merge 之前 两个分支都要先 commit***

1. 当前分支 develop 将 temp 分支合并到develop `git merge temp`
2. 产生冲突（IDEA工具更方便处理冲突）
3. 手动处理冲突代码执行`git add 冲突文件` 然后 `git commit -m "feat: merge temp into develop"`

[top](#git) | [home](index.md)

## init

### local

```shell
# 初始化不指定分支名称 默认创建 master 分支
git init -b <branch-name> --initial-branch=<branch-name>

git init -b develop
git config --local user.email "xxx@xx.com"
git config --local user.name "caddy"
git status
git add README.md
git commit -m "feat: init"
git log --pretty=oneline

# 添加远程分支
# 可以添加多个远程仓库 比如一份代码需要提交到两个仓库
git remote add origin https://xxx/temp.git
git remote
git fetch origin

# gitlab 默认将 master 分支设置为 protect 不允许强制 push
# git push -f 强制提交 风险极高 本地commit会强制覆盖远程commit
git push -f origin develop
```

[init](#init) | [top](#git) | [home](index.md)

### gitlab

#### Create a new repository

```shell
git clone URL
cd testcaddy
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master
```

#### Push an existing folder

```shell
cd existing_folder
git init
git remote add origin URL
git add .
git commit -m "Initial commit"
git push -u origin master
```

#### Push an existing Git repository

```shell
cd existing_repo
git remote rename origin old-origin
git remote add origin URL
git push -u origin --all
git push -u origin --tags
```

[init](#init) | [top](#git) | [home](index.md)

## commit

> 修改 `commit message`
> > 未提交 远程 `git commit --amend`
> > 已提交 远程
> > > ```shell
> > > git reset --soft HEAD~1
> > > git commit -m "new message"
> > > git push -f origin branchname
> > > ```

[top](#git) | [home](index.md)

## show

> 查看其他分支文件 `git show develop:README.md`

[top](#git) | [home](index.md)

## clone

> `git clone xxx`

[top](#git) | [home](index.md)

## branch

> 查
> > 查看本地分支 `git branch`\
> > 查看远程分支 `git branch -r`\
> > 查看全部分支 `git branch -a`\
> > 查看分支详情 `git branch -v -a`
>
> 增
> > 创建不切换
> > > 以当前分支为基线创建temp分支 `git branch temp`\
> > > 以base为基线创建temp分支,***base可以为tag或提交码*** `git branch temp base`
> >
> > 切换 `git checkout temp`
> >
> > 创建并切换
> > > 以当前分支为基线创建temp分支,并切换到temp分支 `git checkout -b temp`\
> > > 以base为基线创建temp分支并切换到temp,***base可以为tag或提交码*** `git checkout -b temp base`\
> > > 以远程仓库origin内的develop分支为基线创建develop分支，并切换到develop分支 `git checkout -b develop origin/develop`
>
> 删
> > 远程分支删除后，本地同步清理 `git remote prune origin` \
> > 删除本地`temp`分支 `git branch -d temp`
> > > `-d` delete fully merged branch \
> > > `-D` delete branch (even if not merged)
> >
> > 删除远程`master`分支 `git push origin --delete master`
>
> 改
> > Rename your local `master` branch into `main` with: `git branch --move master main`\
> > 将当前分支与远程`main`分支绑定`git push --set-upstream origin main`

[top](#git) | [home](index.md)