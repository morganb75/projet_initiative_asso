import React, {useEffect, useMemo, useState} from 'react';
import {Stomp} from "@stomp/stompjs";

const PrivateMsg = ({receiverId}) => {
    const authToken = sessionStorage.getItem('authToken')
    const [messages, setMessages] = useState([])
    const [messageText, setMessageText] = useState("");
    const client = useMemo(() => {
        // return Stomp.client("ws://localhost:8080/api/ws")
        return Stomp.client(`ws://localhost:8080/api/ws?token=${authToken}`)
    }, [])

    useEffect(() => {
        client.connect({Authorization:`Bearer ${authToken}`}, () => {
            console.log("✅ STOMP connecté")
            //Abonnement à la file privée
            client.subscribe("/user/queue/messages", (msg) => {
                setMessages(prev => [...prev, msg.body])
            })
        }, (error) => {
            console.error("💥 STOMP erreur :", error)
        })

        return () => {
            client.disconnect(() => {
                console.log("❌ Déconnecté de STOMP")
            })
        }
    }, []);

    const sendMessage = () => {
        if (!messageText.trim()) return;

        const payload = {
            text: messageText,
            receiverId: receiverId
        };

        client.send("/app/msg.private", {}, JSON.stringify(payload));
        setMessages(prev => [...prev, `(moi) ${messageText}`]);
        setMessageText("");
    };

    return (
        <div>
            <h3>Messages reçus :</h3>
            <ul>
                {messages.map((msg, i) => <li key={i}>{msg}</li>)}
            </ul>
            <input
                type="text"
                value={messageText}
                onChange={(e) => setMessageText(e.target.value)}
                placeholder="Écris ton message"
            />
            <button onClick={sendMessage}>Envoyer</button>
        </div>
    );
};

export default PrivateMsg;