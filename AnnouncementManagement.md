# AnnouncementManagement 公告管理

![image](https://github.com/yifang0000/TeacherWanted/assets/132047011/35499286-ab84-406d-bc10-abe9a64796e2)
![image](https://github.com/yifang0000/TeacherWanted/assets/132047011/2c1b6ccd-f159-4b37-bb67-1c4a31c7cd92)
![image](https://github.com/yifang0000/TeacherWanted/assets/132047011/637a1ccc-f345-409e-b555-6efbde49cca7)


## 公告

```JSON

{
"annId": 14,
"adminId": 8,
"annTitle": "06/29~06/30優惠券",
"annCategory": 1,
"annContent": "優惠代碼：1111<br>有效期間：2023/06/29 13:50:00~2023/06/30 13:50:00<br>折扣金額：1111<br>11111111111111111111<br>1111",
"annDate": "2023/06/12 13:50:16",
"annStatus": 1
}

```




## 公告管理相關API
| URL | method | **Description** |
| :--- | :--- | :--- |
| /announcements | `GET` | 查詢公告全部列表 |
| /announcements/{annId} | `GET` |查詢單一公告|
| /announcements| `POST` |新增公告|
| /announcements/{annId}| `PUT` |更新公告|
| /announcements/{annId}| `Delete` |刪除公告|

----

### 個別介紹API　(1)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /announcements | `GET` | 查詢公告全部列表 |

Parameters
Path
| 參數 |型態 | **描述** |
| :--- | :--- |:--- |
| annCategory | String|公告類別|
| front |boolean |是否為前台公告?|


回應  

| 狀態碼 |  **描述** |
| :--- | :--- |
| 200 | 無論有無公告皆回傳200|

```JSON

[
    {
        "annId": 14,
        "adminId": 8,
        "annTitle": "06/29~06/30優惠券",
        "annCategory": 1,
        "annContent": "優惠代碼：1111<br>有效期間：2023/06/29 13:50:00~2023/06/30 13:50:00<br>折扣金額：1111<br>11111111111111111111<br>1111",
        "annDate": "2023/06/12 13:50:16",
        "annStatus": 1
    },
    {
        "annId": 16,
        "adminId": 8,
        "annTitle": "新上架課程",
        "annCategory": 2,
        "annContent": "新上架課程",
        "annDate": "2023/06/12 13:56:25",
        "annStatus": 1
    },
    {
        "annId": 17,
        "adminId": 8,
        "annTitle": "9月新上架課程",
        "annCategory": 2,
        "annContent": "新上架課程2",
        "annDate": "2023/09/01 21:31:00",
        "annStatus": 1
    }
]


```
----

### 個別介紹API　(2)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /announcements/{annId} | `GET` |查詢單一公告|

回應  

| 狀態碼 |  **描述** |
| :--- | :--- |
| 200 | 回傳該公告|
| 404 | 查無該公告|

```JSON

{
"annId": 14,
"adminId": 8,
"annTitle": "06/29~06/30優惠券",
"annCategory": 1,
"annContent": "優惠代碼：1111<br>有效期間：2023/06/29 13:50:00~2023/06/30 13:50:00<br>折扣金額：1111<br>11111111111111111111<br>1111",
"annDate": "2023/06/12 13:50:16",
"annStatus": 1
}


```

----

### 個別介紹API　(3)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /announcements| `POST` |新增公告|


Request body
| 參數 |  **描述** |
| :--- | :--- |
| adminId | 管理員ID|
| annTitle |公告標題|
| annCategory |公告類別|
| annContent |公告內容|
| annDate |公告上架日期|
| annStatus |公告狀態|


----

### 個別介紹API　(4)
| URL | method | **Description** |
| :--- | :--- | :--- |
| /announcements/{annId}| `PUT` |更新公告|


Request body
| 參數 |  **描述** |
| :--- | :--- |
| annId | 公告ID|
| adminId | 管理員ID|
| annTitle |公告標題|
| annCategory |公告類別|
| annContent |公告內容|
| annDate |公告上架日期|
| annStatus |公告狀態|

```JSON
{
"annId": 14,
"adminId": 8,
"annTitle": "06/29~06/30優惠券",
"annCategory": 1,
"annContent": "優惠代碼：1111<br>有效期間：2023/06/29 13:50:00~2023/06/30 13:50:00<br>折扣金額：1111<br>11111111111111111111<br>1111",
"annDate": "2023/06/12 13:50:16",
"annStatus": 1
}
```

----

### 個別介紹API　(5)
| URL | method | **Description** |
| :--- | :--- | :---|
| /announcements/{annId}| `Delete` |刪除公告|

----
