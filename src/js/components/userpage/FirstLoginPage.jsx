import React, {useEffect, useState} from 'react';
import "./firstloginpage.scss"
import PorteurForm from "./PorteurForm.jsx";
import ParrainForm from "./ParrainForm.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import decodeToken from "../../utils/decodeToken.js";
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const FirstLoginPage = () => {

    const {dataUser} = useUserContext()

    const porteurInitialSate = {
        nom: '',
        prenom: '',
        email: '',
        entreprise: '',
        adresse: {
            numeroDeVoie: '',
            rue: '',
            complement: '',
            codePostal: '',
            ville: ''
        },
        plateForme: '',
        password: '',
        dateDebutActivite: '',
        domaineActivite: '',
        descriptifActivite: '',
        lieuActivite: '',
        besoinsPotentiels: [],
        disponibilites: ''
    }

    const parrainInitialState = {
        nom: '',
        prenom: '',
        email: '',
        entreprise: '',
        adresse: {
            numeroDeVoie: '',
            rue: '',
            complement: '',
            codePostal: '',
            ville: ''
        },
        plateForme: '',
        password: '',
        parcours: '',
        domaineActivite: '',
        zonesDeDeplacement: '',
        disponibilites: ''
    }

    const [porteurFormState, setPorteurFormState] = useState(porteurInitialSate)
    const [parrainFormState, setParrainFormState] = useState(parrainInitialState)

    useEffect(() => {
        const fetchData = async () => {
            const URL_USERBYEMAIL = `/api/user?email=${dataUser.sub}`

            const HTTP_DATA = {
                method: 'GET',
                headers: {
                    'content-type': 'application/json',
                    Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
                }
            }
            const data = await fetchEndPoint(URL_USERBYEMAIL, HTTP_DATA)
            console.log(data)
        }
        fetchData()
    }, [dataUser.sub]);

    if (dataUser.roles.includes('PORTEUR')) {

    } else {
    }


    const handleSubmit = async (e) => {
        e.preventDefault()
        if (dataUser.roles.includes('PORTEUR')) {
            console.log(porteurFormState)
        } else {
            console.log(parrainFormState)
        }
    }

    return (
        <>
            <div className="main">
                <h2>Bienvenue, veuillez vérifier et compléter votre profil</h2>
                <form className="form-first-login" onSubmit={handleSubmit}>
                    {
                        dataUser.roles.includes('PORTEUR') ?
                            <PorteurForm formState={porteurFormState} setFormState={setPorteurFormState}/>
                            :
                            <ParrainForm formState={parrainFormState} setFormState={setParrainFormState}/>
                    }
                    <button className="button-login" type="submit">Valider</button>
                </form>
            </div>
        </>
    );
};

export default FirstLoginPage;