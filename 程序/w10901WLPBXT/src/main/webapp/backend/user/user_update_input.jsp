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
<script>
window.dhx_globalImgPath = "codebase/imgs/";
</script>
<script  src="codebase/dhtmlxcommon.js"></script>
<script  src="codebase/dhtmlxcalendar.js"></script>
<script  src="js/jquery-1.9.1.min.js"></script>
<script  src="js/validate_form.js"></script>
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

<link href="css/style_add.css" rel="stylesheet">

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
		  return false;
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
function v_tel(tel){
	  if (isNull(tel.value)){
		  jQuery("#validate_tel").text("*联系电话不能为空");
		  return false;
		  }
	  if (!isNumber(tel.value)){
		  jQuery("#validate_tel").text("*请输入正确的电话号码");
		  return false;
		  }
	  
	  jQuery("#validate_tel").text("");
	  return true;
}
function v_email(email){
	  if (isNull(email.value)){
		  jQuery("#validate_email").text("*请输入电子邮件地址");
		  return false;
		  }
	  if (!isEmail(email.value)){
		  jQuery("#validate_email").text("*电子邮件地址格式有误");
		  return false;
		  }
	  else{
		  jQuery("#validate_email").text("");
	  }
	  return true;
}
function v_empTime(empTime){
	  if (isNull(empTime.value)){
		  jQuery("#validate_empTime").text("*请输入入职时间");
		  return false;
		  }
	  jQuery("#validate_empTime").text("");
	  return true;
}
</script>

<script type="text/javascript">
function validate_form(thisform){
	
with (thisform)
{
  if(
		  (v_username(username))
		  &(v_name(name))
		  &(v_password(password[0]))
		  &(v_confirm_password(confirm_password))
		  &(v_tel(tel))
		  &(v_email(email))
		  &(v_empTime(empTime))
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
	<h3 class="add_title1">查看用户信息</h3>
	<form action="/backend/index.jsp" method="post">
	<input type="hidden" name="id" value="${user.id }">
	<fieldset>
		<legend  class="add_title2">用户信息</legend>
		<div>
			<label for="username">用户名</label>
			<input type="text" name="username" id="username" value="${user.username}" size="20" maxlength="20" /> 
						<div  style="display: inline"><font id="validate_username" color="red"></font></div>
			<br />	
		</div>
		<div>
			<label for="password">密码</label>
			<input type="password" name="password" id="password" readonly="readonly" value="${user.password}" size="20" maxlength="20" /> 
						<div  style="display: inline"><font id="validate_password" color="red"></font></div>
			
			<br />	
		</div>

		<div class="enter">
		    <input name="submit" type="submit" class="button_add_modify" value="返回" />
		</div>
	</fieldset>
	</form>
</div>

</body>
</html>

