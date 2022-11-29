import React, {Component} from 'react'
import PropTypes from 'prop-types'
import Axios from 'axios'
import Pubsub from 'pubsub-js'

import './main.css'

export default class Main extends Component{
    state={
        initView: true,
        loading:false,
        users:null,
        errorMsg:null
    }

    componentDidMount() {
        //订阅消息
        Pubsub.subscribe('searchName',(msg,searchName)=> {
            //指定新searchName需要请求
            this.setState(
                {
                    initView:false,
                    loading:true
                }
            )
            const url=`https://api.github.com/search/users?q=${searchName}`
            const axios = Axios.create();
            axios.get(url)
                .then(response=>{
                    const result=response.data
                    const users=result.items.map(item=>({name:item.login,url:item.html_url,avatarUrl:item.avatar_url}))
                    // console.log(result)
                    this.setState({loading:false,users})
                })
                .catch(error=>{
                    this.setState({loading:false,errorMsg:error.message})
                })
        })
    }

    //当组件接收到新的属性时回调 
    //@deprecated 16.3, use static getDerivedStateFromProps instead; will stop working in React 17
    // componentWillReceiveProps(newProps) {
    // }
    static getDerivedStateFromProps(newProps) {
        //A valid state object (or null) must be returned. You have returned undefined
        return null;
    }
    render() {
        const {initView,loading,users,errorMsg}=this.state
        const {searchName}=this.props
        // console.log(searchName)
        if(initView){
            return <h2>请输入关键字搜索</h2>
        }else if(loading){
            return <h2>loading...</h2>
        }else if(errorMsg){
            return <h2>{errorMsg}</h2>
        }else{
            return(
                <div className='row'>
                    {
                        users.map((user,index)=>(
                            <div className='card'>
                                <a href={user.url} target="_blank">
                                    <img src={user.avatarUrl} style={{width:100}}/>
                                </a>
                                <p className='card-text'>{user.name}</p>
                            </div>
                        ))
                    }
                </div>
            )
        }
    }
}