import HeaderLogo from "../common/HeaderLogo";
import KakaoLogin from "../common/KakaoLogin";

function LoginPage(){
    return (
            <>
                <div className="p3">
                    <HeaderLogo/>
                </div>
                <div className="base-blue p2">
                    <div className="p2">
                        로그인이 필요해요
                    </div>
                    <div className="p2">
                        <KakaoLogin/>
                    </div>
                </div>
                <div className="p3">
                    <br/>
                </div>

            </>
    );
}

export default LoginPage;