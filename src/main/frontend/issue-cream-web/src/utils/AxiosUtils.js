import axios from "axios";

const axiosUtil = axios.create({
    headers: { 'Content-Type': 'application/json',
    'access':localStorage.getItem("access")},
    withCredentials: true,
});
const axiosPost= async (url) => {
    return await axios.post(url);
}


export default axiosUtil;
