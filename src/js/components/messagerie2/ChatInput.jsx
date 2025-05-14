import React from "react";

const ChatInput = ({ messageText, setMessageText, handleSendMessage }) => {
    return (
        <form className="chat-input" onSubmit={handleSendMessage}>
      <textarea
          placeholder="Écris ton message..."
          rows={1}
          value={messageText}
          onChange={(e) => setMessageText(e.target.value)}
          onInput={(e) => {
              e.target.style.height = "auto";
              e.target.style.height = `${e.target.scrollHeight}px`;
          }}
      />
            <button type="submit">Envoyer</button>
        </form>
    );
};

export default ChatInput;
