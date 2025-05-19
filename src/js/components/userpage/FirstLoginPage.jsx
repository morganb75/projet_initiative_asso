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
        plateForme: null,
        password1: null,
        password2: null,
        parcours: null,
        domaineActivite: null,
        zonesDeDeplacement: [],
        disponibilites: null
    }
    const [formState, setFormState] = useState(() =>
        dataUser?.roles?.includes('PORTEUR') ? porteurInitialState : parrainInitialState
    )
    //TODO a mettre a jour quand le DTO parrain sera géré
    const URL_GET_USER_BY_EMAiL = dataUser?.roles?.includes('PORTEUR') ? `/api/user/dto?email=${dataUser.sub}` : `/api/user?email=${dataUser.sub}`

    useEffect(() => {
        const fetchData = async () => {
            const HTTP_DATA = {
                method: 'GET',
                headers: {
                    'content-type': 'application/json',
                    Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
                }
            }
            try {
                const data = await fetchEndPoint(URL_GET_USER_BY_EMAiL, HTTP_DATA)
                console.log('fetchdata', data)

                //pre remplissage du formulaire First Login
                setFormState(prevState => ({
                    ...prevState,
                    nom: data.nom,
                    prenom: data.prenom,
                    email: data.email,
                    entreprise: data.entreprise
                }))
            } catch (e) {
                console.error('erreur lors du fetch des données user preenregistré', e)
            }
        }
        fetchData()
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault()
        console.log({formState})

        if (formState.password1 !== formState.password2 || formState.password1 === null) {
            alert('Mots de passe différents ou invalides, veuillez à nouveau renseigner')
            setFormState({...formState, password1: '', password2: ''})
            return
        } else {
            setFormState({...formState, password: formState.password2, password1: null, password2: null})
        }

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
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                alert('Informations enregistrées avec succès')
                navigate("/user")
            })
            .catch(e => {
                console.error("Fetch error", e)
                alert('Une erreur est survenue lors de l’enregistrement.')
            })
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