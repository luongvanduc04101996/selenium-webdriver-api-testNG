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




public class Topic03_Exercise_Xpath_CSS {

	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/");
	}
	public String generateEmail() {
		Random ran = new Random();
		return "vanduckma" + ran.nextInt(99999) + "@gmail.com";
	}
	@Test
	public void TC1_Login_With_Empty_User_And_PassWord() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	@Test
	public void TC2_Login_With_Invalid_User() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("ahsfiuashif@odfijsdiof.12312");
		driver.findElement(By.id("pass")).sendKeys("1234567");
		driver.findElement(By.name("send")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	@Test
	public void TC3_Login_With_Invalid_PassWord() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("ahsfiuashif@odfijsdiof.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.name("send")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC4_Login_With_Incorrect_User_And_PassWord() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("ahsfiuashif@odfijsdiof.com");
		driver.findElement(By.id("pass")).sendKeys("1245343");
		driver.findElement(By.name("send")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(), "Invalid login or password.");
	}
	@Test
	public void TC5_Create_Account() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys("Duc");
		driver.findElement(By.id("middlename")).sendKeys("Van");
		driver.findElement(By.id("lastname")).sendKeys("Luong");
		driver.findElement(By.id("email_address")).sendKeys(generateEmail());
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		driver.findElement(By.id("form-validate")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']//strong")).getText(), "Hello, Duc Van Luong!");
	}
	@Test
	public void TC6_Login_With_Incorrect_User_And_PassWord() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("ahsfiuashif@odfijsdiof.com");
		driver.findElement(By.id("pass")).sendKeys("1245343");
		driver.findElement(By.name("send")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(), "Invalid login or password.");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
