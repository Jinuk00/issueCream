import {useEffect} from "react";
import {useLocation, useNavigate} from "react-router-dom";

function LoginSuccess(){
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        const params = new URLSearchParams(location.search);
        const accessToken = params.get("code");

        if (accessToken) {
            localStorage.setItem("access", accessToken);
        }
        navigate("/main");
    });
}

export default LoginSuccess;