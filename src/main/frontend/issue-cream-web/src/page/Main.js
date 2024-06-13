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
            <Footer/>
        </>
    );
}

export default Main;