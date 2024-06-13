import Navbar from "../common/Navbar";
import {Route, Routes} from "react-router-dom";
import Main from "../page/Main";
import LoginPage from "../page/LoginPage";
import Check from "../login/Check";

function MainNavPage(){
    return(
            <>
                <Navbar/>
                <Routes>
                    <Route path="/main" element={<Main/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    {/*<Route path="/signUp" element={<SignUp/>}/>*/}
                    <Route path="/main/{}" element={<Check/>}/>
                </Routes>
            </>
    )
}

export default MainNavPage;