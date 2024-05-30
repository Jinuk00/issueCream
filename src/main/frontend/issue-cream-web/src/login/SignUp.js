import axios from "axios";

function SignUp(){

    const kakaoLogin = ()=>{
        // axios.get("/oauth/signup/kakao");
        axios.get('/kakao/login')
                .then((res)=>{
                    console.log(res);
                })
    }

    // const kakaoOauthUrl = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${process.env.REACT_APP_OAUTH_KAKAO_API_KEY}&redirect_uri=${process.env.REACT_APP_OAUTH_KAKAO_REDIRECT_URI}`
    // const kakaoOauthUrl = `http://localhost:8080/oauth2/authorization/kakao?redirect_uri=${process.env.REACT_APP_OAUTH_KAKAO_REDIRECT_URI}`
    const kakaoOauthUrl = `http://localhost:8080/oauth2/authorization/kakao`

    return(
            <>
                <a href={kakaoOauthUrl}>
                    <img src="/images/kakao_login_medium_narrow.png" />
                </a>
                <input type="hidden" value='%REACT_APP_TEST_KEY%'/>
            </>
    )
}

export default SignUp;