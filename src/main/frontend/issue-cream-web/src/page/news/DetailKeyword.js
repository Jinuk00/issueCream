function DetailKeyword(props){
    return(
            <>
            {
                props.keyword &&
                <div className="flex hashtag mr6 ">
                    <div>
                        {props.keyword}
                    </div>
                </div>
            }
            </>
    )
}

export default DetailKeyword;