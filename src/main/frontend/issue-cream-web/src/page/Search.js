import {Link} from "react-router-dom";
import React, { useState } from 'react';
import HeaderLogo from "../common/HeaderLogo";

function Search(){

    const [search, setSearch] = useState('');

    const SearchInput = (event) => {
        setSearch(event.target.value);
    };

    const Search = (event) => {
        event.preventDefault();
        console.log('Searching for:', search);
    };

    return(
        <>
            <div className="flex" style={{marginBottom: '2rem', paddingTop: '2rem'}}>
                <HeaderLogo/>
            </div>
            <div className="base-blue " style={{marginBottom: '2rem'}}>
                <div className="search">
                    <img src="/images/search.png" alt="search"
                         style={{width: '2rem', height: '2rem', margin: '1rem'}}/>
                    <input className="no-border width" type="text"
                           placeholder="궁금한 기사의 제목을 검색하세요!"
                           value={search}
                           onChange={SearchInput}
                        // onKeyUp={}
                    />
                </div>
            </div>
            <div style={{marginTop: '4rem'}}>
                AI 기반 실시간 뉴스레터 제공 서비스 ‘이슈크림’은
            </div>
            <div style={{marginTop: '4rem'}}>
                2024 년 6월부터 서비스를 제공하고 있어요!
            </div>
            <div style={{marginTop: '4rem'}}>
                하루 두번!
            </div>
            <div style={{marginTop: "4rem", paddingBottom: "6rem"}}>
                AI가 자동생성하는 재미있는 뉴스레터를 읽어보세요.
            </div>
            {/*<div>
                해당 검색 결과가 없어요 ! 검색 결과가 없을 시 이걸로 하고 있으면 관련 기사로 이동
            </div>*/}
        </>
    );
}

export default Search;