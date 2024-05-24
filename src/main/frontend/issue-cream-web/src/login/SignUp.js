import axios from "axios";

function SignUp(){

    const kakaoLogin = ()=>{
        axios.get("/oauth/signup/kakao");
    }

    return(
            <>
                <img src="/images/kakao_login_medium_narrow.png" onClick={kakaoLogin}/>
            </>
    )
}

export default SignUp;