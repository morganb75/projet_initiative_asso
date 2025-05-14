import React, { useEffect, useMemo, useState } from "react";
import "./messagerie2.scss";
import { useUserContext } from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";
import MsgSideBar from "./MsgSideBar.jsx";
import ChatWindow from "./ChatWindow.jsx";

const Messagerie = () => {
    const { dataUser } = useUserContext();
    const authToken = useMemo(() => sessionStorage.getItem("authToken"), []);
    const [conversationsFromServer, setConversationsFromServer] = useState({});
    const [contacts, setContacts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [currentContact, setCurrentContact] = useState(null);

    const URL_MESSAGES = useMemo(
        () => `/api/messages/conversations?userId=${dataUser.id}`,
        [dataUser.id]
    );

    const HttpData = useMemo(
        () => ({
            method: "GET",
            headers: {
                "content-type": "application/json",
                Authorization: `Bearer ${authToken}`,
            },
        }),
        [authToken]
    );

    useEffect(() => {
        const getConversations = async () => {
            const results = await fetchEndPoint(URL_MESSAGES, HttpData);
            setConversationsFromServer(results);
            setLoading(false);
        };
        getConversations();
    }, [URL_MESSAGES, HttpData]);

    useEffect(() => {
        if (!conversationsFromServer) return;
        const newContacts = Object.entries(conversationsFromServer).map(
            ([userId, messages]) => {
                const sampleMsg = messages.find(
                    (msg) => msg.senderId === Number(userId)
                );
                return {
                    id: userId,
                    name: sampleMsg?.senderName,
                    firstName: sampleMsg?.senderFirstName,
                };
            }
        );
        setContacts(newContacts);
    }, [conversationsFromServer]);

    return (
        <div className="main">
            <MsgSideBar
                loading={loading}
                contacts={contacts}
                currentConversationContact={currentContact}
                setCurrentConversationContact={setCurrentContact}
            />
            {currentContact && (
                <ChatWindow
                    myDataUser={dataUser}
                    contact={currentContact}
                    messages={conversationsFromServer[currentContact.id] || []}
                    authToken={authToken}
                />
            )}
        </div>
    );
};

export default Messagerie;
