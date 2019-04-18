package TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_03_DataProvider {
	WebDriver driver;
	
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  driver.get("http://live.guru99.com/");
  }
	
  @Test(dataProvider="UserPassword")
  public void TC_01_Login_Using_DataProvider(String user, String password) {
	  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[text()='Log In']")).click();
	  
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(user);
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
	  
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	  
	  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for ')]")).isDisplayed());
  }

  @DataProvider(name= "UserPassword")
  public Object[][] UserPassData() {
    return new Object[][] {
    	{"auto_test_05@gmail.com", "123123"},
    	{"auto_test_06@gmail.com", "123123"},
    	{"auto_test_07@gmail.com", "123123"}
    };
  }

  @AfterTest
  public void afterTest() {
  }

}
