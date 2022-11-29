import React, {Component} from 'react'

export default class RedusDemo extends Component{
    state={
        count:0
    }

    increment=()=>{
        const {count}=this.state
        let num=this.select.value*1
        this.setState({count:count+num})
    }
    decrement=()=>{
        const {count}=this.state
        let num=this.select.value*1
        this.setState({count:count-num})
    }
    oddIncrement=()=>{
        const {count}=this.state
        let num=this.select.value*1
        if(count%2===1){
            this.setState({count:count+num})
        }
    }
    asynIncrement=()=>{
        const {count}=this.state
        let num=this.select.value*1
        setTimeout(()=>{
            this.setState({count:count+num})
        },1000)

    }

    render() {
        const {count}=this.state
        return(
            <div>
               <p>click {count} times</p>&nbsp;
                <div>
                    <select ref={select=>this.select=select}>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                    </select>&nbsp;
                    <button onClick={this.increment}>+</button>&nbsp;
                    <button onClick={this.decrement}>-</button>&nbsp;
                    <button onClick={this.oddIncrement}>increment if odd</button>&nbsp;
                    <button onClick={this.asynIncrement}>increment async</button>
                </div>
            </div>
        )
    }
}