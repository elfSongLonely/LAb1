<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head>
<title>书籍名称</title>
</head>
<body>

	<table align="center" width="300" border="1">
		<tr>
			<td align="center" colspan="6">
				<h2>
					<s:property value="name" /><%="的所有图书"%></h2>
			</td>
		</tr>
		<tr align="center">

			<c:forEach items="${titleList}" var="title">
				<tr><th><a href="inform?title=${title}"> ${title} </a></th></tr>
			</c:forEach>

		</tr>
	</table>
	   <form action="show">
      <input type="submit" value="显示数据库"/>
   </form>
</body>
</html>