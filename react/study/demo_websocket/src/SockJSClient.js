import { useState } from "react";
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import './App.css';

// [官方文档](http://jmesnil.net/stomp-websocket/doc/)

const url = 'http://127.0.0.1:8090/gs-guide-websocket/';
const topic = '/topic/greetings';
// let index = 0;

export function SockJS_Client() {

    const [client, setClient] = useState(null)
    const [connected, setConnected] = useState(false);
    const [name, setName] = useState('SockJS');
    const [greetings, setGreetings] = useState([]);
    console.log(process.env);
    console.log(process.env.REACT_APP_URL);
    let index = 0;

    function message_callback(message) {
        //todo 此处 有bug greetings 始终是[]
        console.log("greetings===>");
        console.log(greetings);
        // called when the client receives a STOMP message from the server
        if (message && message.body) {
            let name=JSON.parse(message.body).content;
            setGreetings([
                ...greetings,
                { id: index++, name: name}
            ]);
        } else {
            alert("got empty message");
        }
    }

    function error_callback(error) {
        if(error && error.headers && error.headers.message)
            alert(error.headers.message);
    }


    function connect(e) {
        // 阻止 form 表单 默认的 submit 提交行文
        e.preventDefault();
        console.log(client);
        let ws = new SockJS(url);
        let cli = Stomp.over(ws);

        cli.connect({},
            () => {
                var subscription = cli.subscribe(topic, message_callback);
                cli.heartbeat.outgoing = 20000;
                cli.heartbeat.incoming = 0;
                setConnected(true);
                setClient(cli);
            },
            error_callback);
    }



    function disconnect(e) {
        // 阻止 form 表单 默认的 submit 提交行文
        e.preventDefault();
        console.log(client);
        setConnected(false);
        let cli = client;

        if (cli) {
            cli.disconnect(() => {
                setClient(cli);
                alert("See you next time!");
            });
        }

    }

    function sendName(e) {
        // 阻止 form 表单 默认的 submit 提交行文
        e.preventDefault();
        console.log(client);
        if (client && client.connected) {
            client.send("/app/hello", {}, JSON.stringify({ 'name': name }));
        }
    }

    return (
        <div id="main-content" className="container">
            <div className="row">
                <div className="col-md-6">
                    <form className="form-inline">
                        <div className="form-group">
                            <label htmlFor="connect">WebSocket connection:</label>
                            <button id="connect" className="btn btn-default" type="submit" onClick={(e) => connect(e)} disabled={connected}>
                                Connect
                            </button>
                            <button id="disconnect" className="btn btn-default" type="submit" onClick={(e) => disconnect(e, client)} disabled={!connected}>
                                Disconnect
                            </button>
                        </div>
                    </form>
                </div>
                <div className="col-md-6">
                    <form className="form-inline" onSubmit={sendName}>
                        <div className="form-group">
                            <label htmlFor="name">What is your name?</label>
                            <input value={name} onChange={event => setName(event.target.value)}
                                type="text" id="name" className="form-control" placeholder="Your name here..." />
                        </div>
                        <button id="send" className="btn btn-default" type="submit" onClick={(e) => sendName(e, client)}>Send</button>
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
                            {
                                greetings.map((tmp) => <tr key={tmp.id}><td>{tmp.name}</td></tr>)
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}
