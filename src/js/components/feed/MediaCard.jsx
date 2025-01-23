import React from 'react';
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";

const MediaCard = ({nom, prenom, entreprise, domaineActivite}) => {
    return (
        <div className="mediacard">
            <div className="mediacard-header">
                <img src='#' alt='img-profil'/>
            </div>
            <div className="mediacard-body">
                <h2>{nom}</h2>
                <h2>{prenom}</h2>
                <h3>{entreprise}</h3>
                <p>{domaineActivite}</p>
            </div>
        </div>
    );
};

export default MediaCard;