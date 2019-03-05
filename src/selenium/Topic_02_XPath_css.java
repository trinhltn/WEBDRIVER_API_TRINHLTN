package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_XPath_css {
	WebDriver driver;
	
	@BeforeClass
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}
	
	@Test
	public void TC_01_Login_Empty() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.xpath("//button[@title=\"Login\"]")).click();
		
		String emailRequired = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailRequired, "This is a required field.");
		
		String passwordRequired = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordRequired, "This is a required field.");
	}
	
	@Test
	public void TC_02_Login_With_Email_Invalid() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.xpath("//button[@title=\"Login\"]")).click();
		
		String emailInvalid = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailInvalid, "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_Login_With_Password_Less_Than_6_Character() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title=\"Login\"]")).click();
		
		String passwordIncorrect = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordIncorrect, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_Login_With_Password_Incorrect() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@title=\"Login\"]")).click();
		
		String passwordIncorrect = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
		Assert.assertEquals(passwordIncorrect, "Invalid login or password.");
	}
	
	@Test
	public void TC_05_Create_An_Account() throws InterruptedException {
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[contains(@title,'Create an Account')]")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Trinh");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Thi");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Le");
		
		//random email
		Random rand = new Random();
		int n = rand.nextInt(10000);
		String email = "automation" + n + "@gmail.com";
		
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123123123");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		//verify message
		String success_msg = driver.findElement(By.xpath("//li[@class = 'success-msg']//span")).getText();
		Assert.assertEquals(success_msg, "Thank you for registering with Main Website Store.");
		
		//logout
		driver.findElement(By.xpath("//div[@class='page-header-container']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		Thread.sleep(5000);
		//return homePage
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
	}
	
	@AfterClass
	public void afterTest() {
		driver.quit();
	}

}
