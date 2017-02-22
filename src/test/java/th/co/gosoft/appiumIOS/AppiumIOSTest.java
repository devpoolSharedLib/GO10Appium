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
import th.co.gosoft.appiumIOS.AppiumIOSAction;

public class AppiumIOSTest {
	private static  WebDriver driver;
	static AppiumIOSAction appiumIosAction;
	
	private static DesiredCapabilities cap;
	private String STR_POST = "IOS New Topic";
	private String STR_CONTENT = "Appium IOS New Content";
	private String STR_COMMENT = "Appium IOS New Comment";
	private String STR_POST_FOR_COMMENT = "Post for Test Comment and Like Automate";
	private String DEFAULT_NAME_AVATAR = "DefaultAppium";
	private String NAME_AVATAR = "NameAppium";
	private String AVATAR_TYPE_WOMAN = "woman";
	private String AVATAR_TYPE_MAN = "man";
	
	@BeforeClass
	public static void setEnvironment(){
		EnvironmentUtil.deleteTopic();
		EnvironmentUtil.resetTotalTopic();
		EnvironmentUtil.createTopic();
		EnvironmentUtil.deleteLike();
		EnvironmentUtil.deleteRead();
	}
	
	@BeforeClass
	public static void setupAppium() throws MalformedURLException{
		File app = new File("src/test/resources/GO10-1.0.4.app");
		cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
		cap.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		appiumIosAction = new AppiumIOSAction(driver);
		
		appiumIosAction.loginAction();
		appiumIosAction.pressAllowBtnAction();
		appiumIosAction.logoutAction();
		
	}
	
	@Before
	public void login() throws MalformedURLException {
		appiumIosAction.loginAction();
	}
	
////	@Test
//	public void hotTopicList() throws MalformedURLException, InterruptedException{
//		List<WebElement> hotTopicElements = driver.findElements(By.className("UIATableCell"));
//		List<WebElement> roomElements = driver.findElements(By.className("UIACollectionCell"));
//		assertEquals(12, hotTopicElements.size());
//		assertEquals(10, roomElements.size());
//	}
	
//	@Test 
	public void newPost(){
		appiumIosAction.gotoRoomPage();
		appiumIosAction.postTopic(STR_POST, STR_CONTENT);
		assertEquals(STR_POST,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).getAttribute("name"));	
		appiumIosAction.pressBackBtnActoin();
		assertEquals(STR_POST,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).getAttribute("name"));
	}	
	
//	@Test
	public void newComment(){
		appiumIosAction.gotoRoomPage();
		appiumIosAction.gotoTopic(STR_POST_FOR_COMMENT);
		appiumIosAction.postComment(STR_COMMENT);
		assertEquals(STR_COMMENT,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[3]")).getAttribute("name"));	
		
		//check news feed
		appiumIosAction.pressHomeBtnAction();
		assertEquals(STR_POST_FOR_COMMENT,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).getAttribute("name"));
	}
	
	@Test
	public void Like(){
		appiumIosAction.gotoRoomPage();
		appiumIosAction.gotoLikeTopic(STR_POST_FOR_COMMENT);
		assertEquals(1,Integer.parseInt(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[@name = '" + STR_POST_FOR_COMMENT + "'][1]/UIAStaticText[2]")).getAttribute("value")));

		appiumIosAction.gotoLikeTopic(STR_POST_FOR_COMMENT);
		assertEquals(0,Integer.parseInt(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[@name = '" + STR_POST_FOR_COMMENT + "'][1]/UIAStaticText[2]")).getAttribute("value")));

		appiumIosAction.gotoLikeTopic(STR_POST_FOR_COMMENT);
		assertEquals(1,Integer.parseInt(driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[@name = '" + STR_POST_FOR_COMMENT + "'][1]/UIAStaticText[2]")).getAttribute("value")));
	}

//	@Test
	public void changeAvatarPic(){
		appiumIosAction.gotoSettingAvatar();
		appiumIosAction.selectAvatar(AVATAR_TYPE_WOMAN);
		assertEquals("girl07",driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAButton[1]")).getAttribute("name"));
		
		appiumIosAction.selectAvatar(AVATAR_TYPE_MAN);
		assertEquals("man07",driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAButton[1]")).getAttribute("name"));
		
		appiumIosAction.pressBackBtnActoin();
		appiumIosAction.pressBackBtnActoin();
	}
	
//	@Test
	public void changeAvatarName(){
		appiumIosAction.gotoSettingAvatar();
		appiumIosAction.changeNameAvatar(NAME_AVATAR);
		assertEquals(NAME_AVATAR,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]")).getAttribute("name"));
		
		appiumIosAction.changeNameAvatar(DEFAULT_NAME_AVATAR);
		assertEquals(DEFAULT_NAME_AVATAR,driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]")).getAttribute("name"));
		
		appiumIosAction.pressBackBtnActoin();
		appiumIosAction.pressBackBtnActoin();
	}
	
	@After
	public void logout(){
		appiumIosAction.logoutAction();
	}
	
	@AfterClass
	public static void quit(){
		appiumIosAction.quitAppiumAction();
	}
}
