import React from 'react';
import SideBar from "../layout/SideBar.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import Feed from "../feed/Feed.jsx";

const AdminPage = () => {
    const {dataUser} = useUserContext()

    return (
        <>
            <div className="main">
                <SideBar/>
                {/*<h2>Bienvenue sur la page d'administration</h2>*/}
                <Feed/>
            </div>
        </>
    );
};

export default AdminPage;