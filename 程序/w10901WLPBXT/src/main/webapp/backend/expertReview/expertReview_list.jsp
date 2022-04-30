<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<title>CMS 后台管理工作平台</title>
	<link href="css/style_list.css" rel="stylesheet">
<script src="js/jquery-1.9.1.min.js" language="javascript" /></script>
<script  src="js/ajaxfileupload.js"></script>
<style type="text/css">
</style>

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
                <td width="94%" valign="bottom"><span class="list_title"> 专家评审信息列表</span></td>
              </tr>
            </table></td>
            <script>
            	function checkAll(item){
            		var chk = document.getElementsByName("expertReviewCheckbox");
            		for ( var i = 0; i < chk.length; i++) {
						chk[i].checked = item.checked;
					}
            	}
            	function del(){
					document.getElementById("form_expertReview_betch").action="expertReview!deleteBetch?pageNo=${page.pageNo }&pageSize=${page.pageSize }";
            		document.getElementById("form_expertReview_betch").submit();
            	}
            </script>
            <td><div align="right">
            <span>
<c:if test="${login_user.role.id eq '1'}">
             &nbsp;&nbsp;<img src="images/add.gif" width="10" height="10"  class="add_button_img"/> <a href="expertReview!addInput" class="button_list ">添加</a>   
             &nbsp; <img src="images/del.gif" width="10" height="10"  class="add_button_img"/> <a href="javascript:del()" class="button_list ">批量删除</a>   
</c:if>
<c:if test="${login_user.role.id eq '2'}">
</c:if>
<c:if test="${login_user.role.id eq '3'}">
</c:if>
             </span><span> &nbsp;</span></div></td>
          </tr>
           <tr><td colspan="2"> 
           <div align="left" class="list_search" > 
 				<form action="expertReview.do" method="post"> 
 					<input type="hidden" name="pageNo" value="${page.pageNo }"  /> 
 					<input type="hidden" name="pageSize" value="${page.pageSize }"/> 
 					<input type="hidden" name="type" value="backend"/> 
 					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			&nbsp;&nbsp;作品：&nbsp;&nbsp;
			<select name="productionsearch" id="productionsearchIds" >
				<option value=""  <c:if test="${'' eq search.productionsearch }">selected="selected"</c:if>>选择作品</option>
				<c:forEach items="${productionList }" var="s" >
				<option value="${s.id }"  <c:if test="${s.id eq search.productionsearch }">selected="selected"</c:if>
				>${s.name}</option>
				</c:forEach>
			</select> 
			

			
			<c:if test="${ login_user.role.id ne '3' }">
			&nbsp;&nbsp;评委：&nbsp;&nbsp;
			<select name="judgesearch" id="judgesearchIds" >
				<option value=""  <c:if test="${'' eq search.judgesearch }">selected="selected"</c:if>>选择评委</option>
				<c:forEach items="${judgeList }" var="s" >
				<option value="${s.id }"  <c:if test="${s.id eq search.judgesearch }">selected="selected"</c:if>
				>${s.name}</option>
				</c:forEach>
			</select> 
			</c:if>
                    <%--备注2--%>
                   <%-- <select name="waysearch" id="waysearchIds" >
                        <option value=""  <c:if test="&lt;%&ndash;${'' eq search.waysearch }&ndash;%&gt;">selected="selected"</c:if>>选择赛道</option>
                        <c:forEach items="${waylist }" var="s" >
                            <option value="${s.id }"  <c:if test="${s.id eq search.waysearch}">selected="selected"</c:if>
                            >${s.name}</option>
                        </c:forEach>
                    </select>
                   </c:if>
--%>
<input type="submit" class="button_search " value="查询"> 
 				</form> 
 		  </div> 
           </td><td></td></tr> 
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <table  class="table table-bordered table-list">
    <thead>
      <tr>
        <th width="4%" height="20" ><div align="center">
          <input type="checkbox"  onclick="checkAll(this)"/>
        </div></th>
        <th width="200" height="20"><div align="center"><span>作品</span></div></th>
        <th width="200" height="20"><div align="center"><span>发布时间</span></div></th>
        <th style="display:none;" width="200" height="20"><div align="center"><span class="STYLE10">专家意见</span></div></th>
        <th width="200" height="20"><div align="center"><span>评分</span></div></th>
        <th style="display:none;" width="200" height="20"><div align="center"><span class="STYLE10">结论</span></div></th>
        <th width="200" height="20"><div align="center"><span>评委</span></div></th>
        <th width="200" height="20"><div align="center"><span>参赛者</span></div></th>
        <th width="200" height="20"><div align="center"><span>基本操作</span></div></th>
      </tr>
      </thead>
      <tbody>
      <c:if test="${not empty expertReviewList}">
      <form id="form_expertReview_betch" method="post">
      <c:forEach items="${expertReviewList}" var="expertReview">
