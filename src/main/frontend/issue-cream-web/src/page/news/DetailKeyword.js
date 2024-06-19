function DetailKeyword(props){
    return(
            <>
            {
                props.keyword &&
                <div className="flex hashtag">
                    <div>
                        {props.keyword}
                    </div>
                </div>
            }
            </>
    )
}

export default DetailKeyword;