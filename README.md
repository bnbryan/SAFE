# SAFE

### Description

Port: 8443

[Start Server](./safe-server/src/main/java/team/ybj/Main.java)

---

### Dependencies

JDK: 17

spring-boot: 3.2.3

Maven: 3.9.6

Lombok: 1.18.30

*For more details, see [pom.xml](./safe-server/pom.xml)*

---

### API

#### POST /users/login

​	*Request*

```json
{
    "cemail":"email",
    "cpassword":"password"
}
```

​	*Response*

```json
{
    "code": 200,
    "msg": "login success",
    "data": {
        "token": "token"
    }
}
```



#### POST /users/register

​	Request

```json
{
    "clname":"last name",
    "cfname":"first name",
    "cemail":"email address",
    "cpassword":"password",
    "securityQuestion":"question",
    "answer":"answer",
    "valid":1
}
```



#### POST /users/passreset

​	Request

```json
{
    "cemail":"email address",
    "cpassword":"password",
    "securityQuestion":"question",
    "answer":"answer",
}
```

​	Response

```
S:
{
    "code": 200,
    "msg": "Password reset success",
    "data": 1
}
F:
{
    "code": 400,
    "msg": "No Account Found",
    "data": 0
}
{
    "code": 400,
    "msg": "Wrong security question or answer",
    "data": 0
}
{
    "code": 400,
    "msg": "Something went wrong when trying to reset password",
    "data": 0
}
```

#### POST /users/passreset

```
{
	"cemail":"email"
}
```



#### POST /transactions/transfer

​	Request

```json
{
    "fromAccountNum":"From Account Number",
    "fromAccountType":"Account Type/ C or S",
    "toAccountNum":"To Account Number",
    "amount": "money to transfer"
}
```

​	Response

```json
{
  
  
}
```

#### POST /transactions/withdraw

Checking and saving all use this one.

​	Request

```json
{

    "anum":anum,
    "atype":"type",
    "abalance":balance

}
```

​	Response

```
success:
{
	"code": 200,
    "msg": "withdraw success",
    "data": 100.00 						//the new balance
}
Fail:
{
	"code": 400,
    "msg": "Something went wrong when withdrawing",
    "data": 0
}
{
	"code": 400,
    "msg": "Balance not enough",
    "data": 0
}
```



#### GET /account/all/{email}

​	Response

```json
{
    "code": 200,
    "msg": "success",
    "data": [
        {
            "anum": 1,
            "aname": "John Checking",
            "adate": "2023-01-01T05:00:00.000+00:00",
            "atype": "C",
            "cid": 1,
            "adid": 1
        },
        {
            "anum": 15,
            "aname": "John Savings",
            "adate": "2024-01-01T05:00:00.000+00:00",
            "atype": "S",
            "cid": 1,
            "adid": 1
        }
    ]
}
```



#### POST admin/login

​	Request

```json
{
    "username": "admin1",
    "password": "123"
}
```

​	Response

```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjEiLCJpYXQiOjE3MTM5ODcyNTIsImV4cCI6MTcxMzk5MDg1Mn0.si92KHsc60Ho5QIzt08Lh1Af-KdeQXZZyEPbAy1FoLE"
    }
}
```





---



### Branch Creation

##### Naming

1. feature

   ​	feature/feature-name

2. bug fixing

   ​	fix/bug-description

3. docs

   ​	docs/documentation-description

---

### CI/CD

Add test for every functions you wrote.

Ensure all tests are passed before merging to the master.

---

### Project Structure

#### controller

[controller code](./safe-server/src/main/java/team/ybj/controller)

#### service

[service code](./safe-server/src/main/java/team/ybj/service)

#### Dao

[mapper class](./safe-server/src/main/java/team/ybj/mappers)

[mapper implementation](./safe-server/src/main/resources/mappers)

[entity class](./safe-server/src/main/java/team/ybj/pojo)

#### test

[test directory](./safe-server/src/test/java/team/ybj)

