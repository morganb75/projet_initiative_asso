import "./userpage.scss"
import React, {useState} from 'react';
import SideBar from "../layout/SideBar.jsx";
import FeedUser from "../feed/FeedUser.jsx";
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";

const UserPage = () => {
    const {dataUser} = useUserContext()
    const {dataFeed} = useDataFeedContext()
    const [currentUser, setCurrentUser] = useState(null);
    const [isParrain, setIsParrain] = useState(false)

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
            .then(()=> {
                alert('Parrain affecté avec succès!')
                setIsParrain(true)
            })
            .catch(e => alert('Erreur: '+ e.message))
    }

    return (
        <>{dataFeed ?
            (<div className="main" id="main-userpage">
                <SideBar/>
                <FeedUser
                    handleParrainAffect={handleParrainAffect}
                    currentUser={currentUser}
                    setCurrentUser={setCurrentUser}
                    isParrain={isParrain}
                />
            </div>)
            :
            (<p>CHARGEMENT EN COURS .....</p>)}
        </>
    );
};

export default UserPage;