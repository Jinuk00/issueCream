import {BrowserRouter, Route, Routes} from "react-router-dom";
import TestProxy from "./test/TestProxy";

const Router = () => {
    return (
            <BrowserRouter>
                <Routes>
                    <Route path="/test" element={<TestProxy/>}/>
                </Routes>
            </BrowserRouter>
    );
};

export default Router;