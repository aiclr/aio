import React,{Component} from 'react'
import  PropTypes from 'prop-types'

import './componentAdd.css'

class ComponentAdd extends Component{

    static propTypes={
        addComment:PropTypes.func.isRequired
    }

    handleSubmit=()=>{
        let userName=this.input.value
        let content=this.textarea.value
        const comment={userName: userName,content: content}
        this.props.addComment(comment)
        // Pubsub.publish('component',component)
        // this.setState({userName:'',content:''})

        //搜集数据并封装成components对象
        // const component=this.state
        //更新状态
        // this.props.addComponent(component)
        //清楚数据
        // this.setState({
        //     userName:'',
        //     content:''
        // })

    }
    handleChangeName=(event)=>{
        // const userName=event.target.value
        // this.props.handleChangeName(userName)
    }
    handleChangeCont=(event)=>{
        // const content=event.target.value
        // this.props.handleChangeContent(content)
    }

    render() {
        return(
            <div className='col-md-4'>
                <form className='form-horizontal'>
                    <div className='form-group'>
                        <label>用户名</label>
                        <input type='text' className='form-control' placeholder='用户名'
                         ref={(input) => this.input = input}/>
                    </div>
                    <div className='form-group'>
                        <label>评论内容</label>
                        <textarea type='text' className='form-control' rows='6' placeholder='评论内容'
                        ref={(textarea) => this.textarea = textarea}></textarea>
                    </div>
                    <div className='form-group'>
                        <div className='col-sm-offset-2 col-sm-10'>
                            <button type='button' className='btn btn-default pull-right'
                                    onClick={this.handleSubmit}>提交</button>
                        </div>
                    </div>
                </form>
            </div>
        )
    }
}
export default ComponentAdd