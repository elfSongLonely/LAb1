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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加作者信息</title>
</head>
<body> 
<h2>没有作者，请添加作者</h2>
	<table border="1px;">
		<tr>
			<td>姓名</td>
			<td>年龄</td>
			<td>国家</td>
		</tr>
		<tr>
			<td><input type="text" name="newName" /></td>
			<td><input type="text" name="newAge" /></td>
			<td><input type="text" name="newCountry" /></td>
		</tr>
	</table>
	<form action="author">
		<input type="submit" value="添加作者" />
	</form>
</body>
</html>