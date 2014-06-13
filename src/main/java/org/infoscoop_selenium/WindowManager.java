package org.infoscoop_selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowManager {
	private List<String> windowList = new ArrayList<String>();
	private String defaultWindowId;
	private WebDriver driver;
	private static WindowManager instance;
	
	private WindowManager(WebDriver driver) {
		this.driver = driver;
		this.defaultWindowId = driver.getWindowHandle();
		windowList.add(this.defaultWindowId);
	}
	
	public static void init(WebDriver driver){
		instance = new WindowManager(driver);
	}
	
	public static WindowManager getInstance(){
		return instance;
	}
	
	/**
	 * 対象の要素をクリックし、新規ウィンドウへスイッチする
	 * @param driver
	 * @return
	 */
	public WebDriver newWindow(WebElement target){
		target.click();
		return newWindow();
	}
	
	/**
	 * 新規ウィンドウへスイッチする
	 * @return
	 */
	private WebDriver newWindow(){
	    // ウィンドウ表示までに時間がかかると、seleniumが先走ることがあるのでウィンドウが増えるまで待機。
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getWindowHandles().size() > windowList.size();
            }
        });
        
        String newWindowId = null;
        for (String id : driver.getWindowHandles()) {
        	if (!windowList.contains(id)) {
        		newWindowId = id;
        		break;
        	}
        }
        
		windowList.add(newWindowId);
        return driver.switchTo().window(newWindowId);		
	}
	
	/**
	 * ウィンドウを閉じ、デフォルト画面にスイッチする
	 * @param windowHandle
	 */
	public void closeWindow(String windowHandle){
		windowList.remove(windowHandle);
		driver.switchTo().window(windowHandle).close();
		driver.switchTo().window(defaultWindowId);
	}
	
	/**
	 * 指定IDのウィンドウにスイッチする
	 * @param windowHandle
	 */
	public void switchWindow(String windowHandle){
		driver.switchTo().window(defaultWindowId);
	}

}
