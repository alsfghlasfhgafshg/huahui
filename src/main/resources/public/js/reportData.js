var year = new Date().getFullYear();
var day = new Date().getDate();
var month = new Date().getMonth();

console.log(year + " " + day + " " + month);

laydate.render({
  showBottom: false,
  // theme: "#3ce4a9",
  elem: "#test-n1", //渲染的标签id名
  value: month + "." + day //设置默认时间
});
laydate.render({
  showBottom: false,
  elem: "#test-n2", //渲染的标签id名
  value: month + "." + day //设置默认时间
});
laydate.render({
  elem: '#test-n3'
  ,format: 'MM.dd',
  showBottom: false
});
laydate.render({
  elem: '#test-n4'
  ,format: 'MM.dd',
  showBottom: false
})