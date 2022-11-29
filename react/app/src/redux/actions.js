/*
* 生成action creator action的工厂函数
*
* 同步action返回对象
* 异步action返回函数
*
* */
import {INCREMENT,DECREMENT,ADD_COMMENT,DEL_COMMENT,INIT_COMMENT} from "./action-types";

//同步action
/*export const increment=(num)=>{
    return {type:INCREMENT,data:num}
}*/
export const increment=(num)=>({type:INCREMENT,data:num})
export const decrement=(num)=>({type:DECREMENT,data:num})
export const addComment=(comment)=>({type:ADD_COMMENT,data:comment})
export const delComment=(comment)=>({type:DEL_COMMENT,data:comment})
const initComment=(comments)=>({type:INIT_COMMENT,data:comments})





//异步action
export const incrementAsync=(num)=>
    (dispatch => {
        setTimeout(()=>{
            //1s后分发一个action
            dispatch(increment(num))
        },1000)
    }
)
//异步另一种写法
/*
export const incrementAsync=(num)=>{
    return dispatch=>{
        setTimeout(()=>{
            //1s后分发一个action
            dispatch(increment(num))
        },1000)
    }
}
* */

//异步
export const getComment=()=>{
    return dispatch=>{
        const comments=[{userName: "a",content:"b"},{userName: "caddy",content:"qnmb"}]
        //模拟ajax一部请求
        setTimeout(
            ()=>{
                dispatch(initComment(comments))
            },1000
        )
    }

}
