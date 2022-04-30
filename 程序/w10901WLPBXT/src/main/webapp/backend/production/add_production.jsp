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
	<title>添加作品</title>
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
			CKEDITOR.replace( 'content' ,
			{
				width:$("content").parent().width(),
				height:500
			});
		}

var 
settime,
mCal,
mDCal,
newStyleSheet;
var dateFrom = null;
var dateTo = null;
window.onload = function() {
    settime = new dhtmlxCalendarObject('settime');
    initCKEditor();
}

</script>
<style type="text/css">
</style>
<script type="text/javascript">
function addcontent(fileinput){

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
							url="<img src=\"upload_image/"+filename+"\"  style='max-width:100%;' />";
						}else{
							url="<a href=\"upload_file/"+filename+"\" />下载文件</a>";
						}
						
						//向CKEDITOR插入html代码 url
						var editor = CKEDITOR.instances.content;
						// Check the active editing mode.
						if ( editor.mode == 'wysiwyg' ){
							// Insert HTML code.
							// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-insertHtml
							editor.insertHtml( url );
						}
						else{
							alert( 'You must be in WYSIWYG mode!' );
						}
					
					}
				}
			
			},
			error: function (data, status, e){
				alert(e);
			}
		});
	})(jQuery);

}
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
	<h3 class="add_title1">添加作品</h3>
	<form action="production!add"  method="post">
	<fieldset>
		<legend class="add_title2" >作品信息</legend>
		<div>
			<label for="name">作品名称</label>
			<input type="text" name="name" id="name" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<%--备注1--%>
		<label for="name">参赛赛道</label>
		<select>
			<option>初创组</option>
			<option>创意组</option>
			<option>成长组</option>
			<option>师生共创组</option>
			<option>研究生创意组</option>
			<option>红色赛道</option>
		</select>
		<br/>
<c:if test="${login_user.role.id eq '1'}">
		<div>
			<label for="settime">发布时间</label>
			<input type="text" name="settime" id="settime" value="" size="8" maxlength="200" /> 
			<br />	
		</div>
</c:if>
<c:if test="${login_user.role.id ne '1'}">
		<div style="display:none">
			<label for="settime">发布时间</label>
			<input type="text" name="settime" id="settime" value="" size="8" maxlength="200" /> 
			<br />	
		</div>
</c:if>
<div>
			<label for="img">作品文件</label>
			<input type="button" class="button_list2 "  onclick="$('#doctsUpload').click()" id="attachs" value="上传文件">
			<input type="hidden" name="docts" id="docts" value="" size="60" maxlength="200" /> 
			<input type="file" style="display: none" name="uploadFile" id="doctsUpload"  onchange="image('docts',this)"/>
			<br />	
		</div>
		<div>
			<label for="descp">作品简介</label>
			<input type="text" name="descp" id="descp" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="content">作品详情</label>
			<div style="margin:0px ;width:75%;display: inline-block;">
			<textarea rows="10" cols="100" name="content" id="content" ></textarea>
			</div>
			<br />	
		</div>
		<div>
			<label for="attachs">插入作品详情附件或图片</label>
			<input type="file" style="display: none" name="uploadFile" id="contentFile"  onchange="addcontent(this)"  multiple="multiple"/>
			<input type="button" class="button_list2 " onclick="$('#contentFile').click()" id="attachs"  value="添加">
			<br />	
		</div>
<c:if test="${login_user.role.id ne '2'}">
		<div>
			<label for="memberIds">参赛者</label>
			<select name="member" id="memberIds" >
				<c:forEach items="${memberList }" var="s" >
				<option value="${s.id }"  <c:if test="${s.id eq 1 }">selected="selected"</c:if>
				>${s.name}</option>
				</c:forEach>
			</select> 
		</div>
</c:if>
<c:if test="${login_user.role.id eq '2'}">
		<div style="display:none;">
			<label for="memberIds">参赛者</label>
			<select name="member" id="memberIds" >
				<c:forEach items="${memberList }" var="s" >
				<option value="${s.id }"  <c:if test="${s.users.id eq login_user.id }">selected="selected"</c:if>
				>${s.name}</option>
				</c:forEach>
			</select> 
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

