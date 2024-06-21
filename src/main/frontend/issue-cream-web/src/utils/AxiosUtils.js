import axios from "axios";
import {getToken} from "./TokenUtils";

const axiosUtil = axios.create({
    headers: { 'Content-Type': 'application/json',
    'access':getToken()},
    withCredentials: true,
});

export default axiosUtil;
