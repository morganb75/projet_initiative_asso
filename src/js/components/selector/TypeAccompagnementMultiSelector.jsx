import React, {useEffect, useState} from 'react';
import Select from "react-select";

const TypeAccompagnementtMultiSelector = ({selectedDomaines, onDomainesChange}) => {

    const [domaines, setDomaines] = useState([])

    useEffect(() => {
        fetch('/api/enums/accompagnements')
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
        <div className="activite-selector">
            <Select
                isMulti
                options={domaines}
                onChange={handleChange}
                value={domaines.filter((domaine) =>
                    selectedDomaines?.includes(domaine.value)
                )}
            />
        </div>
    );
};

export default TypeAccompagnementtMultiSelector;