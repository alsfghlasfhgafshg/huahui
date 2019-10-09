// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById("main"));

// 指定图表的配置项和数据
var option = {
  tooltip: {},
  axisLabel: {
    show: true,
    textStyle: {
      color: "#19e5a6", //更改坐标轴文字颜色
      fontSize: 14 //更改坐标轴文字大小
    }
  },

  xAxis: {
    data: ["", "", ""],
    axisLine: {
      lineStyle: {
        color: "#19e5a6" //更改坐标轴颜色
      }
    },
    axisTick: {
      show: false
    }
  },
  yAxis: {
    axisLine: {
      lineStyle: {
        color: "#19e5a6" //更改坐标轴颜色
      }
    },
    splitLine: {
      show: true,
      lineStyle: {
        color: ["#d0faec"],
        width: 1,
        type: "solid"
      }
    }
  },
  series: [
    {
      name: "销量",
      type: "bar",
      barWidth: "50%",
      data: [4.85, 6.35, 4.15],
      itemStyle: {
        normal: {
          color: function(params) {
            var colorList = [
              "rgb(6, 141, 227)",
              "rgb(227, 188, 6)",
              "rgb(227, 84, 6)"
            ];
            return colorList[params.dataIndex];
          },
          barBorderRadius: [4, 4, 0, 0],
          label: {
            show: true, //开启显示
            position: "top", //在上方显示
            textStyle: {
              //数值样式
              color: function(params) {
                var colorList = [
                  "rgb(6, 141, 227)",
                  "rgb(227, 188, 6)",
                  "rgb(227, 84, 6)"
                ];
                return colorList[params.dataIndex];
              },
              fontSize: 16
            }
          }
        }
      }
    }
  ],
  grid: {
    left: "8%",
    bottom: "8%",
    top: "12%",
    right: "1%"
  }
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
