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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加书籍</title>
</head>
<body>
   <form action="book">

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
     <td><input type="text" name="newISBN"/></td>  
     <td><input type="text" name="newTitle"/></td> 
     <td><input type="text" name="newPublisher"/></td>  
     <td><input type="text" name="newPublishdate"/></td>  
     <td><input type="text" name="newPrice"/></td>  
     <td><input type="text" name="newAuthorID"/></td>  
     </tr>  
    </table> 
          <input type="submit" value="添加图书"/>
   </form>
</body>
</html>