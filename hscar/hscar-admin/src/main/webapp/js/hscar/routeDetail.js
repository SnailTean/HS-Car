$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + '/hscar/routeDetail/list',
        datatype: "json",
        colModel: [			
			{ label: 'routeId', name: 'routeId', width: 50, key: true },
			{ label: '出发地', name: 'departure', width: 80 }, 			
			{ label: '目的地', name: 'destination', width: 80 }, 			
			{ label: '目的地坐标', name: 'depCoordinate', width: 80 }, 			
			{ label: '出发地坐标', name: 'desCoordinate', width: 80 }, 			
			{ label: '用户ID', name: 'userId', width: 80 }, 			
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
		routeDetail: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.routeDetail = {};
		},
		update: function (event) {
			var routeId = getSelectedRow();
			if(routeId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(routeId);
		},
		saveOrUpdate: function (event) {
			var url = vm.routeDetail.routeId == null ? "/hscar/routeDetail/save" : "/hscar/routeDetail/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.routeDetail),
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
			var routeIds = getSelectedRows();
			if(routeIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "/hscar/routeDetail/delete",
				    contentType: "application/json",
				    data: JSON.stringify(routeIds),
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
		getInfo: function(routeId){
			$.get("routeDetail/info/"+routeId, function(r){
                vm.routeDetail = r.routeDetail;
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