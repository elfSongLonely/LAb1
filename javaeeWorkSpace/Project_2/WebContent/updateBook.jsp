<%@page import="com.bookcase.struts2.Book"%>
<%@page import="com.bookcase.struts2.Author"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<%
    String ISBN=request.getParameter("ISBN"); 
    session.setAttribute("old_isbn", ISBN);
%>
	<form action="run" method="post">
		<table border="1px;">
			<tr>
				<td>ISBN</td>
				<td>题目</td>
				<td>出版社</td>
				<td>出版日期</td>
				<td>价格</td>
				<td>作者ID</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="text" name="publisher" /></td>
				<td><input type="text" name="publishdate" /></td>
				<td><input type="text" name="price" /></td>
				<td><input type="text" name="AuthorID" /></td>
			</tr>
		</table>
		<input type="submit" value="更新" />
	</form>

</body>
</html>