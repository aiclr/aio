import { useState } from 'react';
// 错误组件
export function Challenge4Error() {
    const [name, setName] = useState('');

    function handleClick() {
        setName(prompt('What is your name?错误组件 不会设置 name state'));
        alert(`Hello, ${name}!`);
    }

    return (
            <button onClick={handleClick}>
                Greet
            </button>
            );
}

// 状态变量仅用于 组件的重新渲染之间保持信息
// 在单个事件处理程序中，一个常规变量就可以了
// 如果常规变量运行良好时，不要引入状态变量
export function Challenge4() {

    function handleClick() {
        alert(`Hello, ${prompt('What is your name?')}!`);
    }

    return (
            <button onClick={handleClick}>
                Greet
            </button>
            );
}
