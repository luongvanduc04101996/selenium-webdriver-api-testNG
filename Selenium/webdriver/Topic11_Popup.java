package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic11_Popup {
	WebDriver driver;
	String projectFolder = System.getProperty("user.dir");
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("Webdriver.chrome.driver", projectFolder+"\\BrowserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Test
  public void TC1_Fixed_Popup(){
	  driver.get("https://www.zingpoll.com/");
	  driver.findElement(By.xpath("//a[@id='Loginform']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']/div[@class='modal-content']")).isDisplayed());
	  driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']//button[@class='close']")).click();
	  Assert.assertFalse(driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']/div[@class='modal-content']")).isDisplayed());
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
