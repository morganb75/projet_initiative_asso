import React from 'react';
import UserForm from "./UserForm.jsx";

const ParrainForm = ({formState, setFormState}) => {
    return (
        <>
            <p>formulaire parrain</p>
            <UserForm formState={formState} setFormState={setFormState}/>
        </>
    );
};

export default ParrainForm;