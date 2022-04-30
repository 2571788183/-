<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
	<link href="css/style_add.css" rel="stylesheet">
</head>
<body>
	<div id="formwrapper">
		<h3 class="add_title1">密码修改成功！请重新登录。</h3>
	</div>
	<div class="enter">
		<input name="add" type="button" class="button_add_modify" value="确认" onclick="window.parent.parent.location='${basePath}login.jsp'"/> 
	</div>

</body>
</html>

