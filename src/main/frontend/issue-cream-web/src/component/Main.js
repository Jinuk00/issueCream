import {Link} from "react-router-dom";

function Main() {
    return (
            <>
                <div>메인</div>
                <div>
                    <Link to='/test'>테스트</Link>
                </div>
                <div>
                    <Link to='/signUp'>회원가입</Link>
                </div>
            </>
    );
}

export default Main;