<tr>
        <td height="20"><div align="center">
          <input type="checkbox" name="expertReviewCheckbox" value="${expertReview.id }"/>
        </div></td>
        <td height="20"><div align="center">${expertReview.production.name }</div></td>
        <td height="20"><div align="center"><fmt:formatDate value="${expertReview.settime }" pattern="yyyy-MM-dd"/></div></td>
        <td style="display:none;"  height="20"><div align="center">${expertReview.yijiancontent }</div></td>
        <td height="20"><div align="center">${expertReview.score }</div></td>
        <td style="display:none;"  height="20"><div align="center">${expertReview.jieluncontent }</div></td>
        <td height="20"><div align="center">${expertReview.judge.name }</div></td>
        <td height="20"><div align="center">${expertReview.member.name }</div></td>
        <td height="20"><div align="center">
        <script>
        	function delconfirm(str){
        		var c = confirm("确认要删除此项吗？");
				if(c){
        		document.getElementById("opera").action=str;
        		document.getElementById("opera").submit();
				}else{
				
				}
        	}
        	function opera(str){
        		document.getElementById("opera").action=str;
        		document.getElementById("opera").submit();
        	}
        </script>
<c:if test="${login_user.role.id eq '1'}">
        <a href="javascript:delconfirm('expertReview!delete?id=${expertReview.id }')" class="button_list2 " title="点击删除项目信息">删除</a><span class="shuxian"> |</span>
        <a href="javascript:opera('expertReview!modify?id=${expertReview.id }')" class="button_list2 " title="点击编辑项目信息">编辑</a><br/>
         <a href="javascript:opera('expertReview!modifyYijiancontent?id=${expertReview.id }')" class="button_list2 " title="点击编辑项目信息" class="button_list2 ">专家意见</a>
         <a href="javascript:opera('expertReview!modifyScore?id=${expertReview.id }')" class="button_list2 " title="点击编辑项目信息" class="button_list2 ">评分</a>
         <a href="javascript:opera('expertReview!modifyJieluncontent?id=${expertReview.id }')" class="button_list2 " title="点击编辑项目信息" class="button_list2 ">结论</a>
        <a href="javascript:opera('expertReview!show?id=${expertReview.id }')" class="button_list2 " title="点击查看详情">查看详情</a><br/>
</c:if>
<c:if test="${login_user.role.id eq '2'}">
        <a href="javascript:opera('expertReview!show?id=${expertReview.id }')" class="button_list2 " title="点击查看详情">查看详情</a><br/>
</c:if>
<c:if test="${login_user.role.id eq '3'}">
         <a href="javascript:opera('expertReview!modifyYijiancontent?id=${expertReview.id }')" class="button_list2 " title="点击编辑项目信息" class="button_list2 ">专家意见</a>
         <a href="javascript:opera('expertReview!modifyScore?id=${expertReview.id }')" class="button_list2 " title="点击编辑项目信息" class="button_list2 ">评分</a>
         <a href="javascript:opera('expertReview!modifyJieluncontent?id=${expertReview.id }')" class="button_list2 " title="点击编辑项目信息" class="button_list2 ">结论</a>
        <a href="javascript:opera('expertReview!show?id=${expertReview.id }')" class="button_list2 " title="点击查看详情">查看详情</a><br/>
</c:if>
        </div></td>
      </tr>
      </c:forEach>
      </form>
      </c:if>
      <c:if test="${ empty expertReviewList }">
      <tr>
          <td height="20" colspan="90" class="noinfo"><div align="center">没有专家评审信息可以显示</div></td>
      </tr>
      </c:if>
      </tbody>
      
    </table></td>
  </tr>
<form id="form_page" action="expertReview.do" method="post">
	<input type="hidden" name="pageNo" value="${page.pageNo }" id="pageNo" />
	<input type="hidden" name="pageSize" value="${page.pageSize }" id="pageSize"/>
</form>  
<%--   <%@include file="/backend/commons/page.jsp" %> --%>
<%@include file="/backend/commons/page.jsp" %>
</table>
 				<form action="expertReview.do" method="post"  id="opera" style="display:none"> 
 					<input type="hidden" name="pageNo" value="${page.pageNo }"  /> 
 					<input type="hidden" name="pageSize" value="${page.pageSize }"/> 
 					<input type="hidden" name="type" value="backend"/> 
 					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			&nbsp;&nbsp;作品：&nbsp;&nbsp;
			<select name="productionsearch" id="productionsearchIds" >
				<option value=""  <c:if test="${'' eq search.productionsearch }">selected="selected"</c:if>>选择作品</option>
				<c:forEach items="${productionList }" var="s" >
				<option value="${s.id }"  <c:if test="${s.id eq search.productionsearch }">selected="selected"</c:if>
				>${s.name}</option>
				</c:forEach>
			</select> 
			

			
			<c:if test="${ login_user.role.id ne '3' }">
			&nbsp;&nbsp;评委：&nbsp;&nbsp;
			<select name="judgesearch" id="judgesearchIds" >
				<option value=""  <c:if test="${'' eq search.judgesearch }">selected="selected"</c:if>>选择评委</option>
				<c:forEach items="${judgeList }" var="s" >
				<option value="${s.id }"  <c:if test="${s.id eq search.judgesearch }">selected="selected"</c:if>
				>${s.name}</option>
				</c:forEach>
			</select> 
			</c:if>
			
 				</form> 
</body>
</html>

