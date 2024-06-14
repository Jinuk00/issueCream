import {Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import axiosUtils from "../utils/AxiosUtils";
import HeaderLogo from "../common/HeaderLogo";

function UserInfo(){
    const [email, setEmail] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        if (!localStorage.getItem("access")) {
            alert("로그인이 필요합니다.");
            navigate('/api/oauth2/authorization/kakao');
            window.location.reload();
        }
        axios.post('/api/user/info', {}, {
            headers: {
                'Content-Type': 'application/json',
                'access': localStorage.getItem("access")
            },
            withCredentials: true,
        }).then((res) => {
            setEmail(res.data);
        }).catch((error) => {
            console.log(error);
        });
    }, []);

    const logout = ()=>{
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

    const deleteUser=()=>{
        axiosUtils.delete("/api/user/info")
                .then((res)=>{
                    console.log(res);
                    alert(res.data.message)
                    if (res.data.resultCode == "OK") {
                        navigate("/");
                    }
                })
    }
    return(
        <>
            <div className="flex" style={{marginBottom: '4rem', paddingTop: '4rem'}}>
                <HeaderLogo/>
            </div>
            <div className="base-blue">
                <div  style={{ padding: '2rem'}}>
                    로그인 정보
                </div>
                <div style={{marginBottom: '2rem'   }}>
                    <img src="/images/profile.png"  className="user-info-size" />
                </div>
                <div style={{ marginBottom: '2rem'}}>
                    카카오톡 아이디: {email}
                </div>
                <div  style={{ paddingBottom: '2rem'}}>
                    스크랩한 뉴스레터:
                </div>
            </div>

            <div className="btn" style={{marginTop: '3rem'}} onClick={logout}>
                로그아웃
            </div>
            <div className="btn" style={{paddingBottom: '2 rem', marginTop: "3rem"}} onClick={deleteUser}>
                탈퇴하기
            </div>
        </>
    );
}

export default UserInfo;