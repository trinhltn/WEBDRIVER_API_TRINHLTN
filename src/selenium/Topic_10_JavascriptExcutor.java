package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_10_JavascriptExcutor {
  WebDriver driver;
	
  @BeforeTest
  public void beforeTest() {
	  //driver = new FirefoxDriver();
	  System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TC_01() {
	  //01 - Truy cập vào trang: http://live.guru99.com/
	  navigateToUrlByJS("http://live.guru99.com/");
	  
	  //02 - Sử dụng JE để get domain của page
	  String domainName = executeForBrowser("return document.domain");
	  //Verify domain =  live.guru99.com
	  Assert.assertEquals(domainName, "live.guru99.com");
	  
	  //03 - Sử dụng JE để get URL của page
	  String url = executeForBrowser("return document.URL");
	  Assert.assertEquals(url, "http://live.guru99.com/");
	  
	  //04 - Open MOBILE page (Sử dụng JE)
	  clickToElementByJS(driver.findElement(By.xpath("//a[text()='Mobile']")));
	  highlightElement(driver.findElement(By.xpath("//a[text()='Mobile']")));
	  
	  //05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
	  clickToElementByJS(driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button")));
	  
	  //06 - Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart
	  //(Sử dụng JE - Get innertext of the entire webpage )
	  String innerText = executeForBrowser("return document.documentElement.innerText;");
	  Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
	  
	  //07 - Open PRIVACY POLICY page (Sử dụng JE)
	  //Verify title của page = Privacy Policy (Sử dụng JE)
	  clickToElementByJS(driver.findElement(By.xpath("//a[text()='Privacy Policy']")));
	  String titlePage = executeForBrowser("return document.title");
	  Assert.assertEquals(titlePage, "Privacy Policy");
	  
	  //08 - Srcoll xuống cuối page
	  scrollToBottomPage();
	  
	  //09 - Verify dữ liệu có hiển thị với chỉ 1 xpath: 
	  WebElement verifyText = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
	  Assert.assertTrue(verifyText.isDisplayed());
	  
	  //10 - Navigate tới domain: http://demo.guru99.com/v4/  (Sử dụng JE)
	  //Verify domain sau khi navigate = demo.guru99.com
	  navigateToUrlByJS("http://demo.guru99.com/v4/");
	  String domainNavigate = executeForBrowser("return document.domain");
	  Assert.assertEquals(domainNavigate, "demo.guru99.com");
	  
  }
  
  @Test
  //Run on Chrome
  public void TC_02_Remove_Attribute() {
	  //01 - Access vào trang: http://demo.guru99.com/v4
	  navigateToUrlByJS("http://demo.guru99.com/v4");
	  
	  //02 - Đăng nhập
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr187630");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("rUzUdUv");
	  clickToElementByJS(driver.findElement(By.xpath("//input[@name='btnLogin']")));
	  String textLoginSuccess = executeForBrowser("return document.documentElement.innerText;");
	  Assert.assertTrue(textLoginSuccess.contains("Welcome To Manager's Page of Guru99 Bank"));
	
	  //03 - Chọn menu New Customer
	  clickToElementByJS(driver.findElement(By.xpath("//a[text()='New Customer']")));
	  String textAddNewCustomer = executeForBrowser("return document.documentElement.innerText;");
	  Assert.assertTrue(textAddNewCustomer.contains("Add New Customer"));
	  
	  //04 - Nhập toàn bộ dữ liệu đúng > Click Submit
	  driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Trinh Le");
	  clickToElementByJS(driver.findElement(By.xpath("//input[@value='f']")));
	  //Remove attribute type=date của field Date of Birth
	  removeAttributeInDOM(driver.findElement(By.xpath("//input[@name='dob']")), "type");
	  driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("1999-01-01");
	  
	  driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("123 Nguyen Van Linh");
	  driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Da Nang");
	  driver.findElement(By.xpath("//input[@name='state']")).sendKeys("Hai Chau");
	  driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123123");
	  driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0909123123");
	  String email = "trinh"+randomNumber()+"@gmail.com";
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123123");
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();	  
	  
	  //05 - Verify tạo mới Customer thành công
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
	  /*Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), "Trinh Le");
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), "female");
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), "1999-01-01");
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), "123 Nguyen Van Linh");
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), "Da Nang");
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), "Hai Chau");
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), "123123");
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), "0909123123");*/
  }
  
  @Test
  public void TC_03_create_An_Account() throws Exception {
	  navigateToUrlByJS("http://live.guru99.com/");
	  
	  //02 - Click vào link "My Account" để tới trang đăng nhập (Sử dụng JE)
	  clickToElementByJS(driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")));
	  
	  //03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản (Sử dụng JE)
	  clickToElementByJS(driver.findElement(By.xpath("//a[@title='Create an Account']")));
	  
	  //04 - Nhập thông tin hợp lệ vào tất cả các fields (Sử dụng JE)
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='firstname']")), "Trinh");
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='middlename']")), "Thi");
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='lastname']")), "Le");
	  String email = "trinh"+randomNumber()+"@gmail.com";
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='email_address']")), email);
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='password']")), "123123");
	  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='confirmation']")), "123123");
	  
	  //05 - Click REGISTER button (Sử dụng JE)
	  clickToElementByJS(driver.findElement(By.xpath("//button[@title='Register']")));
	  
	  //06 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store. (Sử dụng JE)
	  String msgRegisterSuccess = executeForBrowser("return document.documentElement.innerText;");
	  Assert.assertTrue(msgRegisterSuccess.contains("Thank you for registering with Main Website Store."));
	  
	  //06 - Logout khỏi hệ thống (Sử dụng JE)
	  clickToElementByJS(driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")));
	  clickToElementByJS(driver.findElement(By.xpath("//a[@title='Log Out']")));
	  Thread.sleep(3000);
	  
	  //07 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công (Sử dụng JE)
	  String titleHomePage = executeForBrowser("return document.title");
	  Assert.assertEquals(driver.getTitle(), titleHomePage);
	  
  }
  
  @AfterTest
  public void afterTest() {
	  
  }

  public void highlightElement(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].style.border='6px groove red'", element);
      try {
		Thread.sleep(1000);
      } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
  }

  public String executeForBrowser(String javaSript) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return (String) js.executeScript(javaSript);
  }

  public Object clickToElementByJS(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("arguments[0].click();", element);
  }

  public Object sendkeyToElementByJS(WebElement element, String value) {
     JavascriptExecutor js = (JavascriptExecutor) driver;
     return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
  }

  public void removeAttributeInDOM(WebElement element, String attribute) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
      try {
		Thread.sleep(3000);
	  } 
      catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
  }

  public Object scrollToBottomPage() {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
  }

  public Object navigateToUrlByJS(String url) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      return js.executeScript("window.location = '" + url + "'");
  }
  
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
  
}
