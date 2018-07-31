$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'invoice/invoiceinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '发票代码', name: 'invoiceCode', index: 'invoice_code', width: 80 },
			{ label: '发票号码', name: 'invoiceNumber', index: 'invoice_number', width: 80 },
			{ label: '开票时间', name: 'billTime', index: 'bill_time', width: 80 },
			{ label: '校验码', name: 'checkCode', index: 'check_code', width: 80 },
			{ label: '开具金额', name: 'invoiceAmount', index: 'invoice_amount', width: 80 },
			/*{ label: '授权码', name: 'token', index: 'token', width: 80 },
			{ label: '状态(0:删除 1:正常)', name: 'state', index: 'state', width: 80 },*/
			{ label: '创建人', name: 'crtUsr', index: 'crt_usr', width: 80 }, 			
			{ label: '创建时间', name: 'crtDt', index: 'crt_dt', width: 80 },
			{ label: '最后修改人', name: 'lastChgUsr', index: 'last_chg_usr', width: 80 }, 			
			{ label: '最后修改时间', name: 'lastChgDt', index: 'last_chg_dt', width: 80 }
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
		invoiceInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.invoiceInfo = {};
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
			var url = vm.invoiceInfo.id == null ? "invoice/invoiceinfo/save" : "invoice/invoiceinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.invoiceInfo),
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
				    url: baseURL + "invoice/invoiceinfo/delete",
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
			$.get(baseURL + "invoice/invoiceinfo/info/"+id, function(r){
                vm.invoiceInfo = r.invoiceInfo;
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