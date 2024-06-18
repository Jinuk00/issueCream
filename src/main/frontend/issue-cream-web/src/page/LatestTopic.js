import React, { useState, useEffect } from 'react';
function LatestTopic(){
    const newsItems = [
        "아이패드 신제품 가격이 냉장고값이라구?!😳\n" +
        "                애플 신제품 출시 현장으로 GO GO☝️☝",
        "아이패드 신제품 가격이 냉장고값이라구?!😳\n" +
        "                애플 신제품 출시 현장으로 GO GO☝️☝",
        "아이패드 신제품 가격이 냉장고값이라구?!😳\n" +
        "                애플 신제품 출시 현장으로 GO GO☝️☝",
        "아이패드 신제품 가격이 냉장고값이라구?!😳\n" +
        "                애플 신제품 출시 현장으로 GO GO☝️☝",
        "아이패드 신제품 가격이 냉장고값이라구?!😳\n" +
        "                애플 신제품 출시 현장으로 GO GO☝️☝",
    ];

    const [currentIndex, setCurrentIndex] = useState(0);

    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentIndex(prevIndex => (prevIndex + 1) % newsItems.length);
        }, 3000);
        return () => clearInterval(interval);
    }, []);

    return (
        <div className="">
            <div className="mr1">
                HOT TOPICS TODAY!
            </div>
            <div className="slider-container">
                <div className="slider" style={{transform: `translateX(-${currentIndex * 100}%)`}}>
                    {newsItems.map((item, index) => (
                        <div className="slide" key={index}>{item}</div>
                    ))}
                </div>
                <div className="flex">
                    <div className="progress-bar">
                        {newsItems.map((_, index) => (
                            <div
                                key={index}
                                className={`progress-segment ${currentIndex === index ? 'active' : ''}`}
                                style={{width: `${100 / newsItems.length}%`}}
                            ></div>
                        ))}
                    </div>
                    <div className="slide-counter">{currentIndex + 1} / {newsItems.length}</div>
                </div>
            </div>
        </div>
    )
}

export default LatestTopic;