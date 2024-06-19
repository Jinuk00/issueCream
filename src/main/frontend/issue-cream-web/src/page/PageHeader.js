import Category from "./Category";
import {Link, useNavigate} from "react-router-dom";
import HeaderLogo from "../common/HeaderLogo";

function PageHeader(){
    const navigate = useNavigate();
    const gotoMain=()=>{
        console.log("메인클릭");
        navigate("/main");
    }
    return (
            <>

                <div className="flex" style={{marginBottom: '2rem', paddingTop: '2rem' }}>
                    <div>
                        <Link to='/search'><img src="/images/search.png" className="mr_auto" alt="search"
                                                style={{width: '2rem', height: '2rem', margin: '1rem'}}/></Link>
                    </div>
                    <HeaderLogo/>
                    <div>
                        <Link to='/userInfo'><img src="/images/myPage.png" className="mr_auto" alt="myPage     "
                                                style={{width: '1.7rem', height: '1.7rem', margin: '1rem'}}/></Link>
                    </div>
                </div>
            </>
    );
}

export default PageHeader;