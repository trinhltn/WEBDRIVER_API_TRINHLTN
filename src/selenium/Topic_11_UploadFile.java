package selenium;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_11_UploadFile {
	WebDriver driver;
	JavascriptExecutor js;
	String rootFolder = System.getProperty("user.dir");
	
	String fileName01 = "image01.jpg";
	String fileName02 = "image02.jpg";
	String fileName03 = "image03.jpg";
	
	String fileNamePath01 = rootFolder+"\\uploadFiles\\"+fileName01;
	String fileNamePath02 = rootFolder+"\\uploadFiles\\"+fileName02;
	String fileNamePath03 = rootFolder+"\\uploadFiles\\"+fileName03;
	
	String firefoxPath = rootFolder+"\\uploadFiles\\firefox.exe";
	String chromePath = rootFolder+"\\uploadFiles\\chrome.exe";
	String iePath = rootFolder+"\\uploadFiles\\ie.exe";
	
	String[] fileUploads = {fileNamePath01, fileNamePath02, fileNamePath03};
	
 
  @BeforeTest
  public void beforeTest() {
	  
//	  System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
//	  driver = new FirefoxDriver();
	  
	  System.setProperty("webdriver.chrome.driver",".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();
	  
//	  System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
//	  driver = new InternetExplorerDriver();
	  
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  js = (JavascriptExecutor) driver;
			  
  }
  
  //@Test
  public void TC_01_Sendkeys_Upload_Queue() throws Exception {
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
  
  //@Test
  public void TC_02_Sendkeys_Upload_Multiple_A_Time() throws Exception{
	  //upload nhiều file 1 lần
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  
	  Thread.sleep(3000);
	  
	  //02 - Sử dụng phương thức sendKeys để upload file nhiều file cùng lúc
	  WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
	  uploadFile.sendKeys(fileNamePath01 +  "\n" + fileNamePath02 + "\n" + fileNamePath03);
	  Thread.sleep(2000);
	  
	  //03 - Kiểm tra file đã được chọn thành công
	  driver.findElement(By.xpath("//span[text()='Start upload']")).click();
	  Thread.sleep(5000);

	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName01+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName02+"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName03+"']")).isDisplayed());
	  
  }
  
  public void clickToElementByJS(WebElement element) {
	  js.executeScript("arguments[0].click()", element);
  }
  
  //@Test
  public void TC_03_AutoIT() throws Exception {
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  
	  //02 - Sử dụng Robot để upload file chạy cho 3 trình duyệt
	  
	  if (driver.toString().contains("chrome") ||  driver.toString().contains("firefox")) {
          System.out.println("Go to Chrome/  Firefox");
          WebElement uploadFile =  driver.findElement(By.cssSelector(".fileinput-button"));
          uploadFile.click();
          Thread.sleep(1000);
          
          if(driver.toString().contains("chrome")) {
        	  Runtime.getRuntime().exec(new  String[] { chromePath, fileNamePath01 }); 
          }
          else if(driver.toString().contains("firefox")) {
        	  Runtime.getRuntime().exec(new  String[] { firefoxPath, fileNamePath01 }); 
          }
		} 
	  
	  else {
          System.out.println("Go to IE");
          WebElement uploadFile =  driver.findElement(By.xpath("//input[@type='file']"));
          clickToElementByJS(uploadFile);
          Thread.sleep(1000);
          Runtime.getRuntime().exec(new  String[] { iePath, fileNamePath01 });
	  }
	  Thread.sleep(3000);
	  
	  driver.findElement(By.xpath("//span[text()='Start upload']")).click();
	  Thread.sleep(5000);

	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName01+"']")).isDisplayed());
	  
  }
  
  //@Test
  public void TC_04_Robot() throws Exception {
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  
	  StringSelection select = new  StringSelection(fileNamePath01);

      // Copy to clipboard
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

      if (driver.toString().contains("chrome")  || driver.toString().contains("firefox")) {
          WebElement uploadFile =  driver.findElement(By.cssSelector(".fileinput-button"));
          uploadFile.click();
          Thread.sleep(1000);
      } else {
          System.out.println("Go to IE");
          WebElement uploadFile =  driver.findElement(By.xpath("//input[@type='file']"));
          clickToElementByJS(uploadFile);
          Thread.sleep(1000);
      }

      Robot robot = new Robot();
      Thread.sleep(1000);

      // Nhan phim Enter
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);

      // Nhan xuong Ctrl - V
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);

      // Nha Ctrl - V
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_V);
      Thread.sleep(1000);

      // Nhan Enter
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      
      Thread.sleep(3000);
      
      driver.findElement(By.xpath("//span[text()='Start upload']")).click();
	  Thread.sleep(5000);

	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName01+"']")).isDisplayed());
  }
  
  public int RandomFile() {
	  Random rand = new Random();
	  return rand.nextInt(1000);
  }
  
  @Test
  public void TC_05_Upload_file() throws Exception {
	  driver.get("https://encodable.com/uploaddemo/");
	  
	  //run on chrome
	  
	  //02 - Choose Files to Upload
      WebElement uploadFile =  driver.findElement(By.xpath("//input[@id='uploadname1']"));
      uploadFile.sendKeys(fileNamePath01);
      Thread.sleep(1000);
      
      //03 - Select dropdown (Upload to: /uploaddemo/files/)
      WebElement parent = driver.findElement(By.xpath("//select[@name='subdir1']"));
      parent.click();
      List <WebElement> allItems = driver.findElements(By.xpath("//select[@name='subdir1']//option"));
      for(WebElement Item: allItems) {
    	  if(Item.getText().equals("/uploaddemo/files/")) {
    		  js.executeScript("arguments[0].scrollIntoView(true);", Item);
    		  Item.click();
    	  }
      }
      
      //04 - Input random folder to 'New subfolder? Name
      String nameSubFolder = "Auto TrinhLTN"+RandomFile();
      WebElement nameFolder = driver.findElement(By.xpath("//input[@id='newsubdir1']"));
      nameFolder.sendKeys(nameSubFolder);
      
      //05 - Input email and firstname
      String email = "trinh@gmail.com";
      driver.findElement(By.xpath("//input[@name='email_address']")).sendKeys(email);
      driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("TrinhLe");
      
      //06 - Click Begin Upload (Note: Wait for page load successfully)
      driver.findElement(By.xpath("//input[@value='Begin Upload']")).click();
      Thread.sleep(4000);
      
      //07 - Verify information: email, firstName, nameFile
      Assert.assertTrue(driver.findElement(By.xpath("//dt[text()='Your upload is complete:']")).isDisplayed());
      String innerText = (String) js.executeScript("return document.documentElement.innerText;");
      Assert.assertTrue(innerText.contains("Email Address: "+email));
      Assert.assertTrue(innerText.contains("First Name: "+"TrinhLe"));
      Assert.assertTrue(innerText.contains(fileName01)); 
      
      //08 - Click 'View Uploaded Files' link
      driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
      Thread.sleep(3000);
      
      //09 - Click to random folder
      /*List <WebElement> folders = driver.findElements(By.xpath("//td//a"));
      for(WebElement folder: folders) {
    	  if(folder.getText().equals(nameSubFolder)) {
    		  js.executeScript("arguments[0].scrollIntoView(true);", folder);
    		  clickToElementByJS(folder);
    		  Thread.sleep(5000);
    	  }
      }*/
      
      driver.findElement(By.xpath("//td//a[text()='"+nameSubFolder+"']")).click();
      
      //10 - Verify file name exist in folder
      String textName = (String) js.executeScript("return document.documentElement.innerText;");
      Assert.assertTrue(textName.contains(fileName01)); 
  }
  
  @AfterTest
  public void afterTest() {
	  
  }

}
