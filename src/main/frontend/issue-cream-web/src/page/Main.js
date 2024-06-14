import {useParams} from "react-router-dom";
import axios from "axios";
import Navbar from "../common/Navbar";
import {useEffect, useState} from "react";
import NewsTitle from "./news/NewsTitle";
import Footer from "../common/Footer";

function Main() {
    const [news, setNews] = useState([]);
    let {category} = useParams();
    const [categoryNm, setCategoryNm] = useState();
    useEffect(() => {
        if (category) {
            setCategoryNm(transCategory);
        } else {
            setCategoryNm('');
        }
        axios.post('/api/news/search' + (category ? "/" + category : ""))
                .then((res) => {
                    setNews(res.data.data);
                })
                .catch((error) => {
                    setNews()
                });
    }, [category]);

    const transCategory=()=>{
        if (category === "it") {
            return "IT";
        }
        if (category === "preview") {
            return "시사";
        }
        if (category === "media") {
            return "미디어";
        }
        if (category === "sports") {
            return "스포츠";
        }
        if (category === "economy") {
            return "경제";
        }
    }
    return (
        <>
            <Navbar category={categoryNm}/>
            <div>
                <div className="pb1"> AI가 하루에 ✌️두 번씩✌️ 뉴스레터를 생성해요</div>
                <NewsTitle news={news}/>
            </div>
            <Footer/>
        </>
    );
}

export default Main;