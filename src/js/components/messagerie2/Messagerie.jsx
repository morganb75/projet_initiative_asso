import React, {useEffect, useMemo, useState} from "react";
import "./messagerie.scss";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";
import MsgSideBar from "./MsgSideBar.jsx";
import ChatWindow from "./ChatWindow.jsx";
import {Stomp} from "@stomp/stompjs";

const Messagerie = () => {
    const {dataUser} = useUserContext()
    const authToken = useMemo(() => sessionStorage.getItem("authToken"), []);
    const [conversationsFromServer, setConversationsFromServer] = useState({})
    const [contacts, setContacts] = useState([])
    const [loading, setLoading] = useState(true)
    const [currentContact, setCurrentContact] = useState(null)
    const [stompClient, setStompClient] = useState(null)
    const [onLineUsers, setOnLineUsers] = useState([])


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
    )

    useEffect(() => {
        const getConversations = async () => {
            const results = await fetchEndPoint(URL_MESSAGES, HttpData)
            setConversationsFromServer(results);
            setLoading(false)
        }
        getConversations();
    }, [URL_MESSAGES, HttpData]);

    useEffect(() => {
        console.log({conversationsFromServer})

        const newContacts = []
        Object.entries(conversationsFromServer).forEach(([userId, messages]) => {
            const userIdNum = Number(userId);
            const msg =
                messages.find((msg) => msg.senderId === userIdNum) ||
                messages.find((msg) => msg.receiverId === userIdNum)

            if (!msg) return;

            newContacts.push({
                id: userId,
                name: msg.senderId === userIdNum ? msg.senderName : msg.receiverName,
                firstName: msg.senderId === userIdNum ? msg.senderFirstName : msg.receiverFirstName,
            })
            console.log({conversationsFromServer})
        })

        setContacts(newContacts);

    }, [conversationsFromServer])

    //Setup Stomp
    useEffect(() => {
        const client = Stomp.client(`ws://localhost:8080/api/ws?token=${authToken}`);

        client.connect({Authorization: `Bearer ${authToken}`}, () => {
                console.log("✅ STOMP connecté")

                // Message de présence initiale pour le client à la connexion
                client.subscribe("/user/queue/presence", (msg) => {
                    const users = JSON.parse(msg.body);
                    setOnLineUsers(users.map(user => user.id));
                    console.log("🧩 Présence initiale :", users)
                })

                // Broadcast des changements de présence
                client.subscribe("/topic/presence", (msg) => {
                    const users = JSON.parse(msg.body)
                    setOnLineUsers(users.map(user => user.id))
                    console.log("📥 Présence mise à jour :", users)
                })

                // Envoi explicite de l'enregistrement de présence
            client.send("/app/presence/register", {});

            }
        )
        setStompClient(client)

        return () => {
            client.disconnect(() => {
                console.log("❌ Déconnecté de STOMP");
            })
        }
    }, [authToken])

    return (
        <div className="main">
            <MsgSideBar
                loading={loading}
                contacts={contacts}
                currentConversationContact={currentContact}
                setCurrentConversationContact={setCurrentContact}
                onLineUsers={onLineUsers}
            />
            {currentContact && (
                <ChatWindow
                    myDataUser={dataUser}
                    contact={currentContact}
                    messages={conversationsFromServer[currentContact.id] || []}
                    stompClient={stompClient}
                />
            )}
        </div>
    )
}

export default Messagerie;
