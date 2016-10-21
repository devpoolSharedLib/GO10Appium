package th.co.gosoft.appiumAndroid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingAvatarPage extends BasePage {

	By btnAvatarPic = By.id(app_package_name+"imgProfileImage");
	By txtAvatarName = By.id(app_package_name + "txtAvatarName");
	By listAvatarName = By.xpath("//android.widget.ListView[contains(@resource-id, 'settingListview')]/android.widget.LinearLayout");
	By upBtnBack = By.xpath("//android.view.ViewGroup[contains(@resource-id, 'action_bar')]/android.widget.ImageButton");
	
	public SettingAvatarPage(WebDriver driver) {
		super(driver);
	}
	
	public SelectAvatarPicPage pressAvatarImage(){
		waitForVisibilityOf(btnAvatarPic);
		driver.findElement(btnAvatarPic).click();
		return new SelectAvatarPicPage(driver);
	}
	
	public SettingAvatarNamePage pressAvatarName(){
		waitForVisibilityOf(listAvatarName);
		driver.findElement(listAvatarName).click();
		return new SettingAvatarNamePage(driver);
	}
	
	public String getAvatarName(){
		waitForVisibilityOf(txtAvatarName);
		return driver.findElement(txtAvatarName).getText();
	}
	
	public void pressBack(){
		waitForVisibilityOf(upBtnBack);
		driver.findElement(upBtnBack).click();
	}
	
}
