
-------


## 设置网站标题

**url:** /setting/websitename

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
name|String|网站名

**返回格式:** json

**返回内容:**
```
{
    "msg": "成功",
    "code": 0
}


```


-------


## 查询网站名

**url:** /setting/websitename

**HTTP请求方式:** GET

**请求参数:**


**返回格式:** json

**返回内容:**

name 网站名
```
{
    "msg": "成功",
    "code": 0,
    "data": {
        "name": "asdfasdfasdfas"
    }
}

```


-------


## 标题

**url:** /status

**HTTP请求方式:** GET

**请求参数:**


**返回格式:** json

**返回内容:**
```


{
    "msg": "成功",
    "code": 0,
    "data": {
        "shopcount": 9,
        "brandcount": 21
    }
}

```
