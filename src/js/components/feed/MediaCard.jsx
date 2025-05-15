import React, {useState} from 'react';
import avatar from '../../assets/avatar_h.jpg'
import likeIcone from '../../assets/like-coeur.jpg'

const MediaCard = ({
                       nom,
                       prenom,
                       entreprise,
                       domaineActivite,
                       descriptifActivite,
                       disponibilites,
                       userId,
                       handleTest
                   }) => {
    const [isOpen, setIsOpen] = useState(false)

    const handleOpen = () => setIsOpen(true)
    const handleClose = () => {
        setIsOpen(false)
    }

    return (
        <>
            <div className="mediacard" onClick={handleOpen}>
                <div className="mediacard-header">
                    <img className="mediacard-header-img-avatar" src={avatar} alt='img-profil'/>
                </div>
                <div className="mediacard-body">
                    <h2>{nom}</h2>
                    <h2>{prenom}</h2>
                    <h3>Société:</h3>
                    <h3>{entreprise}</h3>
                    {/*<h3>{userId}</h3>*/}
                </div>
            </div>

            {isOpen && (
                <div className="modal-overlay">
                    <div className="modal">
                        <div className="modal-infos">
                            <h2>Détails de l'utilisateur</h2>
                            <p><strong>Nom :</strong> {nom}</p>
                            <p><strong>Prénom :</strong> {prenom}</p>
                            <p><strong>Entreprise :</strong> {entreprise}</p>
                            <p><strong>Domaine :</strong> {domaineActivite}</p>
                            <p><strong>Activité :</strong> {descriptifActivite}</p>
                            <p><strong>Disponibilités :</strong> {disponibilites}</p>
                        </div>
                        <div className="modal-actions">
                            <button onClick={handleClose}>Fermer</button>
                            {/*<button onClick={() => {*/}
                            {/*    handleTest(userId)*/}
                            {/*    console.log("userId: ", userId)*/}
                            {/*}*/}
                            {/*}>Test*/}
                            {/*</button>*/}
                        </div>
                    </div>
                </div>
            )}
        </>
    )
}

export default MediaCard;