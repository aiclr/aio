import React, {Component} from 'react'
import Axios from 'axios';

export default class Ajax extends Component{
    //https://api.github.com/search/repositories?q=r&sort=starts
    state={
        repoName:'',
        repoUrl:''
    }

    componentDidMount() {
        const axios = Axios.create();
        const url=`https://api.github.com/search/repositories?q=react&sort=starts`
        // const url=`https://api.github.com/search2/repositories?q=react&sort=starts`
        //axios 请求
        axios.get(url)
            .then(response=>{
                const result=response.data
                // console.log(result)
                //得到数据
                const {name,html_url}=result.items[0]
                this.setState({repoName:name,repoUrl:html_url})
            })
            .catch((error)=>{
                alert(error.message)
            })
        //fetch 请求
        /*fetch(url)
            .then(response=>{
               return response.json()
            }).then(data=>{
            //得到数据
            const {name,html_url}=data.items[0]
            this.setState({repoName:name,repoUrl:html_url})
        })*/
        // axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';

        //axios
    }

    render() {
        const {repoName,repoUrl}=this.state

        if(!repoName){
            return(
                <div>
                    <h2 style={{display:'block'}}>Loading . . .</h2>
                </div>
            )
        }else {
            return(
                <div>
                    <h2 style={{display:'block'}}>Most star repo is <a href={repoUrl}>{repoName}</a>
                    </h2>
                </div>
            )
        }
    }
}