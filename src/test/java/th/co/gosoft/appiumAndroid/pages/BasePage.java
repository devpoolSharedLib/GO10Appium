package th.co.gosoft.appiumAndroid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

/**
 * Created by nishant on 13/09/14.
 */
public class BasePage {

    public String app_package_name = "th.co.gosoft.go10:id/";
    
    protected WebDriver driver;
    protected By drawer = By.xpath("//android.view.ViewGroup[contains(@resource-id,'toolbar')]/android.widget.ImageButton[@content-desc='Open navigation drawer']");
    protected By btnHome = By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'design_navigation_view')]/android.support.v7.widget.LinearLayoutCompat[@index='1']");
    protected By btnSettingAvatar = By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'design_navigation_view')]/android.support.v7.widget.LinearLayoutCompat[@index='2']");
    protected By btnLogout = By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'design_navigation_view')]/android.support.v7.widget.LinearLayoutCompat[@index='4']");
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void waitForVisibilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected void waitForClickabilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public SelectRoomPage pressHomeBtn() {
    	waitForClickabilityOf(drawer);
    	driver.findElement(drawer).click();
    	waitForClickabilityOf(btnHome);
    	driver.findElement(btnHome).click();
    	return new SelectRoomPage(driver);
    }
    
    public SettingAvatarPage pressSettingAvatar() {
    	waitForClickabilityOf(drawer);
    	driver.findElement(drawer).click();
    	waitForClickabilityOf(btnSettingAvatar);
    	driver.findElement(btnSettingAvatar).click();
    	return new SettingAvatarPage(driver);
    }
    
    public void logout() {
    	waitForClickabilityOf(drawer);
    	driver.findElement(drawer).click();
    	waitForClickabilityOf(btnLogout);
    	driver.findElement(btnLogout).click();
    }

    public void scrollPageUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.50);
        swipeObject.put("startY", 0.95);
        swipeObject.put("endX", 0.50);
        swipeObject.put("endY", 0.01);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }


    public void swipeLeftToRight() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.01);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.9);
        swipeObject.put("endY", 0.6);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipeRightToLeft() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.5);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void swipeFirstCarouselFromRightToLeft() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.2);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.2);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    public void performTapAction(WebElement elementToTap) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("x", (double) 360); // in pixels from left
        tapObject.put("y", (double) 170); // in pixels from top
        tapObject.put("element", Double.valueOf(((RemoteWebElement) elementToTap).getId()));
        js.executeScript("mobile: tap", tapObject);
    }
}
