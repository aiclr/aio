import React from 'react';
import ReactDOM from 'react-dom/client';
import { SockJS_Client } from "./SockJSClient";
import { StompjsClient } from "./StompjsClient";
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.min.css'

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    <React.StrictMode>
        <>
            <SockJS_Client />
        </>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();