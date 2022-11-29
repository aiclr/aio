import { useState } from 'react';
import './ResetState.css'

export function StateTiedToPosition() {
    const counter = <Counter />;
    return (
        <div>
            {counter}
            {counter}
        </div>
    );
}

function Counter() {
    const [score, setScore] = useState(0);
    const [hover, setHover] = useState(false);

    let className = 'counter';
    if (hover) {
        className += ' hover';
    }

    return (
        <div
            className={className}
            onPointerEnter={() => setHover(true)}
            onPointerLeave={() => setHover(false)}
        >
            <h1>{score}</h1>
            <button onClick={() => setScore(score + 1)}>
                Add one
            </button>
        </div>
    );
}


// 开关显示后会 重置组件 state
export function StatePosition() {
    const [showB, setShowB] = useState(true);
    return (
        <div>
            <Counter />
            {showB && <Counter />}
            <label>
                <input
                    type="checkbox"
                    checked={showB}
                    onChange={e => {
                        setShowB(e.target.checked)
                    }}
                />
                组件消失再重新生成后会 重置组件 state
            </label>
        </div>
    );
}


// 位置没变 组件状态没变
// 对 React 来说 重要的是 组件在 UI树 中的位置，而不是 JSX 标记中的位置
export function SamePositionNoReset() {
    const [isFancy, setIsFancy] = useState(false);
    return (
        <div>
            {isFancy ? (
                <Counter2 isFancy={true} />
            ) : (
                <Counter2 isFancy={false} />
            )}
            <label>
                <input
                    type="checkbox"
                    checked={isFancy}
                    onChange={e => {
                        setIsFancy(e.target.checked)
                    }}
                />
                状态不变---对React来说重要的是组件在UI树中的位置&nbsp;而不是 JSX 标记中的位置
            </label>
        </div>
    );
}

function Counter2({ isFancy }) {
    const [score, setScore] = useState(0);
    const [hover, setHover] = useState(false);

    let className = 'counter';
    if (hover) {
        className += ' hover';
    }
    if (isFancy) {
        className += ' fancy';
    }

    return (
        <div
            className={className}
            onPointerEnter={() => setHover(true)}
            onPointerLeave={() => setHover(false)}
        >
            <h1>{score}</h1>
            <button onClick={() => setScore(score + 1)}>
                Add one
            </button>
        </div>
    );
}


// 相同位置的不同组件 切换时会改变 state 改变
export function SamePositionReset() {
    const [isPaused, setIsPaused] = useState(false);
    return (
        <div>
            {isPaused ? (
                <p>See you later!</p>
            ) : (
                <Counter />
            )}
            <label>
                <input
                    type="checkbox"
                    checked={isPaused}
                    onChange={e => {
                        setIsPaused(e.target.checked)
                    }}
                />
                相同位置的不同组件 切换时会改变 state
            </label>
        </div>
    );
}

// 嵌套组件 相同位置 state 也会改变 
export function SamePositionReset2() {
    const [isFancy, setIsFancy] = useState(false);
    return (
        <div>
            {isFancy ? (
                <div>
                    <Counter3 isFancy={true} />
                </div>
            ) : (
                <section>
                    <Counter3 isFancy={false} />
                </section>
            )}
            <label>
                <input
                    type="checkbox"
                    checked={isFancy}
                    onChange={e => {
                        setIsFancy(e.target.checked)
                    }}
                />
                相同位置的被嵌套的相同组件 切换时会改变 state
            </label>
        </div>
    );
}

function Counter3({ isFancy }) {
    const [score, setScore] = useState(0);
    const [hover, setHover] = useState(false);

    let className = 'counter';
    if (hover) {
        className += ' hover';
    }
    if (isFancy) {
        className += ' fancy';
    }

    return (
        <div
            className={className}
            onPointerEnter={() => setHover(true)}
            onPointerLeave={() => setHover(false)}
        >
            <h1>{score}</h1>
            <button onClick={() => setScore(score + 1)}>
                Add one
            </button>
        </div>
    );
}


