import './feed.scss'
import React, {useEffect} from 'react';
import MediaCard from "./MediaCard.jsx";
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const FeedUser = ({handleParrainAffect, isParrain, currentUser, setCurrentUser}) => {
    const {dataFeed, setDataFeed} = useDataFeedContext()
    const {dataUser} = useUserContext()

    useEffect(() => {
        const fetchData = async () => {
            const HTTP_DATA = {
                method: 'GET',
                headers: {
                    'content-type': 'application/json',
                    Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
                }
            }
            let URL_FEED = null
            if (dataUser?.parrainId == null && currentUser === null) {
                if (dataUser?.roles.includes('PORTEUR')) {
                    URL_FEED = '/api/user/parrains'
                } else {
                    URL_FEED = '/api/user/porteurs'
                }
            }
            if (dataUser.parrainId != null) {
                URL_FEED = `/api/user/feed/porteur/${dataUser.parrainId}`
            }
            if (currentUser != null) {
                URL_FEED = `/api/user/feed/porteur/${currentUser.id}`
            }
            const newData = await fetchEndPoint(URL_FEED, HTTP_DATA)
            setDataFeed(newData)
        }
        fetchData()
    }, [isParrain]);

    return (
        <div className="feed">
            {!dataUser.parrainId &&
                <h2>{`Bienvenue dans votre espace ${dataUser.prenom}, veuillez choisir le parrain qui vous accompagnera dans votre projet dans la liste`}</h2>
            }
            {dataUser.parrainId &&
                <h2>{`Bienvenue dans votre espace ${dataUser.prenom}`}</h2>
            }
            {dataFeed.map((user) => (
                <MediaCard
                    key={user.id}
                    user={user}
                    handleParrainAffect={handleParrainAffect}
                    setCurrentUser={setCurrentUser}
                />
            ))}
        </div>
    );
};

export default FeedUser;