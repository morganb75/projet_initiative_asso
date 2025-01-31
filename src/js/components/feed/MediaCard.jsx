import React, {useState} from 'react';
import avatarh from '../../assets/avatar_h.jpg'
const MediaCard = ({nom, prenom, entreprise, domaineActivite}) => {
    const [isOpen, setIsOpen] = useState(false)

    const handleOpen = () => setIsOpen(true)
    const handleClose = () => setIsOpen(false)

    return (
        <>
                <div className="mediacard" onClick={handleOpen}>
                    <div className="mediacard-header">
                        <img src={avatarh} alt='img-profil'/>
                    </div>
                    <div className="mediacard-body">
                        <h2>{nom}</h2>
                        <h2>{prenom}</h2>
                        <h3>Société:</h3>
                        <h3>{entreprise}</h3>
                    </div>
                </div>

            {isOpen && (
                <div className="modal-overlay">
                    <div className="modal">
                        <h2>Détails de l'utilisateur</h2>
                        <p><strong>Nom :</strong> {nom}</p>
                        <p><strong>Prénom :</strong> {prenom}</p>
                        <p><strong>Entreprise :</strong> {entreprise}</p>
                        <p><strong>Domaine :</strong> {domaineActivite}</p>
                        <button onClick={handleClose}>Fermer</button>
                    </div>
                </div>
            )}
        </>
    )
}

export default MediaCard;