// 每次点击 button 都会重新渲染嵌套声明的 MyTextField
// 会造成性能问题
// 在外部声明的 MyTextField2 不会被重新渲染
function MyTextField2() {
    const [text, setText] = useState('');

    return (
        <input
            value={text}
            onChange={e => setText(e.target.value)}
            placeholder='点击按钮不会重新渲染输入框'
        />
    );
}

export function MyComponent() {
    const [counter, setCounter] = useState(0);

    function MyTextField() {
        const [text, setText] = useState('');

        return (
            <input
                value={text}
                onChange={e => setText(e.target.value)}
                placeholder='点击按钮会重新渲染输入框'
            />
        );
    }

    return (
        <>
            <MyTextField />
            <MyTextField2 />
            <button onClick={() => {
                setCounter(counter + 1)
            }}>Clicked {counter} times</button>
        </>
    );
}


// react 将 Counter4 视为 仅有 props 改变的同一个位置上的组件
// 注意组件 Counter4 位置没变
export function Scoreboard() {
    const [isPlayerA, setIsPlayerA] = useState(true);
    return (
        <div>
            {isPlayerA ? (
                <Counter4 person="Taylor" />
            ) : (
                <Counter4 person="Sarah" />
            )}
            <button onClick={() => {
                setIsPlayerA(!isPlayerA);
            }}>
                相同位置 仅有 props 改变 状态不变
            </button>
        </div>
    );
}

function Counter4({ person }) {
    const [score, setScore] = useState(0);
    const [hover, setHover] = useState(false);

    let className = 'counter';
    if (hover) {
        className += ' hover';
    }

    return (
        <div
            className={className}
            onPointerEnter={() => setHover(true)}
            onPointerLeave={() => setHover(false)}
        >
            <h1>{person}'s score: {score}</h1>
            <button onClick={() => setScore(score + 1)}>
                Add one
            </button>
        </div>
    );
}

// 将 Counter4 声明到不同位置 重新渲染会修改 state
// 点击按扭 的变化过程为 删除 Taylor 重新渲染 Sarah 所以会重置 state
export function Scoreboard2() {
    const [isPlayerA, setIsPlayerA] = useState(true);
    return (
        <div>
            {isPlayerA && (
                <Counter4 person="Taylor" />
            )}
            {!isPlayerA && (
                <Counter4 person="Sarah" />
            )}
            <button onClick={() => {
                setIsPlayerA(!isPlayerA);
            }}>
                看似相同位置 其实是不同的两个组件 每次都会重新渲染 所以状态改变
            </button>
        </div>
    );
}

// 将 Counter4 声明为不同的组件 比如加个不同的key 重新渲染会修改 state
// key 属于位置的一部分 不同 key 代表不同组件 
// 点击按扭 的变化过程为 删除 Taylor 重新渲染 Sarah 所以会重置 state
// Remember that keys are not globally unique. key不是全局唯一的
// They only specify the position within the parent. 仅是指定在父组件中的位置
export function Scoreboard3() {
    const [isPlayerA, setIsPlayerA] = useState(true);
    return (
        <div>
            {isPlayerA ? (
                <Counter4 key="Taylor" person="Taylor" />
            ) : (
                <Counter4 key="Sarah" person="Sarah" />
            )}
            <button onClick={() => {
                setIsPlayerA(!isPlayerA);
            }}>
                相同位置 不同的key 不同组件 需要重新渲染 状态改变
            </button>
        </div>
    );
}


