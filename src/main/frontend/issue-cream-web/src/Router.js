import {BrowserRouter, Route, Routes} from "react-router-dom";
import TestProxy from "./test/TestProxy";
import Main from "./page/Main";
import SignUp from "./login/SignUp";
import Check from "./login/Check";
import LoginSuccess from "./login/LoginSuccess";
import LoginPage from "./page/LoginPage";
import Navbar from "./common/Navbar";
const Router = () => {
    return (
            <>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<LoginPage/>}/>
                    <Route element={<Navbar/>}>
                        <Route path="/main" element={<Main/>}/>
                        <Route path="/test" element={<TestProxy/>}/>
                        <Route path="/login" element={<LoginPage/>}/>
                        <Route path="/signUp" element={<SignUp/>}></Route>
                        <Route path="/authProcess" element={<LoginSuccess/>}></Route>
                        <Route path="/check" element={<Check/>}></Route>
                    </Route>
                </Routes>
            </BrowserRouter>
            </>
    );
};

export default Router;