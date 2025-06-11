import React from 'react';
import {useNavigate} from "react-router-dom";
import {useUserContext} from "../../contexts/UserContext.jsx";

const MsgSideBar = ({onLineUsers, loading, contacts, currentConversationContact, setCurrentConversationContact}) => {
        const {dataUser} = useUserContext()
        const navigate = useNavigate()
        const handleHome = () => {
            (dataUser.roles.includes('ADMIN')) ? navigate('/admin') : navigate('/user')
        }


        return (
            <aside className="msg-sidebar">
                <div className="sidebar-header">
                    <h2 onClick={handleHome} style={{
                        cursor: "pointer"
                    }}>Retour Accueil </h2>
                    <h2>Conversations</h2>
                </div>
                <div className="conversation-list">
                    {loading ? (
                        <div>Chargement en cours.....</div>
                    ) : (
                        contacts &&
                        contacts.map((ct) => {
                            return (
                                <div key={ct.id}>
                                    <h3
                                        onClick={() => setCurrentConversationContact(prev => ({
                                            ...prev,
                                            id: ct.id,
                                            name: ct.name,
                                            firstName: ct.firstName
                                        }))}
                                        style={{
                                            cursor: "pointer",
                                            backgroundColor: currentConversationContact?.id === ct.id ? "#e0e0e0" : "transparent"
                                        }}
                                    >
                                        <span>{onLineUsers.includes(Number(ct.id)) ? '🟢 ' : '🔴 '}</span>{ct.firstName} {ct.name}
                                    </h3>
                                </div>
                            );
                        })
                    )}
                </div>
            </aside>
        );
    }
;

export default MsgSideBar;