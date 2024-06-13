import {BrowserRouter, Route, Routes} from "react-router-dom";
import TestProxy from "../test/TestProxy";
import Main from "../page/Main";
import SignUp from "../login/SignUp";
import Check from "../login/Check";
import LoginSuccess from "../login/LoginSuccess";
import LoginPage from "../page/LoginPage";
import Navbar from "../common/Navbar";
import UserInfo from "../page/UserInfo";
import Search from "../page/Search";
import News from "../page/News";
import MainNavPage from "./MainNavPage";

const Router = () => {
    return (
            <div className="background">
                <div className="center">
                    <BrowserRouter>
                        <Routes>
                            <Route path="/" element={<LoginPage/>}/>
                            <Route path="/userInfo" element={<UserInfo/>}/>
                            <Route path="/search" element={<Search/>}/>
                            <Route path="/news" element={<News/>}/>
                            <Route path="/check" element={<Check/>}/>
                            <Route path="/test" element={<TestProxy/>}/>
                            <Route path="/authProcess" element={<LoginSuccess/>}/>
                            <Route path="/*" element={<MainNavPage/>}/>
                            {/*    <Route path="/main" element={<Main/>}/>*/}
                            {/*    <Route path="/login" element={<LoginPage/>}/>*/}
                            {/*    /!*<Route path="/signUp" element={<SignUp/>}/>*!/*/}
                            {/*    <Route path="/main/{}" element={<Check/>}/>*/}
                            {/*</Route>*/}
                        </Routes>
                    </BrowserRouter>
                </div>
            </div>
    );
};
/*
* <Route element={<Navbar/>}> */

export default Router;