import {createContext, useContext, useState} from "react";

//1. Creation du context
export const DataFeedContext = createContext({
        dataFeed: [],
        setDataFeed: () => {
        }
    }
)

//2. Installation du context (Provider)
export default function DataFeedContextProvider({children}) {
    const [dataFeed, setDataFeed] = useState()
    const valueDataFeedContext = {
        dataFeed,
        setDataFeed
    }
    return (
        <DataFeedContext.Provider value={valueDataFeedContext}>
            {children}
        </DataFeedContext.Provider>
    )
}

//3. Consommation du context
export const useDataFeedContext = () => useContext(DataFeedContext)