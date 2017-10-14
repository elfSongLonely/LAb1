package com.bookcase.struts2;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Searchauthor extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String trueName;
	public Connection connect;
	public String getName() { 
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String execute() {
		
		try {
			trueName = new String(name.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		Queue<String> queue = new LinkedList<String>();
		List<String>titleList = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect=new where().connect1;


			Statement stmt = connect.createStatement(); // 创建Statement对象
			String sql = "select * from author where name =\'" + name + "\'"; // 要执行的SQL
			ResultSet rs = stmt.executeQuery(sql);// 创建数据对象/ 创建数据对象
			while (rs.next()) {
				queue.offer(rs.getString(2));
			}
			if (queue.isEmpty() == true) {
				return "error";
			}
			String AuthorID = new String();
			while ((AuthorID = queue.poll()) != null) {
				sql = "select * from book where AuthorID ='" + AuthorID + "'"; // 要执行的SQL
				rs = stmt.executeQuery(sql);// 创建数据对象
				while (rs.next()) {
					titleList.add(rs.getString(2));
				}
			}
			ActionContext.getContext().put("titleList", titleList);
			ActionContext.getContext().put("name", name);
			rs.close();
			stmt.close();
			connect.close();
			if (titleList.size() == 0) {
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