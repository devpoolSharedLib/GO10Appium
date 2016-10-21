package th.co.gosoft.appiumAndroid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RoomPage extends BasePage {
	
	By btnNewTopic = By.id(app_package_name+"btnNewTopic");
	By listViewTopic = By.id(app_package_name+"listViewTopic");

	public RoomPage(WebDriver driver) {
		super(driver);
	}
	
	public WritingTopicPage pressNewTopicButton() {
		waitForClickabilityOf(btnNewTopic);
		driver.findElement(btnNewTopic).click();
		return new WritingTopicPage(driver);
	}
	
	public BoardContentPage selectTopicBySubject(String subject) {
		waitForClickabilityOf(listViewTopic);
		driver.findElement(By.xpath("//android.widget.ListView[contains(@resource-id,'listViewTopic')]/android.widget.RelativeLayout/android.widget.TextView[contains(@resource-id,'rowSubject') and @text='"+subject+"']")).click();
		return new BoardContentPage(driver);
	}
	
	public String getLikeCount(String subject) {
		By testTopicBy = By.xpath("//android.widget.ListView[contains(@resource-id,'listViewTopic')]/android.widget.RelativeLayout[./android.widget.TextView[@text='"+subject+"']]");
		waitForClickabilityOf(testTopicBy);
		WebElement element = driver.findElement(testTopicBy);
		return element.findElement(By.id(app_package_name + "txtLikeCount")).getText();
	}

}
