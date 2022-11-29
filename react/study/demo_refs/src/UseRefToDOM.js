import { useState, useRef, forwardRef, useImperativeHandle } from 'react';
import { flushSync } from 'react-dom';

// The useRef Hook returns an object with a single property called current.
// Initially, myRef.current will be null. 
// When React creates a DOM node for this <input>, 
// React will put a reference to this node into myRef.current.
// You can then access this DOM node from your event handlers 
// and use the built-in browser APIs(inputRef.current.value,inputRef.current.id) defined on it.
// browser APIs 例如：
//  inputRef.current.focus()
// 以及属性： inputRef.current.value
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


// 滚动到 DOM 
export function CatFriends() {
    const firstCatRef = useRef(null);
    const secondCatRef = useRef(null);
    const thirdCatRef = useRef(null);

    function handleScrollToFirstCat() {
        firstCatRef.current.scrollIntoView({
            behavior: 'smooth',
            block: 'nearest',
            inline: 'center'
        });
    }

    function handleScrollToSecondCat() {
        secondCatRef.current.scrollIntoView({
            behavior: 'smooth',
            block: 'nearest',
            inline: 'center'
        });
    }

    function handleScrollToThirdCat() {
        thirdCatRef.current.scrollIntoView({
            behavior: 'smooth',
            block: 'nearest',
            inline: 'center'
        });
    }

    return (
        <>
            <nav>
                <button onClick={handleScrollToFirstCat}>
                    Tom
                </button>
                <button onClick={handleScrollToSecondCat}>
                    Maru
                </button>
                <button onClick={handleScrollToThirdCat}>
                    Jellylorum
                </button>
            </nav>
            <div>
                <ul>
                    <li>
                        <img
                            src="https://placekitten.com/g/200/200"
                            alt="Tom"
                            ref={firstCatRef}
                        />
                    </li>
                    <li>
                        <img
                            src="https://placekitten.com/g/300/200"
                            alt="Maru"
                            ref={secondCatRef}
                        />
                    </li>
                    <li>
                        <img
                            src="https://placekitten.com/g/250/200"
                            alt="Jellylorum"
                            ref={thirdCatRef}
                        />
                    </li>
                </ul>
            </div>
        </>
    );
}


const catList = [];
for (let i = 0; i < 10; i++) {
    catList.push({
        id: i,
        imageUrl: 'https://placekitten.com/250/200?image=' + i
    });
}

// Hooks must only be called at the top-level of your component.
// 与 useState 一样 hooks 函数只能在组件顶层调用 不能嵌套使用
// 如下写法是不能正常工作的
// <ul>
//   {items.map((item) => {
//     // Doesn't work!
//     const ref = useRef(null);
//     return <li ref={ref} />;
//   })}
// </ul>
//
// list map DOM 优化
// 借助唯一标识 id， 创建 map 保存 ref
export function CatFriendsPlus() {
    const itemsRef = useRef(null);

    function scrollToId(itemId) {
        const map = getMap();
        // 从map 读取 dom 进行 操作
        const node = map.get(itemId);
        node.scrollIntoView({
            behavior: 'smooth',
            block: 'nearest',
            inline: 'center'
        });
    }

    // 注意 component 使用的是同一个 map （单例）
    function getMap() {
        if (!itemsRef.current) {
            // Initialize the Map on first usage.
            itemsRef.current = new Map();
        }
        return itemsRef.current;
    }

    return (
        <>
            <nav>
                <button onClick={() => scrollToId(0)}>
                    Tom
                </button>
                <button onClick={() => scrollToId(5)}>
                    Maru
                </button>
                <button onClick={() => scrollToId(9)}>
                    Jellylorum
                </button>
            </nav>
            <div>
                <ul>
                    {catList.map(cat => (
                        <li
                            key={cat.id}
                            ref={(node) => {
                                // 将所有 dom 加入到 map
                                const map = getMap();
                                if (node) {// node !=null
                                    map.set(cat.id, node);
                                } else {
                                    map.delete(cat.id);
                                }
                            }}
                        >
                            <img
                                src={cat.imageUrl}
                                alt={'Cat #' + cat.id}
                            />
                        </li>
                    ))}
                </ul>
            </div>
        </>
    );
}


// 每次点击 会触发重新渲染 每次渲染 得到一个 selectedRef，进行聚焦
// 与上面的 CatFriendsPlus 对比 可以知道 重新渲染 可以 应用 新的 selectedRef
export function CatFriends2() {

    const selectedRef = useRef(null);

    const [index, setIndex] = useState(0);

    return (
        <>
            <nav>
                <button onClick={() => {
                    flushSync(() => {
                        if (index < catList.length - 1) {
                            setIndex(index + 1);
                        } else {
                            setIndex(0);
                        }
                    });
                    selectedRef.current.scrollIntoView({
                        behavior: 'smooth',
                        block: 'nearest',
                        inline: 'center'
                    });

                }}>
                    Next
                </button>
            </nav>
            <div>
                <ul>
                    {catList.map((cat, i) => (
                        <li key={cat.id}
                            ref={index === i ?
                                selectedRef :
                                null
                            }>
                            <img
                                className={
                                    index === i ?
                                        'active' :
                                        ''
                                }
                                src={cat.imageUrl}
                                alt={'Cat #' + cat.id}
                            />
                        </li>
                    ))}
                </ul>
            </div>
        </>
    );
}


