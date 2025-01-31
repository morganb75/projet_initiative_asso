import React, {useEffect, useState} from 'react';
import Select from "react-select";

const RolesMultiSelector = ({selectedRoles, onRolesChange}) => {

    const [roles, setRoles] = useState([])

    useEffect(() => {
        fetch('/api/enums/roles')
            .then(response => response.json())
            .then(data => {
                const options = data.map(role => ({
                    value: role,
                    label: role
                }))
                setRoles(options)
            })
            .catch((error => {
                console.error('Erreur lors de la récupération des rôles: ', error)
            }))
    }, []);

    const handleChange = (selectedOptions) => {
        // onRolesChange(selectedOptions);
        const values = selectedOptions ? selectedOptions.map(option => option.value) : []
        onRolesChange(values)
    }

    return (
        <>
            <label htmlFor="roles">Roles</label>
            <Select
                id="roles"
                isMulti
                options={roles}
                onChange={handleChange}
                // value={selectedRoles}
                value={roles.filter(role => selectedRoles.includes(role.value))}
            />
        </>
    );
};

export default RolesMultiSelector;