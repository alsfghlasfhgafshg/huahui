
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

**url:** /setbgimg

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
img|二进制blob|图片

**返回格式:** json

**返回内容:**
```
bgimg返回设置成功后的img url

{
    "msg": "成功",
    "code": 0,
    "data": {
        "bgimg": "/file/img/2bc433d6-764a-4fa7-9670-bbddd90e86f6.png"
    }
}

```




-------


## 标题

**url:** /bgimg

**HTTP请求方式:** GET

**返回格式:** json

**返回内容:**
```
bgimg返回设置成功后的img url

{
    "msg": "成功",
    "code": 0,
    "data": {
        "bgimg": "/file/img/2bc433d6-764a-4fa7-9670-bbddd90e86f6.png"
    }
}

```



