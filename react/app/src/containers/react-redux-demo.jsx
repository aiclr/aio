import React from 'react'
import {connect} from "react-redux";

import {decrement, increment,incrementAsync} from "../redux/actions";
import ReduxDemo2 from '../component/demo-redux/redux-demo2'

export default connect(
    state=>({count:state.counter}),
    {increment,decrement,incrementAsync}
)(ReduxDemo2)