var myChart = echarts.init(document.getElementById("main"));
option = {
  // tooltip: {
  //   trigger: "item",
  //   formatter: "{a} <br/>{b} : {c} ({d}%)"
  // },

  // series: [
  //   {
  //     name: "访问来源",
  //     type: "pie",
  //     radius: "65%",
  //     center: ["50%", "40%"],
  //     data: [
  //       {
  //         value: 25,
  //         name: "卡扣",
  //         itemStyle: { normal: { color: "#e3bc05" } }
  //       },
  //       {
  //         value: 15,
  //         name: "赠送",
  //         itemStyle: { normal: { color: "#e35406" } }
  //       },
  //       { value: 65, name: "现金", itemStyle: { normal: { color: "#078de4" } } }
  //     ], //数据,我们ajax获取
  //     itemStyle: {
  //       emphasis: {
  //         shadowBlur: 10,
  //         shadowOffsetX: 0,
  //         shadowColor: "rgba(0, 0, 0, 0.5)"
  //       }
  //     },

  //     // 设置值域的标签
  //     label: {
  //       normal: {
  //         // position: "inner", // 设置标签位置，默认在饼状图外 可选值：'outer' ¦ 'inner（饼状图上）'
  //         // formatter: '{b}',
  //         // formatter: '{a} {b} : {c}个 ({d}%)'   设置标签显示内容 ，默认显示{b}
  //         // {a}指series.name  {b}指series.data的name
  //         // {c}指series.data的value  {d}%指这一部分占总数的百分比
  //         formatter: "{b}{d}"
  //       }
  //     }
  //   }
  // ]
  title: {
    text: "",
    subtext: "",
    x: "center"
  },
  tooltip: {
    trigger: "item",
    formatter: "{b}"
  },
  series: [
    //重点在这里，以下代码实现图中效果，使用两个重合的饼形图实现此种效果。饼形图的大小位置等都一样。唯一不同的就是label中的position属性（说明文字为“outer”，百分比为“inner”）labelLine中的show属性（说明文字为false，百分比为true）和显示文字（一个显示百分比，一个显示说明文字）
    {
      name: "",
      type: "pie", //饼状图
      radius: "65%", //大小
      center: ["50%", "40%"], //显示位置
      data: [
        {
          value: 25,
          name: "卡扣",
          itemStyle: { normal: { color: "#e3bc05" } }
        },
        {
          value: 15,
          name: "赠送",
          itemStyle: { normal: { color: "#e35406" } }
        },
        { value: 60, name: "现金", itemStyle: { normal: { color: "#078de4" } } }
      ], //数据,我们ajax获取
      itemStyle: {
        normal: {
          label: {
            show: true,
            position: "outer",
            fontSize: 14,
            // fontWeight: "bold",
            // align: "left",
            formatter: function(p) {
              //指示线对应文字，说明文字
              return p.data.name;
            }
          },
          color: function(params) {
            //自定义颜色
            var colorList = ["#00CD00", "#FF7F00"];
            return colorList[params.dataIndex];
          },
          labelLine: {
            //指示线状态
            show: true,
            smooth: 0.2,
            length: 10,
            length2: 20
          }
        }
      }
    },
    {
      name: "",
      type: "pie",
      avoidLabelOverlap: true,
      radius: "65%", //大小
      center: ["50%", "40%"], //显示位置
      data: [
        {
          value: 25,
          name: "25%",
          itemStyle: { normal: { color: "#e3bc05" } }
        },
        {
          value: 15,
          name: "15%",
          itemStyle: { normal: { color: "#e35406" } }
        },
        { value: 60, name: "60%", itemStyle: { normal: { color: "#078de4" } } }
      ],
      itemStyle: {
        normal: {
          label: {
            show: true,
            position: "inner",
            fontSize: 14,
            // fontWeight: "bold",
            // align: "left",
            formatter: function(p) {
              //指示线对应文字,百分比
              // return p.percent + "%";
              return p.data.name;
            }
          },
          color: function(params) {
            //自定义颜色
            var colorList = ["#00CD00", "#FF7F00"];
            return colorList[params.dataIndex];
          },
          labelLine: {
            //指示线状态
            show: true,
            smooth: 0.2,
            length: 10,
            length2: 20
          }
        }
      }
    }
  ]
};
myChart.setOption(option);
