import './feed.scss'
import React from 'react';
import MediaCard from "./MediaCard.jsx";
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";
import {useUserContext} from "../../contexts/UserContext.jsx";

const FeedUser = ({handleTest} ) => {
    const {dataFeed} = useDataFeedContext()
    const {dataUser} = useUserContext()

    return (
        <div className="feed">
            {dataFeed.map((user) => (
                <MediaCard
                    key={user.id}
                    nom={user.nom}
                    prenom={user.prenom}
                    entreprise={user.entreprise}
                    domaineActivite={user.domaineActivite}
                    descriptifActivite={user.descriptifActivite}
                    disponibilites={user.disponibilites}
                    userId={user.id}
                    handleTest={handleTest}
                />
            ))}
        </div>
    );
};

export default FeedUser;