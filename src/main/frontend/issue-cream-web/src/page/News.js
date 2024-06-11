import {Link} from "react-router-dom";
import axios from "axios";
import Category from "./Category";
import PageHeader from "./PageHeader";

function News(){

    return(
        <>
            <PageHeader/>
            <Category/>

        </>
    );
}

export default News;