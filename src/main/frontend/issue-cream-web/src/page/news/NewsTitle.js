function NewsTitle(props) {
    return (
            <>
                {
                        props.news &&
                        props.news.map((item, index) => (
                                <div className="border" key={index}>
                                    <div className="flex" style={{height: "3rem"}}>
                                        <div style={{paddingRight:"1rem"}}>이미지 자리 </div>
                                        <div>{item.newsTitle}</div>
                                    </div>
                                </div>
                        ))
                }
            </>
    );
}

export default NewsTitle;