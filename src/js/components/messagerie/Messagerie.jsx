import React, {useEffect, useMemo, useState} from "react";
import "./messagerie.scss";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";
import MsgSideBar from "./MsgSideBar.jsx";
import ChatWindow from "./ChatWindow.jsx";
import {Stomp} from "@stomp/stompjs";
import {useDataFeedContext} from "../../contexts/DataFeedContext.jsx";

const Messagerie = () => {
    const {dataUser} = useUserContext()
    const {dataFeed} = useDataFeedContext()
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
    )

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
        console.log('MISE A JOUR CONV FROM SERVER')
        getConversations();
    }, [URL_MESSAGES, HttpData, currentContact]);

    useEffect(() => {
        console.log({dataFeed})

        const tabContacts = dataFeed?.map((user) => ({
            id: user.id,
            firstName: user.prenom,
            name: user.nom
        }))

        setContacts(tabContacts);

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
