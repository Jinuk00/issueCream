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
                <div>
                    <img src="/images/search.png" alt="search"
                         style={{width: '2rem', height: '2rem', margin: '1rem'}}/>
                    <input
                        type="text"
                        placeholder="궁금한 기사의 제목을 검색하세요!"
                        value={search}
                        onChange={SearchInput}
                        // onKeyUp={}
                    />
                </div>
            </div>
        </>
    );
}

export default Search;