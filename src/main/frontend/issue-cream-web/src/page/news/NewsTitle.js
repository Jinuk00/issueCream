import React from 'react';
import {Link} from "react-router-dom";

function NewsTitle(props) {
    return (
        <>
            <div><span style={{color:"blue"}}>'{props.searchTitle}'</span> 키워드에 해당하는 검색결과</div>
            {
                props.news &&
                props.news.map((item, index) => (
                    <React.Fragment key={index}>
                        <div className="bannerStyle2">
                            <div className="mr3" style={{marginTop: '0.5rem', textAlign: 'left'}} >
                                {item.newsDate} {item.categoryCode}
                            </div>
                            <div className="flex mr1" style={{textAlign: 'left'}}>
                                <img src="/images/test_image.png" className="test-image mr1"/>
                                <Link to={'/newsDetail/'+item.id} className="mr1 text-center link">{item.newsTitle}</Link>
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