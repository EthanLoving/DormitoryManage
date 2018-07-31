<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生宿舍管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body class="easyui-layout">
  	<div id="pwdDialog">
		<form id="pwdForm">
			<input type="hidden" id="changeId" value="${session_user.id}">
			<input type="hidden" id="changeName" value="${2==flag?session_user.stuNo:session_user.username}">
			<table style="font-size:12px;margin:auto;margin-top:20px;">
				<tr>
					<td>原密码：</td>
					<td><input id="oldPwd" class="easyui-passwordbox" data-options="required:true"></td>
				</tr>
				<tr>
					<td>新密码：</td>
					<td><input id="newPwd" class="easyui-passwordbox" data-options="required:true,validType:['length[6,20]']"></td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input id="rePwd" class="easyui-passwordbox" validType="equals['#newPwd']" data-options="required:true"></td>
				</tr>
			</table>
		</form>
	</div>
  	<input type="hidden" id="loginFlag" value="${flag}">
  	<div id="north" data-options="region:'north',collapsible:false,border:false">
  		<div class="header">学生宿舍管理系统</div>
  		<div id="session_user">
  			<c:choose>
	  				<c:when test="${2 == flag}">
	  					<span>欢迎您,学生
	  						<font color="red">${session_user.stuName}</font>
	  					</span>
	  				</c:when>
	  				<c:otherwise>
	  					<span>欢迎您,<c:if test="${0 == flag}">系统管理员</c:if>
	  						<c:if test="${1 == flag}">宿舍管理员</c:if>
	  						<font color="red">${session_user.name}</font>
	  					</span>
	  				</c:otherwise>
	  			</c:choose>
	  			<span id="nowTime"></span>
  		</div>
  		<div id="operation">
  			<c:if test="${null != flag}">
	  			<span id="openChangePwd">修改密码</span>
	  			<span id="loginOut">退出系统</span>
  			</c:if>
  		</div>
  	</div>
	<div data-options="region:'south',border:false" style="text-align:center;color:#696969;height: 64px;background: rgba(0,0,0,.1);">
		<p>Copyright 2018-2099 湖北理工学院(<a href="http://www.hbpu.edu.cn">www.hbpu.edu.cn</a>)　邮编：435003　联系电话：0714-6353390(党政办公室)  0714-6350612(招生工作处)</p>
		<p>地址：湖北省黄石市桂林北路16号　电子邮件：wlzx@hbpu.edu.cn　鄂ICP备15021563号   　 鄂公网安备 42020402000119号</p>
	</div>
	<div data-options="region:'west',title:'系统菜单'" style="width: 230px;">
		<div class="easyui-accordion" data-options="fit:true">
			<div data-options="iconCls:'icon-large-shapes'" title="管理中心">
				<ul id="mytree"></ul>
			</div>
			<div data-options="iconCls:'icon-help',title:'帮助'">
				帮助
			</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="首页" style="background-color:#F0F0F0;padding: 20px;">
				<img src="img/welcome.jpg" height="363">
			</div>
		</div>
	</div>
    
    <link href="js/easyui/themes/default/easyui.css" type="text/css" rel="stylesheet" />
	<link href="js/easyui/themes/icon.css" type="text/css" rel="stylesheet"/>
	<link type="text/css" rel="stylesheet" href="./css/common.css">
	<script src="js/easyui/jquery.min.js"></script>
	<script src="js/easyui/jquery.easyui.min.js"></script>
	<script src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script src="js/jquery_form/jquery-form.js"></script>
	<script src="js/common.js"></script>
	<script src="js/index.js"></script>
	<style type="text/css">
		#north{
			height:100px;
			background-image: url("./img/bg.jpg");
		}
		.header{
			font-family: "Helvetica", "\5FAE\8F6F\96C5\9ED1", Arial, SimSun, sans-serif;
			color: white;
			font-size: 50px;
			margin-top:15px;
			margin-left:12px;
			width:400px;
			text-shadow: 6px 6px 6px #FFB951;
		}
		#north{
			color:white;
		}
		#operation{
			position:relative;
			float:right;
			margin-right:30px;
		}
		#operation span{
			margin-right:15px;
			cursor:pointer;
		}
		#operation span:hover{
			color:red;
		}
		#session_user{
			float: right;
		    margin-top: -58px;
		    margin-right: 44px;
		}
		#nowTime{
			margin-left:10px;
		}
	</style>
	
  </body>
</html>
