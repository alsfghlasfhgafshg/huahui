
-------


## 手机端今日营业额

**url:** /m/todaydata

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|如果是商店登录查看本店经营分析表，不需要此项，如果是brand查看shop的经营分析，需要传入shopid

**返回格式:** json


**返回内容:**
```

{
    "msg": "成功",
    "code": 0,
    "data": {
        "项目数": 3,
        "人头": 2,
        "客人单价": 96.33,
        "客流": 3,
        "实操数": 3,
        "现金": 234.0
    }
}

```
**url:** /m/cufolw

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|如果是商店登录查看本店经营分析表，不需要此项，如果是brand查看shop的经营分析，需要传入shopid
period|string|日/周/月/季/年报或其他
starttime|string|开始时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)
endtime|string|结束时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)

**返回格式:** json

返回数据：
type为每个表名

con为每个表的数据列表

**返回内容:**
```

{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "con": [
                {
                    "times": 8,
                    "排名": 1,
                    "customer": "testcustomer1"
                },
                {
                    "times": 6,
                    "排名": 2,
                    "customer": "testcustomer2"
                },
                {
                    "times": 2,
                    "排名": 3,
                    "customer": "testcustomer4"
                },
                {
                    "times": 1,
                    "排名": 4,
                    "customer": "testcustomer"
                },
                {
                    "times": 1,
                    "排名": 5,
                    "customer": "testcustomer3"
                }
            ],
            "type": "到店次数"
        },
        {
            "con": [
                {
                    "排名": 1,
                    "smoney": 12.0,
                    "customer": "testcustomer"
                },
                {
                    "排名": 2,
                    "smoney": 237.0,
                    "customer": "testcustomer1"
                },
                {
                    "排名": 3,
                    "smoney": 1.0,
                    "customer": "testcustomer2"
                },
                {
                    "排名": 4,
                    "smoney": 87.0,
                    "customer": "testcustomer4"
                },
                {
                    "排名": 5,
                    "smoney": 337.0,
                    "customer": "总计"
                }
            ],
            "type": "实耗金额"
        },
        {
            "con": [
                {
                    "times": 1,
                    "con": 2,
                    "百分比": "40.00%"
                },
                {
                    "times": 2,
                    "con": 1,
                    "百分比": "20.00%"
                },
                {
                    "times": 5,
                    "con": 2,
                    "百分比": "40.00%"
                }
            ],
            "type": "到店次数"
        }
    ]
}

```


-------

## 经营分析表，excel_5

**url:** /m/customer

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
customer|string|客户名称(非必须,当此项为空为查询区间内全部)
shopid|int|如果是商店登录查看本店经营分析表，不需要此项，如果是brand查看shop的经营分析，需要传入shopid
period|string|日/周/月/季/年报或其他
starttime|string|开始时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)
endtime|string|结束时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)
handorcash|string|下拉选项

**返回格式:** json

**返回内容:**

返回数据：
如果为现金表:
距离上次为距离上次到店天数
客户信息为数据列表

**返回内容:**
现金表:
```

{
    "msg": "成功",
    "code": 0,
    "data": {
        "客户信息": [
            {
                "createtime": "2016-01-01",
                "projectname": "美容1",
                "times": 12,
                "money": 12.0,
                "customer": "testcustomer",
                "status": "睡眠客"
            }
        ],
        "距离上次": 1409
    }
}
```

其他表:
```
{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "createtime": "2016-01-01",
            "projectname": "美容1",
            "times": 12,
            "money": 12.0,
            "customer": "testcustomer",
            "status": "睡眠客"
        }
    ]
}
```







-------


## 经营分析表，excel_1

**url:** /m/management

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|如果是商店登录查看本店经营分析表，不需要此项，如果是brand查看shop的经营分析，需要传入shopid
period|string|日/周/月/季/年报或其他
starttime|string|开始时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)
endtime|string|结束时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)

**返回格式:** json

**返回内容:**

返回数据：

managementanalysis中type为类别实操，产品...，con为内容列表：
- category2：美容，美体，实操...
- summoney：金额
- sumcount：数量

tablefooter 底下的统计：
- headcount：人头
- averageprice：平均单价
- humantraffic： 人流


```


{
    "msg": "成功",
    "code": 0,
    "data": {
        "tablefooter": {
            "headcount": 5,
            "averageprice": 23,
            "humantraffic": 18
        },
        "managementanalysis": [
            {
                "type": "实操类",
                "con": [
                    {
                        "category2": "仪器",
                        "summoney": 332.0,
                        "sumcount": 3.0
                    },
                    {
                        "category2": "美体",
                        "summoney": 76.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "美容",
                        "summoney": 14.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "总计",
                        "summoney": 422.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "产品类",
                "con": [
                    {
                        "category2": "卡扣产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "赠送产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "总计",
                        "summoney": 6.0,
                        "sumcount": 6.0
                    }
                ]
            },
            {
                "type": "现金类",
                "con": [
                    {
                        "category2": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "现金卡",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "现金实操",
                        "summoney": 291.0,
                        "sumcount": 3.0
                    },
                    {
                        "category2": "总计",
                        "summoney": 295.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "实耗类",
                "con": [
                    {
                        "category2": "卡扣产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "现金实操",
                        "summoney": 291.0,
                        "sumcount": 3.0
                    },
                    {
                        "category2": "卡扣实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "category2": "赠送实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "category2": "总计",
                        "summoney": 295.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "赠送类",
                "con": [
                    {
                        "category2": "赠送产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "赠送实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "category2": "总计",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    }
                ]
            }
        ]
    }
}

```






