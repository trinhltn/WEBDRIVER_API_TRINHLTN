package selenium;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_09_HandleWindowsTabs {
  WebDriver driver;
  JavascriptExecutor jsExecutor;
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  /*System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();*/
	  jsExecutor = (JavascriptExecutor) driver;
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
  }
  
  @Test
  public void TC_01() {
	  //parent Window
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  String parentID = driver.getWindowHandle(); 
	  System.out.println("parent ID: "+parentID);
	  
	  driver.findElement(By.xpath("//a[text()='Click Here']")).click();
	  switchToChildWindowByID(parentID);
	  
	  //Kiểm tra title của window mới = Google
	  String ggTitle = driver.getTitle();
	  Assert.assertEquals(ggTitle, "Google");
	  
	  //Close window mới and Switch về parent window
	  Assert.assertTrue(closeAllWithoutParentWindows(parentID));
	  
	  //Kiểm tra đã quay về parent window thành công (title/ url)
	  Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
  }
  
  @Test
  public void TC_02_HdfcBank() {
	  driver.get("https://www.hdfcbank.com/");
	  //get parentID hdfcbank
	  String parentID = driver.getWindowHandle();
	  
	  //handle close popup
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  int notificationIframeSize = notificationIframe.size();
	  System.out.println("Size of notification iframe: " + notificationIframeSize);
	  
	  if(notificationIframeSize > 0) {
		//switch to notify iframe
		  driver.switchTo().frame(notificationIframe.get(0));
		  
		  //check display img
		  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
		  //close img
		  jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='div-close']")));
		  System.out.println("Close popup");
	  }
	  
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  //click vao Agri
	  driver.findElement(By.xpath("//a[text()='Agri']")).click();
	  
	  //switch qua page Agril
	  switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
	  
	  //Click Account Details
	  driver.findElement(By.xpath("//p[text()='Account Details']")).click();
	  
	  ///switch qua page Account Details
	  switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
	  
	  //Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
	  driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
	  driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
	  switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	  
	  //Click CSR link on Privacy Policy page
	  driver.findElement(By.xpath("//a[text()='CSR']")).click();
	  
	  //Close tất cả windows/ tabs khác - chỉ giữ lại parent window (http://www.hdfcbank.com/)
	  closeAllWithoutParentWindows(parentID);
	  
	  //Back về parent windows
	  Assert.assertEquals(driver.getTitle(), "HDFC Bank: Personal Banking Services");
  }
  
  @Test
  public void TC_03_Buy_Mobile() throws Exception {
	  driver.get("http://live.guru99.com/index.php/");
	  String parentID = driver.getWindowHandle();
	  
	  //02: Click vào Mobile tab
	  driver.findElement(By.xpath("//a[text()='Mobile']")).click();
	  Thread.sleep(2000);
	  
	  //03: Add sản phẩm Sony Xperia vào để Compare (Add to Compare)
	  driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	  Thread.sleep(2000);
	  //verify add sony xperia successfully
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
	  
	  //04: Add sản phẩm Samsung Galaxy vào để Compare (Add to Compare)
	  driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	  Thread.sleep(2000);
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
	  
	  //05: Click to Compare button
	  driver.findElement(By.xpath("//span[text()='Compare']")).click();
	  Thread.sleep(2000);
	  
	  //06: Switch qa cửa sổ mới (chứa 2 sản phẩm đã được Add vào để Compare)
	  switchToWindowByTitle("Products Comparison List - Magento Commerce");
	  //check co dung 2sp
	  List <WebElement> ListSP = driver.findElements(By.xpath("//a[@class='product-image']"));
	  Assert.assertEquals(ListSP.size(), 2);
	  
	  //07: Verify title của cửa sổ bằng: Products Comparison List - Magento Commerce
	  Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
	  
	  //08: Close tab và chuyển về Parent Window
	  closeAllWithoutParentWindows(parentID);
	  driver.navigate().back();
	  Assert.assertEquals(driver.getTitle(), "Home page");
  }
  
  @AfterTest
  public void afterTest() {
	  
  }

  //switch if only have 2 windows/ tabs
  public void switchToChildWindowByID(String parentID) {
	  //get all ID của các cửa sổ đang có
      Set<String> allWindows = driver.getWindowHandles();
      for(String runWindow : allWindows) {
    	  System.out.println("runWindow: "+runWindow);
    	  //nếu ID của cửa sổ nào khác với parentID thì switch qua
          if (!runWindow.equals(parentID)) {
              driver.switchTo().window(runWindow);
              break;
          }
      }
  }
  
  //switch have >= 2 windows/ tabs
  public void switchToWindowByTitle(String expectedTitle) {
      Set<String> allWindows = driver.getWindowHandles();
      for(String runWindows : allWindows) {
          driver.switchTo().window(runWindows);
          String currentWin = driver.getTitle();
          if (currentWin.equals(expectedTitle)) {
              break;
          }
      }
  }
  
  //Close without parent window/ tab
  public boolean closeAllWithoutParentWindows(String parentID) {
	  //get all ID của các cửa sổ đang có
      Set<String> allWindows = driver.getWindowHandles();
      for (String runWindows : allWindows) {
          if (!runWindows.equals(parentID)) {
	          driver.switchTo().window(runWindows);
	          driver.close();
          }
      }
      driver.switchTo().window(parentID);
      if (driver.getWindowHandles().size() == 1)
         return true;
      else
         return false;
  }
}
