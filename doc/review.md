
-------


## 判断今日是否审核

**url:** /review/isreview

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|店铺id

**返回格式:** json

**返回内容:**
```
{
    "msg": "成功",
    "code": 0,
    "data": true
}

```


-------


## 审核操作

**url:** /review/addreview

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|店铺id

**返回格式:** json

**返回内容:**
```
成功
{
    "msg": "审核成功",
    "code": 0,
}
失败:
{
    "msg": "XXXX",
    "code": 1,
}

```


-------
