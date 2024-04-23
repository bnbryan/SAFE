// 保存token
const saveToken = (token) => {
    localStorage.setItem('token', token);
};

// 从存储中获取token
const getToken = () => {
    return localStorage.getItem('token');
};

// 检查token是否有效（简化示例，具体逻辑根据后端的实现）
const isTokenValid = () => {
    const token = getToken();
    // 一些简单的前端检查，例如检查token是否存在，或者是否过期（如果你的token包含过期时间）
    return token != null;
};

// 发送需要认证的请求
const sendAuthenticatedRequest = (url, method, data) => {
    const token = getToken();

    return fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            // 如果token过期或无效，可能会收到401或403状态码
            if (response.status === 401) {
                // 处理token过期/无效
                handleTokenExpiry();
            } else if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .catch(error => {
            console.error('Error:', error);
            throw error;
        });
};

// 处理token过期
const handleTokenExpiry = () => {
    // 清除存储中的token
    localStorage.removeItem('token');
    // 重定向到登录页或显示登录提示
    //redirectToLogin();
};

// 登录成功后，保存token
const onLoginSuccess = (loginResponse) => {
    if(loginResponse.token) {
        saveToken(loginResponse.token);
    }
};
