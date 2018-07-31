<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>学生个人信息</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  
  <body>
  	<table style="font-size: 12px; margin-left:40px;margin-top:40px;" width="400" height="300" 
  		cellpadding="5" cellspacing="0">
  		<tr>
  			<td width="20%">学号：</td>
  			<td>${session_user.stuNo}</td>
  		</tr>
  		<tr>
  			<td>姓名：</td>
  			<td>${session_user.stuName}</td>
  		</tr>
  		<tr>
  			<td>性别：</td>
  			<td>${1 == session_user.stuSex?'男':'女'}</td>
  		</tr>
  		<tr>
  			<td>生日：</td>
  			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${session_user.birthday}"/></td>
  		</tr>
  		<tr>
  			<td>邮箱：</td>
  			<td>${session_user.email}</td>
  		</tr>
  		<tr>
  			<td>手机：</td>
  			<td>${session_user.phone}</td>
  		</tr>
  		<tr>
  			<td>班级：</td>
  			<td>${session_user.className}</td>
  		</tr>
  		<tr>
  			<td>创建人：</td>
  			<td>${session_user.creater}</td>
  		</tr>
  		<tr>
  			<td>创建时间：</td>
  			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${session_user.createDate}"/></td>
  		</tr>
  	</table>
  </body>
</html>
