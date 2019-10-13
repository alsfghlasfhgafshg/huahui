
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
data为array,每一项:admin name ,admin的id
{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "name": "admin",
            "id": 1
        }
    ]
}

```


-------


## 删除admin

**url:** /admin/deladminuser

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
userid|int|要删除的admin用户id

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


## 添加admin

**url:** /admin/addadminuser

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
username|String|用户名
password|String|用户密码
repeatpassword|String|重复用户密码


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


## 标题

**url:** /admin/addbranduser

**HTTP请求方式:** POST enctype=multipart/form-data

**请求参数:**

参数名称|类型|描述
---|:--:|---:
brandname|String|品牌名
brandpasswd|String|密码
repeatbrandpasswd|String|重复密码
description|String|简介
img|blob|头像文件二进制

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


## 删除品牌

**url:** /admin/deletebranduser

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
brandid|int|品牌id

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


## 列出所有品牌

**url:** /brand/allbrand

**HTTP请求方式:** GET

**请求参数:** 无

**返回格式:** json

**返回内容:**
```

data为阿所有品牌的array,每一项:name品牌名,description品牌描述,id品牌id,avatar品牌头像
{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "name": "1111",
            "description": "1111",
            "id": 19,
            "avatar": null
        },
        {
            "name": "qw",
            "description": "esdfgdf",
            "id": 21,
            "avatar": "/file/img/2a32928e-cd81-4f46-8ac2-09b9a93fe89c.jpg"
        }
    ]
}
```


-------


## 获得一个品牌的信息

**url:** /brand/getbrand

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
brandid|int|品牌的id

**返回格式:** json

**返回内容:**
```
{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "name": "1111",
            "description": "1111",
            "avatar": null
        }
    ]
}

```


-------


## 更新品牌信息

**url:** /brand/updatebrandadmin

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
brandid|int|品牌id
description|String|描述
img|blob|头像

**返回格式:** json

**返回内容:**
```
sdfa

```

