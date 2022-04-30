<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>    
<html>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script  src="js/jquery-1.9.1.min.js"></script>
<link href="css/style_add2.css" rel="stylesheet">
	<script type="text/javascript">
			$(document).ready(function(){
			    $(".sysName").load("sysName.txt",function(data,x){
			    	document.title=data;
			    });	
			});
	</script>
<title></title>
<style type="text/css">



</style>
</head>

<body>
<script type="text/javascript">
function btn_ok(){
	window.returnValue = "${filename}::${contentType}";
	window.close();
}
</script>
<div id="formwrapper">
	<c:choose>
		<c:when test="${filename==null }">
			<h3>请选择上传文件</h3>
			<div class="enter">
				<form action="upload.do" method="post" enctype="multipart/form-data">
					附件：<input type="file" name="uploadFile"class="button_add_modify">
						  <input type="submit" value="上传"class="button_add_modify">
				</form>
			</div>
		</c:when>
		<c:otherwise>
		<h3>文件添加成功</h3>
			<input type="button" onclick="btn_ok()" value="确定"class="button_add_modify">
		</c:otherwise>
	</c:choose>
</div>


</body>
</html>

