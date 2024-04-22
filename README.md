# SAFE

### Description

Port: 8080

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

