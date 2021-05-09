package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic8_Custom_Dropdown_List {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectFolder = System.getProperty("user.dir");
	JavascriptExecutor jvScripExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		jvScripExecutor = (JavascriptExecutor) driver;
	}
	
	@Test
	public void TC1_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "4");
		sleepInSecond(3);

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		sleepInSecond(3);

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
		sleepInSecond(2);
	}
	
	@Test
	public void TC2_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']/li", "Badminton");
		sleepInSecond(2);
		Assert.assertEquals(getSelectedValueAngular(), "Badminton");

		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']/li", "Basketball");
		sleepInSecond(2);
	}
	
	@Test
	public void TC3_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@class='visible menu transition']//span",
				"Matt");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Matt']")).isDisplayed());
	}
	
	@Test
	public void TC4_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//span[@class='caret']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
	}
	
	@Test
	public void TC5_Editdropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectItemInEditDropdown("//input[@class='search']","//div[@role='listbox']//span" , "Andorra");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Andorra");
	}

	public String getSelectedValueAngular() {
		return (String) jvScripExecutor
				.executeScript("return document.querySelector(\"select[name='games'] option\").text");
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {
		// 1 - Click vào 1 element (parenXpath) để cho nó xổ hết tất cả các item ra
		driver.findElement(By.xpath(parentXpath)).click();
		// 2 - Chờ all item được load
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		// 3 - Lưu nó vào 1 List chứa các element
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		// 4 - Lấy ra text của từng elemet
		for (WebElement item : allItems) {
			String actualValueItem = item.getText();
			// 5 - Kiểm tra nó có bằng với text cần tìm hay ko
			if (actualValueItem.equals(expectedValueItem)) {
				// 6 - Nếu như có thì click vào - thoát khỏi vòng lập
				item.click();
				break;
			}
			// Nếu ko có thì tiếp tục duyệt cho tới khi hết all item
		}
	}
	
	public void selectItemInEditDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {
		// 1 - Input dữ liệu
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedValueItem);;
		// 2 - Chờ all item được load
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		// 3 - Lưu nó vào 1 List chứa các element
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		// 4 - Lấy ra text của từng elemet
		for (WebElement item : allItems) {
			String actualValueItem = item.getText();
			// 5 - Kiểm tra nó có bằng với text cần tìm hay ko
			if (actualValueItem.equals(expectedValueItem)) {
				// 6 - Nếu như có thì click vào - thoát khỏi vòng lập
				item.click();
				break;
			}
			// Nếu ko có thì tiếp tục duyệt cho tới khi hết all item
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
