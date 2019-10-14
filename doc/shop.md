
-------


## 列出所有shop

**url:** /shop/allshop

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
            "description": "好吃",
            "geo": "河北大街"
        }
    ]
}

```


-------


## 删除shop

**url:** /shop/deleteshop

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:

**返回格式:** json

**返回内容:**
```
成功
{
    "msg": "删除成功",
    "code": 0
}
失败:
{
    "msg": "XXXX",
    "code": 1
}

```


-------


## 添加shop

**url:** /shop/addshop

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
username|String|用户名
password|String|密码
repeatpassword|String|重复密码
description|String|店家概述
geo|String|店家位置


**返回格式:** json

**返回内容:**
```

成功
{
    "msg": "添加成功",
    "code": 0,
    "data": [
        {
            "shopid": "1"
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



## 修改shop

**url:** /shop/editshop

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|店家id
description|String|店家描述
geo|String|店家位置

**返回格式:** json

**返回内容:**
```
成功
{
    "msg": "修改成功",
    "code": 0,
}
失败:
{
    "msg": "XXXX",
    "code": 1,
}

```

-------