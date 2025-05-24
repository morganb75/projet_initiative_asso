import React, {useState} from 'react';
import avatar from '../../assets/avatar_h.jpg'

const MediaCard = ({user, handleParrainAffect, setCurrentUser}) => {

    const [isOpen, setIsOpen] = useState(false)
    const handleOpen = () => {
        setIsOpen(true)
        setCurrentUser(user)
    }
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
                    <h2>{user.nom}</h2>
                    <h2>{user.prenom}</h2>
                    <h3>Société:</h3>
                    <h3>{user.entreprise}</h3>
                </div>
            </div>

            {isOpen && (
                <div className="modal-overlay">
                    <div className="modal">
                        <div className="modal-infos">
                            <h2>Fiche détaillée</h2>
                            <p><strong>Nom :</strong> {user.nom}</p>
                            <p><strong>Prénom :</strong> {user.prenom}</p>
                            {user.type === "PARRAIN" ?
                                <p><strong>Parcours :</strong> {user.parcours}</p>
                                :
                                <p><strong>Entreprise :</strong> {user.entreprise}</p>
                            }
                            {user.roles.includes("PORTEUR")  && <p><strong>Domaine :</strong> {user.domaineActivite}</p>}
                            {user.roles.includes("PORTEUR") && <p><strong>Activité :</strong> {user.descriptifActivite}</p>}
                            <p><strong>Disponibilités :</strong> {user.disponibilites}</p>
                        </div>
                        <div className="modal-actions">
                            <button onClick={handleClose}>Fermer</button>
                            {user.roles.includes("PARRAIN") && <button onClick={handleParrainAffect}>Choisir</button>}
                        </div>
                    </div>
                </div>
            )}
        </>
    )
}

export default MediaCard;