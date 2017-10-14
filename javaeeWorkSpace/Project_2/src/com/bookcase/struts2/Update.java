package com.bookcase.struts2;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Update extends ActionSupport
{
	public Connection connect;
	private String ISBN;
	private String publisher;
	private String publishdate;
	private String price;
	private String AuthorID;
	public void setISBN(String ISBN){this.ISBN=ISBN;}
	public String getISBN(){return this.ISBN;}
	public void setpublisher(String publisher){this.publisher=publisher;}
	public String getpublishser(){return this.publisher;}
	public void setAuthorID(String AuthorID){this.AuthorID=AuthorID;}
	public String getAuthorID(){return this.AuthorID;}
	String updateDate(String newDate, String ISBN) {
		try {
			// 调用Class.forName()方法加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			connect=new where().connect1;
			Statement stmt = connect.createStatement(); // 创建Statement对象
			String sql = "update book set publishdate =\'" + newDate + "\'where ISBN = \'" + ISBN +"\'";
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.executeUpdate();
			stmt.close();
			connect.close();
			return "sucess";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	String updatePrice(String newPrice, String ISBN)
	{
		try {
			// 调用Class.forName()方法加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			connect=new where().connect1;
			Statement stmt = connect.createStatement(); // 创建Statement对象
			String sql = "update book set price = " + newPrice + " where ISBN = \'" + ISBN +  "\'";
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.executeUpdate();
			stmt.close();
			connect.close();
			return "success";		
			} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	String updatePublisher(String newPublisher, String ISBN) {
		try {
			// 调用Class.forName()方法加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			connect=new where().connect1;
			String sql = "update book set publisher =\'" + newPublisher + "\'where ISBN = \'" + ISBN+"\'";
			Statement stmt = connect.createStatement(); // 创建Statement对象
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.executeUpdate();
			stmt.close();
			connect.close();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	String updateAuthor(String AuthorID,String ISBN) 
	{
		try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            connect=new where().connect1;
            Statement stmt = connect.createStatement(); //创建Statement对象
            String sql="update book set AuthorID = \'"+AuthorID+"\' where ISBN = \'"+ISBN+"\'";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.executeUpdate();
            stmt.close();
            connect.close();
            return "success";
            }
        catch(Exception e)
            {
                e.printStackTrace();
                return "error";
            }

	}
	public String execute() throws Exception 
	{    
		HttpServletRequest re = ServletActionContext.getRequest();
		ISBN= (String)re.getSession().getAttribute("old_isbn");
		publisher = (String)re.getParameter("publisher");
		publishdate = (String)re.getParameter("publishdate");
		price = (String)re.getParameter("price");
		AuthorID = (String)re.getParameter("AuthorID");
		if(ISBN==null)
			return "error";
		else
		{
			boolean flag=true;
			if(publisher!="")
			{
				if(updatePublisher(publisher, ISBN)=="error")
					flag=false;
			}
			if(flag==false)
				return "error";
			if(publishdate!="")
			{
				String date=publishdate.toString();
				if(updateDate(date, ISBN)=="error")
					flag=false;
			} 
			if(flag==false)
				return "error";
			if(price!="")
			{
				if(updatePrice(price, ISBN)=="error")
					flag=false;
			}
			if(flag==false)
				return "error";
			if(AuthorID!="")
			{
				if(updateAuthor(AuthorID, ISBN)=="error")
					flag=false;
			}
			if(flag==false)
				return "error";
			return "success";
		}
		
	}
}
