
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
            "name": "dewitt",
            "male": 0,
            "birthday":"1998-02-21",
            "nation":"中国",
            "party":"共产党",
            "healthy":"健康",
            "nativeplace":"河北",
            "address","海港区52号",
            "phone","15603382139",
            "emergencyphone","15603382139",
            "role","beautician"
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
avatar|MultipartFile|用户头像
name|String|姓名
male|int|员工性别
birthday|Date|员工生日
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

**url:** /staff/{id}

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:

**返回格式:** json

**返回内容:**
```
成功
{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "staffid":"1",
            "avatar","XXXX",
            "name": "dewitt",
            "male": 0,
            "birthday":"1998-02-21",
            "nation":"中国",
            "party":"共产党",
            "healthy":"健康",
            "nativeplace":"河北",
            "address","海港区52号",
            "phone","15603382139",
            "emergencyphone","15603382139",
            "p1name","p1name",
            "p1male","0",
            "p1company","公司",
            "p1relationship","父子",
            "p2name","p2name",
            "p2male","0",
            "p2company","公司",
            "p2relationship","父子",
            "role","beautician"
        }
    ]
}
失败:
{
    "msg": "XXXX",
    "code": 1,
}

```


-------


## 修改staff

**url:** /staff/editstaff/{staffid}

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
avatar|MultipartFile|用户头像
name|String|姓名
male|int|员工性别
birthday|Date|员工生日
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




## 标题

**url:** /staff/StaffController

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
