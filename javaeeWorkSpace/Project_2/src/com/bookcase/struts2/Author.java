package com.bookcase.struts2;
//first blood
public class Author 
{
	private String name;
	private String AuthorID;
	private int age;
	private String country;
	public void setName(String name){this.name=name;}
	public String getName(){return this.name;}
	public void setAuthorID(String AuthorID){this.AuthorID=AuthorID;}
	public String getAuthorID(){return this.AuthorID;}
	public void setAge(int age){this.age=age;}
	public int getAge(){return this.age;}
	public void setCountry(String country){this.country=country;}
	public String getCountry(){return this.country;}
	public String execute(){return "success";}
}
