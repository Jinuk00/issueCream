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
                <div className="superfluity">
                    <PageHeader/>
                    <div className="pt1 mb2 font2">스크랩한 뉴스레터</div>
                    {
                        (news && news.length!==0) ? <NewsTitle news={news}/> : <div className="p3">스크랩한 뉴스레터가 없습니다.</div>
                    }
                </div>
            </>
    );
}

export default ScrapPage;