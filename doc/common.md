# 公用


所有操作Cookies 认证用户
-----

### Json返回格式:
```
code为结果值,成功永远为0.失败为其他
成功,数据放到data中

{
    "msg": "成功",
    "code": 0,
    "data": {
        "XXX": "XXX",
        "XXXX": "XXXX"
    }
}

失败,失败原因放到msg中

{
    "msg": "用户名或密码错误",
    "code": 1
}

```
----
## 所有必须填写的参数为空时返回:
```

{
    "msg": "输入参数不能为空",
    "code": -1
}
```


-----

## 修改密码
所有用户通用,修改当前已经的登录的账户的密码

**url:** /user/changepssword

**HTTP请求方式:** POST application/x-www-form-urlencoded


**请求参数:**

参数名称|类型|描述
---|:--:|---:
passwd|String|新密码
repeatpasswd|String|重复新密码

**返回格式:** json

**返回内容:**
```
成功:
{
    "msg": "修改成功",
    "code": 0
}
失败:
{
    "msg": "失败原因",
    "code": 1
}

```


-------


## 退出登录

**url:** /logout

**HTTP请求方式:** POST

**请求参数:** 无


**返回格式:** json

**返回内容:**
```
{
    "msg": "ok",
    "code": 0
}
```

