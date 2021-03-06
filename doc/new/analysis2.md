
-------


## 经营分析表，excel_1

**url:** /analysis/management

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|如果是商店登录查看本店经营分析表，不需要此项，如果是brand查看shop的经营分析，需要传入shopid
starttime|string|开始时间，yyyy-mm-dd或者yyyy年mm月dd日格式
endtime|string|结束时间，yyyy-mm-dd或者yyyy年mm月dd日格式

**返回格式:** json

**返回内容:**

返回数据：

managementanalysis中type为类别实操，产品...，con为内容列表：
- project：美容，美体，实操...
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
                        "project": "仪器",
                        "summoney": 332.0,
                        "sumcount": 3.0
                    },
                    {
                        "project": "美体",
                        "summoney": 76.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "美容",
                        "summoney": 14.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "总计",
                        "summoney": 422.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "产品类",
                "con": [
                    {
                        "project": "卡扣产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "赠送产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "总计",
                        "summoney": 6.0,
                        "sumcount": 6.0
                    }
                ]
            },
            {
                "type": "现金类",
                "con": [
                    {
                        "project": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "现金卡",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "现金实操",
                        "summoney": 291.0,
                        "sumcount": 3.0
                    },
                    {
                        "project": "总计",
                        "summoney": 295.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "实耗类",
                "con": [
                    {
                        "project": "卡扣产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "现金实操",
                        "summoney": 291.0,
                        "sumcount": 3.0
                    },
                    {
                        "project": "卡扣实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "project": "赠送实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "project": "总计",
                        "summoney": 295.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "赠送类",
                "con": [
                    {
                        "project": "赠送产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "赠送实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "project": "总计",
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

**url:** /analysis/beauticiant

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
beauticianname|string|美容师姓名
shopid|int|如果是商店登录查看本店美容师分析表，不需要此项，如果是brand查看shop的经营分析，需要传入shopid
starttime|string|开始时间，yyyy-mm-dd或者yyyy年mm月dd日格式
endtime|string|结束时间，yyyy-mm-dd或者yyyy年mm月dd日格式

**返回格式:** json

**返回内容:**

返回数据：

managementanalysis中type为类别实操，产品...，con为内容列表：
- project：美容，美体，实操...
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
                        "project": "仪器",
                        "summoney": 332.0,
                        "sumcount": 3.0
                    },
                    {
                        "project": "美体",
                        "summoney": 76.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "美容",
                        "summoney": 14.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "总计",
                        "summoney": 422.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "产品类",
                "con": [
                    {
                        "project": "卡扣产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "赠送产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "总计",
                        "summoney": 6.0,
                        "sumcount": 6.0
                    }
                ]
            },
            {
                "type": "现金类",
                "con": [
                    {
                        "project": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "现金卡",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "现金实操",
                        "summoney": 291.0,
                        "sumcount": 3.0
                    },
                    {
                        "project": "总计",
                        "summoney": 295.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "实耗类",
                "con": [
                    {
                        "project": "卡扣产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "现金产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "现金实操",
                        "summoney": 291.0,
                        "sumcount": 3.0
                    },
                    {
                        "project": "卡扣实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "project": "赠送实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "project": "总计",
                        "summoney": 295.0,
                        "sumcount": 7.0
                    }
                ]
            },
            {
                "type": "赠送类",
                "con": [
                    {
                        "project": "赠送产品",
                        "summoney": 2.0,
                        "sumcount": 2.0
                    },
                    {
                        "project": "赠送实操",
                        "summoney": 0.0,
                        "sumcount": 0.0
                    },
                    {
                        "project": "总计",
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

**url:** /analysis/project

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
shopid|int|如果是商店登录查看本店美容师分析表，不需要此项，如果是brand查看shop的经营分析，需要传入shopid
starttime|string|开始时间，yyyy-mm-dd或者yyyy年mm月dd日格式
endtime|string|结束时间，yyyy-mm-dd或者yyyy年mm月dd日格式

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

**url:** /analysis/beauticianttable

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
staffname|string|美容师姓名，如果不传或者此项为空，为所有的美容师
fenxi|int|0为实操，1为现金，2为所有
starttime|string|开始时间
endtime|string|结束时间

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



