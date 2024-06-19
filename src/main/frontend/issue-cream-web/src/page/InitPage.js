import KakaoLogin from "../common/KakaoLogin";
import {Link} from "react-router-dom";

function InitPage(){

    return(
      <>
                <div style={{paddingTop:'6rem'}}>
                  <img src="/images/clova.png" alt = "Clova" style={{ width: '18rem' }}/>
              </div>
              <div style={{marginTop:'4rem'}}>
                  <img src="/images/main_logo.png" alt="Logo" style={{ width: '18rem' }} />
              </div>
              <div style={{marginTop:'4rem'}}>
                  AI 기반 실시간 뉴스레터 제공 서비스 '이슈크림'
              </div>
              <div style={{marginTop:"5rem"}}>
                  <KakaoLogin/>
              </div>
              <div  style={{marginTop:"3rem", paddingBottom:"6rem"}}>
                  <Link to='/main' style={{ background:'#CCECFF', paddingLeft:"25px", paddingRight:"25px" , paddingTop:"13px", paddingBottom:"13px",borderRadius: '5px', cursor: "pointer", textDecoration:"none",color:"#000000"}}>
                      로그인 없이 시작하기
                  </Link>
              </div>
      </>
    );
}

export default InitPage;