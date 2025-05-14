import React from 'react';
import {useUserContext} from "../../contexts/UserContext.jsx";

const MsgSideBar = ({loading, contacts, currentConversationContact, setCurrentConversationContact}) => {
    const {dataUser} = useUserContext()
    return (
        <aside className="msg-sidebar">
            <div className="sidebar-header">
                <h2>Conversations</h2>
            </div>
            <div className="conversation-list">
                {loading ? (
                    <div>Chargement en cours.....</div>
                ) : (
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
                                        backgroundColor: currentConversationContact.id === ct.id ? "#e0e0e0" : "transparent"
                                    }}
                                >
                                    {ct.firstName} {ct.name}
                                </h3>
                            </div>
                        );
                    })
                )}
            </div>
        </aside>
    );
};

export default MsgSideBar;