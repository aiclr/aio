import './App.css';
import { UseRef, StopwatchUseRef, Chat, Toggle, Dashboard, LatestStateChat } from './UseRef';
import { Focus, ForwardRefChallenge, CatFriends, CatFriendsPlus, TodoList, TodoListPlus, VideoPlayer, CatFriends2 } from './UseRefToDOM'

function App() {
  return (
    <>
      <p>点击不会重新渲染但是计数器会增加</p>
      <UseRef /><hr />
      <p>自定义组件 通过 forwardRef 转发 ref 给子组件</p>
      <ForwardRefChallenge /><hr />
      <p>使用 ref 操作 video DOM 播放/暂停</p>
      <VideoPlayer /><hr />

      <p>利用 useRef 制作秒表</p>
      <StopwatchUseRef /><hr />
      <p>利用 useRef 取消发送</p>
      <Chat /><hr />
      <p>useRef 不会触发渲染 但是值会改变,点击 off 无反应，但是 点击重新渲染可以看到 ref是变化的 </p>
      <Toggle /><hr />
      <p>利用 useRef 取消发送</p>
      <Dashboard /><hr />
      <p>利用 useRef 显示最新的 state 而不是点击时的state快照</p>
      <LatestStateChat /><hr />

      <p>useRef 最常用的场景是访问一个 DOM 元素:如下：点击聚焦到输入框</p>
      <Focus /><hr />
      <p>useRef 滚动到 DOM:如下：点击按钮 滚动到 对应图片</p>
      <CatFriends /><hr />
      <p>借助 唯一标识 初次渲染时 保存全部dom, 操作时查询对应的dom进行操作。 滚动到 DOM:如下：点击按钮 滚动到 对应图片</p>
      <CatFriendsPlus /><hr />
      <CatFriends2 /><hr />

      <p>state queued 不立即更新 DOM ,此时 ref 还是更新前的DOM</p>
      <TodoList /><hr />
      <p>利用 flushSync 异步刷新 state 使 ref 及时生效</p>
      <TodoListPlus /><hr />
    </>
  );
}

export default App;
