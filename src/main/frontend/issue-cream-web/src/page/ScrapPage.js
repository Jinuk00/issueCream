import PageHeader from "./PageHeader";
import {useEffect, useState} from "react";
import axiosUtils from "../utils/AxiosUtils";
import NewsTitle from "./news/NewsTitle";
import {useNavigate} from "react-router-dom";

function ScrapPage(){
    const [news, setNews] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        if (!localStorage.getItem("access")) {
            alert("로그인이 필요합니다.");
            navigate('/userLogin');
            window.location.reload();
        }
        axiosUtils.post('/api/scrap/list')
                .then((res) => {
                    if (res.data.resultCode == "OK") {
                        setNews(res.data.data);
                    }
                }).catch((error) => {
        });
    }, []);
    return (
            <>
                <PageHeader/>
                <div>스크랩한 뉴스레터</div>
                {
                    news && <NewsTitle news={news}/>
                }
            </>
    );
}

export default ScrapPage;