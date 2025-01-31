import './feed.scss'
import React from 'react';
import MediaCard from "./MediaCard.jsx";
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";

const Feed = () => {
    const {dataFeed} = useDataFeedContext()
    console.log(dataFeed)
    return (
        <div className="feed">
            {dataFeed.map((user) => (
                <MediaCard
                    key={user.id}
                    nom={user.nom}
                    prenom={user.prenom}
                    entreprise={user.entreprise}
                    domaineActivite={user.domaineActivite}
                />
            ))}
        </div>
    );
};

export default Feed;