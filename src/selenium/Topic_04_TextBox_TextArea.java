//link exercise: https://www.evernote.com/shard/s415/client/snv?noteGuid=b926a5a6-935c-4476-9291-dbdf686f4bf2&noteKey=0f4e600e42aa9287&sn=https%3A%2F%2Fwww.evernote.com%2Fshard%2Fs415%2Fsh%2Fb926a5a6-935c-4476-9291-dbdf686f4bf2%2F0f4e600e42aa9287&title=TOPIC%2B04%2B-%2BEXERCISE
//vào http://demo.guru99.com nhập email trinh@gmail.com để lấy userID và Password
//userID: mngr183806  
//pw: uzEmEbU 
//vào http://demo.guru99.com/v4/ để login
package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_04_TextBox_TextArea {
	WebDriver driver;
	String customerName, gender, dateOfBirth, address, city, state, pin, phone, email, password;
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
	
	//lacator element
	By customerNameTextbox = By.xpath("//input[@name='name']");
	By genderRadioButton= By.xpath("//input[@value='m']");
	By dateOfBirthTextbox = By.xpath("//input[@name='dob']");
	By addressArea = By.xpath("//textarea[@name='addr']");
	By cityTextbox = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By pinTextbox = By.xpath("//input[@name='pinno']");
	By phoneTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");
	By passwordTextbox = By.xpath("//input[@name='password']");
	By submitButton = By.xpath("//input[@value='Submit']");
	
  @BeforeTest
  public void beforeTest() {
    driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("http://demo.guru99.com/v4/");
	
	//login
	driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr183806");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("uzEmEbU");
	driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	
	//Verify HomePage được hiển thị thành công
	Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	
	customerName = "Selenium";
	gender = "male";
	dateOfBirth = "01/01/1999";
	address = "123 Nguyen Van Linh";
	city = "Da Nang";
	state = "Hai Chau";
	pin = "123456";
	phone = "0123456789";
	email = "trinh"+randomNumber()+"@gmail.com";
	password = "123456";
  }
  
  @Test
  public void TC_01_create_Customer() {
	 driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	 Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Add New Customer']")).isDisplayed());
	 
	 driver.findElement(customerNameTextbox).sendKeys(customerName);
	 driver.findElement(genderRadioButton).sendKeys(gender);
	 driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
	 driver.findElement(addressArea).sendKeys(address);
	 driver.findElement(cityTextbox).sendKeys(city);
	 driver.findElement(stateTextbox).sendKeys(state);
	 driver.findElement(pinTextbox).sendKeys(pin);
	 driver.findElement(phoneTextbox).sendKeys(phone);
	 driver.findElement(emailTextbox).sendKeys(email);
	 driver.findElement(passwordTextbox).sendKeys(password);
	 driver.findElement(submitButton).click();
	 
	 Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
	 
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	 
  }  
  
  @Test
  public void TC_02_edit_Customer() {
	  
  }

  @AfterTest
  public void afterTest() {
	  
  }

}
