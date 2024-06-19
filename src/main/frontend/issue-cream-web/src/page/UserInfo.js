import {Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import axiosUtils from "../utils/AxiosUtils";
import HeaderLogo from "../common/HeaderLogo";

function UserInfo() {
    const [email, setEmail] = useState("");
    const navigate = useNavigate();
    const [bookmarkCnt, setBookmarkCnt] = useState(0);
    useEffect(() => {
        if (!localStorage.getItem("access")) {
            alert("로그인이 필요합니다.");
            navigate('/userLogin');
            window.location.reload();
        }
        axios.post('/api/user/info', {}, {
            headers: {
                'Content-Type': 'application/json',
                'access': localStorage.getItem("access")
            },
            withCredentials: true,
        }).then((res) => {
            if (res.data.resultCode == "OK") {
                setEmail(res.data.data.email);
                setBookmarkCnt(res.data.data.scrapCnt);
            }
        }).catch((error) => {
            console.log(error);
        });
    }, []);

    const logout = () => {
        axiosUtils.post("/api/user/logout")
            .then((res) => {
                console.log(res);
                if (res.status == "200") {
                    localStorage.removeItem("access");
                    alert("로그아웃 됐습니다.");
                    navigate("/");
                }
            }).catch((error) => {
        });
    }

    const deleteUser = () => {
        axiosUtils.delete("/api/user/info")
            .then((res) => {
                console.log(res);
                alert(res.data.message)
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
                <div style={{padding: '2rem'}}>
                    로그인 정보
                </div>
                <div style={{marginBottom: '2rem'}}>
                    <img src="/images/profile.png" className="user-info-size"/>
                </div>
                <div style={{marginBottom: '2rem'}}>
                    카카오톡 아이디: {email}
                </div>
                <div style={{paddingBottom: '2rem'}}>
                    스크랩한 뉴스레터: <span onClick={gotoScrapPage}>{bookmarkCnt}</span> 개
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