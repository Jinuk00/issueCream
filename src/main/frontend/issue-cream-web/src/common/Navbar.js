import Category from "../page/Category";
import PageHeader from "../page/PageHeader";
import IssueBanner from "../page/IssueBanner";

function Navbar() {

    return (
            <>

                <div>
                    <PageHeader/>
                    <IssueBanner/>
                    <Category/>
                </div>
            </>
    );
}

export default Navbar;
