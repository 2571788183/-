<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.bs.model.*"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <td width="94%" valign="bottom"><span class="list_title"> 在线用户</span></td>
              </tr>
              <tr>
                <td width="6%" height="19" valign="bottom"></td>
                <td width="94%" valign="bottom"><span class="list_search" >
					当前在线人数：${fn:length(users) }
                </td>
              </tr>
            </table>
            </td>
            <script type="text/javascript">
            	function betch(flag){
            		if(flag){
            			document.getElementById("checkboxForm").action = "UserServlet?method=deleteBetch&key=${param.key}&pageSize=${page.pageSize}&pageNo=${page.pageNo}";
            		}else{
            			document.getElementById("checkboxForm").action = "UserServlet?method=publishBetch&key=${param.key}&pageSize=${page.pageSize}&pageNo=${page.pageNo}";
            		}
            			document.getElementById("checkboxForm").submit();
            	}
            </script>
            <td><div align="right"><span>
             </span><span class="STYLE1"> &nbsp;</span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <script type="text/javascript">
    	function checkAll(){
    		var userCheckboxs = document.getElementsByName("userCheckbox");
    		for ( var i = 0; i < userCheckboxs.length; i++) {
				userCheckboxs[i].checked = document.getElementById("checkAll").checked;
			}
    	}
    </script>
    <table  class="table table-bordered table-list">
    <thead>
      <tr>
        <th width="4%" height="20" ><div align="center">
          <input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll()" />
        </div></th>
        <th width="50" height="20" ><div align="center"><span class="STYLE10">用户名</span></div></th>
        <th width="80" height="20" ><div align="center"><span class="STYLE10">用户角色</span></div></th>
      </tr>
      </thead>
      <tbody>
    <form id="checkboxForm" action="" method="post">
	 <c:forEach items="${ users }" var="s">
      <tr>
        <td height="20" bgcolor="#FFFFFF"><div align="center">
          <input type="checkbox" name="userCheckbox" id="userCheckbox${s.id} " value="${s.id }" />
        </div></td>
        <td height="20" ><div align="center">${s.username}</div></td>
        <td height="20" ><div align="center">${s.role.name}
        </div></td>
        
      </tr>
	</c:forEach>
	</form>
	 <c:if test="${empty users }">
      <tr>
          <td height="20" colspan="9"  class="noinfo"><div align="center">不存在相关用户</div></td>
      </tr>
      </c:if>
      </tbody>
    </table></td>
  </tr>

<!--   <%@include file="/backend/commons/page.jsp" %> -->

</table>
</body>
</html>

