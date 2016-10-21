package th.co.gosoft.appiumAndroid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingAvatarNamePage extends BasePage {
	
	public static final String DEFAULT_NAME = "DefaultAppium";
	public static final String NEW_NAME = "NameAppium";
	
	By edtAvatarName = By.id(app_package_name + "edtAvatarName");
	By btnSaveSetting = By.id(app_package_name + "btnSaveSetting");

	public SettingAvatarNamePage(WebDriver driver) {
		super(driver);
	}
	
	public SettingAvatarPage inputDefaultAvatarName(){
		waitForClickabilityOf(edtAvatarName);
		driver.findElement(edtAvatarName).sendKeys(DEFAULT_NAME);
		driver.findElement(btnSaveSetting).click();
		return new SettingAvatarPage(driver);
	}
	
	public SettingAvatarPage inputNewAvatarName(){
		waitForClickabilityOf(edtAvatarName);
		driver.findElement(edtAvatarName).sendKeys(NEW_NAME);
		driver.findElement(btnSaveSetting).click();
		return new SettingAvatarPage(driver);
	}
	
}
