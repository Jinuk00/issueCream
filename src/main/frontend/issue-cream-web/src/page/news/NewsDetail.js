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

    const [isToggled, setIsToggled] = useState(false);

    const handleToggle = () => {
        setIsToggled(prevState => !prevState);
    };

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

    const createBookmark = ()=>{
        console.log("클릭");
        if (!localStorage.getItem("access")) {
            alert("로그인이 필요한 서비스 입니다.");
            return;
        }
        axiosUtils.put("/api/bookmark/"+id)
                .then((res) => {
                    if (res.data.resultCode == "OK") {
                        alert("스크랩이 완료 됐습니다.");
                    }
                }).catch((error) => {
            alert("네트워크 에러입니다.");
        });
    }

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
                <img src="/images/scrap.png" className="mr2 image-size btn" onClick={createBookmark}/>
                    <img src="/images/share.png" className="mr2 image-size btn"/>
                </div>
                <Footer/>
            </>
    );
}

export default NewsDetail;