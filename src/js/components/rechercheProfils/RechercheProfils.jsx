import React, {useEffect, useState} from 'react';
import './rechercheProfils.scss'
import {useAllUsersForAdminContext} from "../../contexts/AllUsersForAdminContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const RechercheProfils = ({showSearchModal, setShowSearchModal, setAdminfeed}) => {

    const {allUsers, setAllUsers} = useAllUsersForAdminContext()

    const URL = '/api/admin/users'

    const HTTP_DATA = {
        method: 'GET',
        headers: {
            'content-type': 'application/json',
            Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
        }
    }

    const initialForm = {
        nom: '',
        prenom: '',
        role: ''
    }

    const [searchForm, setSearchForm] = useState(initialForm)

    useEffect(() => {
        const fetchUsers = async () => {
            const data = await fetchEndPoint(URL, HTTP_DATA);
            setAllUsers(data);
        }
        fetchUsers();
    }, []);

    const handleChangeName = (e) => {
        setSearchForm({...searchForm, nom: e.target.value})
    }
    const handleChangeFirstName = (e) => {
        setSearchForm({...searchForm, prenom: e.target.value})
    }
    const handleChangeRole = (e) => {
        setSearchForm({...searchForm, role: e.target.value})
    }

    const handleSearch = () => {

        const filteredResults = allUsers.filter(user =>
            (searchForm.nom === '' || user.nom.toLowerCase().includes(searchForm.nom.toLowerCase())) &&
            (searchForm.prenom === '' || user.prenom.toLowerCase().includes(searchForm.prenom.toLowerCase())) &&
            (searchForm.role === '' || user.roles.includes(searchForm.role))
        )
        setAdminfeed(filteredResults)
        setShowSearchModal(false)
    }
    return (
        <>
            {showSearchModal && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        <form onSubmit={handleSearch}>
                            <div>
                            <input type="text" value={searchForm.nom} onChange={handleChangeName} placeholder="Nom"/>
                            <input type="text" value={searchForm.prenom} onChange={handleChangeFirstName} placeholder="Prénom"/>
                            <select value={searchForm.role} onChange={handleChangeRole}>
                                <option value="" disabled>Selectionnez un role</option>
                                <option value="PORTEUR">PORTEUR</option>
                                <option value="PARRAIN">PARRAIN</option>
                            </select>
                            </div>
                            <div>
                            <button type="submit">Chercher</button>
                            <button type="cancel" onClick={() => setShowSearchModal(false)}>Annuler</button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </>
    )
}

export default RechercheProfils;