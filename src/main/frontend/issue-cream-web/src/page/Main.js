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
                    <div className="pb1"> AI가  하루에 ✌️두 번씩✌️ 뉴스레터를 생성해요</div>
                    {
                        news &&
                        news.map((item,index)=>(
                                <NewsTitle key={index} title={item.newsTitle}/>
                        ))
                    }
                </div>
                <div>
                    <div className="flex bannerStyle2" style={{marginBottom: '2rem'}}>
                        <img src="/images/test_image.png" className="test-image" />
                        <div style={{marginTop: '4rem'}}>
                            AI 기반 실시간 뉴스레터 제공 서비스 ‘이슈크림’은
                        </div>
                    </div>
                </div>
                <div>
                    <Footer/>
                </div>
            </>
    );
}

export default Main;