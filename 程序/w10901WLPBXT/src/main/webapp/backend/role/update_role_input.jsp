<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>添加角色</title>
	
	<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxcalendar.css">
	<script  src="codebase/dhtmlxcommon.js"></script>
	<script  src="codebase/dhtmlxcalendar.js"></script>
	
	<script src="js/jquery-1.9.1.min.js" language="javascript" /></script>
	<script src="js/js.js" language="javascript" /></script>
	<script src="ckeditor/ckeditor.js"></script>
	<link href="css/style_add.css" rel="stylesheet">
</head>
<body>
<div id="formwrapper">
	<h3 class="add_title1">添加角色</h3>
	<form action="role!update?pageNo=${page.pageNo }&pageSize=${page.pageSize }" method="post">
	<input type="hidden" name="id" value="${role.id }">	<fieldset>
		<legend  class="add_title2">角色信息</legend>
		<div>
			<label for="name">角色名称</label>
			<input type="text" name="name" id="name" value="${role.name }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="descp">权限</label>
			<input type="text" name="descp" id="descp" value="${role.descp }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div class="enter">
		    <input name="submit" type="submit" class="button_add_modify" value="提交" />
		    <input name="reset" type="reset" class="button_add_modify" value="重置" />
		    <input name="return" type="button" class="button_add_modify" value="返回列表页面" onclick="window.location = 'role.do'"/>
		</div>
	</fieldset>
	</form>
</div>

</body>
</html>

