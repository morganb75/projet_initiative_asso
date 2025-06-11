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
                        <div className="admin-content">
                            <h1 className="color-text">RESEAU INITIATIVE</h1>
                            <h1 className="color-text">PLATEFORME: DEUX-SEVRES</h1>
                            <h2 className="color-text">Page d'administration de l'application "Trouve ton match"</h2>
                        </div>
                    </>
                )
            }
        </>
    )
}

export default FeedAdmin;