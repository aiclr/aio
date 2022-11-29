import React, {Component} from 'react'
import PropTypes from "prop-types";

export default class ReduxDemo2 extends Component{

    static propTypes={
        count:PropTypes.number.isRequired,
        increment:PropTypes.func.isRequired,
        decrement:PropTypes.func.isRequired,
        incrementAsync:PropTypes.func.isRequired
    }

    increment=()=>{
        let num=this.select.value*1
        this.props.increment(num)
    }
    decrement=()=>{
        let num=this.select.value*1
        this.props.decrement(num)
    }
    oddIncrement=()=>{
        let num=this.select.value*1
        if(this.props.count%2===1){
            this.props.increment(num)
        }
    }
    asynIncrement=()=>{
        let num=this.select.value*1
        this.props.incrementAsync(num)
    }

    render() {
        const count=this.props.count
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