$(function(){
	var isCheckFlag = true;
	// 分页显示
	$("#student").datagrid({
		url:"./student/listByPage",
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
		    {field:"stuNo", title:"学号", align:"center", width:48,resizable:false},
		    {field:"stuName", title:"姓名", align:"center", width:40,resizable:false},
		    {field:"stuSex", title:"性别", align:"center", width:20,resizable:false,
		    	formatter:function(value, row, index) {
		    		if (0 == row.stuSex) {
		    			return "女";
		    		} else {
		    			return "男";
		    		}
		    	}
		    },
		    {field:"birthday", title:"生日", align:"center",width:40,resizable:false},
		    {field:"email", title:"邮箱", align:"center",width:55,resizable:false},
		    {field:"phone", title:"手机号", align:"center",width:40,resizable:false},
		    {field:"dormNo", title:"宿舍号", align:"center", width:30,resizable:false,
		    	formatter:function(value, row, index) {
		    		return row.dorm.dormNo;
		    	}
		    },      
		    {field:"className", title:"班级", align:"center", width:30,resizable:false},      
		    {field:"creater", title:"创建人", align:"center", width:30,resizable:false},      
		    {field:"createDate", title:"创建时间", align:"center", width:50,resizable:false},
		    {field:"dormId", hidden:true},
		    {field:"operation", title:"操作", align:"center", width:80,resizable:false,
		    	formatter:function(value, row, index) {
		    		var str = '<a href="javascript:void(0)" onclick="viewStu(' + index + ')" name="view" class="easyui-tooltip" title="查看">查看</a>'
	    			+ '<a href="javascript:void(0)" onclick="editStu(' + index + ')" style="margin-left:10px;" name="edit" class="easyui-tooltip" title="修改">修改</a>'
		    		+ '<a href="javascript:void(0)" onclick="deleteStu(&quot;' + row.id + "&quot;,&quot;" 
		    		+ row.stuNo + "&quot;,&quot;" + row.dormId + '&quot;)" style="margin-left:10px;" name="delete" class="easyui-tooltip" title="删除">删除</a>';
		    		return str;
		    	}
		    },      
		]],
		toolbar:"#stutools",
		onLoadSuccess:function(){
			$("a[name='view']").linkbutton({plain:true,iconCls:'icon-search'}); 
			$("a[name='edit']").linkbutton({plain:true,iconCls:'icon-edit'}); 
			$("a[name='delete']").linkbutton({plain:true,iconCls:'icon-no'}); 
			$('#student').datagrid('fixRowHeight'); //格式化后行号对齐
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
	$("#stuSearch").click(function(){
		var queryParams = $('#student').datagrid('options').queryParams;
		queryParams.stuNo = $('#stuNo').textbox("getValue");
		queryParams.stuName = $('#stuName').textbox("getValue");
		queryParams.className = $('#className').textbox("getValue");
		queryParams.dormNo = $("#searchStuDorm").combobox("getValue");
		$('#student').datagrid('load');
	});
	
	// 重置查询条件
	$("#stuReset").click(function(){
		$('#stuNo').textbox("clear");
		$('#stuName').textbox("clear");
		$('#className').textbox("clear");
		$("#searchStuDorm").combobox("clear");
		var queryParams = $('#student').datagrid('options').queryParams;
		queryParams.stuNo = "";
		queryParams.stuName = "";
		queryParams.className = "";
		queryParams.dormNo = "";
		$('#student').datagrid('load');
	});
	
	// 添加学生
	function addStu() {
		if ($("#addStuForm").form("validate")) {
			var stuNo = $("#addStuNo").textbox("getValue");
			var stuName = $("#addStuName").textbox("getValue");
			var className = $("#addStuClass").textbox("getValue");
			var stuSex = $("#addStuForm input[name='stuSex']:checked").val();
			var birthday = $("#addStuBirthday").datebox("getValue");
			var phone = $("#addStuPhone").textbox("getValue");
			var email = $("#addStuEmail").textbox("getValue");
			var dormId = $("#addStuDorm").combobox("getValue");
			var postData = JSON.stringify({"stuNo":stuNo,"stuName":stuName,"className":className,
				"stuSex":stuSex,"birthday":birthday,"phone":phone,"email":email,"dormId":dormId,
				"password":stuNo});
			$.ajax({
				url:"./student/save",
				type:"post",
				contentType:"application/json",
				data:postData,
				success:function(data){
					if ("success" == data.status) {
						$.messager.alert("系统提示", data.message, "info", function(){
							$("#student").datagrid("load");
							$("#addStuDiv").dialog("close");
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
	$("#openAddStu").click(function(){
		$('#addStuDiv').dialog({
	        title:'添加学生',
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
	            		addStu();
	            	}
	            },
	            {
	            	text:"取消",
	            	iconCls:"icon-cancel",
	            	handler:function(){
	            		$("#addStuDiv").dialog("close");
	            	}
	            }
	        ],
	        onBeforeClose:function(){
	           $("#addStuForm").form("reset");
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
		url:"./dorm/listAll",
		type:"get",
		success:function(data) {
			var dorm = [];
			for (var i = 0; i < data.content.length; i++) {
				dorm.push({"id":data.content[i].id, "dormNo":data.content[i].dormNo});
			}
			$("#searchStuDorm").combobox({
				prompt:"宿舍号...",
				width:100,
				data:dorm,
				textField:"dormNo",
				valueField:"dormNo",
			});
			$("#addStuDorm").combobox({
				data:dorm,
				textField:"dormNo",
				valueField:"id",
			});
			$("#studentDorm").combobox({
				data:dorm,
				textField:"dormNo",
				valueField:"id",
			});
		},
		error: function(xhr, status, err) {
            $.messager.alert("系统提示", "网络异常,请刷新重试！", "error");
        }
	});
    
});
	
	
	
	//打开查看窗口
	function openViewStu(index, stuName) {
		$('#searchStuDiv').dialog({
			title:'查看学生(只读)=>' + stuName,
			width:460,
			height:415,
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
			    	   $("#searchStuDiv").dialog("close");
			       }
			    }
			],
			onBeforeClose:function(){
				$("#student").datagrid("unselectRow", index);
	            $("#seeStu").form("reset");
	        }
		});
	}
	
	// 查看
	function viewStu(index) {
		$("#student").datagrid("selectRow", index); // 手动选择行
		var row = $("#student").datagrid("getSelected");
		if (row) {
			row.dormNo = row.dorm.dormNo;
			row.stuSex = formatSex(row.stuSex);
			$("#seeStu").form("load", row);
			openViewStu(index, row.stuName);
		}
	}
	
	function formatSex(stuSex) {
		if (1 == stuSex) {
			return "男";
		} else {
			return "女";
		}
	}
	
	//打开修改窗口
	function openEditStu(index,stuName,oldDormId) {
		$('#editStuDiv').dialog({
			title:'修改学生信息=>' + stuName,
			width:460,
			height:380,
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
				    	modifyStu(oldDormId);
				    }
				 },
			    {
			       text:"取消",
			       iconCls:"icon-cancel",
			       handler:function(){
			    	   $("#editStuDiv").dialog("close");
			       }
			    }
			],
			onBeforeClose:function(){
				$("#student").datagrid("unselectRow", index);
	            $("#editStuForm").form("reset");
	        }
		});
	}
	
	// 修改回显
	function editStu(index) {
		$("#student").datagrid("selectRow", index); // 手动选择行
		var row = $("#student").datagrid("getSelected");
		if ("男" == row.stuSex) {
			row.stuSex = 1;
		}
		if ("女" == row.stuSex) {
			row.stuSex = 0;
		}
		if (row) {
			$("#studentDorm").combobox("setValue", row.dormId);
			$("#studentDorm").combobox("setText", row.dorm.dormNo);
			$("#editStuForm").form("load", row);
			openEditStu(index, row.stuName, row.dormId);
		}
	}
	
	// 修改
	function modifyStu(oldDormId){
		if ($("#editStuForm").form("validate")) {
			var id = $("#stuId").val();
			var stuName = $("#studentName").textbox("getValue");
			var className = $("#studentClass").textbox("getValue");
			var stuSex = $("#editStuForm input[name='stuSex']:checked").val();
			var birthday = $("#studentBirthday").datebox("getValue");
			var phone = $("#studentPhone").textbox("getValue");
			var email = $("#studentEmail").textbox("getValue");
			var dormId = $("#studentDorm").combobox("getValue");
			var postData = JSON.stringify({"id":id,"stuName":stuName,"className":className,"stuSex":stuSex,
				"birthday":birthday,"phone":phone,"email":email,"dormId":dormId,"oldDormId":oldDormId});
			$.ajax({
				url:"./student/update",
				type:"put",
				contentType:"application/json",
				data:postData,
				success:function(data){
					if ("success" == data.status) {
						$.messager.alert("系统提示", data.message, "info", function(){
							$("#student").datagrid("load");
							$("#editStuDiv").dialog("close");
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
	function deleteStu(id, stuNo, dormId) {
		$.messager.confirm("系统提示", "确定删除学号为<font color='red'>" + stuNo + "</font>的学生吗?" , function(r) {
			if (r) {
				$.ajax({
		           url:"./student/deleteById/" + id + "/" + dormId,
		           type:"delete",
		           success:function (data) {
			            if ("success" == data.status) {
			            	$.messager.alert("系统提示", data.message, "info", function(){
			            		$("#student").datagrid("reload");
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