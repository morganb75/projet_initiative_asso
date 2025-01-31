import "./login.scss"
import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import decodeToken from "../../utils/decodeToken.js";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const Login = () => {

    const {setDataUser} = useUserContext();
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate()
    const URL_CONNEXION = '/api/connexion'
    const HTTP_DATA = {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({email, password})
    }

    const handleSubmit = async (event) => {
        event.preventDefault()
        try {
            const data = await fetchEndPoint(URL_CONNEXION, HTTP_DATA)
            const token = data.bearer
            setDataUser(decodeToken(token))
            sessionStorage.setItem('authToken', token)
            const roles = (decodeToken(token)).roles
            const firstLogin = (decodeToken(token).firstLogin)
            if (roles.includes("ADMIN")) {
                navigate("/admin")
            } else if (firstLogin) {
                navigate("/firstlogin")
            } else {
                navigate("/user")
            }
        } catch (error) {
            alert(error + " Echec de connexion! Email ou mot de Passe incorrect")
        }
    }

    return (
        <>
            <div className="main">
                <h2 className="login-h2">Veuillez vous identifier</h2>
                <form className="form-login" onSubmit={handleSubmit}>
                    <div className="form-login-field">
                        <label htmlFor="Email">Email utilisateur:</label>
                        <input
                            className="login-input"
                            type="text"
                            id="email"
                            autoComplete="username"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>
                    <div className="form-login-field">
                        <label htmlFor="password">Mot de passe:</label>
                        <input
                            className="login-input"
                            type="password"
                            id="password"
                            autoComplete="current-password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)
                            }
                        />
                    </div>
                    <div className="form-login-field">
                        <button className="login-button" type="submit">Login</button>
                    </div>
                </form>
            </div>
        </>

    );
};

export default Login;