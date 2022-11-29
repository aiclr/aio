/*
* 生成store 写法固定
*
* */
import {legacy_createStore as createStore,applyMiddleware} from "redux"//applyMiddleware应用异步中间件
import thunk from 'redux-thunk'//异步
import {composeWithDevTools} from 'redux-devtools-extension'//composeWithDevTools用于chrome插件

import rootReducers from "./reducers"

//@deprecated createStore
// const store=createStore(
//      rootReducers,
//      composeWithDevTools(applyMiddleware(thunk))
// )//内部会第一次调用reduer函数得到初始state


const store=createStore(
    rootReducers,
    composeWithDevTools(applyMiddleware(thunk))
)//内部会第一次调用reduer函数得到初始state

console.log(store.getState())
export default store
