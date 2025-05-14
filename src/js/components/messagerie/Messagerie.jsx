import React, {useEffect, useMemo, useState} from 'react';
import "./messagerie.scss";
import {useUserContext} from "../../contexts/UserContext.jsx";
import fetchEndPoint from "../../utils/fetchEndPoint.js";
import MsgSideBar from "./MsgSideBar.jsx";
import ChatPanel from "./ChatPanel.jsx";
import ChatInput from "./ChatInput.jsx";
import {Stomp} from "@stomp/stompjs";

/**
 * messages attendus sous forme :
 * [
 *   {
 *     id: 123,
 *     senderId: 1,
 *     senderName: "Alice",
 *     text: "Salut, comment ça va ?",
 *     timestamp: "2024-05-07T14:12:00Z",
 *     read: false
 *   },
 *   ...
 * ]
 */
const Messagerie = () => {
    const {dataUser} = useUserContext();
    // const authToken = sessionStorage.getItem('authToken')
    const authToken = useMemo(() => sessionStorage.getItem('authToken'), []);
    const [conversationsFromServer, setConversationsFromServer] = useState({})
    const [currentConversationContact, setCurrentConversationContact] = useState({})
    const [currentConversationMsg, setCurrentConversationMsg] = useState([])
    const [realTimeConversationMsg, setRealTimeCurrentConversationMsg] = useState([])
    const [contacts, setContacts] = useState([])
    const [loading, setLoading] = useState(true)
    const [client, setClient] = useState(null) //stomp client
    const [messageText, setMessageText] = useState("");

    const URL_STOMP_CLIENT = `ws://localhost:8080/api/ws?token=${authToken}`

    const URL_MESSAGES = useMemo(
        () => `/api/messages/conversations?userId=${dataUser.id}`,
        [dataUser.id]
    );

    const HttpData = useMemo(
        () => ({
            method: 'GET',
            headers: {
                'content-type': 'application/json',
                Authorization: `Bearer ${authToken}`
            }
        }),
        [authToken]
    );
    // const URL_MESSAGES = `/api/messages/conversations?userId=${dataUser.id}`
    // const HttpData = {
    //     method: 'GET',
    //     headers: {
    //         'content-type': 'application/json',
    //         Authorization: `Bearer ${authToken}`
    //     }
    // }

    //Websocket client setup
    useEffect(() => {
        const stompClient = Stomp.client(URL_STOMP_CLIENT)
        setClient(stompClient)

        stompClient.connect({Authorization: `Bearer ${authToken}`}, () => {
            console.log("✅ STOMP connecté")
            //Abonnement à la file privée
            stompClient.subscribe("/user/queue/messages", (msg) => {
                setRealTimeCurrentConversationMsg(prev => [...prev, msg.body])
            })
        }, (error) => {
            console.error("💥 STOMP erreur :", error)
        })

        return () => {
            stompClient.disconnect(() => {
                console.log("❌ Déconnecté de STOMP")
            })
        }
    }, [authToken]);

    //fetch conversations from server
    useEffect(() => {
        console.log({authToken})
        const getConversations = async () => {
            const results = await fetchEndPoint(URL_MESSAGES, HttpData)
            setConversationsFromServer(results)
            setLoading(false)
        }
        getConversations()
    }, [URL_MESSAGES, HttpData]);

    //Gestion des contacts pour Sidebar
    useEffect(() => {
        conversationsFromServer &&
        Object.entries(conversationsFromServer).map(([userId, messages]) => {
            const msgFromContact = messages.find((msg) => msg.senderId === Number(userId))
            const newContact = {
                id: userId,
                name: msgFromContact?.senderName,
                firstName: msgFromContact?.senderFirstName
            }
            setContacts(prev => {
                const alreadyExist = prev.some(ct => ct.id === newContact.id)
                return alreadyExist ? prev : [...prev, newContact]
            })
        })
    }, [conversationsFromServer]);


    useEffect(() => {
        console.log({conversationsFromServer})
        contacts &&
        console.log({contacts})
    }, [contacts]);

    useEffect(() => {
        currentConversationContact &&
        console.log({currentConversationContact})
        setCurrentConversationMsg(conversationsFromServer[currentConversationContact.id])
        setRealTimeCurrentConversationMsg(conversationsFromServer[currentConversationContact.id])
    }, [currentConversationContact]);

    const handleSendMessage = () => {
        if (!messageText.trim()) return;

        const payload = {
            read: false,
            senderId: dataUser.id,
            receiverId: currentConversationContact.id,
            senderName: dataUser.name,
            senderFirstName: dataUser.firstName,
            text: messageText,
            time: new Date().toISOString()
        };

        client.send("/app/msg.private", {}, JSON.stringify(payload));
        setRealTimeCurrentConversationMsg(prev => [...prev, payload]);
        setMessageText("")
    };

    return (
        <>
            <div className="main">
                <MsgSideBar
                    loading={loading}
                    setLoading={setLoading}
                    contacts={contacts}
                    currentConversationContact={currentConversationContact}
                    setCurrentConversationContact={setCurrentConversationContact}
                />
                <ChatPanel
                    myDataUser={dataUser}
                    realTimeConversationMsg={realTimeConversationMsg}
                />
                <ChatInput
                    messageText={messageText}
                    setMessageText={setMessageText}
                    handleSendMessage={handleSendMessage}
                />
            </div>
        </>
    );
};

export default Messagerie;