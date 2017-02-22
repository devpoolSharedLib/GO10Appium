package th.co.gosoft.appiumAndroid.scenarios;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import th.co.gosoft.appiumAndroid.pages.BasePage;
import th.co.gosoft.appiumAndroid.pages.BoardContentPage;
import th.co.gosoft.appiumAndroid.pages.LoginPage;
import th.co.gosoft.appiumAndroid.pages.RoomPage;
import th.co.gosoft.appiumAndroid.pages.SettingAvatarNamePage;
import th.co.gosoft.appiumAndroid.pages.SettingAvatarPage;
import th.co.gosoft.util.EnvironmentUtil;

public class AppiumTest extends AndroidSetup {
	
	private static final int ROOM_INDEX = 0;
	
	@BeforeClass
    public static void setUp() throws Exception {
		EnvironmentUtil.deleteTopic();
		EnvironmentUtil.createTopic();
		EnvironmentUtil.deleteLike();
		prepareAndroidForAppium();
    }

	@Test
//	@Ignore
	public void postNewTopicTest() {
		new LoginPage(driver)
			.userLogin()
			.selectRoom(ROOM_INDEX)
			.pressNewTopicButton()
			.writeSubjectAndContent();
		
		assertEquals(BoardContentPage.HOST_SUBJECT, new BoardContentPage(driver).getHostSubject());
		assertEquals(BoardContentPage.HOST_CONTENT, new BoardContentPage(driver).getHostContent());
		assertEquals(BoardContentPage.HOST_SUBJECT, new BoardContentPage(driver).pressHomeBtn().getSubjectFromTopicList(0));
	}
	
//	@Test
	@Ignore
	public void commentTopicTest() {
		String toppicSubject = "Post for Test Comment and Like Automate";
		new LoginPage(driver)
			.userLogin()
			.selectRoom(ROOM_INDEX)
			.selectTopicBySubject(toppicSubject)
			.pressCommentBtn()
			.writeComment();
		
		assertEquals(BoardContentPage.COMMENT_CONTENT, new BoardContentPage(driver).getCommentContent());
		assertEquals(toppicSubject, new BoardContentPage(driver).pressHomeBtn().getSubjectFromTopicList(0));
	}
	
//	@Test
	@Ignore
	public void likeTopicTest() throws InterruptedException {
		String toppicSubject = "Post for Test Comment and Like Automate";
		new LoginPage(driver)
			.userLogin()
			.selectRoom(ROOM_INDEX)
			.selectTopicBySubject(toppicSubject);
		
		assertEquals("0", new BoardContentPage(driver).getLikeCount());
		
		assertEquals("1", new BoardContentPage(driver).pressLikeBtn().getLikeCount());
		driver.navigate().back();
		assertEquals("1", new RoomPage(driver).getLikeCount(toppicSubject));

		assertEquals("0", new RoomPage(driver).selectTopicBySubject(toppicSubject).pressLikeBtn().getLikeCount());
		driver.navigate().back();
		assertEquals("0", new RoomPage(driver).getLikeCount(toppicSubject));
		
		assertEquals("1", new RoomPage(driver).selectTopicBySubject(toppicSubject).pressLikeBtn().getLikeCount());
		driver.navigate().back();
		assertEquals("1", new RoomPage(driver).getLikeCount(toppicSubject));
	}
	
//	@Test
	@Ignore
	public void changeAvatarPicTest() {
		new LoginPage(driver)
			.userLogin()
			.pressSettingAvatar()
			.pressAvatarImage()
			.selectAvatarPicWoman6()
			.pressAvatarImage()
			.selectAvatarPicMan6();
		
		new SettingAvatarPage(driver).pressBack();
	}
	
//	@Test
	@Ignore
	public void changeAvatarNameTest() {
		new LoginPage(driver)
			.userLogin()
			.pressSettingAvatar()
			.pressAvatarName()
			.inputNewAvatarName();
		assertEquals(SettingAvatarNamePage.NEW_NAME, new SettingAvatarPage(driver).getAvatarName());
		
		new SettingAvatarPage(driver)
			.pressAvatarName()
			.inputDefaultAvatarName();
		assertEquals(SettingAvatarNamePage.DEFAULT_NAME, new SettingAvatarPage(driver).getAvatarName());
		
		new SettingAvatarPage(driver).pressBack();
	}
	
	@After
	public void logout() {
		new BasePage(driver).logout();
	}
	
	@AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

}
