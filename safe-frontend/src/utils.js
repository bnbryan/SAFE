import tokenUse from './tokenUse'
const SERVER_ORIGIN = "";

const loginUrl = `${SERVER_ORIGIN}/safe/users/login`;
const getAuthToken = () => localStorage.getItem('token');


export const login = (data) => {
    return fetch(loginUrl, {
        method: "POST",
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json',
            // Authorization header should not be included in a login request
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            console.log(response)
            // Check if the network response was ok
            if (!response.ok) {
                if(response.status===403){
                    throw new Error('Wrong password')
                }
                else {
                    throw new Error('Network response was not ok');
                }
            }
            return response.json(); // Parse the body of the response
        })
        .then(json => {
            console.log(json);
            // Assuming the token is in the 'data' object of the response
            // and you check the status with a 'code' property
            if (json.code === 200 && json.data.token) {
                // Save the token in localStorage
                localStorage.setItem('token', json.data.token);
                console.log('Login successful. Token saved.');
            } else {
                // Handle any situation where the login was not successful
                throw Error(json.message || "Fail to login");
            }
            return json; // Continue with the JSON response
        })
        .catch(error => {
            console.error('There has been a problem with your login operation:', error);
            throw error; // Re-throw the error to make sure the caller is aware of the failure
        });
};

const registerUrl = `${SERVER_ORIGIN}/safe/users/register`;

export const register = (data) => {
    /*const testD = {
        clname: "last name",
        cfname: "first name",
        cemail: "12341@gmail.com",
        cpassword: "1231231",
        securityQuestion: "question",
        answer: "answer",
        valid: "1" // 注意这里如果后端期待一个数字，则不要用引号
    };*/
    console.log(registerUrl)
    return fetch(registerUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    })

        .then(response => {
            console.log(response)

            if (!response.ok) {

                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then((json) => {
            console.log(json)
            if (json.code !== 200) {
                throw Error(json.message || "Fail to register");
            }
            return json;
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
            throw error; // 重新抛出错误，确保调用者知道失败了
        });
};

export const logout = () =>{

}
const passreserURL = `${SERVER_ORIGIN}/safe/users/passreset`;
export const passwordReset = (data)=>{
    return fetch(passreserURL, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            // Authorization header should not be included in a login request
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            console.log(response)
            // Check if the network response was ok
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the body of the response
        })
        .then(json => {
            console.log(json);
            // Assuming the token is in the 'data' object of the response
            // and you check the status with a 'code' property
            if (json.code === 200 ) {
                // Save the token in localStorage
                console.log('Reset successful. Token saved.');
            } else {
                // Handle any situation where the login was not successful
                throw Error(json.message || "Fail to Reset, "+json.msg);
            }
            return json; // Continue with the JSON response
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
            throw error; // Re-throw the error to make sure the caller is aware of the failure
        });

}
const withdrawURL = `${SERVER_ORIGIN}/safe/transactions/withdraw`;
export const withdraw=(data)=>{
    const token = getAuthToken();
    return fetch(withdrawURL,{
        method:"POST",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(data),
        }).then(response=>{
        console.log(response)
        // Check if the network response was ok
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // Parse the body of the response

    }).then(json=>{
        console.log(json);
        // Assuming the token is in the 'data' object of the response
        // and you check the status with a 'code' property
        if (json.code === 200 ) {
            // Save the token in localStorage
            console.log('withdraw success.');
        } else {
            // Handle any situation where the login was not successful
            throw Error(json.message || "Fail to withdraw, "+json.msg);
        }
        return json; // Continue with the JSON response

    }).catch(error=>{
        console.error('There has been a problem with your fetch operation:', error);
        throw error; // Re-throw the error to make sure the caller is aware of the failure

    })

}
const depositURL = `${SERVER_ORIGIN}/safe/transactions/deposit`;
export const deposit=(data)=>{
    const token = getAuthToken();
    return fetch(depositURL,{
        method:"POST",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(data),
    }).then(response=>{
        console.log(response)
        // Check if the network response was ok
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // Parse the body of the response

    }).then(json=>{
        console.log(json);
        // Assuming the token is in the 'data' object of the response
        // and you check the status with a 'code' property
        if (json.code === 200 ) {
            // Save the token in localStorage
            console.log('withdraw success.');
        } else {
            // Handle any situation where the login was not successful
            throw Error(json.message || "Fail to withdraw, "+json.msg);
        }
        return json; // Continue with the JSON response

    }).catch(error=>{
        console.error('There has been a problem with your fetch operation:', error);
        throw error; // Re-throw the error to make sure the caller is aware of the failure

    })

}
const transferURL = `${SERVER_ORIGIN}/safe/transactions/transfer`;
export const transfer=(data)=>{
    console.log(JSON.stringify(data))
    const token = getAuthToken();
    return fetch(transferURL,{
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
            // Authorization header should not be included in a login request
        },
        body: JSON.stringify(data),
    }).then(response=>{
        console.log(response)
        // Check if the network response was ok
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // Parse the body of the response
    }).then(json=>{
        console.log(json);
        // Assuming the token is in the 'data' object of the response
        // and you check the status with a 'code' property
        if (json.code === 200 ) {
            // Save the token in localStorage
            console.log('transfer success.');
        } else {
            // Handle any situation where the login was not successful
            throw Error(json.message || "Fail to tranfer, "+json.msg);
        }
        return json; // Continue with the JSON response
    }).catch(error=>{
        console.error('There has been a problem with your fetch operation:', error);
        throw error; // Re-throw the error to make sure the caller is aware of the failure

    })
}


