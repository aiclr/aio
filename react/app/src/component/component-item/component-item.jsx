import React,{Component} from 'react'
import PropTypes from "prop-types";
import Pubsub from 'pubsub-js'

import './componentItem.css'

export default class ComponentItem extends Component{
    //static 给组件类指定属性
    static propTypes={
        component: PropTypes.object.isRequired,
        index: PropTypes.number.isRequired,
        delComment:PropTypes.func.isRequired
    }

    delClick=(e)=>{
        e.preventDefault()
        const {component,index}=this.props
        
        if(window.confirm(`确定删除${component.userName}的评论`)){
            //发布订阅模式
            // Pubsub.publish('deleteComponent',index)
            //redux版本
            this.props.delComment(component)
        }
    }

    render() {
        const {component}=this.props
        return(
            <li className='list-group-item'>
                <div className='handle'>
                    <a onClick={this.delClick}>删除</a>
                </div>
                <p className="user"><span>{component.userName}</span><span>说：</span></p>
                <p className="centence">{component.content}</p>
            </li>
        )
    }
}