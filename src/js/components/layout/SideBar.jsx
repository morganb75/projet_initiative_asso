import React from 'react';
import '../../style/sidebar.scss'
import {useUserContext} from "../../contexts/UserContext.jsx";
import {useNavigate} from "react-router-dom";

const SideBar = () => {

    const {dataUser} = useUserContext()
    const navigate = useNavigate()

    const handleListUsers = () => {
        alert('liste users')
    }
    const handleSearchUser = () => {
        alert('recherche user')
    }
    const handleModify = () => {
        alert('modifier user')
    }
    const handlePreRegister = () => {
        navigate("/admin/preinscrire")
    }
    const handleDelete = () => {
        alert('delete user')
    }

    return (
        <div className="sidebar">
            {dataUser.roles.includes('ADMIN') &&
                <>
                    <button onClick={handleListUsers}>Liste des profils</button>
                    <button onClick={handleSearchUser}>Rechercher un profil</button>
                    <button onClick={handleModify}>Modifier un profil</button>
                    <button onClick={handlePreRegister}>Pre-inscription</button>
                    <button onClick={handleDelete}>Radiation</button>
                </>
            }

            {(dataUser.roles.includes('PARRAIN')) || (dataUser.roles.includes('PORTEUR')) &&
                <>
                    <button onClick={handleListUsers}>Liste des profils</button>
                    <button onClick={handleSearchUser}>Rechercher un profil</button>
                    <button onClick={handleModify}>Modifier mon profil</button>
                </>
            }

        </div>
    );
};

export default SideBar;