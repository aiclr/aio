import { useState, useEffect } from 'react';
import { useEvent } from 'react';

// 开发中的 hooks  useEvent
export function Timer() {
    const [count, setCount] = useState(0);
    const [increment, setIncrement] = useState(1);

    const onTick = useEvent(() => {
        setCount(c => c + increment);
    });

    useEffect(() => {
        const id = setInterval(() => {
            onTick();
        }, 1000);
        return () => {
            clearInterval(id);
        };
    }, []);

    return (
        <>
            <h1>
                Counter: {count}
                <button onClick={() => setCount(0)}>Reset</button>
            </h1>
            <hr />
            <p>
                Every second, increment by:
                <button disabled={increment === 0} onClick={() => {
                    setIncrement(i => i - 1);
                }}>–</button>
                <b>{increment}</b>
                <button onClick={() => {
                    setIncrement(i => i + 1);
                }}>+</button>
            </p>
        </>
    );
}
