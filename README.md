## BW Money API
API for BW Money Build Weeks Project

# Endpoints
## __/createnewuser__
## HTTP Method: POST

### Expected Response Types
| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters
| Name    | In   | Description | Required? | Type                     |
| ------- | ---- | ----------- | --------- | ------------------------ |
| username | body | username     | true      | [User](#user-definition) |
| password | body | password     | true      | [User](#user-definition) |

### Content Types Produced
| Produces         |
| ---------------- |
| application/json |

### Content Types Consumed
| Consumes         |
| ---------------- |
| application/json |

---

## __expenses/all__
### HTTP Method: GET
Return all current user's expenses

### Expected Response Types
| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters
| Name                     | In    | Description                             | Required? | Type    |
| ------------------------ | ----- | --------------------------------------- | --------- | ------- |
| page=0                 | query | Results page you want to retrieve (0..N)  | false     | object  |
| size=0                 | query | Number of records per page.               | false     | object  |
| sort=expensename,desc  | query | Sort by parameter and method              | false     | object  |

### Content Types Produced
| Produces         |
| ---------------- |
| application/json |

## expenses/delete/{id}
## HTTP Method: DELETE
Updates expense by ID, returns updated expense

### Expected Response Types
| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Content Types Produced
| Produces |
| -------- |
| None     |

---

## __expenses/new__
## HTTP Method: POST
Adds new expense to current user

### Expected Response Types
| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters
| Name                     | In    | Description | Required? | Type                           |
| ------------------------ | ----- | ----------- | --------- | ------------------------------ |
| expensename              | body  |             | true      | String                         |
| amount                   | body  |             | false     | Int                            |
| category                 | body  |             | false     | String                         |

### Content Types Produced
| Produces         |
| ---------------- |
| application/json |

### Content Types Consumed
| Consumes         |
| ---------------- |
| application/json |

---

## __expenses/update/{id}__
## HTTP Method: PUT
Updates expense by ID, returns updated expense

### Expected Response Types
| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters
| Name                     | In    | Description | Required? | Type                           |
| ------------------------ | ----- | ----------- | --------- | ------------------------------ |
| expensename              | body  |             | true      | String                         |
| amount                   | body  |             | false     | Int                            |
| category                 | body  |             | false     | String                         |

### Content Types Produced
| Produces         |
| ---------------- |
| application/json |

### Content Types Consumed
| Consumes         |
| ---------------- |
| application/json |

---

## __expenses/{id}__
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

### Content Types Consumed
| Consumes |
| -------- |
| None     |

---

## logout
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

## __oauth/token__
## HTTP Method: GET
getAccessToken

### Expected Response Types
| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters
| Name       | In    | Description | Required? | Type   |
| ---------- | ----- | ----------- | --------- | ------ |
| username   | query |             | true      | string |
| password   | query |             | true      | string |

### Content Types Produced
| Produces |
| -------- |
| None     |

---

## __/oauth/token__
## HTTP Method: POST
postAccessToken

### Expected Response Types
| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters
| Name       | In    | Description | Required? | Type   |
| ---------- | ----- | ----------- | --------- | ------ |
| name       | query |             | false     | string |

### Content Types Produced
| Produces |
| -------- |
| None     |

### Content Types Consumed
| Consumes         |
| ---------------- |
| application/json |

---

## __passthrough/data__
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

## __users/currentuser__
## HTTP Method: GET
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

## __users/currentuser__
## HTTP Method: PUT
### updateUserUsingPUT
Updates current user

### Expected Response Types
| Response | Reason |
| -------- | ------ |
| 200      | OK     |

### Parameters
| Name                     | In    | Description | Required? | Type                                    |
| ------------------------ | ----- | ----------- | --------- | --------------------------------------- |
| authenticated            | query |             | false     | boolean                                 |
| authorities[0].authority | query |             | false     | string                                  |
| credentials              | query |             | false     | object                                  |
| details                  | query | updateUser  | true      | object                                  |

### Content Types Produced
| Produces |
| -------- |
| None     |

### Content Types Consumed
| Consumes         |
| ---------------- |
| application/json |

---

## __users/users__
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

# __Models__
### __Expense Definition__
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

### __User Definition__
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

## External Documentation
https://bw-money-backend.herokuapp.com/swagger-ui.html#/