function Chat({ contact }) {
    const [text, setText] = useState('');
    return (
        <section className="chat">
            <textarea
                value={text}
                placeholder={'Chat to ' + contact.name}
                onChange={e => setText(e.target.value)}
            />
            <br />
            <button>Send to {contact.email}</button>
        </section>
    );
}
function ContactList({
    selectedContact,
    contacts,
    onSelect
}) {
    return (
        <section className="contact-list">
            <ul>
                {contacts.map(contact =>
                    <li key={contact.id}>
                        <button onClick={() => {
                            onSelect(contact);
                        }}>
                            {contact.name}
                        </button>
                    </li>
                )}
            </ul>
        </section>
    );
}
// 在输入框输入内容 选择不同 联系人时 不会清空输入框内容 仅是改变 placeholder
// 在聊天程序中不应该如此，需要清空输入框 防止将发送给上一个人的消息 发送给点击用户
// 处理方式 可以给 Chat 加个 key 每次都渲染
export function Messenger() {
    const [to, setTo] = useState(contacts[0]);
    return (
        <div>
            <ContactList
                contacts={contacts}
                selectedContact={to}
                onSelect={contact => setTo(contact)}
            />
            <Chat key={to.id} contact={to} />
        </div>
    )
}

const contacts = [
    { id: 0, name: 'Taylor', email: 'taylor@mail.com' },
    { id: 1, name: 'Alice', email: 'alice@mail.com' },
    { id: 2, name: 'Bob', email: 'bob@mail.com' }
];


// 保持 输入框信息
export function KeepState() {
    const [showHint, setShowHint] = useState(false);
    return (
        <div>
            {showHint && <p><i>Hint: Your favorite city?</i></p>}
            <Form />
            <button onClick={() => {
                setShowHint(!showHint);
            }}>{showHint ? 'Hide ' : 'Show '}hint</button>
        </div>
    )
}

function Form() {
    const [text, setText] = useState('');
    return (
        <textarea
            value={text}
            onChange={e => setText(e.target.value)}
        />
    );
}

//交换两个 表单
//React 可以根据 key 匹配到正确的状态 即使 他们在父组件中的顺序发生变化
export function SwapForm() {

    const [reverse, setReverse] = useState(false);

    let checkbox = (
        <label>
            <input
                type="checkbox"
                checked={reverse}
                onChange={e => setReverse(e.target.checked)}
            />
            Reverse order
        </label>
    );

    return (reverse ?
        <>

            <Field label="Last name" key="Last name" />
            <Field label="First name" key="First name" />
            {checkbox}
        </>
        :
        <>
            <Field label="First name" key="First name" />
            <Field label="Last name" key="Last name" />
            {checkbox}
        </>

    );
}

function Field({ label }) {
    const [name, setName] = useState('');
    return (
        <label>
            {label}:{' '}
            <input
                type="text"
                value={name}
                placeholder={label}
                onChange={e => setName(e.target.value)}
            />
        </label>
    );
}

const initialContacts = [
    { id: 0, name: 'Taylor', email: 'taylor@mail.com' },
    { id: 1, name: 'Alice', email: 'alice@mail.com' },
    { id: 2, name: 'Bob', email: 'bob@mail.com' }
];

function ContactList2({
    contacts,
    selectedId,
    onSelect
}) {
    return (
        <section>
            <ul>
                {contacts.map(contact =>
                    <li key={contact.id}>
                        <button onClick={() => {
                            onSelect(contact.id);
                        }}>
                            {contact.id === selectedId ?
                                <b>{contact.name}</b> :
                                contact.name
                            }
                        </button>
                    </li>
                )}
            </ul>
        </section>
    );
}

function EditContact({ initialData, onSave }) {
    const [name, setName] = useState(initialData.name);
    const [email, setEmail] = useState(initialData.email);
    return (
        <section>
            <label>
                Name:{' '}
                <input
                    type="text"
                    value={name}
                    onChange={e => setName(e.target.value)}
                />
            </label>
            <label>
                Email:{' '}
                <input
                    type="email"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                />
            </label>
            <button onClick={() => {
                const updatedData = {
                    id: initialData.id,
                    name: name,
                    email: email
                };
                onSave(updatedData);
            }}>
                Save
            </button>
            <button onClick={() => {
                setName(initialData.name);
                setEmail(initialData.email);
            }}>
                Reset
            </button>
        </section>
    );
}

