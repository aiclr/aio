import React from 'react'
import {connect} from "react-redux";

import ComponentList from '../component/component-list/component-list'
import {getComment} from '../redux/actions'

export default connect(
    state=>({comment:state.comment}),{getComment}//状态与reducers.jsx里function名对应
)(ComponentList)