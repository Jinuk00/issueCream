import {Link} from "react-router-dom";
import axios from "axios";
import Category from "./Category";
import PageHeader from "./PageHeader";
import Footer from "../common/Footer";

function NewsDetail(){

    return(
        <>
            <PageHeader/>
            <Category/>

            <div style={{marginBottom: '2rem'}}>
                it
            </div>
            <div className="base-blue" style={{marginBottom: '2rem'}}>
                제목
            </div>
            <div className="mr2 ">
                내애플이 새로운 아이패드를 출시 하면서 가격을 너무 높게 책정 했다는 의견이 많이 있어요. 아이패드 프로 13인치 모델은 200만 원에 가깝고, 최고 용량인 2TB 모델은 400만 원에 육박해요.😮
                애플은 새로운 아이패드에 최신 기술을 적용 했기 때문에 가격이 높을 수 밖에 없다고 주장하고 있어요. 예를 들어, 아이패드 프로에는 애플이 자체 개발한 M4 칩이 탑재되어 있는데, 이 칩은 이전 세대 칩인 M1 보다 성능이 더 뛰어나다고 해요. 또 아이패드 프로에는 OLED 디스플레이가 탑재되어 있는데, 이 디스플레이는 기존의 LCD 디스플레이보다 더 밝고 선명하다고 해요.🖥️
                하지만 소비자들은 애플이 가격을 너무 높게 책정 했다고 생각하고 있어요. 특히, 한국에서는 미국보다 가격이 더 비싸게 책정되어 있어서 소비자들의 불만이 크다고 해요. 또, 환율을 너무 높게 적용해서 한국 소비자들이 더 많은 돈을 내야 한다는 불만도 있어요.😠
                애플은 새로운 아이패드를 출시 하면서 다양한 기능을 추가 했어요. 예를 들어, 아이패드 프로에는 애플 펜슬 2세대를 지원하고, 카메라 성능도 향상 되었어요. 또, 아이패드OS 16이 탑재되어 있어서 새로운 기능을 사용할 수 있어요.📱
                새로운 아이패드를 구매하려는 소비자들은 가격과 성능을 꼼꼼히 비교해 보고 자신에게 맞는 제품을 선택하는 것이 중요해요. 또, 애플이 가격을 인하할 가능성도 있기 때문에, 조금 더 기다려 보는 것도 좋은 선택일 수 있어요.💰
            </div>
            <div className="flex ">
                <img src="/images/scrap.png" className="mr2 image-size"/>
                <img src="/images/share.png" className="mr2 image-size"/>
            </div>
            <Footer/>
        </>
    );
}

export default NewsDetail;