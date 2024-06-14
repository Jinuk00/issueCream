import Category from "../page/Category";
import PageHeader from "../page/PageHeader";
import IssueBanner from "../page/IssueBanner";

function Navbar(props) {

    return (
            <div>
                    <PageHeader/>
                    <IssueBanner/>
                    <Category category={props.category}/>
            </div>
    );
}

export default Navbar;
