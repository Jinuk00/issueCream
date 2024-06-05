import {Link} from "react-router-dom";
import axios from "axios";

function Main() {

    const logOut = ()=>{
        axios.post("/api/logout")
                .then((res)=>{
                    console.log(res);
                    // navigator("/",{replace: true});
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
                <button onClick={logOut}>
                    로그아웃
                </button>
            </>
    );
}

export default Main;