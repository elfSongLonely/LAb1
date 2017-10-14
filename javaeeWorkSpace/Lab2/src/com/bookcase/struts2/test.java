package com.bookcase.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class test {
	public static void main(String[] args) {
		String ISBN="1";
		String newDate="2000-1-2";
		try {
			// 调用Class.forName()方法加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb?useSSL=false", "root", "518540");
			Statement stmt = connect.createStatement(); // 创建Statement对象
			String sql = "update book set publishdate =\'" + newDate + "\'where ISBN = \'" + ISBN +"\'";
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.executeUpdate();
			stmt.close();
			connect.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
