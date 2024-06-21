import {Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import axiosUtils from "../utils/AxiosUtils";
import HeaderLogo from "../common/HeaderLogo";
import {getToken} from "../utils/TokenUtils";

function UserInfo() {
    const [email, setEmail] = useState("");
    const navigate = useNavigate();
    useEffect(() => {
        if (!getToken()) {
            alert("로그인이 필요합니다.");
            navigate('/userLogin');
        }
        axiosUtils.post('/api/user/info')
                .then((res) => {
                    if (res.data.resultCode == "OK") {
                        setEmail(res.data.data.email);
                    }
                }).catch((error) => {
        });
    }, []);

    const logout = () => {
        axiosUtils.post("/api/user/logout")
            .then((res) => {
                if (res.status == "200") {
                    localStorage.removeItem("access");
                    alert("로그아웃 됐습니다.");
                    navigate("/");
                }
            }).catch((error) => {
            localStorage.removeItem("access");
        });
    }

    const deleteUser = () => {
        axiosUtils.delete("/api/user/info")
            .then((res) => {
                alert(res.data.message)
                localStorage.removeItem("access");
                if (res.data.resultCode == "OK") {
                    navigate("/");
                }
            })
    }

    const gotoScrapPage=()=>{
        navigate('/scrap')
    }
    return (
        <>
            <div className="flex" style={{marginBottom: '4rem', paddingTop: '4rem'}}>
                <HeaderLogo/>
            </div>
            <div className="base-blue">
                <div className="font2" style={{padding: '2rem'}}>
                    로그인 정보
                </div>
                <div style={{marginBottom: '2rem'}}>
                    <img src="/images/profile2.png" className="user-info-size"/>
                </div>
                <div style={{marginBottom: '2rem'}}>
                    카카오톡 아이디: {email}
                </div>
                <div style={{paddingBottom: '2rem'}}>
                    <span className=" btn selectCategory4" onClick={gotoScrapPage}>스크랩한 뉴스레터 바로가기</span>
                </div>
            </div>

            <div>
                <div className="btn selectCategory2" style={{marginTop: '3rem',}} onClick={logout}>
                    로그아웃
                </div>
            </div>
            <div>
                <div className="btn selectCategory2" style={{marginBottom: '3rem', marginTop: "3rem"}}
                     onClick={deleteUser}>
                    탈퇴하기
                </div>
            </div>
        </>
    );
}

export default UserInfo;