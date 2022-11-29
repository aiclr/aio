import React,{Component} from 'react'



export default class News extends Component{
    state={
        newsList:['news001','news002','news003']
    }

    render() {
        return(
            <ul>
                {this.state.newsList.map(
                    (item,index)=>(<li key={index}>{item}</li>))}
            </ul>
        )
    }
}