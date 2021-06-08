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
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic12_Popup {
	WebDriver driver;
	String projectFolder = System.getProperty("user.dir");
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", projectFolder+"\\BrowserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  public void TC1_Fixed_Popup(){
	  driver.get("https://www.zingpoll.com/");
	  driver.findElement(By.xpath("//a[@id='Loginform']")).click();
	  sleepInSeconds(2);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']/div[@class='modal-content']")).isDisplayed());
	  driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']//button[@class='close']")).click();
	  sleepInSeconds(2);
	  Assert.assertFalse(driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']/div[@class='modal-content']")).isDisplayed());
  }

  public void TC2_Fixed_Popup(){
	  driver.get("https://bni.vn/");
	  sleepInSeconds(15);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
	  driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();
  }

  public void TC3_Random_Popup_INDOM(){
	  String key = "Selenium";
	  driver.get("https://blog.testproject.io/");
	  sleepInSeconds(10);
	  WebElement popupTemp = driver.findElement(By.xpath("//div[@class='mailch-wrap']"));
	  if (popupTemp.isDisplayed()) {
		sleepInSeconds(2);
		driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
	}
	  driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys(key);
	  sleepInSeconds(2);
	  driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys(Keys.ENTER);
	 
	  List <WebElement> elementTitleSearch = driver.findElements(By.xpath("//h3[@class='post-title']"));
	  for (WebElement tempElement : elementTitleSearch) {
		Assert.assertTrue(tempElement.getText().contains(key));
	}
  }
  @Test
  public void TC4_Random_Popup_NOTINDOM(){
	  driver.get("https://anime47.com/");
	  driver.findElement(By.xpath("//a[@class='button-register']")).click();
	  sleepInSeconds(5);
	  List <WebElement> temppopupElement = driver.findElements(By.xpath("//a[@class='movie-item m-block' and @title='Piano No Mori - Piano No Mori']"));
	  if (temppopupElement.size()>0 && temppopupElement.get(0).isDisplayed()) {
		driver.findElement(By.xpath("//button[@class='advertisement__button']")).click();
		System.out.println("FOUND ELEMENT");
	}else {
		System.out.println("NOT FOUND ELEMENT");
	}
	  
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

}
