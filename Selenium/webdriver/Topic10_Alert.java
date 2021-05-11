package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic10_Alert {
	WebDriver driver;
	String projectFolder = System.getProperty("user.dir");
	Alert alert;
	WebDriverWait explicitWait;
	String inputValue = "ducdeptrai";
	
  @BeforeClass
  public void beforeClass() {
	System.setProperty("webdriver.chrome.driver", projectFolder + "\\BrowserDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
//	driver = new FirefoxDriver();
	driver.manage().window().maximize();
	explicitWait = new WebDriverWait(driver, 30);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  public void TC1_Accept_Alert() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  sleepInSecond(3);
	  alert = driver.switchTo().alert();
	  alert.accept();
	  Assert.assertEquals(driver.findElement(By.xpath("//p[text()='You clicked an alert successfully ']")).getText(), "You clicked an alert successfully");
  }

  public void TC2_Confirm_Alert() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  sleepInSecond(3);
	  alert = driver.switchTo().alert();
	  alert.accept();
	  Assert.assertEquals(driver.findElement(By.xpath("//p[text()='You clicked: Ok']")).getText(), "You clicked: Ok");
	  
	  driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  sleepInSecond(3);
	  alert = driver.switchTo().alert();
	  alert.dismiss();
	  Assert.assertEquals(driver.findElement(By.xpath("//p[text()='You clicked: Cancel']")).getText(), "You clicked: Cancel");
  }

  public void TC3_Prompt_Alert() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  sleepInSecond(3);
	  alert = driver.switchTo().alert();
	  alert.sendKeys(inputValue);
	  Assert.assertEquals(alert.getText(), "I am a JS prompt");
	  sleepInSecond(3);
	  alert.accept();
	  Assert.assertEquals(driver.findElement(By.xpath("//p[text()='You entered: ducdeptrai']")).getText(), "You entered: " + inputValue);
  }
  @Test
  public void TC4_Authentication_Alert() {
	  String userLogin, passwordLogin;
	  userLogin = "admin";
	  passwordLogin = "admin";
	  driver.get(byPassed_Authentication_Link("http://the-internet.herokuapp.com/basic_auth", userLogin, passwordLogin));
	  sleepInSecond(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public String byPassed_Authentication_Link(String fullurl, String user, String password) {
	  String temp[] = fullurl.split("//");
	  return temp[0]+"//"+user+":"+password+"@"+temp[1];
  }
  
  public void sleepInSecond(long timeInSeconds) {
	  try {
		Thread.sleep(timeInSeconds*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

}
