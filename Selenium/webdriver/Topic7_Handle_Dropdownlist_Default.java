package webdriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic7_Handle_Dropdownlist_Default {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver = new FirefoxDriver();
		driver.get("https://demo.nopcommerce.com/");
	}
	@Test
	public void TC1_Register() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("");
		driver.findElement(By.id("LastName")).sendKeys("");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
