import './feed.scss'
import React from 'react';
import MediaCard from "./MediaCard.jsx";
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";

const FeedAdmin = () => {
    const {dataFeed} = useDataFeedContext()

    return (
        <div className="feed">
            {dataFeed?.map((user) => (
                <MediaCard
                    key={user.id}
                    nom={user.nom}
                    prenom={user.prenom}
                    entreprise={user.entreprise}
                    domaineActivite={user.domaineActivite}
                    descriptifActivite={user.descriptifActivite}
                    disponibilites={user.disponibilites}
                    userId={user.id}
                />
            ))}
        </div>
    );
};

export default FeedAdmin;