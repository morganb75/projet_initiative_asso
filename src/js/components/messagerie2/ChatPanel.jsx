import React, { useEffect, useRef } from "react";
import { formatMessageTime } from "../../utils/formatTime.js";

const ChatPanel = ({ myDataUser, realTimeConversationMsg }) => {
    const bottomRef = useRef(null);

    useEffect(() => {
        bottomRef.current?.scrollIntoView({ behavior: "smooth" });
    }, [realTimeConversationMsg]);

    if (!realTimeConversationMsg?.length) return <div>Pas de messages</div>;

    return (
        <div className="chat-messages">
            {realTimeConversationMsg.map((msg, idx) => (
                <div key={idx} className={myDataUser.id === msg.senderId ? "me" : "contact"}>
                    <div>{formatMessageTime(msg.time)}</div>
                    <div>{msg.text}</div>
                </div>
            ))}
            <div ref={bottomRef} />
        </div>
    );
};

export default ChatPanel;
