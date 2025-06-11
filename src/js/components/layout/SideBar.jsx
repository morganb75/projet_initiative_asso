import './sidebar.scss'
import React, { useState } from 'react';
import { useUserContext } from "../../contexts/UserContext.jsx";
import { useNavigate } from "react-router-dom";

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
    const { dataUser } = useUserContext()

    if (!dataUser || !dataUser.roles) {
        return null; // ou un loader si tu préfères
    }

    const handleDevEnCours = () => alert('DEVELOPPEMENT EN COURS .... PATIENCE.')

    return (
        <div className="sidebar">
            {dataUser.roles.includes('ADMIN') && (
                <>
                    <button onClick={handleRetourHome}>Accueil</button>
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
                    <button onClick={() => navigate('/user')}>Accueil</button>
                    <button onClick={() => navigate("/user/modify")}>Modifier mon profil</button>
                    <button onClick={() => navigate("/user/feed")}>Mes Contacts</button>
                    <button onClick={() => setShowMeetingsMenu(!showMeetingsMenu)}>
                        <span>Mes Rendez-Vous</span>
                        <span className={`chevron ${showMeetingsMenu ? 'rotated' : ''}`}>&#9662;</span>
                    </button>
                    {showMeetingsMenu && (
                        <div className="submenu">
                            {dataUser.roles.includes("PORTEUR") &&
                            <div className="submenu-item" onClick={() => navigate("reunion/create")}>Créer une réunion</div>
                            }
                            <div className="submenu-item" onClick={() => navigate("reunions")}>Voir les réunions</div>
                        </div>
                    )}
                    <button onClick={handleMessagerie}>Messagerie</button>
                </>
            )}
        </div>
    );
}

export default SideBar;
