
# 统计
### 需要为品牌或者商店，品牌能查看所属商店的统计信息，商店只能查看本商店的所属信息，url格式为/statistics/1，1为excel对应的第一张表，2为excel对应的第二张表

### 开始时间结束时间为yyyy-mm-dd格式，



## 统计第一张表的标题

**url:** /statistics/1

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|可选参数，如果为商店，此参数不填写，为品牌，填写商店的id
starttime|string|起始统计时间  yyyy-mm-dd格式
endtime|string|终止统计时间 yyyy-mm-dd格式

**返回格式:** json

**返回内容:**
```
productProjectSumPriceAndCount 返回商品类信息
 projectname商品名称，sumprice总销售金额，countprojectname总销售数量
beautyProjectSumPriceAndCount 和bodyProjectSumPriceAndCount同样

格式
{
    "msg": "成功",
    "code": 0,
    "data": {
        "productProjectSumPriceAndCount": [
            {
                "projectname": "project1",
                "sumprice": 25,
                "countprojectname": 2
            },
            {
                "projectname": "project2",
                "sumprice": 45,
                "countprojectname": 1
            }
        ],
        "beautyProjectSumPriceAndCount": [],
        "bodyProjectSumPriceAndCount": []
    }
}

```

## 统计第二张表的标题

**url:** /statistics/2

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|可选参数，如果为商店，此参数不填写，为品牌，填写商店的id
starttime|string|起始统计时间  yyyy-mm-dd格式
endtime|string|终止统计时间 yyyy-mm-dd格式


**返回格式:** json

**返回内容:**
```

5times 到点5次的人数
4times 到点4次的人数
....

countCustomerTimes为每个顾客到店次数，arraylist
"count": 1,数量
"customername": "dsf" 顾客名

countCustomerPrice每个顾客消费总数
countprice消费金额
customername顾客名称

返回示例
{
    "msg": "成功",
    "code": 0,
    "data": {
        "2times": 0,
        "4times": 0,
        "1times": 3,
        "countCustomerTimes": [
            {
                "count": 1,
                "customername": "dsf"
            },
            {
                "count": 1,
                "customername": "112"
            },
            {
                "count": 1,
                "customername": "11"
            }
        ],
        "countCustomerPrice": [
            {
                "countprice": 45,
                "customername": "11"
            },
            {
                "countprice": 25,
                "customername": "dsf"
            }
        ],
        "5times": 0,
        "3times": 0
    }
}
```



-------


## 第三个excel

**url:** /statistics/3

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|可选参数，如果为商店，此参数不填写，为品牌，填写商店的id
starttime|string|起始统计时间  yyyy-mm-dd格式
endtime|string|终止统计时间 yyyy-mm-dd格式

**返回格式:** json

**返回内容:**
```

category2SumCountAndSumPrice为array
category2name二级分类名称
sumcount总销售量
sumprice总销售金额

{
    "msg": "成功",
    "code": 0,
    "data": {
        "category2SumCountAndSumPrice": [
            {
                "sumcount": 1,
                "category2name": "tttttt",
                "sumprice": 45
            },
            {
                "sumcount": 2,
                "category2name": "为杀人犯",
                "sumprice": 25
            }
        ]
    }
}
```



-------
## 第四个excel

**url:** /statistics/4

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|可选参数，如果为商店，此参数不填写，为品牌，填写商店的id
starttime|string|起始统计时间  yyyy-mm-dd格式
endtime|string|终止统计时间 yyyy-mm-dd格式
consultantname|string|顾问姓名

**返回格式:** json

**返回内容:**
```
consultantstatistics为array，顾问对应的销售量和金额

 sumcount 销售量
 category2name项目名
 sumprice销售额

{
    "msg": "成功",
    "code": 0,
    "data": {
        "consultantstatistics": [
            {
                "sumcount": 1,
                "category2name": "tttttt",
                "sumprice": 45
            },
            {
                "sumcount": 2,
                "category2name": "为杀人犯",
                "sumprice": 25
            }
        ]
    }
}


```


-------
## 第5个excel

**url:** /statistics/5

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|可选参数，如果为商店，此参数不填写，为品牌，填写商店的id
starttime|string|起始统计时间  yyyy-mm-dd格式
endtime|string|终止统计时间 yyyy-mm-dd格式
consultantname|string|顾问姓名

**返回格式:** json

**返回内容:**
```
beauticianstatistics为array，顾问对应的销售量和金额

 sumcount 销售量
 category2name项目名
 sumprice销售额

{
    "msg": "成功",
    "code": 0,
    "data": {
        "beauticianstatistics": [
            {
                "countcategory2name": 1,
                "category2name": "tttttt",
                "sumprice": 45
            },
            {
                "countcategory2name": 2,
                "category2name": "为杀人犯",
                "sumprice": 25
            }
        ],
        "guesttraffic": {
            "countcustomername": 3,
            "distinctcustomername": 2,
            "passengerflow": 3.0000
        }
    }
}

```
