package webdriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic07_Handle_Dropdownlist_Default {
	WebDriver driver;
	Select select;
	String firstName, lastName, dayBirth, monthBirth, yearBirth, email, company;
	By firstNameBy, lastNameBy, dayBirthBy, monthBirthBy, yearBirthBy, emailBy, companyBy, genderBy;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		email = randomEmai();
		firstName = "Duc";
		lastName = "Luong";
		dayBirth = "4";
		monthBirth = "October";
		yearBirth = "1996";
		company = "WinABC";

		genderBy = By.id("gender-male");
		firstNameBy = By.id("FirstName");
		lastNameBy = By.id("LastName");
		dayBirthBy = By.name("DateOfBirthDay");
		monthBirthBy = By.name("DateOfBirthMonth");
		yearBirthBy = By.name("DateOfBirthYear");
		emailBy = By.id("Email");
		companyBy = By.id("Company");
	}

	@Test
	public void TC1_DropdownList1() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.id("job1")));

		Assert.assertFalse(select.isMultiple());

		select.selectByVisibleText("Mobile Testing");
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");

		select.selectByValue("manual");
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

		select.selectByIndex(9);
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

		Assert.assertEquals(select.getOptions().size(), 10);

		select = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(select.isMultiple());

		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Desktop");
		Thread.sleep(2000);

		List<WebElement> listItemSelected = select.getAllSelectedOptions();
		List<String> listItemCheck = new ArrayList<String>();
		List<String> listItemTest = new ArrayList<String>();
		listItemTest.add("Automation");
		listItemTest.add("Mobile");
		listItemTest.add("Desktop");

		for (WebElement item : listItemSelected) {
			listItemCheck.add(item.getText());
		}
		Assert.assertEquals(listItemCheck, listItemTest);

		select.deselectAll();
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
	}

	@Test
	public void TC2_Register() {
		driver.get("https://demo.nopcommerce.com/register");
		
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		driver.findElement(genderBy).click();
		driver.findElement(firstNameBy).sendKeys(firstName);
		driver.findElement(lastNameBy).sendKeys(lastName);

		select = new Select(driver.findElement(dayBirthBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(dayBirth);
		Assert.assertEquals(select.getOptions().size(), 32);

		select = new Select(driver.findElement(monthBirthBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(monthBirth);
		Assert.assertEquals(select.getOptions().size(), 13);

		select = new Select(driver.findElement(yearBirthBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(yearBirth);
		Assert.assertEquals(select.getOptions().size(), 112);

		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(companyBy).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
	
		driver.findElement(By.id("register-button")).click();
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");

		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		Assert.assertTrue(driver.findElement(genderBy).isSelected());
		Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"), company);
		
		select = new Select(driver.findElement(dayBirthBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dayBirth);   
		
		select = new Select(driver.findElement(monthBirthBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), monthBirth);  

		select = new Select(driver.findElement(yearBirthBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), yearBirth);  
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	String randomEmai() {
		Random ran = new Random();
		return "luongvanduc" + ran.nextInt(99999) + "@gmail.com";
	}

}
