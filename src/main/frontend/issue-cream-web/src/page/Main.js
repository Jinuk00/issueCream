import {Link, useNavigate} from "react-router-dom";
import axios from "axios";

function Main() {
    const navigate = useNavigate();

    const logOut = ()=>{
        axios.post('/api/user/logout')
                .then((res)=>{
                    console.log(res);
                    localStorage.removeItem("access");
                    alert("로그아웃 됐습니다.");
                    navigate("/");
                }).catch((error)=>{
            console.log(error);
        })
    }

    return (
            <>
                <div>메인</div>
                <div>
                    <Link to='/test'>테스트</Link>
                </div>
                <div>
                    <Link to='/signUp'>회원가입</Link>
                </div>
                <div>
                    {
                            localStorage.getItem("access") &&
                            <div>
                                <Link to='/check'>내정보</Link>
                            </div>
                    }
                    {
                        localStorage.getItem("access") &&
                        <button onClick={logOut}>
                            로그아웃
                        </button>
                    }
                </div>
            </>
    );
}

export default Main;