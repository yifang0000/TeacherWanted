# CouponManagement 優惠券管理

![image](https://github.com/yifang0000/TeacherWanted/assets/132047011/9e317aff-7e2a-40b7-b554-97ccc59ee1b8)
![image](https://github.com/yifang0000/TeacherWanted/assets/132047011/bb92fa68-03d7-4cc4-bd83-64083b33788f)


## 優惠券

```JSON

{
"couponId": 1,
"couponCode": "123456",
"couponDetail": "優惠折100",
"discount": "100",
"activateTime": "2023-05-08T16:00:00.000+00:00",
"expirationDate": "2023-11-08T16:00:00.000+00:00"
}

```




## 優惠券管理相關API
| URL | method | **Description** |
| :--- | :--- | :--- |
| /coupons | `GET` | 查詢優惠券全部列表 |
| /coupons/{couponId} | `GET` |查詢單一優惠券|
| /coupons| `POST` |新增優惠券|
| /coupons/{couponId}| `PUT` |更新優惠券|
| /coupons/{couponId}| `Delete` |刪除優惠券|

----

### 個別介紹API　(1)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /coupons | `GET` | 查詢優惠券全部列表 |

回應  

| 狀態碼 |  **描述** |
| :--- | :--- |
| 200 | 無論有無優惠券皆回傳200|

```JSON

[
    {
        "couponId": 1,
        "couponCode": "123456",
        "couponDetail": "優惠折100",
        "discount": "100",
        "activateTime": "2023-05-08T16:00:00.000+00:00",
        "expirationDate": "2023-11-08T16:00:00.000+00:00"
    },
    {
        "couponId": 5,
        "couponCode": "333",
        "couponDetail": "限時優惠折333",
        "discount": "333",
        "activateTime": "2023-06-15T13:20:00.000+00:00",
        "expirationDate": "2023-07-28T13:20:00.000+00:00"
    },
    {
        "couponId": 10,
        "couponCode": "1111",
        "couponDetail": "111",
        "discount": "1111",
        "activateTime": "2023-06-29T21:50:00.000+00:00",
        "expirationDate": "2023-06-30T21:50:00.000+00:00"
    }
]

```
----

### 個別介紹API　(2)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /coupons/{couponId} | `GET` |查詢單一優惠券|

回應  

| 狀態碼 |  **描述** |
| :--- | :--- |
| 200 | 回傳該優惠券|
| 404 | 查無該優惠券|

```JSON

{
    "couponId": 1,
    "couponCode": "123456",
    "couponDetail": "優惠折100",
    "discount": "100",
    "activateTime": "2023-05-08T16:00:00.000+00:00",
    "expirationDate": "2023-11-08T16:00:00.000+00:00"
}

```

### 個別介紹API　(3)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /coupons/{couponId}| `PUT` |新增優惠券|


Request body
| 參數 |  **描述** |
| :--- | :--- |
| couponCode | 優惠券代碼|
| couponDetail | 優惠券文案|
| discount | 折扣金額|
| activateTime | 優惠券開始時間|
| expirationDate | 優惠券結束時間|


----

### 個別介紹API　(4)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /coupons/{couponId}| `PUT` |更新優惠券|


Request body
| 參數 |  **描述** |
| :--- | :--- |
| couponId | 優惠券id|
| couponCode | 優惠券代碼|
| couponDetail | 優惠券文案|
| discount | 折扣金額|
| activateTime | 優惠券開始時間|
| expirationDate | 優惠券結束時間|

----

### 個別介紹API　(5)
| URL | method | **Description** |
| :--- | :--- | :---
| /coupons/{couponId}| `Delete` |刪除優惠券|

----
