
-------


## 获得所有商品一级分类

**url:** /brand/getallcategory

**HTTP请求方式:** GET

**请求参数:** 无

**返回格式:** json

**返回内容:**
```

{
    "msg": "成功",
    "code": 0,
    "data": [
        []
    ]
}
```

-------


## 添加一级分类

**url:** /brand/addcategory

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
categoryname|String|品牌的一级分类名称

**返回格式:** json

**返回内容:**
```
成功
id:添加成功后的一级分类的id

{
    "msg": "成功",
    "code": 0,
    "data": {
        "id": 34
    }
}
失败
{
    "msg": "分类已存在",
    "code": 1
}

```


-------


## 删除一级分类

**url:** /brand/deletecategory

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
categoryid|int|一级分类的id

**返回格式:** json

**返回内容:**
```
成功:
{
    "msg": "删除成功",
    "code": 0
}

失败:
{
    "msg": "删除失败",
    "code": 1
}

```


-------


## 添加二级分类

**url:** /brand/addcategory2

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
categoryid|int|一级分类id
category2name|String|二级分类name

**返回格式:** json

**返回内容:**
```
成功
category2id:添加成功后的二级分类id
{
    "msg": "成功",
    "code": 0,
    "data": {
        "category2id": 4
    }
}

```


-------


## 删除二级分类

**url:** /brand/deletecategory2

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
categoryid|int|一级分类id
category2id|int|二级分类id

**返回格式:** json

**返回内容:**
```
成功
{
    "msg": "删除成功",
    "code": 0
}
失败
{
    "msg": "删除失败",
    "code": 1
}
```


-------


## 添加三级分类（具体项目）

**url:** /brand/addproject

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
category2id|int|二级分类id
projecetname|string|三级分类名称

**返回格式:** json

**返回内容:**
```
成功
projectid:返回添加成功的项目id
{
    "msg": "成功",
    "code": 0,
    projectid:123
}
失败
{
    "msg": "添加失败",
    "code": 1
}

```


-------


## 删除三级分类

**url:** /brand/deleteproject

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
projectid|int|三级分类id

**返回格式:** json

**返回内容:**
```
成功
{
    "msg": "删除成功",
    "code": 0
}
失败
{
    "msg": "删除失败",
    "code": 1
}

```

-------


## 标题

**url:** /brand/allproject

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
category2id|int|二级分类id

**返回格式:** json

**返回内容:**
```

allprojects：所有三级分类

{
    "msg": "成功",
    "code": 0,
    "allprojects":[
    {
        "id":1,
        "category2id":"12",
        "name":"XXX"
    },
    {
        "id":1,
        "category2id":"12",
        "name":"XXX"
    }
    ]

}

```

-------


## 为当前登录的品牌添加一个分店商店

**url:** /shop/addshop

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
username|string|用户名
password|string|密码
repeatpassword|string|重复密码
description|string|分店简介
geo|内容|地理位置

**返回格式:** json

**返回内容:**
```
成功
shopid:添加的分店商店的id
{
    "msg": "添加成功",
    "code": 0,
    "data": {
        "shopid": 30
    }
}
失败
{
    "msg": "添加失败",
    "code": 1,
}

```

-------


## 编辑分店

**url:** /shop/editshop

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|分店名称
description|string|分店简介，非必须参数，如不修改保持原参数或者为空
geo|string|分店地理位置，非必须参数，如不修改，保持原来参数或为空

**返回格式:** json

**返回内容:**
```
成功
{
    "msg": "修改成功",
    "code": 0
}
失败
{
    "msg": "修改失败",
    "code": 1
}

```


-------


## 删除品牌的分店

**url:** /shop/deleteshop

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|分店id

**返回格式:** json

**返回内容:**
```
成功
{
    "msg": "删除成功",
    "code": 0
}

失败
{
    "msg": "删除失败",
    "code": 1
}

```
