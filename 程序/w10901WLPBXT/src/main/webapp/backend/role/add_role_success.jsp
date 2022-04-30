<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>添加角色成功</title>
	<link href="css/style_add.css" rel="stylesheet">
</head>
<body>
<div id="formwrapper">
	<h3 class="add_title1">添加角色成功</h3>
	<div class="enter">
	    <input name="addRole" type="button" class="button_add_modify" value="继续添加" onclick="window.location = 'role!addInput'"/>
	    <input name="return" type="button" class="button_add_modify" value="返回列表页面" onclick="window.location = 'role.do'"/>
	</div>
</div>

</body>
</html>

