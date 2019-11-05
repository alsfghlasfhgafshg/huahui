
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
productProjectSumPriceAndCount 返回商品类统计信息，类型为array
 projectname商品名称，sumprice总销售金额，countprojectname总销售数量

beautyProjectSumPriceAndCount 美容类统计信息，类型为array
 projectname商品名称，sumprice总销售金额，countprojectname总销售数量

bodyProjectSumPriceAndCount 美体类统计信息，类型为array
 projectname商品名称，sumprice总销售金额，countprojectname总销售数量

bodyTotal美体统计 totalcount总数量 总价格totalprice 
beautyTotal美容统计 totalcount总数量 总价格totalprice 
productTotal产品统计 totalcount总数量 总价格totalprice 

格式
{
    "msg": "成功",
    "code": 0,
    "data": {
        "bodyTotal": {
            "totalcount": 0,
            "totalprice": 0
        },
        "productProjectSumPriceAndCount": [],
        "beautyTotal": {
            "totalcount": 0,
            "totalprice": 0
        },
        "beautyProjectSumPriceAndCount": [],
        "bodyProjectSumPriceAndCount": [
            {
                "projectname": "meirong1",
                "sumprice": 25,
                "countprojectname": 2
            },
            {
                "projectname": "meirong2",
                "sumprice": 45,
                "countprojectname": 1
            }
        ],
        "productTotal": {
            "totalcount": 0,
            "totalprice": 0
        }
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


"eq4times": 到店4次的人数
"eq1times": 到店1次的人数
"greateroreq5times": 到店大于等于5次的人数
"eq2times": 到店2次的人数
"eq3times": 到店3次的人数

....

countCustomerTimes为每个顾客到店次数，arraylist
"times": 1,数量
"customername": "dsf" 顾客名

countCustomerPrice每个顾客消费总数
countprice消费金额
customername顾客名称

返回示例
{
    "msg": "成功",
    "code": 0,
    "data": {
        "countCustomerTimes": [
            {
                "times": 2,
                "customername": "c1"
            },
            {
                "times": 1,
                "customername": "c2"
            }
        ],
        "countCustomerPrice": [
            {
                "countprice": 70,
                "customername": "c1"
            }
        ],
        "eq4times": 0,
        "eq1times": 1,
        "greateroreq5times": 0,
        "eq2times": 1,
        "eq3times": 0
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

category2SumCountAndSumPrice为array,
总计也包含在内，总计作为二级分类
category2name二级分类名称
sumcount总销售量
sumprice总销售金额

statistics"包含 人头 客流 平均单价

{
    "msg": "成功",
    "code": 0,
    "data": {
        "category2SumCountAndSumPrice": [
            {
                "sumcount": 0,
                "categoryname": "实操类",
                "category2name": "美容",
                "sumprice": 0
            },
            {
                "sumcount": 3,
                "categoryname": "实操类",
                "category2name": "美体",
                "sumprice": 70
            },
            {
                "sumcount": 0,
                "categoryname": "实操类",
                "category2name": "仪器",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "卡扣产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "现金产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "赠送产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "现金产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "现金卡",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "现金实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "卡扣产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "卡扣实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "现金产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "现金实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "赠送实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "赠送类",
                "category2name": "赠送产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "赠送类",
                "category2name": "赠送实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "总计",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "总计",
                "sumprice": 0
            },
            {
                "sumcount": 3,
                "categoryname": "实操类",
                "category2name": "总计",
                "sumprice": 70
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "总计",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "赠送类",
                "category2name": "总计",
                "sumprice": 0
            }
        ],
        "statistics": {
            "人头": 2,
            "客流": 3,
            "平均单价": 23
        }
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

consultantstatistics 顾问统计,数据类型为array
总计也包含在内，总计作为二级分类
category2name二级分类名称
sumcount总销售量
sumprice总销售金额

statistics"包含 人头 客流 平均单价

{
    "msg": "成功",
    "code": 0,
    "data": {
        "consultantstatistics": [
            {
                "sumcount": 0,
                "categoryname": "实操类",
                "category2name": "美容",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实操类",
                "category2name": "美体",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实操类",
                "category2name": "仪器",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "卡扣产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "现金产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "赠送产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "现金产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "现金卡",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "现金实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "卡扣产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "卡扣实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "现金产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "现金实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "赠送实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "赠送类",
                "category2name": "赠送产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "赠送类",
                "category2name": "赠送实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "总计",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "总计",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实操类",
                "category2name": "总计",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "总计",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "赠送类",
                "category2name": "总计",
                "sumprice": 0
            }
        ],
        "statistics": {
            "人头": 2,
            "客流": 2,
            "平均单价": 0
        }
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
beauticianstatistics 美容师统计,数据类型为array
总计也包含在内，总计作为二级分类
category2name二级分类名称
sumcount总销售量
sumprice总销售金额

statistics"包含 人头 客流 平均单价
{
    "msg": "成功",
    "code": 0,
    "data": {
        "beauticianstatistics": [
            {
                "sumcount": 0,
                "categoryname": "实操类",
                "category2name": "美容",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实操类",
                "category2name": "美体",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实操类",
                "category2name": "仪器",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "卡扣产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "现金产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "赠送产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "现金产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "现金卡",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "现金实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "卡扣产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "卡扣实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "现金产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "现金实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "赠送实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "赠送类",
                "category2name": "赠送产品",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "赠送类",
                "category2name": "赠送实操",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "产品类",
                "category2name": "总计",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实耗类",
                "category2name": "总计",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "实操类",
                "category2name": "总计",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "现金类",
                "category2name": "总计",
                "sumprice": 0
            },
            {
                "sumcount": 0,
                "categoryname": "赠送类",
                "category2name": "总计",
                "sumprice": 0
            }
        ],
        "statistics": {
            "人头": 1,
            "客流": 3,
            "平均单价": 0
        }
    }
}
```
