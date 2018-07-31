$(function(){
	
	// 页面加载显示验证码
	$("#code").text(createCode(4));
	
	// 点击更换验证码
	$("#code").click(function(){
		$(this).text(createCode(4));
	});
	
	// 生成验证码
    function createCode(len) {
        var seed = new Array(
            'abcdefghijklmnopqrstuvwxyz',
            'ABCDEFGHIJKLMNOPQRSTUVWXYZ',
            '0123456789'
        );
        var idx, i;
        var result = '';
        for (i = 0; i < len; i++) {
            idx = Math.floor(Math.random() * 3);
            result += seed[idx].substr(Math.floor(Math.random() * (seed[idx].length)), 1);
        }
        return result;
    }

    // 登录中
	function ajaxLoading(){   
		$("<div class='datagrid-mask'></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");   
		$("<div class='datagrid-mask-msg'></div>").html("正在登录...").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});   
	}  
	// 登陆成功
	function ajaxLoadEnd(){   
		 $(".datagrid-mask").remove();   
		 $(".datagrid-mask-msg").remove();               
	} 
    
    // 登录
    $("#login").click(function(){
    	var username = $("#username").textbox("getValue");
    	var password = $("#password").textbox("getValue");
    	var checkCode = $("#checkCode").textbox("getValue");
    	var flag = $("#loginForm input[name='flag']:checked").val();
    	var code = $("#code").text();
    	var url;
    	var postData;
    	var flag = $("input[name='flag']:checked").val();
    	if ("2" == flag) {
    		url = "./student/login";
    		postData = JSON.stringify({"stuNo":username, "password":password});
    	} else {
    		url = "./user/login";
    		postData = JSON.stringify({"username":username,"password":password,"flag":flag});
    	}
    	if($("#loginForm").form("validate")) {
        	if (checkCode.toLowerCase() == code.toLowerCase() ) {
        		$.ajax({
        			url:url,
        			type:"post",
        			dataType:"json",
        			data:postData,
        			contentType:"application/json;charset=utf-8",
					beforeSend:ajaxLoading,
        			success:function(data){
        				if ("success" == data.status) {
							setTimeout(function(){
								ajaxLoadEnd();
								location.href = "./index.jsp";
							},1000);
        				} else {
							ajaxLoadEnd();
        					$.messager.alert("系统提示", data.message, "error");
        				}
        			},
        			error: function(xhr, status, err) {
        				ajaxLoadEnd();
        				$.messager.alert("系统提示", "网络异常", "error");
                    }
        		});
        	} else {
        		$.messager.alert("系统提示", "验证码错误", "warning");
        	}
        }
    });
    
    // 重置
    $("#reset").click(function(){
    	$("#loginForm").form("reset");
    });
	
});