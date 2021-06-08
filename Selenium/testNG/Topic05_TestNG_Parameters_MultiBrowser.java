package testNG;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class Topic05_TestNG_Parameters_MultiBrowser {
	WebDriver driver;
	String projectFolder =  System.getProperty("user.dir");
	@Test
	public void TC01_Create_User() {
		driver.get("http://live.demoguru99.com/");
	}
	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		
		if(browserName.equals("chrome")) {
			projectFolder = System.setProperty("webdriver.chrome.driver", projectFolder + "\\BrowserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			projectFolder = System.setProperty("webdriver.edge.driver", projectFolder + "\\BrowserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else if (browserName.equals("firefox")) {
			projectFolder = System.setProperty("webdriver.gecko.driver", projectFolder + "\\BrowserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			new RuntimeException("Browser is required");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
