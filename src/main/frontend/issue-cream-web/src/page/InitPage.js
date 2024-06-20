import KakaoLogin from "../common/KakaoLogin";
import {Link} from "react-router-dom";
import React from "react";

function InitPage(){

    return(
      <>
                <div style={{paddingTop:'6rem'}}>
                  <img src="/images/clova.png" alt = "Clova" style={{ width: '7rem' }}/>
              </div>
              <div style={{marginTop:'4rem'}}>
                  <img src="/images/main_logo2.png" alt="Logo" style={{ width: '15rem' }} />
              </div>
              <div style={{marginTop:'4rem'}}>
                  AI 기반 실시간 뉴스레터 제공 서비스 '이슈크림'
              </div>
              <div style={{marginTop:"5rem"}}>
                  <KakaoLogin/>
              </div>
              <div  className="font1" style={{marginTop:"1rem", paddingBottom:"8rem"}}>
                  <Link to='/main'>
                      <img src="/images/start.png" className="image-size3" />
                  </Link>
              </div>
      </>
    );
}

export default InitPage;