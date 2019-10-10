
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


## 标题

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


## 标题

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

