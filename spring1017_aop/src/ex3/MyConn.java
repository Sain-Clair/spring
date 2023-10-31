package ex3;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MyConn {
	private static DataSource ds;
	static {
		InitialContext ctx;
		try {
			ctx= new InitialContext();
			ds = (DataSource)ctx.lookupLink("java:comp/env/jdbc/myora");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getDs() throws SQLException{
		return ds.getConnection();
	}

}
