import './App.css';
import Router from "./router/Router";
import {useEffect} from "react";
import {getToken} from "./utils/TokenUtils";

function App() {
    // axios.defaults.withCredentials = true; // withCredentials 전역 설정
    useEffect(() => {
        getToken();
    }, []);
    return (
            <>
                <Router/>
            </>
    );
}

export default App;
