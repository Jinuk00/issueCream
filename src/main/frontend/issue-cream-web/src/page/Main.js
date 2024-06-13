import {Link, Route, useNavigate} from "react-router-dom";
import axios from "axios";
import Navbar from "../common/Navbar";
import {useEffect, useState} from "react";
import NewsTitle from "./news/NewsTitle";

function Main() {
    const [news, setNews] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        axios.post('/api/news/search')
                .then((res) => {
                    setNews(res.data.data);
                })
                .catch((error) => {
                });
    }, []);

    return (
            <>
                <div>
                    <div className="pb1"> AI가  하루에 ✌️두 번씩✌️ 뉴스레터를 생성해요</div>
                    {
                        news.map((item,index)=>(
                                <NewsTitle key={index} title={item.newsTitle}/>
                        ))
                    }
                </div>
            </>
    );
}

export default Main;