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

public class Topic19_ExplicitWait_Part2 {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectFolderPath = System.getProperty("user.dir");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectFolderPath+"\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
	}

	@Test
	public void TC1_ExplicitWait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='calendarContainer']")));
		//scrollToElement(driver, "//div[@class='rcTitlebar']");
		
		WebElement notifyDayElement = driver.findElement(By.xpath("//div[@class='datesContainer']//span"));
		Assert.assertEquals(notifyDayElement.getText(), "No Selected Dates to display.");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='12']"))).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@style,'position')]")));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='12']/parent::td[@class='rcSelected']")));
		
		notifyDayElement = driver.findElement(By.xpath("//div[@class='datesContainer']//span"));
		Assert.assertEquals(notifyDayElement.getText(), "Wednesday, May 12, 2021");
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
