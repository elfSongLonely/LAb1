<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>书籍管理系统</title>
</head>
<body>
   <form action="allBook">
      <label for="name">作者姓名</label><br/>
      <input type="text" name="name"/>
      <input type="submit" value="作者查询"/>
   </form>
      <form action="show">
      <input type="submit" value="显示数据库"/>
   </form>
   <a href="newBook.jsp">新增图书</a>

</body>
</html>