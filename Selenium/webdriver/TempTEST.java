package webdriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class TempTEST {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4");
	}
	@Test
	public void TC1_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
