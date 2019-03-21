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

public class Topic_05_MultiDropdown_common {
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

  public void selectMultiItemInDropdown(String parentXpath,String allItemXpath, String[] expectedValue) throws Exception {
  	  // 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
	  WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
	  if(parentDropdown.isDisplayed()) {
		  parentDropdown.click();
	  }
	  else {
		  javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
	  }
	  // 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	  List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
	  System.out.println("size is: = " + allItems.size());
	  // Duyệt qa hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
	  for (WebElement  childElement : allItems) {
		  // "January, April, July"
		  for (String item : expectedValue) {
			  if(childElement.getText().equals(item)) {
				  // 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko cần scroll)
				  javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				  Thread.sleep(1500);
				  //4 Click item can chon
				  if(childElement.isDisplayed()) {
					  childElement.click();
				  }
				  else {
					  javascriptExecutor.executeScript("arguments[0].click();", childElement);
				  }
			  }
		   
		   }
	  }
  }
          
          
          
  public boolean isCountrySelected01(String[] selectedcountries) {
	  List<WebElement> selectedItems = driver.findElements(By.xpath("//div[@class='menu transition visible']/div[@class='item active filtered']"));
	  int numberOfElements = selectedItems.size();
	  List<WebElement> allSelectedTextElement = driver.findElements(By.xpath("//a[@class='ui label transition visible']"));
	  if((numberOfElements >0) &&(selectedItems.size()==selectedcountries.length)) {
		  for (WebElement selectedTextElement : allSelectedTextElement) {
			  System.out.println("selected text is: = " + selectedTextElement.getText());
			  for (String value : selectedcountries) {
				  if(value.equals(selectedTextElement.getText())) {
				Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ui label transition visible' and contains(text(),'"+selectedTextElement.getText()+"')]")).isDisplayed());
				  }
			  }
	  }
	return true;
	  }
	  return false;
}
  
  @Test
  public void TC_01_SelectMultiCountry() throws Exception {
	  driver.get("https://semantic-ui.com/modules/dropdown.html");
  
	  String[] items = {"England", "American Samoa", "European Union"};
	  String[] newItems = {"American Samoa", "Algeria", "Aland Islands", "French Guiana", "Vietnam"};
	  
	  selectMultiItemInDropdown("//div[contains(text(),'Dropdowns can support')]/following-sibling::div", "//div[@class='menu transition visible']//div", items);
	  isCountrySelected01(items);
	 
	  
  }
  
  public boolean checkItemSelected(String[] itemSelectedText) {
	  List <WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
	  int numberItemSelected = itemSelected.size();
	  
	  String allItemSelectedText = driver.findElement(By.xpath("//button[@class='ms-choice']/span")).getText();
	  System.out.println("Text choosen: "+ allItemSelectedText);
			  
	  if(numberItemSelected <= 3 && numberItemSelected > 0 && (itemSelected.size()==itemSelectedText.length)) {
		  for(String item: itemSelectedText) {
			  if(allItemSelectedText.contains(item)) {
				  //break;
				  
			  }
		  }
		  return true;
	  }
	  else {
		  return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='"+numberItemSelected+" of 12 selected']")).isDisplayed();
	  }
  }
  
  
  @Test
  public void TC_02_CustomMultiSelectDropdownList() throws Exception {
	  driver.get("http://multiple-select.wenzhixin.net.cn/examples/#basic.html");
	  By contentIframeXpath = By.xpath("//div[@class='content']//iframe");
	  
	  String[] items = {"January", "April", "July"};
	  String[] newItems = {"January", "April", "July", "October", "December"};
	  
	  WebElement contentIframe = driver.findElement(contentIframeXpath);
	  driver.switchTo().frame(contentIframe);
	  selectMultiItemInDropdown("//button[@class='ms-choice']", "//div[@class='ms-drop bottom']//span", items);
	  checkItemSelected(items);
	  
  }
  
  @AfterTest
  public void afterTest() {
	  
  }

}
