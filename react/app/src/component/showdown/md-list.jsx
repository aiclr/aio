import React, {Component} from 'react'

export default class MdList extends Component {

    render() {
        return (
            <div className="col-2">
                <select>
                    <option value ="volvo">Volvo</option>
                    <option value ="saab">Saab</option>
                    <option value="opel">Opel</option>
                    <option value="audi">Audi</option>
                </select>
            </div>
        )
    }
}