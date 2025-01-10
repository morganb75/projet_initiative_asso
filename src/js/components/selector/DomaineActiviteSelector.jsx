import React, {useEffect, useState} from 'react';
import Select from "react-select";

const DomaineActiviteSelector = ({selectedDomaines, onDomainesChange}) => {

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
        // onDomainesChange(selectedOptions);
        const value = selectedOptions ? selectedOptions.value : ''
        onDomainesChange(value)
    }
    return (
        <div>
            <Select
                // isMulti
                options={domaines}
                onChange={handleChange}
                // value={selectedDomaines}
                value={domaines.find(domaine => domaine.value === selectedDomaines) || null}
            />
        </div>
    );
};

export default DomaineActiviteSelector;