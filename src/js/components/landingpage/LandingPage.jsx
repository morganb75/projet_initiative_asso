import React from 'react';
import "./landingpage.scss"
import {Link} from "react-router-dom";

const LandingPage = () => {

    return (
        <>
            <div className="main">
                <div className="content">
                    <h2 className="color-text">Trouve ton match c'est quoi?</h2>
                    <h3>Trouve ton match est L'APPLI qui vous permettra de trouver LA personne pour vous accompagner et vous
                        soutenir, une épaule sur
                        laquelle vous reposer</h3>
                    <h3>Le but est de créer un climat de confiance avec cette personne afin que vous puissiez vous tourner vers elle
                        lorsque vous avez
                        des soucis ou des besoins.</h3>
                    <h3>Ce parrain/marraine peut être un chef d’entreprise, ou un cadre dirigeant ayant de l’expérience et un réseau
                        important pour faire
                        appel à des partenaires experts dans vos besoins.</h3>
                    <Link to="/login" className="button">CONNEXION</Link>
                </div>
            </div>
        </>
    );
};
export default LandingPage;