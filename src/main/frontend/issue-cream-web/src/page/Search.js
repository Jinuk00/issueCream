import React, {useState} from 'react';
import HeaderLogo from "../common/HeaderLogo";
import axios from "axios";
import NewsTitle from "./news/NewsTitle";
import {useCallback} from "react";
import Footer from "../common/Footer";

function Search() {

    const [searchTitle, setSearchTitle] = useState('');

    const [news, setNews] = useState([]);
    const [searchCheck, SetSearchCheck] = useState(false);
    const [viewTitle, setViewTitle] = useState("");
    const searchInput = (event) => {
        setSearchTitle(event.target.value);
    };

    const searchNews = useCallback((event) => {
        if (event.key == "Enter") {
            SetSearchCheck(true);

            axios.post("/api/news/search/title", "searchTitle=" + searchTitle)
                    .then((res) => {
                        if (res.data.resultCode == "OK") {
                            console.log(res.data.data);
                            setNews(res.data.data.newsData);
                            setViewTitle(res.data.data.searchTitle);
                        }
                    })
                    .catch((error) => {
                        setNews([]);
                    });
        }
    });

    return (
            <>
                <div className="superfluity">
                <div className="flex" style={{marginBottom: '2rem', paddingTop: '2rem'}}>
                    <HeaderLogo/>
                </div>
                <div className="base-blue " style={{marginBottom: '2rem'}}>
                    <div className="search">
                        <img src="/images/search.png" alt="search"
                             style={{width: '2rem', height: '2rem', margin: '1rem'}}/>
                        <input className="no-border width" type="text"
                               placeholder="궁금한 기사의 제목을 검색하세요!"
                               value={searchTitle}
                               onChange={searchInput}
                               onKeyUp={searchNews}
                        />
                    </div>
                </div>
                <div style={{display: searchCheck ? "none" : ""}}>

                    <br/>
                    <br/>
                    <div>
                        이슈크림은 다양한 관심사에 맞는 실시간 뉴스를 매일 제공하고 있어요 :)

                    </div>
                    <br/>
                    <br/>
                    <div style={{paddingBottom: "3rem"}}>
                        기사형, 대화형 두 가지 모드로 읽기 쉽게 뉴스레터를 접해보세요 -!
                    </div>
                </div>
                {
                        searchCheck && <div className="mb2">' <span style={{color: "#2196F3"}}>{viewTitle}</span> ' 키워드에 해당하는 검색결과</div>
                }
                {
                        searchCheck && (news == null || news.length == 0 ? <div className="mb2">해당 검색 결과가 없어요 !</div> :
                                <NewsTitle news={news}/>)
                }
                {/*<Footer/>*/}
                </div>
            </>
    );
}

export default Search;