import { useState } from 'react';
import { useImmer } from 'use-immer';

let nextId = 3;
const initialTodos = [
    { id: 0, title: 'Buy milk', done: true },
    { id: 1, title: 'Eat tacos', done: false },
    { id: 2, title: 'Brew tea', done: false },
];

export function TaskApp() {
    const [todos, setTodos] = useState(initialTodos);

    function handleAddTodo(title) {
        setTodos([
            ...todos,
            {
                id: nextId++,
                title: title,
                done: false
            }
        ]);
    }

    function handleChangeTodo(nextTodo) {

        setTodos(
            todos.map(todo => todo.id === nextTodo.id ? nextTodo : todo)
        );
    }

    function handleDeleteTodo(todoId) {
        setTodos(todos.filter(todo => todo.id !== todoId));
    }

    return (
        <>
            <AddTodo
                onAddTodo={handleAddTodo}
            />
            <TaskList
                todos={todos}
                onChangeTodo={handleChangeTodo}
                onDeleteTodo={handleDeleteTodo}
            />
        </>
    );
}

export function TaskAppUseImmer() {
    const [todos, updateTodos] = useImmer(initialTodos);

    function handleAddTodo(title) {
        updateTodos(draft => {
            draft.push({
                id: nextId++,
                title: title,
                done: false
            })
        });
    }

    function handleChangeTodo(nextTodo) {

        updateTodos(draft => {
            const tmp = draft.find(todo => todo.id === nextTodo.id);
            tmp.title = nextTodo.title;
            tmp.done = nextTodo.done;

        });
    }

    function handleDeleteTodo(todoId) {
        updateTodos(draft => {
            const index = draft.findIndex(e => e.id === todoId);
            draft.splice(index, 1);
        })
    }

    return (
        <>
            <AddTodo
                onAddTodo={handleAddTodo}
            />
            <TaskList
                todos={todos}
                onChangeTodo={handleChangeTodo}
                onDeleteTodo={handleDeleteTodo}
            />
        </>
    );
}



function TaskList({
    todos,
    onChangeTodo,
    onDeleteTodo
}) {
    return (
        <ul>
            {todos.map(todo => (
                <li key={todo.id}>
                    <Task
                        todo={todo}
                        onChange={onChangeTodo}
                        onDelete={onDeleteTodo}
                    />
                </li>
            ))}
        </ul>
    );
}

function Task({ todo, onChange, onDelete }) {
    const [isEditing, setIsEditing] = useState(false);
    let todoContent;
    if (isEditing) {
        todoContent = (
            <>
                <input
                    value={todo.title}
                    onChange={e => {
                        onChange({
                            ...todo,
                            title: e.target.value
                        });
                    }} />
                <button onClick={() => setIsEditing(false)}>
                    Save
                </button>
            </>
        );
    } else {
        todoContent = (
            <>
                {todo.title}
                <button onClick={() => setIsEditing(true)}>
                    Edit
                </button>
            </>
        );
    }
    return (
        <label>
            <input
                type="checkbox"
                checked={todo.done}
                onChange={e => {
                    onChange({
                        ...todo,
                        done: e.target.checked
                    });
                }}
            />
            {todoContent}
            <button onClick={() => onDelete(todo.id)}>
                Delete
            </button>
        </label>
    );
}

function AddTodo({ onAddTodo }) {
    const [title, setTitle] = useState('');
    return (
        <>
            <input
                placeholder="Add todo"
                value={title}
                onChange={e => setTitle(e.target.value)}
            />
            <button onClick={() => {
                setTitle('');
                onAddTodo(title);
            }}>Add</button>
        </>
    )
}
