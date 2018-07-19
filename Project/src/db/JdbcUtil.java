package db;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

//DB와 관련된 기능들을 static 메소드로 정의해 놓은 클래스
public class JdbcUtil {
	//getConnection() : DB접속
	public static Connection getConnection() {
		Connection con = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx =
					(Context)initCtx.lookup("java:comp/env");
			DataSource ds = 
					(DataSource)envCtx.lookup("jdbc/OracleDB");
			con= ds.getConnection();
			con.setAutoCommit(false);
			System.out.println("connect success");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}

public static void close(Connection con) {
	try {
		con.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}

public static void close(Statement stmt) {
	try {
		stmt.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}

public static void close(ResultSet rs) {
	try {
		rs.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}

public static void commit(Connection con) {
	try {
		con.commit();
	}catch(Exception e) {
		e.printStackTrace();
	}
}

public static void rollback(Connection con) {
	try {
		con.rollback();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}