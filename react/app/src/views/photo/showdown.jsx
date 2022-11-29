import React,{Component} from 'react'
import MyShowDown from "../../component/showdown/myshowdown";
import Ajax from "../../component/ajax/ajax";


export default class ShowDown extends Component{
    render() {
        return(
            <div>
                <MyShowDown/>
                <Ajax/>
            </div>
        )
    }
}