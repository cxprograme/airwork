<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,active.acentity,user.uentity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.lt3{
margin-left:400px;
margin-top:-400px;
}
</style>
</head>
<body>
<jsp:include page="/jstest.jsp" flush="true"></jsp:include>
    <form action="UploadExcelServlet" method="post" enctype="multipart/form-data">
        上传附件：<input type="file" name="file"><br>
        <input type="submit" value="确定">
    </form>
	<table width="580" class="lt3">
	<tr>
	<td>员工</td>
	<td>培训项目</td>
	<td>培训内容</td>
	<td>培训时间</td>
	</tr>
	<%
	List<uentity> ulist=(List)request.getAttribute("userli");
	//System.out.println("ulist:"+ulist);
	if(ulist.size()>0){
		for(int j=0;j<ulist.size();j++){
			uentity u=(uentity)ulist.get(j);%>
			<tr align="left">
			<td height="25" align="center" width="35"
				style="border-bottom: #999999 1px dashed"><%=u.getName() %></td>
		<% }%>
	<% }%>
	
		<%
 	List<acentity> list=(List)request.getAttribute("acentitiy");
 	//System.out.println("list:"+list);
 	if(list.size()>0){
	    for(int i=0;i<list.size();i++) {
    	 acentity a= (acentity)list.get(i);%>
			<td height="25" width="130" style="border-bottom: #999999 1px dashed"><%=a.getActivename()%></td>
			<td height="25" align="center" width="35"
				style="border-bottom: #999999 1px dashed"><%=a.getAcontent() %></td>
			<td height="25" align="center" width="35"
				style="border-bottom: #999999 1px dashed"><%=a.getAcdate()%></td>
		</tr>
		<%} %>
		<%} %>
	</table>

</body>
</html>