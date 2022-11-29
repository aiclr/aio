import React from 'react';
import {useState} from 'react';//updating the screen
import './App.css';
import {Cup, Cups, PureCup} from "./PureComponent";
import {FixClock} from "./Clock";
import {Profile,PureProfile} from "./Profile";

const user = {
    name: 'Leo',
    age: 29
}

function MyJSXButton() {
    return (
        <button>
            I'm a jsx button
        </button>
    );
}

function MyButton() {
    return React.createElement('button', {onClick: () => console.log(123)}, 'I\'m a button');
}

//条件判断常规写法
function ConditionalButton() {
    let content;
    const [show, setAge] = React.useState(user.age);
    if (show > 18) {
        content = React.createElement('button', {onClick: () => setAge(user.age = 17)}, 'I\'m a Conditional Button');
        console.log(user.age);
    } else {
        content = React.createElement('button', {onClick: () => setAge(user.age = 29)}, 'I\'m a Conditional Button also');
        console.log(user.age);
    }
    return content;
}

//条件判断 三元运算符 写法
function ConditionalButton1() {
    const [show, setAge] = React.useState(user.age);
    return show > 18 ? React.createElement('button', {onClick: () => setAge(user.age = 17)}, 'I\'m a Conditional Button')
        : React.createElement('button', {onClick: () => setAge(user.age = 29)}, 'I\'m a Conditional Button also');
}

//不需要else语句的写法
function ConditionalButton2() {
    const [show, setAge] = React.useState(user.age);
    return show > 18 && React.createElement('button', {onClick: () => setAge(user.age = 17)}, 'Click to hide');
}


function AboutPage() {
    return (
        <>
            <h1>About</h1>
            <p className="about"> Hello {user.name}.<br/>How do you do?</p>
        </>
    );
}


//渲染列表
const products = [
    {title: 'Cabbage', isFruit: false, id: 1},
    {title: 'Garlic', isFruit: false, id: 2},
    {title: 'Apple', isFruit: true, id: 3},
];


function RenderingLists() {
    const listItems = products.map(product =>
        <li key={product.id} style={
            {
                color: product.isFruit ? 'magenta' : 'darkgreen'
            }
        }>
            {product.title}
        </li>
    );

    return (
        <ul>{listItems}</ul>
    );
}

//响应事件
function ButtonEvent() {
    function handleClick() {
        alert('You clicked me!');
    }

    return (
        //传递 handleClick 而不是调用 handleClick()
        <button onClick={handleClick}>
            Click me
        </button>
    );
}

//updating the screen

function UpdateTheScreen() {
    const [count, setCount] = useState(0);

    function handleClick() {
        setCount(count + 1);
    }

    return (
        <button onClick={handleClick}>
            clicked {count} times
        </button>
    );
}

function GlobalData({global, setGlobal}) {
    function handleClick() {
        setGlobal(global + 1);
    }

    return (
        <button onClick={handleClick}>
            Clicked {global} times
        </button>
    );
}

function GlobalData2({global, handleClick}) {
    return (
        <button onClick={handleClick}>
            Clicked {global} times
        </button>
    );
}

function GlobalData3({global, handleClick}) {
    return (
            <button onClick={handleClick}>
                Clicked {global} times
            </button>
            );
}


export default function MyApp() {

    //useState 是 hooks 函数 只能在 components 组件顶层（或者其他钩子函数内）调用 此处位置在 MyApp 组件顶层
    // You can only call Hooks at the top level of your components (or other Hooks)
    const [global, setGlobal] = useState(0);

    function handleClick3(){
        setGlobal(global + 1);
    }

    return (
        <div>
            <h1>Welcome to my app</h1>
            <PureProfile person={{
                imageId: 'lrWQx8l',
                name: 'Subrahmanyan Chandrasekhar',
            }} />
            <PureProfile person={{
                imageId: 'MK3eW3A',
                name: 'Creola Katherine Johnson',
            }} />
            <Profile person={{
                imageId: 'lrWQx8l',
                name: 'Subrahmanyan Chandrasekhar',
            }} />
            <Profile person={{
                imageId: 'MK3eW3A',
                name: 'Creola Katherine Johnson',
            }} />
            <FixClock time={new Date()}/>
            <div><ConditionalButton/></div>
            <div><ConditionalButton1/></div>
            <div><ConditionalButton2/></div>
            <div><MyButton/></div>
            <div><MyJSXButton/></div>
            <div><RenderingLists/></div>
            <div><ButtonEvent/></div>
            <div><UpdateTheScreen/></div>
            <div><UpdateTheScreen/></div>
            <div><GlobalData global={global} setGlobal={setGlobal}/></div>
            <div><GlobalData2 global={global} handleClick={() => setGlobal(global + 1)}/></div>
            <div><GlobalData3 global={global} handleClick={handleClick3}/></div>
            <AboutPage/>
            <Cup/>
            <Cup/>
            <Cup/>
            <PureCup guest={1}/>
            <PureCup guest={2}/>
            <PureCup guest={3}/>
            <Cups/>

        </div>
    );
}