export const allAccount=(email)=>{
    const token = getAuthToken()
    return fetch(`safe/account/all/${email}`, {
        method: 'GET', // GET请求方法
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
    }).then(response => {
        console.log(response);
        // 检查网络响应是否ok
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // 解析响应体

    }).then(json => {
        console.log(json);
        // 假设你需要检查响应中的某个状态码
        if (json.code === 200) {
            console.log('Fetch accounts success.');
        } else {
            // 处理请求不成功的情况
            throw Error(json.message || "Failed to fetch accounts, " + json.msg);
        }
        return json; // 继续处理响应的JSON数据

    }).catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
        throw error; // 重新抛出错误，确保调用者知道失败
    });
}

const adminLoginURL = `${SERVER_ORIGIN}/safe/admin/login`;

export const adminLogin = (data) => {
    return fetch(adminLoginURL, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            // Authorization header should not be included in a login request
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            console.log(response)
            // Check if the network response was ok
            if (!response.ok) {
                if(response.status===403){
                    throw new Error('Wrong password')
                }
                else {
                    throw new Error('Network response was not ok');
                }
            }
            return response.json(); // Parse the body of the response
        })
        .then(json => {
            console.log(json);
            // Assuming the token is in the 'data' object of the response
            // and you check the status with a 'code' property
            if (json.code === 200 && json.data.token) {
                // Save the token in localStorage
                localStorage.setItem('token', json.data.token);
                console.log('Login successful. Token saved.');
            } else {
                // Handle any situation where the login was not successful
                throw Error(json.message || "Fail to login");
            }
            return json; // Continue with the JSON response
        })
        .catch(error => {
            console.error('There has been a problem with your login operation:', error);
            throw error; // Re-throw the error to make sure the caller is aware of the failure
        });
};
const postAllicationURL = `${SERVER_ORIGIN}/safe/account/app`;
export const postAllication = (data) => {
    const token = getAuthToken()
    return fetch(adminLoginURL, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
            // Authorization header should not be included in a login request
        },
        body: JSON.stringify(data),
    })
        .then(response => {
            console.log(response)
            // Check if the network response was ok
            if (!response.ok) {
                    throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the body of the response
        })
        .then(json => {
            console.log(json);
            // Assuming the token is in the 'data' object of the response
            // and you check the status with a 'code' property
            if (json.code === 200) {
                console.log('Sending application success.');
                // Save the token in localStorage

            } else {
                // Handle any situation where the login was not successful
                throw Error(json.message || "Fail to Sumbit application");
            }
            return json; // Continue with the JSON response
        })
        .catch(error => {
            console.error('There has been a problem with your login operation:', error);
            throw error; // Re-throw the error to make sure the caller is aware of the failure
        });
};

export const allApplication=(id)=>{
    const token = getAuthToken()
    return fetch(`safe/account/app/${id}`, {
        method: 'GET', // GET请求方法
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
    }).then(response => {
        console.log(response);
        // 检查网络响应是否ok
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // 解析响应体

    }).then(json => {
        console.log(json);
        // 假设你需要检查响应中的某个状态码
        if (json.code === 200) {
            console.log('Fetch applications success.');
        } else {
            // 处理请求不成功的情况
            throw Error(json.message || "Failed to fetch applications, " + json.msg);
        }
        return json; // 继续处理响应的JSON数据

    }).catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
        throw error; // 重新抛出错误，确保调用者知道失败
    });
}
export const allActivites=(id)=>{
    const token = getAuthToken()
    return fetch(`safe/account/app/${id}`, {
        method: 'GET', // GET请求方法
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
    }).then(response => {
        console.log(response);
        // 检查网络响应是否ok
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // 解析响应体

    }).then(json => {
        console.log(json);
        // 假设你需要检查响应中的某个状态码
        if (json.code === 200) {
            console.log('Fetch applications success.');
        } else {
            // 处理请求不成功的情况
            throw Error(json.message || "Failed to fetch applications, " + json.msg);
        }
        return json; // 继续处理响应的JSON数据

    }).catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
        throw error; // 重新抛出错误，确保调用者知道失败
    });
}

export const getIdByEmail=(email)=>{
    const token = getAuthToken();
    return fetch(`safe/users/email/${email}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
    }).then(response => {
        if (!response.ok) {
            console.log(response)
            throw new Error('Network response was not ok');

        }

        return response.json();
    }).then(json => {
        if (json.code !== 200) {
            throw new Error(json.message || "Failed to get informations, " + json.msg);
        }
        return json.data;
    }).catch(error => {
        console.error('There has been a problem with your get operation:', error);
        throw error;
    });
}

