package com.bookcase.struts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class where {

	public Connection connect1;

	public where() throws SQLException {
//		 connect1=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb","root","518540");

		connect1 = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_chenyuanzhe", "w5011knj0k",
				"3ki3003yyi1k4iyhmh1xikwzkwy1imw255w144l3");

	}

}
