<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>    
<html>
<head>
<base href="<%=basePath%>"/>
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
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
-->
</style></head>

<body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="200" valign="top" id="left"><iframe height="100%" width="100%" frameborder="0" frameborder="0" src="left${login_user.role.id }.jsp" name="leftFrame" id="leftFrame" title="leftFrame"></iframe></td>
    <td valign="top"><iframe height="100%" width="100%" frameborder="0" frameborder="0" src="right.jsp" name="rightFrame" id="rightFrame" title="rightFrame"></iframe></td>
  </tr>
</table>
<script language="javascript">
	var opened = true;
	function openMenu(){
		if(opened){
//  			document.getElementById("left").style.width=0;
 			document.getElementById("left").style.display="none";
			document.getElementById("middle").innerHTML="&gt;";
			opened = false;
		}else{
//  			document.getElementById("left").style.width=147;
 			document.getElementById("left").style.display="";
 			document.getElementById("middle").innerHTML="&lt;";
			opened = true;
		}
	}
</script>
</body>
</html>

