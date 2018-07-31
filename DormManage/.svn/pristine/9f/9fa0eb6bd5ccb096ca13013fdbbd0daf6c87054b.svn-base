$(function(){
	var isCheckFlag = true;
	// 分页显示
	$("#dormbuild").datagrid({
		url:"./dormBuild/listByPage",
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
		    {field:"buildNo", title:"楼层编号", align:"center", width:50,resizable:false},      
		    {field:"buildName", title:"楼层名称", align:"center", width:50,resizable:false},      
		    {field:"buildType", title:"楼层类型", align:"center", width:30,resizable:false,
		    	formatter:function(value, row, index) {
		    		if (0 == row.buildType) {
		    			return "女";
		    		} else {
		    			return "男";
		    		}
		    	}
		    },      
		    {field:"buildIsDelete", title:"状态", align:"center", width:30,resizable:false,
		    	formatter:function(value, row, index) {
		    		if (0 == row.buildIsDelete) {
		    			return "正常";
		    		} else {
		    			return "已停用";
		    		}
		    	},
		    	styler:function(value, row, index) {
		    		if (0 == row.buildIsDelete) {
		    			return "color:green";
		    		} else {
		    			return "color:red";
		    		}
		    	}
		    },      
		    {field:"creater", title:"创建人", align:"center", width:30,resizable:false},      
		    {field:"createDate", title:"创建时间", align:"center", width:50,resizable:false},      
		    {field:"buildDescription", title:"楼层描述", align:"center", width:80,resizable:false},      
		    {field:"operation", title:"操作", align:"center", width:100,resizable:false,
		    	formatter:function(value, row, index) {
		    		var str = '<a href="javascript:void(0)" onclick="viewBuild(' + index + ')" name="view" class="easyui-tooltip" title="查看">查看</a>'
	    			+ '<a href="javascript:void(0)" onclick="editBuild(' + index + ')" style="margin-left:10px;" name="edit" class="easyui-tooltip" title="修改">修改</a>';
		    		if (0 == row.buildIsDelete) {
		    			str += '<a href="javascript:void(0)" onclick="operationBuild(&quot;' + row.id + "&quot;,&quot;" + row.buildName + "&quot;," + row.buildIsDelete + ')" style="margin-left:10px;" name="delete" class="easyui-tooltip" title="停用">停用</a>';
		    		} else {
		    			str += '<a href="javascript:void(0)" onclick="operationBuild(&quot;' + row.id + "&quot;,&quot;" + row.buildName + "&quot;," + row.buildIsDelete + ')" style="margin-left:10px;" name="recover" class="easyui-tooltip" title="启用">启用</a>';
		    		}
		    		return str;
		    	}
		    },      
		]],
		toolbar:"#datatools",
		onLoadSuccess:function(){
			$("a[name='view']").linkbutton({plain:true,iconCls:'icon-search'}); 
			$("a[name='edit']").linkbutton({plain:true,iconCls:'icon-edit'}); 
			$("a[name='delete']").linkbutton({plain:true,iconCls:'icon-no'}); 
			$("a[name='recover']").linkbutton({plain:true,iconCls:'icon-ok'}); 
			$('#dormbuild').datagrid('fixRowHeight'); //格式化后行号对齐
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
	$("#buildSearch").click(function(){
		var queryParams = $('#dormbuild').datagrid('options').queryParams;
		queryParams.buildNo = $('#buildNo').textbox("getValue");
		queryParams.buildType = $('#buildType').combobox("getValue");
		$('#dormbuild').datagrid('load');
	});
	
	// 重置查询条件
	$("#buildReset").click(function(){
		$('#buildNo').textbox("clear");
		$('#buildType').combobox("clear");
		var queryParams = $('#dormbuild').datagrid('options').queryParams;
		queryParams.buildNo = "";
		queryParams.buildType = "";
		$('#dormbuild').datagrid('load');
	});
	
	// 批量停用
	$("#batchDelete").click(function(){
		var selectRows = $("#dormbuild").datagrid("getSelections");
	    if(selectRows.length == 0){
	        $.messager.alert("提示信息","请选择至少一条数据",'warning');
	        return;
	    }
	    var ids = new Array();
	    for(var i = 0; i < selectRows.length; i++){
	    	if (0 == selectRows[i].buildIsDelete) {
	    		ids.push(selectRows[i].id);
	    	} else {
	    		$.messager.alert("系统提示", "请不要选择已经停用的楼层", "warning", function(){
	    			$("#dormbuild").datagrid("unselectAll");
	    			$("#dormbuild").datagrid("uncheckAll");
	    		});
	    		return;
	    	}
	    }
	    $.messager.confirm("系统提示","您确定要批量停用这<font color='red'>"+selectRows.length+"</font>个楼层吗？",function (r) {
	        if(r){
	            $.ajax({
	               url:"./dormBuild/logicBatchDelete",
	               type:"post",
	               data:{"ids":JSON.stringify(ids)},
	               success:function (data) {
	                    if("success" == data.status){
	                        $.messager.alert("系统提示",data.message,'info',function () {
	                            $("#dormbuild").datagrid("reload");
	                        });
	                    }else{
	                        $.messager.alert("系统提示", data.message, 'error',function(){
	                        	$("#dormbuild").datagrid("unselectAll");
		    	    			$("#dormbuild").datagrid("uncheckAll");
	                        });
	                    } 
	               },
	               error: function(xhr, status, err) {
		            	$.messager.alert("系统提示", "网络异常,请刷新重试！", "error", function(){
		            		$("#dormbuild").datagrid("unselectAll");
	    	    			$("#dormbuild").datagrid("uncheckAll");
		            	});
	                }
	            });
	        } else {
	        	$("#dormbuild").datagrid("unselectAll");
    			$("#dormbuild").datagrid("uncheckAll");
	        }
	    });
	});
	
	// 批量启用
	$("#batchRecover").click(function(){
		var selectRows = $("#dormbuild").datagrid("getSelections");
	    if(selectRows.length == 0){
	        $.messager.alert("提示信息","请选择至少一条数据",'warning');
	        return;
	    }
	    var ids = new Array();
	    for(var i = 0; i < selectRows.length; i++){
	    	if (1 == selectRows[i].buildIsDelete) {
	    		ids.push(selectRows[i].id);
	    	} else {
	    		$.messager.alert("系统提示", "请不要选择在用的楼层", "warning", function(){
	    			$("#dormbuild").datagrid("unselectAll");
	    			$("#dormbuild").datagrid("uncheckAll");
	    		});
	    		return;
	    	}
	    }
	    $.messager.confirm("系统提示","您确定要批量启用这<font color='red'>"+selectRows.length+"</font>个楼层吗？",function (r) {
	        if(r){
	            $.ajax({
	               url:"./dormBuild/batchRecover",
	               type:"post",
	               data:{"ids":JSON.stringify(ids)},
	               success:function (data) {
	                    if("success" == data.status){
	                        $.messager.alert("系统提示",data.message,'info',function () {
	                            $("#dormbuild").datagrid("reload");
	                        });
	                    }else{
	                        $.messager.alert("系统提示", data.message, 'error');
	                    } 
	               },
	               error: function(xhr, status, err) {
		            	$.messager.alert("系统提示", "网络异常,请刷新重试！", "error");
	                }
	            });
	        } else {
	        	$("#dormbuild").datagrid("unselectAll");
    			$("#dormbuild").datagrid("uncheckAll");
	        }
	    });
	});
	
	
	// 添加楼层
	function addBuild() {
		if ($("#buildform").form("validate")) {
			var buildNo = $("#dormBuildNo").textbox("getValue");
			var buildName = $("#buildName").textbox("getValue");
			var buildType = $("#buildform input[name='buildType']:checked").val();
			var description = $("#description").val();
			var postData = {"buildNo":buildNo,"buildType":buildType,"buildName":buildName,
					"buildDescription":description};
			$.ajax({
				url:"./dormBuild/saveBuild",
				type:"post",
				data:JSON.stringify(postData),
				contentType:"application/json;charset=utf-8",
				dataType:"json",
				success:function(data) {
					if ("success" == data.status) {
						$.messager.alert("系统提示", data.message, "info", function(){
							$("#addBuildDiv").dialog("close");
							$("#dormbuild").datagrid("load");
						});
					} else {
						$.messager.alert("系统提示", data.message, "error");
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
	$("#openAddBuild").click(function(){
		$('#addBuildDiv').dialog({
	        title:'添加楼层',
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
	            		addBuild();
	            	}
	            },
	            {
	            	text:"取消",
	            	iconCls:"icon-cancel",
	            	handler:function(){
	            		$("#addBuildDiv").dialog("close");
	            	}
	            }
	        ],
	        onBeforeClose:function(){
	           $("#buildform").form("reset");
	        }
	    });
	});
	
});

	//打开查看窗口
	function openViewDialog(index) {
		$('#searchBuildDiv').dialog({
			title:'查看楼层(只读)',
			width:380,
			height:400,
			modal:true,
			minimizable:false,
			maximizable:false,
			collapsible:false,
			closable:true,
			resizable:false,
			draggable:false,
			iconCls:'icon-search',
			buttons:[
			    {
			       text:"关闭",
			       iconCls:"icon-cancel",
			       handler:function(){
			    	   $("#searchBuildDiv").dialog("close");
			       }
			    }
			],
			onBeforeClose:function(){
			   $("#dormbuild").datagrid("unselectRow", index);	
	           $("#searchform").form("clear");
	        }
		});
	}
	
	// 查看
	function viewBuild(index) {
		$("#dormbuild").datagrid("selectRow", index); // 手动选择行
		var row = $("#dormbuild").datagrid("getSelected");
		if (row) {
			row.buildIsDelete = formatIsDelete(row.buildIsDelete);
			row.buildType = formatBuildType(row.buildType);
			$("#searchform").form("load", row);
			openViewDialog(index);
		}
	}
	
	//打开修改窗口
	function openEditDialog(index) {
		$('#editBuildDiv').dialog({
			title:'修改楼层',
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
				    	editDormBuild();
				    }
				 },
			    {
			       text:"取消",
			       iconCls:"icon-cancel",
			       handler:function(){
			    	   $("#editBuildDiv").dialog("close");
			    	   $("#dormbuild").datagrid("unselectRow", index);
			       }
			    }
			],
			onBeforeClose:function(){
	           $("#editform").form("clear");
	        }
		});
	}
	
	// 修改回显
	function editBuild(index) {
		$("#dormbuild").datagrid("selectRow", index); // 手动选择行
		var row = $("#dormbuild").datagrid("getSelected");
		if ("男" == row.buildType) {
			row.buildType = 1;
		}
		if ("女" == row.buildType) {
			row.buildType = 0;
		}
		if (row) {
			$("#editform").form("load", row);
			openEditDialog(index);
		}
	}
	
	// 修改楼层
	function editDormBuild(){
		if($("#editform").form("validate")) {
			var id = $("#buildId").val();
			var buildName = $("#editName").textbox("getValue");
			var buildType = $("#editform input[name='buildType']:checked").val();
			var buildDescription = $("#buildDescription").val();
			$.ajax({
				url:"./dormBuild/update",
				type:"put",
				dataType:"json",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify({"id":id,"buildName":buildName,"buildType":buildType,
					"buildDescription":buildDescription}),
				success:function (data) {
			         if ("success" == data.status) {
			            $.messager.alert("系统提示", data.message, "info", function(){
			            	$("#editBuildDiv").dialog("close");
			            	$("#dormbuild").datagrid("reload");
			            });
			         } else {
			            $.messager.alert("系统提示", data.message, "error");
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
	
	// 格式化状态
	function formatIsDelete(isDelete) {
		if (0 == isDelete) {
			return "在用";
		} else {
			return "停用";
		}
	}
	
	// 格式化性别
	function formatBuildType(buildType) {
		if (0 == buildType) {
			return "女";
		} 
		if (1 == buildType){
			return "男";
		}
	}

	// 根据id停用、启用楼层
	function operationBuild(id, buildName, isDelete) {
		var url;
		var type;
		var tip;
		if (0 == isDelete) {
			url = "./dormBuild/logicDeleteById/" + id;
			type = "delete";
			tip = "确定停用<font color='red'>" + buildName + "</font>吗?";
		} else {
			url = "./dormBuild/recoverById/" + id;
			type = "put";
			tip = "确定启用<font color='red'>" + buildName + "</font>吗?";
		}
		$.messager.confirm("系统提示", tip,function(r) {
			if (r) {
				$.ajax({
		           url:url,
		           type:type,
		           success:function (data) {
			            if ("success" == data.status) {
			            	$.messager.alert("系统提示", data.message, "info", function(){
			            		$("#dormbuild").datagrid("reload");
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