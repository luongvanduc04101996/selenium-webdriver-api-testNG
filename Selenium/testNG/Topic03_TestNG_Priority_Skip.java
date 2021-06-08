package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Topic03_TestNG_Priority_Skip {


	@Test(groups = "user", priority = 5, enabled = true, description = "TC1 Priority = 5")
	public void TC01() {
		System.out.println("TC01: user group");
	}

	@Test(groups = "user", priority = 4, enabled = true, description = "TC2 Priority = 4")
	public void TC02() {
		System.out.println("TC02: user group");
	}

	@Test(groups = "admin", priority = 3, enabled = true, description = "TC3 Priority = 3")
	public void TC03() {
		System.out.println("TC03: admin group");
	}

	@Test(groups = "admin", priority = 2, enabled = false, description = "TC4 Priority = 2")
	public void TC04() {
		System.out.println("TC04: admin group");
	}

	@Test(groups = "admin", priority = 1, enabled = true, description = "TC5 Priority = 1")
	public void TC05() {
		System.out.println("TC05: admin group");
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("BeforeClass");
	}

	@AfterClass(alwaysRun = true) 
	public void afterClass() {
		System.out.println("AfterClass");
	}

}
