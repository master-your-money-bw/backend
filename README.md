## BW Money API

API for BW Money Build Weeks Project
URL = bw-money-backend.herokuapp.com

Endpoints

- [Create New User](##/createnewuser)
- [Get All Expenses](##expenses/all)
- [Delete Expense By Id](##expenses/delete/{id})
- [Create New Expense](##expenses/new)
- [Update Expense By Id](##expenses/update/{id})
- [Get Expense By Id](##expenses/{id})
- [Logout - no endpoint?](##logout)
- [Login](##oauth/token)
- [Get Current User](######Get_Current_User)
- [Update Current User](###updateUserUsingPUT)
- [Get a List of Users](##users/users)

Models

- [Expense](###Expense)
- [User](###User)

Swagger

- [Extra Documentation](##External)

# Endpoints

##/createnewuser

## HTTP Method: POST

### Expected Response Types

| Response | Reason  |
| -------- | ------- |
| 201      | created |

### Parameters

| Name     | In   | Description | Required? | Type                     |
| -------- | ---- | ----------- | --------- | ------------------------ |
| username | body | username    | true      | [User](#user-definition) |
| password | body | password    | true      | [User](#user-definition) |

### Content Types Consumed

| Consumes         |
| ---------------- |
| application/json |

### Example Request

```javascript
data = {
  username: "someUserName",
  password: "s3cr3tP45Sw0Rd"
};
axios.post("https://bw-money-backend.herokuapp.com/createnewuser", data);
```

---

##expenses/all

### HTTP Method: GET

Return all current user's expenses

### Expected Response Types

| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters

| Name                  | In    | Description                              | Required? | Type   |
| --------------------- | ----- | ---------------------------------------- | --------- | ------ |
| page=0                | query | Results page you want to retrieve (0..N) | false     | object |
| size=0                | query | Number of records per page.              | false     | object |
| sort=expensename,desc | query | Sort by parameter and method             | false     | object |

### Content Types Produced

| Produces         |
| ---------------- |
| application/json |

### Example Request

```javascript
// gets results 11-20
axios.get("https://bw-money-backend.herokuapp.com/expenses/all?size=10&page=2");
```

##expenses/delete/{id}

## HTTP Method: DELETE

Updates expense by ID, returns updated expense

### Expected Response Types

| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Example Request

```javascript
axios.delete("https://bw-money-backend.herokuapp.com/expenses/delete/13");
```

---

##expenses/new

## HTTP Method: POST

Adds new expense to current user

### Expected Response Types

| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters

| Name        | In   | Description | Required? | Type   |
| ----------- | ---- | ----------- | --------- | ------ |
| expensename | body |             | true      | String |
| amount      | body |             | false     | Int    |
| category    | body |             | false     | String |

### Content Types Produced

| Produces         |
| ---------------- |
| application/json |

### Content Types Consumed

| Consumes         |
| ---------------- |
| application/json |

### Example Request

```javascript
data = {
  expensename: "Lunch meeting",
  amount: 3,
  category: "food"
};
axios.post("https://bw-money-backend.herokuapp.com/expenses/new", data);
```

---

##expenses/update/{id}

## HTTP Method: PUT

Updates expense by ID, returns updated expense

### Expected Response Types

| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters

| Name        | In   | Description | Required? | Type   |
| ----------- | ---- | ----------- | --------- | ------ |
| expensename | body |             | true      | String |
| amount      | body |             | false     | Int    |
| category    | body |             | false     | String |

### Content Types Produced

| Produces         |
| ---------------- |
| application/json |

### Content Types Consumed

| Consumes         |
| ---------------- |
| application/json |

### Example Request

```javascript
data = {
  expensename: "Lunch meeting",
  amount: 5,
  category: "food"
};
axios.put("https://bw-money-backend.herokuapp.com/expenses/update/13", data);
```

---

##expenses/{id}

## HTTP Method: GET

Return all expense by ID, checks to make sure user has permission

### Expected Response Types

| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Content Types Produced

| Produces         |
| ---------------- |
| application/json |

### Example Request

```javascript
axios.get("https://bw-money-backend.herokuapp.com/expenses/13");
```

---

##logout

## HTTP Method: GET

logout

### Expected Response Types

| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Content Types Produced

| Produces |
| -------- |
| None     |

---

##oauth/token

## HTTP Method: GET

getAccessToken

### Expected Response Types

| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters

| Name     | In    | Description | Required? | Type   |
| -------- | ----- | ----------- | --------- | ------ |
| username | query |             | true      | string |
| password | query |             | true      | string |

### Content Types Produced

| Produces |
| -------- |
| None     |

### Example Request

```javascript
data = {
  expensename: "Lunch meeting",
  amount: 3,
  category: "food"
};

config = {
  // request will return a 401 without this header
  headers: {
    Authorization: `bearer ${btoa("lambda-client")}:${btoa("lambda-secret")}`
  }
};
axios.get("https://bw-money-backend.herokuapp.com/oauth/token", data, config);
```

---

##passthrough/data

## HTTP Method: POST

Takes JSON, sends it to the Data API, returns the response.

### Expected Response Types

| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters

| Name    | In   | Description | Required? | Type   |
| ------- | ---- | ----------- | --------- | ------ |
| request | body | request     | true      | string |

### Content Types Produced

| Produces         |
| ---------------- |
| application/json |

### Content Types Consumed

| Consumes         |
| ---------------- |
| application/json |

---

##users/currentuser

## HTTP Method: GET

######Get_Current_User
Return current user

### Expected Response Types

| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters

| Name                     | In    | Description | Required? | Type    |
| ------------------------ | ----- | ----------- | --------- | ------- |
| authenticated            | query |             | false     | boolean |
| authorities[0].authority | query |             | false     | string  |
| credentials              | query |             | false     | object  |

### Content Types Produced

| Produces         |
| ---------------- |
| application/json |

### Content Types Consumed

| Consumes |
| -------- |
| None     |

---

##users/currentuser

## HTTP Method: PUT

###updateUserUsingPUT

Updates current user

### Expected Response Types

| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters

| Name                     | In    | Description | Required? | Type    |
| ------------------------ | ----- | ----------- | --------- | ------- |
| authenticated            | query |             | false     | boolean |
| authorities[0].authority | query |             | false     | string  |
| credentials              | query |             | false     | object  |
| details                  | query | updateUser  | true      | object  |

### Content Types Produced

| Produces |
| -------- |
| None     |

### Content Types Consumed

| Consumes         |
| ---------------- |
| application/json |

---

##users/users

## HTTP Method: GET

Return all users, ADMIN ONLY

### Expected Response Types

| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Content Types Produced

| Produces         |
| ---------------- |
| application/json |

---

#Models

###Expense Definition

| Property    | Type    | Format |
| ----------- | ------- | ------ |
| amount      | integer | int32  |
| category    | string  |        |
| expenseid   | integer | int64  |
| expensename | string  |        |

```
  {
    "expenseid": int, //Do not post this, automatically generated
    "expensename": "String",
    "amount": int,
    "category": "String"
  }
```

###User Definition

| Property       | Type    | Format |
| -------------- | ------- | ------ |
| transportation | integer | int32  |
| age            | integer | int32  |
| userid         | integer | int64  |
| password       | string  |        |
| housing        | integer | int32  |
| username       | string  |        |
| clothing       | integer | int32  |
| food           | integer | int32  |
| education      | string  |        |
| location       | string  |        |
| bills          | integer | int32  |
| income         | integer | int32  |

```
{
    "userid": 16, //Do not post this, automatically generated
    "username": "String",
    "location": "String",
    "age": int,
    "income": int,
    "education": "String",
    "transportation": int,
    "food": int,
    "clothing": int,
    "bills": int,
    "housing": int
  }
```

##External Documentation

https://bw-money-backend.herokuapp.com/swagger-ui.html#/
