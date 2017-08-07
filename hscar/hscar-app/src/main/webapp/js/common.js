//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

//当前路径
var pathName = window.document.location.pathname;
//当前工程名
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
//请求前缀
//var baseURL = "http://demo.open.renren.io/hscar-app/";
var baseURL = projectName + "/";

//登录token
var token = localStorage.getItem("token");

//jquery全局配置
$.ajaxSetup({
	dataType: "json",
	cache: false,
    headers: {
        "token": token
    },
    complete: function(xhr) {
        //token过期，则跳转到登录页面
        if(xhr.responseJSON.code == 401) { // Token失效或者为空
            parent.location.href = baseURL + 'login.html';
        } else if(xhr.responseJSON.code == 1000) { // 只注册了乘客用户，未注册司机用户
            parent.location.href = baseURL + 'hscar/app/customer/customer_register_driver.html';
        }
    }
});

//重写alert
window.alert = function(msg, callback) {
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//重写confirm式样框
window.confirm = function(msg, callback) {
	parent.layer.confirm(msg, {btn: ['确定','取消']},
	function(){//确定事件
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}