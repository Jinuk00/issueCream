import {Link, Route, useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import Navbar from "../common/Navbar";
import {useEffect, useState} from "react";
import NewsTitle from "./news/NewsTitle";
import Footer from "../common/Footer";

function Main() {
    const [news, setNews] = useState([]);
    let {category} = useParams();
    const navigate = useNavigate();
    useEffect(() => {
        console.log(category);
        if (category) {
            console.log("들어옴?")
        }
        axios.post('/api/news/search' + (category ? "/" + category : ""))
                .then((res) => {
                    setNews(res.data.data);
                })
                .catch((error) => {
                    setNews()
                });
    }, [category]);

    return (
        <>
            <div>
                <div className="pb1"> AI가 하루에 ✌️두 번씩✌️ 뉴스레터를 생성해요</div>
                <NewsTitle news={news}/>
            </div>
            <div>
                <div className="bannerStyle2" style={{marginBottom: '2rem'}}>
                    <div className="mr1">
                        2024. 05. 11 IT
                    </div>
                    <div className="flex mr1">
                        <img src="/images/test_image.png" className="test-image mr1"/>
                        <div className="mr1 text-center">
                            애플 신제품 가격이 냉장고 값이라구?! 애플 신제품 출시 현장으로 GO GO!
                        </div>
                    </div>
                </div>
            </div>
            <Footer/>
        </>
    );
}

export default Main;