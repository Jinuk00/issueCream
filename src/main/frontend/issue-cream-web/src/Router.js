import {BrowserRouter, Route, Routes} from "react-router-dom";
import TestProxy from "./test/TestProxy";
import Main from "./component/Main";
import SignUp from "./login/SignUp";
import Check from "./login/Check";
import LoginSuccess from "./login/LoginSuccess";
const Router = () => {
    return (
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Main/>}/>
                    <Route path="/test" element={<TestProxy/>}/>
                    <Route path="/signUp" element={<SignUp/>}></Route>
                    <Route path="/authProcess" element={<LoginSuccess/>}></Route>
                    <Route path="/check" element={<Check/>}></Route>
                </Routes>
            </BrowserRouter>
    );
};

export default Router;