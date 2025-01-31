import './sidebar.scss'
import React from 'react';
import {useUserContext} from "../../contexts/UserContext.jsx";
import {useNavigate} from "react-router-dom";

const SideBar = () => {

    const {dataUser} = useUserContext()
    const navigate = useNavigate()

    const handleDevEnCours = () => alert('DEVELOPPEMENT EN COURS .... PATIENCE.')

    const handlePreRegister = () => {
        navigate("/admin/preinscrire")
    }


    return (
        <div className="sidebar">
            {dataUser.roles.includes('ADMIN') &&
                <>
                    <button onClick={handleDevEnCours}>Liste des profils</button>
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
                    <button onClick={handleDevEnCours}>Messagerie</button>
                    <button onClick={handleDevEnCours}>Rechercher un profil</button>
                    <button onClick={handleDevEnCours}>Modifier mon profil</button>
                </>
            }

        </div>
    );
};

export default SideBar;