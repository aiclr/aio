import { useState } from 'react';
import { TasksProvider ,useTasks,useTasksDispatch} from './TaskContext';

let nextId = 3;


function TaskList() {
    const tasks = useTasks();
    return (
      <ul>
        {tasks.map(task => (
          <li key={task.id}>
            <Task task={task} />
          </li>
        ))}
      </ul>
    );
  }
  
  function Task({ task }) {
    const [isEditing, setIsEditing] = useState(false);
    const dispatch = useTasksDispatch();
    let taskContent;
    if (isEditing) {
      taskContent = (
        <>
          <input
            value={task.text}
            onChange={e => {
              dispatch({
                type: 'changed',
                task: {
                  ...task,
                  text: e.target.value
                }
              });
            }} />
          <button onClick={() => setIsEditing(false)}>
            Save
          </button>
        </>
      );
    } else {
      taskContent = (
        <>
          {task.text}
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
          checked={task.done}
          onChange={e => {
            dispatch({
              type: 'changed',
              task: {
                ...task,
                done: e.target.checked
              }
            });
          }}
        />
        {taskContent}
        <button onClick={() => {
          dispatch({
            type: 'deleted',
            id: task.id
          });
        }}>
          Delete
        </button>
      </label>
    );
  }
  



function AddTask() {
    const [text, setText] = useState('');
  const dispatch = useTasksDispatch();
  return (
    <>
      <input
        placeholder="Add task"
        value={text}
        onChange={e => setText(e.target.value)}
      />
      <button onClick={() => {
        setText('');
        dispatch({
          type: 'added',
          id: nextId++,
          text: text,
        }); 
      }}>Add</button>
    </>
  );
}

// 将 state 和 dispatch 传递下去
export function TaskApp() {
    return (
      <TasksProvider>
        <h1>Day off in Kyoto</h1>
        <AddTask />
        <TaskList />
      </TasksProvider>
    );
  }



