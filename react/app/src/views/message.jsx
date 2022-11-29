import React,{Component} from 'react'
import {Route} from 'react-router-dom'

import MessageDetail from "./message-detail";
import MyNavLink from "../component/component-navigation/my-navlink";


export default class Message extends Component{
    state={
        messages:[]
    }

    componentDidMount() {
        setTimeout(()=>{
            const messages=[
                {id:1,title:'message001'},
                {id:2,title:'message002'},
                {id:3,title:'message003'}]
            this.setState({messages})
        },1000)
    }

    render() {
        return(
            <div>
                <ul>
                    {this.state.messages.map(
                        (item,index)=>(
                            <li key={index}>
                                <MyNavLink to={`/home/message/messagedetail/${item.id}`}>{item.title}</MyNavLink>
                            </li>
                            )
                        )}
                </ul>
                <Route path='/home/message/messagedetail/:id' component={MessageDetail}/>
            </div>
        )
    }
}