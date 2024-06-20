import React from 'react';
import {Link} from "react-router-dom";

function NewsTitle(props) {

    // const formatDate = (dateString) => {
    //     const year = dateString.substring(0, 4);
    //     const month = dateString.substring(4, 6);
    //     const day = dateString.substring(6, 8);
    //     return `${year}. ${month}. ${day}`;
    // };

    return (
        <>
            {
                props.news &&
                props.news.map((item, index) => (
                    <React.Fragment key={index}>
                        <div className="bannerStyle2">
                            <div className="ml1 flex2" style={{marginTop: '0.5rem', textAlign: 'left'}}>
                                <div className="text-color2 mr4">{item.newsDate}</div>
                                <div className="text-color3 mr4">{item.categoryCode}</div>
                            </div>
                            <div className="flex mr1" style={{textAlign: 'left'}}>
                                <img src="/images/preview_logo.png" className="test-image mr4"/>
                                <Link to={'/newsDetail/'+item.id} className="mr5 text-center link">{item.newsTitle}</Link>
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