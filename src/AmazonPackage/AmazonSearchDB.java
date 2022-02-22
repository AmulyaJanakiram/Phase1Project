package AmazonPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonSearchDB {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
		WebElement searchtext = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchtext.sendKeys("iphone12");
		WebElement searchbutton = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		searchbutton.click();
		List<WebElement> list = driver
				.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));

		HashSet<String> hs = new HashSet<String>();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "Cts-1982");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from eproduct");
		while (rs.next()) {
			String dbProduct = rs.getString("name");
			for (int i = 0; i < list.size(); i++) {
				String amazonProduct = list.get(i).getText();
				if (dbProduct.equals(amazonProduct)) {
					hs.add(rs.getString("name"));
				}

			}
		}
		Iterator<String> itr = hs.iterator();
		System.out.println("List of products in DB and Amazon are below : ");
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		driver.close();
	}

}
