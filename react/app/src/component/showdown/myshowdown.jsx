import React, {Component} from 'react'
import ReactMarkdown from "react-markdown"

import MdList from "./md-list"
import './myShowDown.css'

export default class MyShowDown extends Component{

    state={
        value:'Hello, World!\n===\n---\n# This is an h1\n## This is an h2\n### This is an h3\n#### This is an h4\n##### This is an h5\n###### This is an h6'
    }

    handleChange=(event)=>{
        this.setState({value: event.target.value})
    }

    render() {
        const {value}=this.state
        return(
            <div className="row">
                <MdList/>
                <div className="col-5">
                    <textarea defaultValue={value} onChange={this.handleChange} id='markdown' className='col-xs-10 col-xs-offset-1'/>
                </div>
                <div className="col-5">
                    <ReactMarkdown source={value} escapeHtml={false} className="htmlArea col-xs-10 col-xs-offset-1 full-height"/>
                </div>
            </div>
        )
    }
}