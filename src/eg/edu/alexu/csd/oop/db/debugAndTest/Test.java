package eg.edu.alexu.csd.oop.db.debugAndTest;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import eg.edu.alexu.csd.oop.jdbc.DriverImpl;

public class Test {

	public static void main(String[] args) throws SQLException {
		Driver d = new DriverImpl();
		Properties info = new Properties();
		File dir = new File("debug");
		info.put("path", dir.getAbsoluteFile());
		Connection cn = d.connect("sad", info);
		Statement st = cn.createStatement();
		st.execute("DROP DATABASE testt");
		st.execute("CREATE DATABASE testt");
		st = cn.createStatement();
		st.execute("CREATE TABLE tab(col int)");
		st.execute("INSERT INTO tab (col) values (8)");
		ResultSet rs = st.executeQuery("SELECT * FROM tab");
		rs.next();
		System.out.println(rs.getInt(1));
		ResultSetMetaData meta = rs.getMetaData();
		System.out.println(meta.getColumnName(1));
		System.out.println(meta.getColumnCount());
	}

}
