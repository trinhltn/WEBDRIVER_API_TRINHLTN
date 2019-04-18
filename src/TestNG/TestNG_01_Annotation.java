package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_01_Annotation {
  @Test
  public void TC_01() {
	  System.out.println("run TC 01");
  }
  
  @Test
  public void TC_02() {
	  System.out.println("run TC 02");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("run beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("run afterMethod");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("run beforeClass");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("run afterClass");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("run beforeTest");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("run afterTest");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("run beforeSuite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("run afterSuite");
  }

}
