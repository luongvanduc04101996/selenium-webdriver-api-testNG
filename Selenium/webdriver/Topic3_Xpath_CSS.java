package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic3_Xpath_CSS {
	
	WebDriver driver;
	@Test
	public void TC_01 () {
		
		// 1. Mở trình duyệt FF
		driver = new FirefoxDriver();
		
		// 2. Mở app (Facebook)
		driver.get("https://www.facebook.com/");
			
		// 3. Nhập vào textbox
		
		//CSS
		driver.findElement(By.cssSelector("#m_login_email")).sendKeys("luongvanduc");
		driver.findElement(By.cssSelector("#m_login_password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input[name='pass']")).clear();
		driver.findElement(By.cssSelector("input[id='m_login_email']")).clear();
		
		//Xpath
		driver.findElement(By.xpath("//input[@id='m_login_email']")).sendKeys("abcdef@gmail.com");
		driver.findElement(By.xpath("//input[@id='m_login_password']")).sendKeys("12312312");
		driver.findElement(By.xpath("//input[@name='pass']")).clear();
		driver.findElement(By.xpath("//a[text()='Quên mật khẩu?']")).click();
		
	}
}
