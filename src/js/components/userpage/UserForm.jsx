import React from 'react';

const UserForm = ({formState, setFormState}) => {


    return (
        <>
            <div className="user-infos">
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
                <label htmlFor="password">Nouveau mot de passe:</label>
                <input
                    type="password"
                    id="password"
                    value={formState?.password}
                    onChange={(e) => setFormState({...formState, password: e.target.value})}
                />
            </div>

            <div className="user-adresse">
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
            </div>
        </>

    );
};

export default UserForm;