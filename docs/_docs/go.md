---
title: golang
targets:
  - name: Top
    link: go#command
  - name: go command
    link: go#command
---

## command

> init 初始化 `go mod init example.com/greetings` \
> run 运行不会生成二进制文件 `go run hello.go` or `go run .` \
> test 在有单元测试的`module`中运行 `go test` or `go test -v` -v flag to get verbose \
> build 打包 `go build` 在当前目录打包可执行二进制文件 \
> install 安装`go install` 安装二进制文件到 `～/go/bin/` 目录下 \
> dependency
> > 配置国内加速镜像
> > > 查看全部配置  `go env` \
> > > 查看部分配置  `go env | grep GOPROXY` \
> > > 官方 `go env -w GOPROXY=https://goproxy.io,direct` \
> > > 七牛 `go env -w GOPROXY=https://goproxy.cn,direct` \
> > > 阿里云 `go env -w GOPROXY=https://mirrors.aliyun.com/goproxy/,direct`
> > >
> > > 多个地址
> > > > 逗号分割时，第一个URL return HTTP 404 or 410.将尝试第二个`go env -w GOPROXY=https://goproxy.cn,direct,https://mirrors.aliyun.com/goproxy` \
> > > > 管道符号分割时，将尝试下一个URL,不管HTTP error code `go env -w GOPROXY=https://goproxy.cn,direct|https://mirrors.aliyun.com/goproxy`
> >
> > 查看指定依赖
> > > `go list -m example.com/greetings` \
> > > `go list -m example.com/greetings@version` \
> > > `go list -m example.com/greetings@gitbranch` \
> > > `go list -m example.com/greetings@gitcommitcode` \
> > > 列出所有依赖模块信息，当前使用版本以及最新版本 `go list -m -u all` \
> > > 列出指定依赖模块信息，当前使用版本以及最新版本 `go list -m -u example.com/greetings`
> >
> > 添加指定依赖
> > > `go get example.com/greetings` \
> > > `go get example.com/greetings@version` \
> > > `go get example.com/greetings@gitbranch` \
> > > `go get example.com/greetings@gitcommitcode`
> >
> > 将依赖指向本地文件系统
> > > `go mod edit -replace=example.com/greetings=../greetings` \
> > > `go mod edit -replace=example.com/greetings@version=../greetings`
> >
> > 整理项目 会移除未使用的 modules 会自动添加 `go.mod` 中构建包所需的 modules
> > > `go mod tidy`
> >
> > 移除依赖 `@none`
> > > `go get example.com/theirmodule@none`
> >
> > 清空模块缓存
> > > 模块缓存位置 `$GOPATH/pkg/mod` 查看 GOPATH`go env | grep GOPATH`\
> > > `go clean --modcache`