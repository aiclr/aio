import {useState} from 'react';

//state 快照 类似值传递
// 第一次点击时 此时 number = 0 快照中 状态 number=0
//三次 setNumber(number + 1); 都调用一个快照中的number
//等于调用三次 setNumber(0 + 1);
//每次设置的新状态 number 都是 1
//所有 setNumber 调用完之后才进行 渲染
//类似 餐厅点菜
export function Counter() {
    const [number, setNumber] = useState(0);

    return (
        <>
            <h1>{number}</h1>
            <button
                onClick={() => {
                    setNumber(number + 1);
                    setNumber(number + 1);
                    setNumber(number + 1);
                }}
            >
                +3
            </button>

            <button
                onClick={() => {
                    setNumber(number + 5);
                    alert(number);
                }}
            >
                +5
            </button>
            <button
                onClick={() => {
                    setNumber(number + 5);
                    setTimeout(() => {
                        alert(number);
                    }, 3000);
                }}
            >
                +5
            </button>
        </>
    )
}

// setTimeout(e,5000) 延迟五秒执行e
// 虽然调用e是异步的 但是 e使用的状态是当时的快照状态
export function Form() {
    const [to, setTo] = useState('Alice');
    const [message, setMessage] = useState('Hello');

    function handleSubmit(e) {
        e.preventDefault();
        setTimeout(() => {
            alert(`You said ${message} to ${to}`);
        }, 5000);
    }

    return (
        <form onSubmit={handleSubmit}>
            <label>
                To:{' '}
                <select
                    value={to}
                    onChange={e => setTo(e.target.value)}>
                    <option value="Alice">Alice</option>
                    <option value="Bob">Bob</option>
                </select>
            </label>
            <textarea
                placeholder="Message"
                value={message}
                onChange={e => setMessage(e.target.value)}
            />
            <button type="submit">Send</button>
        </form>
    );
}


export function TrafficLight() {
    const [walk, setWalk] = useState(true);

    function handleClick() {
        setWalk(!walk);
        alert(walk ? 'Stop is next' : 'Walk is next');
    }

    return (
        <>
            <button onClick={handleClick}>
                Change to {walk ? 'Stop' : 'Walk'}
            </button>
            <h1 style={{color: walk ? 'darkgreen' : 'darkred'}}>
                {walk ? 'Walk' : 'Stop'}
            </h1>
        </>
    );
}