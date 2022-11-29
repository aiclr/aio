import { useState, useRef, useEffect, useMemo } from 'react'

let nextId = 0;

function createTodo(text, completed = false) {
  return {
    id: nextId++,
    text,
    completed
  };
}

const initialTodos = [
  createTodo('Get apples', true),
  createTodo('Get oranges', true),
  createTodo('Get carrots'),
];

function NewTodo({ onAdd }) {
  const [text, setText] = useState('');

  function handleAddClick() {
    setText('');
    onAdd(createTodo(text));
  }

  return (
    <>
      <input value={text} onChange={e => setText(e.target.value)} />
      <button onClick={handleAddClick}>
        Add
      </button>
    </>
  );
}

// 简化版本
export function TodoList() {
  const [todos, setTodos] = useState(initialTodos);
  const [showActive, setShowActive] = useState(false);

  // 活动的 待办事件
  const activeTodos = todos.filter(todo => !todo.completed);

  return (
    <>
      <label>
        <input
          type="checkbox"
          checked={showActive}
          onChange={e => setShowActive(e.target.checked)}
        />
        Show only active todos
      </label>
      <NewTodo onAdd={newTodo => setTodos([...todos, newTodo])} />
      <ul>
        {todos.map(todo => (
          <li key={todo.id} hidden={todo.completed && showActive}>
            {todo.completed ? <s>{todo.text}</s> : todo.text}
          </li>
        ))}
      </ul>
      <footer>
        {activeTodos.length} todos left
      </footer>
    </>
  );
}

// 冗余 useEffect
export function TodoListBug() {
  const [todos, setTodos] = useState(initialTodos);
  const [showActive, setShowActive] = useState(false);
  const [activeTodos, setActiveTodos] = useState([]);
  const [visibleTodos, setVisibleTodos] = useState([]);
  const [footer, setFooter] = useState(null);

  useEffect(() => {
    setActiveTodos(todos.filter(todo => !todo.completed));
  }, [todos]);

  useEffect(() => {
    setVisibleTodos(showActive ? activeTodos : todos);
  }, [showActive, todos, activeTodos]);

  useEffect(() => {
    setFooter(
      <footer>
        {activeTodos.length} todos left
      </footer>
    );
  }, [activeTodos]);

  return (
    <>
      <label>
        <input
          type="checkbox"
          checked={showActive}
          onChange={e => setShowActive(e.target.checked)}
        />
        Show only active todos
      </label>
      <NewTodo onAdd={newTodo => setTodos([...todos, newTodo])} />
      <ul>
        {visibleTodos.map(todo => (
          <li key={todo.id}>
            {todo.completed ? <s>{todo.text}</s> : todo.text}
          </li>
        ))}
      </ul>
      {footer}
    </>
  );
}



let calls = 0;

function getVisibleTodos(todos, showActive) {
  console.log(`getVisibleTodos() was called ${++calls} times`);
  const activeTodos = todos.filter(todo => !todo.completed);
  const visibleTodos = showActive ? activeTodos : todos;
  return visibleTodos;
}

// 使用 useMemo 缓存 hoosks
export function TodoList2() {
  const [todos, setTodos] = useState(initialTodos);
  const [showActive, setShowActive] = useState(false);
  const [text, setText] = useState('');
  // useMemo 缓存技术 如果 [todos, showActive] 不变 则 不重新请求 getVisibleTodos
  const visibleTodos = useMemo(
    () => getVisibleTodos(todos, showActive),
    [todos, showActive]);

  function handleAddClick() {
    setText('');//新增后清空输入框
    setTodos([...todos, createTodo(text)]);
  }

  return (
    <>
      <label>
        <input
          type="checkbox"
          checked={showActive}
          onChange={e => setShowActive(e.target.checked)}
        />
        Show only active todos
      </label>
      <input value={text} onChange={e => setText(e.target.value)} />
      <button onClick={handleAddClick}>
        Add
      </button>
      <ul>
        {visibleTodos.map(todo => (
          <li key={todo.id}>
            {todo.completed ? <s>{todo.text}</s> : todo.text}
          </li>
        ))}
      </ul>
    </>
  );
}

//将 input state 与 showActive state 通过封装组件的形式分离
export function TodoList2Plus() {
  const [todos, setTodos] = useState(initialTodos);
  const [showActive, setShowActive] = useState(false);
  const visibleTodos = getVisibleTodos(todos, showActive);

  return (
    <>
      <label>
        <input
          type="checkbox"
          checked={showActive}
          onChange={e => setShowActive(e.target.checked)}
        />
        Show only active todos
      </label>
      <AddTodo onAdd={newTodo => setTodos([...todos, newTodo])} />
      <ul>
        {visibleTodos.map(todo => (
          <li key={todo.id}>
            {todo.completed ? <s>{todo.text}</s> : todo.text}
          </li>
        ))}
      </ul>
    </>
  );
}

function AddTodo({ onAdd }) {
  const [text, setText] = useState('');
  return (
    <>
      <input value={text} onChange={e => setText(e.target.value)} />
      <button onClick={() => {
        setText('');
        onAdd(createTodo(text));
      }
      }>
        Add
      </button>
    </>
  );
}