export function ContactManager() {
    const [
        contacts,
        setContacts
    ] = useState(initialContacts);
    const [
        selectedId,
        setSelectedId
    ] = useState(0);
    const selectedContact = contacts.find(c =>
        c.id === selectedId
    );

    function handleSave(updatedData) {
        const nextContacts = contacts.map(c => {
            if (c.id === updatedData.id) {
                return updatedData;
            } else {
                return c;
            }
        });
        setContacts(nextContacts);
    }

    return (
        <div>
            <ContactList2
                contacts={contacts}
                selectedId={selectedId}
                onSelect={id => setSelectedId(id)}
            />
            <hr />
            {/* Give key={selectedId} to the EditContact component. This way, switching between different contacts will reset the form: */}
            <EditContact
                key={selectedId}
                initialData={selectedContact}
                onSave={handleSave}
            />
        </div>
    )
}

let images = [{
    place: 'Penang, Malaysia',
    src: 'https://i.imgur.com/FJeJR8M.jpg'
}, {
    place: 'Lisbon, Portugal',
    src: 'https://i.imgur.com/dB2LRbj.jpg'
}, {
    place: 'Bilbao, Spain',
    src: 'https://i.imgur.com/z08o2TS.jpg'
}, {
    place: 'Valparaíso, Chile',
    src: 'https://i.imgur.com/Y3utgTi.jpg'
}, {
    place: 'Schwyz, Switzerland',
    src: 'https://i.imgur.com/JBbMpWY.jpg'
}, {
    place: 'Prague, Czechia',
    src: 'https://i.imgur.com/QwUKKmF.jpg'
}, {
    place: 'Ljubljana, Slovenia',
    src: 'https://i.imgur.com/3aIiwfm.jpg'
}, {
    place: 'logo192',
    src: './logo192.png'
}, {
    place: 'logo512',
    src: './logo512.png'
}];

// 点击next 浏览器开始加载图片，图片都在同一个<img> 标记中，默认情况下
// 在加载下一个图像之前，仍然会看到上一个图片
// 如果文字与图片匹配很重要，则需要让 <img> 每次重新渲染 加个 key
export function Gallery() {
    const [index, setIndex] = useState(0);
    const hasNext = index < images.length - 1;

    function handleClick() {
        if (hasNext) {
            setIndex(index + 1);
        } else {
            setIndex(0);
        }
    }

    let image = images[index];
    return (
        <>
            <button onClick={handleClick}>
                Next
            </button>
            <h3>
                Image {index + 1} of {images.length}
            </h3>
            <img key={image.src} src={image.src} />
            <p>
                {image.place}
            </p>
        </>
    );
}

const contacts2 = [
    { id: 0, name: 'Alice', email: 'alice@mail.com' },
    { id: 1, name: 'Bob', email: 'bob@mail.com' },
    { id: 2, name: 'Taylor', email: 'taylor@mail.com' }
];

function Contact({ contact }) {
    const [expanded, setExpanded] = useState(false);
    return (
        <>
            <p><b>{contact.name}</b></p>
            {expanded &&
                <p><i>{contact.email}</i></p>
            }
            <button onClick={() => {
                setExpanded(!expanded);
            }}>
                {expanded ? 'Hide' : 'Show'} email
            </button>
        </>
    );
}
export function ContactList3() {
    const [reverse, setReverse] = useState(false);

    const displayedContacts = [...contacts2];
    if (reverse) {
        displayedContacts.reverse();
    }

    return (
        <>
            <label>
                <input
                    type="checkbox"
                    value={reverse}
                    onChange={e => {
                        setReverse(e.target.checked)
                    }}
                />{' '}
                Show in reverse order
            </label>
            <ul>
                {displayedContacts.map((contact) =>
                    <li key={contact.id}>
                        <Contact  contact={contact} />
                    </li>
                )}
            </ul>
        </>
    );
}