-------


## 美容师分析表，excel_2

**url:** /m/beauticiant

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
beauticianname|string|美容师姓名
shopid|int|如果是商店登录查看本店美容师分析表，不需要此项，如果是brand查看shop的经营分析，需要传入shopid
period|string|日/周/月/季/年报或其他
starttime|string|开始时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)
endtime|string|结束时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)

**返回格式:** json

**返回内容:**

返回数据：

managementanalysis中type为类别实操，产品...，con为内容列表：
- category2：美容，美体，实操...
- summoney：金额
- sumcount：数量

tablefooter 底下的统计：
- headcount：人头
- averageprice：平均单价
- humantraffic： 人流


```


{
    "msg": "成功",
    "code": 0,
    "data": {
        "tablefooter": {
            "headcount": 5,
            "averageprice": 23,
            "humantraffic": 18
        },
        "managementanalysis": [
            {
                "type": "实操类",
                "con": [
                    {
                        "category2": "仪器",
                        "summoney": 332.0,
                        "sumcount": 3.0
                    },
                    {
                        "category2": "美体",
                        "summoney": 76.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "美容",
                        "summoney": 14.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "总计",
                        "summoney": 422.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "产品类",
                "con": [
                    {
                        "category2": "卡扣产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "赠送产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "总计",
                        "summoney": 6.0,
                        "sumcount": 6.0
                    }
                ]
            },
            {
                "type": "现金类",
                "con": [
                    {
                        "category2": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "现金卡",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "现金实操",
                        "summoney": 291.0,
                        "sumcount": 3.0
                    },
                    {
                        "category2": "总计",
                        "summoney": 295.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "实耗类",
                "con": [
                    {
                        "category2": "卡扣产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "现金实操",
                        "summoney": 291.0,
                        "sumcount": 3.0
                    },
                    {
                        "category2": "卡扣实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "category2": "赠送实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "category2": "总计",
                        "summoney": 295.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "赠送类",
                "con": [
                    {
                        "category2": "赠送产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "category2": "赠送实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "category2": "总计",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    }
                ]
            }
        ]
    }
}

```









-------


## 美容师分析表，excel_3

**url:** /m/project

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|如果是商店登录查看本店美容师分析表，不需要此项，如果是brand查看shop的经营分析，需要传入shopid
period|string|日/周/月/季/年报或其他
starttime|string|开始时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)
endtime|string|结束时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)

**返回格式:** json

**返回内容:**

返回数据：

```

product，beauty，body分别为产品，美容，美体排名：
- name 名
- summoney 金额
- sumcount 数量

{
    "msg": "成功",
    "code": 0,
    "data": {
        "product": [
            {
                "name": "projectname",
                "summoney": 6.0,
                "sumcount": 6.0
            },
            {
                "name": "总计",
                "summoney": 6.0,
                "sumcount": 6.0
            }
        ],
        "beauty": [
            {
                "name": "美容1",
                "summoney": 1.0,
                "sumcount": 1.0
            },
            {
                "name": "美容2",
                "summoney": 1.0,
                "sumcount": 1.0
            },
            {
                "name": "总计",
                "summoney": 2.0,
                "sumcount": 2.0
            }
        ],
        "body": [
            {
                "name": "projectname",
                "summoney": 2.0,
                "sumcount": 2.0
            },
            {
                "name": "总计",
                "summoney": 2.0,
                "sumcount": 2.0
            }
        ]
    }
}
```





-------


## 美容师分析表 最后一个excel

**url:** /m/beauticianttable

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
staffname|string|美容师姓名，如果不传或者此项为空，为所有的美容师
fenxi|int|0为实操，1为现金，2为所有
period|string|日/周/月/季/年报或其他
starttime|string|开始时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)
endtime|string|结束时间，yyyy-mm-dd或者yyyy年mm月dd日格式(当period为其他时需要这个参数)

**返回格式:** json

**返回内容:**

返回内容：
name美容师姓名，con为list

time：时间
projectname:项目名
summoney: 金额
count数量
sumhand手工

```
错误code 为1：
{
    "msg": "无此美容师",
    "code": 1
}

正确code为0：

{
    "msg": "成功",
    "code": 0,
    "data": [
        {
            "name": "臧三",
            "con": [
                {
                    "time": "2016年01月01日",
                    "projectname": "美容1",
                    "summoney": 12,
                    "count": 1,
                    "sumhand": 1
                },
                {
                    "time": "2019年01月01日",
                    "projectname": "美容2",
                    "summoney": 315,
                    "count": 4,
                    "sumhand": 29
                },
                {
                    "time": "2019年03月01日",
                    "projectname": "projectname",
                    "summoney": 42,
                    "count": 1,
                    "sumhand": 1
                },
                {
                    "time": "2019年08月01日",
                    "projectname": "projectname",
                    "summoney": 53,
                    "count": 1,
                    "sumhand": 2
                }
            ]
        },
        {
            "name": "总计",
            "con": [
                {
                    "time": "",
                    "projectname": "",
                    "summoney": 422,
                    "count": 7,
                    "sumhand": 33
                }
            ]
        }
    ]
}

```



