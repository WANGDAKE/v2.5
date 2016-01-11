package com.xls.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	
	/**
	 * 连接数mysql据库
	 * @param DRIVER 数据库驱动
	 * @param URL 数据库连接地址
	 * @param USER 数据库用户名
	 * @param PWD 用户密码
	 */
	
	private final static String DRIVER="com.mysql.jdbc.Driver";
	private final static String URL="jdbc:mysql://localhost:3306/xxxx";
	private final static String USER="root";
	private final static String PWD="jenkins";
	
	private static Connection con;
	
	public static Connection getConnection(){
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
	public static void close(ResultSet rs, Statement ps, Connection con){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();	
			}
			if(con!=null){
				con.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}	
	