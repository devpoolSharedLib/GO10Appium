package th.co.gosoft.appiumAndroid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WritingCommentPage extends BasePage {
	
	By txtCommentContent = By.xpath("//android.webkit.WebView/android.webkit.WebView/android.view.View");
	By btnPost = By.id(app_package_name + "btnPost");
	
	public WritingCommentPage(WebDriver driver) {
		super(driver);
	}

	public BoardContentPage writeComment(){
		waitForVisibilityOf(txtCommentContent);
		driver.findElement(txtCommentContent).sendKeys(BoardContentPage.COMMENT_CONTENT);
		driver.findElement(btnPost).click();
		return new BoardContentPage(driver);
	} 
}
