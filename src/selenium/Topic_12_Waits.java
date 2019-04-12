package selenium;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeTest;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_12_Waits {
	WebDriver driver;
	By startBtn = By.xpath("//div[@id=\"start\"]//button");
	By imgLoading = By.xpath("//div[@id='loading']//img");
	By helloText = By.xpath("//div[@id='finish']//h4[text()='Hello World!']");
	By loaderAjax = By.xpath("//div[@class='raDiv']");
	
	WebDriverWait explicitWait;
	public String date;
	public String month;
	public String year;
	

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
  
  //@Test
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
  
  //@Test
  public void TC_05_ExplicitWait_DateTimePicker() {
	  driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	  
	  //02 - Wait cho "Date Time Picker" được hiển thị
	  By contentWrapper = By.xpath("//div[@class='contentWrapper']");
	  explicitWait = new WebDriverWait(driver, 5);
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(contentWrapper));
	  
	  //03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
	  By noSelectDateText = By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']");
	  System.out.println("Ngày đã chọn: "+driver.findElement(noSelectDateText).getText());
	  
	  //04 - Chọn ngày hiện tại (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
	  CurrentDate();
	  driver.findElement(By.xpath("//a[text()='"+date+"']")).click();
	  
	  //05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility)
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loaderAjax));
	  
	  //06 - Wait cho selected date được visible ((sử dụng: visibility)
	  By selectedDate = By.xpath("//td[contains(@class,'rcSelected')]//a[text()='"+date+"']");
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(selectedDate));
	  
	  //07 - get ngày đã chọn ở tile để assert = Friday, April 12, 2019
	  String textDateSelected = driver.findElement(By.xpath("//td[contains(@class,'rcSelected')]")).getAttribute("title");
	  System.out.println("textDateSelected: "+textDateSelected);
	 
	  //08 - Verify ngày đã chọn bằng = Friday, April 12, 2019
	  WebElement textDisplay = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
	  Assert.assertEquals(textDisplay.getText(), textDateSelected);
	  
  }
  
  public void CurrentDate() {
	  LocalDate localDate = LocalDate.now();
      System.out.println(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));
      String currentDate = DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate);
      
      String[] splits = currentDate.split("/");
      this.year = splits[0];
      //System.out.println(year);
      this.month = splits[1];
      //System.out.println(month);
      this.date = splits[2];
      System.out.println(date);
      
  }
  
  @Test
  public void TC_06_Fluent_Wait() {
	  driver.get("https://daominhdam.github.io/fluent-wait/");
	  
	  //02 - Wait cho đến khi countdown time được visible
	  WebElement countdown =  driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	  explicitWait = new WebDriverWait(driver, 5);
	  explicitWait.until(ExpectedConditions.visibilityOf(countdown));
	  
	  //03 - Sử dụng Fluent wait để: Mỗi 1s kiểm tra countdount= 00 được xuất hiện trên page hay chưa (giây đếm ngược về 00)
	  // Khởi tạo Fluent wait
	  new FluentWait<WebElement>(countdown)
         // Tổng time wait là 15s
         .withTimeout(Duration.ofSeconds(15))
          // Tần số mỗi 1s check 1 lần
          .pollingEvery(Duration.ofSeconds(1))
         // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
          .ignoring(NoSuchElementException.class)
          // Kiểm tra điều kiện
          .until(new Function<WebElement,Boolean>() {
              public Boolean apply(WebElement element) {
                 // Kiểm tra điều kiện countdount = 00
                 boolean flag =  element.getText().endsWith("00");
                 System.out.println("Time = " +  element.getText());
                 // return giá trị cho function apply
                 return flag;
              }
         });
	  
  }
  
  @AfterTest
  public void afterTest() {
  }

}
