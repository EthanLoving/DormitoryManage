$(function(){
	var isCheckFlag = true;
	// 分页显示
	$("#user").datagrid({
		url:"./user/listByPage",
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
		    {field:"username", title:"用户名", align:"center", width:45,resizable:false},
		    {field:"name", title:"姓名", align:"center", width:40,resizable:false},
		    {field:"sex", title:"性别", align:"center", width:20,resizable:false,
		    	formatter:function(value, row, index) {
		    		if (0 == row.sex) {
		    			return "女";
		    		} else {
		    			return "男";
		    		}
		    	}
		    },
		    {field:"birthday", title:"生日", align:"center",width:40,resizable:false},
		    {field:"phone", title:"手机号", align:"center",width:40,resizable:false},
		    {field:"flag", title:"角色", align:"center", width:40,resizable:false,
		    	formatter:function(value, row, index) {
		    		if (0 == row.flag) {
		    			return "系统管理员";
		    		} else {
		    			return "宿舍管理员";
		    		}
		    	}
		    },      
		    {field:"creater", title:"创建人", align:"center", width:30,resizable:false},      
		    {field:"createDate", title:"创建时间", align:"center", width:50,resizable:false},      
		    {field:"operation", title:"操作", align:"center", width:60,resizable:false,
		    	formatter:function(value, row, index) {
		    		var str = '<a href="javascript:void(0)" onclick="editUser(' + index + ')" style="margin-left:10px;" name="edit" class="easyui-tooltip" title="修改">修改</a>'
		    		+ '<a href="javascript:void(0)" onclick="deleteUser(&quot;' + row.id + "&quot;,&quot;" + row.username + '&quot;)" style="margin-left:20px;" name="delete" class="easyui-tooltip" title="删除">删除</a>';
		    		return str;
		    	}
		    },      
		]],
		toolbar:"#usertools",
		onLoadSuccess:function(){
			$("a[name='edit']").linkbutton({plain:true,iconCls:'icon-edit'}); 
			$("a[name='delete']").linkbutton({plain:true,iconCls:'icon-no'}); 
			$('#user').datagrid('fixRowHeight'); //格式化后行号对齐
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
	$("#userSearch").click(function(){
		var queryParams = $('#user').datagrid('options').queryParams;
		queryParams.username = $('#username').textbox("getValue");
		queryParams.flag = $("#flag").combobox("getValue");
		$('#user').datagrid('load');
	});
	
	// 重置查询条件
	$("#userReset").click(function(){
		$('#username').textbox("clear");
		$("#flag").combobox("clear");
		var queryParams = $('#user').datagrid('options').queryParams;
		queryParams.username = "";
		queryParams.flag = "";
		$('#user').datagrid('load');
	});
	
	// 批量删除
	$("#batchDeleteUser").click(function(){
		var selectRows = $("#user").datagrid("getSelections");
	    if(selectRows.length == 0){
	        $.messager.alert("提示信息","请选择至少一条数据",'warning');
	        return;
	    }
	    var ids = new Array();
	    for(var i = 0; i < selectRows.length; i++){
	    	ids.push(selectRows[i].id);
	    }
	    $.messager.confirm("系统提示","您确定要批量删除这<font color='red'>"+selectRows.length+"</font>个用户吗？",function (r) {
	        if(r){
	            $.ajax({
	               url:"./user/batchDelete",
	               type:"post",
	               data:{"ids":JSON.stringify(ids)},
	               success:function (data) {
	                    if("success" == data.status){
	                        $.messager.alert("系统提示",data.message,'info',function () {
	                            $("#user").datagrid("reload");
	                        });
	                    } else {
	                        $.messager.alert("系统提示", data.message, 'error', function(){
	                        	$("#user").datagrid("unselectAll");
	        	    			$("#user").datagrid("uncheckAll");
	                        });
	                    } 
	               },
	               error: function(xhr, status, err) {
		            	$.messager.alert("系统提示", "网络异常,请刷新重试！", "error", function(){
		            		$("#user").datagrid("unselectAll");
			    			$("#user").datagrid("uncheckAll");
		            	});
	                }
	            });
	        } else {
	        	$("#user").datagrid("unselectAll");
    			$("#user").datagrid("uncheckAll");
	        }
	    });
	});
	
	// 添加用户
	function addUser() {
		if ($("#addUserForm").form("validate")) {
			var username = $("#addUsername").textbox("getValue");
			var name = $("#addRealName").textbox("getValue");
			var sex = $("#addUserForm input[name='sex']:checked").val();
			var birthday = $("#addUserBirth").datebox("getValue");
			var phone = $("#addUserPhone").textbox("getValue");
			var flag = $("#addUserFlag").combobox("getValue");
			var postData = JSON.stringify({"username":username,"name":name,"sex":sex,"password":username,
				"birthday":birthday,"phone":phone,"flag":flag,});
			$.ajax({
				url:"./user/save",
				type:"post",
				data:postData,
				dataType:"json",
				contentType:"application/json",
				success:function(data){
					if ("success" == data.status) {
						$.messager.alert("系统提示", data.message, "info", function(){
							$("#addUserDiv").dialog("close");
							$("#user").datagrid("load");
						});
					} else {
						$.messager.alert("系统提示", data.message, "error");
					}
				}
			});
		} else {
			$.messager.alert("系统提示", "表单填写不规范", "warning");
		}
	}
	
	// 打开添加窗口
	$("#openAddUser").click(function(){
		$('#addUserDiv').dialog({
	        title:'添加用户',
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
	            		addUser();
	            	}
	            },
	            {
	            	text:"取消",
	            	iconCls:"icon-cancel",
	            	handler:function(){
	            		$("#addUserDiv").dialog("close");
	            	}
	            }
	        ],
	        onBeforeClose:function(){
	           $("#addUserForm").form("reset");
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
    
});
	
	
	
	//打开修改窗口
	function openEditUser(index,username) {
		$('#editUserDiv').dialog({
			title:'修改用户信息=>' + username,
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
				    	modifyUser();
				    }
				 },
			    {
			       text:"取消",
			       iconCls:"icon-cancel",
			       handler:function(){
			    	   $("#editUserDiv").dialog("close");
			       }
			    }
			],
			onBeforeClose:function(){
				$("#user").datagrid("unselectRow", index);
	            $("#editUserForm").form("reset");
	        }
		});
	}
	
	// 修改回显
	function editUser(index) {
		$("#user").datagrid("selectRow", index); // 手动选择行
		var row = $("#user").datagrid("getSelected");
		if (row) {
			$("#editUserFlag").combobox("setValue", row.flag);
			$("#editUserForm").form("load", row);
			openEditUser(index, row.username);
		}
	}
	
	// 修改
	function modifyUser(){
		if ($("#editUserForm").form("validate")) {
			var id = $("#userId").val();
			var name = $("#editRealName").textbox("getValue");
			var sex = $("#editUserForm input[name='sex']:checked").val();
			var birthday = $("#editUserBirth").datebox("getValue");
			var phone = $("#editUserPhone").textbox("getValue");
			var flag = $("#editUserFlag").combobox("getValue");
			var postData = JSON.stringify({"id":id,"name":name,"sex":sex,
				"birthday":birthday,"phone":phone,"flag":flag,});
			$.ajax({
				url:"./user/update",
				type:"put",
				data:postData,
				dataType:"json",
				contentType:"application/json",
				success:function(data){
					if ("success" == data.status) {
						$.messager.alert("系统提示", data.message, "info", function(){
							$("#editUserDiv").dialog("close");
							$("#user").datagrid("load");
						});
					} else {
						$.messager.alert("系统提示", data.message, "error");
					}
				}
			});
		} else {
			$.messager.alert("系统提示", "表单填写不规范", "warning");
		}
	}
	
	// 根据id删除
	function deleteUser(id, username) {
		$.messager.confirm("系统提示", "确定删除用户名为<font color='red'>" + username + "</font>的用户吗?" , function(r) {
			if (r) {
				$.ajax({
		           url:"./user/deleteById/" + id,
		           type:"delete",
		           success:function (data) {
			            if ("success" == data.status) {
			            	$.messager.alert("系统提示", data.message, "info", function(){
			            		$("#user").datagrid("reload");
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
	
