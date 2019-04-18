package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_02_Group_Priority {
	
  @Test(groups = "add", priority=5, enabled=false)
  public void TC_01() {
	  System.out.println("run TC01");
  }
  
  @Test(groups = "edit", priority=4)
  public void TC_02() {
	  System.out.println("run TC02");
  }
  
  @Test(groups = "add", priority=3)
  public void TC_03() {
	  System.out.println("run TC03");
  }
  
  @Test(groups = "edit", priority=2)
  public void TC_04() {
	  System.out.println("run TC04");
  }
  
  @Test(groups = "delete", priority=1)
  public void TC_05() {
	  System.out.println("run TC05");
  }
  
  @Test(groups = "add", priority=0)
  public void TC_06() {
	  System.out.println("run TC06");
  }
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
