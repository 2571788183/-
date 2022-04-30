function form_submit(){
	document.getElementById("login").submit();
}
function form_regist(){
	window.location = "regist.jsp";
}
function form_reset(){
	document.getElementById("login").reset();
}
function reloadcode(){
    var verify=document.getElementById('safecode');
    verify.setAttribute('src','code.jsp?'+Math.random());
}
$(function(){
	if(document.URL.indexOf("show")!=-1){
		$("body").append("<link href='css/style_show.css' rel='stylesheet'>");
		$("select,input[type=text]").attr("disabled","disabled");
	}
});
