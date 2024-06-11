import Category from "./Category";
import {Link} from "react-router-dom";

function PageHeader(){
    return (
            <>

                <div className="flex" style={{marginBottom: '2rem', paddingTop: '2rem'}}>
                    <div>
                        <Link to='/search'><img src="/images/search.png" className="mr_auto" alt="search"
                                                style={{width: '2rem', height: '2rem', margin: '1rem'}}/></Link>
                    </div>
                    <img src="/images/headerLogo.png" className="mr_auto" alt="Clova" style={{width: '10rem'}}/>
                    <div>
                        <Link to='/userInfo'><img src="/images/myPage.png" className="mr_auto" alt="myPage     "
                                                style={{width: '1.7rem', height: '1.7rem', margin: '1rem'}}/></Link>
                    </div>
                </div>
            </>
    );
}

export default PageHeader;