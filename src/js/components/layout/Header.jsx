import React from 'react'
import "../../style/header.scss"
import logottm from "../../assets/logo1.png"
import logoids from "../../assets/logo.png"
import {useUserContext} from "../../contexts/UserContext.jsx";
import {useNavigate} from "react-router-dom";
import deconnect from '../../assets/icons8-se-déconnecter-32.png'
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const Header = () => {

    const {dataUser, setDataUser} = useUserContext()
    const navigate = useNavigate()

    const handleLogout = async () => {
        const urlLogout = "/api/logout"
        const httpData = {
            method: 'POST', // credentials: 'include'
        }
        try {
            const response = await fetch(urlLogout, httpData)
            if (response.ok) {
                sessionStorage.clear()
                localStorage.removeItem('authToken')
                setDataUser([])
                navigate('/')
            } else {
                console.error('Failed to logout')
            }
        } catch (error) {
            console.error('Error during logout: ', error)
        }
    }

    return (<header className="header">
            <div className="logo1">
                <img src={logoids} alt="logo"/>
            </div>
            <div className="logo2">
                <img src={logottm} alt="logo"/>
            </div>
            <div className="user">
                {(dataUser.length !== 0) && (<>
                        <span>{dataUser.prenom} </span>
                        <span>{dataUser.nom}</span>
                        <button onClick={handleLogout}>
                            <img src={deconnect}/>
                        </button>
                    </>)}
            </div>
        </header>)
}

export default Header;