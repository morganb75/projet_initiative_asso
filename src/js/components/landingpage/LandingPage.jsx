import React from 'react';
import "./landingpage.scss"
import {Link} from "react-router-dom";
import imgrel from "../../assets/img-relation.jpg"
import {useUserContext} from "../../contexts/UserContext.jsx";

const LandingPage = () => {
    const {dataUser} = useUserContext()

    return (
        <>
        <div className="main" id="main-landingpage">
            <img className="landing-img" src={imgrel} alt="accueil"></img>
            <h2 className="text1">Trouve ton match est L'APPLI qui vous permettra de trouver LA personne pour vous accompagner et vous soutenir, une épaule sur
                laquelle vous reposer</h2>
            <h2 className="text2">Le but est de créer un climat de confiance avec cette personne afin que vous puissiez vous tourner vers elle lorsque vous avez
                des soucis ou des besoins.</h2>
            <h2 className="text3">Ce parrain/marraine peut être un chef d’entreprise, ou un cadre dirigeant ayant de l’expérience et un réseau important pour faire
                appel à des partenaires experts dans vos besoins.</h2>
            <Link to="/login" className="button">CONNEXION</Link>
        </div>
        </>
    );
};
export default LandingPage;