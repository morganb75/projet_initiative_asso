import React, {useEffect, useState} from 'react';
import './feedreunion.scss'
import fetchEndPoint from "../../utils/fetchEndPoint.js";
import {useUserContext} from "../../contexts/UserContext.jsx";

const FeedReunion = () => {

    const {dataUser} = useUserContext()
    const [reunions, setReunions] = useState([])
    const [modalCR, setModalCR] = useState(null)
    const URL_REUNION_FEED = `/api/reunions/all/${dataUser.id}`
    const HTTP_FEED_DATA = {
        method: 'GET',
        headers: {
            'content-type': 'application/json',
            Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
        }
    }

    useEffect(() => {
        const fetchReunionFeed = async () => {
            const data = await fetchEndPoint(URL_REUNION_FEED, HTTP_FEED_DATA)
            const sortedData = data.sort((a, b) => new Date(a.date) - new Date(b.date));
            setReunions(sortedData)
        }
        fetchReunionFeed()
    }, []);

    return (
        <>
            {reunions.length === 0 ? (
                <h2>Aucune réunion à afficher</h2>
            ) : (
                <div className="feed">
                    <h2>Mes réunions</h2>
                    <ul>
                        {reunions.map(reunion => (
                            <li key={reunion.id} className="reunion-card">
                                <div><strong>Date :</strong> {new Date(reunion.date).toLocaleString()}</div>
                                <div><strong>Objet :</strong> {reunion.motif}</div>
                                {reunion.parrain && (
                                    <div><strong>Parrain :</strong> {reunion.parrain.nom || 'Inconnu'}</div>
                                )}
                                {reunion.porteur && (
                                    <div><strong>Porteur :</strong> {reunion.porteur.nom || 'Inconnu'}</div>
                                )}
                                { reunion.compteRendu !==null ?
                                    (
                                        <button
                                        className="btn-cr"
                                        onClick={() => setModalCR(reunion)}
                                    >
                                        Voir le compte rendu
                                    </button>
                                    ) : (
                                        <button
                                            className="btn-cr"
                                            onClick={() => alert('DEV EN COURS!')}
                                        >
                                            Enregistrer un compte rendu
                                        </button>
                                    )
                                }
                            </li>
                        ))}
                    </ul>

                    {modalCR && (
                        <div className="modal-overlay" onClick={() => setModalCR(null)}>
                            <div className="modal" onClick={e => e.stopPropagation()}>
                                <h3>Compte rendu</h3>
                                <p>{modalCR.compteRendu || 'Non disponible'}</p>
                                <button onClick={() => setModalCR(null)}>Fermer</button>
                            </div>
                        </div>
                    )}

                </div>
            )}
        </>
    );
};

export default FeedReunion;