export function TodoList2Bug() {
  const [todos, setTodos] = useState(initialTodos);
  const [showActive, setShowActive] = useState(false);
  const [text, setText] = useState('');
  const [visibleTodos, setVisibleTodos] = useState([]);

  useEffect(() => {
    setVisibleTodos(getVisibleTodos(todos, showActive));
  }, [todos, showActive]);

  function handleAddClick() {
    setText('');
    setTodos([...todos, createTodo(text)]);
  }

  return (
    <>
      <label>
        <input
          type="checkbox"
          checked={showActive}
          onChange={e => setShowActive(e.target.checked)}
        />
        Show only active todos
      </label>
      <input value={text} onChange={e => setText(e.target.value)} />
      <button onClick={handleAddClick}>
        Add
      </button>
      <ul>
        {visibleTodos.map(todo => (
          <li key={todo.id}>
            {todo.completed ? <s>{todo.text}</s> : todo.text}
          </li>
        ))}
      </ul>
    </>
  );
}


let contactId = 0;

const initContacts = [
  createContact('Alice', 'alice@mail.com'),
  createContact('Taylor', 'taylor@mail.com'),
  createContact('Bob', 'bob@mail.com'),
];

function createContact(name, email) {
  return {
    id: contactId++,
    name,
    email
  }
}

function getContact(contacts, selectedId) {
  return contacts.find(tmp => selectedId === tmp.id)
}

export function ContactList() {

  const [contacts, setContacts] = useState(initContacts);
  const [selectedId, setSectedId] = useState(0);
  const savedContact = getContact(contacts, selectedId);

  function onSave(contact) {
    setContacts([
      // 注意浅拷贝问题 不能直接修改选择的项，因为 state 需要 setState 来修改。
      ...contacts.map(element => {
        if (element.id === contact.id) {
          return contact;
        } else {
          return element;
        }
      })
    ]);
  }

  return (
    <>
      {contacts.map(contact =>
      (<button
        key={contact.id}
        onClick={() => setSectedId(contact.id)}>{contact.name}</button>)
      )}
      <hr />
      {/* <EditContact key={savedContact.id} savedContact={selectContact} onSave={onSave} /> */}
      <EditContactPlus
        key={savedContact.id}
        savedContact={savedContact}
        onSave={onSave}
      />
    </>
  );
}

// 不使用 effect 通过 加 key 实现 重置效果
function EditContactPlus({ savedContact, onSave }) {
  const [name, setName] = useState(savedContact.name);
  const [email, setEmail] = useState(savedContact.email);

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
          id: savedContact.id,
          name: name,
          email: email
        };
        onSave(updatedData);
      }}>
        Save
      </button>
      <button onClick={() => {
        setName(savedContact.name);
        setEmail(savedContact.email);
      }}>
        Reset
      </button>
    </section>
  );
}
// 使用 effect 实现 重置效果
function EditContact({ savedContact, onSave }) {
  const [name, setName] = useState(savedContact.name);
  const [email, setEmail] = useState(savedContact.email);

  useEffect(() => {
    setName(savedContact.name);
    setEmail(savedContact.email);
  }, [savedContact]);

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
          id: savedContact.id,
          name: name,
          email: email
        };
        onSave(updatedData);
      }}>
        Save
      </button>
      <button onClick={() => {
        setName(savedContact.name);
        setEmail(savedContact.email);
      }}>
        Reset
      </button>
    </section>
  );
}


function sendMessage(message) {
  console.log('Sending message: ' + message);
}


// 不使用 effects 提交表单
// sendMessage(message);应该由于用户操作发生，而不是因为窗口渲染 所以将其移到 handleSubmit(e) 才合理
export function FormUseEffect() {
  const [showForm, setShowForm] = useState(false);
  const [message, setMessage] = useState('');

  function handleSubmit(e) {
    e.preventDefault();
    sendMessage(message);
    setShowForm(false);
  }

  if (!showForm) {
    return (
      <>
        <h1>Thanks for using our services!</h1>
        <button onClick={() => {
          setMessage('');
          setShowForm(true);
        }}>
          Open chat
        </button>
      </>
    );
  }

  return (
    <form onSubmit={handleSubmit}>
      <textarea
        placeholder="Message"
        value={message}
        onChange={e => setMessage(e.target.value)}
      />
      <button type="submit" disabled={message === ''}>
        Send
      </button>
    </form>
  );
}


// showForm 默认 true 正常
// showForm 默认 false 。console 会显示 发送空消息
//sendMessage(message);应该由于用户操作发生，而不是因为窗口渲染 所以将其移到 handleSubmit(e) 才合理
export function Form() {
  const [showForm, setShowForm] = useState(true);
  const [message, setMessage] = useState('');

  useEffect(() => {
    if (!showForm) {
      sendMessage(message);
    }
  }, [showForm, message]);

  function handleSubmit(e) {
    e.preventDefault();
    setShowForm(false);
  }

  if (!showForm) {
    return (
      <>
        <h1>Thanks for using our services!</h1>
        <button onClick={() => {
          setMessage('');
          setShowForm(true);
        }}>
          Open chat
        </button>
      </>
    );
  }

  return (
    <form onSubmit={handleSubmit}>
      <textarea
        placeholder="Message"
        value={message}
        onChange={e => setMessage(e.target.value)}
      />
      <button type="submit" disabled={message === ''}>
        Send
      </button>
    </form>
  );
}




export function Timer() {
  const [count, setCount] = useState(0);
  const [increment, setIncrement] = useState(1);

  function onTick() {
    setCount(count + increment);
  }

  useEffect(() => {
    const id = setInterval(onTick, 1000);
    return () => clearInterval(id);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [onTick]);// 如果不依赖 onTick 则每次使用 初始化时的  function onTick() 即每次都是 setCount(0 + 1);

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