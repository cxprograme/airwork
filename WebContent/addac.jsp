<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.d1{
margin-left:400px;
margin-top:-500px;
}
form{
line-height:30px;}
</style>
</head>
<body>
<jsp:include page="/jstest.jsp" flush="true"></jsp:include>

<div class="d1">
<h3>添加员工信息</h3><br/>
<form>
<input type="hidden" value=""  name="">
员工姓名：
<input type="text"><br/>
培训任务：
<select>
<option select="checked" style="text-align:center">请选择任务</option>
<option >紧急医学 新</option>
<option >MA60转机型及复训</option>
<option >乘务长</option>
<option >乘务教员</option>
<option >乘务检查员</option>
<option >国际航线</option>
<option >头等舱</option>
<option >辞职</option>
<option >B737-900ER差异</option>
<option >高原航线</option>
</select><br/>
培训时间： 
<input type="date">
</form>
</div>
</body>
</html>