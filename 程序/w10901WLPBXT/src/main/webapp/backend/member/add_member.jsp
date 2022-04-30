<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>添加用户</title>
<script  src="js/jquery-1.9.1.min.js"></script>
<script  src="js/validate_form.js"></script>
<script type="text/javascript">
function isUsername(str){
	  var boo=1;
	  jQuery.ajax({
          type: "POST",
          url: 'user!validate',
          cache: false,
          async: false,
          data:{
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
		  jQuery("#validate_username").text(" √此用户名可用").css("color","green");
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
<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxcalendar.css">
<script  src="codebase/dhtmlxcommon.js"></script>
<script  src="codebase/dhtmlxcalendar.js"></script>
<script src="js/jquery-1.9.1.min.js"  /></script>
<script  src="js/ajaxfileupload.js"></script>
	<script src="js/js.js" language="javascript" /></script>
	<script src="ckeditor/ckeditor.js"></script>
	<link href="css/style_add.css" rel="stylesheet">
<script>
		function initCKEditor(){
		}

var 
birthday,
mCal,
mDCal,
newStyleSheet;
var dateFrom = null;
var dateTo = null;
window.onload = function() {
    birthday = new dhtmlxCalendarObject('birthday');
    initCKEditor();
}

</script>
<style type="text/css">
</style>
<script type="text/javascript">
function image(str,fileinput){

	(function ($) {
        $(".loading").ajaxStart(function(){
			$(this).css("display","inline-block");
		}).ajaxComplete(function(){
			$(this).css("display","none");
		});
		$.ajaxFileUpload({
			url:"upload.do",
			secureuri:false,
			fileElementId:fileinput.id,
			dataType:'json',
			success: function (data, status){
				var filenames=data.filenames;
				var contentTypes=data.contentTypes;
				for(var i=0;i<data.nums;i++){
					var ret = filenames[i]+contentTypes[i];
					if(ret!=null&&ret!=""){
						filename=filenames[i];
						contentType=contentTypes[i];
					var url="";
					if(contentType.indexOf("image")>-1){
						if($("#"+str+"_image").size()>0){
						$("#"+str+"_image").attr("src","upload_image/"+filename+"");
						}else{
						$("#"+str).after("<img id='"+str+"_image' style='max-width:150px;border:1px  solid #dddddd;padding:4px;border-radius:5px;' src='upload_image/"+filename+"'>");
						}
					}else{
						if($("#"+str+"_file").size()>0){
						$("#"+str+"_file").attr("href","upload_file/"+filename+"");
						}else{
						$("#"+str).after("<a id='"+str+"_file'  href='upload_file/"+filename+"'>下载文件</a>");
						}
					}
					document.getElementById(str).value = filename;
					}
				}
			},
			error: function (data, status, e){
				alert(e);
			}
		});
	})(jQuery);
    
}
function InsertHTML(value){
	var editor = CKEDITOR.instances.content;
	// Check the active editing mode.
	if ( editor.mode == 'wysiwyg' )
	{
		// Insert HTML code.
		// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-insertHtml
		editor.insertHtml( value );
	}
	else
		alert( 'You must be in WYSIWYG mode!' );
}
</script>
</head>
<body>
<div id="formwrapper">
	<h3 class="add_title1">添加用户</h3>
	<form action="member!add1" onsubmit="return validate_form(this)" method="post">
<input type="hidden" name="roles" value="2">	<fieldset>
		<legend class="add_title2" >用户信息</legend>
<div>
			<label for="username">用户名</label>
			<input type="text" name="username" id="username" value="" size="20" maxlength="20" onblur="v_username(this)"/> 
			<div  style="display: inline"><font id="validate_username" color="red"></font></div>
			<br />	
		</div>
		<div>
			<label for="password">密码</label>
			<input type="password" name="password" id="password" value="" size="20" maxlength="20" onblur="v_password(this)"/> 
			<div  style="display: inline"><font id="validate_password" color="red"></font></div>
			<br />	
		</div>
		<div>
			<label for="confirm_password">确认密码</label>
			<input type="password" name="password" id="confirm_password" value="" size="20" maxlength="20" onblur="v_confirm_password(this)" /> 
			<div  style="display: inline"><font id="validate_confirm_password" color="red"></font></div>
			<br />	
		</div>
		<br>
<div>
			<label for="img">头像</label>
			<input type="hidden" name="img" id="img" value="" size="60" maxlength="200" /> 
			<input type="file" style="display: none" name="uploadFile" id="imgUpload"  onchange="image('img',this)"/>
			<input type="button" class="button_list2 "  onclick="$('#imgUpload').click()" id="attachs" value="上传图片">
			<br />	
		</div>
		<div>
			<label for="name">姓名</label>
			<input type="text" name="name" id="name" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="sex">性别</label>
			<input type="text" name="sex" id="sex" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="birthday">出生日期</label>
			<input type="text" name="birthday" id="birthday" value="" size="8" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="tel">联系电话</label>
			<input type="text" name="tel" id="tel" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="address">住址</label>
			<input type="text" name="address" id="address" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="email">邮箱</label>
			<input type="text" name="email" id="email" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
<c:if test="${login_user.role.id eq '1'}">
<div>
			<label for="img">身份证</label>
			<input type="hidden" name="idcardimg" id="idcardimg" value="" size="60" maxlength="200" /> 
			<input type="file" style="display: none" name="uploadFile" id="idcardimgUpload"  onchange="image('idcardimg',this)"/>
			<input type="button" class="button_list2 "  onclick="$('#idcardimgUpload').click()" id="attachs" value="上传图片">
			<br />	
		</div>
</c:if>
<c:if test="${login_user.role.id ne '1'}">
<div style="display:none">
			<label for="img">身份证</label>
			<input type="hidden" name="idcardimg" id="idcardimg" value="" size="60" maxlength="200" /> 
			<input type="file" style="display: none" name="uploadFile" id="idcardimgUpload"  onchange="image('idcardimg',this)"/>
			<input type="button" class="button_list2 "  onclick="$('#idcardimgUpload').click()" id="attachs" value="上传图片">
			<br />	
		</div>
</c:if>
		<div class="enter">
		    <input name="submit" type="submit" class="button_add_modify"  value="提交" />
		    <input name="reset" type="reset" class="button_add_modify"  value="重置" />
<c:if test="${login_user ne null}">
		    <input name="return" type="button" class="button_add_modify"  value="返回" onclick="window.history.go(-1)"/>
</c:if>
		</div>
	</fieldset>
	</form>
</div>

</body>
</html>

