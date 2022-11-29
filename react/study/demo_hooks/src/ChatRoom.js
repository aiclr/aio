import { useState, useEffect } from 'react';
import Toastify from 'toastify-js';
import 'toastify-js/src/toastify.css';

function showNotification(message, theme = 'dark') {
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

function createConnection({ serverUrl, roomId }) {
    // A real implementation would actually connect to the server
    if (typeof serverUrl !== 'string') {
        throw Error('Expected serverUrl to be a string. Received: ' + serverUrl);
    }
    if (typeof roomId !== 'string') {
        throw Error('Expected roomId to be a string. Received: ' + roomId);
    }
    let intervalId;
    let messageCallback;
    return {
        connect() {
            console.log('✅ Connecting to "' + roomId + '" room at ' + serverUrl + '...');
            clearInterval(intervalId);
            intervalId = setInterval(() => {
                if (messageCallback) {
                    if (Math.random() > 0.5) {
                        messageCallback('hey')
                    } else {
                        messageCallback('lol');
                    }
                }
            }, 3000);
        },
        disconnect() {
            clearInterval(intervalId);
            messageCallback = null;
            console.log('❌ Disconnected from "' + roomId + '" room at ' + serverUrl + '');
        },
        on(event, callback) {
            if (messageCallback) {
                throw Error('Cannot add the handler twice.');
            }
            if (event !== 'message') {
                throw Error('Only "message" event is supported.');
            }
            messageCallback = callback;
        },
    };
}
// 将 useEffect 提出为 自定义 hooks
function ChatRoom({ roomId }) {
    const [serverUrl, setServerUrl] = useState('https://localhost:1234');

    useEffect(() => {
        const options = {
            serverUrl: serverUrl,
            roomId: roomId
        };
        const connection = createConnection(options);
        connection.on('message', (msg) => {
            showNotification('New message: ' + msg);
        });
        connection.connect();
        return () => connection.disconnect();
    }, [roomId, serverUrl]);

    return (
        <>
            <label>
                Server URL:
                <input value={serverUrl} onChange={e => setServerUrl(e.target.value)} />
            </label>
            <h1>Welcome to the {roomId} room!</h1>
        </>
    );
}
// 提出的 useEffect
function useChatRoom({ serverUrl, roomId }) {
    useEffect(() => {
        const options = {
            serverUrl: serverUrl,
            roomId: roomId
        };
        const connection = createConnection(options);
        connection.connect();
        connection.on('message', (msg) => {
            showNotification('New message: ' + msg);
        });
        return () => connection.disconnect();
    }, [roomId, serverUrl]);
}

//使用自定义 hooks
function ChatRoomPlus({ roomId }) {
    const [serverUrl, setServerUrl] = useState('https://localhost:1234');
    useChatRoom({ serverUrl, roomId });
    return (
        <>
            <label>
                Server URL:
                <input value={serverUrl} onChange={e => setServerUrl(e.target.value)} />
            </label>
            <h1>Welcome to the {roomId} room!</h1>
        </>
    );
}

export function Chat() {
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
            {/* <ChatRoom roomId={roomId}/>  */}
            <ChatRoomPlus roomId={roomId}/> 
        </>
    );
}
