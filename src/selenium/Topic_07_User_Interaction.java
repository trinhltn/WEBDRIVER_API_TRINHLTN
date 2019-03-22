package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_07_User_Interaction {
	WebDriver driver;
	Actions action;
	
  @BeforeTest
  public void beforeTest() {
	  //System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	  driver = new FirefoxDriver();
	  //driver = new ChromeDriver();
	  action = new Actions(driver);
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  //@Test
  public void TC_01_HoverMouse() {
	  driver.get("https://www.myntra.com/");
	  WebElement profile = driver.findElement(By.xpath("//span[@class='desktop-userTitle' and text()='Profile']"));
	  
	  action.moveToElement(profile).perform();
	  
	  WebElement loginBtn = driver.findElement(By.xpath("//a[@data-track='login']"));
	  action.click(loginBtn).perform();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
  }
  
  //@Test
  public void TC_02_ClickAndHold() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  //init list li and click vao phan tu thu 0 -> 3
	  List <WebElement> items = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  action.clickAndHold(items.get(0)).moveToElement(items.get(3)).release().perform();
	  
	  //List phan tu duoc chon
	  List <WebElement> ItemsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	  
	  //verify
	  Assert.assertEquals(ItemsSelected.size(), 4);
  }
  
  //@Test
  public void TC_02_ClickAndHold_Random() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  List <WebElement> items = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  //use: control+ click vao items can chon
	  action.keyDown(Keys.CONTROL).build().perform();
	  items.get(0).click();
	  items.get(2).click();
	  items.get(4).click();
	  items.get(6).click();
	  items.get(8).click();
	  action.keyUp(Keys.CONTROL).build().perform();
	  
	  //verify
	  List <WebElement> ItemsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	  Assert.assertEquals(ItemsSelected.size(), 5);
  }
  
 // @Test
  public void TC_03_DoubleClick() {
	  driver.get("http://www.seleniumlearn.com/double-click");
	  WebElement doubleClickBtn = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
	  
	  action.doubleClick(doubleClickBtn).perform();
	  
	  Alert verifyAccept = driver.switchTo().alert();
	  Assert.assertEquals(verifyAccept.getText(), "The Button was double-clicked.");
	  
	  verifyAccept.accept();
  }
  
  //@Test
  //Testscript này chỉ dùng đc trên chrome
  public void TC_04_RightClick() throws Exception {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  
	  WebElement rightCLickBtn = driver.findElement(By.xpath("//span[text()='right click me']"));
	  action.contextClick(rightCLickBtn).perform();
	  
	  //hover
	  WebElement quitBtn = driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit')]"));
	  action.moveToElement(quitBtn).perform();
	  
	  //check có visible and hover
	  //thứ tự của class xpath trên FF quit-visible-hover, Chrome: quit-hover-visible
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit') and contains(@class,'context-menu-hover') and contains(@class,'context-menu-visible')]")).isDisplayed());
	  
	  quitBtn.click();
	  //alert này chỉ bật cho chrome, firefox k support
	  Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
	  driver.switchTo().alert().accept();
	  
	  //sleep để có time nó verify 
	  Thread.sleep(1500);
	  //check xong thao tác, element k còn hover và visible nữa
	  Assert.assertFalse(quitBtn.isDisplayed());
	  
  }
  
  @Test
  public void TC_05_DragAndDrop() throws Exception {
	  
	  driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
	  
	  WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
	  
	  action.dragAndDrop(smallCircle, bigCircle).perform();
	  Thread.sleep(2000);
	  
	  Assert.assertEquals(bigCircle.getText(), "You did great!");
	  
  }
  
  @AfterTest
  public void afterTest() {
	  
  }

}
