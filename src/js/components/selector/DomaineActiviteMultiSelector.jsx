import React, {useEffect, useState} from 'react';
import Select from "react-select";

const DomaineActiviteMultiSelector = ({selectedDomaines, onDomainesChange}) => {

    const [domaines, setDomaines] = useState([])

    useEffect(() => {
        fetch('/api/enums/activites')
            .then(response => response.json())
            .then(data => {
                const options = data.map(domaine => ({
                    value: domaine,
                    label: domaine
                }))
                setDomaines(options)
            })
            .catch((error => {
                console.error('Erreur lors de la récupération des domaines: ', error)
            }))
    }, []);

    const handleChange = (selectedOptions) => {
        onDomainesChange(selectedOptions);
    }

    return (
        <div>
            <Select
                isMulti
                options={domaines}
                onChange={handleChange}
                value={domaines.filter((domaine) =>
                    selectedDomaines.some((selected) => selected.value === domaine.value)
                )} // Sélectionner les options déjà choisies
            />
        </div>
    );
};

export default DomaineActiviteMultiSelector;