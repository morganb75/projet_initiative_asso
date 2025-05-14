export const formatMessageTime = (isoDate) => {
    const messageDate = new Date(isoDate);
    const now = new Date();

    const isToday =
        messageDate.toDateString() === now.toDateString();

    const yesterday = new Date();
    yesterday.setDate(now.getDate() - 1);
    const isYesterday =
        messageDate.toDateString() === yesterday.toDateString();

    const time = messageDate.toLocaleTimeString("fr-FR", {
        hour: "2-digit",
        minute: "2-digit"
    });

    if (isToday) {
        return `Aujourd’hui à ${time}`;
    } else if (isYesterday) {
        return `Hier à ${time}`;
    } else {
        const date = messageDate.toLocaleDateString("fr-FR");
        return `${date} à ${time}`;
    }
};
