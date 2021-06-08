package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic18_ExplicitWait_Part1 {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectFolderPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectFolderPath+"\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC1_ExplicitWait_3s() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicitWait = new WebDriverWait(driver, 3);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']//img")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4")).isDisplayed());
	}
	
	@Test
	public void TC2_ExplicitWait_5s() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicitWait = new WebDriverWait(driver, 5);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']//img")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4")).isDisplayed());
	}
	
	@Test
	public void TC3_ExplicitWait_3s() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicitWait = new WebDriverWait(driver, 3);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']//h4")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4")).isDisplayed());
	}

	@Test
	public void TC4_ExplicitWait_5s() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicitWait = new WebDriverWait(driver, 5);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']//h4")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']//h4")).isDisplayed());
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}
	
	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}

}
