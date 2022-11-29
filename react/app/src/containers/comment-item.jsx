import React from 'react'
import {connect} from "react-redux";

import {delComment} from "../redux/actions";
import ComponentItem from '../component/component-item/component-item'

export default connect(
    state=>({}),
    {delComment}
)(ComponentItem)