package com.bookcase.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

public class Show 
{
	public String showAll()
	{
		List<Book> bookList = new ArrayList<Book>();
		List<Author> authorList = new ArrayList<Author>();
		Book book=new Book();
		Author author=new Author();
		try {
			Class.forName("com.mysql.jdbc.Driver");

Connection connect = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_chenyuanzhe", "w5011knj0k", "3ki3003yyi1k4iyhmh1xikwzkwy1imw255w144l3");

Statement stmt = connect.createStatement(); // 创建Statement对象
			String sql = "select * from book"; // 要执行的SQL
			ResultSet rs = stmt.executeQuery(sql);// 创建数据对象/ 创建数据对象
			while (rs.next()) {
				book.setISBN(rs.getString(1));
				book.setTitle(rs.getString(2));
				book.setpublisher(rs.getString(3));
				book.setpublishdate(rs.getString(4));
				book.setprice(rs.getFloat(5));
				book.setAuthorID(rs.getString(6));
				bookList.add(book);
			}
			sql = "select * from author"; // 要执行的SQL
			rs = stmt.executeQuery(sql);// 创建数据对象/ 创建数据对象
			while (rs.next()) {
				author.setName(rs.getString(1));
				author.setAuthorID(rs.getString(2));
				author.setAge(rs.getInt(3));
				author.setCountry(rs.getString(4));
				authorList.add(author);
			}
			ActionContext.getContext().put("authorList", authorList);
			rs.close();
			stmt.close();
			connect.close();
			if (bookList.size() == 0) {
				System.out.println("no book");
				return "error";
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
