import React, {useEffect} from 'react';
import SideBar from "../layout/SideBar.jsx";
import Feed from "../feed/Feed.jsx";
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const UserPage = () => {
    const {dataUser} = useUserContext()
    const {setDataFeed} = useDataFeedContext()
    let URL_USERFEED
    if(dataUser?.roles.includes('PORTEUR')){
        URL_USERFEED = '/api/user/parrains'
    } else {
        URL_USERFEED = '/api/user/porteurs'
    }
    const HTTP_DATA = {
        method:'GET',
        headers:{
            'content-type': 'application/json',
            Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
        }
    }
    useEffect(() => {
    const fetchFeedData = async () =>{
        try{
            const data = await fetchEndPoint(URL_USERFEED,HTTP_DATA)
            setDataFeed(data)
        } catch (e) {
            alert(e + 'Echec de fetch users feed')
        }
    }
    fetchFeedData()
    }, []);
    return (
        <>
            <div className="main">
                <SideBar/>
                <Feed/>
            </div>
        </>
    );
};

export default UserPage;