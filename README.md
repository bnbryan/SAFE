# SAFE
### Dependencies

JDK: 17

spring-boot: 3.2.3

Maven: 3.9.6

Lombok: 1.18.30

*For more details, see [pom.xml](./safe-server/pom.xml)*

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

#### service

[service code](./safe-server/src/main/java/team/ybj/service)

#### Dao

[mapper class](./safe-server/src/main/java/team/ybj/mappers)

[mapper implementation](./safe-server/src/main/resources/mappers)

[entity class](./safe-server/src/main/java/team/ybj/pojo)

#### controller

[controller code](./safe-server/src/main/java/team/ybj/controller)

#### test

[test directory](./safe-server/src/test/java/team/ybj)

