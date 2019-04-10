package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_12_Test_Wait {
	WebDriver driver;
	WebDriverWait waitExplicit;
	
	By startButton = By.xpath("//div[@id=\"start\"]//button");
    By imgLoading = By.xpath("//div[@id='loading']//img");
    By helloText = By.xpath("//div[@id='finish']//h4[text()='Hello World!']");
	
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
  }
  
  public Date getDateTimeSecond() {
      Date date = new Date();
      date = new Timestamp(date.getTime());
      return date;
  }
  
	@Test
    public void TC_04_HelloWorldTextVisibleFailed_01() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("Start time before click = " + getDateTimeSecond());
		driver.findElement(startButton).click();
		System.out.println("Start time after click = " + getDateTimeSecond());
		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
	}
	
	@Test
	public void TC_04_HelloWorldTextVisibleFailed_02() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 4);
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("Start time before click = " + getDateTimeSecond());
		driver.findElement(startButton).click();
		System.out.println("Start time after click = " + getDateTimeSecond());
		
		waitExplicit.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
	}
	
	@Test
	public void TC_04_HelloWorldTextVisibleFailed_03() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 4);
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("Start time before click = " + getDateTimeSecond());
		driver.findElement(startButton).click();
		System.out.println("Start time after click = " + getDateTimeSecond());
		
		waitExplicit.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
	}
	
	@Test
	public void TC_04_HelloWorldTextVisibleFailed_04() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 4);
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("Start time before click = " + getDateTimeSecond());
		driver.findElement(startButton).click();
		System.out.println("Start time after click = " + getDateTimeSecond());
		
		waitExplicit.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
	}

	@Test
	public void TC_04_HelloWorldTextVisibleFailed_05() {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 4);

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("Start time before click = " + getDateTimeSecond());
		driver.findElement(startButton).click();
		System.out.println("Start time after click = " + getDateTimeSecond());
		
		waitExplicit.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
	}
	

  @AfterTest
  public void afterTest() {
  }

}
