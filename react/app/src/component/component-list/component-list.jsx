import React,{Component} from 'react'
import PropTypes from 'prop-types'

import ComponentItem from '../../containers/comment-item'
import './componentList.css'

export default class ComponentList extends Component{
    //static 给组件类指定属性
   static propTypes={
        comment: PropTypes.array.isRequired
        ,getComment:PropTypes.func.isRequired

    }

    componentDidMount(){
        this.props.getComment()
    }

    render() {
        const {comment}=this.props
        
        const display=comment.length===0?'block':'none'
        return(
            <div className='col-md-8'>
                <h3 className='reply'>评论回复：</h3>
                <h2 style={{display}}>暂无评论，点击左侧添加评论</h2>
                <ul className='list-group'>
                    {
                        comment.map(
                        (component,index) =>(<ComponentItem component={component} key={index} index={index}/>)
                        )
                    }
                </ul>
            </div>
        )
    }
}
