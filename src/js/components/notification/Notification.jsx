import React, {useState} from 'react';
import "./notification.scss"
import iconNotif from "../../assets/notification.jpg"

const Notification = ({notification}) => {

    return (
        <>
            {
                notification.length > 0 &&
                <div onClick={()=>alert("ACCES AUX NOTIFICATIONS")} className="notification-button">{notification.length}</div>
            }
        </>
    )
}
export default Notification;