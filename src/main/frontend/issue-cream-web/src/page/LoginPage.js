import KakaoLogin from "../common/KakaoLogin";
import {Link} from "react-router-dom";

function LoginPage(){

    return(
      <>
          <div className="background">
          <div className="center">
                <div style={{marginTop:'5rem'}}>
                  <img src="/images/clova.png" alt = "Clova" style={{ width: '300px' }}/>
              </div>
              <div style={{marginTop:'3rem'}}>
                  <img src="/images/main_logo.png" alt="Logo" style={{ width: '300px' }} />
              </div>
              <div style={{marginTop:'5rem'}}>
                  AI 기반 실시간 뉴스레터 제공 서비스 '이슈크림'
              </div>
              <div style={{marginTop:"5rem"}}>
                  <KakaoLogin/>
              </div>
              <div className="mr2">
                  <Link to='/main' style={{background:'#CCECFF', padding:"0.8rem",borderRadius: '5px', cursor: "pointer", textDecoration:"none",color:"#000000"}}>
                      로그인 정보 없이 시작하기
                  </Link>
              </div>
          </div>
          </div>
      </>
    );
}

export default LoginPage;