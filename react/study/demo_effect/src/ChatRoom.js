import { useState, useEffect,useEvent} from 'react';
import Toastify from 'toastify-js';
import 'toastify-js/src/toastify.css';

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
    //应该返回一个清理函数，告诉 react 怎么从服务端断开连接
    useEffect(() => {
        const connection = createConnection();
        connection.connect();
        return () => connection.disconnect();
    }, []);
    return <h1>Welcome to the chat!</h1>;
}


const serverUrl = 'https://localhost:1234';


//-------------------------------------------------------------//

function createConnection2(serverUrl, roomId) {
    // A real implementation would actually connect to the server
    return {
        connect() {
            console.log('✅ Connecting to "' + roomId + '" room at ' + serverUrl + '...');
        },
        disconnect() {
            console.log('❌ Disconnected from "' + roomId + '" room at ' + serverUrl);
        }
    };
}




function ChatRoom2({ roomId }) {
    // effect 取决于 roomid 的值，意味着可以在重新渲染时更改
    // 注意在更新 roomid 后，effect 会重新同步 (重新连接到服务器)
    useEffect(() => {
        const connection = createConnection2(serverUrl, roomId);
        connection.connect();
        return () => connection.disconnect();
    }, [roomId]);

    return <h1>Welcome to the {roomId} room!</h1>;
}


export function ChatRoom2App() {
    const [roomId, setRoomId] = useState('general');

    return (
        <>
            <label>
                Choose the chat room:{' '}
                <select
                    value={roomId}
                    onChange={e => setRoomId(e.target.value)}
                >
                    <option value="general">general</option>
                    <option value="travel">travel</option>
                    <option value="music">music</option>
                </select>
            </label>
            <hr />
            <ChatRoom2 roomId={roomId} />
        </>
    );

}





//-------------------------------------------------------------//



function createConnection3(serverUrl, roomId) {
    // A real implementation would actually connect to the server
    let connectedCallback;
    let timeout;

    return {
        connect() {
            timeout = setTimeout(() => {
                if (connectedCallback) {
                    connectedCallback()
                }
            }, 100);
        },
        on(event, callback) {
            if (connectedCallback) {
                throw Error('Cannot add the handler twice')
            }
            if (event !== 'connected') {
                throw Error('Only "connected" event is supported.');
            }
            connectedCallback = callback;
        },
        disconnect() {
            clearTimeout(timeout);
        }
    };
}

function ChatRoom3({ roomId, theme }) {

    useEffect(() => {
        const connection = createConnection3(serverUrl, roomId);
        connection.on('connected', () => {
            showNotification('Connected!', theme);
        });
        connection.connect();
        return () => connection.disconnect();
    }, [roomId, theme]);

    return <h1>Welcome to the {roomId} room!</h1>;
}

function showNotification(message, theme) {
    Toastify({
        text: message,
        duration: 2000,
        gravity: 'top',
        position: 'right',
        style: {
            background: theme === 'dark' ? 'black' : 'white',
            color: theme === 'dark' ? 'white' : 'black',
        },
    }).showToast();
}


export function ChatRoom3App() {
    const [roomId, setRoomId] = useState('general');
    // 修改主题后 会重新渲染 重新 connect 不合理
    const [isDark, setIsDark] = useState(false);

    return (
        <>
            <label>
                Choose the chat room:{' '}
                <select
                    value={roomId}
                    onChange={e => setRoomId(e.target.value)}
                >
                    <option value="general">general</option>
                    <option value="travel">travel</option>
                    <option value="music">music</option>
                </select>
            </label>
            <label>
                <input
                    type="checkbox"
                    checked={isDark}
                    onChange={e => setIsDark(e.target.checked)}
                />
                Use dark theme
            </label>
            <hr />
            <ChatRoom3
                roomId={roomId}
                theme={isDark ? 'dark' : 'light'}
            />
        </>
    );
}




//-------------------------------------------------------------//

// 使用 useEvent 修复 切换 主题 重新渲染 重新 connect 的 问题

function ChatRoom4({ roomId, theme }) {

    const onConnected = useEvent(() => {
        showNotification('Connected!', theme);
    });

    useEffect(() => {
        const connection = createConnection3(serverUrl, roomId);
        connection.on('connected', () => {
            onConnected();
        });
        connection.connect();
        return () => connection.disconnect();
    }, [roomId]);

    return <h1>Welcome to the {roomId} room!</h1>
}

export function ChatRoom4App() {
    const [roomId, setRoomId] = useState('general');
    const [isDark, setIsDark] = useState(false);
    return (
        <>
            <label>
                Choose the chat room:{' '}
                <select
                    value={roomId}
                    onChange={e => setRoomId(e.target.value)}
                >
                    <option value="general">general</option>
                    <option value="travel">travel</option>
                    <option value="music">music</option>
                </select>
            </label>
            <label>
                <input
                    type="checkbox"
                    checked={isDark}
                    onChange={e => setIsDark(e.target.checked)}
                />
                Use dark theme
            </label>
            <hr />
            <ChatRoom4
                roomId={roomId}
                theme={isDark ? 'dark' : 'light'}
            />
        </>
    );
}


