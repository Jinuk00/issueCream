import React from "react";

function Talk3(props){
    return (
        <div className="chat-container">
            <img src="/images/Characters_image3.png" className="character-logo"/>
            <div className="speech-bubble">
                {props.content}
            </div>
        </div>
    )
}

export default Talk3;