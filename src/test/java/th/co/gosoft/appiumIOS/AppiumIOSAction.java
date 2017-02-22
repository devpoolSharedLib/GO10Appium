package th.co.gosoft.appiumIOS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppiumIOSAction {
	private  WebDriver driver;
	
	AppiumIOSAction(WebDriver driver){
		this.driver = driver;
	}
	
	public  void loginAction(){
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[1]")).clear();
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATextField[1]")).sendKeys("appium@gosoft.co.th");
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIASecureTextField[1]")).clear();
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIASecureTextField[1]")).sendKeys("password");
		this.driver.findElement(By.id("Login")).click();	
	}
	
	public void logoutAction(){
		this.driver.findElement(By.id("ic settings")).click();
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[3]")).click();	
	}
	
	public void quitAppiumAction(){
		this.driver.quit();
	}
	
	public void gotoRoomPage(){
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]")).click();
	}
	
	public  void postTopic(String strPost,String strContent){
		this.driver.findElement(By.id("Compose")).click();
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextView[2]")).clear();
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextView[2]")).sendKeys(strPost);
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAScrollView[1]/UIAWebView[1]")).clear();
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAScrollView[1]/UIAWebView[1]")).sendKeys(strContent);
		this.driver.findElement(By.id("ic send")).click();
	}
	
	public void postComment(String strComment){
		this.driver.findElement(By.id("COMMENT")).click();
		this.driver.findElement(By.className("UIAWebView")).clear();
		this.driver.findElement(By.className("UIAWebView")).sendKeys(strComment);
		this.driver.findElement(By.id("ic send")).click();
	}
	
	public  void gotoLikeTopic(String strPostForComment) {
		gotoTopic(strPostForComment);
		pressLikeBtnAction();
		pressBackBtnActoin();
	}
	
	public  void gotoTopic(String strPostForComment){
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[@name = '" + strPostForComment + "'][1]")).click();
	}
	
	public  void gotoSettingAvatar(){
		this.driver.findElement(By.id("ic settings")).click();
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]")).click();
	}
	
	public  void selectAvatar(String typeAvatar){
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIAButton[1]")).click();
		this.driver.findElement(By.id(typeAvatar)).click();
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIACollectionView[1]/UIACollectionCell[7]")).click();
		this.driver.findElement(By.id("select")).click();
	}
	
	public void changeNameAvatar(String avatarName){
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]")).click();
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")).clear();
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")).sendKeys(avatarName);
		this.driver.findElement(By.id("Save")).click();
	}
	
	public void pressAllowBtnAction(){
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[7]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[2]")).click();
	}
	
	public  void pressBackBtnActoin(){
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")).click();
	}
	
	public  void pressHomeBtnAction(){
		this.driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]")).click();
	}
	
	public  void pressLikeBtnAction(){
		this.driver.findElement(By.id("LIKE")).click();
	}
	

}
