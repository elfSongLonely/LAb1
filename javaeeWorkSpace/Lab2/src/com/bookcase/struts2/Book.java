package com.bookcase.struts2;
//first blood
import java.sql.Date;


public class Book
{
	private String ISBN;
	private String title;
	private String publisher;
	private Date publishdate;
	private float price;
	private String AuthorID;
	public void setISBN(String ISBN){this.ISBN=ISBN;}
	public String getISBN(){return this.ISBN;}
	public void setTitle(String title)	{this.title=title;}
	public String getTitle()	{return this.title;}
	public void setpublisher(String publisher){this.publisher=publisher;}
	public String getpublishser(){return this.publisher;}
	public void setpublishdate(String publishdate){this.publishdate=Date.valueOf(publishdate);}
	public Date getpublishdate(){return this.publishdate;}
	public void setprice(float price){this.price=price;}
	public float getprice()	{return this.price;}
	public void setAuthorID(String AuthorID){this.AuthorID=AuthorID;}
	public String getAuthorID(){return this.AuthorID;}
	public String execute() throws Exception {return "success";}
}