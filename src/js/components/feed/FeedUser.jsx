import './feed.scss'
import React from 'react';
import MediaCard from "./MediaCard.jsx";

const FeedUser = ({dataFeed, handleParrainAffect, setCurrentUser}) => {

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