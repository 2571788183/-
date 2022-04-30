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
	<title>编辑评委</title>
<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxcalendar.css">
<script  src="codebase/dhtmlxcommon.js"></script>
<script  src="codebase/dhtmlxcalendar.js"></script>
<script src="js/jquery-1.9.1.min.js" language="javascript" /></script>
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
<script>
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
<style type="text/css">
</style>
</head>
<body>
<div id="formwrapper">
	<h3 class="add_title1">查看评委</h3>
	<form action="judge!update?pageNo=${page.pageNo }&pageSize=${page.pageSize }" method="post">
	<input type="hidden" name="id" value="${judge.id }">
	<input type="hidden" name="type" value="${param.type }">
	<input type="hidden" name="string" value="${param.string }">
	<input type="hidden" name="authorityName" value="${param.authorityName }">
	<input type="hidden" name="authorityValue" value="${param.authorityValue }">
	<input type="hidden" name="users" value="${judge.users.id }">
	<fieldset>
		<legend class="add_title2">评委信息</legend>
		<div>
			<label for="name">姓名</label>
			<input type="text" name="name" id="name" value="${judge.name }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="sex">性别</label>
			<input type="text" name="sex" id="sex" value="${judge.sex }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="birthday">出生日期</label>
			<input type="text" name="birthday" id="birthday" value="<fmt:formatDate value="${judge.birthday }" pattern="yyyy-MM-dd"/>" size="8" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="name">评选赛道</label>
			<input type="text" name="judgeWay" id="初创组" value="初创组">
			<br />
		</div>
		<div>
			<label for="tel">联系电话</label>
			<input type="text" name="tel" id="tel" value="${judge.tel }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="address">住址</label>
			<input type="text" name="address" id="address" value="${judge.address }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="email">邮箱</label>
			<input type="text" name="email" id="email" value="${judge.email }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div class="enter">
		    <input name="return" type="button" class="button_add_modify"  value="返回" onclick="window.history.go(-1)"/>
		</div>
	</fieldset>
	</form>
</div>

</body>
</html>

