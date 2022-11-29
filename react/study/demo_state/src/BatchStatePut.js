import {useState} from 'react';

export function CounterPro() {

    return (
        <>
            <Counter1/>
            <Counter2/>
            <Counter3/>
            <Counter31/>
            <Counter4/>
        </>
    );

}

//setNumber(number + 1); 等价于 setNumber(n=>number + 1)
// n 是 最新的 number 的值
// 第一次 setNumber(n=>number + 1); 此时n=0 number=快照值0; number 被设置为 1
// 第二次 setNumber(n=>number + 1); 此时n=1 number=快照值0; number 被设置为 1
// 第三次 setNumber(n=>number + 1); 此时n=2 number=快照值0; number 被设置为 1
//最终 number 由第三次 设置为1
// n 是 最新的 number 的值 但是 并未使用
function Counter1() {

    const [number, setNumber] = useState(0);

    return (
        <>
            <h1>{number}</h1>
            <button onClick={() => {
                setNumber(number + 1);
                setNumber(number + 1);
                setNumber(number + 1);
            }}>
                +1
            </button>
        </>
    )
}

// n 是 最新的 number 的值，
// 第一次 setNumber(n => n + 1);此时n=0; number 被设置为 1
// 第二次 setNumber(n => n + 1);此时n=1; number 被设置为 2
// 第三次 setNumber(n => n + 1);此时n=2; number 被设置为 3
//所以Counter2 写法可以实现 +3
function Counter2() {

    const [number, setNumber] = useState(0);

    return (
        <>
            <h1>{number}</h1>
            <button onClick={() => {
                setNumber(n => n + 1);
                setNumber(n => n + 1);
                setNumber(n => n + 1);
            }}>
                +3
            </button>
        </>
    )
}

// n 是 最新的 number 的值，
// 第一次 setNumber(number + 1);此时n=number=快照值0; number 被设置为 1
// 第二次 setNumber(n => n + 1);此时n=最新number状态值=1; number 被设置为 2
// 根据第二次 setNumber(n => n + 1); number 被设置为 2
// 所以Counter3 写法可以实现 +2
// 对比 Counter31  最后一次是 setNumber(number + 1);
//所以 Counter31 number快照值=0 所以最终number 被设置为1
function Counter3() {

    const [number, setNumber] = useState(0);

    return (
        <>
            <h1>{number}</h1>
            <button onClick={() => {
                setNumber(number + 1);
                setNumber(n => n + 1);
            }}>
                +2
            </button>
        </>
    )
}

function Counter31() {

    const [number, setNumber] = useState(0);

    return (
        <>
            <h1>{number}</h1>
            <button onClick={() => {
                setNumber(n => n + 1);
                setNumber(number + 1);
            }}>
                +1
            </button>
        </>
    )
}

//setNumber(2) 等价于 setNumber(n=>2)
//setNumber(number + 2) 等价于 setNumber(n=>number + 2)
// n 是 最新的 number 的值
// 第一次 setNumber(number + 2); 此时 number=快照值0; number 被设置为 2
// 第二次 setNumber(n => n + 1); 此时n=2 =最新 number 状态值; number 被设置为 3
// 第三次 setNumber(2); 此时number最细值=3 number=快照值0; number 被设置为 2
// 最终number 由第三次 setNumber 设置为2
function Counter4() {

    const [number, setNumber] = useState(0);

    return (
        <>
            <h1>{number}</h1>
            <button onClick={() => {
                setNumber(number + 2);
                setNumber(n => n + 1);
                setNumber(2);
            }}>
                ===2
            </button>
        </>
    )
}