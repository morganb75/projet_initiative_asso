import React from 'react';
import "./adminpage.scss"
import SideBar from "../layout/SideBar.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import FeedAdmin from "../feed/FeedAdmin.jsx";
//TODO implementer les feed admin
const AdminPage = () => {
    const {dataUser} = useUserContext()

    return (
        <>
            <div className="main" id="main-admin">
                <SideBar/>
                <div className="admin-feed">
                    <h1>RESEAU INITIATIVE</h1>
                    <h1>PLATEFORME: DEUX-SEVRES</h1>
                    <h2>Page d'administration de l'application "Trouve ton match"</h2>
                    <FeedAdmin/>
                </div>
            </div>
        </>
    );
};

export default AdminPage;