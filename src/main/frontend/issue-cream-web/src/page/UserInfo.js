import {Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import axiosUtils from "../utils/AxiosUtils";

function UserInfo(){
    const [email, setEmail] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
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
        })
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
    return(
        <>
            <div className="flex" style={{marginBottom: '4rem', paddingTop: '4rem'}}>
                <img src="/images/headerLogo.png" className="mr_auto" alt="Clova" style={{width: '15rem'}}/>
            </div>
            <div className="base-blue">
                <div  style={{marginBottom: '2rem'}}>
                    로그인 정보

                    이미지
                </div>
                <div>
                    카카오톡 아이디: {email}
                </div>
                <div>
                    스크랩한 뉴스레터:
                </div>
            </div>

            <div className="btn" style={{marginTop: '5rem'}} onClick={logout}>
                로그아웃
            </div>

            <div className="flex" style={{paddingBottom: '1rem', marginTop: "3rem"}}>
                탈퇴하기
            </div>
        </>
    );
}

export default UserInfo;