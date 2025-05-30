import React, {useState} from 'react';
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";

const CreateReunionForm = () => {
    const {dataUser} = useUserContext()
    const initialReunionForm = {
        motif: '',
        date: '',
        porteurId: dataUser.id,
        parrainId: dataUser.parrainId
    }
    const [formState, setFormState] = useState(initialReunionForm)
    const URL_POST_DATA = '/api/reunions/create'
    const HTTP_POST_DATA = {
        method: 'POST',
        headers: {
            'content-type': 'application/json',
            Authorization: `Bearer ${sessionStorage.getItem('authToken')}`
        },
        body: JSON.stringify(formState)
    }

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormState({...formState, [name]: value});
    }

    const handleCreate =  (e) => {
        e.preventDefault()
        console.log({formState})
        fetchEndPoint(URL_POST_DATA,HTTP_POST_DATA)

    }

    return (
        <div className="feed">
            <form onSubmit={handleCreate}>
                <fieldset>
                    <legend>Nouvelle réunion</legend>
                    <label htmlFor="motif">Motif</label>
                    <input
                        type="text"
                        id="motif"
                        name="motif"
                        value={formState.motif}
                        onChange={handleChange}
                        required
                    />
                    <label htmlFor="date">Date</label>
                    <input
                        type="datetime-local"
                        id="date"
                        name="date"
                        value={formState.date}
                        onChange={handleChange}
                        required
                    />
                    <button type="submit">Créer</button>
                    <button type="Cancel">Annuler</button>
                </fieldset>
            </form>
        </div>
    );
};

export default CreateReunionForm;