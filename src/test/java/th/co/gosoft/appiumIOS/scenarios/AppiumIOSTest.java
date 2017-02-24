package th.co.gosoft.appiumIOS.scenarios;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import th.co.gosoft.util.EnvironmentUtil;

public class AppiumIOSTest extends AppiumIOSPages {
	
	private String STR_POST = "IOS New Topic";
	private String STR_CONTENT = "Appium IOS New Content";
	private String STR_COMMENT = "Appium IOS New Comment";
	private String STR_POST_FOR_COMMENT = "Post for Test Comment and Like Automate";
	private String DEFAULT_NAME_AVATAR = "DefaultAppium";
	private String NAME_AVATAR = "NameAppium";
	private String AVATAR_TYPE_WOMAN = "woman";
	private String AVATAR_TYPE_MAN = "man";
	private String STR_POST_FOR_DELETE = "host for delete";
	private String STR_CONTENT_FOR_DELETE = "conten for delete";
	private String STR_COMMENT_FOR_DELETE = "comment for delete";
	private String STR_COMMENT_IN_DELETE = "comment in delete";
	
	@BeforeClass
	public static void setEnvironment() throws MalformedURLException{
		EnvironmentUtil.deleteTopic();
		EnvironmentUtil.resetTotalTopic();
		EnvironmentUtil.createTopic();
		EnvironmentUtil.deleteLike();
		EnvironmentUtil.deleteRead();
		prepareIOSForAppium();
	}
	
	@Before
	public void login() {
		loginAction();
	}
	
	@Test
	public void newPost(){
		gotoRoomPage();
		postTopic(STR_POST, STR_CONTENT);
		assertEquals(STR_POST,getTopicName("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]"));	
		pressBackBtnActoin();
		assertEquals(STR_POST,getTopicName("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
	}	
	
	@Test
	public void newComment(){
		gotoRoomPage();
		gotoTopic(STR_POST_FOR_COMMENT);
		postComment(STR_COMMENT);
		assertEquals(STR_COMMENT,getCommentName("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[3]"));	
		
		//check news feed
		pressHomeBtnAction();
		assertEquals(STR_POST_FOR_COMMENT,getTopicName("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]"));
	}
	
	@Test
	public void Like(){
		String pathToGetcountLike = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[@name = '" + STR_POST_FOR_COMMENT + "'][1]/UIAStaticText[2]";
		gotoRoomPage();
		gotoLikeTopic(STR_POST_FOR_COMMENT);
		assertEquals(1,getCountLike(pathToGetcountLike));

		gotoLikeTopic(STR_POST_FOR_COMMENT);
		assertEquals(0,getCountLike(pathToGetcountLike));
		
		gotoLikeTopic(STR_POST_FOR_COMMENT);
		assertEquals(1,getCountLike(pathToGetcountLike));

	}

	@Test
	public void changeAvatarPic(){
		String pathToGetPicAvatarName = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAButton[1]"; 
		gotoSettingAvatar();
		selectAvatar(AVATAR_TYPE_WOMAN);
		assertEquals("girl07",getPicAvatarName(pathToGetPicAvatarName));
		
		selectAvatar(AVATAR_TYPE_MAN);
		assertEquals("man07",getPicAvatarName(pathToGetPicAvatarName));
		
		pressBackBtnActoin();
		pressBackBtnActoin();
	}
	
	@Test
	public void changeAvatarName(){
		String pathToGetPicAvatarName = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[2]"; 
		gotoSettingAvatar();
		changeNameAvatar(NAME_AVATAR);
		assertEquals(NAME_AVATAR,getAvatarName(pathToGetPicAvatarName));
		
		changeNameAvatar(DEFAULT_NAME_AVATAR);
		assertEquals(DEFAULT_NAME_AVATAR,getAvatarName(pathToGetPicAvatarName));
		
		pressBackBtnActoin();
		pressBackBtnActoin();
	}
	
	@Test
	public void deleteTopic(){
		gotoRoomPage();
		postTopic(STR_POST_FOR_DELETE, STR_CONTENT_FOR_DELETE);
		gotoTopic(STR_POST_FOR_DELETE);
		postComment(STR_COMMENT_FOR_DELETE);
		pressBackBtnActoin();
		gotoTopic(STR_POST_FOR_DELETE);
		postComment(STR_COMMENT_IN_DELETE);
		deleteComment("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAButton[1]");
		assertEquals(STR_COMMENT_IN_DELETE,getCommentName("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[3]"));
		deletePost();
		pressBackBtnActoin();
	}
	
	@After
	public void logout(){
		logoutAction();
	}
	
	@AfterClass
	public static void quit(){
		quitAppiumAction();
	}
}
