import React from 'react';

function NewsTitle(props) {
    return (
        <>
            {
                props.news &&
                props.news.map((item, index) => (
                    <React.Fragment key={index}>
                        <div className="bannerStyle2">
                            <div style={{marginTop: '0.5rem'}}>
                                2024. 05. 11 IT
                            </div>
                            <div className="flex mr1">
                                <img src="/images/test_image.png" className="test-image mr1"/>
                                <div className="mr1 text-center">{item.newsTitle}</div>
                            </div>
                        </div>
                        {index === props.news.length - 1 && (
                            <div className="bannerStyle3"></div>
                        )}
                    </React.Fragment>
                ))
            }
        </>
    );
}

export default NewsTitle;