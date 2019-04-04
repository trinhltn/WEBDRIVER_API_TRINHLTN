package selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_11_UploadFile {
	WebDriver driver;
	String rootFolder = System.getProperty("user.dir");
	
	String fileName01 = "image01.jpg";
	String fileName02 = "image02.jpg";
	String fileName03 = "image03.jpg";
	
	String fileNamePath01 = rootFolder+"\\uploadFiles\\"+fileName01;
	String fileNamePath02 = rootFolder+"\\uploadFiles\\"+fileName02;
	String fileNamePath03 = rootFolder+"\\uploadFiles\\"+fileName03;
	
	String[] fileUploads = {fileNamePath01, fileNamePath02, fileNamePath03};
	
 
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  //System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
	  //driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
			  
  }
  
  @Test
  public void TC_01_Upload_Queue() throws Exception {
	  //upload lần lượt từng file - hàng đợi
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  
	  //02 - Sử dụng phương thức sendKeys để upload file nhiều file cùng lúc
	  for(String file : fileUploads) {
		  WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		  uploadFile.sendKeys(file);
		  Thread.sleep(2000);
	  };
	  
	  //03 - Kiểm tra file đã được chọn thành công
	  driver.findElement(By.xpath("//span[text()='Start upload']")).click();
	  Thread.sleep(2000);
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName01+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName02+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName03+"']")).isDisplayed());
	  
  }
  
  @AfterTest
  public void afterTest() {
	  
  }

}
