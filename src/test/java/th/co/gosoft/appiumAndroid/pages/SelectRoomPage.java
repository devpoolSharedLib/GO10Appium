package th.co.gosoft.appiumAndroid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectRoomPage extends BasePage {
	
	By linearRoom = By.id(app_package_name+"linearRoom");
	By listViewSelectAvatar = By.id(app_package_name+"listViewSelectAvatar");
	
	public SelectRoomPage(WebDriver driver) {
		super(driver);
	}
	
	public RoomPage selectRoom(int roomIndex){
		waitForVisibilityOf(linearRoom);
		driver.findElement(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'linearRoom')]/android.widget.LinearLayout[@index='"+roomIndex+"']")).click();
		return new RoomPage(driver);
	}
	
	public String getSubjectFromTopicList(int roomIndex){
		waitForVisibilityOf(listViewSelectAvatar);
		return driver.findElement(By.xpath("//android.widget.ListView[contains(@resource-id,'listViewSelectAvatar')]/android.widget.RelativeLayout[@index='"+roomIndex+"']/android.widget.TextView[contains(@resource-id, 'rowSubject')]")).getText();
	}

}
