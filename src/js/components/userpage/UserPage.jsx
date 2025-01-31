import "./userpage.scss"
import React, {useEffect} from 'react';
import SideBar from "../layout/SideBar.jsx";
import Feed from "../feed/Feed.jsx";
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const UserPage = () => {
    const {dataUser} = useUserContext()
    const {dataFeed, setDataFeed} = useDataFeedContext()
    let URL_USERFEED
    if (dataUser?.roles.includes('PORTEUR')) {
        URL_USERFEED = '/api/user/parrains'
    } else {
        URL_USERFEED = '/api/user/porteurs'
    }
    console.log(URL_USERFEED)
    const HTTP_DATA = {
        method: 'GET',
        headers: {
            'content-type': 'application/json',
            Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
        }
    }

    useEffect(() => {
        fetchEndPoint(URL_USERFEED, HTTP_DATA)
            .then((data) => {
                setDataFeed(data)
                console.log(data)
            })
            .catch((e) => {
                alert(e + 'Echec de fetch users feed')
            })
    }, []);

    useEffect(() => {
        console.log("dataFeed a été mis à jour :", dataFeed);
    }, [dataFeed]);

    return (
        <>{dataFeed ?
            (<div className="main" id="main-userpage">
            <SideBar/>
            <h2 className="userpage-text2">Profils qui peuvent vous correspondre:</h2>
            <Feed/>
            </div>)
            :
            (<p>CHARGEMENT EN COURS .....</p>)}
        </>
    );
};

export default UserPage;