package webdriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Topic09_Button_RadioButton_CheckBox_Alert {
	String projectFolder = System.getProperty("user.dir");
	WebDriver driver;
	By userBy = By.id("login_username");
	By passwordBy = By.id("login_password");
	By buttonLogin = By.className("fhs-btn-login");
	By loginTab = By.xpath("//a[text()='Đăng nhập']");

	JavascriptExecutor jvscrip;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectFolder + "\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		jvscrip = (JavascriptExecutor) driver;
	}

	public void TC1_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(loginTab).click();
		Assert.assertFalse(isElementEnabled(buttonLogin));
		driver.findElement(userBy).sendKeys("lvd@gmail.com");
		driver.findElement(passwordBy).sendKeys("123456");
		sleepInSeconds(3);
		Assert.assertTrue(isElementEnabled(buttonLogin));
		driver.navigate().refresh();
		driver.findElement(loginTab).click();
		removeDisableAttribute(buttonLogin);
		driver.findElement(buttonLogin).click();
		sleepInSeconds(3);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div")).getText(),"Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_password']/parent::div/following-sibling::div")).getText(),"Thông tin này không thể để trống");
	}

	public void TC2_Checkbox_Radio() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		checkToCheckBoxOrRadio(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"))));
		sleepInSeconds(2);
		uncheckToCheckBox(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		Assert.assertFalse(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"))));
		sleepInSeconds(2);
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		checkToCheckBoxOrRadio(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"))));
		sleepInSeconds(2);
		uncheckToCheckBox(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"))));
		sleepInSeconds(2);
	}

	@Test
	public void TC3_CustomCheckboxOrRadio() {
		driver.get("https://material.angular.io/components/radio/examples");
		clickByJavaScript(driver.findElement(By.xpath("//input[@value='Summer']")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//input[@value='Summer']"))));
		sleepInSeconds(2);
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		clickByJavaScript(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")));
		sleepInSeconds(2);
		clickByJavaScript(driver.findElement(By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input")));
		sleepInSeconds(2);
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input"))));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input"))));
		
		clickByJavaScript(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")));
		sleepInSeconds(2);
		clickByJavaScript(driver.findElement(By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input")));
		sleepInSeconds(2);
		Assert.assertFalse(isElementSelected(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input"))));
		Assert.assertFalse(isElementSelected(driver.findElement(By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input"))));
		
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//span[text()='After']/preceding-sibling::span/input"))));
		Assert.assertFalse(isElementSelected(driver.findElement(By.xpath("//span[text()='Before']/preceding-sibling::span/input"))));
		//checkToCheckBoxOrRadio(driver.findElement(By.xpath("//span[text()='Before']/preceding-sibling::span/input")));
		clickByJavaScript(driver.findElement(By.xpath("//span[text()='Before']/preceding-sibling::span/input")));
		sleepInSeconds(3);
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//span[text()='Before']/preceding-sibling::span/input"))));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void checkToCheckBoxOrRadio(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckBox(WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementSelected(WebElement element) {
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public void sleepInSeconds(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			return true;
		} else {
			return false;
		}

	}
	
	public void removeDisableAttribute(By by) {
		WebElement element = driver.findElement(by);
		jvscrip.executeScript("arguments[0].removeAttribute('disabled')", element);
	}
	
	public void clickByJavaScript(WebElement element) {
		jvscrip.executeScript("arguments[0].click();", element);
	}

}