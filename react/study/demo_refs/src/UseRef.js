import { useRef, useState } from 'react';

// 使用 useRef 记住信息 但是不触发重新渲染 , 所以可以用于保存两次渲染之间的一个值
// 如果需要重新渲染 则需要使用 state
// When you want a component to “remember” some information,
// but you don’t want that information to trigger new renders,
// you can use a ref
// ref 可以是 string number object 甚至是一个函数 
// ref 是一个普通 javascript object 拥有 current 属性，允许读取和修改
// component 不会 随着 ref 变化重新渲染 
// 也就是，在两次渲染之间 会保存 ref 值
// 修改 ref 不会出发重新渲染，但是修改的 ref新值 会立即生效
// 修改 state 会触发重新渲染，渲染不应该关心 怎么使用 ref 和 其内容
// 注意： 
//  React 不知道 ref.current 什么时候会被修改。所以渲染时读取它会使组件的行为难以预测
// useRef 不会触发渲染 所以 点击 按钮 无反应
export function Toggle() {

  const counter = useRef(0);
  const [show, setShow] = useState(false)

  return (
    <>
      <button onClick={() => {
        counter.current += 1;
      }}>
        点击 {counter.current} 次
      </button>
      <button onClick={() => {
        setShow(!show)
      }}>
        重新渲染
      </button>
    </>

  );
}

export function UseRef() {
  let ref = useRef(0);

  function handleClick() {
    ref.current = ref.current + 1;
    alert('You clicked ' + ref.current + ' times!');
  }

  return (
    <button onClick={handleClick}>
      Click me!
    </button>
  );
}

// ref 最常用的场景是访问一个 DOM 元素
export function Focus() {

  const inputRef = useRef(null);

  function handleClick() {
    inputRef.current.focus();
    console.log(inputRef.current.value)
    console.log(inputRef.current.id)
    console.log(inputRef.current.className)
  }

  return (
    <>
      <input ref={inputRef} id='focus' className='myclass' />

      <button onClick={handleClick}>
        Focus the input
      </button>
    </>
  );
}


// setInterval 定时执行 会返回一个 id
// clearInterval 清理 定时器 需要 setInterval 返回的 id
export function StopwatchUseRef() {
  const [startTime, setStartTime] = useState(null);
  const [now, setNow] = useState(null);
  // clearInterval 用于 停止 setInterval 
  // 但是 clearInterval需要一个ID 
  // 所以使用 useRef 保存两次渲染之间的这个 ID 
  const intervalRef = useRef(null);

  function handleStart() {
    setStartTime(Date.now());
    setNow(Date.now());
    // 开始前先清除上次的 防止多次点击 start 按钮 导致 intervalRef丢失 无法stop
    clearInterval(intervalRef.current);
    // 每次点击 start 按钮 setInterval 会返回一个 唯一id (相当于线程id)
    // clearInterval(ID) 可以取消此ID对应任务的执行
    intervalRef.current = setInterval(() => {
      console.log(intervalRef.current)
      setNow(Date.now());
    }, 10);
  }

  function handleStop() {
    // 点击 stop 那一刻  获取 对应的 intervalRef.current 之后 stop
    clearInterval(intervalRef.current);
  }

  let secondsPassed = 0;
  if (startTime != null && now != null) {
    secondsPassed = (now - startTime) / 1000;
  }

  return (
    <>
      <h1>Time passed: {secondsPassed.toFixed(3)}</h1>
      <button onClick={handleStart}>
        Start
      </button>
      <button onClick={handleStop}>
        Stop
      </button>
    </>
  );
}



// 点击 发送 等三秒 弹出 sent 如果三秒内点击undo 取消 sent 弹出
export function Chat() {
  const [text, setText] = useState('');
  const [isSending, setIsSending] = useState(false);
  const timeoutID = useRef(null);

  function handleSend() {
    setIsSending(true);
    // 防止多次点击 “发送”按钮， 丢失上次的 任务ID
    // 每次点击 “发送”按钮之前 先把 上次 任务 取消
    clearTimeout(timeoutID.current);
    timeoutID.current = setTimeout(() => {
      alert('Sent!');
      setIsSending(false);
    }, 3000);
  }

  function handleUndo() {
    clearTimeout(timeoutID.current);
    setIsSending(false);
  }

  return (
    <>
      <input
        disabled={isSending}
        value={text}
        onChange={e => setText(e.target.value)}
      />
      <button
        disabled={isSending}
        onClick={handleSend}>
        {isSending ? 'Sending...' : 'Send'}
      </button>
      {isSending &&
        <button onClick={handleUndo}>
          Undo
        </button>
      }
    </>
  );
}



// bug 写法 使每个独立的 button 通过 全局变量 timeoutID 产生联系
// 每次点击 都会将上个按钮点击事件 取消
// bug 修复为： 
//    1. 让每个按钮独立，不会互相取消 事件 
//    2. 同一按钮点击多次，取消上一次的点击事件
// let timeoutID;

// function DebouncedButton({ onClick, children }) {
//   return (
//     <button onClick={() => {
//       clearTimeout(timeoutID);
//       timeoutID = setTimeout(() => {
//         onClick();
//       }, 1000);
//     }}>
//       {children}
//     </button>
//   );
// }


function DebouncedButton({ onClick, children }) {

  const timeoutID = useRef(null);

  return (
    <button onClick={() => {
      clearTimeout(timeoutID.current);
      timeoutID.current = setTimeout(() => {
        onClick();
      }, 1000);
    }}>
      {children}
    </button>
  );
}

export function Dashboard() {
  return (
    <>
      <DebouncedButton
        onClick={() => alert('Spaceship launched!')}
      >
        Launch the spaceship
      </DebouncedButton>
      <DebouncedButton
        onClick={() => alert('Soup boiled!')}
      >
        Boil the soup
      </DebouncedButton>
      <DebouncedButton
        onClick={() => alert('Lullaby sung!')}
      >
        Sing a lullaby
      </DebouncedButton>
    </>
  )
}


// 输入 点击 send 趁没有弹出 alert 再次输入 
// alert 弹出的是 点击时的 state 不是最新的state ( state 快照)
export function LatestStateChat() {
  const [text, setText] = useState('');
  const latestState = useRef(text);

  function handleSend() {
    setTimeout(() => {
      alert('Sending: ' + latestState.current);
    }, 3000);
  }

  return (
    <>
      <input
        value={text}
        onChange={e => {
          latestState.current = e.target.value;
          setText(e.target.value);
        }}
      />
      <button
        onClick={handleSend}>
        Send
      </button>
    </>
  );
}