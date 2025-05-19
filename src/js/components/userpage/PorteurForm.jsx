import React, {useState} from 'react';
import UserForm from "./UserForm.jsx";
import DomaineActiviteSelector from "../selector/DomaineActiviteSelector.jsx";
import DomaineActiviteMultiSelector from "../selector/DomaineActiviteMultiSelector.jsx";
import TypeAccompagnementMultiSelector from "../selector/TypeAccompagnementMultiSelector.jsx";

const PorteurForm = ({formState, setFormState}) => {


    const handleDomaineChange = (selectedDomaine) => {
        setFormState(formState => ({...formState, domaineActivite: selectedDomaine}))
    }

    const handleBesoinsChange = (selectedOptions) => {
        const values = selectedOptions?.map(opt => opt.value) ?? []
        setFormState(formState => ({...formState, besoinsPotentiels: values}))
    }

    return (
        <>
            <UserForm formState={formState} setFormState={setFormState}/>

            <div className="porteur-infos">
                <fieldset>
                    <legend>Activité, besoins, disponibilités</legend>
                    <div className="porteur-infos-activite">
                        <div className="item"><label htmlFor="datedebutactivite">Date de début d'activité:</label>
                            <input
                                type="date"
                                id="datedebutactivite"
                                value={formState?.dateDebutActivite}
                                onChange={(e) => setFormState({...formState, dateDebutActivite: e.target.value})}
                            />
                        </div>
                        <div className="item"><label htmlFor="porteur-domaineactivite">Domaine d'activité:</label>
                            <DomaineActiviteSelector
                                id="porteur-domaineactivite"
                                selectedDomaine={formState.domaineActivite}
                                onDomaineChange={handleDomaineChange}
                            />
                        </div>
                        <div className="item"><label htmlFor="besoinspotentiels">Besoins potentiels:</label>
                            <TypeAccompagnementMultiSelector
                                id="besoinspotentiels"
                                selectedDomaines={formState?.besoinsPotentiels}
                                // selectedDomaines={currentBesoinSelected}
                                onDomainesChange={handleBesoinsChange}
                            />
                        </div>
                        <div className="item"><label htmlFor="porteur-disponibilites">Disponibilités:</label>
                            <input
                                type="text"
                                id="porteur-disponibilites"
                                value={formState?.disponibilites}
                                onChange={(e) => setFormState({...formState, disponibilites: e.target.value})}
                            />
                        </div>
                        <div className="item"><label htmlFor="descriptifactivite">Descriptif de l'activité:</label>
                            <textarea
                                id="descriptifactivite"
                                value={formState?.descriptifActivite}
                                onChange={(e) => setFormState({...formState, descriptifActivite: e.target.value})}
                                placeholder="Decrivez succintement votre activité....."
                                rows="3"
                                cols="50"
                            ></textarea>
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Adresse entreprise</legend>
                    <div className="porteur-infos-adresse-pro">
                        <div className="item"><label htmlFor="lieu-act-numero">N° de voie:</label>
                            <input
                                type="number"
                                id="lieu-act-numero"
                                value={formState?.lieuActivite?.numeroDeVoie}
                                onChange={(e) => setFormState({
                                    ...formState, lieuActivite: {...formState.lieuActivite, numeroDeVoie: e.target.value}
                                })}
                            />
                        </div>
                        <div className="item"><label htmlFor="lieu-act-rue">Rue:</label>
                            <input
                                type="text"
                                id="lieu-act-rue"
                                value={formState?.lieuActivite?.rue}
                                onChange={(e) => setFormState({
                                    ...formState, lieuActivite: {...formState.lieuActivite, rue: e.target.value}
                                })}
                            />
                        </div>
                        <div className="item"><label htmlFor="lieu-act-complement">Complement:</label>
                            <input
                                type="text"
                                id="lieu-act-complement"
                                value={formState?.lieuActivite?.complement}
                                onChange={(e) => setFormState({
                                    ...formState, lieuActivite: {...formState.lieuActivite, complement: e.target.value}
                                })}
                            />
                        </div>
                        <div className="item"><label htmlFor="lieu-act-codePostal">Code postal:</label>
                            <input
                                type="codePostal"
                                id="lieu-act-codePostal"
                                value={formState?.lieuActivite?.codePostal}
                                onChange={(e) => setFormState({
                                    ...formState, lieuActivite: {...formState.lieuActivite, codePostal: e.target.value}
                                })}
                            />
                        </div>
                        <div className="item"><label htmlFor="lieu-act-ville">Ville:</label>
                            <input
                                type="text"
                                id="lieu-act-ville"
                                value={formState?.lieuActivite?.ville}
                                onChange={(e) => setFormState({
                                    ...formState, lieuActivite: {...formState.lieuActivite, ville: e.target.value}
                                })}
                            />
                        </div>
                    </div>
                </fieldset>

            </div>
        </>
    );
};

export default PorteurForm;