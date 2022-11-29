import {useState} from "react";

// react 仅在渲染之间存在差异时更改DOM节点
// 往 <input/>输入文本 当time改变时 组件重新渲染
// 更新 <h1>{time}</h1>
// 此时 <input/>出现在 JSX 中与上次相同位置，
// 因此 React 不会触及 <input/> 也不会触及 <input/> 的 value

export function MyClock() {

    const [time,setTime]=useState(new Date().toLocaleTimeString());
    setInterval(()=>{setTime(new Date().toLocaleTimeString())},1000)

    return (
        <>
        <Clock time={time}/>
        </>
    );
}

function Clock({time}) {
    return (
        <>
            <h1>{time}</h1>
            <input/>
        </>
    );
}