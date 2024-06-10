import {BrowserRouter, Route, Routes} from "react-router-dom";
import TestProxy from "./test/TestProxy";
import Main from "./component/Main";
import SignUp from "./login/SignUp";
import Check from "./login/Check";
import LoginSuccess from "./login/LoginSuccess";
import NavBar from "./common/NavBar";
import LoginPage from "./login/LoginPage";
const Router = () => {
    return (
            <>
            {/*<NavBar/>*/}
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Main/>}/>
                    <Route path="/test" element={<TestProxy/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/signUp" element={<SignUp/>}></Route>
                    <Route path="/authProcess" element={<LoginSuccess/>}></Route>
                    <Route path="/check" element={<Check/>}></Route>
                </Routes>
            </BrowserRouter>
            </>
    );
};

export default Router;