package com.bookcase.struts2;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Jump extends ActionSupport {
	private String isbn;
	public String execute(){
		HttpServletRequest re = ServletActionContext.getRequest();
		isbn=re.getParameter("ISBN");
		re.setAttribute("ISBN", isbn);
		return SUCCESS;
	}
}
