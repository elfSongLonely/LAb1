package com.bookcase.struts2;
//first blood
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class Create {
	public Connection connect;
	private Book newBook = new Book();
	private String newISBN;
	private String newTitle;
	private String newPublisher;
	private String newPublishdate;
	private String newPrice;
	private String newAuthorID;
	private String newName;
	private int newAge;
	private String newCountry;

	public void setnewISBN(String newISBN) {
		this.newISBN = newISBN;
	}

	public String getnewISBN() {
		return this.newISBN;
	}

	public void setnewTitle(String newTitle) {
		this.newTitle = newTitle;
	}

	public String getnewTitle() {
		return this.newTitle;
	}

	public void setnewPublisher(String newPublisher) {
		this.newPublisher = newPublisher;
	}

	public String getnewPublisher() {
		return this.newPublisher;
	}

	public void setnewPublishdate(String newPublishdate) {
		this.newPublishdate = newPublishdate;
	}

	public String getnewPublishdate() {
		return this.newPublishdate;
	}

	public void setnewPrice(String newPrice) {
		this.newPrice = newPrice;
	}

	public String getnewPrice() {
		return this.newPrice;
	}

	public void setnewAuthorID(String newAuthorID) {
		this.newAuthorID = newAuthorID;
	}

	public String getnewAuthorID() {
		return this.newAuthorID;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getnewName() {
		return this.newName;
	}

	public void setNewAge(int newAge) {
		this.newAge = newAge;
	}

	public int getNewAge() {
		return this.newAge;
	}

	public void setNewCountry(String newCountry) {
		this.newCountry = newCountry;
	}

	public String getNewCountry() {
		return this.newCountry;
	}

	public String createNewBook() {
		HttpServletRequest re = ServletActionContext.getRequest();
		newISBN = (String) re.getParameter("newISBN");
		newTitle = (String) re.getParameter("newTitle");
		newPublisher = (String) re.getParameter("newPublisher");
		newPublishdate = (String) re.getParameter("newPublishdate");
		Date date = Date.valueOf(newPublishdate);
		newPrice = (String) re.getParameter("newPrice");
		float price = Float.parseFloat(newPrice);
		newAuthorID = (String) re.getParameter("newAuthorID");
		newBook.setAuthorID(newAuthorID);
		newBook.setISBN(newISBN);
		newBook.setprice(price);
		String str = newPublishdate.toString();
		newBook.setpublishdate(str);
		newBook.setpublisher(newPublisher);
		newBook.setTitle(newTitle);
		try {
			// 调用Class.forName()方法加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			
			connect=new where().connect1;

			
			Statement stmt = connect.createStatement(); // 创建Statement对象
			String sql = "select * from author where AuthorID =\'" + newAuthorID + "\'"; // 要执行的SQL
			ResultSet rs = stmt.executeQuery(sql);// 创建数据对象
			rs.next();
			if (rs.wasNull() == false) {
				sql = "INSERT INTO book(ISBN,title, publisher,publishdate,price,AuthorID)" + " VALUES (\'" + newISBN
						+ "\', \'" + newTitle + "\', \'" + newPublisher + "\',\'" + newPublishdate + "\',\'" + newPrice
						+ "\',\'" + newAuthorID + "\')"; // 插入数据的sql语句
				PreparedStatement pst = connect.prepareStatement(sql);
				pst.executeUpdate();
				stmt.close();
				connect.close();
				return "success";
			} else {
				stmt.close();
				connect.close();
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String createNewAuthor() {
		try {
			HttpServletRequest re = ServletActionContext.getRequest();
			newISBN = (String) re.getParameter("newISBN");
			newName = (String) re.getSession().getAttribute("newName");
			String age = (String) re.getParameter("newAge");
			newAge = Integer.valueOf(age).intValue();
			newCountry = (String) re.getParameter("newCountry");
			// 调用Class.forName()方法加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");

			connect=new where().connect1;			
			Statement stmt = connect.createStatement(); // 创建Statement对象
			String sql = "INSERT INTO author(name,AuthorID,age,country)" + " VALUES (\'" + newName + "\', \'"
					+ newAuthorID + "\',\'" + newAge + "\',\'" + newCountry + "\')"; // 插入数据的sql语句
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.executeUpdate();
			sql = "INSERT INTO book(ISBN,title, publisher,publishdate,price,AuthorID)" + " VALUES (\'"
					+ newBook.getISBN() + "\', \'" + newBook.getTitle() + "\', \'" + newBook.getpublishser() + "\',\'"
					+ newPublishdate + "\',\'" + newPrice + "\',\'" + newAuthorID + "\')"; // 插入数据的sql语句
			pst = connect.prepareStatement(sql);
			pst.executeUpdate();
			stmt.close();
			connect.close();
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
