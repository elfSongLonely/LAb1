package com.bookcase.struts2;

import java.sql.*;

import com.opensymphony.xwork2.ActionContext;

public class Information {
	private String title;
	public Connection connect;
	public String getTitle(){return this.title;}
	public void setTitle(String title){this.title=title;}

	public String execute() {
		ActionContext context = ActionContext.getContext();
		context.put("title", title);
		Book book = new Book();
		String AuthorID=new String();
		Author author = new Author();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect=new where().connect1;
			
			Statement stmt = connect.createStatement(); // 创建Statement对象
			String sql = "select * from book where title =\'" +title+ "\'";// 要执行的SQL
			ResultSet rs = stmt.executeQuery(sql);// 创建数据对象
			while (rs.next()) {
				if (rs.wasNull() == true)
					return "error";
				book.setISBN(rs.getString(1));
				book.setTitle(rs.getString(2));
				book.setpublisher(rs.getString(3));
				book.setpublishdate(rs.getString(4));
				book.setprice(rs.getFloat(5));
				book.setAuthorID(rs.getString(6));
				AuthorID = rs.getString(6);
			}
			sql = "select * from author where AuthorID =\'" + AuthorID + "\'"; // 要执行的SQL
			rs = stmt.executeQuery(sql);// 创建数据对象
			while (rs.next()) {
				if (rs.wasNull() == true)
					return "error";
				author.setName(rs.getString(1));
				author.setAuthorID(rs.getString(2));
				author.setAge(rs.getInt(3));
				author.setCountry(rs.getString(4));
			}
			rs.close();
			stmt.close();
			connect.close();
			context.put("book", book);
			context.put("author", author);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
