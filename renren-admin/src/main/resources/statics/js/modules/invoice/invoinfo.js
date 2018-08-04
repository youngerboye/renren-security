$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'invoice/invoinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '发票序号', name: 'invoiceSeq', index: 'invoice_seq', width: 80 }, 			
			{ label: '发票代码', name: 'invoiceCode', index: 'invoice_code', width: 80 }, 			
			{ label: '发票号码', name: 'invoiceNumber', index: 'invoice_number', width: 80 }, 			
			{ label: '开票时间', name: 'billTime', index: 'bill_time', width: 80 }, 			
			{ label: '校验码', name: 'checkCode', index: 'check_code', width: 80 }, 			
			{ label: '开具金额', name: 'invoiceAmount', index: 'invoice_amount', width: 80 }, 			
			{ label: '是否校验通过', name: 'isChecked', index: 'is_checked', width: 80 }, 			
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
		invoInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.invoInfo = {};
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
			var url = vm.invoInfo.id == null ? "invoice/invoinfo/save" : "invoice/invoinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.invoInfo),
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
				    url: baseURL + "invoice/invoinfo/delete",
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
			$.get(baseURL + "invoice/invoinfo/info/"+id, function(r){
                vm.invoInfo = r.invoInfo;
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