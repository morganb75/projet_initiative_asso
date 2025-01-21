import React, {useState} from 'react';
import RolesMultiSelector from "../selector/RolesMultiSelector.jsx";

const PreInscription = () => {

    const initialState = {
        nom: '', prenom: '', email: '', entreprise: '', adresse: {
            numeroDeVoie: '', rue: '', complement: '', codePostal: '', ville: ''
        }, plateForme: 'DEUXSEVRES', roles: [], password: ''
    }

    const [formState, setFormState] = useState(initialState)

    const handleRolesChange = (selectedRoles) => {
        setFormState(formState => ({
            ...formState, roles: selectedRoles
        }))
    }

    function handleSubmit(event) {
        event.preventDefault()
        console.log(formState)
        const URL_PREINSCRIPTION = '/api/admin/user'

        const HTTP_DATA = {
            method: 'POST',
            headers: {
                'content-type': 'application/json',
                Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
            },
            body: JSON.stringify(formState)
        }
        fetch(URL_PREINSCRIPTION,HTTP_DATA)
            .then(response => alert('Utilisateur pré-enregistré avec succès'))
            .catch(error => console.error('Error', error))
    }

    return (<div className="main">
        <h1 className="text1">Pré-inscrire un utilisateur</h1>
        <form className="form-preregister" onSubmit={handleSubmit}>
            <div className="form-preregister-field">
                <label htmlFor="nom">Nom:</label>
                <input
                    type="text"
                    id="nom"
                    value={formState?.nom}
                    onChange={(e) => setFormState({...formState, nom: e.target.value})}
                />
                <label htmlFor="prenom">Prénom:</label>
                <input
                    type="text"
                    id="prenom"
                    value={formState?.prenom}
                    onChange={(e) => setFormState({...formState, prenom: e.target.value})}
                />
                <label htmlFor="email">Email:</label>
                <input
                    type="text"
                    id="email"
                    value={formState?.email}
                    onChange={(e) => setFormState({...formState, email: e.target.value})}
                />
                <label htmlFor="entreprise">Entreprise:</label>
                <input
                    type="text"
                    id="entreprise"
                    value={formState?.entreprise}
                    onChange={(e) => setFormState({...formState, entreprise: e.target.value})}
                />
                <label htmlFor="numero">N° de voie:</label>
                <input
                    type="number"
                    id="numero"
                    value={formState?.adresse?.numeroDeVoie}
                    onChange={(e) => setFormState({
                        ...formState, adresse: {...formState.adresse, numeroDeVoie: e.target.value}
                    })}
                />
                <label htmlFor="rue">Rue:</label>
                <input
                    type="text"
                    id="rue"
                    value={formState?.adresse?.rue}
                    onChange={(e) => setFormState({
                        ...formState, adresse: {...formState.adresse, rue: e.target.value}
                    })}
                />
                <label htmlFor="complement">Complement:</label>
                <input
                    type="text"
                    id="complement"
                    value={formState?.adresse?.complement}
                    onChange={(e) => setFormState({
                        ...formState, adresse: {...formState.adresse, complement: e.target.value}
                    })}
                />
                <label htmlFor="codePostal">Code postal:</label>
                <input
                    type="codePostal"
                    id="codePostal"
                    value={formState?.adresse?.codePostal}
                    onChange={(e) => setFormState({
                        ...formState, adresse: {...formState.adresse, codePostal: e.target.value}
                    })}
                />
                <label htmlFor="ville">Ville:</label>
                <input
                    type="text"
                    id="ville"
                    value={formState?.adresse?.ville}
                    onChange={(e) => setFormState({
                        ...formState, adresse: {...formState.adresse, ville: e.target.value}
                    })}
                />
                <RolesMultiSelector selectedRoles={formState.roles} onRolesChange={handleRolesChange}/>
                <div>
                    <strong>Roles:</strong> {formState.roles.map(role => role.label).join(', ')}
                </div>
                <button className="button-preregister" type="submit">Enregistrer</button>
            </div>
        </form>
    </div>)
}

export default PreInscription;