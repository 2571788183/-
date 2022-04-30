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

<tr>
    <td height="30">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> ${page.recordsCount }</strong> 条记录，当前第<strong> ${page.pageNo }</strong> 页，共 <strong>${page.pagesCount }</strong> 页</span></div></td>
        <td width="67%" align=right vAlign="center" noWrap>
        
       	<c:choose>
       		<c:when test="${page.pagesCount le 13}">
		        		首页
		        		<c:if test="${page.pageNo eq 1}">上页</c:if>
		        		<c:if test="${page.pageNo ne 1}"><a href="javascript:go(${page.pageNo-1 })">上页</a></c:if>
		        		<c:forEach begin="${1}" end="${page.pagesCount}" step="1" var="i">
		        			<c:choose>
		        				<c:when test="${i eq page.pageNo }"><font color="red">${i }</font></c:when>
		        				<c:otherwise><a href="javascript:go(${i })">${i }</a></c:otherwise>
		        			</c:choose>
		        		</c:forEach>
						<c:if test="${page.pageNo >= page.pagesCount}">下页</c:if>
		        		<c:if test="${page.pageNo < page.pagesCount}"><a href="javascript:go(${page.pageNo+1 })">下页</a></c:if>
						尾页
       		</c:when>
       		<c:otherwise>
       			<c:choose>
       			
		        	<c:when test="${page.pageNo le 3 }">
		        		首页
		        		<c:if test="${page.pageNo eq 1}">上页</c:if>
		        		<c:if test="${page.pageNo ne 1}"><a href="javascript:go(${page.pageNo-1 })">上页</a></c:if>
		        		<c:forEach begin="${1}" end="${10}" step="1" var="i">
		        			<c:choose>
		        				<c:when test="${i eq page.pageNo }"><font color="red">${i }</font></c:when>
		        				<c:otherwise><a href="javascript:go(${i })">${i }</a></c:otherwise>
		        			</c:choose>
		        		</c:forEach>
		        		<a href="javascript:go(${page.pageNo+1 })">下页</a>
						<a href="javascript:go(${page.pagesCount })">尾页</a>
					</c:when>
					
		        	<c:when test="${page.pageNo ge page.pagesCount-9 }">
		        		<a href="javascript:go(1)">首页</a>
						<a href="javascript:go(${page.pageNo-1 })">上页</a>
						<c:forEach begin="${page.pagesCount-9}" end="${page.pagesCount}" step="1" var="i">
		        			<c:choose>
		        				<c:when test="${i eq page.pageNo }"><font color="red">${i }</font></c:when>
		        				<c:otherwise><a href="javascript:go(${i })">${i }</a></c:otherwise>
		        			</c:choose>
		        		</c:forEach>
						<c:if test="${page.pageNo eq page.pagesCount}">下页</c:if>
		        		<c:if test="${page.pageNo ne page.pagesCount}"><a href="javascript:go(${page.pageNo+1 })">下页</a></c:if>
						尾页
		        	</c:when>
		        	
		        	<c:otherwise>
			        	<a href="javascript:go(1)">首页</a>
						<a href="javascript:go(${page.pageNo-1 })">上页</a>
						<c:forEach begin="${page.pageNo-2}" end="${page.pageNo+7}" step="1" var="i">
		        			<c:choose>
		        				<c:when test="${i eq page.pageNo }"><font color="red">${i }</font></c:when>
		        				<c:otherwise><a href="javascript:go(${i })">${i }</a></c:otherwise>
		        			</c:choose>
		        		</c:forEach>
						<a href="javascript:go(${page.pageNo+1 })">下页</a>
						<a href="javascript:go(${page.pagesCount })">尾页</a>
		        	</c:otherwise>
	        	</c:choose>
       		</c:otherwise>
       	</c:choose>
	<select name="pagesize" onchange="changePageSize(this)" >
		<c:forEach begin="10" end="100" step="5" var="i">
			<option value="${i }"	
				<c:if test="${i eq page.pageSize}">selected</c:if>
			>${i }</option>
		</c:forEach>
	</select>

      </td>
      </tr>
    </table></td>
  </tr>
