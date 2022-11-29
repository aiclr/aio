import { useState, useEffect, useRef } from 'react';

function createConnection() {
    // A real implementation would actually connect to the server
    return {
        connect() {
            console.log('✅ Connecting...');
        },
        disconnect() {
            console.log('❌ Disconnected.');
        }
    };
}

export function ChatRoom() {
    // 如果 effect 创建了一个连接
    // 应该返回一个清理函数，告诉 react 怎么从服务端断开连接
    // 清理函数应该停止或者撤销 useEffect 正在执行的操作
    // react 每次重新执行 useEffect 都会先调用 return 函数
    useEffect(() => {
        const connection = createConnection();
        connection.connect();
        return () => connection.disconnect();
    }, []);

    return <h1>Welcome to the chat!</h1>;
}

// 查看日志感受 cleanup 函数

function Playground() {
    const [text, setText] = useState('a');

    useEffect(() => {
        function onTimeout() {
            console.log('---->' + text);
        }

        console.log('----> Schedule "' + text + '" log');
        const timeoutId = setTimeout(onTimeout, 3000);

        return () => {
            console.log('----> Cancel "' + text + '" log');
            clearTimeout(timeoutId);
        };
    }, [text]);

    return (
        <>
            <label>
                What to log:{' '}
                <input
                    value={text}
                    onChange={e => setText(e.target.value)}
                />
            </label>
            <h1>{text}</h1>
        </>
    );
}

export function TestCleanup() {
    const [show, setShow] = useState(false);
    return (
        <>
            <button onClick={() => setShow(!show)}>
                {show ? 'Unmount' : 'Mount'} the component
            </button>
            {show && <hr />}
            {show && <Playground />}
        </>
    );
}

// 当 MyInput 显示到屏幕上时聚焦到输入框
// 当有多个 MyInput 时 焦点会聚集在最后一个 组件上，所以需要添加一个 props 控制 聚焦

export function MyInput({ value, onChange }) {
    const ref = useRef(null);

    useEffect(() => {
        ref.current.focus()
    }, []);// 当组件挂载时即显示在屏幕上时执行，所以需要空依赖项数组 []

    return (
        <input
            ref={ref}
            value={value}
            onChange={onChange}
        />
    );
}


export function MyInput2({ shouldFocus, value, onChange }) {
    const ref = useRef(null);

    useEffect(() => {
        if (shouldFocus) {
            ref.current.focus()
        }
    }, [shouldFocus]);// shouldFocus 改变时 重新渲染会重新判断聚焦

    return (
        <input
            ref={ref}
            value={value}
            onChange={onChange}
        />
    );
}


// 累加器 每次应该加1 但是下面的写法是加2
export function CounterBug() {
    const [count, setCount] = useState(0);
    useEffect(() => {
        function onTick() {
            // 定时设置 state 触发重新渲染 react 严格模式下 会渲染两次组件所以 会执行两次累加
            setCount(c => c + 1);
        }
        setInterval(onTick, 1000);
    }, []);

    return <h1>{count}</h1>;

}
//
export function CounterFixBug() {
    const [count, setCount] = useState(0);

    useEffect(() => {

        function onTick() {
            setCount(c => c + 1);
        }
        // 第二次渲染时 结束上一次的渲染 effect
        const taskID = setInterval(onTick, 1000);
        return (() => { clearInterval(taskID) })
    }, []);

    return <h1>{count}</h1>;
}


async function fetchBio(person) {
    const delay = person === 'Bob' ? 2000 : 200;
    return new Promise(resolve => {
        setTimeout(() => {
            resolve('This is ' + person + '’s bio.');
        }, delay);
    })
}
// bug
export function PageBug() {
    const [person, setPerson] = useState('Alice');
    const [bio, setBio] = useState(null);

    useEffect(() => {
        setBio(null);
        fetchBio(person).then(result => {
            setBio(result);
        });
    }, [person]);

    return (
        <>
            <select value={person} onChange={e => {
                setPerson(e.target.value);
            }}>
                <option value="Alice">Alice</option>
                <option value="Bob">Bob</option>
                <option value="Taylor">Taylor</option>
            </select>
            <hr />
            <p><i>{bio ?? 'Loading...'}</i></p>
        </>
    );
}

export function PageFixbug() {
    const [person, setPerson] = useState('Alice');
    const [bio, setBio] = useState(null);

    useEffect(() => {
        let ignore = false;
        setBio(null);
        fetchBio(person).then(result => {
            if (!ignore) {
                setBio(result);
            }
        });
        return () => ignore = true;

    }, [person]);

    return (
        <>
            <select value={person} onChange={e => {
                setPerson(e.target.value);
            }}>
                <option value="Alice">Alice</option>
                <option value="Bob">Bob</option>
                <option value="Taylor">Taylor</option>
            </select>
            <hr />
            <p><i>{bio ?? 'Loading...'}</i></p>
        </>
    );
}