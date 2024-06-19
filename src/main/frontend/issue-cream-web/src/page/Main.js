import {useParams} from "react-router-dom";
import axios from "axios";
import Navbar from "../common/Navbar";
import {useEffect, useState} from "react";
import NewsTitle from "./news/NewsTitle";
import Footer from "../common/Footer";
import InfiniteScroll from "react-infinite-scroll-component";

function Main() {
    const [news, setNews] = useState([]);
    let {category} = useParams();
    const [categoryNm, setCategoryNm] = useState();
    const [page, setPage] = useState(0);
    const [hasMore, setHasMore] = useState(true);
    useEffect(() => {
        setPage(0);
        setNews([]);
        if (category) {
            setCategoryNm(transCategory);
        } else {
            setCategoryNm('');
        }
        setHasMore(true);
        fetchNews(0);
    }, [category]);

    const fetchNews = async (pageToLoad = page)=>{
        try{
            const response = await axios.post('/api/news/search' + (category ? "/" + category : ""), "page=" + pageToLoad);
            if (response.data.resultCode !== "OK") {
                return;
            }
            const data = response.data.data;
            const newNews = data.content;
            setNews(prevNews => (pageToLoad === 0 ? newNews : [...prevNews, ...newNews]));
            setHasMore(!data.last);
            setPage(pageToLoad + 1);
        }catch(error){
            alert("뉴스를 불러오는 도중 오류가 발생했습니다.");
            setNews([]);
        }
    }

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
                <InfiniteScroll
                        dataLength={news.length}
                        next={fetchNews}
                        hasMore={hasMore}
                        endMessage={<p>더 이상 뉴스가 없습니다</p>}
                >
                    <div>
                        <div className="pb1" style={{marginBottom: '2rem'}}> AI가 하루에 ✌️두 번씩✌️ 뉴스레터를 생성해요</div>
                        <NewsTitle news={news}/>
                    </div>
                </InfiniteScroll>
                <Footer/>
            </>
    );
}

export default Main;