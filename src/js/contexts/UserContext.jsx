import React, {useContext, useEffect, useState} from 'react';
import {createContext} from "react";
import decodeToken from "../utils/decodeToken.js";

//1. Creation du context

export const UserContext = createContext({
        dataUser: [],
        setDataUser: () => {
        }
    }
)

//2. Installation du context (Provider)

export default function UserContextProvider({children}) {
    const [dataUser, setDataUser] = useState(null)

    // // Met à jour le state ET stocke dans sessionStorage
    // const setUser = (user) => {
    //     setDataUser(user);
    //     sessionStorage.setItem("dataUser", JSON.stringify(user));
    // };
    //
    // // Initialise depuis sessionStorage
    useEffect(() => {
        const token = sessionStorage.getItem("authToken");
        if (token) {
            setDataUser(decodeToken(token));
        }
    }, []);

    const valueUserContext = {
        dataUser,
        setDataUser
    }
    return (
        <UserContext.Provider value={valueUserContext}>
            {children}
        </UserContext.Provider>
    )
}

//3. Consommation du context
export const useUserContext = () => useContext(UserContext)
