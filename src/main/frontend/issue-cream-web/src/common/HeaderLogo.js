import {useNavigate} from "react-router-dom";

function HeaderLogo(){
    const navigate = useNavigate();
    const gotoMain=()=>{
        navigate("/main");
    }
    return(
            <img src="/images/headerLogo.png" className="mr_auto btn" style={{width: '10rem'}} onClick={gotoMain}/>

    )
}

export default HeaderLogo;