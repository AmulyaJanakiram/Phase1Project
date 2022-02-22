package AmazonPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonSearch {

	public static void main(String[] args) {
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
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='a-price-whole']"));

		System.out.println("The count is " + list.size());

		for (int i = 0; i < list.size(); i++) {
			System.out.println(" Result :  " + list.get(i).getText() + " Price : " + price.get(i).getText());
		}

	}

}
