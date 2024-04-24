import tokenUse from './tokenUse'
const SERVER_ORIGIN = "";

const loginUrl = `${SERVER_ORIGIN}/safe/users/login`;


export const login = (data) => {
    return fetch(loginUrl, {
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
            console.error('There has been a problem with your fetch operation:', error);
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
    return fetch(withdrawURL,{
        method:"POST",
        headers: {
            'Content-Type': 'application/json',
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
            console.log('withdraw successful. Token saved.');
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
const transferURL = `${SERVER_ORIGIN}/safe/transactions/withdraw`;
export const transfer=(data)=>{
    return fetch(transferURL,{
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
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
            console.log('transfer successful. Token saved.');
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

