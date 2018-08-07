$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'invoice/invocheckdetl/list',
        datatype: "json",
        colModel: [			
			// { label: 'id', name: 'id', index: 'id', width: 50, key: true },
			// { label: '发票序号', name: 'invoiceSeq', index: 'invoice_seq', width: 80 },
			{ label: '发票代码', name: 'invoiceDataCode', index: 'invoice_data_code', width: 40,"align":"left" },
			{ label: '发票号码', name: 'invoiceNumber', index: 'invoice_number', width: 30 },
			// { label: '发票类型名称', name: 'invoiceTypeName', index: 'invoice_type_name', width: 80 },
			// { label: '发票类型，01：增值税专票，03：机动车销售统一发票，04：增值税普通发票，10：电子发票，11：卷式普通发票，14:电子普通[通行费]发票，15：二手车统一发票', name: 'invoiceTypeCode', index: 'invoice_type_code', width: 80 },
			{ label: '发票类型', name: 'invoiceTypeCode', index: 'invoice_type_code', width: 30 },
			{ label: '开票时间', name: 'billingTime', index: 'billing_time', width: 30 },
			// { label: '查询日期', name: 'checkDate', index: 'check_date', width: 80 },
			// { label: '查验次数', name: 'checkNum', index: 'check_num', width: 80 },
			// { label: '校验码', name: 'checkCode', index: 'check_code', width: 80 },
			// { label: '机器码', name: 'taxDiskCode', index: 'tax_disk_code', width: 80 },
			{ label: '购方名称', name: 'purchaserName', index: 'purchaser_name', width: 110 },
			// { label: '购方纳税人识别号', name: 'taxpayerNumber', index: 'taxpayer_number', width: 80 },
			// { label: '购方银行账号', name: 'taxpayerBankAccount', index: 'taxpayer_bank_account', width: 80 },
			// { label: '购方地址，电话', name: 'taxpayerAddressOrId', index: 'taxpayer_address_or_id', width: 80 },
			{ label: '销方名称', name: 'salesName', index: 'sales_name', width: 110 },
			// { label: '销方纳税人识别号', name: 'salesTaxpayerNum', index: 'sales_taxpayer_num', width: 80 },
			// { label: '销方银行，账号', name: 'salesTaxpayerBankAccount', index: 'sales_taxpayer_bank_account', width: 80 },
			// { label: '销方地址，电话', name: 'salesTaxpayerAddress', index: 'sales_taxpayer_address', width: 80 },
			{ label: '价税合计', name: 'totalTaxSum', index: 'total_tax_sum', width: 35 },
			{ label: '税额', name: 'totalTaxNum', index: 'total_tax_num', width: 35 },
			// { label: '不含税价（金额）', name: 'totalAmount', index: 'total_amount', width: 80 },
			// { label: '备注', name: 'invoiceRemarks', index: 'invoice_remarks', width: 80 },
			// { label: '是否为清单票，Y：是，N：否', name: 'isBillMark', index: 'is_bill_mark', width: 80 },
			// { label: '作废标志，0：正常，1：作废', name: 'voidMark', index: 'void_mark', width: 80 },
			// { label: '收货员（卷式发票新增字段，其他票可以不用）', name: 'goodsClerk', index: 'goods_clerk', width: 80 },
			// { label: '收费标志字段（06：可抵扣通行费 07：不可抵扣通行费，08：成品油）', name: 'tollSign', index: 'toll_sign', width: 80 },
			// { label: '收费标志名称（没有为空）', name: 'tollSignName', index: 'toll_sign_name', width: 80 },
			// { label: '发票详情（清单票首行号为0，折扣清单票行号从-2开始，正常行号都是从1开始', name: 'invoiceDetailData', index: 'invoice_detail_data', width: 80 },
			{ label: '录入人', name: 'crtUsr', index: 'crt_usr', width: 30 },
			{ label: '录入日期', name: 'crtDt', index: 'crt_dt', width: 50 },
			// { label: '最后修改人', name: 'lastChgUsr', index: 'last_chg_usr', width: 80 },
			// { label: '最后修改日期', name: 'lastChgDt', index: 'last_chg_dt', width: 80 },
			// { label: '备用字段1', name: 'ext1', index: 'ext1', width: 80 },
			// { label: '备用字段2', name: 'ext2', index: 'ext2', width: 80 },
			// { label: '备用字段3', name: 'ext3', index: 'ext3', width: 80 },
			// { label: '备用字段4', name: 'ext4', index: 'ext4', width: 80 },
			// { label: '备用字段5', name: 'ext5', index: 'ext5', width: 80 }
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
		q:{
			name: null
		},
		showList: true,
		title: null,
        scanStr:'',
		invoCheckDetl: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
        exportExcel: function () {
			var url = 'invoice/invocheckdetl/exportExcel';
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: vm.q.name,
                success: function(r){
                    if(r.code == 0){
                        alert('导出成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
            vm.reload();
        },
		scan: function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "发票核查",
                area: ['700px', '200px'],
                shadeClose: false,
                content: jQuery("#scanLayer"),
                btn: ['查询','取消'],
                btn1: function (index) {
                    var data = "scanStr="+vm.scanStr;
                    debugger;
                    $.ajax({
                        type: "POST",
                        url: baseURL + "invoice/invocheckdetl/scan",
                        data: data,
                        dataType: "json",
                        success: function(result){
                            if(result.code == 0){
                                layer.close(index);
                                layer.alert('校验成功,详细信息请查看详情页面!', function(index){
                                    location.reload();
                                });
                            }else{
                                layer.alert('校验失败,' + result.msg);
                            }
                        }
                    });
                }
            });
        },
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.invoCheckDetl = {};
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
        view: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "查看";

            vm.getInfo(id)
        },
		saveOrUpdate: function (event) {
			var url = vm.invoCheckDetl.id == null ? "invoice/invocheckdetl/save" : "invoice/invocheckdetl/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.invoCheckDetl),
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
				    url: baseURL + "invoice/invocheckdetl/delete",
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
			$.get(baseURL + "invoice/invocheckdetl/info/"+id, function(r){
                vm.invoCheckDetl = r.invoCheckDetl;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		}
	}
});