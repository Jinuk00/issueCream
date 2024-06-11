import KakaoLogin from "../common/KakaoLogin";

function LoginPage(){
    return(
      <>
          <div className="center">
              <div style={{marginTop:'5rem'}}>
                  <img src="/images/clova.png"/>
              </div>
              <div style={{marginTop:'5rem'}}>
                  <img src="/images/main_logo.png" />
              </div>
              <div style={{marginTop:'5rem'}}>
                  AI 기반 실시간 뉴스레터 제공 서비스 '이슈크림'
              </div>
              <div style={{marginTop:"10rem"}}>
                  <KakaoLogin/>
              </div>
              <div className="mr2">
                  <span style={{background:'#CCECFF', padding:"0.9rem",borderRadius: '5px', cursor: "pointer"}}>
                      로그인 정보 없이 시작하기
                  </span>
              </div>
          </div>
      </>
    );
}

export default LoginPage;