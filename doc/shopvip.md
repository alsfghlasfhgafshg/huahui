
-------


## 列出所有会员

**url:** /shopvip/all

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
page|int|页数,每页10个,可选参数默认值1

**返回格式:** json

**返回内容:**
```
{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "vipname": "vipname",
            "vipnumber":"68641554354",
            "male": 0,
            "age":21,
            "telephone",1562655616,
            "isnew",0,
            "consultant","dewitt",
            "beautician","dewitt"
        }
    ]
}

```


-------


## 删除会员

**url:** /shopvip/delete

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
vipid|int|要删除的会员id

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


## 添加会员

**url:** /shopvip/add

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
vipname|String|会员名
vipnumber|String|会员码
male|int|性别(0男1女)
age|int|年龄
telephone|String|电话
shopid|int|店铺id
consultant|int|顾问id
beautician|int|美容师id

**返回格式:** json

**返回内容:**
```

成功
{
    "msg": "添加成功",
    "code": 0,
}
失败:
{
    "msg": "XXXX",
    "code": 1,
}

```

-------


## 按姓名查找会员

**url:** /shopvip/find

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
vipname|String|会员名

**返回格式:** json

**返回内容:**
```
{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "vipname": "vipname",
            "vipnumber":"54556447112",
            "male": 0,
            "age":21,
            "telephone",1562655616,
            "isnew",0,
            "consultant","dewitt",
            "beautician","dewitt"
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
## 将新客改为会员

**url:** /shopvip/change

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
vipname|String|会员名
vipnumber|String|会员码
male|int|性别(0男1女)
age|int|年龄
telephone|String|电话
shopid|int|店铺id
consultant|int|顾问id
beautician|int|美容师id


**返回格式:** json

**返回内容:**
```
{
    "msg": "更改成功",
    "code": 0
}
失败:
{
    "msg": "XXXX",
    "code": 1,
}

```


-------