$("#settings_close").click(function() {
//	alert("关闭");
});

$("#settings_update_password").click(function() {
	//跳转到密码修改页面
	location.href = baseURL + 'hscar/app/customer/customer_password_modify.html';
});

$("#settings_logout").click(function() {
	//删除本地token
    localStorage.removeItem("token");
	
    //跳转到登录页面
    location.href = baseURL + 'login.html';
});