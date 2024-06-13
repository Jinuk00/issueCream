function NewsTitle(props) {
    return (
            <div className="border">
                <div className="flex" style={{height:"3rem"}}>
                    <div>img</div>
                    <div>{props.title}</div>
                </div>
            </div>
    );
}

export default NewsTitle;