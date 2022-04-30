<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title></title>

<link href="css/style_nav.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="js/left.js"></script>
<style type="text/css">
<!--

-->
</style>
</head>
<body>
<div  style="height:100%;">
  <ul id="navigation">

	<li> <a class="head"><i class="mb-head-icon"></i>用户管理</a>
      <ul>
        <li><a href="member!addInput" target="rightFrame"><i class="mb-list-icon"></i>添加用户</a></li>
        <li><a href="member.do" target="rightFrame"><i class="mb-list-icon"></i>查看用户信息</a></li>
      </ul>
    </li>

	<li> <a class="head"><i class="mb-head-icon"></i>评委管理</a>
      <ul>
        <li><a href="judge!addInput" target="rightFrame"><i class="mb-list-icon"></i>添加评委</a></li>
        <li><a href="judge.do" target="rightFrame"><i class="mb-list-icon"></i>查看评委信息</a></li>
      </ul>
    </li>

	<li> <a class="head"><i class="mb-head-icon"></i>报名管理</a>
      <ul>
        <li><a href="signup!addInput" target="rightFrame"><i class="mb-list-icon"></i>添加报名</a></li>
        <li><a href="signup.do" target="rightFrame"><i class="mb-list-icon"></i>查看报名信息</a></li>
      </ul>
    </li>
    <li> <a class="head"><i class="mb-head-icon"></i>赛道管理</a>
      <ul>
        <li><a href="way!addInput" target="rightFrame"><i class="mb-list-icon"></i>添加赛道</a></li>
        <li><a href="way.do" target="rightFrame"><i class="mb-list-icon"></i>查看赛道信息</a></li>
      </ul>
    </li>

	<li> <a class="head"><i class="mb-head-icon"></i>作品管理</a>
      <ul>
        <li><a href="production!addInput" target="rightFrame"><i class="mb-list-icon"></i>添加作品</a></li>
        <li><a href="production.do" target="rightFrame"><i class="mb-list-icon"></i>查看作品信息</a></li>
      </ul>
    </li>

	<li> <a class="head"><i class="mb-head-icon"></i>专家评审管理</a>
      <ul>
        <li><a href="expertReview!addInput" target="rightFrame"><i class="mb-list-icon"></i>添加专家评审</a></li>
        <li><a href="expertReview.do" target="rightFrame"><i class="mb-list-icon"></i>查看专家评审信息</a></li>
      </ul>
    </li>

<!-- 	<li> <a class="head"><i class="mb-head-icon"></i>报名审核管理</a> -->
<!--       <ul> -->
<!--         <li><a href="shenhe!addInput" target="rightFrame"><i class="mb-list-icon"></i>添加报名审核</a></li> -->
        
<!--       </ul> -->
<!--     </li> -->

<!--     <li> <a class="head"><i class="mb-role-icon"></i>角色管理</a> -->
<!--       <ul> -->
<!--         <li><a href="role/add_role.jsp" target="rightFrame"><i class="mb-list-icon"></i>添加角色</a></li> -->
        
<!--       </ul> -->
<!--     </li> -->
    <li> <a class="head"><i class="mb-sys-icon"></i>系统管理</a>
      <ul>
      <li><a href="shenhe.do" target="rightFrame"><i class="mb-list-icon"></i>查看报名审核信息</a></li>
      <li><a href="role.do" target="rightFrame"><i class="mb-list-icon"></i>查看角色信息</a></li>
        <li><a href="sys/update_password.jsp" target="rightFrame"><i class="mb-list-icon"></i>修改个人密码</a></li>
<!--         <li><a href="/log.log" target="rightFrame"><i class="mb-list-icon"></i>查看登录日志</a></li> -->
<!--         <li><a href="user!users" target="rightFrame"><i class="mb-list-icon"></i>查看在线用户</a></li> -->
      </ul>
    </li>
  </ul>
</div>
</body>
</html>

