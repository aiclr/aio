import React from 'react'
import {connect} from "react-redux";

import {addComment} from "../redux/actions";
import ComponentAdd from '../component/component-add/component-add'

export default connect(
    state=>({}),
    {addComment}
)(ComponentAdd)