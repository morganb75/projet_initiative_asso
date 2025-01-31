import React, {useState} from 'react';
import "./preinscription.scss"
import RolesMultiSelector from "../selector/RolesMultiSelector.jsx";
import UserForm from "../userpage/UserForm.jsx";
import {useNavigate} from "react-router-dom";
//TODO Resserez ce components sur les affaires de roles etc etc......
const PreInscription = () => {
    const initialState = {
        type: null,
        roles: [],
        // civilite: "",
        nom: null,
        prenom: null,
        email: null,
        entreprise: null,
        adresse: {
            numeroDeVoie: null,
            rue: null,
            complement: null,
            codePostal: null,
            ville: null
        },
        plateForme: 'DEUXSEVRES',
        password: 'password123',
    }

    const [formState, setFormState] = useState(initialState)
    const navigate = useNavigate()

    function handleSubmit(event) {
        event.preventDefault()
        const varType = formState.roles[0]
        setFormState(prevState => ({...formState, type: varType}))
        console.log(formState)
        const URL_PREINSCRIPTION = '/api/admin/user'

        const HTTP_DATA = {
            method: 'POST',
            headers: {
                'content-type': 'application/json',
                Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
            },
            body: JSON.stringify(formState)
        }

        fetch(URL_PREINSCRIPTION, HTTP_DATA)
            .then(response => {
                alert('Utilisateur pré-enregistré avec succès')
                navigate("/admin")
            })
            .catch(error => console.error('Error', error))
    }

    function handleCancel(event){
        event.preventDefault()
        navigate('/admin')
    }

    return (
        <div className="main">
            <h1 className="preinscription-h1">Pré-inscrire un utilisateur</h1>
            <form className="form-preinscription" onSubmit={handleSubmit}>
                <div className="form">
                    <UserForm formState={formState} setFormState={setFormState}/>
                </div>
                <button className="preinscription-button" onClick={handleSubmit} type="submit">Enregistrer</button>
                <button className="preinscription-button" onClick={handleCancel} type="submit">Annuler</button>
            </form>
        </div>
    )
}

export default PreInscription;