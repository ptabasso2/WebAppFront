package com.pej.web.struts.da;

import java.sql.*;

public class Admin {
	
	// method for create connection
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/concretepage", "root", "admin");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// method for save user data in database
	public int registerUser(String uname, String uemail, String upass, String udeg) throws Exception {
		int i = 0;
		try {
			//String sql = "INSERT INTO STRUTS2CRUD VALUES (?,?,?,?);DO SLEEP(5)";
			String sql = "INSERT INTO struts2crud select ?, ?, ?, ? from (SELECT sleep(RAND()*(0.5-0.2)+0.2)) as t";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, uemail);
			ps.setString(3, upass);
			ps.setString(4, udeg);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return i;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method for fetch saved user data
	public ResultSet report() throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT UNAME,UEMAIL,UPASS,UDEG, sleep(RAND()*(0.5-0.2)+0.2) FROM struts2crud";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method for fetch old data to be update
	public ResultSet fetchUserDetails(String uemail) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT UNAME,UEMAIL,UPASS,UDEG, sleep(RAND()*(0.5-0.2)+0.2) FROM struts2crud WHERE UEMAIL=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, uemail);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method for update new data in database
	public int updateUserDetails(String uname, String uemail, String upass, String udeg, String uemailhidden)
			throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		int i = 0;
		try {
			//String sql = "UPDATE STRUTS2CRUD SET UNAME=?,UEMAIL=?,UPASS=?,UDEG=? WHERE UEMAIL=?";
			String sql = "UPDATE struts2crud SET UNAME=(select t.name from (SELECT ? as name, sleep(RAND()*(1-0.5)+0.5)) as t), UEMAIL=(select t.email from (SELECT ? as email, sleep(RAND()*(1-0.5)+0.5)) as t), UPASS=(select t.pass from (SELECT ? as pass, sleep(RAND()*(1-0.5)+0.5)) as t), UDEG=(select t.deg from (SELECT ? as deg, sleep(RAND()*(1-0.5)+0.5)) as t) WHERE UEMAIL=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, uemail);
			ps.setString(3, upass);
			ps.setString(4, udeg);
			ps.setString(5, uemailhidden);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			getConnection().rollback();
			return 0;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}

	// method for delete the record
	public int deleteUserDetails(String uemail) throws SQLException, Exception {
		getConnection().setAutoCommit(false);
		int i = 0;
		try {
			//String sql = "DELETE FROM STRUTS2CRUD WHERE UEMAIL=?";
			String sql = "delete from struts2crud where uemail = (select t.uemail from (SELECT *,sleep(RAND()*(1-0.5)+0.5) from struts2crud) as t where t.uemail = ?)";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, uemail);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			getConnection().rollback();
			return 0;
		} finally {
			if (getConnection() != null) {
				getConnection().close();
			}
		}
	}
}
