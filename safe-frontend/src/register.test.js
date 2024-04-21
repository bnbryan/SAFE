import { register } from './utils'; // 从你的文件导入注册函数
import fetchMock from 'jest-fetch-mock';

fetchMock.enableMocks();

beforeEach(() => {
    fetch.resetMocks(); // 在每个测试前重置模拟
});

const registerUrl = "http://localhost:8080/safe/users"; // 假设这是的注册URL

describe('Register API Tests', () => {
    it('successfully registers a new user', async () => {
        const mockData = {
            "cname": "test2",
            "cfname": "test2f",
            "cemail": "157@gmail.com",
            "cpassword": "12345",
            "securityQuestion": "test question",
            "securityAnswer": "test",
            "cvalid": "1"
        };
        const expectResult = {
            code: 200,
            msg: "Register success",
            data: 1
        };
        fetch.mockResponseOnce(JSON.stringify(expectResult));

        await expect(register(mockData)).resolves
            .toEqual(expectResult)

    });

    it('fails to register a user with invalid data', async () => {
        const mockData = {
            "cname": "",
            "cfname": "",
            // ... remaining data
        };

        fetch.mockResponseOnce(JSON.stringify({ code: 400, msg: "Register failed", data: 1 }), { status: 400 });

        await expect(register(mockData)).rejects.toThrow("Fail to register");

        expect(fetch).toHaveBeenCalledWith(registerUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(mockData),
        });
    });
});
