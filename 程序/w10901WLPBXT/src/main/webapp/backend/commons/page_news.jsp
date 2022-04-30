<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.bs.model.*"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function go(pageNo) {
		document.getElementById("opera").pageNo.value = pageNo;
		document.getElementById("opera").submit();
	}
	function changePageSize(pageSize) {
		document.getElementById("opera").pageSize.value = pageSize.value;
		document.getElementById("opera").submit();
	}
</script>
  
<div style="height:30px">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="35%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> ${page.recordsCount }</strong> 条新闻，当前第<strong> ${page.pageNo }</strong> 页，共 <strong>${page.pagesCount }</strong> 页</span></div></td>
        <td width="64%" align=right vAlign="center" noWrap>
        
       	<c:choose>
       		<c:when test="${page.pagesCount le 13}">
		        		<font color="gray">首页</font>
		        		<c:if test="${page.pageNo eq 1}"><font color="gray">上页</font></c:if>
		        		<c:if test="${page.pageNo ne 1}"><a href="javascript:go(${page.pageNo-1 })"><font color="white">上页</font></a></c:if>
		        		<c:forEach begin="${1}" end="${page.pagesCount}" step="1" var="i">
		        			<c:choose>
		        				<c:when test="${i eq page.pageNo }"><span><a class="hover">${i }</a></span></c:when>
		        				<c:otherwise><span><a href="javascript:go(${i })">${i }</a></span></c:otherwise>
		        			</c:choose>
		        		</c:forEach>
						<c:if test="${page.pageNo eq page.pagesCount}"><font color="gray">下页</font></c:if>
		        		<c:if test="${page.pageNo ne page.pagesCount}"><a href="javascript:go(${page.pageNo+1 })"><font color="white">下页</font></a></c:if>
						<font color="gray">尾页</font>
       		</c:when>
       		<c:otherwise>
       			<c:choose>
       			
		        	<c:when test="${page.pageNo le 3 }">
		        		<font color="white">首页</font>
		        		<c:if test="${page.pageNo eq 1}"><font color="white">上页</font></c:if>
		        		<c:if test="${page.pageNo ne 1}"><a href="javascript:go(${page.pageNo-1 })"><font color="white">上页</font></a></c:if>
		        		<c:forEach begin="${1}" end="${10}" step="1" var="i">
		        			<c:choose>
		        				<c:when test="${i eq page.pageNo }"><span><a class="hover">${i }</a></span></c:when>
		        				<c:otherwise><span><a href="javascript:go(${i })">${i }</a></span></c:otherwise>
		        			</c:choose>
		        		</c:forEach>
		        		<a href="javascript:go(${page.pageNo+1 })"><font color="white">下页</font></a>
						<a href="javascript:go(${page.pagesCount })"><font color="white">尾页</font></a>
					</c:when>
					
		        	<c:when test="${page.pageNo ge page.pagesCount-9 }">
		        		<a href="javascript:go(1)"><font color="white">首页</font></a>
						<a href="javascript:go(${page.pageNo-1 })"><font color="white">上页</font></a>
						<c:forEach begin="${page.pagesCount-9}" end="${page.pagesCount}" step="1" var="i">
		        			<c:choose>
		        				<c:when test="${i eq page.pageNo }"><span><a class="hover">${i }</a></span></c:when>
		        				<c:otherwise><span><a href="javascript:go(${i })">${i }</a></span></c:otherwise>
		        			</c:choose>
		        		</c:forEach>
						<c:if test="${page.pageNo eq page.pagesCount}"><font color="white">下页</font></c:if>
		        		<c:if test="${page.pageNo ne page.pagesCount}"><a href="javascript:go(${page.pageNo+1 })"><font color="white">下页</font></a></c:if>
						<font color="white">尾页</font>
		        	</c:when>
		        	
		        	<c:otherwise>
			        	<a href="javascript:go(1)"><font color="white">首页</font></a>
						<a href="javascript:go(${page.pageNo-1 })"><font color="white">上页</font></a>
						<c:forEach begin="${page.pageNo-2}" end="${page.pageNo+7}" step="1" var="i">
		        			<c:choose>
		        				<c:when test="${i eq page.pageNo }"><span><a class="hover">${i }</a></span></c:when>
		        				<c:otherwise><span><a href="javascript:go(${i })">${i }</a></span></c:otherwise>
		        			</c:choose>
		        		</c:forEach>
						<a href="javascript:go(${page.pageNo+1 })"><font color="white">下页</font></a>
						<a href="javascript:go(${page.pagesCount })"><font color="white">尾页</font></a>
		        	</c:otherwise>
	        	</c:choose>
       		</c:otherwise>
       	</c:choose>

      </td>
      </tr>
    </table>
    
</div>
