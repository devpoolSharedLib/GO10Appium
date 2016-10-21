package th.co.gosoft.appiumAndroid.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardContentPage extends BasePage {
	
	public static final String HOST_SUBJECT = "Appium - Android New Topic";
    public static final String HOST_CONTENT = "Appium - android new content";
    public static final String COMMENT_CONTENT = "Appium - android new comment";
	    
	By commentListView = By.id(app_package_name + "commentListView"); 
	By hostSubject = By.id(app_package_name + "hostSubject");
	By hostContent = By.id(app_package_name + "hostContent");
	By btnComment = By.xpath("//android.widget.Button[contains(@resource-id, 'btnComment')]");
	By btnLike = By.id(app_package_name + "btnLike");
	By txtLikeCount = By.id(app_package_name + "txtLikeCount");
	
	public BoardContentPage(WebDriver driver) {
		super(driver);
	}
	
	public String getHostSubject(){
		waitForVisibilityOf(commentListView);
		return driver.findElement(hostSubject).getText();
	}
	
	public String getHostContent(){
		waitForVisibilityOf(commentListView);
		return driver.findElement(hostContent).getText();
	}
	
	public String getCommentContent(){
		String outputString;
		waitForVisibilityOf(commentListView);
		List<WebElement> webElementList = driver.findElements(By.xpath("//android.widget.ListView[contains(@resource-id, 'commentListView')]/android.widget.RelativeLayout"));
		if(webElementList.isEmpty()) {
			outputString = "Cannot find last comment.";
		} else {
			outputString = webElementList.get(webElementList.size()-1).findElement(By.id(app_package_name + "commentContent")).getText();
		}
		
		return outputString;
	}
	
	public WritingCommentPage pressCommentBtn(){
		waitForClickabilityOf(btnComment);
		driver.findElement(btnComment).click();
		return new WritingCommentPage(driver);
	}
	
	public BoardContentPage pressLikeBtn(){
		waitForClickabilityOf(btnLike);
		driver.findElement(btnLike).click();
		return new BoardContentPage(driver);
	}
	
	public String getLikeCount(){
		waitForClickabilityOf(txtLikeCount);
		return driver.findElement(txtLikeCount).getText();
	}

}
