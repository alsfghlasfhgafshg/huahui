
-------


## 获取所有支付方式id

**url:** /settlement/allpaymentmethod

**HTTP请求方式:** GET

**请求参数:** 无参数

**返回格式:** json

**返回内容:**
```

返回array
id：支付方式id
name：支付方式名称

{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "id": 1,
            "name": "XXX"
        }
    ]
}

```


-------


## 标题

**url:** /settlement/getsettlement

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
settlementid|int|结算单id

**返回格式:** json

**返回内容:**
```


失败
{
    "msg": "XXXXX",
    "code": 1,
    
}

返回内容

settlementItems为此结算单做的项目，为array，


{
    "msg": "成功",
    "code": 0,
    "data": {
        "id": 2, //结算单id
        "shopid": 12, //商店id
        "createtime": "2019-09-26T07:00:00.000+0000",//创建结算单的时间
        "customername": "dsf",//顾客姓名
        "peoplenum": 0,//顾客手机号
        "roomname": "1",//顾客所在房间名
        "consultant": "sd",//顾问名
        "paymentmethod": 卡扣实操,//付款方式
        "settlementItems": [   //此结算单的所有项目
            {
                "id": 2, //此结算单中的项目的id
                "settlementid": 2, //结算单id
                "category2name": null, //项目id
                "times": 2, //次数
                "price": 2,//价格
                "staff1name": "dewittshop", //美容师1
                "staff2name": "dewittshop" //美容师2
            },
            {
                "id": 3,
                "settlementid": 2,
                "category2name": null,
                "times": 23,
                "price": 23,
                "staff1name": "dewittshop",
                "staff2name": "dewittshop"
            }
        ]
    }
}


```



-------


## 新的结算单

**url:** /settlement/addsettlement

**HTTP请求方式:** POST
**Http Header:** Content-Type: application/json

**请求:**
{
    "customername":"",//顾客名
    "peoplenum":"",//人数
    "roomname":"",// 房间名
    "consultantid":"",//顾问名
    "paymentmethod":"",//付款方式id
    settlementitems:[  //settlementitems结算单具体项目,array
        {
        "projectid":"", //项目id
        "times":"",//次数
        "price":"",//价格
        "staff1":"",//顾问1id
        "staff2":"",//顾问2id
        },
    ]

}



**返回格式:** json

**返回内容:**
```
{
    "msg": "成功",
    "code": 0,
    "data":{
            "settlementid": 1
        }
}

```
## 根据新客名字获取顾问名字

**url:** /settlement/getconsultantname

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
vipname|string|新客名字

**返回格式:** json

**返回内容:**
```
{
    "msg": "成功",
    "code": 0,
    "data":"哈哈"
}

```

## 根据新客名字获取顾问名字

**url:** /settlement/addshopvip

**HTTP请求方式:** POST

**请求参数:**

参数名称|类型|描述
---|:--:|---:
vipname|string|新客名字
shopid|int|店铺id
consultantid|int|顾问id

**返回格式:** json

**返回内容:**
```
{
    "msg": "添加成功",
    "code": 0
}

```



