<%@ page language="java" import="org.bs.model.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title></title>
<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxcalendar.css">
<script  src="codebase/dhtmlxcommon.js"></script>
<script  src="codebase/dhtmlxcalendar.js"></script>
<script  src="js/jquery-1.9.1.min.js"></script>
<script  src="js/validate_form.js"></script>
<script>
$.noConflict();
window.dhx_globalImgPath = "codebase/imgs/";
</script>
<script>
var cal1,
cal2,
mCal,
mDCal,
newStyleSheet;
var dateFrom = null;
var dateTo = null;
window.onload = function() {
    cal1 = new dhtmlxCalendarObject('calInput1');
};
</script>
<style type="text/css">
<!--
body {
	font-family: Arial, Helvetica, sans-serif;
	font-size:12px;
	color:#666666;
	background:#fff;
	text-align:center;

}

* {
	margin:0;
	padding:0;
}

a {
	color:#1E7ACE;
	text-decoration:none;	
}

a:hover {
	color:#000;
	text-decoration:underline;
}
h3 {
	font-size:14px;
	font-weight:bold;
}

pre,p {
	color:#1E7ACE;
	margin:4px;
}
input, select,textarea {
	padding:1px;
	margin:2px;
	font-size:12px;
}
.buttom{
	padding:1px 10px;
	font-size:12px;
	border:1px #1E7ACE solid;
	background:#D0F0FF;
}
#formwrapper {
	width:95%;
	margin:15px auto;
	padding:20px;
	text-align:left;
}

fieldset {
	padding:10px;
	margin-top:5px;
	border:1px solid #1E7ACE;
	background:#fff;
}

fieldset legend {
	color:#1E7ACE;
	font-weight:bold;
	background:#fff;
}

fieldset label {
	float:left;
	width:500px;
	text-align:right;
	padding:4px;
	margin:1px;
}

fieldset div {
	clear:left;
	margin-bottom:2px;
}

.enter{ text-align:center;}
.clear {
	clear:both;
}

-->
</style>


<script type="text/javascript">
function isUsername(str){
	  var boo=1;
	  jQuery.ajax({
          type: "POST",
          url: 'UserServlet',
          cache: false,
          async: false,
          data:{
        	 method:"validate",
           	 username:str
          },
          success: function(data){
        	  boo=data;
          }
       });
	  return boo == 0 ? true : false;
}
</script>

<script type="text/javascript">

function v_username(username){
	  if (isNull(username.value)){
		  jQuery("#validate_username").text("*用户名不能为空").css("color","red");
		  return false;
		  }
	  if (!isUsername(username.value)){
		  jQuery("#validate_username").text("*此用户名已被注册").css("color","red");
		  return false;
		  }
	  else{
		  jQuery("#validate_username").text(" √ 此用户名可用").css("color","green");
	  }
	  jQuery("#validate_email").text("");
	  return true;
}
function v_password(password){
	  if (isNull(password.value)){
		  jQuery("#validate_password").text("*密码不能为空");
		  return false;
		  }
	  jQuery("#validate_password").text("");
	  return true;
}
function v_confirm_password(confirm_password){
	  if ((jQuery("#confirm_password").val()!=jQuery("#password").val())){
		  jQuery("#validate_confirm_password").text("*两次输入的密码不一致");
		  return false;
		  }
	  jQuery("#validate_confirm_password").text("");
	  return true;
}
function v_name(name){
	  if (isNull(name.value)){
		  jQuery("#validate_name").text("*请输入姓名");
		  return false;
		  }
	  jQuery("#validate_name").text("");
	  return true;
}
function v_no(no){
	  if (isNull(no.value)){
		  jQuery("#validate_no").text("*学号不能为空");
		  return false;
		  }
	  jQuery("#validate_no").text("");
	  return true;
}

</script>

<script type="text/javascript">
function validate_form(thisform){
	
with (thisform)

{

  if(
		  (v_username(username))
		  &(v_password(password[0]))
		  &(v_confirm_password(confirm_password))
	
		  )

  return true;
  else
  return false;
}
}
</script>
</head>
<body>
<div id="formwrapper">
	<h3>注册用户</h3>
	<form id="form" action="UserServlet?method=add" onsubmit="return validate_form(this)" method="post">
	
	<fieldset>
		<legend>用户信息</legend>
		<div><br><br>
			<label for="username">用户名</label>
			<input type="text" name="username" id="username" value="" size="20" maxlength="20" onblur="v_username(this)"/> 
			<div  style="display: inline"><font id="validate_username" color="red"></font></div>
			<br />	<br>
		</div>
		<div>
			<label for="password">密码</label>
			<input type="password" name="password" id="password" value="" size="20" maxlength="20" onblur="v_password(this)"/> 
			<div  style="display: inline"><font id="validate_password" color="red"></font></div>
			<br />	<br>
		</div>
		<div>
			<label for="confirm_password">确认密码</label>
			<input type="password" name="password" id="confirm_password" value="" size="20" maxlength="20" onblur="v_confirm_password(this)" /> 
			<div  style="display: inline"><font id="validate_confirm_password" color="red"></font></div>
			<br />	<br>
		</div>
		
		<div>
			<label for="name">真实姓名</label>
			<input type="text" name="name" id="name" value="" size="20" maxlength="20"  onblur="v_name(this)"/> 
			<div  style="display: inline"><font id="validate_name" color="red"></font></div>
			
		</div>
				<br>
		<div>
			<label for="sex">性别</label>
			<select name="sex" id="sex">
				<option value="男" selected="selected">男</option>
				<option value="女">女</option>
			</select>
			<br />	<br>
		</div>

<br><br><br><br>
		
		<div class="enter">
		    <input name="submit" type="submit" class="buttom" value="提交" />
		    <input name="reset" type="reset" class="buttom" value="重置" />
		</div>
	</fieldset>
	</form>
</div>

</body>
</html>

