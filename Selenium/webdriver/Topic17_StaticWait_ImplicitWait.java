package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Topic17_StaticWait_ImplicitWait {
 

  @BeforeClass
  public void beforeClass() {
  }

  public void TC1_StaticWait() {
//	  Sleep cứng của Java --> Thread.sleep(3000); time in miliseconds
  }
  
  public void TC2_ImplicitWait() {
//	  To find element, if not find the element it will poll 0.5s per time to find again
  }
  @AfterClass
  public void afterClass() {
  }

}
