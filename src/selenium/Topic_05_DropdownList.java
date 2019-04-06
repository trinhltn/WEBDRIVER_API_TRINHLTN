//link bt: https://www.evernote.com/shard/s415/client/snv?noteGuid=b926a5a6-935c-4476-9291-dbdf686f4bf2&noteKey=0f4e600e42aa9287&sn=https%3A%2F%2Fwww.evernote.com%2Fshard%2Fs415%2Fsh%2Fb926a5a6-935c-4476-9291-dbdf686f4bf2%2F0f4e600e42aa9287&title=TOPIC%2B04%2B-%2BEXERCISE
package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_05_DropdownList {
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

  @Test
  public void TC_01_DefaultDropdown() throws Exception {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  WebElement jobRole01 = driver.findElement(By.xpath("//select[@id='job1']"));
	  Select jobRoleSelect = new Select(jobRole01);

	  //Check dropdown Job Role 01 not support multi-select
	  Assert.assertFalse(jobRoleSelect.isMultiple());

	  //Selected Automation Tester n dropdown using selectVisible & Kiểm tra giá trị đã được chọn thành công
	  jobRoleSelect.selectByVisibleText("Automation Tester");
	  Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Automation Tester");
	  Thread.sleep(3000);

	  //Selected Manual Tester on dropdown using selectValue
	  jobRoleSelect.selectByValue("manual");
	  Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Manual Tester");
	  Thread.sleep(3000);

	  //Selected Mobile Tester on dropdown using selectIndex
	  jobRoleSelect.selectByIndex(3);
	  Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Mobile Tester");
	  Thread.sleep(3000);

	  //Check the dropdown  has 5 values
	  Assert.assertEquals(jobRoleSelect.getOptions().size(), 5);
  }

  public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) throws Exception {

	WebElement parentDropDown = driver.findElement(By.xpath(parentXpath));
  	javascriptExecutor.executeScript("arguments[0].click();", parentDropDown);
	javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", parentDropDown);
	waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	List<WebElement> allElements = driver.findElements(By.xpath(allItemXpath));
	for (WebElement childElement : allElements) {
		if (childElement.getText().equals(expectedValueItem)) {
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
			Thread.sleep(1000);
			//childElement.click();
			javascriptExecutor.executeScript("arguments[0].click();", childElement);
			break;
		}
	}

  }

 public void selectItemInCustomDropdown_NotUseClickOfJS(String parentXpath, String allItemXpath, String expectedValueItem) throws Exception {

	WebElement parentDropDown = driver.findElement(By.xpath(parentXpath));
	javascriptExecutor.executeScript("arguments[0].click();", parentDropDown);
	javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", parentDropDown);
	waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	List<WebElement> allElements = driver.findElements(By.xpath(allItemXpath));
	for (WebElement childElement : allElements) {
		if (childElement.getText().equals(expectedValueItem)) {
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
			Thread.sleep(1000);
			childElement.click();
			break;
		}
	}

  }

  public boolean elementIsDisplayed(String xpathValue) {
	  WebElement element = driver.findElement(By.xpath(xpathValue));
	  if(element.isDisplayed()) {
		  return true;
	  }
	  else {
		  return false;
	  }
  }

  @Test
  public void TC_02_CustomDropdown() throws Exception {
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

	  selectItemInCustomDropdown_NotUseClickOfJS("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
	  Assert.assertTrue(elementIsDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']"));

  }

  @Test
  public void TC_03_CustomDropdown_Angular() throws Exception {
	  driver.get("https://material.angular.io/components/select/examples");

	  selectItemInCustomDropdown("//mat-select[@id='mat-select-5']", "//mat-option/span", "Michigan");
	  Assert.assertTrue(elementIsDisplayed("//div[@class='mat-select-value']//span[text()='Michigan']"));

	  selectItemInCustomDropdown("//mat-select[@id='mat-select-5']", "//mat-option/span", "Wyoming");
	  Assert.assertTrue(elementIsDisplayed("//div[@class='mat-select-value']//span[text()='Wyoming']"));

	  selectItemInCustomDropdown("//mat-select[@id='mat-select-5']", "//mat-option/span", "Alabama");
	  Assert.assertTrue(elementIsDisplayed("//div[@class='mat-select-value']//span[text()='Alabama']"));
  }

  @Test
  public void TC_04_CustomDropdown_JQuery() throws Exception {
	  driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");

	  selectItemInCustomDropdown("//span[@class='k-input' and text()='Black']/parent::span", "//ul[@id='color_listbox']/child::li", "Orange");
	  Assert.assertTrue(elementIsDisplayed("//span[@unselectable='on']//span[text()='Orange']"));

  }

  @Test
  public void TC_05_CustomDropdown_VueJS() throws Exception {
	  driver.get("https://mikerodham.github.io/vue-dropdowns/");

	  selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Second Option");
	  Assert.assertTrue(elementIsDisplayed("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]"));

  }

  @Test
  public void TC_06_CustomDropdown_JQuery_Editable() throws Exception {
	  driver.get("http://indrimuska.github.io/jquery-editable-select/");

	  WebElement effects = driver.findElement(By.xpath("//div[text()='Default']/following-sibling::div/input"));
	  effects.click();
	  effects.sendKeys("a");
  }


  @AfterTest
  public void afterTest() {

  }

}