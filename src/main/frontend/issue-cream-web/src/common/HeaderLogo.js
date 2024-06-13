import {useNavigate} from "react-router-dom";

function HeaderLogo(){
    const navigate = useNavigate();
    const gotoMain=()=>{
        console.log("메인클릭");
        navigate("/main");
    }
    return(
            <img src="/images/headerLogo.png" className="mr_auto" style={{width: '10rem'}} onClick={gotoMain}/>

    )
}

export default HeaderLogo;