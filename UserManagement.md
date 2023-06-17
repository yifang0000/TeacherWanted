# UserManagement 後台使用者管理

![image](https://github.com/yifang0000/TeacherWanted/assets/132047011/24632c06-25c9-4f77-bd1e-882133abf869)


後台使用者角色分為2類：管理員、老師

後台使用者  | 描述
------------- | -------------
管理員  | 可管理老師、老師相關功能，新增優惠券及上架公告
老師  | 可在後台上架課程、商品、活動等


## 後台使用者

```JSON

{
    "adminId": 8,
    "adminAccount": "11",
    "adminPassword": "11",
    "adminName": "測試用管管",
    "adminEmail": "javatha10127@gmail.com",
    "adminPhone": "11",
    "permissionId": 1,
    "adminStatus": 1,
    "createdDate": "2023/06/08 11:00:39",
    "lastUpdatedDate": "2023/06/12 13:41:15"
}

```




## 使用者管理相關API
| URL | method | **Description** |
| :--- | :--- | :--- |
| /administrators | `GET` | 查詢使用者全部列表 |
| /administrators/{adminId} | `GET` |查詢單一使用者|
| /administrators/{adminId}| `PUT` |更新使用者|
| /administrators/{adminId}| `Delete` |刪除使用者|
| /administrators/login| `POST` | 登入 |
| /administrators/session| `GET` | 確認是否有登入session |
| /administrators/logout| `GET` | 登出 |
| /administrators/resetPassword| `POST` | 重設密碼 |

----

### 個別介紹API　(1)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /administrators | `GET` | 查詢使用者全部列表 |  

回應  

| 狀態碼 |  **描述** |
| :--- | :--- |
| 200 | 無論有無使用者皆回傳200|

```JSON 
[
{
"adminId": 26,
"adminAccount": "0620",
"adminPassword": "0620",
"adminName": "啼雀",
"adminEmail": "11111111@gmail.com",
"adminPhone": "0912123123",
"permissionId": 2,
"adminStatus": 1,
"createdDate": null,
"lastUpdatedDate": null
},
{
"adminId": 32,
"adminAccount": "tea007",
"adminPassword": "tea007",
"adminName": "狗狗",
"adminEmail": "javatha10127@gmail.com",
"adminPhone": "0966666666",
"permissionId": 2,
"adminStatus": 1,
"createdDate": "2023/06/12 14:04:54",
"lastUpdatedDate": "2023/06/12 14:10:46"
}
]
```

----

### 個別介紹API　(2)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /administrators/{adminId} | `GET` |查詢單一使用者|

回應  

| 狀態碼 |  **描述** |
| :--- | :--- |
| 200 | 回傳該使用者|
| 404 | 查無該使用者|
```JSON
// 回應 200  
{
"teaId": 26,
"adminId": 26,
"teaProfile": "春天的雀",
"teaPhoto": null
"teaLocation": "台北市",
"subscribeNumber": 1,
"balance": 30000,
"teachingSubject1": 4,
"teachingSubject2": 2,
"teachingSubject3": 3,
"bankCode": "700",
"bankAccount": "12345677654321",
"createTime": "2023/05/09 20:31:01",
"updateTime": "2023/06/04 13:42:42",
"teaName": "啼雀"
}
```
<br>
----

### 個別介紹API　(3)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /administrators/{adminId}| `PUT` |更新使用者|


Request body
| 參數 |  **描述** |
| :--- | :--- |
| adminId | 使用者id|
| adminAccount | 使用者帳號，可以為空|
| adminPassword | 使用者密碼，可以為空|
| adminName | 使用者名稱，可以為空|
| adminEmail | 使用者信箱，可以為空|
| adminPhone | 使用者電話，可以為空|
----

### 個別介紹API　(4)
| URL | method | **Description** |
| :--- | :--- | :---
| /administrators/{adminId}| `Delete` |刪除使用者|

----

### 個別介紹API　(5)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /administrators/login| `POST` | 登入 |

Request body
| 參數 |  **描述** |
| :--- | :--- |
| adminAccount | 使用者帳號|
| adminPassword | 使用者密碼|

Response

| 狀態碼 |  **描述** |
| :--- | :--- |
| 200 | 查有該筆帳號密碼的使用者|
| 400 | 帳號、密碼、使用者狀態，任一錯誤則回傳400，令該使用者不可登入|

----
### 個別介紹API　(6)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /administrators/session| `GET` | 確認是否有登入session |

----

### 個別介紹API　(7)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /administrators/logout| `GET` | 登出 |

----
### 個別介紹API　(8)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /administrators/resetPassword| `POST` | 重設密碼 |

Request body
| 參數 |  **描述** |
| :--- | :--- |
| adminAccount | 使用者帳號|
| adminEmail | 使用者信箱|

Response

| 狀態碼 |  **描述** |
| :--- | :--- |
| 200 | 查有該筆帳號+信箱的使用者|
| 400 | 帳號、信箱，任一錯誤則回傳400，令該使用者不可重設密碼|
