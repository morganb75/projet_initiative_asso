import './sidebar.scss'
import React, {useEffect, useState} from 'react';
import {useUserContext} from "../../contexts/UserContext.jsx";
import {useNavigate} from "react-router-dom";
import Notification from "../Notification/Notification.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const SideBar = () => {

    const {dataUser} = useUserContext()
    const navigate = useNavigate()
    const handleDevEnCours = () => alert('DEVELOPPEMENT EN COURS .... PATIENCE.')
    const handlePreRegister = () => {
        navigate("/admin/preinscrire")
    }
    const handleListeProfils = () => {
        navigate("api/")
    }

    return (
        <div className="sidebar">
            {dataUser.roles.includes('ADMIN') &&
                <>
                    <button onClick={handleDevEnCours}>Liste des porteurs</button>
                    <button onClick={handleDevEnCours}>Liste des parrains</button>
                    <button onClick={handleDevEnCours}>Rechercher un profil</button>
                    <button onClick={handleDevEnCours}>Modifier un profil</button>
                    <button onClick={handlePreRegister}>Pre-inscription</button>
                    <button onClick={handleDevEnCours}>Radiation</button>
                    <button onClick={handleDevEnCours}>Matchs</button>
                    <button onClick={handleDevEnCours}>KPI</button>
                </>
            }

            {((dataUser.roles.includes('PARRAIN')) || (dataUser.roles.includes('PORTEUR'))) &&
                <>
                    <button onClick={handleDevEnCours}>Mes profils favoris</button>
                    <button onClick={handleDevEnCours}>Mes Likers</button>
                    <button onClick={handleDevEnCours}>Messagerie</button>
                    <button onClick={handleDevEnCours}>Rechercher un profil</button>
                    <button onClick={handleDevEnCours}>Modifier mon profil</button>
                </>
            }

        </div>
    );
};

export default SideBar;