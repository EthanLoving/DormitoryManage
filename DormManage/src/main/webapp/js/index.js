$(function(){
	// 显示当前时间
	function showTime(){
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		var hour = date.getHours();
		var minute = date.getMinutes();
		var second = date.getSeconds();
		if(month<10){
			month = "0"+month;
		}
		if(day<10){
			day = "0" + day;
		}
		if(hour<10){
			hour = "0" + hour;
		}
		if(minute<10){
			minute = "0" + minute;
		}
		if(second<10){
			second = "0" + second;
		}
		$("#nowTime").html(year+"年"+month+"月"+day+"日"+" "+hour+"时"+minute+"分"+second+"秒");
	}
	
	setInterval(showTime,500);
	
	//右下角弹窗信息
    $.messager.show({
        title:'系统消息',
        msg:'欢迎进入本系统',
        timeout:3000,
        width:250,
        height:150,
        showType:'show'
    });
    
    // 修改密码窗口
    $("#openChangePwd").click(function(){
    	$("#pwdDialog").dialog({
        	title:'修改密码',
            width:300,
            height:220,
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
                	text:"修改",
                	iconCls:"icon-ok",
                	handler:function(){
                		changePwd();
                	}
                },
                {
                	text:"取消",
                	iconCls:"icon-cancel",
                	handler:function(){
                		$("#pwdDialog").dialog("close");
                	}
                }
            ],
            onBeforeClose:function(){
               $("#pwdForm").form("reset");
            }
        });
    });
    
    var loginFlag = $("#loginFlag").val();
    
    // 修改密码
    function changePwd() {
    	if ($("#pwdForm").form("validate")) {
    		var id = $("#changeId").val();
    		var username = $("#changeName").val();
    		var password = $("#newPwd").textbox("getValue");
    		var oldPassword = $("#oldPwd").textbox("getValue");
    		var updateUrl;
    		var postDate;
    		if (2 == loginFlag) {
    			updateUrl = "./student/updatePwd";
    			postDate = JSON.stringify({"id":id,"stuNo":username,"password":password,"oldPassword":oldPassword});
    		} else {
    			updateUrl = "./user/updatePwd";
    			postDate = JSON.stringify({"id":id,"username":username,"password":password,"oldPassword":oldPassword});
    		}
    		$.ajax({
    			url:updateUrl,
    			type:"put",
    			data:postDate,
    			contentType:"application/json",
    			success:function(data){
    				if ("success" == data.status) {
    					$.messager.alert("系统提示", data.message, "info", function(){
    						location.href = "./login.html";
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
    
    // 退出系统
    $("#loginOut").click(function() {
    	var url;
    	if (2 == loginFlag) {
    		url = "./student/loginOut";
    	} else {
    		url = "./user/loginOut";
    	}
    	$.messager.confirm("系统提示", "确定退出系统吗?",function(r){
    		if (r) {
    			$.ajax({
    				url:url,
    				type:"get",
    				dataType:"json",
    				success:function(data){
    					if ("success" == data.status) {
    						location.href = "./login.html";
    					} else {
    						$.messager.alert("系统提示", data.message, "error");
    					}
    				},
    				error: function(xhr, status, err) {
    					$.messager.alert("系统提示", "网络异常", "error");
    				}
    			});
    		}
    	});
	});
    
    // 动态加载树形结构
    var treeUrl;
    if (null !== loginFlag && "" !== loginFlag) {
    	if (0 == loginFlag) {
    		treeUrl = "/DormManage/json_tree/admin.json";
    	} else if(1 == loginFlag) {
    		treeUrl = "/DormManage/json_tree/dorm_manager.json";
    	} else {
    		treeUrl = "/DormManage/json_tree/student.json";
    	}
    }
  // 远程加载树结构,创建选项卡
    $("#mytree").tree({
    	url:treeUrl,
    	animate:true,
    	lines:true,
        onClick:function(node){
        	var href;
        	switch (node.text) {
        	case "楼层管理" :
        		href = "dormbuild.html";
        		break;
        	case "宿舍管理" :
        		href = "dorm.html";
        		break;
        	case "员工管理" :
        		href = "employee.html";
        		break;
        	case "学生管理" :
        		href = "student.html";
        		break;
        	case "物品进出登记" :
        		href = "articlerecord.html";
        		break;
        	case "来访人员登记" :
        		href = "vistor.html";
        		break;
        	case "用户管理" :
        		href = "user.html";
        		break;
        	case "个人信息" :
        		href = "student_self.jsp";
        		break;
        	}
        	if ($("#mytree").tree("isLeaf", node.target)) {
        		 var tabs = $("#tabs").tabs("getTab",node.text);
                 if(tabs == null){
                     $("#tabs").tabs("add",{
                        title:node.text,
                        closable:true,
                        href:href
                     });
                 }else {
                     $("#tabs").tabs("select", node.text);
                 }
        	}
        }
    });
});