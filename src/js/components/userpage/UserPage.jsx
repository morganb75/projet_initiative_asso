import "./userpage.scss"
import React, {useEffect, useState} from 'react';
import SideBar from "../layout/SideBar.jsx";
import FeedUser from "../feed/FeedUser.jsx";
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";
import {Route, Routes, useNavigate} from "react-router-dom";
import FeedReunion from "../feed/FeedReunion.jsx";
import CreateReunionForm from "../feed/CreateReunionForm.jsx";
import FirstLoginPage from "./FirstLoginPage.jsx";

const UserPage = () => {
    const navigate = useNavigate()
    const {dataUser} = useUserContext()
    const {dataFeed, setDataFeed} = useDataFeedContext()
    const [currentUser, setCurrentUser] = useState(null);
    const [isParrain, setIsParrain] = useState(false)

    let URL_FEED
    const URL_REUNION_FEED = `/api/reunions/all/${dataUser.id}`
    const HTTP_FEED_DATA = {
        method: 'GET',
        headers: {
            'content-type': 'application/json',
            Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
        }
    }

    useEffect(() => {
        const fetchFeed = async () => {
            if (dataUser?.parrainId == null && !isParrain) {
                if (dataUser.roles.includes('PORTEUR')) {
                    URL_FEED = '/api/user/parrains'
                } else if (dataUser.roles.includes('PARRAIN')) {
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

    }, [isParrain]);


    const handleRetourHome = () => {
    }

    const handleMessagerie = () => {
        navigate("/user/messagerie")
        // window.open('/user/messagerie','_blank')
    }

    const handleParrainAffect = () => {
        const URL_PATCH_PARRAIN = `/api/user/${dataUser.id}/${currentUser.id}`
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
                setIsParrain(true)

            })
            .catch(e => alert('Erreur: ' + e.message))
    }

    return (
        <>
            {/*{!dataUser.parrainId &&*/}
            {/*    <h2>{`Bienvenue dans votre espace ${dataUser.prenom}, veuillez choisir le parrain qui vous accompagnera dans votre projet dans la liste`}</h2>*/}
            {/*}*/}
            {/*{dataUser.parrainId &&*/}
            {/*    <h2>{`Bienvenue dans votre espace ${dataUser.prenom}`}</h2>*/}
            {/*}*/}
            {dataFeed ?
                (<div className="main" id="main-userpage">
                    <SideBar
                        handleRetourHome={handleRetourHome}
                        handleMessagerie={handleMessagerie}
                    />

                    <Routes>
                        <Route path="modify" element=
                            {
                                // <FirstLoginPage/>
                                <h1> DEV en cours.......</h1>
                            }
                        />
                        <Route path="feed" element=
                            {
                                <FeedUser
                                    handleParrainAffect={handleParrainAffect}
                                    setCurrentUser={setCurrentUser}
                                    dataFeed={dataFeed}
                                />
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
                </div>)
                :
                (<p>CHARGEMENT EN COURS .....</p>)}
        </>
    );
};

export default UserPage;