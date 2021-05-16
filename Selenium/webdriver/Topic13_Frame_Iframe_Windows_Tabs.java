package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic13_Frame_Iframe_Windows_Tabs {
	WebDriver driver;
	String projectFolder = System.getProperty("user.dir");
	Select select;
	JavascriptExecutor jvscrip;
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", projectFolder + "\\BrowserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  jvscrip = (JavascriptExecutor) driver;
  }

  public void TC1_Iframe_Frame() {
	  String name, phone, note, search;
	  name = "test";
	  phone = "0234567899";
	  note = "345";
	  search = "Excel";
	  driver.get("https://kyna.vn/");
	  sleepInSeconds(5);
	  WebElement elementFacebookIframe = driver.findElement(By.xpath("//div[@class='fanpage ']//iframe"));
	  WebElement elementChatIframe = driver.findElement(By.xpath("//div[@id='cs-live-chat']/iframe"));
	 
	  driver.switchTo().frame(elementFacebookIframe);
	  //driver.switchTo().frame(0);
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Kyna.vn']")).isDisplayed());
	
	  driver.switchTo().defaultContent();
	  driver.switchTo().frame(elementChatIframe);
	  clickByJavaScript(driver.findElement(By.xpath("//div[@class='button_text']")));
	  driver.findElement(By.xpath("//input[contains(@class,'input_name')]")).sendKeys(name);
	  driver.findElement(By.xpath("//input[contains(@class,'input_phone')]")).sendKeys(phone);
	  select = new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']")));
	  select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
	  driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys(note);
	  driver.findElement(By.xpath("//input[contains(@class,'submit mes')]")).click();
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='floater']//label[contains(@class,'logged_in_name')]")).getText(), name);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='floater']//label[contains(@class,'logged_in_phone')]")).getText(), phone);
	  Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='message']")).getText(), note);

	  driver.switchTo().defaultContent();
	  driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys(search);
	  sleepInSeconds(3);
	  driver.findElement(By.xpath("//button[@class='search-button']")).click();
	  
	  List <WebElement> allItemsSearch = driver.findElements(By.xpath("//ul[@class='k-box-card-list']//h4"));
	  for (WebElement tempElement : allItemsSearch) {
		Assert.assertTrue(tempElement.getText().contains(search));
	}
	  
  }
 
  public void TC2_Windows_Tab_1() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  String parentID = driver.getWindowHandle();
	  driver.findElement(By.xpath("//div[@class='container']/a[text()='GOOGLE']")).click();
	  sleepInSeconds(3);
	  switchWindows_Tab_ID_AtoB(parentID);
	  sleepInSeconds(2);
	  Assert.assertEquals(driver.getTitle(), "Google");
	  driver.switchTo().window(parentID);
	  driver.findElement(By.xpath("//div[@class='container']/a[text()='FACEBOOK']")).click();
	  switchWindows_Tab_byTiTle("Facebook - Đăng nhập hoặc đăng ký");
	  sleepInSeconds(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Facebook giúp bạn kết nối và chia sẻ với mọi người trong cuộc sống của bạn.']")).isDisplayed());
	  driver.switchTo().window(parentID);
	  
  }
  @Test
  public void TC3_Windows_Tab_2() {
	  driver.get("http://live.demoguru99.com/index.php/");
	  driver.findElement(By.xpath("//a[text()='Mobile']")).click();
	  driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	  Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),"The product Sony Xperia has been added to comparison list.");
	  
	  driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	  Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),"The product Samsung Galaxy has been added to comparison list.");
	  
	  driver.findElement(By.xpath("//span[text()='Compare']")).click();
	  String parentID = driver.getWindowHandle();
	  switchWindows_Tab_ID_AtoB(parentID);
	  sleepInSeconds(3);
	  Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
	  closedWindowsWithoutParent(parentID);
	  
	  driver.findElement(By.xpath("//a[text()='Clear All']")).click();
	  driver.switchTo().alert().accept();
	  Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),"The comparison list was cleared.");
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  public void sleepInSeconds(long timeInSeconds) {
	  try {
		Thread.sleep(timeInSeconds*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void clickByJavaScript(WebElement element) {
	  jvscrip.executeScript("arguments[0].click();", element);
  }
  
  public void switchWindows_Tab_ID_AtoB(String currentID) {
	  Set<String> allID = driver.getWindowHandles();
	  for (String tempString : allID) {
		if (!tempString.equals(currentID)) {
			driver.switchTo().window(tempString);
			break;
		}
	}
  }
  
  public void switchWindows_Tab_byTiTle(String expectedTitle) {
	  Set<String> allID = driver.getWindowHandles();
	  for (String tempString : allID) {
		  if(driver.switchTo().window(tempString).getTitle().equals(expectedTitle))
			  break;
	}
  }
  
  public void closedWindowsWithoutParent(String parentID) {
	  Set<String> allID = driver.getWindowHandles();
	  for (String tempIP : allID) {
		if (!tempIP.equals(parentID)) {
			driver.switchTo().window(tempIP);
			driver.close();
			driver.switchTo().window(parentID);
			if (allID.size()==2) {
				break;
			}
		}
	}
  }
  
}
