package com.bookcase.struts2;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DeleteBook extends ActionSupport {
	public Connection connect;
	public String execute() throws SQLException, ClassNotFoundException {
		String deleteBook = new String();
		HttpServletRequest re = ServletActionContext.getRequest();
		String title = (String) re.getSession().getAttribute("del_title");
//		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect=new where().connect1;
			
			Statement stmt = connect.createStatement(); // ����Statement����
//			String sql = "select * from book where title =\'" + title + "\'"; // Ҫִ�е�SQL
//			ResultSet rs = stmt.executeQuery(sql);// �������ݶ���
//			while(rs.next())
//			{
				deleteBook = "delete from book where title =\'" + title + "\'";
//			}

			PreparedStatement pst = connect.prepareStatement(deleteBook);
			pst.executeUpdate();
//			rs.close();
			stmt.close();
			return "success";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
	}

}
