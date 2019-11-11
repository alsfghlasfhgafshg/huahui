
-------


## 列出所有staff

**url:** /staff/allstaff

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
page|int|页数,每页10个,可选参数默认值1

**返回格式:** json

**返回内容:**
```
data为array
{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "birthday": "1991年10月16日",
            "address": "秦皇岛开发区",
            "role": "顾问",
            "nation": "汉族",
            "employment": true,
            "phone": "15242525986",
            "healthy": "健康",
            "name": "臧三",
            "emergencyphone": "15242525986",
            "id": 1,
            "nativeplace": "秦皇岛",
            "male": 0,
            "party": "中共党员"
        },
        {
            "birthday": "1949年10月01日",
            "address": "秦皇岛",
            "nation": "汉族",
            "phone": "13833238038",
            "healthy": "良好",
            "name": "胡志军",
            "emergencyphone": "15500225822",
            "id": 162,
            "nativeplace": "中国",
            "employment": true,
            "male": 0,
            "party": "团员"
        }
    ]
}

```


-------


## 删除staff

**url:** /staff/deletestaff

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
staffid|int|员工id

**返回格式:** json

**返回内容:**
```
成功
{
    "msg": "删除成功",
    "code": 0,
}
失败:
{
    "msg": "XXXX",
    "code": 1,
}

```


-------


## 添加staff

**url:** /staff/addstaff

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
username|String|用户名
employment|boolean|是否在职
male|int|员工性别
birthday|String|员工生日
nation|String|员工籍贯
party|String|政治面貌
healthy|String|健康状况
nativeplace|String|籍贯
address|String|现住址
phone|String|电话
emergencyphone|String|紧急电话
p1name|String|家庭成员1姓名
p1male|int|家庭成员1性别
p1company|String|家庭成员1工作单位
p1relationship|String|家庭成员1与本人关系
p2name|String|家庭成员2姓名
p2male|int|家庭成员2性别
p2company|String|家庭成员2工作单位
p2relationship|String|家庭成员2与本人关系
role|String|员工角色("consultant","beautician")



**返回格式:** json

**返回内容:**
```

成功
{
    "msg": "创建成功",
    "code": 0,
}
失败:
{
    "msg": "XXXX",
    "code": 1,
}

```

-------


## 获取一个员工的详细信息

**url:** /staff/getonestaff

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
id|int|id

**返回格式:** json

**返回内容:**
```
成功
{
    "msg": "成功",
    "code": 0,
    "data": {
        "staffid": 162,
        "avatar": null,
        "name": "胡志军",
        "employment": true,
        "male": 0,
        "birthday": "1949-10-01T00:00:00.000+0000",
        "nation": "汉族",
        "party": "团员",
        "healthy": "良好",
        "nativeplace": "中国",
        "address": "秦皇岛",
        "phone": "13833238038",
        "emergencyphone": "15500225822",
        "p1name": "father",
        "p1male": 0,
        "p1company": "公司",
        "p1relationship": "父子",
        "p2name": "无",
        "p2male": 0,
        "p2company": "无",
        "p2relationship": "无",
        "role": "美容师",
        "shopid": 12
    }
}
失败:
{
    "msg": "XXXX",
    "code": 1,
}

```


-------


## 修改staff

**url:** /staff/editstaff

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
staffid|int|用户id
username|String|姓名
male|int|员工性别
employment|boolean|是否在职
birthday|String|员工生日
nation|String|员工民族
party|String|政治面貌
healthy|String|健康状况
nativeplace|String|籍贯
address|String|现住址
phone|String|电话
emergencyphone|String|紧急电话
p1name|String|家庭成员1姓名
p1male|int|家庭成员1性别
p1company|String|家庭成员1工作单位
p1relationship|String|家庭成员1与本人关系
p2name|String|家庭成员2姓名
p2male|int|家庭成员2性别
p2company|String|家庭成员2工作单位
p2relationship|String|家庭成员2与本人关系
role|String|员工角色
shopid|int|店家的id

**返回格式:** json

**返回内容:**
```
成功
{
    "msg": "modify success",
    "code": 0,
}
失败:
{
    "msg": "XXXX",
    "code": 1,
}

```

-------




## 标题

**url:** /staff/getworkstatus

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
staffid|String|店员id
date|String|日期yyyy-dd-mm格式

**返回格式:** json

**返回内容:**
```
{
    "msg": "成功",
    "code": 0,
    "data": {
        "recordingservice": true
        "remindcustomers": true
        "returningcustomers" :true
        "servicenote": true
    }
}

```

-------




## 标记员工工作日志完成

**url:** /staff/todaywork

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
staffid|String|店员id
recordingservice|bool|提醒客户（可选）
remindcustomers|bool|提醒客户（可选）
returningcustomers|bool|回访客户（可选）
servicenote|bool|服务备注（可选）


**返回格式:** json

**返回内容:**
```
{
    "msg": "成功",
    "code": 0,
    "data": {
        "recordingservice": true
        "remindcustomers": true
        "returningcustomers" :true
        "servicenote": true
    }
}

```


-------


## 本店所有顾问，下拉选择顾问时候用

**url:** /staff/allconsultant

**HTTP请求方式:** GET

**请求参数:**


**返回格式:** json

**返回内容:**
```


name 名字，staffid ：顾问id



{"msg":"成功",
"code":0,
"data":[
    {"name":"consultant33","staffid":31},
    {"name":"java11","staffid":32}
]
}

```


-------


## 本店所有美容师，下拉选择顾问时候用

**url:** /staff/allbeautician

**HTTP请求方式:** GET

**请求参数:**


**返回格式:** json

**返回内容:**
```

name 名字，staffid ：美容师id

{"msg":"成功",
"code":0,
"data":[
    {"name":"consultant33","staffid":31},
    {"name":"java11","staffid":32}
]
}

```

