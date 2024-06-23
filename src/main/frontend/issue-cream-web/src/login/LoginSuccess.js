import {useEffect} from "react";
import {useLocation, useNavigate} from "react-router-dom";

function LoginSuccess(){
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        const params = new URLSearchParams(location.search);
        const accessToken = params.get("code");
        const now = new Date();
        const authToken ={
            token: accessToken,
            expire: now.getTime() + 60 * 60 * 1000,
        }
        if (accessToken) {
            localStorage.setItem("access", JSON.stringify(authToken));
        }
        navigate("/main");
        window.location.reload();
    });
}

export default LoginSuccess;