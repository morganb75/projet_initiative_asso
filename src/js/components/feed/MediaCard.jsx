import React, {useState} from 'react';
import avatar from '../../assets/avatar_h.jpg'
import {useUserContext} from "../../contexts/UserContext.jsx";

const MediaCard = ({user, setCurrentUser, handleParrainAffect}) => {
    const {dataUser} = useUserContext()
    const [isOpen, setIsOpen] = useState(false)

    const handleOpen = () => {
        setIsOpen(true)
        setCurrentUser(user)
    }

    const handleClose = () => {
        setIsOpen(false)
        setCurrentUser(null)
    }

    const handleAdevelopper=()=>alert('A DEVELOPPER.....')

    return (
        <>
            <div className="mediacard" onClick={handleOpen}>
                <div className="mediacard-header">
                    <img className="mediacard-header-img-avatar" src={avatar} alt='img-profil'/>
                </div>
                <div className="mediacard-body">

                    <div className="info-block">
                        <h2>{(user.nom).toUpperCase()}</h2>
                        <h2>{user.prenom}</h2>
                    </div>
                    <div className="info-block">
                        <h3>Société:</h3>
                        <h3>{user.entreprise}</h3>
                    </div>
                    {user.roles.includes('ADMIN') &&
                        <h2>ADMIN</h2>
                    }
                </div>
            </div>

            {isOpen && (
                <div className="modal-overlay">
                    <div className="modal">
                        <div className="modal-infos">
                            <h2>Fiche détaillée</h2>
                            <p><strong>Nom :</strong> {user.nom}</p>
                            <p><strong>Prénom :</strong> {user.prenom}</p>
                            {user.type === 'PARRAIN' ?
                                <p><strong>Parcours :</strong> {user.parcours}</p>
                                :
                                <p><strong>Entreprise :</strong> {user.entreprise}</p>
                            }
                            {user.roles.includes('PORTEUR') && <p><strong>Domaine :</strong> {user.domaineActivite}</p>}
                            {user.roles.includes('PORTEUR') && <p><strong>Activité :</strong> {user.descriptifActivite}</p>}
                            <p><strong>Disponibilités :</strong> {user.disponibilites}</p>
                        </div>
                        <div className="modal-actions">
                            <button onClick={handleClose}>Fermer</button>
                            {
                                (user.roles.includes('ADMIN') ||
                                    dataUser.roles.includes('PARRAIN') ||
                                    dataUser.parrainId) &&
                                <button onClick={() => alert('Branchement à venir!')}>Message</button>
                            }

                            {
                                (user.roles.includes('PARRAIN') &&
                                    dataUser.roles.includes('PORTEUR') && !dataUser.parrainId
                                ) &&
                                <button onClick={handleParrainAffect}>Choisir</button>
                            }
                            {
                                dataUser.roles.includes('ADMIN') &&
                                <>
                                    <button onClick={handleAdevelopper}>Modifier</button>
                                    <button onClick={handleAdevelopper}>Radier/Désactiver</button>
                                    <button onClick={handleAdevelopper}>Réunions</button>
                                </>
                            }
                        </div>
                    </div>
                </div>
            )}
        </>
    )
}

export default MediaCard;