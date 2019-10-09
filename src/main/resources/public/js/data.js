var year = new Date().getFullYear();
var day = new Date().getDate();
var month = new Date().getMonth();
var hour = new Date().getHours();
var minute = new Date().getMinutes();

console.log(year + " " + day + " " + month);

laydate.render({
  elem: "#test-n1", //渲染的标签id名
  showBottom: false,
  // theme: "#3ce4a9",
  value:
    year +
    "-" +
    (month < 10 ? "0" + month : month) +
    "-" +
    (day < 10 ? "0" + day : day) //设置默认时间
});
laydate.render({
  elem: "#test-n2", //渲染的标签id名
  showBottom: false,
  // theme: "#3ce4a9",
  value:
    year +
    "-" +
    (month < 10 ? "0" + month : month) +
    "-" +
    (day < 10 ? "0" + day : day) //设置默认时间
});
laydate.render({
  elem: "#test14",
  type: "time",
  format: "H:M",
  value: hour + ":" + minute
});
