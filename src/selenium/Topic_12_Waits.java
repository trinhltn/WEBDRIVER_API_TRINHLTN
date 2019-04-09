package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_12_Waits {
	WebDriver driver;
	By startBtn = By.xpath("//div[@id=\"start\"]//button");
	By imgLoading = By.xpath("//div[@id='loading']//img");
	By helloText = By.xpath("//div[@id='finish']//h4[text()='Hello World!']");
	
	WebDriverWait explicitWait;

  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
  }
  
  //@Test
  public void TC_01_ImplicitWait_fail_2s() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
	  //02 - Define an implicit wait (If you set 2 seconds, test will fail)
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  
	  //03 - Click the Start button
	  driver.findElement(startBtn).click();
	  
	  //04 - Wait result text will appear  
	  //05 - Check result text is "Hello World!"
	  Assert.assertTrue(driver.findElement(helloText).isDisplayed());  
	  
  }
  
  //@Test
  public void TC_01_ImplicitWait_pass_5s() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
	  //02 - Define an implicit wait (If you set 5 seconds, test will pass)
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  //03 - Click the Start button
	  driver.findElement(startBtn).click();
	  
	  //04 - Wait result text will appear  
	  //05 - Check result text is "Hello World!"
	  Assert.assertTrue(driver.findElement(helloText).isDisplayed());  
	  
  }
  
  //@Test
  public void TC_02_ExplicitWait_fail_2s() {
	  
	  driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	  //Check cho loading icon invisible trước khi Helloworld text được hiển thị
	  //Nếu Implicit wait set 5s (pass ở testcase trước) nhưng Explicit set 2s thì testcase vẫn failed
	  explicitWait = new WebDriverWait(driver, 2);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2"); 
	  
	  driver.findElement(startBtn).click();
	  
	  //Step 03 - Wait Loading invisible
	  explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(imgLoading));
	  
	  Assert.assertTrue(driver.findElement(helloText).isDisplayed()); 
	  
  }
  
 //@Test
  public void TC_02_ExplicitWait_pass_5s() {
	  
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  //Check cho loading icon invisible trước khi Helloworld text được hiển thị
	  //Nếu Implicit wait set 5s (pass ở testcase trước) nhưng Explicit set 2s thì testcase vẫn failed
	  explicitWait = new WebDriverWait(driver, 2);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2"); 
	  
	  driver.findElement(startBtn).click();
	  
	  //Step 03 - Wait Loading invisible
	  explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(imgLoading));
	  
	  Assert.assertTrue(driver.findElement(helloText).isDisplayed()); 
	  
  }
  
  //@Test
  public void TC_03_ExplicitWait_fail_2s() {
	  
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  
	  explicitWait = new WebDriverWait(driver, 2);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2"); 
	  
	  driver.findElement(startBtn).click();
	  
	  //03 - Wait Hello World visible
	  explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(helloText));
	  
	  Assert.assertTrue(driver.findElement(helloText).isDisplayed()); 
	  
  }
  
  //@Test
  public void TC_03_ExplicitWait_pass_5s() {
	  
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  explicitWait = new WebDriverWait(driver, 2);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2"); 
	  
	  driver.findElement(startBtn).click();
	  
	  //03 - Wait Hello World visible
	  explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(helloText));
	  
	  Assert.assertTrue(driver.findElement(helloText).isDisplayed()); 
	  
  }
  
  public Date getDateTimeSecond() {
      Date date = new Date();
      date = new Timestamp(date.getTime());
      return date;
  }
  
  @Test
  public void TC_04_ExplicitWait_pass() {
	  
	  driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);  
	  explicitWait = new WebDriverWait(driver, 4);
	  
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2"); 
	  
	  //02 - Check Hello World text invisible + k co trong DOM
	  System.out.println("Start invisible Text: "+getDateTimeSecond());
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(helloText));
	  System.out.println("End invisible Text: "+getDateTimeSecond());
	  
	  //03 - Check Loading invisible + k co trong DOM
	  System.out.println("Start invisible loading: "+getDateTimeSecond());
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(imgLoading));
	  System.out.println("Start invisible loading: "+getDateTimeSecond());
	  
	  driver.findElement(startBtn).click();
	  
	  //05 - Check Loading invisible + co trong DOM
	  System.out.println("Start invisible loading: "+getDateTimeSecond());
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(imgLoading));
	  System.out.println("Start invisible loading: "+getDateTimeSecond());
	  
	  //06- Check Start button inivisible + co trong DOM
	  System.out.println("Start invisible startBtn: "+getDateTimeSecond());
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(startBtn));
	  System.out.println("Start invisible startBtn: "+getDateTimeSecond());
	  
  }
  
  @AfterTest
  public void afterTest() {
  }

}
