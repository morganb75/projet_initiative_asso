import React from 'react';
import UserForm from "./UserForm.jsx";
import DomaineActiviteSelector from "../selector/DomaineActiviteSelector.jsx";
import ZonesDeDeplacementMultiSelector from "../selector/ZonesDeDeplacementMultiSelector.jsx";
import optionsMapper from "../../utils/optionsMapper.js";

const ParrainForm = ({formState, setFormState}) => {

    const handleDomaineChange = (selectedDomaine) => {
        setFormState(formState => ({...formState, domaineActivite: selectedDomaine}))
    }

    const handleZonesChange = (selectedZones) => {
        const mappedZones = optionsMapper(selectedZones)
        console.log(mappedZones)
        setFormState(formState => ({...formState, zonesDeDeplacement: mappedZones}))
    }
    return (
        <>
            <UserForm formState={formState} setFormState={setFormState}/>
            <fieldset>
                <legend>Activité,parcours, disponibilités</legend>
                <div className="parrain-infos-activite">
                    <div className="item">
                        <label htmlFor="parrain-domaineactivite">Domaine d'activité:</label>
                        <DomaineActiviteSelector
                            id="parrain-domaineactivite"
                            selectedDomaine={formState.domaineActivite}
                            onDomaineChange={handleDomaineChange}
                        /></div>
                    <div className="item">
                        <label htmlFor="parcours">Parcours:</label>
                        <textarea
                            id="parcours"
                            value={formState?.parcours}
                            onChange={(e) => setFormState({...formState, parcours: e.target.value})}
                            placeholder="Decrivez votre parcours....."
                            rows="5"
                            cols="50"
                        ></textarea></div>
                    <div className="item">
                        <label htmlFor="zones-de-deplacement">Zones de déplacement:</label>
                        <ZonesDeDeplacementMultiSelector
                            id="zones-de-deplacement"
                            selectedZones={formState?.zonesDeDeplacement.map(zone => ({
                                value: zone,
                                label: zone
                            }))}
                            onZonesChange={handleZonesChange}
                        /></div>
                    <div className="item">
                        <label htmlFor="parrain-disponibilites">Disponibilités:</label>
                        <input
                            type="text"
                            id="parrain-disponibilites"
                            value={formState?.disponibilites}
                            onChange={(e) => setFormState({...formState, disponibilites: e.target.value})}
                        />
                    </div>
                </div>
            </fieldset>
        </>
    );
};

export default ParrainForm;