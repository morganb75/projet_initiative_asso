import React, {useEffect, useState} from 'react';
import './rechercheProfils.scss'
import MediaCard from "../feed/MediaCard.jsx";
import {useAllUsersForAdminContext} from "../../contexts/AllUsersForAdminContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";
import SideBar from "../layout/SideBar.jsx";

const RechercheProfils = () => {
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
    const [showModal, setShowModal] = useState(true)
    const [searchResults, setSearchResults] = useState([])

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
        console.log({allUsers, searchForm})
        const filteredResults = allUsers.filter(user =>
            (searchForm.nom === '' || user.nom.toLowerCase().includes(searchForm.nom.toLowerCase())) &&
            (searchForm.prenom === '' || user.prenom.toLowerCase().includes(searchForm.prenom.toLowerCase())) &&
            (searchForm.role === '' || user.roles.includes(searchForm.role))
    )
        console.log({filteredResults})
        setSearchResults(filteredResults)
        setShowModal(false)
    }
    return (
        <>
            <SideBar/>
            {(!showModal && searchResults.length > 0) && (
                <div className="search-results">
                    <h2>Résultats de recherche</h2>
                    {searchResults.map((user) => (
                        <MediaCard
                            user={user}/>
                    ))}
                </div>
            )}

            {showModal && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        <form onSubmit={handleSearch}>
                            <input type="text" value={searchForm.nom} onChange={handleChangeName} placeholder="Nom"/>
                            <input type="text" value={searchForm.prenom} onChange={handleChangeFirstName} placeholder="Prénom"/>
                            <select value={searchForm.role} onChange={handleChangeRole}>
                                <option value="" disabled>Selectionnez un role</option>
                                <option value="PORTEUR">PORTEUR</option>
                                <option value="PARRAIN">PARRAIN</option>
                            </select>
                            <button type="submit">Chercher</button>
                            <button type="cancel">Annuler</button>
                        </form>
                    </div>
                </div>
            )}
        </>
    )
        ;
};

export default RechercheProfils;