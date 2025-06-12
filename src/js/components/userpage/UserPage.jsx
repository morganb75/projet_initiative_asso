import "./userpage.scss"
import React, {useEffect, useState} from 'react';
import SideBar from "../layout/SideBar.jsx";
import FeedUser from "../feed/FeedUser.jsx";
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";
import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import FeedReunion from "../feed/FeedReunion.jsx";
import CreateReunionForm from "../feed/CreateReunionForm.jsx";

const UserPage = () => {
    const navigate = useNavigate()
    const {dataUser, setDataUser} = useUserContext()
    const {dataFeed, setDataFeed} = useDataFeedContext()
    const [currentUser, setCurrentUser] = useState(null);

    let URL_FEED
    const HTTP_FEED_DATA = {
        method: 'GET',
        headers: {
            'content-type': 'application/json',
            Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
        }
    }

    useEffect(() => {
        const fetchFeed = async () => {
            if (dataUser?.parrainId == null) {
                if (dataUser?.roles.includes('PORTEUR')) {
                    URL_FEED = '/api/user/parrains'
                } else if (dataUser?.roles.includes('PARRAIN')) {
                    URL_FEED = `/api/user/feed/parrain/${dataUser.id}`
                }
            } else if (dataUser?.parrainId) {
                URL_FEED = `/api/user/feed/porteur/${dataUser.parrainId}`
            } else if (currentUser.id) {
                URL_FEED = `/api/user/feed/porteur/${currentUser.id}`
            }
            const data = await fetchEndPoint(URL_FEED, HTTP_FEED_DATA)
            console.log({URL_FEED, data})
            setDataFeed(data)
        }
        fetchFeed()

    }, [dataUser]);


    const handleMessagerie = () => {
        navigate("/user/messagerie")
        // window.open('/user/messagerie','_blank')
    }

    const handleParrainAffect = () => {
        if (window.confirm(`Vous êtes sur le point de choisir ${currentUser.prenom} ${currentUser.nom} en tant que Parrain, êtes vous sûr?`)) {
            const URL_PATCH_PARRAIN = `/api/user/${dataUser?.id}/${currentUser.id}`
            const HTTP_PATCH_PARRAIN = {
                method: 'PATCH',
                headers: {
                    'content-type': 'application/json',
                    Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
                },
                body: JSON.stringify(null)
            }
            fetch(URL_PATCH_PARRAIN, HTTP_PATCH_PARRAIN)
                .then(async response => {
                    if (!response.ok) {
                        const errorMessage = await response.text()
                        throw new Error(errorMessage)
                    }
                    return response.json()
                })
                .then(() => {
                    alert('Parrain affecté avec succès!')
                    setDataUser({...dataUser, parrainId: currentUser.id})
                    navigate('/user/feed')


                })
                .catch(e => alert('Erreur: ' + e.message))
        }
    }

    useEffect(() => {
        if (!dataUser?.parrainId) {
            navigate('/user/feed')
        }
    }, []);

    return (
        <>
            <div className="main">
                {dataFeed ?
                    (
                        <>
                            {(dataUser?.parrainId || dataUser?.roles.includes('PARRAIN')) &&
                                <SideBar
                                    handleMessagerie={handleMessagerie}
                                />}

                            <Routes>
                                <Route path="" element=
                                    {
                                    <h1 className="content-accueil">{`Bienvenue dans votre espace ${dataUser?.prenom}`}</h1>
                                }
                                />
                                <Route path="modify" element=
                                    {
                                        // <FirstLoginPage/>
                                        <h1> NON DEVELOPPE A CE JOUR.......</h1>
                                    }
                                />
                                <Route path="feed" element=
                                    {
                                        <div className="feed-user-content">
                                            {(dataUser?.roles.includes('PORTEUR') && !dataUser?.parrainId) &&
                                                <div className="feed-user-text-content">
                                                    <h2>{`Bienvenue dans votre espace ${dataUser?.prenom}`} < /h2>
                                                    <h2>veuillez choisir le parrain qui vous accompagnera dans votre projet</h2>
                                                </div>
                                            }


                                            <FeedUser
                                                handleParrainAffect={handleParrainAffect}
                                                setCurrentUser={setCurrentUser}
                                                dataFeed={dataFeed}
                                            />
                                        </div>
                                    }
                                />
                                <Route path="reunions" element=
                                    {
                                        <FeedReunion/>
                                    }
                                />
                                <Route path="reunion/create" element=
                                    {
                                        <CreateReunionForm/>
                                    }
                                />

                            </Routes>
                        </>
                    )
                    :
                    (<p>CHARGEMENT EN COURS .....</p>)}
            </div>
        </>
    )
}

export default UserPage;