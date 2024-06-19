import HeaderLogo from "../common/HeaderLogo";
import KakaoLogin from "../common/KakaoLogin";

function LoginPage(){
    return (
            <>
                <HeaderLogo/>
                <br/>
                <div className="base-blue">
                    <div>
                        로그인이 필요해요
                    </div>
                    <br/>
                    <KakaoLogin/>
                </div>
            </>
    );
}

export default LoginPage;