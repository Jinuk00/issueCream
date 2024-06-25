

export const getToken = () => {
    const itemStr = localStorage.getItem('access');
    // 토큰이 존재하지 않으면 null 반환
    if (!itemStr) {
        return null;
    }

    const item = JSON.parse(itemStr);
    const now = new Date();

    // 만료 시간이 지나면 토큰 삭제
    if (now.getTime() > item.expire) {
        console.log("삭제");
        localStorage.removeItem('access');
        return null;
    }

    return item.token;
};