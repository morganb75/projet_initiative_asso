import React, {useEffect, useState} from 'react';
import "./firstloginpage.scss"
import PorteurForm from "./PorteurForm.jsx";
import ParrainForm from "./ParrainForm.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";
import {useNavigate} from "react-router-dom";

const FirstLoginPage = () => {

    const {dataUser} = useUserContext()
    const navigate = useNavigate()
    const porteurInitialState = {
        type: 'PORTEUR',
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
        plateForme: null,
        password: null,
        dateDebutActivite: null,
        domaineActivite: null,
        descriptifActivite: null,
        lieuActivite: {
            numeroDeVoie: null,
            rue: null,
            complement: null,
            codePostal: null,
            ville: null
        },
        besoinsPotentiels: [],
        disponibilites: null
    }

    const parrainInitialState = {
        type: 'PARRAIN',
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
        plateForme: null,
        password: null,
        parcours: null,
        domaineActivite: null,
        zonesDeDeplacement: [],
        disponibilites: null
    }
    const [formState, setFormState] = useState(() =>
    dataUser?.roles?.includes('PORTEUR') ? porteurInitialState : parrainInitialState
)

    useEffect(() => {
        const fetchData = async () => {
            const URL_USERBYEMAIL = `/api/user?email=${dataUser.sub}`
            // console.log(dataUser)
            const HTTP_DATA = {
                method: 'GET',
                headers: {
                    'content-type': 'application/json',
                    Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
                }
            }
            try {
                const data = await fetchEndPoint(URL_USERBYEMAIL, HTTP_DATA)
                console.log('fetchdata', data)

                //pre remplissage du formulaire First Login
                setFormState(prevState => ({
                    ...prevState,
                    nom: data.nom,
                    prenom: data.prenom,
                    email: data.email,
                    entreprise: data.entreprise,
                    adresse: {
                        ...formState.adresse,
                        numeroDeVoie: data.adresse.numeroDeVoie,
                        rue: data.adresse.rue,
                        complement: data.adresse.complement,
                        codePostal: data.adresse.codePostal,
                        ville: data.adresse.ville
                    }
                }))
            } catch (e) {
                console.error('erreur lors du fetch des données user preenregistré', e)
            }
        }
        fetchData()
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault()
        console.log(formState)
        const URL_FIRSTLOGIN = `/api/user/${dataUser.id}`
        const HTTP_DATA = {
            method: 'PATCH',
            headers: {
                'content-type': 'application/json',
                Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
            },
            body: JSON.stringify(formState)
        }
        fetch(URL_FIRSTLOGIN, HTTP_DATA)
            .then(response =>{
                alert('Informations enregistrées avec succès')
                // navigate("/user")
            } )
            .catch(e => console.error("Fetch error", e))
    }

    return (
        <>
            <div className="main">
                <h2 className="first-login-h2">Bienvenue, veuillez vérifier et compléter votre profil</h2>
                <form className="form-first-login" onSubmit={handleSubmit}>
                    <div className="form">
                    {
                        dataUser.roles.includes('PORTEUR') ?
                            <PorteurForm formState={formState} setFormState={setFormState}/>
                            :
                            <ParrainForm formState={formState} setFormState={setFormState}/>
                    }
                    </div>
                    <button className="firstlogin-button" type="submit">Valider</button>
                </form>
            </div>
        </>
    )
}

export default FirstLoginPage;