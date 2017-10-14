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
<title>书籍所有信息</title>
</head>
<body>
 	 
	<table border="1px;">  
     <tr>  
      <td>ISBN</td>  
      <td>题目</td>  
      <td>出版社</td>  
      <td>出版日期</td>  
      <td>价格</td>  
      <td>作者ID</td>  
     </tr> 
    <%Book booktmp=(Book)(ActionContext.getContext().get("book")); %>
    <% Author authortmp=(Author)(ActionContext.getContext().get("author")); %>
     <tr>    
     <td><% out.println(booktmp.getISBN()); String ISBN=booktmp.getISBN();%></td>  
     <td><% out.println(booktmp.getTitle()); %></td> 
      <% session.setAttribute("del_title",booktmp.getTitle()); %> 
     <td><% out.println(booktmp.getpublishser()); %></td>  
     <td><% out.println(booktmp.getpublishdate()); %></td>  
     <td><% out.println(booktmp.getprice()); %></td>  
     <td><% out.println(booktmp.getAuthorID()); %></td>  
     </tr>  
    </table>  
    <table border="1px;">  
     <tr>  
      <td>姓名</td>  
      <td>作者ID</td>  
      <td>年龄</td>  
      <td>国家</td>      
     </tr> 
     <tr>   
     <td><% out.println(authortmp.getName()); %></td> 
     <td><% out.println(authortmp.getAuthorID()); %></td>  
     <td><% out.println(authortmp.getAge()); %></td>  
     <td><% out.println(authortmp.getCountry()); %></td>   
     </tr>  
    </table> 	
       <form action="show">
      <input type="submit" value="显示数据库"/>
   </form>
   <form action="update">
   <input type="hidden" name="ISBN" value="<%=booktmp.getISBN()%>"> 
      <input type="submit" value="修改"/>
   </form>
   <form action="delete">
      <input type="submit" value="删除"/>
   </form>
   <form action="back">
      <input type="submit" value="跳转主页"/>
   </form>
</body>
</html>