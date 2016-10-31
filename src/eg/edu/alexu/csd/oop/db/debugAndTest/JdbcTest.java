package eg.edu.alexu.csd.oop.db.debugAndTest;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import eg.edu.alexu.csd.oop.jdbc.DriverImpl;

public class JdbcTest {
	
	@Test
	public void testCreateAndOpenAndDropDatabase() throws SQLException {
		File dummy = null;
		Driver driver = new DriverImpl();
		Properties info = new Properties();
		File dbDir = new File("debug/db/test/sample");
		info.put("path", dbDir.getAbsoluteFile());
		System.out.println(info.get("path"));
		Connection connection = driver.connect("jdbc:xmldb://localhost", info);
		{
			Statement statement = connection.createStatement();
			statement.execute("DROP DATABASE SaMpLe");
			statement.execute("CREATE DATABASE SaMpLe");
			String files[] = dbDir.list();
			Assert.assertTrue("Database directory is not empty!", files == null || files.length == 0);
			dummy = new File(dbDir, "dummy");
			try {
				boolean created = dummy.createNewFile();
				Assert.assertTrue("Failed t create file into DB", created && dummy.exists());
			} catch (IOException e) {
				Assert.fail("Failed t create file into DB");
			}
			statement.close();
		}
		{
			Statement statement = connection.createStatement();
			statement.execute("CREATE DATABASE sAmPlE");
			String files[] = dbDir.list();
			Assert.assertTrue("Database directory is empty after opening!", files.length > 0);
			Assert.assertTrue("Failed t create find a previously created file into DB", dummy.exists());
			statement.close();
		}
		{
			Statement statement = connection.createStatement();
			statement.execute("CREATE DATABASE SAMPLE");
			statement.execute("DROP DATABASE SAMPLE");
			String files[] = dbDir.list();
			Assert.assertTrue("Database directory is not empty after drop!", files == null || files.length == 0);
			statement.close();
		}
		connection.close();
	}
}
