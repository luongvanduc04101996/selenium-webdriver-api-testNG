package webdriver;

import org.testng.annotations.Test;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic15_UploadFiles {
	WebDriver driver;
	String projectfolderPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectfolderPath + "\\BrowserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectfolderPath + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
	}
	
	
	public void TC1_UploadFile_By_Senkey() {
		String flowerImageName = "flower.jpg";
		String rapitImageName = "rapit.jpg";
		String windowImageName = "window.jpg";
		
		String flowImagePath = projectfolderPath + "\\Uploadfile\\" + flowerImageName;
		String rapitImagePath = projectfolderPath + "\\Uploadfile\\" + rapitImageName;
		String windowImagePath = projectfolderPath + "\\Uploadfile\\" + windowImageName;
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		uploadElement.sendKeys(flowImagePath);
		sleepInSeconds(2);
		
		uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		uploadElement.sendKeys(rapitImagePath);
		sleepInSeconds(2);
		
		uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		uploadElement.sendKeys(windowImagePath);
		sleepInSeconds(2);
		
		List<WebElement> allElementsImageUpload = driver.findElements(By.xpath("//tbody[@class='files']//button[@class='btn btn-primary start']"));
		Assert.assertEquals(allElementsImageUpload.size(), 3);
		
		for (WebElement tempElement : allElementsImageUpload) {
			tempElement.click();
			sleepInSeconds(2);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+flowerImageName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+rapitImageName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+windowImageName+"']")).isDisplayed());
	}
	@Test
	public void TC2_UploadFile_By_Senkey() {
		String flowerImageName = "flower.jpg";
		String rapitImageName = "rapit.jpg";
		String windowImageName = "window.jpg";
		
		String flowImagePath = projectfolderPath + "\\Uploadfile\\" + flowerImageName;
		String rapitImagePath = projectfolderPath + "\\Uploadfile\\" + rapitImageName;
		String windowImagePath = projectfolderPath + "\\Uploadfile\\" + windowImageName;
		
		driver.get("https://gofile.io/?t=uploadFiles");
		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		uploadElement.sendKeys(flowImagePath + "\n" + rapitImagePath +"\n" + windowImagePath);
		
		driver.findElement(By.xpath("//button[@id='uploadFiles-btnUpload']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Download link']/following-sibling::td")));
		driver.findElement(By.xpath("//td[text()='Download link']/following-sibling::td")).click();
		
		String parentID = driver.getWindowHandle();
		Set<String> allID = driver.getWindowHandles();
		for (String tempID : allID) {
			if (!tempID.equals(parentID)) {
				driver.switchTo().window(tempID);
			}
		}
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Are you protected with a VPN ?']")));
		driver.findElement(By.xpath("//button[text()='I have a VPN already']")).click();
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//td[@class='text-right']//button"))));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public void sleepInSeconds(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
