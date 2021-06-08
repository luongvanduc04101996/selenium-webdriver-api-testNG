package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Topic02_TestNG_Groups {
//	WebDriver driver;
//	String projectFolder = System.getProperty("user.dir");

	@Test(groups = "user")
	public void TC01() {
		System.out.println("TC01: user group");
	}

	@Test(groups = "user")
	public void TC02() {
		System.out.println("TC02: user group");
	}

	@Test(groups = "admin")
	public void TC03() {
		System.out.println("TC03: admin group");
	}

	@Test(groups = "admin")
	public void TC04() {
		System.out.println("TC04: admin group");
	}

	@Test(groups = "admin")
	public void TC05() {
		System.out.println("TC05: admin group");
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
//		projectFolder = System.setProperty("webdriver.chrome.driver", projectFolder + "\\BrowserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.get("https://google.com");
		System.out.println("BeforeClass");
		
	}

	@AfterClass(alwaysRun = true) 
	public void afterClass() {
//		driver.quit();
		System.out.println("AfterClass");
	}

}
