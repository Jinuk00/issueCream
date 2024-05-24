import {useEffect, useState} from "react";
import axios from "axios";


function TestProxy(){
    const [hello, setHello] = useState("");

    useEffect(() => {
        axios.post('/api/test/proxy')
                .then((res) => {
                    setHello(res.data);
                })
    }, []);


    return (
            <div>
                백엔드 데이터2 : {hello}
            </div>
    );
}

export default TestProxy;