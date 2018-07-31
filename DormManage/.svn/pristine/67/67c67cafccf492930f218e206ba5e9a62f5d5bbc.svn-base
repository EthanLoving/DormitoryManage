$(function(){
	var isCheckFlag = true;
	// 分页显示
	$("#vistor").datagrid({
		url:"./vistor/listByPage",
		fit:true,
		fitColumns:true,
		rownumbers:true,
        pagination:true,
        pageNumber:1,
        striped:true,
		pageSize:10,
		pageList:[10,20,30,50],
		queryParams:{},
		loadFilter:function(data){
			return data.content;
		},
		loadMsg:'正在加载数据，请稍候...',
		columns:[[
		    {field:"ids", checkbox:true},
		    {field:"vistorName", title:"来访人", align:"center", width:30,resizable:false},
		    {field:"vistorPhone", title:"手机号", align:"center", width:30,resizable:false},
		    {field:"vistorIdcard", title:"身份证号", align:"center", width:40,resizable:false},
		    {field:"startTime", title:"来访时间", align:"center", width:45,resizable:false},
		    {field:"endTime", title:"结束时间", align:"center", width:45,resizable:false},
		    {field:"vistedName", title:"被访人", align:"center", width:30,resizable:false},
		    {field:"creater", title:"创建人", align:"center", width:30,resizable:false},      
		    {field:"createDate", title:"创建时间", align:"center", width:35,resizable:false,
		    	formatter:function(value, row, index) {
		    		return row.createDate.split(" ")[0];
		    	}
		    },      
		    {field:"operation", title:"操作", align:"center", width:50,resizable:false,
		    	formatter:function(value, row, index) {
		    		var str;
		    		if (null == row.endTime || "" == row.endTime) {
		    			str = '<a href="javascript:void(0)" onclick="editVistor(&quot;' + row.id + "&quot;,&quot;" + row.vistorName + '&quot;)" name="edit" class="easyui-tooltip" title="结束访问">结束访问</a>'
			    		+ '<a href="javascript:void(0)" onclick="deleteVistor(&quot;' + row.id + "&quot;,&quot;" 
			    		+ row.vistorName + '&quot;)" style="margin-left:10px;" name="delete" class="easyui-tooltip" title="删除">删除</a>';
		    		} else {
		    			str = '<a href="javascript:void(0)" onclick="deleteVistor(&quot;' + row.id + "&quot;,&quot;" 
			    		+ row.vistorName + '&quot;)" name="delete" class="easyui-tooltip" title="删除">删除</a>';
		    		}
		    		return str;
		    	}
		    },      
		]],
		toolbar:"#vistortools",
		onLoadSuccess:function(){
			$("a[name='edit']").linkbutton({plain:true,iconCls:'icon-edit'}); 
			$("a[name='delete']").linkbutton({plain:true,iconCls:'icon-no'}); 
			$('#vistor').datagrid('fixRowHeight'); //格式化后行号对齐
		},
		onClickCell: function (rowIndex, field, value) {
			isCheckFlag = false;
		},
	    onSelect: function (rowIndex, rowData) {
			if (!isCheckFlag) {
			   isCheckFlag = true;
			   $(this).datagrid("unselectRow", rowIndex);
			}
		},                    
		 onUnselect: function (rowIndex, rowData) {
			if (!isCheckFlag) {
			    isCheckFlag = true;
		        $(this).datagrid("selectRow", rowIndex);
			}
		}
	});
	
	// 查询
	$("#vistorSearch").click(function(){
		var queryParams = $('#vistor').datagrid('options').queryParams;
		queryParams.vistorName = $('#vistorName').textbox("getValue");
		queryParams.vistedName = $('#vistedName').textbox("getValue");
		$('#vistor').datagrid('load');
	});
	
	// 重置查询条件
	$("#vistorReset").click(function(){
		$('#vistorName').textbox("clear");
		$('#vistedName').textbox("clear");
		var queryParams = $('#vistor').datagrid('options').queryParams;
		queryParams.vistorName = "";
		queryParams.vistedName = "";
		$('#vistor').datagrid('load');
	});
	
	// 批量删除
	$("#batchDeleteVistor").click(function(){
		var selectRows = $("#vistor").datagrid("getSelections");
	    if(selectRows.length == 0){
	        $.messager.alert("提示信息","请选择至少一条数据",'warning');
	        return;
	    }
	    var ids = new Array();
	    for(var i = 0; i < selectRows.length; i++){
	    	ids.push(selectRows[i].id);
	    }
	    $.messager.confirm("系统提示","您确定要批量删除这<font color='red'>"+selectRows.length+"</font>条记录吗？",function (r) {
	        if(r){
	            $.ajax({
	               url:"./vistor/batchDelete",
	               type:"post",
	               data:{"ids":JSON.stringify(ids)},
	               success:function (data) {
	                    if("success" == data.status){
	                        $.messager.alert("系统提示",data.message,'info',function () {
	                            $("#vistor").datagrid("reload");
	                        });
	                    } else {
	                        $.messager.alert("系统提示", data.message, 'error', function(){
	                        	$("#vistor").datagrid("unselectAll");
	        	    			$("#vistor").datagrid("uncheckAll");
	                        });
	                    } 
	               },
	               error: function(xhr, status, err) {
		            	$.messager.alert("系统提示", "网络异常,请刷新重试！", "error", function(){
		            		$("#vistor").datagrid("unselectAll");
			    			$("#vistor").datagrid("uncheckAll");
		            	});
	                }
	            });
	        } else {
	        	$("#vistor").datagrid("unselectAll");
    			$("#vistor").datagrid("uncheckAll");
	        }
	    });
	});
	
	// 添加
	function addVistor() {
		if ($("#addVistorForm").form("validate")) {
			var vistorName = $("#addVistorName").textbox("getValue");
			var vistorPhone = $("#addVistorPhone").textbox("getValue");
			var vistorIdcard = $("#addVistorIdcard").textbox("getValue");
			var vistedName = $("#addVistedName").textbox("getValue");
			var postData = JSON.stringify({"vistorName":vistorName,"vistorPhone":vistorPhone,
				"vistorIdcard":vistorIdcard,"vistedName":vistedName});
			$.ajax({
				url:"./vistor/save",
				type:"post",
				contentType:"application/json",
				data:postData,
				success:function(data){
					if ("success" == data.status) {
						$.messager.alert("系统提示", data.message, "info", function(){
							$("#vistor").datagrid("load");
							$("#addVistorDiv").dialog("close");
						});
					} else {
						 $.messager.alert("系统提示", data.message , "error");
					}
				},
				error: function(xhr, status, err) {
		            $.messager.alert("系统提示", "网络异常,请刷新重试！", "error");
		        }
			});
		} else {
			$.messager.alert("系统提示", "表单填写不规范", "warning");
		}
	}
	
	// 打开添加窗口
	$("#openAddVistor").click(function(){
		$('#addVistorDiv').dialog({
	        title:'添加记录',
	        width:380,
	        height:350,
	        modal:true,
	        minimizable:false,
	        maximizable:false,
	        collapsible:false,
	        closable:false,
	        resizable:false,
	        draggable:false,
	        iconCls:'icon-add',
	        buttons:[
	            {
	            	text:"添加",
	            	iconCls:"icon-ok",
	            	handler:function(){
	            		addVistor();
	            	}
	            },
	            {
	            	text:"取消",
	            	iconCls:"icon-cancel",
	            	handler:function(){
	            		$("#addVistorDiv").dialog("close");
	            	}
	            }
	        ],
	        onBeforeClose:function(){
	           $("#addVistorForm").form("reset");
	        }
	    });
	});
	
});
	
	// 结束访问
	function editVistor(id, vistorName) {
		$.messager.confirm("系统提示","确定结束<font color='red'>" + vistorName + "</font>的访问吗?", function(r){
			if (r) {
				$.ajax({
					url:"./vistor/update",
					type:"put",
					contentType:"application/json",
					dataType:"json",
					data:JSON.stringify({"id":id}),
					success:function(data){
						if ("success" == data.status) {
							$.messager.alert("系统提示", data.message, "info", function(){
								$("#vistor").datagrid("load");
							});
						} else {
							 $.messager.alert("系统提示", data.message , "error");
						}
					},
					error: function(xhr, status, err) {
			            $.messager.alert("系统提示", "网络异常,请刷新重试！", "error");
			        }
				});
			}
		});
	}
	
	// 根据id删除
	function deleteVistor(id, vistorName) {
		$.messager.confirm("系统提示", "确定删除来访人<font color='red'>" + vistorName + "</font>的记录吗?" , function(r) {
			if (r) {
				$.ajax({
		           url:"./vistor/deleteById/" + id,
		           type:"delete",
		           success:function (data) {
			            if ("success" == data.status) {
			            	$.messager.alert("系统提示", data.message, "info", function(){
			            		$("#vistor").datagrid("reload");
			            	});
			            } else {
			            	$.messager.alert("系统提示", data.message, "error");
			            }
		            },
		            error: function(xhr, status, err) {
		            	$.messager.alert("系统提示", "网络异常,请刷新重试！", "error");
	                }
		        });
			}
		});
	}