import axios from "axios";

const axiosUtil = axios.create({
    headers: { 'Content-Type': 'application/json',
    'access':localStorage.getItem("access")},
    withCredentials: true,
});


export default axiosUtil;
