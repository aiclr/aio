# react study

> 查看源 `npm config get registry`\
> 设置淘宝加速镜像 `npm config set registry https://registry.npm.taobao.org`
> 切换官方镜像 `npm config set registry https://www.npmjs.org`
>
> 查看 依赖版本信息 `npm view react-stomp version `
>
> `-g` 全局安装 **create-react-app** 创建 react `sudo npm install -g create-react-app` \
> `-g` 全局安装 **create-expo-app** 创建 react-native `sudo npm install -g create-expo-app` \
> `-g` 全局安装 **npm-check-updates** 检查版本 `sudo npm install -g npm-check-updates`
> > 全局安装目录 `/usr/lib/node_modules/create-react-app/` \
> > 非全局安装位置在当前目录下的 **node_modules** 文件夹下
>
> 创建 `react` 项目 `npx create-react-app projectName` \
> 创建 `react-native` 项目 `npx create-expo-app projectName` [README](native/README.md) \
> 查看依赖版本 `ncu -i` \
> 升级依赖版本 `ncu -u` 
>
> React sets ref.current during the commit. Before updating the DOM, React sets the affected ref.current values to null.\
>  After updating the DOM, React immediately sets them to the corresponding DOM nodes.
> > react 在 commit 期间设置 ref.current. 在更新 DOM 之前，React 将受影响的 ref.current 值设置为 null.\
> > 更新 DOM 后，React 立即将 ref.current 设置为相应的 DOM 节点
>
> 多环境配置 `npm install dotenv --save`
> > 参考：
> > > [create-react-app](https://create-react-app.dev/docs/adding-custom-environment-variables) \
> > > [dotenv](https://github.com/motdotla/dotenv) \
> > > [参考](study/demo_websocket/)
> >
> > `create-react-app` 创建项目 \
> > Create a `.env` file in the root of your project \
> > > [可用配置文件名以及优先级](https://create-react-app.dev/docs/adding-custom-environment-variables/#what-other-env-files-can-be-used)
> > 
> > 关于启动环境 `NODE_ENV`
> > > `NODE_ENV`不能手动覆盖 `You cannot override NODE_ENV manually` \
> > > `npm start` 时 `NODE_ENV=development` \
> > > `npm test` 时 `NODE_ENV=test` \
> > > `npm  run build` 时 `NODE_ENV=production`

# tips

> 使用 `e.preventDefault()` [参考](study/demo_manage_state/src/FeedbackForm.js)
> > 阻止 form 表单 默认的 submit 提交行文 \
> > 阻止 herf 默认的跳转行为
>
> always declare component functions at the top level, and don’t nest their definitions
> > 在顶层声明 组件函数 不要嵌套定义组件
>
> React 可以根据 key 匹配到正确的状态 即使 他们在父组件中的顺序发生变化
>
> `Refs` 主要应用在 管理焦点，滚动位置 调用 React 未 expose 的浏览器API
> > 避免更改 React 管理的DOM节点。\
> > 修改 或向 React 管理的元素添加子元素或从中删除子元素可能会导致不一致的视觉结果或崩溃。\
> > 如果调用 remove() 删除 ODM ，再 setState 会造成异常，React 不知道怎么继续管理 DOM 
> >
> > 需要谨慎处理：
> > > 可以安全地修改 React 不会更新的 DOM 部分\
> > > 如始终为空的 `<div>`，那么React没有理由去更新它的子列表，则通过 ref 手动添加或删除元素较安全
        
## component

### 生命周期 lifecycle

> 装载 A component mounts when it’s added to the screen.\
> 更新 A component updates when it receives new props or state. This usually happens in response to an interaction.\
> 卸载 A component unmounts when it’s removed from the screen.


## useEffect

> 反应性代码块 \
> 描述一个可以启动停止的单独同步过程 \
> 从 effect 的如何启动停止这个角度思考问题，而不是从组件的（装载、更新、卸载）来思考 \

### 生命周期 lifecycle

> Effect’s body specifies how to start synchronizing: Effect 的业务代码指定如何开始一个同步 \
> The cleanup function returned by your Effect specifies how to stop synchronizing: Effect 的 return clean function 指定如何停止同步

> tips
> > 一些 Effects 不返回 cleanup function。通常都会希望返回一个 cleanup function \
> > 如果不返回，react 会假装返回了一个 空的 cleanup function （空什么都没做）


### 应用

> 应用场景
> > Controlling non-React widgets 控制非React控件
> > > 例如想将引入的第三方地图控件缩放比例与页面比例同步，可以使用如下代码\
> > >(并不需要返回 cleanup 函数,因为依赖项数组 [zoomLevel]，两次渲染时值不变，不会重复执行)
> > > ```
> > > useEffect(() => {
> > >  const map = mapRef.current;
> > >  map.setZoomLevel(zoomLevel);
> > > }, [zoomLevel]);
> > >```
> > > 例如需要 cleanup 函数的例子，两次渲染会触发两次弹窗 所以需要及时关闭弹窗
> > > ```
> > > useEffect(() => {
> > >   const dialog = dialogRef.current;
> > >   dialog.showModal();
> > >   return () => dialog.close();
> > > }, []);
> > > ```
> > Subscribing to events 订阅事件
> > > 如果 effect 订阅了事件 则需要cleanup函数取消订阅
> > > ```
> > > useEffect(() => {
> > >   function handleScroll(e) {
> > >     console.log(e.clientX, e.clientY);
> > >   }
> > >   window.addEventListener('scroll', handleScroll);
> > >   return () => window.removeEventListener('scroll', handleScroll);
> > > }, []);
> > >```
> > Triggering animations 触发动画
> > > 如果useEffect 触发动画效果，则需要cleanup函数重置动画效果
> > > ```
> > > useEffect(() => {
> > >   const node = ref.current;
> > >   node.style.opacity = 1; // Trigger the animation
> > >   return () => {
> > >     node.style.opacity = 0; // Reset to the initial value
> > >   };
> > > }, []);
> > >``` 
> > Fetching data 获取数据
> > > 如果 useEffect 请求获取一些数据，需要cleanup函数中止请求或忽略请求结果\
> > > (已经发生的网络请求无法手动撤销)
> > > ```
> > > useEffect(() => {
> > >   let ignore = false;
> > > 
> > >   async function startFetching() {
> > >     const json = await fetchTodos(userId);
> > >     if (!ignore) {
> > >       setTodos(json);
> > >     }
> > >   }
> > > 
> > >   startFetching();
> > > 
> > >   return () => {
> > >     ignore = true;
> > >   };
> > > }, [userId]);
> > >```
> > Sending analytics 发送分析日志
> > > 记录日志 开发环境由于react 开发模式会触发两次渲染 所以每次都会看到两个日志
> > > ```
> > > useEffect(() => {
> > >   logVisit(url); // Sends a POST request
> > > }, [url]);
> > >```


## 前后端分离项目

- 前端react
- 后台springboot1.5.x

## 下载一些依赖

- PropType

```shell script
npm install --save prop-types
```

- axios

```shell script
npm install --save axios
```

- markdown

```shell script
npm install --save showdown
```

- bootstrap

```shell script
npm install --save bootstrap
```

- 发布订阅PubSubJS库，组件间通信

```shell script
npm install --save pubsub-js
```

- Router react-router-dom网页/react-router-native手机端/react-router-anywhere服务器端

```shell script
npm install --save react-router-dom
```

-- markdown插件

```shell script
npm install --save react-markdown
```

-- redus

```shell script
npm install --save redux
```

-- react-redus

```shell script
npm install --save react-redux
```

-- redux异步插件

```shell script
npm install --save redux-thunk
```

-- redux管理state react无法查看，需要chrome插件和依赖

```shell script
npm install --save-dev redux-devtools-extension
```

## SPA应用 single page web application

## 路由

路由即映射关系 key：value；key是路由路径，value为function/component

1. 后台路由
    1. node服务器端路由，value是function，处理客户端提交的请求并返回响应数据
2. 前台路由
    1. 浏览器端路由，value是component组件，当请求的是路由path时浏览器端美欧发送http请求，更新界面显示对应组件
    2. 实现：
        1. react-router:
            1. 组件（路由组件非路由组件）
            ```shell script
                <BrowserRouter>:带#号
                <HashRouter>:不带#号
                <Router>:路由
                <Redirect>:重定向
                <Link>:自动请求链接
                <NavLink>:导航链接
                <Switch>:可包含多个<Router>
                history对象
                match对象
                withRouter对象
            ```
        2. 如何编写路由效果
            1. 编写路由组件
            2. 在父路由组件中指定
                1. 路由链接 <NavLink>
                2. 路由<Route>
3. UI组件，容器组件，解耦
    1. UI组件：
    2. 容器组件：redux组件包装

# web 建站技术栈演进

- CSR
  - client side rendering 客户端（浏览器）渲染
- SSR
  - server side rendering 服务端渲染
- SSG
  - static site generation 静态网站生成
- ISR
  - incremental site rendering 增量式的网站渲染
- DPR
  - distributed persistent rendering 分布式的持续渲染

# react 笔记

| componentWillMount                         | componentDidMount                                                  |
| :----------------------------------------- | :----------------------------------------------------------------- |
| 将要装载，在render之前调用                 | （装载完成），在render之后调用                                     |
| 每一个组件render之前立即调用               | render之后并不会立即调用，而是所有的子组件都render完之后才可以调用 |
| 可以在服务端被调用，也可以在浏览器端被调用 | 只能在浏览器端被调用，在服务器端使用react的时候不会被调用          |
