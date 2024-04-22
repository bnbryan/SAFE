const SERVER_ORIGIN = "";

const loginUrl = `${SERVER_ORIGIN}/safe/login`;

export const login = (data) => {

    return fetch(loginUrl, {
        method: "POST",
        credentials: "include",
        body: JSON.stringify(data),
    }).then((response) => response.json())
        .then((json) => {
            if (json.code !== 200) { // 然后检查json中的code
                throw Error("Fail to log in");
            }
            return json; // 返回解析后的JSON，以供后续使用
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