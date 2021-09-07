import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class jdbconnection {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		// connection URL: "jdbc:mysql://"+host+":"+" + port+"/databasename"
		// port is 3366
		// in executor mode: "jdbc:mysql://"+localhost+":"+"3306 +"/databasename"

		String host = "localhost";
		String port = "3306";
		String user = "root";
		String password = "Win950501!";
		String db = "/demo?serverTimezone=Europe/Warsaw";

		System.out.println(TimeZone.getDefault());

		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + db, user, password);
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from demotable where scenario='visa'");

		while (rs.next()) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\resources\\geckodriver.exe");
			// user.dir = C:\\Users\\Winnie Ng\\Documents\\Project Coding\\Java\\Database
			// Testing

			WebDriver driver = new FirefoxDriver();
			driver.get("https://login.salesforce.com");

			driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(rs.getString("username"));
			driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(rs.getString("password"));

			System.out.println(rs.getString("username"));
			System.out.println(rs.getString("password"));
		}

	}

}
