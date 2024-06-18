import Category from "../page/Category";
import PageHeader from "../page/PageHeader";
import IssueBanner from "../page/IssueBanner";
import LatestTopic from "../page/LatestTopic";

function Navbar(props) {

    return (
            <div>
                    <PageHeader/>
                    <IssueBanner/>
                    <LatestTopic/>
                    <Category category={props.category}/>
            </div>
    );
}

export default Navbar;
