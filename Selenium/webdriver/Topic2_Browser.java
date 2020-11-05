package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic2_Browser {
	WebDriver driver;
	String projectFolder = System.getProperty("user.dir");
	
	@Test
	public void TC1_FireFox() {
		driver = new FirefoxDriver();
		
		driver.get("https://google.com");
		
		driver.quit();
	}
	
	@Test
	public void TC2_Chrome() {
		System.setProperty("webdriver.chrome.driver", projectFolder + "\\BrowserDrivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.get("https://google.com");
		
		driver.quit();
	}
	
	@Test
	public void TC3_Edge() {
		System.setProperty("webdriver.edge.driver", ".\\BrowserDrivers\\chromedriver.exe");
		
		driver = new EdgeDriver();
		
		driver.get("https://google.com");
		
		driver.quit();
	}
	@Test
		public void TC4_Edge_2() {
			System.setProperty("webdriver.edge.driver", ".\\BrowserDrivers\\chromedriver.exe");
			
			driver = new EdgeDriver();
			
			driver.get("https://google.com");
			
			driver.quit();
	}
	
}
