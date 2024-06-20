import React, { useState, useEffect ,useRef} from 'react';
import {useNavigate} from "react-router-dom";
function LatestTopic(props){
    const [currentIndex, setCurrentIndex] = useState(0);
    const [manualChange, setManualChange] = useState(false);
    const intervalRef = useRef(null);
    const navigate = useNavigate();
    useEffect(() => {
        startAutoSlide();
        return () => stopAutoSlide();
    }, []);

    useEffect(() => {
        if (manualChange) {
            stopAutoSlide();
            const resetManualChange = setTimeout(() => {
                setManualChange(false);
                startAutoSlide();
            }, 1500);
            return () => clearTimeout(resetManualChange);
        }
    }, [manualChange]);

    const startAutoSlide = () => {
        intervalRef.current = setInterval(() => {
            setCurrentIndex(prevIndex => (prevIndex + 1) % 5);
        }, 3000);
    };

    const stopAutoSlide = () => {
        if (intervalRef.current) {
            clearInterval(intervalRef.current);
        }
    };

    const handleNext = () => {
        setManualChange(true);
        setCurrentIndex(prevIndex => (prevIndex + 1) % 5);
    };

    const handlePrev = () => {
        setManualChange(true);
        setCurrentIndex(prevIndex => (prevIndex - 1 + 5) % 5);
    };

    const gotoNewsDetail=(id)=>{
        navigate(`/newsDetail/${id}`);
    }

    return (
        <div className="topic-background pb1">
            <div className="pt1 pl1 text-left text-color font2">
                HOT TOPICS TODAY!
            </div>
            <div className="slider-container">
                <div className="slider"
                     style={{transform: `translateX(-${currentIndex * 100}%)`, transition: 'transform 1.5s ease'}}>
                    {props.topics.map((item, index) => (
                        <div className="slide text-left btn" key={index} onClick={()=>gotoNewsDetail(item.id)}>{item.newsTitle}</div>
                    ))}
                </div>
                <div className="flex">
                    <div className="progress-bar">
                        {props.topics.map((_, index) => (
                            <div
                                key={index}
                                className={`progress-segment ${currentIndex === index ? 'active' : ''}`}
                                style={{width: `${100 / 5}%`}}
                            ></div>
                        ))}
                    </div>
                    <div className="slide-counter">{currentIndex + 1} / {5}</div>
                </div>
                <div className="slider-buttons">
                    <button onClick={handlePrev}><img src="/images/left.png" className=" image-size2"/>
                    </button>
                    <button onClick={handleNext}><img src="/images/right.png" className=" image-size2"/>
                    </button>
                </div>
            </div>
        </div>
    )
}

export default LatestTopic;