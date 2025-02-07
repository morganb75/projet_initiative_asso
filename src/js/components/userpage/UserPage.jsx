import "./userpage.scss"
import React, {useEffect, useState} from 'react';
import SideBar from "../layout/SideBar.jsx";
import FeedUser from "../feed/FeedUser.jsx";
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";
import mapTabLikedId from "../../utils/mapTabLikedId.js";

const UserPage = () => {
    const {dataUser} = useUserContext()
    const {dataFeed, setDataFeed} = useDataFeedContext()
    const [likes, setLikes] = useState([])

    let URL_USERFEED
    if (dataUser?.roles.includes('PORTEUR')) {
        URL_USERFEED = '/api/user/parrains'
    } else {
        URL_USERFEED = '/api/user/porteurs'
    }

    const HTTP_DATA = {
        method: 'GET',
        headers: {
            'content-type': 'application/json',
            Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
        }
    }

    // LIFTING STATE UP => controle du userpage par Mediacard pour like/unlike et likedUserId

    const URL_LIKE_LIST_USER_CONNECTE = `api/user/${dataUser.id}/like`
    const HTTP_DATA_LIKE = {
        method: 'POST',
        headers: {
            'content-type': 'application/json',
            Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
        }
    }
    const HTTP_DATA_UNLIKE = {
        method: 'DELETE',
        headers: {
            'content-type': 'application/json',
            Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
        }
    }

    const handleLike = (userId) => {
        const URL_LIKE_UNLIKE = `api/user/${dataUser.id}/like/${userId}`
        fetch(URL_LIKE_UNLIKE, HTTP_DATA_LIKE)
            .then(async (response) => {
                if (!response.ok) {
                    const errorMessage = await response.text()
                    throw new Error(errorMessage)
                }
                setLikes((prevLikes) => [...prevLikes, userId])
                // alert(`Like envoyé à ${userId}`)
            })
            .catch(e => alert(e.message))
    }

    const handleUnLike = (userId) => {
        const URL_LIKE_UNLIKE = `api/user/${dataUser.id}/like/${userId}`
        fetch(URL_LIKE_UNLIKE, HTTP_DATA_UNLIKE)
            .then(async (response) => {
                if (!response.ok) {
                    const errorMessage = await response.text()
                    throw new Error(errorMessage);
                }
                setLikes((prevLikes) => prevLikes.filter(id => id !== userId))
                // alert(`Vous ne likez plus ${userId}`)
            })
            .catch(e => alert(e.message))
    }

    const handleTest = () => {
        console.log("Contexte dataUser", dataUser)
        console.log("Liste des Likes user connecté", likes)
    }

    //------------------------------------------------------------------------

    useEffect(() => {
        fetchEndPoint(URL_USERFEED, HTTP_DATA)
            .then(data => {
                setDataFeed(data)
            })
            .catch((e) => {
                alert(e + 'Echec de fetch users feed')
            })
    }, []);

    useEffect(() => {
        fetchEndPoint(URL_LIKE_LIST_USER_CONNECTE, HTTP_DATA)
            .then(data => {
                setLikes(mapTabLikedId(data))
            })
    }, [dataFeed])

    return (
        <>{dataFeed ?
            (<div className="main" id="main-userpage">
                <SideBar/>
                <h2 className="userpage-text2">Profils qui peuvent vous correspondre:</h2>
                <FeedUser likes={likes} handleLike={handleLike} handleUnlike={handleUnLike} handleTest={handleTest}/>
            </div>)
            :
            (<p>CHARGEMENT EN COURS .....</p>)}
        </>
    );
};

export default UserPage;