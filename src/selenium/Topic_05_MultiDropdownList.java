package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_05_MultiDropdownList {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascriptExecutor;
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  waitExplicit = new WebDriverWait(driver, 30);
	  javascriptExecutor = (JavascriptExecutor) driver;
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  public void selectMultiItemInDropdown(String parentXpath, String allItemXpath, String[] expectedValueItem) throws Exception {
	  //click on dropdown
	  WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
	  javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
	  
	  //wait until display allItems successfully
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	  
	  List <WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
	  System.out.println("Number of elements in dropdown: "+ allItems.size());
	  
	  for(WebElement childElement:allItems) {
		  //January-April-July
		  for(String item:expectedValueItem) {
			  if(childElement.getText().equals(item)) {
				  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				  Thread.sleep(1500);
				  javascriptExecutor.executeScript("arguments[0].click();", childElement);
				  Thread.sleep(1500);
				  
				  List <WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
				  System.out.println("Number of Items is selected: "+itemSelected.size());
				  if(expectedValueItem.length == itemSelected.size()) {
					  break;
				  }
			  }
		  }
	  }
  }
  
  public void selectMultiCountry(String parentXpath, String allItemXpath, String[] expectedValueItem) throws Exception {
	  WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
	  javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
	  
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	  
	  List <WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
	  System.out.println("Number of elements in dropdown: "+ allItems.size());
	  
	  for(WebElement childElement:allItems) {
		  for(String item:expectedValueItem) {
			  if(childElement.getText().equals(item)) {
				  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				  Thread.sleep(1500);
				  javascriptExecutor.executeScript("arguments[0].click();", childElement);
				  Thread.sleep(1500);
				  
				  List <WebElement> itemSelected = driver.findElements(By.xpath("//div[@class='ui fluid multiple search selection dropdown upward active visible']//input[@name='country']/following-sibling::a"));
				  System.out.println("Number of Items is selected: "+itemSelected.size());
				  if(expectedValueItem.length == itemSelected.size()) {
					  break;
				  } 
			  }
		  }
	  }
  }
  
  public boolean checkItemSelected(String[] itemSelectedText) {
	  List <WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
	  int numberItemSelected = itemSelected.size();
	  
	  String allItemSelectedText = driver.findElement(By.xpath("//button[@class='ms-choice']/span")).getText();
	  System.out.println("Text choosen: "+ allItemSelectedText);
			  
	  if(numberItemSelected <= 3 && numberItemSelected > 0 ) {
		  for(String item: itemSelectedText) {
			  if(allItemSelectedText.contains(item)) {
				  break;
			  }
		  }
		  return true;
	  }
	  else {
		  return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='"+numberItemSelected+" of 12 selected']")).isDisplayed();
	  }
  }
  
  public boolean checkItemSelectedInCountry(String[] itemSelectedText) {
	  List <WebElement> itemSelected = driver.findElements(By.xpath("//div[@class='ui fluid multiple search selection dropdown active visible']//input[@name='country']/following-sibling::a"));
	  int numberItemSelected = itemSelected.size();
	  
	  String allItemSelectedText = driver.findElement(By.xpath("//div[@class='ui fluid multiple search selection dropdown active visible']")).getText();
	  System.out.println("Text choosen: "+ allItemSelectedText);
			  
	  if(numberItemSelected >= 1 ) {
		  for(String item: itemSelectedText) {
			  if(allItemSelectedText.contains(item)) {
				  break;
			 }
		  }
		  return true;
	  }
	  else {
		  System.out.println("You not choose item");
		  return false;
	  }
  }
  
 // @Test
  public void TC_01_CustomMultiSelectDropdownList() throws Exception {
	  driver.get("http://multiple-select.wenzhixin.net.cn/examples/#basic.html");
	  By contentIframeXpath = By.xpath("//div[@class='content']//iframe");
	  
	  String[] items = {"January", "April", "July"};
	  String[] newItems = {"January", "April", "July", "October", "December"};
	  
	  WebElement contentIframe = driver.findElement(contentIframeXpath);
	  driver.switchTo().frame(contentIframe);
	  
	  selectMultiItemInDropdown("//button[@class='ms-choice']", "//div[@class='ms-drop bottom']//span", items);
	  Assert.assertTrue(checkItemSelected(items));
	  
	  driver.navigate().refresh();
	  WebElement contentIframeRefresh = driver.findElement(contentIframeXpath);
	  driver.switchTo().frame(contentIframeRefresh);
	  
	  selectMultiItemInDropdown("//button[@class='ms-choice']", "//div[@class='ms-drop bottom']//span", newItems );
	  Assert.assertTrue(checkItemSelected(newItems));
  }
  
  @Test
  public void TC_02_CustomMultiSelectDropdownList_Bai2() throws Exception {
	  driver.get("https://semantic-ui.com/modules/dropdown.html");
	  By contentIframeXpath = By.xpath("//iframe[@class='github']");
  
	  String[] items = {"England", "American Samoa", "European Union"};
	  String[] newItems = {"American Samoa", "Algeria", "Aland Islands", "French Guiana", "Vietnam"};
	  
	  WebElement contentIframe = driver.findElement(contentIframeXpath);
	  driver.switchTo().frame(contentIframe);
	  
	  selectMultiCountry("//*[@id='example']/div[4]/div/div[2]/div[4]/div[1]/div[10]/div[2]/div[1]", "//div[@class='ui fluid multiple search selection dropdown active visible']//div[@class='item']", items);
	  //Assert.assertTrue(checkItemSelected(items));
	  
	/*  driver.navigate().refresh();
	  WebElement contentIframeRefresh = driver.findElement(contentIframeXpath);
	  driver.switchTo().frame(contentIframeRefresh);
	  
	  selectMultiCountry("//div[@class='ui fluid multiple search selection dropdown active visible']", "//div[@class='ui fluid multiple search selection dropdown active visible']//div[@class='item']", newItems );
	  Assert.assertTrue(checkItemSelected(newItems));*/

  }
  
  @AfterTest
  public void afterTest() {
	  
  }

}
