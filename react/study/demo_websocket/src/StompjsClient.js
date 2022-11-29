import Stomp from 'stompjs';
import { useEffect, useState } from "react";
import './App.css';

// [官方文档](http://jmesnil.net/stomp-websocket/doc/)

const url = 'ws://127.0.0.1:8080/gs-guide-websocket/';
const topic = '/topic/greetings';

export function StompjsClient() {
    let client = null;
    const [connected, setConnected] = useState(false);
    const [name, setName] = useState('Stomp');
    var headers = {
        'Access-Control-Allow-Origin': 'http://localhost:8080',
        'Access-Control-Allow-Credentials': true,
        // 'Access-Control-Allow-Origin': 'localhost:3000',
        // 'Access-Control-Allow-Origin': 'localhost:3000',
        'Access-Control-Max-Age': 31536000,
    };

    useEffect(() => {
        client = Stomp.client(url);
        return () => disconnect();
    }, []);


  function connect() {
        client.connect(headers,
            () => {
                alert('connecting...');
                setConnected(true);
                // 订阅消息
                client.subscribe(topic, (message) => {
                    // called when the client receives a STOMP message from the server
                    if (message.body) {
                        alert("got message with body " + JSON.parse(message.body).content)
                    } else {
                        alert("got empty message");
                    }
                });
                client.heartbeat.outgoing = 20000;
                client.heartbeat.incoming = 0;
            },
            (error) => {
                alert(error.headers.message);
            });
    }

    function disconnect() {
        if (client !== null) {
            client.disconnect(() => {
                alert("See you next time!");
            });
        }
        setConnected(false);
        alert('Disconnected');
    }

     function sendName() {
        if (client.connected) {
            client.send("/app/hello", {}, JSON.stringify({ 'name': { name } }));
        }
    }

    return (
        <div id="main-content" className="container">
            <div className="row">
                <div className="col-md-6">
                    <form className="form-inline">
                        <div className="form-group">
                            <label htmlFor="connect">WebSocket connection:</label>
                            <button id="connect" className="btn btn-default" type="submit" onClick={connect} disabled={connected}>
                                Connect
                            </button>
                            <button id="disconnect" className="btn btn-default" type="submit" onClick={disconnect} disabled={!connected}>
                                Disconnect
                            </button>
                        </div>
                    </form>
                </div>
                <div className="col-md-6">
                    <form className="form-inline">
                        <div className="form-group">
                            <label htmlFor="name">What is your name?</label>
                            <input value={name} onChange={event => setName(event.target.value)}
                                type="text" id="name" className="form-control" placeholder="Your name here..." />
                        </div>
                        <button id="send" className="btn btn-default" type="submit" onClick={sendName}>Send</button>
                    </form>
                </div>
            </div>
            <div className="row">
                <div className="col-md-12">
                    <table id="conversation" className="table table-striped">
                        <thead>
                            <tr>
                                <th>Greetings</th>
                            </tr>
                        </thead>
                        <tbody id="greetings">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}
