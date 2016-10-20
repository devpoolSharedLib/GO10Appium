package th.co.gosoft.appiumIOS;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchShortcuts;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import th.co.gosoft.util.EnvironmentUtil;

public class AppiumIOSTest {
	private static  WebDriver driver;
	
	private static DesiredCapabilities cap;
	private String strPost = "IOS New Topic";
	private String strContent = "Appium IOS New Content";
	private String strComment = "Appium IOS New Comment";
	private String strPostFotComment = "Post for Test Comment and Like Automate";
	private String defaultNameAvatar = "DefaultAppuim";
	private String nameAvatar = "NameAppium";
	
	@BeforeClass
	public static void setEnvironment(){
		EnvironmentUtil.deleteTopic();
		EnvironmentUtil.createTopic();
		EnvironmentUtil.deleteLike();
	}
	
	@BeforeClass
	public static void setupAppium() throws MalformedURLException{
//		File appDir = new File("src");	
		File appDir = new File("/Users/Plooer/Library/Developer/Xcode/DerivedData/GO10-fguwrzphkqtrmfescdvxhwnxgxtr/Build/Products/Debug-iphonesimulator");
		File app = new File(appDir,"GO10.app");
		
		cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
//		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
		cap.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Before
	public void login() throws MalformedURLException {
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[1]")).clear();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[1]")).sendKeys("appium@gosoft.co.th");
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIASecureTextField[1]")).clear();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIASecureTextField[1]")).sendKeys("password");
		driver.findElement(By.id("Login")).click();
	}
	
//	@AfterClass
	public static void quit(){
		driver.quit();
	}
	
	@After
	public void logout(){
		driver.findElement(By.id("ic settings")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]")).click();
	}
	
//	@Test
	public void hotTopicList() throws MalformedURLException, InterruptedException{
		List<WebElement> hotTopicElements = driver.findElements(By.className("UIATableCell"));
		List<WebElement> roomElements = driver.findElements(By.className("UIACollectionCell"));
		assertEquals(12, hotTopicElements.size());
		assertEquals(10, roomElements.size());
	}
	
	@Test
	public void newPost(){
		gotoRoomPage();
		driver.findElement(By.id("Compose")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextView[2]")).clear();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextView[2]")).sendKeys(strPost);
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAScrollView[1]/UIAWebView[1]")).clear();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAScrollView[1]/UIAWebView[1]")).sendKeys(strContent);
		driver.findElement(By.id("ic send")).click();
		assertEquals(strPost,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).getAttribute("name"));	
		
		//check news feed
		pressBackBtn();
		assertEquals(strPost,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).getAttribute("name"));
	}	
	
	
	@Test
	public void newComment(){
		gotoRoomPage();
		gotoTopic();
		driver.findElement(By.id("COMMENT")).click();
		driver.findElement(By.className("UIAWebView")).clear();
		driver.findElement(By.className("UIAWebView")).sendKeys(strComment);
		driver.findElement(By.id("ic send")).click();
		assertEquals(strComment,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[3]")).getAttribute("name"));	
		
		//check news feed
		pressHomeBtn();
		assertEquals(strPostFotComment,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).getAttribute("name"));
	}
	
	@Test
	public void Like(){
		gotoRoomPage();
		//newLike
		gotoLike();
		assertEquals(1,Integer.parseInt(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[@name = '" + strPostFotComment + "'][1]/UIAStaticText[2]")).getAttribute("value")));
		//UpdateLike disLike
		gotoLike();
		assertEquals(0,Integer.parseInt(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[@name = '" + strPostFotComment + "'][1]/UIAStaticText[2]")).getAttribute("value")));
		//UpdateLike Like
		gotoLike();
		assertEquals(1,Integer.parseInt(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[@name = '" + strPostFotComment + "'][1]/UIAStaticText[2]")).getAttribute("value")));
	}
	

	//	@Test
	public void newLike(){
		gotoRoomPage();
		gotoTopic();
		//Like
		pressLikeBtn();
		//Dislike
		pressLikeBtn();
		//Like
		pressLikeBtn();
		
		pressBackBtn();
		assertEquals(1,Integer.parseInt(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[2]")).getAttribute("value")));
		
	}
	
//	@Test
	public void updatedisLike(){
		gotoRoomPage();
		gotoTopic();
		//DisLike
		pressLikeBtn();
		
		pressBackBtn();
		assertEquals(0,Integer.parseInt(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[2]")).getAttribute("value")));
		
	}
	
//	@Test
	public void updateLike(){
//		gotoRoomPage();
		gotoTopic();
		//Like
		pressLikeBtn();
		
		pressBackBtn();
		assertEquals(1,Integer.parseInt(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[2]")).getAttribute("value")));
		
	}
	
	@Test
	public void changeAvatarPic(){
		
		gotoSettingAvatar();
		selectAvatar("Woman");
		assertEquals("girl07",driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAButton[1]")).getAttribute("name"));
		
		selectAvatar("Man");
		assertEquals("man07",driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAButton[1]")).getAttribute("name"));
		
		pressBackBtn();
		pressBackBtn();
	}
	
	@Test
	public void changeAvatarName(){
		gotoSettingAvatar();
		changeNameAvatar(nameAvatar);
		assertEquals(nameAvatar,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]")).getAttribute("name"));
		changeNameAvatar(defaultNameAvatar);
		assertEquals(defaultNameAvatar,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]")).getAttribute("name"));
		pressBackBtn();
		pressBackBtn();
	}
	
	public void pressHomeBtn(){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
		
	}
	
	public void pressBackBtn(){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
	}
	
	public void gotoRoomPage(){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]")).click();
	}
	
	public void gotoTopic(){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[@name = '" + strPostFotComment + "'][1]")).click();
//		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]")).click();
	}
	
	public void gotoLike() {
		gotoTopic();
		pressLikeBtn();
		pressBackBtn();
	}
	
	public void pressLikeBtn(){
		driver.findElement(By.id("LIKE")).click();
	}
	
	public void gotoSettingAvatar(){
		driver.findElement(By.id("ic settings")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]")).click();
	}
	
	public void selectAvatar(String typeAvatar){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAButton[1]")).click();
		driver.findElement(By.id(typeAvatar)).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIACollectionView[1]/UIACollectionCell[7]")).click();
		driver.findElement(By.id("select")).click();
	}
	
	public void changeNameAvatar(String avatarName){
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]")).click();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")).clear();
		driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")).sendKeys(avatarName);
		driver.findElement(By.id("Save")).click();
	}
}
