import {StrictMode} from 'react'
import "./init.scss"
import {createRoot} from 'react-dom/client'
import {Route, BrowserRouter as Router, Routes} from 'react-router-dom'

import React from 'react';
import Layout from "./components/layout/Layout.jsx";
import Login from "./components/login/Login.jsx";
import LandingPage from "./components/landingpage/LandingPage.jsx";
import UserContextProvider from "./contexts/UserContext.jsx";
import AdminPage from "./components/adminpage/AdminPage.jsx";
import UserPage from "./components/userpage/UserPage.jsx";
import PreInscription from "./components/adminpage/PreInscription.jsx";
import FirstLoginPage from "./components/userpage/FirstLoginPage.jsx";
import DataFeedContextProvider from "./contexts/DataFeedContext.jsx";
import Messagerie from "./components/messagerie/Messagerie.jsx";
import RechercheProfils from "./components/rechercheProfils/RechercheProfils.jsx";
import AllUsersForAdminContextProvider from "./contexts/AllUsersForAdminContext.jsx";

const Main = () => {
    return (

        <Router>
            <Routes>
                <Route element={<Layout/>}>
                    <Route path="" element={<LandingPage/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/firstlogin" element={<FirstLoginPage/>}/>
                    <Route path="/user/*" element={<UserPage/>}/>
                    <Route path="/user/messagerie" element={<Messagerie/>}/>
                    <Route path="/admin" element={<AdminPage/>}/>
                    <Route path="/admin/preinscrire" element={<PreInscription/>}/>
                    <Route path="/admin/recherche" element={<RechercheProfils/>}/>
                </Route>
            </Routes>
        </Router>)
};

export default Main;

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <AllUsersForAdminContextProvider>
            <UserContextProvider>
                <DataFeedContextProvider>
                    <Main/>
                </DataFeedContextProvider>
            </UserContextProvider>
        </AllUsersForAdminContextProvider>
    </StrictMode>
)
