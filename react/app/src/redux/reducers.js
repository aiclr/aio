/**
 * 包含n个reducer函数模块
 * 根据旧state和action返回新state
 */
import {combineReducers} from 'redux'

import {INCREMENT,DECREMENT,ADD_COMMENT,DEL_COMMENT,INIT_COMMENT} from './action-types'

// 1.定义默认数据
const initialState = {
    count: 1
    ,comments:[]
}

function counter(state=initialState.count,action) {
    switch (action.type) {
        case INCREMENT:
            return state+action.data
        case DECREMENT:
            return state-action.data
        default:
            return state
    }
}

function comment(state=initialState.comments,action) {
    switch (action.type) {
        case ADD_COMMENT:
            state.unshift(action.data)
            return [...state];
            // return state;
        case DEL_COMMENT:
            state.splice(action.data,1)
            return [...state];
        case INIT_COMMENT:
            return action.data;
        default:
            return state
    }
}


export default combineReducers({
    counter,comment
})
