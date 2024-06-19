import Category from "../page/Category";
import PageHeader from "../page/PageHeader";
import IssueBanner from "../page/IssueBanner";
import LatestTopic from "../page/LatestTopic";
import axios from "axios";
import {useEffect, useState} from "react";

function Navbar(props) {
    const [hotTopicList, setHotTopicList] = useState([]);

    const hotTopics= async ()=>{
        try{
            const response = await axios.post('/api/news/hotTopics');
            if (response.data.resultCode !== "OK") {
                return;
            }
            const data = response.data.data;
            setHotTopicList(data);
        }catch(error){
            console.error(error);
        }
    }
    useEffect(() => {
        hotTopics();
    }, []);
    return (
        <div>
            <div className="sticky-header">
                <PageHeader/>
            </div>
            <IssueBanner/>
            <LatestTopic topics={hotTopicList}/>
            <div className="sticky-category">
                <Category category={props.category}/>
            </div>
        </div>
    );
}

export default Navbar;
