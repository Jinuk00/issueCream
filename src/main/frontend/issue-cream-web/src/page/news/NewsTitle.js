import React, {useState} from 'react';
import {Link} from "react-router-dom";

function NewsTitle(props) {

    const ImageSource = (categoryCode) => {
        switch(categoryCode) {
            case '스포츠':
                return '/images/sports_logo.png';
            case 'IT':
                return '/images/it_logo.png';
            case '미디어':
                return '/images/media_logo.png';
            case '경제':
                return '/images/economy_logo.png';
            case '시사':
                return '/images/preview_logo.png';
            default:
                return '/images/test_image.png';
        }
    }

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
                                <img src={ImageSource(item.categoryCode)} className="test-image mr4"/>
                                <Link to={'/newsDetail/' + item.id}
                                      className="mr5 text-center link">{item.newsTitle}</Link>
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