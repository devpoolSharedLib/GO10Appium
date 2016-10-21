package th.co.gosoft.appiumAndroid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;

public class SelectAvatarPicPage extends BasePage {
																															
	By tabMan = By.xpath("//android.widget.HorizontalScrollView[contains(@resource-id, 'tabs')]/android.widget.LinearLayout/android.support.v7.app.ActionBar$Tab[@index='0']");
	By tabWoman = By.xpath("//android.widget.HorizontalScrollView[contains(@resource-id, 'tabs')]/android.widget.LinearLayout/android.support.v7.app.ActionBar$Tab[@index='1']");
	By gridMan = By.id(app_package_name + "gridMan"); 
	By avatarManIndex6 = By.xpath("//android.widget.GridView[contains(@resource-id, 'gridMan')]/android.widget.LinearLayout[@index='6']");
	By avatarManIndex8 = By.xpath("//android.widget.GridView[contains(@resource-id, 'gridMan')]/android.widget.LinearLayout[@index='8']");
	By avatarWomanIndex6 = By.xpath("//android.widget.GridView[contains(@resource-id, 'gridWoman')]/android.widget.LinearLayout[@index='6']");
	By btnSave = By.id(app_package_name + "btnSaveSetting");
	
	public SelectAvatarPicPage(WebDriver driver) {
		super(driver);
	}
	
	public SettingAvatarPage selectAvatarPicMan6(){
		waitForVisibilityOf(gridMan);
		driver.findElement(avatarManIndex6).click();
		driver.findElement(btnSave).click();
		return new SettingAvatarPage(driver);
	}

	public SettingAvatarPage selectAvatarPicWoman6(){
		scrollPage();
		waitForVisibilityOf(avatarWomanIndex6);
		driver.findElement(avatarWomanIndex6).click();
		driver.findElement(btnSave).click();
		return new SettingAvatarPage(driver);
	}
	
	public void scrollPage(){
        int startX = driver.findElement(avatarManIndex8).getLocation().getX() + (driver.findElement(avatarManIndex8).getSize().getWidth());
        int startY = driver.findElement(avatarManIndex8).getLocation().getY() + (driver.findElement(avatarManIndex8).getSize().getHeight() / 2);
        int endX = driver.findElement(avatarManIndex6).getLocation().getX();
        int endY = driver.findElement(avatarManIndex6).getLocation().getY() + (driver.findElement(avatarManIndex6).getSize().getHeight() / 2);
        
		TouchAction touchAction = new TouchAction((MobileDriver) driver);
		touchAction.press(startX, startY).waitAction(2000).moveTo(endX, endY).release().perform();
	}
}
