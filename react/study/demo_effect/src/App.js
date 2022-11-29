import './App.css';
import { Play } from './VideoPlay';
import { ChatRoom, TestCleanup, MyInput, MyInput2, CounterBug, CounterFixBug ,PageBug,PageFixbug} from './CleanupEffect'
import { TodoList,TodoList2 ,TodoList2Plus,ContactList,FormUseEffect,Form,Timer} from './RemoveEffect';
import {PointerMove,Page,PagePlus} from './LifecycleEffect'

function App() {
  return (
    <>
      <p>是否监听鼠标位置</p>
      <PointerMove/><hr/>

      <Timer/><hr/>

      <p>链式调用effect</p>
      <Page/><hr/>
      <PagePlus/><hr/>
      {/* <p>使用 useEvent 使切换主题不会重新连接 console</p>
      <ChatRoom4App /><hr />
      <p>切换主题会重新连接 不合理 console</p>
      <ChatRoom3App /><hr />
      <p>模拟聊天查看 console</p>
      <ChatRoom2App /><hr />
      <p>模拟聊天查看 console</p>
      <ChatRoom /><hr /> */}
      <p>模拟聊天查看 console</p>
      <ChatRoom /><hr />
      <p>点击挂载带有 useEffect 组件查看 console</p>
      <TestCleanup /><hr />
      <p>默认聚焦到最后一个输入框</p>
      <MyInput /><hr />
      <MyInput /><hr />
      <MyInput /><hr />
      <p>聚焦到指定的输入框</p>
      <MyInput2 /><hr />
      <MyInput2 shouldFocus='true' /><hr />
      <MyInput2 /><hr />
      <p>每秒+2</p>
      <CounterBug /><hr />
      <p>每秒+1</p>
      <CounterFixBug /><hr />
      <p>有bug 点击Bob 再快速点击 alice 会看到 bug</p>
      <PageBug /><hr />
      <p>修复后。主要bug是没取消 点击 Bob 的 Effect</p>
      <PageFixbug /><hr />
      <p>待办</p>
      <TodoList/><hr/>
      <p>useMemo写法</p>
      <TodoList2/><hr/>
      <p>拆分state写法</p>
      <TodoList2Plus/>
      <p>带 useEffect 的 reset 功能</p>
      <ContactList/><hr/>
      <p>不应使用 useEffect 的 submit 功能</p>
      <FormUseEffect/><hr/>
      {/* <p>带 useEffect 的 submit 功能</p> */}
      {/* <Form/><hr/> */}

     

      <p>暂停/播放</p>
      <Play /><hr />
    </>
  );
}

export default App;
