package com.userInfoStrutsApp.business;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;
public class DBManager {
	public static Connection getConnection() throws ServletException{
		Connection con = null;
				try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/userinfo_db");
			con = ds.getConnection();
		}
		catch (SQLException se) {
			throw new ServletException(se);
		}
		catch (NamingException ne) {
			throw new ServletException(ne);
		}
		return con;
	}
}