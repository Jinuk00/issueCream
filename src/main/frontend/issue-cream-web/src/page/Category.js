import {Link} from "react-router-dom";

function Category(props){
    return (
            <div className="flex ">
                <div className="base-blue width pb1 pb3 categoryBorder" style={{marginBottom: '2rem'}}>
                    <Link to={"/main/it"} className={`link category ${props.category === 'IT' ? "selectCategory" : ''}`}>IT</Link>
                    <Link to={"/main/preview"} className={`link category ${props.category === '시사' ? "selectCategory" : ''}`}>시사</Link>
                    <Link to={"/main/media"} className={`link category ${props.category === '미디어' ? "selectCategory" : ''}`}>미디어</Link>
                    <Link to={"/main/sports"} className={`link category ${props.category === '스포츠' ? "selectCategory" : ''}`}>스포츠</Link>
                    <Link to={"/main/economy"} className={`link category ${props.category === '경제' ? "selectCategory" : ''}`}>경제</Link>
                </div>
            </div>
    );
}

export default Category;