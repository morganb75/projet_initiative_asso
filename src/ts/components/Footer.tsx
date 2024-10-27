import React from 'react';
import assoLogo from '../assets/favicion.ico'

const Footer = () => {
    return (
        <div>
            <h2> footer</h2>
            <a href="https://www.initiative79.com/" target="_blank">
                <img src={assoLogo} className="logo" alt="Vite logo"/>
            </a>
        </div>
    );
};

export default Footer;