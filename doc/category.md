
-------


## 获得所有一级分类二级别分类

**url:** /getallcategoryand2

**HTTP请求方式:** GET



**返回格式:** json

**返回内容:**

data:为array，每一项id一级分类id，name 一级分类名字
category2s二级分类

category2s为array，一级分类对应的二级分类的列表，
category2id二级分类id，name二级分类名字


```
{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "id": 50,
            "name": "实操类",
            "projects": [
                {
                    "category2id": 49,
                    "name": "美容"
                },
                {
                    "category2id": 50,
                    "name": "美体"
                },
                {
                    "category2id": 51,
                    "name": "仪器"
                }
            ]
        },
        {
            "id": 51,
            "name": "产品类",
            "projects": [
                {
                    "category2id": 52,
                    "name": "卡扣产品"
                },
                {
                    "category2id": 53,
                    "name": "现金产品"
                },
                {
                    "category2id": 54,
                    "name": "赠送产品"
                }
            ]
        },
        {
            "id": 52,
            "name": "现金类",
            "projects": [
                {
                    "category2id": 55,
                    "name": "现金产品"
                },
                {
                    "category2id": 56,
                    "name": "现金卡"
                },
                {
                    "category2id": 57,
                    "name": "现金实操"
                }
            ]
        },
        {
            "id": 53,
            "name": "实耗类",
            "projects": [
                {
                    "category2id": 58,
                    "name": "卡扣产品"
                },
                {
                    "category2id": 59,
                    "name": "卡扣实操"
                },
                {
                    "category2id": 60,
                    "name": "现金产品"
                },
                {
                    "category2id": 61,
                    "name": "现金实操"
                },
                {
                    "category2id": 62,
                    "name": "赠送实操"
                }
            ]
        },
        {
            "id": 54,
            "name": "赠送类",
            "projects": [
                {
                    "category2id": 63,
                    "name": "赠送产品"
                },
                {
                    "category2id": 64,
                    "name": "赠送实操"
                }
            ]
        }
    ]
}

```