// React does not let a component access the DOM nodes of other components. Not even for its own children!
// 默认情况 React 不允许组件操作 其他组件的 DOM。甚至不允许操作自己的子组件
// This is intentional. Refs are an escape hatch that should be used sparingly
// 作者故意为之，refs 作为一个 escape hatch（逃生仓）,应该谨慎使用。
// Manually manipulating another component’s DOM nodes makes your code even more fragile
// 手动操作另一个组件的 DOM 节点 会使代码更加脆弱
// 如果想要公开组件的 DOM 节点 使用 forwardRef 指定将其 ref 转发到其子级之一 如下
const MyInput = forwardRef((props, ref) => {
    return <input {...props} ref={ref} />;
});

// 限制公开 仅公开 focus()
const MyInputOnlyFocus = forwardRef((props, ref) => {

    const realInputRef = useRef(null);
    //import { forwardRef,useImperativeHandle} from 'react';
    // 使用 useImperativeHandle 仅公开 focus()
    useImperativeHandle(ref, () => ({
        // Only expose focus and nothing else
        focus() {
            realInputRef.current.focus();
        },
    }));
    return <input {...props} ref={realInputRef} />;
});

export function Form() {
    const inputRef = useRef(null);

    function handleClick() {
        inputRef.current.focus();
    }

    return (
        <>
            <MyInput ref={inputRef} />
            <MyInputOnlyFocus ref={inputRef} />
            <button onClick={handleClick}>
                Focus the input
            </button>
        </>
    );
}

// 新增 任务 并滚动到最新的任务上

let nextId = 0;
let initialTodos = [];
for (let i = 0; i < 20; i++) {
    initialTodos.push({
        id: nextId++,
        text: 'Todo #' + (i + 1)
    });
}

// state 是 queued，不是立即更新 DOM ，所以 ref 还是上次的 DOM ，因此只能聚焦到 倒数第二位上
export function TodoList() {
    const listRef = useRef(null);
    const [text, setText] = useState('');
    const [todos, setTodos] = useState(initialTodos);

    function handleAdd() {
        const newTodo = { id: nextId++, text: text };
        // 置空输入框
        setText('');
        setTodos([...todos, newTodo]);
        // setTodos 没有立即更新 DOM。因为 state 是 queued
        // 所以 下面这一段代码 还是上次的DOM 所以仅聚焦到倒数第二为上
        listRef.current.lastChild.scrollIntoView({
            behavior: 'smooth',
            block: 'nearest'
        });
    }

    return (
        <>
            <button onClick={handleAdd}>
                Add
            </button>
            <input
                value={text}
                onChange={e => setText(e.target.value)}
            />
            <ul ref={listRef}>
                {todos.map(todo => (
                    <li key={todo.id}>{todo.text}</li>
                ))}
            </ul>
        </>
    );
}
export function TodoListPlus() {
    const listRef = useRef(null);
    const [text, setText] = useState('');
    const [todos, setTodos] = useState(initialTodos);

    function handleAdd() {
        const newTodo = { id: nextId++, text: text };
        // 置空输入框
        setText('');
        // import { flushSync } from 'react-dom';
        flushSync(() => {
            setTodos([...todos, newTodo]);
        });

        // setTodos 没有立即更新 DOM。因为 state 是 queued
        // 所以 下面这一段代码 还是上次的DOM 所以仅聚焦到倒数第二为上
        listRef.current.lastChild.scrollIntoView({
            behavior: 'smooth',
            block: 'nearest'
        });
    }

    return (
        <>
            <button onClick={handleAdd}>
                Add
            </button>
            <input
                value={text}
                onChange={e => setText(e.target.value)}
            />
            <ul ref={listRef}>
                {todos.map(todo => (
                    <li key={todo.id}>{todo.text}</li>
                ))}
            </ul>
        </>
    );
}

//challenges

export function VideoPlayer() {
    const [isPlaying, setIsPlaying] = useState(false);

    const videoRef = useRef(null)

    function handleClick() {
        setIsPlaying(!isPlaying);

        if (isPlaying) {
            videoRef.current.pause()
        } else {
            videoRef.current.play()
        }
    }

    return (
        <>
            <button onClick={handleClick}>
                {isPlaying ? 'Pause' : 'Play'}
            </button>
            <video
                width="250"
                ref={videoRef}
                onPlay={() => setIsPlaying(true)}
                onPause={() => setIsPlaying(false)}>
                <source
                    src="https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"
                    type="video/mp4"
                />
            </video>
        </>
    )
}

const SearchInput=forwardRef((props, ref)=>{
    return (
      <input
        ref={ref}
        placeholder="Looking for something?"
      />
    );
  }
);
// 拆分式写法
// export default forwardRef(function ExposeSearchInput(props, ref){
//     return (
//       <input
//         ref={ref}
//         placeholder="Looking for something?"
//       />
//     );
//   }
// );

function SearchButton({onClick}) {
    return (
        <button onClick={onClick}>
            Search
        </button>
    );
}

export function ForwardRefChallenge() {
    const focusRef = useRef(null);

    return (
        <>
            <nav>
                <SearchButton onClick={() => {
                    focusRef.current.focus()
                }} />
            </nav>
            <SearchInput ref={focusRef} />
        </>
    );
}