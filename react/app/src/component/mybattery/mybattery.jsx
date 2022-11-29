import React,{Component} from 'react'

import './myBattery.css'

export default class MyBattery extends Component{

    render() {
        return(
            <div className="container-battery">
                <div className="battery1"></div>

                <div className="header"></div>
                <div className="battery2">
                </div>
                <div className="battery-copy">
                    <div className="g-wave"></div>
                    <div className="g-wave"></div>
                    <div className="g-wave"></div>
                </div>
            </div>

        )
    }
}