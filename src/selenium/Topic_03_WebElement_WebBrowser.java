package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_03_WebElement_WebBrowser {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18Radio = By.xpath("//input[@id='under_18']");
	By educationTextArea = By.xpath("//textarea[@id='edu']");
	By jobRole01 = By.xpath("//select[@id='job1']");
	By interestsDevelopment = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonEnable = By.xpath("//button[@id='button-enabled']");
	By password = By.xpath("//input[@id='password']");
	By ageRadioIsDisabled = By.xpath("//input[@id='radio-disabled']");
	By biography = By.xpath("//textarea[@id='bio']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By interestsCheckboxDisable = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonDisabled = By.xpath("//button[@id='button-disabled']");
	
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
  }

  //check element isDisplayed
  public boolean isElementDisplayed(By byvalue) {
	  return driver.findElement(byvalue).isDisplayed();
  }
  
  //check element isEnable
  public boolean isEnable(By byvalue) {
	  return driver.findElement(byvalue).isEnabled();
  }
  
  //check element isSelected
  public boolean isSelected(By byvalue) {
	  return driver.findElement(byvalue).isSelected();
  }
  
 @Test
  public void TC_01_CheckDisplayed() throws InterruptedException {
	  if(isElementDisplayed(emailTextbox)) {
		  driver.findElement(emailTextbox).sendKeys("Automation Testing");
	  }
	  
	  if(isElementDisplayed(educationTextArea)) {
		  driver.findElement(educationTextArea).sendKeys("Automation Testing");
	  }
	  
	  if(isElementDisplayed(ageUnder18Radio)) {
		  driver.findElement(ageUnder18Radio).click();
	  }
	  
	  Thread.sleep(5000);
  }
  
 @Test
  public void TC_02_Check_Enable_Disable() {
	  //email
	  if(isEnable(emailTextbox)) {
		  System.out.println("Email is enabled");
	  }
	  else System.out.println("Email is enabled");
	  //ageumder18
	  if(isEnable(ageUnder18Radio)) {
		  System.out.println("Age (Under 18) is enabled");
	  }
	  else System.out.println("Age (Under 18) is disabled");
	  //education
	  if(isEnable(educationTextArea)) {
		  System.out.println("Education is enabled");
	  }
	  else System.out.println("Education is disabled");
	  //Job Role 01
	  if(isEnable(jobRole01)) {
		  System.out.println("Job Role 1 is enabled");
	  }
	  else System.out.println("Job Role 1 is disabled");
	  //Interests (Development
	  if(isEnable(interestsDevelopment)) {
		  System.out.println("Interests (Development) is enabled");
	  }
	  else System.out.println("Interests (Development) is disabled");
	  //slider 01
	  if(isEnable(slider01)) {
		  System.out.println("Slider 01 is enabled");
	  }
	  else System.out.println("Slider 01 is disabled");
	  //button is enabled
	  if(isEnable(buttonEnable)) {
		  System.out.println("ButtonEnable is enabled");
	  }
	  else System.out.println("ButtonEnable is disabled");
	  //password
	  if(isEnable(password)) {
		  System.out.println("Password is enabled");
	  }
	  else System.out.println("Password is disabled");
	  //age radio button is disabled
	  if(isEnable(ageRadioIsDisabled)) {
		  System.out.println("Age (Radiobutton is disabled) is enabled");
	  }
	  else System.out.println("Age (Radiobutton is disabled) is disabled");
	  //biography
	  if(isEnable(biography)) {
		  System.out.println("Biography is enabled");
	  }
	  else System.out.println("Biography is disabled");
	  //job role 02
	  if(isEnable(jobRole02)) {
		  System.out.println("Job Role 02 is enabled");
	  }
	  else System.out.println("Job Role 02 is disabled");
	  //interests checkbox is disable
	  if(isEnable(interestsCheckboxDisable)) {
		  System.out.println("Interests (Checkbox is disabled) is enabled");
	  }
	  else System.out.println("Interests (Checkbox is disabled) is disabled");
	  //Slider02
	  if(isEnable(slider02)) {
		  System.out.println("Slider 02 is enabled");
	  }
	  else System.out.println("Slider 02 is disabled");
	  //button is disabled
	  if(isEnable(buttonDisabled)) {
		  System.out.println("ButtonDisable is enabled");
	  }
	  else System.out.println("ButtonDisable is disabled");

  }
 
  public boolean CheckEnabled(By Value) {
	  if(driver.findElement(Value).isEnabled()) {
		  System.out.println("Element <"+Value+"> is enabled");
		  return true;
	  }
	  else {
		  System.out.println("Element <"+Value+"> is disabled");
		  return false;
	  }
  }
  
  @Test
  public void TC_02_cach_02() {
	  //use CheckEnabled()
	  CheckEnabled(emailTextbox);
	  CheckEnabled(ageUnder18Radio);
	  CheckEnabled(educationTextArea);
	  CheckEnabled(jobRole01);
	  CheckEnabled(interestsDevelopment);
	  CheckEnabled(slider01);
	  CheckEnabled(buttonEnable);
	  CheckEnabled(password);
	  CheckEnabled(ageRadioIsDisabled);
	  CheckEnabled(biography);
	  CheckEnabled(jobRole02);
	  CheckEnabled(biography);
	  CheckEnabled(interestsCheckboxDisable);
	  CheckEnabled(slider02);
	  CheckEnabled(buttonDisabled);
	  
  }
  @Test
  public void TC_03_Check_Element_Is_Selected() throws InterruptedException {
	  //step 02: Click chọn Age (Under 18)/ Interests (Development)
	  driver.findElement(ageUnder18Radio).click();
	  driver.findElement(interestsDevelopment).click();
	  //Nếu chưa được chọn thì cho phép chọn lại 1 lần nữa
	  if(isSelected(ageUnder18Radio)) {}
	  else {
		  driver.findElement(ageUnder18Radio).click();
	  }
	  if(isSelected(interestsDevelopment)) {}
	  else {
		  driver.findElement(interestsDevelopment).click();
	  }
	  
	  Thread.sleep(5000);
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
