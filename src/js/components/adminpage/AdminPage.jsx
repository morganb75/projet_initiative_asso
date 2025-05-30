import React, {useState} from 'react';
import "./adminpage.scss"
import SideBar from "../layout/SideBar.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import FeedAdmin from "../feed/FeedAdmin.jsx";
import {useNavigate} from "react-router-dom";
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const AdminPage = () => {
    const navigate = useNavigate()
    const {dataUser} = useUserContext()
    const [adminFeed, setAdminFeed] = useState(null)
    const HTTP_GET_DATA = {
        method: 'GET',
        headers: {
            'content-type': 'application/json',
            Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
        }
    }
    const URL_ALL_PORTEURS = '/api/admin/users/porteurs'
    const URL_ALL_PARRAINS = '/api/admin/users/parrains'

    const handleRetourHome = () => setAdminFeed(null)

    const handlePreRegister = () => {
        navigate("/admin/preinscrire")
    }
    const handleMessagerie = () => {
        navigate("/user/messagerie")
        // window.open('/user/messagerie','_blank')
    }

    const handleListePorteurs = async () => {
        const data = await fetchEndPoint(URL_ALL_PORTEURS, HTTP_GET_DATA)
        setAdminFeed(data)
    }

    const handleListeParrains = async () => {
        const data = await fetchEndPoint(URL_ALL_PARRAINS, HTTP_GET_DATA)
        setAdminFeed(data)
    }

    const handleSearch = () => navigate("/admin/recherche")

    return (
        <>
            <div className="main" id="main-admin">
                <SideBar
                    handleRetourHome={handleRetourHome}
                    handleMessagerie={handleMessagerie}
                    handlePreRegister={handlePreRegister}
                    handleListePorteurs={handleListePorteurs}
                    handleListeParrains={handleListeParrains}
                    handleSearch={handleSearch}
                />
                <div className="admin-feed">
                    <FeedAdmin
                        adminFeed={adminFeed}/>
                </div>
            </div>
        </>
    );
};

export default AdminPage;