import React, {useContext, useState} from 'react';
import {createContext} from "react";

//1. Creation du context

export const UserContext = createContext({
        dataUser: [],
        setDataUser: () => {
        }
    }
)

//2. Installation du context (Provider)

export default function UserContextProvider({children}) {
    const [dataUser, setDataUser] = useState([])
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
