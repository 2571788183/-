<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>编辑专家评审</title>
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
			CKEDITOR.replace( 'yijiancontent' ,
			{
				width:$("yijiancontent").parent().width(),
				height:500
			});
			CKEDITOR.replace( 'jieluncontent' ,
			{
				width:$("jieluncontent").parent().width(),
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
<script>
function addyijiancontent(fileinput){

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
						var editor = CKEDITOR.instances.yijiancontent;
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
function addjieluncontent(fileinput){

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
						var editor = CKEDITOR.instances.jieluncontent;
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
<style type="text/css">
</style>
</head>
<body>
<div id="formwrapper">
	<h3 class="add_title1">编辑专家评审</h3>
	<form action="expertReview!updateJieluncontent?pageNo=${page.pageNo }&pageSize=${page.pageSize }" method="post">
	<input type="hidden" name="id" value="${expertReview.id }">
	<input type="hidden" name="type" value="${param.type }">
	<input type="hidden" name="string" value="${param.string }">
	<input type="hidden" name="authorityName" value="${param.authorityName }">
	<input type="hidden" name="authorityValue" value="${param.authorityValue }">
	<input type="hidden" name="productionsearch" value="${param.productionsearch }">
	<input type="hidden" name="judgesearch" value="${param.judgesearch }">

	<fieldset>
		<legend class="add_title2">专家评审信息</legend>
		<div>
			<label for="jieluncontent">结论</label>
			<div style="margin:0px ;width:75%;display: inline-block;">
			<textarea rows="10" cols="100" name="jieluncontent" id="jieluncontent" >${expertReview.jieluncontent }</textarea>
			</div>
			<br />	
		</div>
		<div>
			<label for="attachs">插入结论附件或图片</label>
			<input type="file" style="display: none" name="uploadFile" id="jieluncontentFile"  onchange="addjieluncontent(this)"  multiple="multiple"/>
			<input type="button" class="button_list2 " onclick="$('#jieluncontentFile').click()" id="attachs"  value="添加">
			<br />	
		</div>
		<div class="enter">
		    <input name="submit" type="submit" class="button_add_modify"  value="提交" />
		    <input name="reset" type="reset" class="button_add_modify"  value="重置" />
		    <input name="return" type="button" class="button_add_modify"  value="返回" onclick="window.history.go(-1)"/>
		</div>
	</fieldset>
	</form>
</div>

</body>
</html>

