import './feed.scss'
import React from 'react';
import MediaCard from "./MediaCard.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";

const FeedUser = ({dataFeed, handleParrainAffect, setCurrentUser}) => {
    const {dataUser} = useUserContext()

    return (
        <div className="feed">

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