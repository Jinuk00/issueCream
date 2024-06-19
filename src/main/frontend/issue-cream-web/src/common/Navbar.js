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
                <PageHeader/>
                <IssueBanner/>
                <LatestTopic topics={hotTopicList}/>
                <Category category={props.category}/>
            </div>
    );
}

export default Navbar;
