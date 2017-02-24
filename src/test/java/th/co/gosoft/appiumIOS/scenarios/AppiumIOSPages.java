package th.co.gosoft.appiumIOS.scenarios;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumIOSPages {
	private static  WebDriver driver;
	private static  DesiredCapabilities cap;

	
	public static void prepareIOSForAppium() throws MalformedURLException{
		File app = new File("src/test/resources/GO10-1.0.4.app");
		cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
		cap.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		loginAction();
		pressAllowBtnAction();
		logoutAction();
	}
	
	public static  void loginAction(){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[1]")).clear();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[1]")).sendKeys("appium@gosoft.co.th");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIASecureTextField[1]")).clear();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIASecureTextField[1]")).sendKeys("password");
		driver.findElement(By.id("Login")).click();	
	}
	
	public static void logoutAction(){
		driver.findElement(By.id("ic settings")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]")).click();	
	}
	
	public static void quitAppiumAction(){
		driver.quit();
	}
	
	public static void gotoRoomPage(){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]")).click();
	}
	
	public static  void postTopic(String strPost,String strContent){
		driver.findElement(By.id("Compose")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextView[2]")).clear();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextView[2]")).sendKeys(strPost);
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAScrollView[1]/UIAWebView[1]")).clear();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAScrollView[1]/UIAWebView[1]")).sendKeys(strContent);
		driver.findElement(By.id("ic send")).click();
	}
	
	public static void postComment(String strComment){
		driver.findElement(By.id("COMMENT")).click();
		driver.findElement(By.className("UIAWebView")).clear();
		driver.findElement(By.className("UIAWebView")).sendKeys(strComment);
		driver.findElement(By.id("ic send")).click();
	}
	
	public static  void gotoLikeTopic(String strPostForComment) {
		gotoTopic(strPostForComment);
		pressLikeBtnAction();
		pressBackBtnActoin();
	}
	
	public static  void gotoTopic(String topicName){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[@name = '" + topicName + "'][1]")).click();
	}
	
	public static  void gotoSettingAvatar(){
		driver.findElement(By.id("ic settings")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]")).click();
	}
	
	public static  void selectAvatar(String typeAvatar){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAButton[1]")).click();
		driver.findElement(By.id(typeAvatar)).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIACollectionView[1]/UIACollectionCell[7]")).click();
		driver.findElement(By.id("select")).click();
	}
	
	public static void changeNameAvatar(String avatarName){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")).clear();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")).sendKeys(avatarName);
		driver.findElement(By.id("Save")).click();
	}
	
	public static void deleteComment(String xpath){
		driver.findElement(By.xpath(xpath)).click();
		driver.findElement(By.id("Delete")).click();	
	}
	
	public static void deletePost(){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[3]")).click();
		driver.findElement(By.id("Delete")).click();	
	}
	
	public static void pressAllowBtnAction(){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[7]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[2]")).click();
	}
	
	public static  void pressBackBtnActoin(){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
	}
	
	public static  void pressHomeBtnAction(){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
	}
	
	public static  void pressLikeBtnAction(){
		driver.findElement(By.id("LIKE")).click();
	}
	
	
	public static String getTopicName(String xpath){
		return driver.findElement(By.xpath(xpath)).getAttribute("name");
	}
	
	public static String getCommentName(String xpath){
		return driver.findElement(By.xpath(xpath)).getAttribute("name");
	}
	
	public static int getCountLike(String xpath){
		return Integer.parseInt(driver.findElement(By.xpath(xpath)).getAttribute("value"));
	}
	
	public static String getPicAvatarName(String xpath){
		return driver.findElement(By.xpath(xpath)).getAttribute("name");
	}
	
	public static String getAvatarName(String xpath){
		return driver.findElement(By.xpath(xpath)).getAttribute("name");
	}
	
}
