import React, {useEffect, useRef} from 'react';
import {formatMessageTime} from "../../utils/formatTime.js";

const ChatPanel = ({myDataUser, realTimeConversationMsg}) => {
    const bottomRef = useRef(null);
    console.log({realTimeConversationMsg})
    useEffect(() => {
        if (bottomRef.current) {
            bottomRef.current.scrollIntoView({behavior: 'smooth'});
        }
    }, [realTimeConversationMsg]);
    return (
        <div className="chat-messages">
            {!realTimeConversationMsg ? (
                <div>pas de sélection</div>
            ) : (
                <>
                    {realTimeConversationMsg.map((msg) => (
                        <div key={msg.id} className={myDataUser.id === msg.senderId ? 'me' : 'contact'}>
                            <div>{formatMessageTime(msg.time)}</div>
                            <div>{msg.text}</div>
                        </div>
                    ))}
                    <div ref={bottomRef}/>
                </>
            )}
        </div>
    )
}
export default ChatPanel;