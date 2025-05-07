import React from 'react';
import {useUserContext} from "../../contexts/UserContext.jsx";
import PrivateMsg from "./PrivateMsg.jsx";

const Messagerie = () => {
    const {dataUser} = useUserContext()

    return (
        <div>{
            dataUser.id === 19 ?
                <PrivateMsg receiverId={1}/>
                :
                <PrivateMsg receiverId={19}/>
        }
        </div>
    )
}

export default Messagerie;