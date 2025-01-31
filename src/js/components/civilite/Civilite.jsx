import React, {useState} from 'react';

const Civilite = () => {
    const [civilite, setCivilite] = useState("Mr")

    const handleChange = (e) => {
        setCivilite(e.target.value)
    }

    return (
        <div className="item-civilité">
            <div className="item-civilite-secondary">
                <label>Mr</label>
                    <input
                        type="radio"
                        value="Mr"
                        checked={civilite === "Mr"}
                        onChange={handleChange}
                    />

            </div>
            <div className="item-civilite-secondary">
                <label>Mme</label>
                    <input
                        type="radio"
                        value="Mme"
                        checked={civilite === "Mme"}
                        onChange={handleChange}
                    />

            </div>
        </div>
    );
};

export default Civilite;