package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic11_User_Interaction {
	WebDriver driver;
	Actions action;
	String projectFolder = System.getProperty("user.dir");
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", projectFolder+"\\BrowserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  action = new Actions(driver);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  

  public void TC1_Hover_To_Element_Tooltip() {
	  driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
	  action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
	  sleepInSeConds(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content' and text()='We ask for your age only for statistical purposes.']")).isDisplayed());
  }

  public void TC2_Hover_To_Element() {
	  driver.get("https://www.myntra.com/");
	  action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
	  sleepInSeConds(3);
	  action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
	  sleepInSeConds(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
  }

  public void TC3_Hover_To_Element() {
	  driver.get("https://hn.telio.vn/");
	  Assert.assertFalse(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='Bia']")).isDisplayed());
	  action.moveToElement(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//span[text()='Đồ uống']"))).perform();
	  sleepInSeConds(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='Bia']")).isDisplayed());
  }

  public void TC4_ClickAndHold_Element() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  List <WebElement> allElements = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  action.clickAndHold(allElements.get(0)).moveToElement(allElements.get(3)).release().perform();
	  sleepInSeConds(3);
	  List <WebElement> elemetsSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
	  Assert.assertEquals(elemetsSelected.size(), 4);
  }

  public void TC5_ClickAndSelect_RandomItems() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  List <WebElement> allElements = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  action.keyDown(Keys.CONTROL).perform();
	  action.click(allElements.get(0)).click(allElements.get(2)).click(allElements.get(5)).click(allElements.get(11)).perform();
	  action.keyUp(Keys.CONTROL).perform();
	  sleepInSeConds(3);
	  List <WebElement> elemetsSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
	  Assert.assertEquals(elemetsSelected.size(), 4);
  }

  public void TC6_Double_Click() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	  sleepInSeConds(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).getText(), "Hello Automation Guys!");
  }
  @Test
  public void TC7_Right_Click_ToElement() {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	  action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit context-menu-hover context-menu-visible')]")).isDisplayed());
	  driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit context-menu-hover context-menu-visible')]")).click();
	  sleepInSeConds(2);
	  driver.switchTo().alert().accept();
	  Assert.assertFalse(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());  
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  public void sleepInSeConds(long timeInSeconds) {
	  try {
		Thread.sleep(timeInSeconds*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
