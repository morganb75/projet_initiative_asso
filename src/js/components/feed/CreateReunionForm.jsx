import React, {useState} from 'react';
import './createreunionform.scss'
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";
import {useNavigate} from "react-router-dom";

const CreateReunionForm = () => {
    const {dataUser} = useUserContext()
    const navigate = useNavigate()
    const initialReunionForm = {
        motif: '',
        date: '',
        porteurId: dataUser.id,
        parrainId: dataUser.parrainId
    }
    const [formState, setFormState] = useState(initialReunionForm)
    console.log({formState})
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

    const handleCreate = async (e) => {
        e.preventDefault()
        try {
            await fetchEndPoint(URL_POST_DATA, HTTP_POST_DATA)
            alert("Réunion créée avec succès ✅")
            navigate("/user/reunions");
        } catch (error) {
            alert("Échec de la création de la réunion ❌")
        }
    }


const handleCancel = () => navigate('/user/reunions')

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
                <div className="btn-group">
                    <button type="submit">Créer</button>
                    <button type="button" onClick={handleCancel}>Annuler</button>
                </div>
            </fieldset>
        </form>
    </div>
)
}


export default CreateReunionForm;