import React, {useEffect, useState} from 'react';
import Select from "react-select";

const DomaineActiviteSelector = ({selectedDomaine, onDomaineChange}) => {

    const [domaine, setDomaine] = useState([])

    useEffect(() => {
        fetch('/api/enums/activites')
            .then(response => response.json())
            .then(data => {
                const options = data.map(domaine => ({
                    value: domaine,
                    label: domaine
                }))
                setDomaine(options)
            })
            .catch((error => {
                console.error('Erreur lors de la récupération des domaines: ', error)
            }))
    }, []);

    const handleChange = (selectedOption) => {
        const value = selectedOption ? selectedOption.value : ''
        onDomaineChange(value)
    }
    return (
        <div className="activite-selector">
            <Select
                // className="activite-selector"
                options={domaine}
                onChange={handleChange}
                // value={selectedDomaines}
                value={domaine.find(domaine => domaine.value === selectedDomaine) || null}
            />
        </div>
    );
};

export default DomaineActiviteSelector;