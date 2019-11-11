
-------


## 结算单管理页展示的内容

**url:** /settlement/thismonth

**HTTP请求方式:** GET


**返回格式:** json

**返回内容:**
data为list：
- consumptionpattern 消费方式
- classify 分类
- projectname 项目名
- money 金额
- consumptioncategory 消费类别
- time 日期
- category 类别
- brandname 商店
- customer 顾客名
- beautician 美容师


```

{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "settltmentid":1,
            "consumptionpattern": "consumptionpattern",
            "classify": "classify",
            "projectname": "projectname",
            "money": 1.0,
            "consumptioncategory": "现金卡",
            "time": "2019年11月01日",
            "category": "卡",
            "brandname": "brandname",
            "customer": "testcustomer2",
            "beautician": "臧三/臧三"
        }
    ]
}

```



-------


## 标题

**url:** /settlement/add

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
customer|string|顾客名
classify|string|分类
category|string|类别
brandname|string|品牌 
projectname|string|项目名
times|int|数量
hand|int|手工费
money|int|金额
consumptioncategory|string|消费类别
consumptionpattern|string|消费方式
allocate|string|指非新
beautician1|int|美容师1（/staff/allstaff这个借口获得美容师）
beautician2|int|美容师2
cardcategory|string|卡项类别
consultant|string|顾问
checker|string|审核人
createtime|string|yyyy-dd-mm或者yyyy年dd月mm日格式 

**返回格式:** json

**返回内容:**
```
{
    "msg": "添加成功",
    "code": 0
}
{
    "msg": "添加失败",
    "code": 0
}

```


-------


## 点击一项弹窗

**url:** /settlement/selectone

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
settlementid|int|结算单id

**返回格式:** json

**返回内容:**
```
返回内容和添加的命名一样

{
    "msg": "成功",
    "code": 0,
    "data": {
        "settlementid": 18,
        "customer": "testcustomer2",
        "classify": "classify",
        "category": "卡",
        "brandname": "brandname",
        "projectname": "projectname",
        "times": 1,
        "hand": 2,
        "money": 1.0,
        "consumptioncategory": "卡扣卡",
        "consumptionpattern": "consumptionpattern",
        "allocate": "allocate",
        "beautician1": "臧三",
        "beautician2": "臧三",
        "cardcategory": "cardcategory",
        "consultant": "consultant",
        "checker": "checker",
        "createtime": "2019年10月01日"
    }
}
```


-------


## 删除一个结算单

**url:** /settlement/deleteone

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
settlementid|int|结算单id

**返回格式:** json

**返回内容:**
```
{
    "msg": "删除成功",
    "code": 0
}
{
    "msg": "删除失败",
    "code": 1
}

```


-------


## 修改结算单

**url:** /settlement/update

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
settlementid|int|结算单id
customer|string|顾客名
classify|string|分类
category|string|类别
brandname|string|品牌 
projectname|string|项目名
times|int|数量
hand|int|手工费
money|int|金额
consumptioncategory|string|消费类别
consumptionpattern|string|消费方式
allocate|string|指非新
beautician1|int|美容师1（/staff/allstaff这个借口获得美容师）
beautician2|int|美容师2
cardcategory|string|卡项类别
consultant|string|顾问
checker|string|审核人
createtime|string|yyyy-dd-mm或者yyyy年dd月mm日格式 

**返回格式:** json

**返回内容:**
```
{
    "msg": "修改成功",
    "code": 0
}
{
    "msg": "添加失败",
    "code": 0
}

```




-------


## 在新建结算单页显示的顾客上次到店时间

**url:** /settlement/dayslaststoshop

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
customer|string|顾客姓名

**返回格式:** json

**返回内容:**
```
day距离上次到店时间，如果为非负数，则是到店时间。-1为从未到店

{
    "msg": "成功",
    "code": 0,
    "data": {
        "day": -1
    }
}

```



-------


## 顾客剩余项目次数

**url:** /settlement/projectremainingtimes

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
customer|string|顾客姓名
projectname|string|项目名

**返回格式:** json

**返回内容:**
```
成功返回code 0
remainingtimes 为剩余次数
{
    "msg": "成功",
    "code": 0,
    "data": {
        "remainingtimes": 8
    }
}

失败返回1
{
    "msg": "顾客没有购买此项目",
    "code": 1
}

```
