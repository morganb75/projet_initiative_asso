import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import {Route, BrowserRouter as Router, Routes} from 'react-router-dom'

import React from 'react';
import Layout from "./components/layout/Layout.jsx";
import Login from "./components/login/Login.jsx";
import LandingPage from "./components/landingpage/LandingPage.jsx";
import UserContextProvider from "./contexts/UserContext.jsx";
import AdminPage from "./components/adminpage/AdminPage.jsx";
import UserPage from "./components/userpage/UserPage.jsx";
import PreInscription from "./components/PreInscription.jsx";

const Main = () => {
    return (
        <UserContextProvider>
            <Router>
                <Routes>
                    <Route element={<Layout/>}>
                        <Route path="/" element={<LandingPage/>}/>
                        <Route path="/login" element={<Login/>}/>
                        <Route path="/admin" element={<AdminPage/>}/>
                        <Route path="/admin/preinscrire" element={<PreInscription/>}/>
                        <Route path="/user" element={<UserPage/>}/>
                    </Route>
                </Routes>
            </Router>
        </UserContextProvider>
    );
};

export default Main;

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <Main/>
    </StrictMode>,
)
