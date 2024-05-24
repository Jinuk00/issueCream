import {BrowserRouter, Route, Routes} from "react-router-dom";
import TestProxy from "./test/TestProxy";
import Main from "./component/Main";
const Router = () => {
    return (
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Main/>}/>
                    <Route path="/test" element={<TestProxy/>}/>
                </Routes>
            </BrowserRouter>
    );
};

export default Router;