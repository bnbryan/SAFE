import fetchMock from 'jest-fetch-mock';
import { login } from './utils'; // 引入您的登录函数

fetchMock.enableMocks();

beforeEach(() => {
    fetchMock.resetMocks();
});

const loginUrl = "http://localhost:8080/safe/login"; // 您的登录API的URL

describe('Login API function', () => {
    it('successfully logs in a user and receives a token', async () => {
        // 模拟成功的API响应
        const mockSuccessResponse = {
            code: 200,
            msg: "login success",
            data: {
                token: "someTokenString"
            }
        };

        fetchMock.mockResponseOnce(JSON.stringify(mockSuccessResponse), { status: 200 });

        // 登录动作
        await expect(login({ cemail: "123@gmail.com", cpassword: "12345" }))
            .resolves.toEqual({
                code: 200,
                msg: "login success",
                data: {
                    token: "someTokenString"
                }
            });

    });

    it('fails to log in a user with incorrect credentials', async () => {
        // 模拟失败的API响应
        const mockFailResponse = {
            code: 401, // 假设401为登录失败的状态码
            msg: "login failed",
            data: {}
        };

        fetchMock.mockResponseOnce(JSON.stringify(mockFailResponse), { status: 401 });

        // 期望登录函数抛出错误
        await expect(login({ cemail: "wrong@gmail.com", cpassword: "wrong" })).rejects.toThrow("Fail to log in");

        // 确认fetch被正确调用
        expect(fetch).toHaveBeenCalledWith(loginUrl, {
            method: "POST",
            credentials: "include",
            body: JSON.stringify({ cemail: "wrong@gmail.com", cpassword: "wrong" }),
        });
    });
});
