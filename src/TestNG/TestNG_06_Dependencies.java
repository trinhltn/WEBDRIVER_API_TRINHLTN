package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestNG_06_Dependencies {
	@Test
	  public void TC_01() {
		  System.out.println("run TC 01");
		  
		  //Skip testcase 01 -> k chạy các TC dependenci nó
		  Assert.assertTrue(false);
	  }
	  
	  @Test(dependsOnMethods= "TC_01")
	  public void TC_02() {
		  System.out.println("run TC 02");
	  }
	  
	  @Test(dependsOnMethods= "TC_02")
	  public void TC_03() {
		  System.out.println("run TC 02");
	  }
	  
	  @Test(dependsOnMethods= "TC_03")
	  public void TC_04() {
		  System.out.println("run TC 02");
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
