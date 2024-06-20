import React from "react";

function Talk2(props){
    return (
        <div className="chat-container reverse">
            <img src="/images/Characters_image1.png" className="character-logo2"/>
            <div className="speech-bubble2">
                {props.content}
            </div>
        </div>
    )
}

export default Talk2;