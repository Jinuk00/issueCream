import axios from "axios";

const instance = axios.create({
    baseURL:"/api",
    headers: { 'Content-Type': 'application/json',
    'access':localStorage.getItem("access")},
    withCredentials: true,
});
const axiosPost= async (url) => {
    return await axios.post(url);
}
