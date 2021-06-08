package webdriver;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic16_Visible_Invisible_Presence_Staleness {
	WebDriverWait explicitWait;
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		explicitWait = new WebDriverWait(driver, 30);
	}

	public void TC1_Visible_Displayed() {
//		Element in DOM and UI
//		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator))	
	}
	
	public void TC2_Invisible_Undisplayed_InDOM() {
//		Element in DOM and not in UI
//		explicitWait.until(ExpectedConditions.invisibilityOfAllElementsLocatedBy(locator))
	}
	
	public void TC3_Visible_Displayed_NOTinDOM() {
//		Element not in DOM and UI
//		explicitWait.until(ExpectedConditions.invisibilityOfAllElementsLocatedBy(locator))
	}
	
	public void TC4_Presence() {
//		Element in DOM
//		explicitWait.until(ExpectedConditions.presenceOfElementLocated(locator)
	}
	
	public void TC4_Staleness() {
//		1. Lưu 1 element đã xuất hiện vào 1 biến
//		2. Chuyển qua trang khác không còn element đó trong DOM và UI nữa
//		explicitWait.until(ExpectedConditions.stalenessOf(element))
	}
	@AfterClass
	public void afterClass() {
	}

}
