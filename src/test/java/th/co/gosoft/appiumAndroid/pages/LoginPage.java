package th.co.gosoft.appiumAndroid.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchShortcuts;

public class LoginPage extends BasePage {
	
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	
    By userId = By.id(app_package_name + "txtEmail");
    By password = By.id(app_package_name + "txtPassword");
    By btnLogin = By.id(app_package_name + "btnLogin");
    By linearRoom = By.id(app_package_name+"linearRoom");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public SelectRoomPage userLogin() {
        waitForVisibilityOf(userId);
        driver.findElement(userId).sendKeys("appium@gosoft.co.th");
        driver.findElement(password).sendKeys("password");
        driver.findElement(btnLogin).click();
        return new SelectRoomPage(driver);
    }
    
    public RoomPage scrollToContractUs(){
    	waitForVisibilityOf(linearRoom);
        List<WebElement> roomWebElementList = driver.findElements(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'linearRoom')]/android.widget.LinearLayout"));
        
        startX = roomWebElementList.get(0).getLocation().getX() + (roomWebElementList.get(0).getSize().getWidth() / 2);
        startY = roomWebElementList.get(0).getLocation().getY() + (roomWebElementList.get(0).getSize().getHeight() / 2);
        endX = roomWebElementList.get(3).getLocation().getX() + (roomWebElementList.get(3).getSize().getWidth() / 2);
        endY = roomWebElementList.get(3).getLocation().getY() + (roomWebElementList.get(3).getSize().getHeight() / 2);
        
        testHorizontalScroll();
    	return new RoomPage(driver);
    }
    
    private void testHorizontalScroll() {
    	try {
    		for(int i=0;i<4;i++) {
                Thread.sleep(2000);
                if (!driver.findElements(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'linearRoom')]/android.widget.LinearLayout[@index='9']")).isEmpty()) {
                	driver.findElement(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'linearRoom')]/android.widget.LinearLayout[@index='9']")).click();
                    break;
                } else {
                    horizontalScroll();
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
    }
    
    public void horizontalScroll() {
        ((TouchShortcuts) driver).swipe(endX, startY, startX, endY, 400);
    }
}
