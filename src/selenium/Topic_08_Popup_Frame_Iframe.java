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
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_08_Popup_Frame_Iframe {
  WebDriver driver;
  JavascriptExecutor jsExecutor;
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  jsExecutor = (JavascriptExecutor) driver;
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  //check real img, img load successfully
  public boolean isImageLoadedSuccess(WebElement element) {
	  return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
  }
  
  @Test
  public void TC_01() {
	  driver.get("https://www.hdfcbank.com/");
	  /*
	   * Popup:
	   * case 01: Nếu có display -> close -> next step
	   * case 02: Không display -> chạy bth
	   */
	  /*List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  int notificationIframeSize = notificationIframe.size();
	  System.out.println("Size of notification iframe: " + notificationIframeSize);
	  
	  if(notificationIframeSize > 0) {
		//switch to notify iframe
		  driver.switchTo().frame(notificationIframe.get(0));
		  
		  //check display img
		  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
		  //close img
		  jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='div-close']")));
		  System.out.println("Close popup");
		  
	  }*/
	  

	  //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  //về Top Window
	  //driver.switchTo().defaultContent();
	  
	  WebElement imgPopup = driver.findElement(By.xpath("//img[@class='popupbanner']"));
	  if(imgPopup.isDisplayed()) {
		  driver.findElement(By.xpath("//img[@class='popupCloseButton']")).click();
	  }
	  
	  WebElement lookingforIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	  driver.switchTo().frame(lookingforIframe);
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']")).isDisplayed());
	 
	  //Verify banner có đúng 6 images
	  driver.switchTo().defaultContent();
	  WebElement imgIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	  driver.switchTo().frame(imgIframe);
	  
	  List <WebElement> imgages = driver.findElements(By.xpath("//img[@class='bannerimage']"));
	  int sizeImages = imgages.size();
	  System.out.println("Size of img banner: " + sizeImages);
	  
	  Assert.assertEquals(sizeImages, 6);
	  
	  //check element = real img
	  for(WebElement img:imgages) {
		  Assert.assertTrue(isImageLoadedSuccess(img));
	  }
	  
	  //Verify flipper banner được hiển thị và có 8 items
	  driver.switchTo().defaultContent();
	  List <WebElement> flipperImages = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	  int SizeOfFlipperImages = flipperImages.size();
	  System.out.println("Size of img flipperBanner: " + SizeOfFlipperImages);
	  
	  Assert.assertEquals(SizeOfFlipperImages, 8);
	  
	  //check real img
	  for(WebElement img: flipperImages) {
		  Assert.assertTrue(isImageLoadedSuccess(img));
		  Assert.assertTrue(img.isDisplayed());
	  }
	  
  }
  
  @AfterTest
  public void afterTest() {
	  
  }

}
