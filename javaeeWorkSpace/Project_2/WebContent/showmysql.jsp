<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示初始数据库</title>
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

		<tr>
			<td>9787506261579</td>
			<td>Jane Eyre</td>
			<td>世界图书出版公司</td>
			<td>2003-11-13</td>
			<td>18</td>
			<td>Charlotte</td>
		</tr>
		<tr>
			<td>1</td>
			<td>wu</td>
			<td>the</td>
			<td>1999-01-01</td>
			<td>1</td>
			<td>chen</td>
		</tr>
		<tr>
			<td>9787020027606</td>
			<td>Wuthering Heights</td>
			<td>人民文学出版社</td>
			<td>1999-01-05</td>
			<td>27.3</td>
			<td>Emily</td>
		</tr>
		<tr>
			<td>9787532723447</td>
			<td>The old man and the sea</td>
			<td>上海译文出版社</td>
			<td>1999-10-04</td>
			<td>8.2</td>
			<td>Zdenek Miler</td>
		</tr>
		<tr>
			<td>9787532724987</td>
			<td>The Tenant of Wildfell Hall</td>
			<td>上海世纪出版集团</td>
			<td>2000-12-04</td>
			<td>21.8</td>
			<td>Annie</td>
		</tr>
		<tr>
			<td>9787532725021</td>
			<td>Agnes Grey</td>
			<td>上海世纪出版集团</td>
			<td>2000-12-03</td>
			<td>11.1</td>
			<td>Annie</td>
		</tr>
		<tr>
			<td>9787532725694</td>
			<td>Forests in Norway</td>
			<td>上海译文出版社</td>
			<td>2001-01-25</td>
			<td>18.8</td>
			<td>Watanabe</td>
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
			<td>Bront</td>
			<td>Annie</td>
			<td>29</td>
			<td>England</td>
		</tr>

		<tr>
			<td>Bront</td>
			<td>Charlotte</td>
			<td>39</td>
			<td>England</td>
		</tr>
		<tr>
			<td>yuanzhe</td>
			<td>chen</td>
			<td>20</td>
			<td>China</td>
		</tr>
		<tr>
			<td>Bront</td>
			<td>Emily</td>
			<td>30</td>
			<td>England</td>
		</tr>
		<tr>
			<td>Haruki Murakami</td>
			<td>Watanabe</td>
			<td>30</td>
			<td>Japan</td>
		</tr>
		<tr>
			<td>Hemingway</td>
			<td>Zdenek Miler</td>
			<td>62</td>
			<td>America</td>
		</tr>
	</table>
	<form action="back">
		<input type="submit" value="跳转主页" />
	</form>
</body>
</html>