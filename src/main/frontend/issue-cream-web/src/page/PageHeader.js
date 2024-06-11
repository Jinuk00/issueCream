import Category from "./Category";

function PageHeader(){
    return (
            <>

                <div className="flex" style={{marginBottom: '2rem',paddingTop: '2rem'}}>
                    <img src="/images/search.png" className="mr_auto" alt="Clova" style={{width: '2rem', height:'2rem', margin:'1rem'}}/>
                    <img src="/images/headerLogo.png" className="mr_auto" alt="Clova" style={{width: '150px'}}/>
                    <img src="/images/myPage.png" className="mr_auto" alt="Clova" style={{width: '1.7rem',height:'1.7rem', margin:'1rem'}}/>
                </div>
            </>
    );
}

export default PageHeader;