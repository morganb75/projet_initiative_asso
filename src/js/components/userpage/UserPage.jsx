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

    const handleTest = () => {
        console.log("Contexte dataUser", dataUser)
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

    return (
        <>{dataFeed ?
            (<div className="main" id="main-userpage">
                <SideBar/>
                <h2 className="userpage-text2">Profils qui peuvent vous correspondre:</h2>
                <FeedUser  handleTest={handleTest}/>
            </div>)
            :
            (<p>CHARGEMENT EN COURS .....</p>)}
        </>
    );
};

export default UserPage;