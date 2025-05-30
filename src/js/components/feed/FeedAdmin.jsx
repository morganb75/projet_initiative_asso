import './feed.scss'
import React from 'react';
import MediaCard from "./MediaCard.jsx";

const FeedAdmin = ({adminFeed}) => {

    if (adminFeed === undefined) {
        return <div className="loader">Chargement en cours...</div>;
    }
    return (
        <>
            {adminFeed !== null ?
                (<div className="feed">
                    {adminFeed.map((user) => (
                        <MediaCard
                            key={user.id}
                            user={user}
                        />
                    ))}
                </div>)
                :
                (<>
                        <h1>RESEAU INITIATIVE</h1>
                        <h1>PLATEFORME: DEUX-SEVRES</h1>
                        <h2>Page d'administration de l'application "Trouve ton match"</h2>
                    </>
                )
            }
        </>
    )
}

export default FeedAdmin;