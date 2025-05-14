import React, { useEffect, useState } from "react";
import ChatPanel from "./ChatPanel.jsx";
import ChatInput from "./ChatInput.jsx";
import { Stomp } from "@stomp/stompjs";

const ChatWindow = ({ myDataUser, contact, messages, authToken }) => {
    const [client, setClient] = useState(null);
    const [realTimeMessages, setRealTimeMessages] = useState(messages);
    const [messageText, setMessageText] = useState("");

    useEffect(() => {
        setRealTimeMessages(messages); // reset si changement de contact
    }, [messages]);

    useEffect(() => {
        const stompClient = Stomp.client(`ws://localhost:8080/api/ws?token=${authToken}`);
        setClient(stompClient);

        stompClient.connect({ Authorization: `Bearer ${authToken}` }, () => {
            console.log("✅ STOMP connecté");
            stompClient.subscribe("/user/queue/messages", (msg) => {
                const parsed = JSON.parse(msg.body);
                // On filtre ici pour n'ajouter que les messages de cette conversation
                if (
                    parsed.senderId === Number(contact.id) ||
                    parsed.receiverId === Number(contact.id)
                ) {
                    setRealTimeMessages((prev) => [...prev, parsed]);
                }
            });
        });

        return () => {
            stompClient.disconnect(() => {
                console.log("❌ Déconnecté de STOMP");
            });
        };
    }, [contact.id, authToken]);

    const handleSendMessage = (e) => {
        e.preventDefault();
        if (!messageText.trim()) return;

        const payload = {
            read: false,
            senderId: myDataUser.id,
            receiverId: contact.id,
            senderName: myDataUser.name,
            senderFirstName: myDataUser.firstName,
            text: messageText,
            time: new Date().toISOString(),
        };

        client.send("/app/msg.private", {}, JSON.stringify(payload));
        setRealTimeMessages((prev) => [...prev, payload]);
        setMessageText("");
    };

    return (
        <div className="chat-window">
            <ChatPanel myDataUser={myDataUser} realTimeConversationMsg={realTimeMessages} />
            <ChatInput
                messageText={messageText}
                setMessageText={setMessageText}
                handleSendMessage={handleSendMessage}
            />
        </div>
    );
};

export default ChatWindow;
