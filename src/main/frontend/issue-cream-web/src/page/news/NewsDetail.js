import {Link, useParams} from "react-router-dom";
import axios from "axios";
import Category from "../Category";
import PageHeader from "../PageHeader";
import Footer from "../../common/Footer";
import React, {useEffect, useState} from "react";
import axiosUtils from "../../utils/AxiosUtils";
import Talk1 from "../news/Talk1";
import Talk2 from "../news/Talk2";
function NewsDetail(){
    let {id} = useParams();
    const [newsInfo, setNewsInfo] = useState({
        newsTitle:'',
        newsContent: '',
        categoryCode: '',
    });

    useEffect(()=>{
        axiosUtils.post('/api/news/searchDetail/' + id)
                .then((res) => {
                    if (res.data.resultCode == "OK") {
                        setNewsInfo(res.data.data);
                    }
                }).catch((error) => {
            setNewsInfo();
        });
    },[])

    return (
            <>
                <PageHeader/>
                <Category category={newsInfo && newsInfo.categoryCode}/>
                <div className="flex">
                    <div style={{marginBottom: '2rem'}} className="selectCategory">
                        {newsInfo && newsInfo.categoryCode}
                    </div>
                </div>
                <div className="base-blue2 main_padding" style={{marginBottom: '2rem'}}>
                    {newsInfo && newsInfo.newsTitle}
                        <div>
                            2024.04.11
                        </div>
                        <div>
                            카테고리####
                        </div>
                    </div>
                <div className="flex mr2">
                    <img src="/images/book.png" style={{width: '2rem', height: '2rem'}}
                         className={`mr1 ${isToggled ? 'hidden' : ''}`}/>
                    <div className={`toggle-switch mr1 ${isToggled ? 'toggled' : ''}`} onClick={handleToggle}>
                        <div className="toggle-knob"></div>
                    </div>
                    <img src="/images/talk.png" style={{width: '2rem', height: '2rem'}}
                         className={`mr1 ${isToggled ? '' : 'hidden'}`}/>
                </div>
                <div className={`mr2 ${isToggled ? 'hidden2' : ''}`}>
                    {
                        newsInfo &&
                        newsInfo.newsContent.split("\n").map(item => {
                            return (
                                <>
                                    {item}
                                    <br/>
                                </>
                            )
                            })
                    }
                </div>

                <div className={`mr2 ${isToggled ? '' : 'hidden2'}`}>
                    <Talk1/>
                    <Talk2/>
                </div>

                <div className="flex ">
                <img src="/images/scrap.png" className="mr2 image-size btn"/>
                    <img src="/images/share.png" className="mr2 image-size btn"/>
                </div>
                <Footer/>
            </>
    );
}

export default NewsDetail;