import Navbar from "../common/Navbar";
import {Route, Routes} from "react-router-dom";
import Main from "../page/Main";
import InitPage from "../page/InitPage";
import Check from "../login/Check";

function MainNavPage(){
    return(
            <>
                <Routes>
                    <Route path="/main" element={<Main/>}/>
                    <Route path="/main/:category" element={<Main/>}/>
                    {/*<Route path="/signUp" element={<SignUp/>}/>*/}
                    {/*<Route path="/main/{}" element={<Check/>}/>*/}
                </Routes>
            </>
    )
}

export default MainNavPage;