import './sidebar.scss'
import React, {useEffect, useState} from 'react';
import {useUserContext} from "../../contexts/UserContext.jsx";
import {useNavigate} from "react-router-dom";
import Notification from "../notification/Notification.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const SideBar = ({
                     handleRetourHome,
                     handlePreRegister,
                     handleMessagerie,
                     handleListePorteurs,
                     handleListeParrains,
                     handleSearch,
                 }) => {
    const navigate = useNavigate()
    const [showMeetingsMenu, setShowMeetingsMenu] = useState(false)
    const {dataUser} = useUserContext()
    const handleDevEnCours = () => alert('DEVELOPPEMENT EN COURS .... PATIENCE.')

    return (
        <div className="sidebar">
            {dataUser.roles.includes('ADMIN') && (
                <>
                    <button onClick={handleRetourHome}>Home</button>
                    <button onClick={() => setShowMeetingsMenu(!showMeetingsMenu)}>
                        <span>Gestion utilisateurs</span>
                        <span className={`chevron ${showMeetingsMenu ? 'rotated' : ''}`}>&#9662;</span>
                    </button>
                    {showMeetingsMenu && (
                        <div className="submenu">
                            <div className="submenu-item" onClick={handlePreRegister}>Pre-inscription</div>
                            <div className="submenu-item" onClick={handleListePorteurs}>Liste des porteurs</div>
                            <div className="submenu-item" onClick={handleListeParrains}>Liste des parrains</div>
                            <div className="submenu-item" onClick={handleSearch}>Recherche un profil</div>
                        </div>
                    )}
                    <button onClick={handleMessagerie}>Messagerie</button>
                    <button onClick={handleDevEnCours}>KPI</button>
                </>
            )}

            {(dataUser.roles.includes('PARRAIN') || dataUser.roles.includes('PORTEUR')) && (
                <>
                    <button onClick={handleRetourHome}>Home</button>
                    <button onClick={() => navigate("modify")}>Modifier mon profil</button>
                    <button onClick={() => navigate("feed")}>Mes Contacts</button>
                    <button onClick={() => setShowMeetingsMenu(!showMeetingsMenu)}>
                        <span>Mes Rendez-Vous</span>
                        <span className={`chevron ${showMeetingsMenu ? 'rotated' : ''}`}>&#9662;</span>
                    </button>
                    {showMeetingsMenu && (
                        <div className="submenu">
                            <div className="submenu-item" onClick={() => navigate("reunion/create")}>Créer une réunion</div>
                            <div className="submenu-item" onClick={() => navigate("reunions")}>Voir les réunions</div>
                        </div>
                    )}
                    <button onClick={handleMessagerie}>Messagerie</button>
                </>
            )}
        </div>
    );
}

export default SideBar