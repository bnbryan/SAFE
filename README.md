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

#### GET /users/email/{email}

​	Response

success

```json
{
    "code": 200,
    "msg": "OK",
    "data": {
        "customer": {
            "cid": 1,
            "clname": "Smith",
            "cfname": "John",
            "cemail": "123@gmail.com"
        }
    }
}
```

fail

```json
{
    "code": 422,
    "msg": "exception handler: no data exception",
    "data": "Can't find customer by email: 478677878@gmail.com"
}
```



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
    "clname":"first name",
    "cfname":"last name",
    "cemail":"email address",
    "cpassword":"password",
    "securityQuestion":"question",
    "answer":"answer",
    "valid":1,
    "adstreet":"street",
    "adcity":"city",
    "adstate":"state",
    "adapt":"apt",
    "adzip":12332
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

#### GET /users/record

```
email=123@gmail.com
```

Response

```
{
    "code": 200,
    "msg": "Query success",
    "data": [
        {
            "rid": 1,
            "anum": 1,
            "toanum": 2,
            "ratype": "C",
            "ramount": 1500.0,
            "rtime": "2023-09-01T14:30:00"
        },
        {
            "rid": 6,
            "anum": null,
            "toanum": 1,
            "ratype": "C",
            "ramount": 10.0,
            "rtime": "2024-04-27T10:06:47"
        }
    ]
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



#### POST /transactions/deposit

​	Request

```json
{
    "accountNum":1,
    "amount": 100.00
}
```

​	Response

```json
{
    "code": 200,
    "msg": "deposit success",
    "data": 2400.0
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
            "anum": 22,
            "aname": "Emily Checking",
            "date": "2024-03-06T05:00:00.000+00:00",
            "atype": "C",
            "cid": 2,
            "adid": 2,
            "balance": 6000.0,
            "rate": null,
            "charge": 30.0,
            "loanType": null
        },
        {
            "anum": 16,
            "aname": "Emily Loan",
            "date": "2024-01-20T05:00:00.000+00:00",
            "atype": "L",
            "cid": 2,
            "adid": 2,
            "balance": 130000.0,
            "rate": 3.5,
            "charge": null,
            "loanType": "HOME"
        },
        {
            "anum": 2,
            "aname": "Emily Savings",
            "date": "2023-02-01T05:00:00.000+00:00",
            "atype": "S",
            "cid": 2,
            "adid": 2,
            "balance": 52020.0,
            "rate": 1.5,
            "charge": null,
            "loanType": null
        }
    ]
}
```



#### Get /account/app/{cid}

​	Response

​	success

```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "userApps": [
            {
                "appId": 1,
                "type": "C",
                "status": null
            }
        ]
    }
}
```

​	fail

```json
{
    "code": 422,
    "msg": "exception handler: no data exception",
    "data": "No active applications found"
}
```



#### POST /account/app

Request

```json
{
    "cid": 8,
    "type": "C",
    "income": 10000.00,
    "career": "student"
}
```

​	Response

​	success

```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "appId": 14
    }
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

​	success

```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjEiLCJpYXQiOjE3MTM5ODcyNTIsImV4cCI6MTcxMzk5MDg1Mn0.si92KHsc60Ho5QIzt08Lh1Af-KdeQXZZyEPbAy1FoLE"
    }
}
```

#### POST admin/app/reject

​	Request

```json
{
    "appId":2
}
```

​	Response

```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "rejectedApp": 2
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

