$(function(){
	var isCheckFlag = true;
	// 分页显示
	$("#record").datagrid({
		url:"./record/listByPage",
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
		    {field:"articleName", title:"名称", align:"center", width:50,resizable:false},
		    {field:"enterTime", title:"进楼时间", align:"center", width:40,resizable:false},
		    {field:"outTime", title:"出楼时间", align:"center", width:40,resizable:false},
		    {field:"buildName", title:"所属楼层", align:"center", width:40,resizable:false,
		    	formatter:function(value, row, index) {
		    		return row.dormBuild.buildName;
		    	}
		    },      
		    {field:"creater", title:"创建人", align:"center", width:30,resizable:false},      
		    {field:"createDate", title:"创建时间", align:"center", width:50,resizable:false,
		    	formatter:function(value, row, index) {
		    		return row.createDate.split(" ")[0];
		    	}
		    },      
		    {field:"operation", title:"操作", align:"center", width:50,resizable:false,
		    	formatter:function(value, row, index) {
		    		var str = '<a href="javascript:void(0)" onclick="editRecord(' + index + ')" name="edit" class="easyui-tooltip" title="修改">修改</a>'
		    		+ '<a href="javascript:void(0)" onclick="deleteRecord(&quot;' + row.id + "&quot;,&quot;" 
		    		+ row.articleName + '&quot;)" style="margin-left:20px;" name="delete" class="easyui-tooltip" title="删除">删除</a>';
		    		return str;
		    	}
		    },      
		]],
		toolbar:"#recordtools",
		onLoadSuccess:function(){
			$("a[name='edit']").linkbutton({plain:true,iconCls:'icon-edit'}); 
			$("a[name='delete']").linkbutton({plain:true,iconCls:'icon-no'}); 
			$('#record').datagrid('fixRowHeight'); //格式化后行号对齐
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
	$("#recordSearch").click(function(){
		var queryParams = $('#record').datagrid('options').queryParams;
		queryParams.articleName = $('#recordName').textbox("getValue");
		$('#record').datagrid('load');
	});
	
	// 重置查询条件
	$("#recordReset").click(function(){
		$('#recordName').textbox("clear");
		var queryParams = $('#record').datagrid('options').queryParams;
		queryParams.articleName = "";
		$('#record').datagrid('load');
	});
	
	// 批量删除
	$("#batchDeleteRecord").click(function(){
		var selectRows = $("#record").datagrid("getSelections");
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
	               url:"./record/batchDelete",
	               type:"post",
	               data:{"ids":JSON.stringify(ids)},
	               success:function (data) {
	                    if("success" == data.status){
	                        $.messager.alert("系统提示",data.message,'info',function () {
	                            $("#record").datagrid("reload");
	                        });
	                    } else {
	                        $.messager.alert("系统提示", data.message, 'error', function(){
	                        	$("#record").datagrid("unselectAll");
	        	    			$("#record").datagrid("uncheckAll");
	                        });
	                    } 
	               },
	               error: function(xhr, status, err) {
		            	$.messager.alert("系统提示", "网络异常,请刷新重试！", "error", function(){
		            		$("#record").datagrid("unselectAll");
			    			$("#record").datagrid("uncheckAll");
		            	});
	                }
	            });
	        } else {
	        	$("#record").datagrid("unselectAll");
    			$("#record").datagrid("uncheckAll");
	        }
	    });
	});
	
	// 添加
	function addRecord() {
		if ($("#addRecordForm").form("validate")) {
			var articleName = $("#addName").textbox("getValue");
			var enterTime = $("#addEnterTime").datetimebox("getValue");
			var outTime = $("#addOutTime").datetimebox("getValue");
			var buildId = $("#addRecordBuild").combobox("getValue");
			if ("" == enterTime && "" == outTime) {
				$.messager.alert("系统提示", "请选择进出楼时间", "warning");
				return;
			}
			if ("" != enterTime && "" != outTime) {
				$.messager.alert("系统提示", "请只选择进楼或出楼时间", "warning", function(){
					$("#addEnterTime").datetimebox("clear");
					$("#addOutTime").datetimebox("clear");
				});
				return;
			}
			var postData = JSON.stringify({"articleName":articleName,"enterTime":enterTime,"outTime":outTime,
				"buildId":buildId});
			$.ajax({
				url:"./record/save",
				type:"post",
				contentType:"application/json",
				data:postData,
				success:function(data){
					if ("success" == data.status) {
						$.messager.alert("系统提示", data.message, "info", function(){
							$("#record").datagrid("load");
							$("#addRecordDiv").dialog("close");
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
	$("#openAddRecord").click(function(){
		$('#addRecordDiv').dialog({
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
	            		addRecord();
	            	}
	            },
	            {
	            	text:"取消",
	            	iconCls:"icon-cancel",
	            	handler:function(){
	            		$("#addRecordDiv").dialog("close");
	            	}
	            }
	        ],
	        onBeforeClose:function(){
	           $("#addRecordForm").form("reset");
	        }
	    });
	});
	
 // 动态加载下拉列表
	$.ajax({
		url:"./dormBuild/listAll",
		type:"get",
		success:function(data) {
			var dormBuild = [];
			for (var i = 0; i < data.content.length; i++) {
				dormBuild.push({"id":data.content[i].id, "buildName":data.content[i].buildName});
			}
			$("#addRecordBuild").combobox({
				data:dormBuild,
				textField:"buildName",
				valueField:"id",
			});
			$("#editRecordBuild").combobox({
				data:dormBuild,
				textField:"buildName",
				valueField:"id",
			});
		},
		error: function(xhr, status, err) {
            $.messager.alert("系统提示", "网络异常,请刷新重试！", "error");
        }
	});
    
});
	
	//打开修改窗口
	function openEditRecord(index) {
		$('#editRecordDiv').dialog({
			title:'修改记录',
			width:380,
			height:350,
			modal:true,
			minimizable:false,
			maximizable:false,
			collapsible:false,
			closable:false,
			resizable:false,
			draggable:false,
			iconCls:'icon-edit',
			buttons:[
				{
				    text:"保存",
				    iconCls:"icon-save",
				    handler:function(){
				    	modifyRecord();
				    }
				 },
			    {
			       text:"取消",
			       iconCls:"icon-cancel",
			       handler:function(){
			    	   $("#editRecordDiv").dialog("close");
			       }
			    }
			],
			onBeforeClose:function(){
				$("#record").datagrid("unselectRow", index);
	            $("#editRecordForm").form("reset");
	        }
		});
	}
	
	// 修改回显
	function editRecord(index) {
		$("#record").datagrid("selectRow", index); // 手动选择行
		var row = $("#record").datagrid("getSelected");
		if (row) {
			$("#editRecordBuild").combobox("setValue", row.buildId);
			$("#editRecordBuild").combobox("setText", row.dormBuild.buildName);
			$("#editRecordForm").form("load", row);
			openEditRecord(index);
		}
	}
	
	// 修改
	function modifyRecord(){
		if ($("#editRecordForm").form("validate")) {
			var id = $("#articleId").val();
			var articleName = $("#editName").textbox("getValue");
			var enterTime = $("#editEnterTime").datetimebox("getValue");
			var outTime = $("#editOutTime").datetimebox("getValue");
			var buildId = $("#editRecordBuild").combobox("getValue");
			var postData = JSON.stringify({"id":id,"articleName":articleName,"enterTime":enterTime,
				"outTime":outTime,"buildId":buildId});
			$.ajax({
				url:"./record/update",
				type:"put",
				contentType:"application/json",
				data:postData,
				success:function(data){
					if ("success" == data.status) {
						$.messager.alert("系统提示", data.message, "info", function(){
							$("#record").datagrid("load");
							$("#editRecordDiv").dialog("close");
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
	
	// 根据id删除
	function deleteRecord(id, artileName) {
		$.messager.confirm("系统提示", "确定删除名称为<font color='red'>" + artileName + "</font>的记录吗?" , function(r) {
			if (r) {
				$.ajax({
		           url:"./record/deleteById/" + id,
		           type:"delete",
		           success:function (data) {
			            if ("success" == data.status) {
			            	$.messager.alert("系统提示", data.message, "info", function(){
			            		$("#record").datagrid("reload");
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