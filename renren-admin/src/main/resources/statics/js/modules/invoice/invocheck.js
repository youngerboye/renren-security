$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'invoice/invocheck/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '发票序号', name: 'invoiceSeq', index: 'invoice_seq', width: 80 }, 			
			{ label: '二维码字符串', name: 'scanStr', index: 'scan_str', width: 80 }, 			
			{ label: '授权码', name: 'token', index: 'token', width: 80 }, 			
			{ label: '00：成功，99：失败', name: 'rtnCode', index: 'rtn_code', width: 80 }, 			
			{ label: '查询发票状态码，1000：查询到票的信息，2001：没有查询到票的信息', name: 'resultCode', index: 'result_code', width: 80 }, 			
			{ label: '失败状态码，201，210，220等', name: 'invoicefalseCode', index: 'invoicefalse_code', width: 80 }, 			
			{ label: '结果提示信息', name: 'resultMsg', index: 'result_msg', width: 80 }, 			
			{ label: '发票名称', name: 'invoiceName', index: 'invoice_name', width: 80 }, 			
			{ label: '数据查询结果', name: 'invoiceResult', index: 'invoice_result', width: 80 }, 			
			{ label: '查询是否免费，Y：是，N：否', name: 'isFree', index: 'is_free', width: 80 }, 			
			{ label: '创建人', name: 'crtUsr', index: 'crt_usr', width: 80 }, 			
			{ label: '创建日期', name: 'crtDt', index: 'crt_dt', width: 80 }, 			
			{ label: '最后修改人', name: 'lastChgUsr', index: 'last_chg_usr', width: 80 }, 			
			{ label: '最后修改时间', name: 'lastChgDt', index: 'last_chg_dt', width: 80 }, 			
			{ label: '备用字段1', name: 'ext1', index: 'ext1', width: 80 }, 			
			{ label: '备用字段2', name: 'ext2', index: 'ext2', width: 80 }, 			
			{ label: '备用字段3', name: 'ext3', index: 'ext3', width: 80 }, 			
			{ label: '备用字段4', name: 'ext4', index: 'ext4', width: 80 }, 			
			{ label: '备用字段5', name: 'ext5', index: 'ext5', width: 80 }			
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
		invoCheck: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.invoCheck = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.invoCheck.id == null ? "invoice/invocheck/save" : "invoice/invocheck/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.invoCheck),
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
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "invoice/invocheck/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
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
		getInfo: function(id){
			$.get(baseURL + "invoice/invocheck/info/"+id, function(r){
                vm.invoCheck = r.invoCheck;
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