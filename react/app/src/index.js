import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter, HashRouter } from "react-router-dom";
import store from './redux/store';
import 'bootstrap/dist/css/bootstrap.css'

import App from './component/app/app'


const root = ReactDOM.createRoot(document.getElementById('root'));


//react插件reactRedux
root.render(
    <React.StrictMode>
        <BrowserRouter>
            <App />
        </BrowserRouter>
    </React.StrictMode>
);



//原生redux使用方式
// function render2() {
//     root.render(
//         <React.StrictMode>
//             <BrowserRouter>
//                 <App store={store} />
//             </BrowserRouter>
//         </React.StrictMode>
//     );
// }
// //页面初始化显示
// render2()
// //订阅监听 store中的状态变化了，就会自动调用重绘dom
// store.subscribe(render2)
