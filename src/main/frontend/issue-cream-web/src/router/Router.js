import {BrowserRouter, Route, Routes} from "react-router-dom";
import TestProxy from "../test/TestProxy";
import Main from "../page/Main";
import Check from "../login/Check";
import LoginSuccess from "../login/LoginSuccess";
import InitPage from "../page/InitPage";
import UserInfo from "../page/UserInfo";
import Search from "../page/Search";
import NewsDetail from "../page/news/NewsDetail";
import LoginPage from "../page/LoginPage";
import ScrapPage from "../page/ScrapPage";

const Router = () => {
    return (
            <div className="background">
                <div className="center">
                    <BrowserRouter>
                        <Routes>
                            <Route path="/" element={<InitPage/>}/>
                            <Route path="/userInfo" element={<UserInfo/>}/>
                            <Route path="/search" element={<Search/>}/>
                            <Route path="/newsDetail/:id" element={<NewsDetail/>}/>
                            <Route path="/check" element={<Check/>}/>
                            <Route path="/test" element={<TestProxy/>}/>
                            <Route path="/authProcess" element={<LoginSuccess/>}/>
                            <Route path="/main" element={<Main/>}/>
                            <Route path="/main/:category" element={<Main/>}/>
                            <Route path="/userLogin" element={<LoginPage/>}/>
                            <Route path="/scrap" element={<ScrapPage/>}/>
                        </Routes>
                    </BrowserRouter>
                </div>
            </div>
    );
};
/*
* <Route element={<Navbar/>}> */

export default Router;