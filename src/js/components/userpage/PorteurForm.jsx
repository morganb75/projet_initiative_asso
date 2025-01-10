import React from 'react';
import UserForm from "./UserForm.jsx";
import DomaineActiviteSelector from "../selector/DomaineActiviteSelector.jsx";

const PorteurForm = ({formState, setFormState}) => {

    const handleDomaineChange = (selectedDomaine) =>{
        setFormState(formState => ({...formState, domaineActivite:selectedDomaine}))
    }

    const handleBesoinChange = (selectedDomaine) =>{
        setFormState(formState => ({...formState, besoinPotentiel:selectedDomaine}))
    }

    return (
        <>
            <UserForm formState={formState} setFormState={setFormState}/>
            <div className="porteur-infos">
                <label htmlFor="datedebutactivite">Date de début d'activité:</label>
                <input
                    type="date"
                    id="datedebutactivite"
                    value={formState?.dateDebutActivite}
                    onChange={(e) => setFormState({...formState, dateDebutActivite: e.target.value})}
                />
                <label htmlFor="domaineactivite">Domaine d'activité:</label>
                <DomaineActiviteSelector
                    id="domaineactivite"
                    selectedDomaines={formState.domaineActivite}
                    onDomainesChange={handleDomaineChange}
                />
                <label htmlFor="lieuactivite">Lieu d'activité:</label>
                <input
                    type="text"
                    id="lieuactivite"
                    value={formState.lieuActivite}
                    onChange={(e) => setFormState({...formState, lieuActivite: e.target.value})}
                />
                <label htmlFor="besoinpotentiel">Besoin potentiel:</label>
                <DomaineActiviteSelector
                    id="besoinpotentiel"
                    selectedDomaines={formState.besoinPotentiel}
                    onDomainesChange={handleBesoinChange}
                />
                <label htmlFor="disponibilites">Disponibilités:</label>
                <input
                    type="text"
                    id="disponibilites"
                    value={formState?.disponibilites}
                    onChange={(e) => setFormState({...formState, disponibilites: e.target.value})}
                />
            </div>
        </>
    );
};

export default PorteurForm;