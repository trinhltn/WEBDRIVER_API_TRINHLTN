package selenium;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_09_HandleWindowsTabs {
  WebDriver driver;
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
  }
  
  //@Test
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
