import React, { useState, useEffect } from 'react';
function LatestTopic(props){
    const [currentIndex, setCurrentIndex] = useState(0);


    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentIndex(prevIndex => (prevIndex + 1) % 5);
        }, 3000);
        return () => clearInterval(interval);
    }, []);

    return (
        <div className="topic-background pb1">
            <div className="pt1 pl1 text-left text-color">
                HOT TOPICS TODAY!
            </div>
            <div className="slider-container">
                <div className="slider " style={{transform: `translateX(-${currentIndex * 100}%)`}}>
                    {props.topics.map((item, index) => (
                        <div className="slide text-left" key={index}>{item.newsTitle}</div>
                    ))}
                </div>
                <div className="flex">
                    <div className="progress-bar">
                        {props.topics.map((_, index) => (
                            <div
                                key={index}
                                className={`progress-segment ${currentIndex === index ? 'active' : ''}`}
                                style={{width: `${100 /5}%`}}
                            ></div>
                        ))}
                    </div>
                    <div className="slide-counter">{currentIndex + 1} / {5}</div>
                </div>
            </div>
        </div>
    )
}

export default LatestTopic;