import React, {useEffect, useState} from 'react';
import "./firstloginpage.scss"
import PorteurForm from "./PorteurForm.jsx";
import ParrainForm from "./ParrainForm.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const FirstLoginPage = () => {

    const {dataUser} = useUserContext()
    const porteurInitialState = {
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
    const [formState, setFormState] = useState(() =>
        dataUser?.roles?.includes('PORTEUR') ? porteurInitialState : parrainInitialState)

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

    const handleSubmit = async (e) => {
        e.preventDefault()
        console.log(formState)
    }

    return (
        <>
            <div className="main">
                <h2>Bienvenue, veuillez vérifier et compléter votre profil</h2>
                <form className="form-first-login" onSubmit={handleSubmit}>
                    {
                        dataUser.roles.includes('PORTEUR') ?
                            <PorteurForm formState={formState} setFormState={setFormState}/>
                            :
                            <ParrainForm formState={formState} setFormState={setFormState}/>
                    }
                    <button className="button-login" type="submit">Valider</button>
                </form>
            </div>
        </>
    );
};

export default FirstLoginPage;