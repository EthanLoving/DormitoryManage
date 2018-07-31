$(function(){
	var isCheckFlag = true;
	// 分页显示
	$("#employee").datagrid({
		url:"./employee/listByPage",
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
		    {field:"empNo", title:"员工号", align:"center", width:60,resizable:false},
		    {field:"empName", title:"姓名", align:"center", width:40,resizable:false},
		    {field:"empSex", title:"性别", align:"center", width:20,resizable:false,
		    	formatter:function(value, row, index) {
		    		if (0 == row.empSex) {
		    			return "女";
		    		} else {
		    			return "男";
		    		}
		    	}
		    },
		    {field:"birthday", title:"生日", align:"center",width:40,resizable:false},
		    {field:"phone", title:"手机号", align:"center",width:40,resizable:false},
		    {field:"buildName", title:"所属楼层", align:"center", width:40,resizable:false,
		    	formatter:function(value, row, index) {
		    		return row.dormBuild.buildName;
		    	}
		    },      
		    {field:"job", title:"职位", align:"center", width:30,resizable:false},      
		    {field:"creater", title:"创建人", align:"center", width:30,resizable:false},      
		    {field:"createDate", title:"创建时间", align:"center", width:45,resizable:false}, 
		    {field:"buildId", hidden:true},
		    {field:"operation", title:"操作", align:"center", width:80,resizable:false,
		    	formatter:function(value, row, index) {
		    		var str = '<a href="javascript:void(0)" onclick="viewEmp(' + index + ')" name="view" class="easyui-tooltip" title="查看">查看</a>'
	    			+ '<a href="javascript:void(0)" onclick="editEmp(' + index + ')" style="margin-left:10px;" name="edit" class="easyui-tooltip" title="修改">修改</a>'
		    		+ '<a href="javascript:void(0)" onclick="deleteEmp(&quot;' + row.id + "&quot;,&quot;" + row.empNo + '&quot;)" style="margin-left:10px;" name="delete" class="easyui-tooltip" title="删除">删除</a>';
		    		return str;
		    	}
		    },      
		]],
		toolbar:"#emptools",
		onLoadSuccess:function(){
			$("a[name='view']").linkbutton({plain:true,iconCls:'icon-search'}); 
			$("a[name='edit']").linkbutton({plain:true,iconCls:'icon-edit'}); 
			$("a[name='delete']").linkbutton({plain:true,iconCls:'icon-no'}); 
			$('#employee').datagrid('fixRowHeight'); //格式化后行号对齐
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
	$("#empSearch").click(function(){
		var queryParams = $('#employee').datagrid('options').queryParams;
		queryParams.empName = $('#empName').textbox("getValue");
		queryParams.buildName = $("#empBuild").combobox("getValue");
		$('#employee').datagrid('load');
	});
	
	// 重置查询条件
	$("#empReset").click(function(){
		$('#empName').textbox("clear");
		$("#empBuild").combobox("clear");
		var queryParams = $('#employee').datagrid('options').queryParams;
		queryParams.empName = "";
		queryParams.buildName = "";
		$('#employee').datagrid('load');
	});
	
	// 批量删除
	$("#batchDeleteEmp").click(function(){
		var selectRows = $("#employee").datagrid("getSelections");
	    if(selectRows.length == 0){
	        $.messager.alert("提示信息","请选择至少一条数据",'warning');
	        return;
	    }
	    var ids = new Array();
	    for(var i = 0; i < selectRows.length; i++){
	    	ids.push(selectRows[i].id);
	    }
	    $.messager.confirm("系统提示","您确定要批量删除这<font color='red'>"+selectRows.length+"</font>个员工吗？",function (r) {
	        if(r){
	            $.ajax({
	               url:"./employee/batchDelete",
	               type:"post",
	               data:{"ids":JSON.stringify(ids)},
	               success:function (data) {
	                    if("success" == data.status){
	                        $.messager.alert("系统提示",data.message,'info',function () {
	                            $("#employee").datagrid("reload");
	                        });
	                    } else {
	                        $.messager.alert("系统提示", data.message, 'error', function(){
	                        	$("#employee").datagrid("unselectAll");
	        	    			$("#employee").datagrid("uncheckAll");
	                        });
	                    } 
	               },
	               error: function(xhr, status, err) {
		            	$.messager.alert("系统提示", "网络异常,请刷新重试！", "error", function(){
		            		$("#employee").datagrid("unselectAll");
			    			$("#employee").datagrid("uncheckAll");
		            	});
	                }
	            });
	        } else {
	        	$("#employee").datagrid("unselectAll");
    			$("#employee").datagrid("uncheckAll");
	        }
	    });
	});
	
	// 添加员工
	function addEmp() {
		if ($("#addEmpForm").form("validate")) {
			$("#addEmpForm").ajaxSubmit({
				url:"./employee/save",
				type:"post",
				success:function(data) {
					if ("success" == data.status) {
						$.messager.alert("系统提示", data.message, "info", function(){
							$("#addEmpDiv").dialog("close");
							$("#employee").datagrid("load");
						});
					} else {
						$.messager.alert("系统提示", data.message, "error");
					}
				}
			});
			return false; // 防止表单自动提交
		} else {
			$.messager.alert("系统提示", "表单填写不规范", "warning");
		}
	}
	
	// 打开添加窗口
	$("#openAddEmp").click(function(){
		$('#addEmpDiv').dialog({
	        title:'添加员工',
	        width:380,
	        height:400,
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
	            		addEmp();
	            	}
	            },
	            {
	            	text:"取消",
	            	iconCls:"icon-cancel",
	            	handler:function(){
	            		$("#addEmpDiv").dialog("close");
	            	}
	            }
	        ],
	        onBeforeClose:function(){
	           $("#addEmpForm").form("reset");
	        }
	    });
	});
	
	// 日期清除
    var buttons = $.extend([], $.fn.datebox.defaults.buttons);  
    buttons.splice(1, 0, {  
        text: '清除',  
        handler: function (target) {  
            $(target).datebox("setValue","");
            $(target).datebox("hidePanel");
        }  
    });  
    $('.easyui-datebox').datebox({  
        buttons: buttons  
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
			$("#empBuild").combobox({
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
	
	// 获取本地浏览器对应的图片路径
	function getFileUrl(file) {
		var url;
		if (window.createObjectURL != undefined) {
			url = window.createObjectURL(file); //basic
		} else if (window.URL != undefined) {
			url = window.URL.createObjectURL(file); // firefox
		} else if (window.webkitURL != undefined) {
			url = window.webkitURL.createObjectURL(file); // chorme or webkit
		}
		return url;
	}
	
	//上传时触发的事件
	function photoEdit(obj) {
		var imgsrc = $(obj).filebox("getValue");
		var file = $(obj).context.ownerDocument.activeElement.files[0];
		$("#empPhoto").attr("src", getFileUrl(file));
	}
	
	$("#myPhoto").filebox({
		onChange:function(){
			photoEdit(this);
		}
	});
    
});
	
	
	
	//打开查看窗口
	function openViewEmp(index, empName, photoExists) {
		$('#searchEmpDiv').dialog({
			title:'查看员工(只读)=>' + empName,
			width:460,
			height:500,
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
			    	   $("#searchEmpDiv").dialog("close");
			       }
			    }
			],
			onBeforeClose:function(){
				$("#employee").datagrid("unselectRow", index);
	            $("#seeEmp").form("reset");
	            photoExists.css("display", "none");
	        }
		});
	}
	
	// 查看
	function viewEmp(index) {
		$("#employee").datagrid("selectRow", index); // 手动选择行
		var row = $("#employee").datagrid("getSelected");
		if (row) {
			row.buildName = row.dormBuild.buildName;
			row.empSex = formatSex(row.empSex);
			if (null != row.photo && "" != row.photo) {
				$("#viewEmpPhoto").get(0).src = "/pic/" + row.photo;
				$("#photoExists").css("display", "block");
			}
			$("#seeEmp").form("load", row);
			openViewEmp(index, row.empName, $("#photoExists"));
		}
	}
	
	function formatSex(empSex) {
		if (1 == empSex) {
			return "男";
		} else {
			return "女";
		}
	}
	
	//打开修改窗口
	function openEditEmp(index,empName, photoExists) {
		$('#editEmpDiv').dialog({
			title:'修改员工信息=>' + empName,
			width:460,
			height:460,
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
				    	modifyEmp();
				    }
				 },
			    {
			       text:"取消",
			       iconCls:"icon-cancel",
			       handler:function(){
			    	   $("#editEmpDiv").dialog("close");
			       }
			    }
			],
			onBeforeClose:function(){
				$("#employee").datagrid("unselectRow", index);
	            $("#editEmpForm").form("reset");
	            photoExists.css("display", "none");
	        }
		});
	}
	
	// 修改回显
	function editEmp(index) {
		$("#employee").datagrid("selectRow", index); // 手动选择行
		var row = $("#employee").datagrid("getSelected");
		if ("男" == row.empSex) {
			row.empSex = 1;
		}
		if ("女" == row.empSex) {
			row.empSex = 0;
		}
		if (row) {
			if (null != row.photo && "" != row.photo) {
				$("#empPhoto").get(0).src = "/pic/" + row.photo;
				$("#editEmpPhoto").css("display", "block");
			}
			$("#myBuild").combobox("setValue", row.buildId);
			$("#myBuild").combobox("setText", row.dormBuild.buildName);
			$("#editEmpForm").form("load", row);
			openEditEmp(index, row.empName, $("#editEmpPhoto"));
		}
	}
	
	// 修改
	function modifyEmp(){
		if ($("#editEmpForm").form("validate")) {
			$("#realBuildId").val($("#myBuild").combobox("getValue"));
			$("#editEmpForm").ajaxSubmit({
				url:"./employee/update",
				type:"post",
				success:function(data) {
					if ("success" == data.status) {
						$.messager.alert("系统提示", data.message, "info", function(){
							$("#editEmpDiv").dialog("close");
							$("#employee").datagrid("reload");
						});
					} else {
						$.messager.alert("系统提示", data.message, "error");
					}
				}
			});
			return false; // 防止表单自动提交
		} else {
			$.messager.alert("系统提示", "表单填写不规范", "warning");
		}
	}
	
	// 根据id删除
	function deleteEmp(id, empNo) {
		$.messager.confirm("系统提示", "确定删除员工号为<font color='red'>" + empNo + "</font>的员工吗?" , function(r) {
			if (r) {
				$.ajax({
		           url:"./employee/deleteById/" + id,
		           type:"delete",
		           success:function (data) {
			            if ("success" == data.status) {
			            	$.messager.alert("系统提示", data.message, "info", function(){
			            		$("#employee").datagrid("reload");
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
	
