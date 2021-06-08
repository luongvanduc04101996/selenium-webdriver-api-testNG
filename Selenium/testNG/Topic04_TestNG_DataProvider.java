package testNG;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class Topic04_TestNG_DataProvider {
	WebDriver driver;
	String projectFolder =  System.getProperty("user.dir");
	WebDriverWait explicitWait;
	@Test(dataProvider = "dp")
	public void TC01_Create_User(String user, String passWord) {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log In']")));
		driver.findElement(By.xpath("//a[text()='Log In']")).click();
		driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys(user);
		driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys(passWord);
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "duc1", "123456" }, new Object[] { "duc2", "123456" }, };
	}
	
	@BeforeClass
	public void beforeClass() {
		projectFolder = System.setProperty("webdriver.chrome.driver", projectFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
