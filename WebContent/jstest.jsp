<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'center.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
*{
margin:0;
padding:0;
}
body{
font-size:12px;
}
.dtop{
width:100%;
height:60px;
background-color:#20B2AA;
color:white;
}
.dsearch{
margin-left:400px;
margin-top:15px;
}
#nav {
width:170px;
height:538px;
background:#1c1f1f;
line-height:800px;
margin-top:-35px;
}
#nav ul{
display:block;
padding-top:90px;

}
#nav ul li{
list-style-type:none;
display:block;
height:40px;
line-height:40px;
text-align:center;
border-bottom:1px solid #696969;
}

#nav a{
color:white;
display:block;
width:170px;
text-decoration:none;}
#nav ul li:hover a{
background-color:#20B2AA;
}
.th3{
padding-left:50px;
padding-top:20px;
}
</style>
  </head>
  <body>
  <div class="dtop">
  <h2 class="th3">奥凯员工培训信息管理系统</h2>
  </div>
  <div class="dsearch">
  <form action="/testcc/searchac" method="post">
员工姓名： <input type="text" name="uname">
<!--  培训时间：<input type="text">-->
<input type="submit" value="搜索">
 </form>
 </div>
  <div id="nav">
  <ul >
  <li><a href="#" ><img src="img/-purchase .png"> 员工信息录入</a></li>
  <li> <a href="#"><img src="img/csearch.png"> 员工信息查询</a></li>
  <li><a href="#"><img src="img/map.png"> 员工信息删除</a></li>
  </ul>
  </div> 
      
  </body>
</html>
