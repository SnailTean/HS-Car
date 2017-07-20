$("#settings_close").click(function(){
	alert("close");
});

$("#settings_test").click(function(){
	alert('测试成功', function() {
		
	});
});

$("#settings_logout").click(function(){
	//删除本地token
    localStorage.removeItem("token");
	
    //跳转到登录页面
    location.href = baseURL + 'login.html';
});