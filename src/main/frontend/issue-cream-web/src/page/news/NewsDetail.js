import {Link, useParams} from "react-router-dom";
import axios from "axios";
import Category from "../Category";
import PageHeader from "../PageHeader";
import Footer from "../../common/Footer";
import React, {useEffect, useState} from "react";
import axiosUtils from "../../utils/AxiosUtils";
import Talk1 from "../news/Talk1";
import Talk2 from "../news/Talk2";
import Talk3 from "../news/Talk3";
import DetailKeyword from "./DetailKeyword";
import emojiDictionary from "emoji-dictionary";

function NewsDetail(){
    let {id} = useParams();
    const [newsInfo, setNewsInfo] = useState({
        newsTitle:'',
        newsContent: '',
        categoryCode: '',
        newsDate:'',
        keyWord1: '',
        keyWord2: '',
        keyWord3: '',
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

    const handleShare = async () => {
        try {
            if (navigator.share) {
                await navigator.share({
                    title: document.title,
                    text: 'Check out this site!',
                    url: window.location.href,
                });
                console.log('Successfully shared');
            } else {
                console.log('Share not supported on this browser, do it the old way.');
            }
        } catch (error) {
            console.error('Error sharing:', error);
        }
    };

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

    const unicodeToEmoji = (text) => {
        return text.split('|||\\').map(part => {
            console.log("part", part);
            return part.replace(/U([0-9A-Fa-f]{8})/g, (match, p1) => {
                const emojiUnicode = `\\u${p1}`;
                console.log(emojiUnicode);
                console.log("match",match);
                console.log(emojiDictionary.getUnicode(emojiUnicode) || String.fromCodePoint(parseInt(p1, 16)));
                return emojiDictionary.getUnicode(emojiUnicode) || String.fromCodePoint(parseInt(p1, 16));
            });
        }).join('');
    };

    const processNewsContent = (content) => {
        return unicodeToEmoji(content).split("\n").map((item, index) => (
                <React.Fragment key={index}>
                    {item}
                    <br />
                </React.Fragment>
        ));
    };

    return (
            <>
                <PageHeader/>
                <Category category={newsInfo && newsInfo.categoryCode}/>
                <div className="flex">
                    <div style={{marginBottom: '2rem', marginTop: '2rem'}} className="selectCategory3">
                        {newsInfo && newsInfo.categoryCode}
                    </div>
                </div>
                <div className="base-blue2 main_padding pt2" style={{marginBottom: '2rem'}}>
                    {newsInfo && newsInfo.newsTitle}
                    <div className="pb3 text-color2">
                        {newsInfo && newsInfo.newsDate}
                    </div>
                    <div className="flex pt1">
                        <DetailKeyword keyword={newsInfo.keyWord1}/>
                        <DetailKeyword keyword={newsInfo.keyWord2}/>
                        <DetailKeyword keyword={newsInfo.keyWord3}/>
                    </div>
                </div>
                <div className="flex3 mr2">
                    <div className={`toggle-switch mr1 ${isToggled ? 'toggled' : ''}`} onClick={handleToggle}>
                        <div className="toggle-knob">
                            <img src="/images/book2.png" alt="book" className={`${isToggled ? 'hidden' : ''}`}/>
                            <img src="/images/talk2.png" alt="talk" className={`${isToggled ? '' : 'hidden'}`}/>
                        </div>
                    </div>
                </div>
                <div className={`mr2 ${isToggled ? 'hidden2' : ''}`}>
                    {
                            newsInfo &&
                            processNewsContent(newsInfo.newsContent)
                    }
                </div>

                <div className={`mr2 ${isToggled ? '' : 'hidden2'}`}>
                    <Talk1/>
                    <Talk2/>
                    <Talk3/>
                </div>

                <div className="flex ">
                    <img src="/images/scrap.png" className="mr2 image-size btn" onClick={createBookmark}/>
                    <img src="/images/share.png" className="mr2 image-size btn" onClick={handleShare}/>
                </div>
                {/*<Footer/>*/}
            </>
    );
}

export default NewsDetail;