import React from 'react';
import {useUserContext} from "../../contexts/UserContext.jsx";
import RolesMultiSelector from "../selector/RolesMultiSelector.jsx";

const UserForm = ({formState, setFormState}) => {
    const {dataUser} = useUserContext()

    const handleRolesChange = (selectedRoles) => {
        setFormState(prevState => ({
            ...formState, roles: selectedRoles
        }))
    }

    return (
        <>
            <fieldset>
                <legend> Infos personnelles</legend>
                <div className="user-infos">
                    {/*<Civilite/>*/}
                    <div className="item">
                        <label htmlFor="nom">Nom:</label>
                        <input
                            type="text"
                            id="nom"
                            value={formState?.nom}
                            onChange={(e) => setFormState({...formState, nom: e.target.value})}
                        />
                    </div>
                    <div className="item">
                        <label htmlFor="prenom">Prénom:</label>
                        <input
                            type="text"
                            id="prenom"
                            value={formState?.prenom}
                            onChange={(e) => setFormState({...formState, prenom: e.target.value})}
                        /></div>
                    <div className="item">
                        <label htmlFor="email">Email:</label>
                        <input
                            type="text"
                            id="email"
                            value={formState?.email}
                            onChange={(e) => setFormState({...formState, email: e.target.value})}
                        /></div>
                    <div className="item">
                        <label htmlFor="entreprise">Entreprise:</label>
                        <input
                            type="text"
                            id="entreprise"
                            value={formState?.entreprise}
                            onChange={(e) => setFormState({...formState, entreprise: e.target.value})}
                        /></div>

                    {!dataUser?.roles.includes('ADMIN') && (
                        <div className="item">
                            <label htmlFor="password">Mot de passe:</label>
                            <input
                                type="password"
                                id="password"
                                value={formState?.password1}
                                onChange={(e) => setFormState({...formState, password1: e.target.value})}
                            />
                            <label htmlFor="password">Confirmer mot de passe:</label>
                            <input
                                type="password"
                                id="password"
                                value={formState?.password2}
                                onChange={(e) => setFormState({...formState, password2: e.target.value})}
                            />
                        </div>

                    )}
                    {dataUser?.roles.includes('ADMIN') &&
                        <RolesMultiSelector selectedRoles={formState.roles} onRolesChange={handleRolesChange}/>
                    }
                </div>
            </fieldset>
        </>

    );
};

export default UserForm;