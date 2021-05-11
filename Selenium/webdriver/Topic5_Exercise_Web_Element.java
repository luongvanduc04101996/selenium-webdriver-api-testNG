package webdriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic5_Exercise_Web_Element {

	WebDriver driver;
	By footerMyAccount = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By email = By.id("email");
	By pass = By.id("pass");
	By name = By.name("name");
	By send = By.name("send");
	
	String firstName = "Duc";
	String midName = "Van";
	String lastName = "Luong";
	String contactInfo = "";
	String emailInfo = "";
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Random ran = new Random();
		emailInfo = "vanduckma" + ran.nextInt(99999) + "@gmail.com";
		driver.get("http://live.demoguru99.com/index.php/");
	}
//	public String generateEmail() {
//		Random ran = new Random();
//		return "vanduckma" + ran.nextInt(99999) + "@gmail.com";
//	}
	@Test
	public void TC1_Login_With_Empty_User_And_PassWord() {
		driver.findElement(footerMyAccount).click();
		driver.findElement(email).sendKeys("");
		driver.findElement(pass).sendKeys("");
		driver.findElement(send).click();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	@Test
	public void TC2_Login_With_Invalid_User() {
		driver.findElement(footerMyAccount).click();
		driver.findElement(email).sendKeys("ahsfiuashif@odfijsdiof.12312");
		driver.findElement(pass).sendKeys("1234567");
		driver.findElement(send).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	@Test
	public void TC3_Login_With_Invalid_PassWord() {
		driver.findElement(footerMyAccount).click();
		driver.findElement(email).sendKeys("ahsfiuashif@odfijsdiof.com");
		driver.findElement(pass).sendKeys("123");
		driver.findElement(send).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC4_Login_With_Incorrect_User_And_PassWord() {
		driver.findElement(footerMyAccount).click();
		driver.findElement(email).sendKeys("ahsfiuashif@odfijsdiof.com");
		driver.findElement(pass).sendKeys("1245343");
		driver.findElement(send).click();
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(), "Invalid login or password.");
	}
	@Test
	public void TC5_Create_Account() {
		driver.findElement(footerMyAccount).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("middlename")).sendKeys(midName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailInfo);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']//strong")).getText(), "Hello, " + firstName + " " + midName + " " + lastName + "!");
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']/span[@class='label']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		driver.findElement(By.xpath("//div[@class='page-title']//img")).isDisplayed();
		
	}
	@Test
	public void TC6_Login_With_Correct_User_And_PassWord() {
		driver.findElement(footerMyAccount).click();
		driver.findElement(email).sendKeys(emailInfo);
		driver.findElement(pass).sendKeys("123456");
		driver.findElement(send).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='dashboard']//h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']//strong")).getText(), "Hello, " + firstName + " " + midName + " " + lastName + "!");
		contactInfo = driver.findElement(By.xpath("//div[@class='col-1']//p")).getText();
		Assert.assertTrue(contactInfo.contains(firstName));
		Assert.assertTrue(contactInfo.contains(midName));
		Assert.assertTrue(contactInfo.contains(lastName));
		Assert.assertTrue(contactInfo.contains(emailInfo));
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
