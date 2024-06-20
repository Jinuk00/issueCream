import {useEffect, useState} from "react";
import {Link, useLocation} from 'react-router-dom';
import axios from "axios";

function Check(){
    const [name, setName] = useState("");

    useEffect(() => {
        axios.post(
            '/api/user/info',{},{
            headers: { 'Content-Type': 'application/json',
                'access':localStorage.getItem("access")},
            withCredentials: true,
        })
                .then((res) => {
                    setName(res.data);
                })
                .catch((error) => {
                })
    }, []);

    return(
            <>
                <div>로그인</div>
                <div>사용자 : {name}</div>
                <div>
                    <Link to="/">메인으로</Link>
                </div>
            </>
    )

}

export default Check;