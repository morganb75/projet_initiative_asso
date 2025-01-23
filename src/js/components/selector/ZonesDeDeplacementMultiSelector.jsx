import React, {useEffect, useState} from 'react';
import Select from "react-select";

const ZonesDeDeplacementMultiSelector = ({selectedZones, onZonesChange}) => {

    const [zones, setZones] = useState([])

    useEffect(() => {
        fetch('/api/enums/zonesdeplacement')
            .then(response => response.json())
            .then(data => {
                const options = data.map(zone => ({
                    value: zone,
                    label: zone
                }))
                console.log(options)
                setZones(options)
            })
            .catch((error => {
                console.error('Erreur lors de la récupération des zones de déplacement: ', error)
            }))
    }, []);

    const handleChange = (selectedOptions) => {
        onZonesChange(selectedOptions || []);
    }

    return (
        <div>
            <Select
                isMulti
                options={zones}
                onChange={handleChange}
                value={zones.filter((zone) =>
                    selectedZones.some((selected) => selected.value === zone.value)
                )} // Sélectionner les options déjà choisies
            />
        </div>
    );
};

export default ZonesDeDeplacementMultiSelector;