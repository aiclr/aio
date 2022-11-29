import { useState, useEffect } from 'react';


function Counter1() {
    const [count, setCount] = useState(0);
    useEffect(() => {
        const id = setInterval(() => {
            setCount(c => c + 1);
        }, 1000);
        return () => clearInterval(id);
    }, []);
    return <h1>Seconds passed: {count}</h1>;
}

//抽出 useCounter 
function useCounter() {
    const [count, setCount] = useState(0);
    useEffect(() => {
        const id = setInterval(() => {
            setCount(c => c + 1);
        }, 1000);
        return () => clearInterval(id);
    }, []);
    return count;
}

function Counter11() {
    const count = useCounter();
    return <h1>Seconds passed: {count}</h1>;
}

// 添加 delay
function Counter2() {
    const [delay, setDelay] = useState(1000);
    const count = useCounter();
    return (
        <>
            <label>
                Tick duration: {delay} ms
                <br />
                <input
                    type="range"
                    value={delay}
                    min="10"
                    max="2000"
                    onChange={e => setDelay(Number(e.target.value))}
                />
            </label>
            <hr />
            <h1>Ticks: {count}</h1>
        </>
    );
}

function useCounter2(delay) {
    const [count, setCount] = useState(0);
    useEffect(() => {
        const id = setInterval(() => {
            setCount(c => c + 1);
        }, delay);
        return () => clearInterval(id);
    }, [delay]);
    return count;
}

function Counter22() {
    const [delay, setDelay] = useState(1000);
    const count = useCounter2(delay);
    return (
        <>
            <label>
                Tick duration: {delay} ms
                <br />
                <input
                    type="range"
                    value={delay}
                    min="10"
                    max="2000"
                    onChange={e => setDelay(Number(e.target.value))}
                />
            </label>
            <hr />
            <h1>Ticks: {count}</h1>
        </>
    );
}

function useInterval(onTick, delay) {
    useEffect(() => {
        const id = setInterval(onTick, delay);
        return () => clearInterval(id);
    }, [delay]);
}

function useCounter3(delay) {
    const [count, setCount] = useState(0);
    useInterval(() => {
        setCount(c => c + 1);
    }, delay);
    return count;
}

function Counter3() {
    const [delay, setDelay] = useState(1000);
    const count = useCounter3(delay);
    return (
        <>
            <label>
                Tick duration: {delay} ms
                <br />
                <input
                    type="range"
                    value={delay}
                    min="10"
                    max="2000"
                    onChange={e => setDelay(Number(e.target.value))}
                />
            </label>
            <hr />
            <h1>Ticks: {count}</h1>
        </>
    );
}


export function Challenges() {
    return (
        <>
            <p>useState useEffect 累加器</p>
            <Counter1 />
            <p>自定义hooks 累加器</p>
            <Counter11 />
            <p>未添加 delay</p>
            <Counter2 />
            <p>添加 delay</p>
            <Counter22 />
            <p>添加 delay</p>
            <Counter3 />
        </>
    );
}