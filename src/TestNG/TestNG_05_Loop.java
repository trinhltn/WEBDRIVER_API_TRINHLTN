package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TestNG_05_Loop {
WebDriver driver;
	
	@Parameters("browser")
	 @BeforeTest
	  public void preCondition(String browserName) {
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("headless")){
			System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
			
			ChromeOptions options = new ChromeOptions();
	        options.addArguments("headless");
	        options.addArguments("window-size=1366x768");
	        driver = new ChromeDriver(options);
		}
		
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  driver.get("http://live.guru99.com/");
	  }
		
	  @Test(invocationCount = 3)
	  public void TC_01_Login() {
		  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		  driver.findElement(By.xpath("//a[text()='Log In']")).click();
		  
		  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("auto_test_05@gmail.com");
		  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");
		  
		  driver.findElement(By.xpath("//button[@id='send2']")).click();
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		  
		  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for ')]")).isDisplayed());
	  }

  @AfterTest
  public void afterTest() {
  }

}
