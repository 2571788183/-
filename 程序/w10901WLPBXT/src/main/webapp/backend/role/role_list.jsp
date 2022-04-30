<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>CMS 后台管理工作平台</title>
  <link href="css/style_list.css" rel="stylesheet">
</head>
<body class="listbg">
<table class="list_content">
  <tr>
    <td class="list_content2">
    <table width="100%" >
      <tr>
        <td height="24" class="list_head">
        <table width="100%">
          <tr>
            <td>
            <table width="100%">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" class="list_title_img" /></div></td>
                <td width="94%" valign="bottom"><span class="list_title"> 角色信息列表</span></td>
              </tr>
            </table></td>
            <script>
            	function checkAll(item){
            		var chk = document.getElementsByName("roleCheckbox");
            		for ( var i = 0; i < chk.length; i++) {
						chk[i].checked = item.checked;
					}
            	}
            	function del(){
					document.getElementById("form_role_betch").action="role!deleteBetch?pageNo=${page.pageNo }&pageSize=${page.pageSize }";
            		document.getElementById("form_role_betch").submit();
            	}
            </script>
            <td><div align="right"><span>
<!--              &nbsp;&nbsp;<img src="images/add.gif" width="10" height="10"  class="add_button_img"/> <a href="role!addInput" class="button_list ">添加</a>    -->
<!--              &nbsp; <img src="images/del.gif" width="10" height="10"  class="delete_button_img"/> <a href="javascript:del()" class="button_list ">删除</a>    &nbsp;&nbsp;   &nbsp; -->
             </span><span> &nbsp;</span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <table  class="table table-bordered table-list">
    <thead>
      <tr>
        <th width="4%" height="20"><div align="center">
          <input type="checkbox"  onclick="checkAll(this)"/>
        </div></th>
        <th width="200" height="20"><div align="center"><span class="STYLE10">角色名称</span></div></th>
        <th width="200" height="20"><div align="center"><span class="STYLE10">权限</span></div></th>
        <th width="200" height="20"><div align="center"><span class="STYLE10">基本操作</span></div></th>
      </tr>
      </thead>
      <tbody>
      <c:if test="${not empty roleList}">
      <form id="form_role_betch" method="post">
      <c:forEach items="${roleList}" var="role">
      <tr>
        <td height="20"><div align="center">
          <input type="checkbox" name="roleCheckbox" value="${role.id }"/>
        </div></td>
        <td height="20"><div align="center">${role.name }</div></td>
        <td height="20"><div align="center">${role.descp }</div></td>
        <td height="20"><div align="center">
        <script>
        	function delconfirm(str){
        		var c = confirm("确认要删除此项吗？");
				if(c){
				  window.location.href=str;
				}else{
				
				}

        	}
        </script>
<!--         <a href="javascript:delconfirm('role!delete?id=${role.id }&pageNo=${page.pageNo }&pageSize=${page.pageSize }')"class="button_list2 " title="点击删除项目信息">删除</a><span class="shuxian"> |</span> -->
        <a href="role!modify?id=${role.id }&pageNo=${page.pageNo }&pageSize=${page.pageSize }"class="button_list2 " title="点击编辑项目信息">编辑</a>
        </div></td>
      </tr>
      </c:forEach>
      </form>
      </c:if>
      <c:if test="${ empty roleList }">
      <tr>
          <td height="20" colspan="9" class="noinfo"><div align="center">没有角色信息可以显示</div></td>
      </tr>
      </c:if>
      </tbody>
    </table></td>
  </tr>
<form id="form_page" action="role.do" method="post">
	<input type="hidden" name="pageNo" value="${page.pageNo }" id="pageNo" />
	<input type="hidden" name="pageSize" value="${page.pageSize }" id="pageSize"/>
</form>  
<%--   <%@include file="/backend/commons/page.jsp" %> --%>
<%@include file="/backend/commons/page.jsp" %>
</table>
</body>
</html>

