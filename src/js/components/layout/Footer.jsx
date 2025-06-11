import React from 'react';
import  "./footer.scss"
import logo from '../../assets/logo2.png'
import logofb from '../../assets/facebook.svg'
import logolnkdn from '../../assets/linkedin.png'
import logotiktok from '../../assets/tiktok.svg'
import logoinsta from '../../assets/instagram.svg'

const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer-content">
                <div className="footer-left">
                    <img src={logo} alt="logo-ttm" className="footer-logo"/>
                    <div className="contact-info">
                        <p><strong>Votre plateforme:</strong> 0679875609</p>
                        <p><strong>Initiative Deux-Sèvres:</strong> accompagnement@initiativedeuxsevres.fr</p>
                    </div>
                </div>
                <div className="footer-right">
                    <img src={logofb} alt="logo-facebook"/>
                    <img src={logolnkdn} alt="logo-linkedin"/>
                    <img src={logotiktok} alt="logo-tiktok"/>
                    <img src={logoinsta} alt="logo-instagram"/>
                </div>
            </div>
            <div className="footer-bottom">
                <p>© 2024 Tous droits réservés.</p>
            </div>
        </footer>
    );
};

export default Footer;