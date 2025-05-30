import {createContext, useContext, useState} from "react";

export const AllUsersForAdminContext = createContext(
    {
        allUsers: [],
        setAllUsers: () => {
        }
    }
)

export default function AllUsersForAdminContextProvider({children}) {
    const [allUsers, setAllUsers] = useState([])

    const contextValue = {
        allUsers,
        setAllUsers
    }

    return (
        <AllUsersForAdminContext.Provider value={contextValue}>
            {children}
        </AllUsersForAdminContext.Provider>
    )
}

export const useAllUsersForAdminContext = () => useContext(AllUsersForAdminContext)