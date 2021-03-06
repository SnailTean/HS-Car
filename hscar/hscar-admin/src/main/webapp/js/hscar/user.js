$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'hscar/user/list',
        datatype: "json",
        colModel: [			
			{ label: 'userId', name: 'userId', width: 50, key: true },
			{ label: '用户名', name: 'username', width: 80 }, 			
			{ label: '手机号', name: 'mobile', width: 80 }, 			
			{ label: '密码', name: 'password', width: 80 }, 			
			{ label: '昵称', name: 'nickName', width: 80 }, 			
			{ label: '年龄', name: 'age', width: 80 }, 			
			{ label: '性别', name: 'sex', width: 80 }, 			
			{ label: '身份证', name: 'identityCard', width: 80 }, 			
			{ label: '邮箱', name: 'mail', width: 80 }, 			
			{ label: '职业', name: 'occupation', width: 80 }, 			
			{ label: '行业', name: 'trade', width: 80 }, 			
			{ label: '公司', name: 'company', width: 80 }, 			
			{ label: '各项签名', name: 'signature', width: 80 }, 			
			{ label: '头像', name: 'avatar', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		user: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.user = {};
		},
		update: function (event) {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(userId);
		},
		saveOrUpdate: function (event) {
			var url = vm.user.userId == null ? "hscar/user/save" : "hscar/user/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.user),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "hscar/user/delete",
				    contentType: "application/json",
				    data: JSON.stringify(userIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(userId){
			$.get("user/info/"+userId, function(r){
                vm.user = r.user;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});