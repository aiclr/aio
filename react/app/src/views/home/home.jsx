import React,{Component} from 'react'

import ComponentCarousel from "../../component/component-carousel/component-carousel";
import MyBattery from "../../component/mybattery/mybattery";

export default class Home extends Component{
    render() {
        return(
            <div>
                <ComponentCarousel/>
                <MyBattery/>
            </div>
        )
    }
}