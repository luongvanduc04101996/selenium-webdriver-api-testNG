package webdriver;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic8_Custom_Dropdown_List {
	WebDriver driver;
	WebDriverWait explicitWait;
	
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  explicitWait = new WebDriverWait(driver, 30);
	  
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  @Test
  public void TC1_JQuery() {
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	  selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "4");
	  sleepInSecond(3);
	  
	  selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
	  sleepInSecond(3);
	  
	  selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
	  sleepInSecond(3);
  }
  public void sleepInSecond(long timeInSecond) {
	  try {
		Thread.sleep(timeInSecond*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {
	//  1 - Click vào 1 element (parenXpath) để cho nó xổ hết tất cả các item ra
	  driver.findElement(By.xpath(parentXpath)).click();
	//  2 - Chờ all item được load
	  explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	//  3 - Lưu nó vào 1 List chứa các element
	  List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
	//  4 - Lấy ra text của từng elemet
	  for (WebElement item : allItems) {
		String actualValueItem = item.getText();
	//  5 - Kiểm tra nó có bằng với text cần tìm hay ko
		if (actualValueItem.equals(expectedValueItem)) {
		//  6 - Nếu như có thì click vào - thoát khỏi vòng lập
			item.click();
			break;
		}
	//  Nếu ko có thì tiếp tục duyệt cho tới khi hết all item
	}
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
