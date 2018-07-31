$(function(){
	var isCheckFlag = true;
	// 分页显示
	$("#dorm").datagrid({
		url:"./dorm/listByPage",
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
		    {field:"dormNo", title:"宿舍号", align:"center", width:60,resizable:false},
		    {field:"buildName", title:"所属楼层", align:"center", width:60,resizable:false,
		    	formatter:function(value, row, index) {
		    		return row.dormBuild.buildName;
		    	}
		    },      
		    {field:"dormCapacity", title:"容量", align:"center", width:20,resizable:false},      
		    {field:"dormNum", title:"实住人数", align:"center", width:20,resizable:false},      
		    {field:"isDelete", title:"状态", align:"center", width:20,resizable:false,
		    	formatter:function(value, row, index) {
		    		if (0 == row.isDelete) {
		    			return "正常";
		    		} else {
		    			return "已停用";
		    		}
		    	},
		    	styler:function(value, row, index) {
		    		if (0 == row.isDelete) {
		    			return "color:green";
		    		} else {
		    			return "color:red";
		    		}
		    	}
		    },      
		    {field:"creater", title:"创建人", align:"center", width:40,resizable:false},      
		    {field:"createDate", title:"创建时间", align:"center", width:60,resizable:false},      
		    {field:"operation", title:"操作", align:"center", width:60,resizable:false,
		    	formatter:function(value, row, index) {
		    		var str = '<a href="javascript:void(0)" onclick="viewDorm(' + index + ')" name="view" class="easyui-tooltip" title="查看">查看</a>'
	    			+ '<a href="javascript:void(0)" onclick="editDorm(' + index + ')" style="margin-left:10px;" name="edit" class="easyui-tooltip" title="修改">修改</a>';
		    		if (0 == row.isDelete) {
		    			str += '<a href="javascript:void(0)" onclick="operationDorm(&quot;' + row.id + "&quot;,&quot;" 
		    			+ row.dormNo + "&quot;," + row.isDelete + "," + row.dormBuild.buildIsDelete + "," + row.dormNum + ')" style="margin-left:10px;" name="delete" class="easyui-tooltip" title="停用">停用</a>';
		    		} else {
		    			str += '<a href="javascript:void(0)" onclick="operationDorm(&quot;' + row.id + "&quot;,&quot;" 
		    			+ row.dormNo + "&quot;," + row.isDelete + "," + row.dormBuild.buildIsDelete + "," + row.dormNum + ')" style="margin-left:10px;" name="recover" class="easyui-tooltip" title="启用">启用</a>';
		    		}
		    		return str;
		    	}
		    },      
		]],
		toolbar:"#dormtools",
		onLoadSuccess:function(){
			$("a[name='view']").linkbutton({plain:true,iconCls:'icon-search'}); 
			$("a[name='edit']").linkbutton({plain:true,iconCls:'icon-edit'}); 
			$("a[name='delete']").linkbutton({plain:true,iconCls:'icon-no'}); 
			$("a[name='recover']").linkbutton({plain:true,iconCls:'icon-ok'}); 
			$('#dorm').datagrid('fixRowHeight'); //格式化后行号对齐
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
	$("#dormSearch").click(function(){
		var queryParams = $('#dorm').datagrid('options').queryParams;
		queryParams.dormNo = $('#dormNo').textbox("getValue");
		queryParams.dormCapacity = $('#dormCapacity').spinner("getValue");
		queryParams.buildName = $("#searchCombobox").combobox("getValue");
		$('#dorm').datagrid('load');
	});
	
	// 重置查询条件
	$("#dormReset").click(function(){
		$('#dormNo').textbox("clear");
		$('#dormCapacity').spinner("clear");
		$("#searchCombobox").combobox("clear");
		var queryParams = $('#dorm').datagrid('options').queryParams;
		queryParams.dormNo = "";
		queryParams.dormCapacity = "";
		queryParams.buildName = "";
		$('#dorm').datagrid('load');
	});
	
	// 批量停用
	$("#batchDeleteDorm").click(function(){
		var selectRows = $("#dorm").datagrid("getSelections");
	    if(selectRows.length == 0){
	        $.messager.alert("提示信息","请选择至少一条数据",'warning');
	        return;
	    }
	    var ids = new Array();
	    for(var i = 0; i < selectRows.length; i++){
	    	if (0 == selectRows[i].isDelete) {
	    		if (selectRows[i].dormNum > 0){
	    			$.messager.alert("系统提示","尚有宿舍存在学生,不能停用","warning", function(){
	    				$("#dorm").datagrid("unselectAll");
		    			$("#dorm").datagrid("uncheckAll");
	    			});
	    			return;
	    		}
	    		ids.push(selectRows[i].id);
	    	} else {
	    		$.messager.alert("系统提示", "请不要选择已经停用的宿舍", "warning", function(){
	    			$("#dorm").datagrid("unselectAll");
	    			$("#dorm").datagrid("uncheckAll");
	    		});
	    		return;
	    	}
	    }
	    $.messager.confirm("系统提示","您确定要批量停用这<font color='red'>"+selectRows.length+"</font>个宿舍吗？",function (r) {
	        if(r){
	            $.ajax({
	               url:"./dorm/logicBatchDelete",
	               type:"post",
	               data:{"ids":JSON.stringify(ids)},
	               success:function (data) {
	                    if("success" == data.status){
	                        $.messager.alert("系统提示",data.message,'info',function () {
	                            $("#dorm").datagrid("reload");
	                        });
	                    }else{
	                        $.messager.alert("系统提示", data.message, 'error', function(){
	                        	$("#dorm").datagrid("unselectAll");
	        	    			$("#dorm").datagrid("uncheckAll");
	                        });
	                    } 
	               },
	               error: function(xhr, status, err) {
		            	$.messager.alert("系统提示", "网络异常,请刷新重试！", "error", function(){
		            		$("#dorm").datagrid("unselectAll");
			    			$("#dorm").datagrid("uncheckAll");
		            	});
	                }
	            });
	        } else {
	        	$("#dorm").datagrid("unselectAll");
    			$("#dorm").datagrid("uncheckAll");
	        }
	    });
	});
	
	// 批量启用
	$("#batchRecoverDorm").click(function(){
		var selectRows = $("#dorm").datagrid("getSelections");
	    if(selectRows.length == 0){
	        $.messager.alert("提示信息","请选择至少一条数据",'warning');
	        return;
	    }
	    var ids = new Array();
	    for(var i = 0; i < selectRows.length; i++){
	    	if (1 == selectRows[i].isDelete) {
	    		if (1 == selectRows[i].dormBuild.buildIsDelete){
	    			$.messager.alert("系统提示", "尚有楼层未启用,不能启用宿舍", "warning", function(){
		    			$("#dorm").datagrid("unselectAll");
		    			$("#dorm").datagrid("uncheckAll");
		    		});
		    		return;
	    		}
	    		ids.push(selectRows[i].id);
	    	} else {
	    		$.messager.alert("系统提示", "请不要选择在用的宿舍", "warning", function(){
	    			$("#dorm").datagrid("unselectAll");
	    			$("#dorm").datagrid("uncheckAll");
	    		});
	    		return;
	    	}
	    }
	    $.messager.confirm("系统提示","您确定要批量启用这<font color='red'>"+selectRows.length+"</font>个宿舍吗？",function (r) {
	        if(r){
	            $.ajax({
	               url:"./dorm/batchRecover",
	               type:"post",
	               data:{"ids":JSON.stringify(ids)},
	               success:function (data) {
	                    if("success" == data.status){
	                        $.messager.alert("系统提示",data.message,'info',function () {
	                            $("#dorm").datagrid("reload");
	                        });
	                    }else{
	                        $.messager.alert("系统提示", data.message, 'error',function(){
	                        	$("#dorm").datagrid("unselectAll");
	        	    			$("#dorm").datagrid("uncheckAll");
	                        });
	                    } 
	               },
	               error: function(xhr, status, err) {
		            	$.messager.alert("系统提示", "网络异常,请刷新重试！", "error", function(){
		            		$("#dorm").datagrid("unselectAll");
			    			$("#dorm").datagrid("uncheckAll");
		            	});
	                }
	            });
	        } else {
	        	$("#dorm").datagrid("unselectAll");
    			$("#dorm").datagrid("uncheckAll");
	        }
	    });
	});
	
	
	// 添加宿舍
	function addDorm() {
		if ($("#addform").form("validate")) {
			var dormNo = $("#dormitoryNo").textbox("getValue");
			var dormCapacity = $("#capacity").textbox("getValue");
			var buildId = $("#dormBuild").combobox("getValue");
			var postData = {"dormNo":dormNo,"dormCapacity":dormCapacity,"buildId":buildId};
			$.ajax({
				url:"./dorm/save",
				type:"post",
				data:JSON.stringify(postData),
				contentType:"application/json;charset=utf-8",
				dataType:"json",
				success:function(data) {
					if ("success" == data.status) {
						$.messager.alert("系统提示", data.message, "info", function(){
							$("#addDormDiv").dialog("close");
							$("#dorm").datagrid("load");
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
	$("#openAddDorm").click(function(){
		$('#addDormDiv').dialog({
	        title:'添加宿舍',
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
	            		addDorm();
	            	}
	            },
	            {
	            	text:"取消",
	            	iconCls:"icon-cancel",
	            	handler:function(){
	            		$("#addDormDiv").dialog("close");
	            	}
	            }
	        ],
	        onBeforeClose:function(){
	           $("#addform").form("reset");
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
			$("#dormBuild").combobox({
				data:dormBuild,
				textField:"buildName",
				valueField:"id",
			});
			$("#myBuild").combobox({
				data:dormBuild,
				textField:"buildName",
				valueField:"id",
			});
			$("#searchCombobox").combobox({
				prompt:"所在楼层...",
				width:120,
				data:dormBuild,
				textField:"buildName",
				valueField:"buildName",
			});
		},
		error: function(xhr, status, err) {
            $.messager.alert("系统提示", "网络异常,请刷新重试！", "error");
        }
	});
	
});
	
	
	//打开查看窗口
	function openViewDorm(index) {
		$('#searchDormDiv').dialog({
			title:'查看宿舍(只读)',
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
			    	   $("#searchDormDiv").dialog("close");
			       }
			    }
			],
			onBeforeClose:function(){
				$("#dorm").datagrid("unselectRow", index);
	            $("#seeDorm").form("reset");
	        }
		});
	}
	
	// 查看
	function viewDorm(index) {
		$("#dorm").datagrid("selectRow", index); // 手动选择行
		var row = $("#dorm").datagrid("getSelected");
		if (row) {
			row.isDelete = formatDelete(row.isDelete);
			row.buildName = row.dormBuild.buildName;
			$("#seeDorm").form("load", row);
			openViewDorm(index);
		}
	}
	
	//打开修改窗口
	function openEditDorm(index) {
		$('#editDormDiv').dialog({
			title:'修改宿舍',
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
				    	modifyDorm();
				    }
				 },
			    {
			       text:"取消",
			       iconCls:"icon-cancel",
			       handler:function(){
			    	   $("#editDormDiv").dialog("close");
			       }
			    }
			],
			onBeforeClose:function(){
				$("#dorm").datagrid("unselectRow", index);
	            $("#editDorm").form("reset");
	        }
		});
	}
	
	// 修改回显
	function editDorm(index) {
		$("#dorm").datagrid("selectRow", index); // 手动选择行
		var row = $("#dorm").datagrid("getSelected");
		if (row) {
			$("#myBuild").combobox("setValue", row.buildId);
			$("#myBuild").combobox("setText", row.dormBuild.buildName);
			$("#editDorm").form("load", row);
			openEditDorm(index);
		}
	}
	
	// 修改宿舍
	function modifyDorm(){
		if ($("#editDorm").form("validate")) {
			var id = $("#dormId").val();
			var dormCapacity = $("#editCapacity").textbox("getValue");
			var buildId = $("#myBuild").combobox("getValue");
			$.ajax({
				url:"./dorm/update",
				type:"put",
				dataType:"json",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify({"id":id,"dormCapacity":dormCapacity,"buildId":buildId}),
				success:function (data) {
			         if ("success" == data.status) {
			            $.messager.alert("系统提示", data.message, "info", function(){
			            	$("#editDormDiv").dialog("close");
			            	$("#dorm").datagrid("reload");
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
	function formatDelete(isDelete) {
		if (0 == isDelete) {
			return "在用";
		} else {
			return "停用";
		}
	}
	
	// 根据id停用、启用宿舍
	function operationDorm(id, dormNo, isDelete, buildIsDelete,dormNum) {
		var url;
		var type;
		var tip;
		if (0 == isDelete) {
			if (dormNum > 0) {
				$.messager.alert("系统提示", "该宿舍尚有学生,不能停用", "warning");
				return;
			}
			url = "./dorm/logicDeleteById/" + id;
			type = "delete";
			tip = "确定停用<font color='red'>" + dormNo + "</font>吗?";
		} else {
			if (1 == buildIsDelete) {
				$.messager.alert("系统提示", "宿舍所在宿舍楼还未启用,不能启用宿舍", "warning");
				return;
			}
			url = "./dorm/recoverById/" + id;
			type = "put";
			tip = "确定启用<font color='red'>" + dormNo + "</font>吗?";
		}
		$.messager.confirm("系统提示", tip,function(r) {
			if (r) {
				$.ajax({
		           url:url,
		           type:type,
		           success:function (data) {
			            if ("success" == data.status) {
			            	$.messager.alert("系统提示", data.message, "info", function(){
			            		$("#dorm").datagrid("reload");
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