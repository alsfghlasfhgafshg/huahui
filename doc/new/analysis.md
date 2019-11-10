
-------


## 经营分析表，excel_4

**url:** /analysis/cufolw

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

**url:** /analysis/customer

**HTTP请求方式:** GET

**请求参数:**

参数名称|类型|描述
---|:--:|---:
customer|string|客户名称
shopid|int|如果是商店登录查看本店经营分析表，不需要此项，如果是brand查看shop的经营分析，需要传入shopid
starttime|string|开始时间，yyyy-mm-dd或者yyyy年mm月dd日格式
endtime|string|结束时间，yyyy-mm-dd或者yyyy年mm月dd日格式
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



