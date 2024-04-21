const SERVER_ORIGIN = "http://localhost:8080";

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

const registerUrl = `${SERVER_ORIGIN}/safe/users`;

export const register = (data) => {
    return fetch(registerUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((response) => response.json())
        .then((json) => {
            if (json.code !== 200) { // 然后检查json中的code
                throw Error("Fail to register");
            }
            return json; // 返回解析后的JSON，以供后续使用
        });
};

export const logout = () =>{

}