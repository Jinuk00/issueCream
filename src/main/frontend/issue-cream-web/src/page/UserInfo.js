import {Link} from "react-router-dom";

function UserInfo(){

    return(
        <>
            <div className="flex" style={{marginBottom: '4rem', paddingTop: '4rem'}}>
                <img src="/images/headerLogo.png" className="mr_auto" alt="Clova" style={{width: '15rem'}}/>
            </div>
            <div>
                <div className="base-blue" style={{marginBottom: '2rem'}}>
                    로그인 정보

                    이미지

                    카카오톡 아이디:

                    스크랩한 뉴스레터:
                </div>
            </div>

            <div className="flex" style={{marginTop: '5rem'}}>
                로그아웃
            </div>

            <div className="flex" style={{paddingBottom: '1rem', marginTop: "3rem"}}>
                탈퇴하기
            </div>
        </>
    );
}

export default UserInfo;