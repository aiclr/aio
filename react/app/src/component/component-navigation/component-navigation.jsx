import React, { Component } from 'react'
import { Routes, Route, Navigate } from 'react-router-dom'
import MyNavLink from "./my-navlink"

import './componentNavigation.css'
import Home from "../../views/home/home";
import ShowDown from "../../views/photo/showdown";

export default class ComponentNavigation extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="pos-f-t">
                <nav className="navbar navbar-light">
                    <a className="navbar-brand">Hi</a>
                    <ul className="nav nav-tabs">
                        <li className="nav-item">
                            <MyNavLink to='/home' className='list-group-item'>Home</MyNavLink>
                        </li>
                        <li className="nav-item">
                            <MyNavLink to='/showdown' className='list-group-item'>ShowDown</MyNavLink>
                        </li>
                    </ul>
                    <form className="form-inline">
                        <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" />
                        <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </nav>
                <div>
                    <Routes>
                        <Route path='/home' element={<Home/>} />
                        <Route path='/showdown' element={<ShowDown/>} />
                        <Route path='*' element={<Navigate to="/home" />} />
                    </Routes>
                </div>
            </div>
        )
    }
}