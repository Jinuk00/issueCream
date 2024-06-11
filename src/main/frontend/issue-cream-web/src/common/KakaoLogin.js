function KakaoLogin(){
    const kakaoOauthUrl = `${process.env.REACT_APP_BASE}/api/oauth2/authorization/kakao`
    return(
            <>
                <a href={kakaoOauthUrl}>
                    <img src="/images/kakao_login_medium_narrow.png" />
                </a>
                <input type="hidden" value='%REACT_APP_TEST_KEY%'/>
            </>
    )
}

export default KakaoLogin;