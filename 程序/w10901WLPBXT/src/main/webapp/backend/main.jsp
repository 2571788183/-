<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="images/logo_icon.png">
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script  src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
			$(document).ready(function(){
			    $(".sysName").load("sysName.txt",function(data,x){
			    	document.title=data;
			    });	
			});
	</script>
	<title></title>
</head>
<frameset rows="80,*" frameborder="no" border="0" framespacing="0">
	<frame src="top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
	<frame src="center.jsp" name="mainFrame" id="mainFrame" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>

