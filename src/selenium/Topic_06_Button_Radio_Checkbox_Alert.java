//https://www.evernote.com/shard/s415/client/snv?noteGuid=a5da7010-7e14-43b0-ae89-81335ba9828a&noteKey=c3ea9e0fba7c9d55&sn=https%3A%2F%2Fwww.evernote.com%2Fshard%2Fs415%2Fsh%2Fa5da7010-7e14-43b0-ae89-81335ba9828a%2Fc3ea9e0fba7c9d55&title=TOPIC%2B05%2B-%2BEXERCISE
package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_06_Button_Radio_Checkbox_Alert {
  WebDriver driver;
  JavascriptExecutor jsExcutor;
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  jsExcutor = (JavascriptExecutor) driver;
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TC_01_Button_Click() {
	  driver.get("http://live.guru99.com/");
	  
	  WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
	  jsExcutor.executeScript("arguments[0].click();",myAccountLink);
	  
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
	  
	  WebElement createAnAccountLink = driver.findElement(By.xpath("//span[text()='Create an Account']"));
	  jsExcutor.executeScript("arguments[0].click();", createAnAccountLink);
	  
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
  }
  
  
  public void CheckToCheckbox(By byValue) {
	  WebElement element = driver.findElement(byValue);
	  if(!element.isSelected()) {
		  element.click();
	  }
  }
  public void UnCheckToCheckbox(By byValue) {
	  WebElement element = driver.findElement(byValue);
	  if(element.isSelected()) {
		  element.click();
	  }
  }
  
  @Test
  public void TC_02_Custom_Checkbox_Click() {
	  driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
	  WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
	  
	  //check
	  jsExcutor.executeScript("arguments[0].click()", dualZoneCheckbox);
	  Assert.assertTrue(dualZoneCheckbox.isSelected());
	  
	  //uncheck
	  jsExcutor.executeScript("arguments[0].click()", dualZoneCheckbox);
	  Assert.assertFalse(dualZoneCheckbox.isSelected());
	  
  }
  
  public void CheckToRadio(WebElement element) {
	  if(element.isSelected()) {
		  System.out.println("Selected!!!");
	  }
	  else {
		  jsExcutor.executeScript("arguments[0].click();", element);
	  }
  }
  
  @Test
  public void TC_03_Custom_RadioButton() {
	  driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
	  WebElement Petrol = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
	  
	  //check
	  jsExcutor.executeScript("arguments[0].click()", Petrol);
	  Assert.assertTrue(Petrol.isSelected());
	  CheckToRadio(Petrol);
	  
  }
  
  @Test
  public void TC_04_Alert() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  WebElement JSAlert = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
	  JSAlert.click();
	  
	  Alert acceptAlert = driver.switchTo().alert();
	  Assert.assertEquals(acceptAlert.getText(), "I am a JS Alert");
	  
	  acceptAlert.accept();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked an alert successfully ']")).isDisplayed());
  }
  
  @Test
  public void TC_05_Alert_Confirm() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  WebElement JSAlert = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
	  JSAlert.click();
	  
	  Alert acceptAlert = driver.switchTo().alert();
	  Assert.assertEquals(acceptAlert.getText(), "I am a JS Confirm");
	  
	  acceptAlert.dismiss();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked: Cancel']")).isDisplayed());
  }
  
  @Test
  public void TC_05_Alert_Prompt() {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  WebElement JSAlert = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
	  JSAlert.click();
	  
	  Alert acceptAlert = driver.switchTo().alert();
	  Assert.assertEquals(acceptAlert.getText(), "I am a JS prompt");
	  String key = "trinh";
	  acceptAlert.sendKeys(key);
	  acceptAlert.accept();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You entered: "+ key +"']")).isDisplayed());
  }
  
  @Test
  public void TC_05_Alert_Signin() {
	  driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
  }
  @AfterTest
  public void afterTest() {
  }

}
