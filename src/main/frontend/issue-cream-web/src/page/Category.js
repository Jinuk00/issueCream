import {Link} from "react-router-dom";

function Category(){
    return (
        <div className="flex">
            <div className="base-blue mr_auto">
                <Link to={"/category/it"} className="link category">IT</Link>
                <Link to={"/category/it"} className="link category">시사</Link>
                <Link to={"/category/it"} className="link category">미디어</Link>
                <Link to={"/category/it"} className="link category">스포츠</Link>
                <Link to={"/category/it"} className="link category">경제</Link>
            </div>
        </div>
    );
}

export default Category;