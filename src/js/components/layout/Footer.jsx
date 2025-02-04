import React from 'react';
import "./footer.scss"
import logo from '../../assets/logo2.png'
import logofb from '../../assets/facebook.svg'
import logolnkdn from '../../assets/linkedin.png'
import logotiktok from '../../assets/tiktok.svg'
import logoinsta from '../../assets/instagram.svg'

const Footer = () => {
    return (
        <footer className="footer">
            <div className='footer-top'>
                <div className='footer-top-left'>
                    <div className='logo-footer'>
                        <img src={logo} alt="logo-ttm"/>
                    </div>
                    <div className='text-footer'>
                        <div className='text-tel-footer'>
                            <span>Votre plateforme:</span>
                            <span>0679875609</span>
                        </div>
                        <div className='text-mail-footer'>
                            <span>Initiative Deux-Sèvres:</span>
                            <span>accompagnement@initiativedeuxsevres.fr</span>
                        </div>
                    </div>
                </div>
                <div className='footer-top-right'>
                    <div className='reseaux-sociaux-footer'>
                        <img src={logofb} alt="logo-facebook"/>
                        <img src={logolnkdn} alt="logo-linkedin"/>
                        <img src={logotiktok} alt="logo-tiktok"/>
                        <img src={logoinsta} alt="logo-instagram"/>
                    </div>
                </div>
            </div>
            <div className='footer-bottom'>
                <p>&copy; 2024 Tous droits réservés.</p>
            </div>
        </footer>
    );
};

export default Footer;