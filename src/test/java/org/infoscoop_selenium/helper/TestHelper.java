package org.infoscoop_selenium.helper;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.Wait;

public class TestHelper {
	private static int WAIT_SECOND = 5;

	/**
	 * 要素の出現を待つ
	 * @param context
	 * @param by
	 */
	public static void waitPresent(final SearchContext context, final By by) {
		Wait wait = new Wait() {
			@Override
			public boolean until() {
				try {
					WebElement elem = context.findElement(by);
					return elem.isDisplayed();
				} catch (NoSuchElementException e) {
					return false;
				}
			}
		};

		wait.wait("Element not exists", WAIT_SECOND * 1000);
	}
	
	/**
	 * 要素が消えるのを待つ
	 * @param context
	 * @param by
	 */
	public static void waitInvisible(final SearchContext context, final By by) {
		Wait wait = new Wait() {
			@Override
			public boolean until() {
				WebElement elem = context.findElement(by);
				return false == elem.isDisplayed();
			}
		};
		wait.wait("Element exists", WAIT_SECOND * 1000);
	}
	
	/**
	 * 別ウィンドウを開く
	 * @return webdriver
	 * @param fileName
	 * @param driver
	 */
	public static WebDriver getNewWindowDriver(final WebDriver driver, final String currentWindowId) {
	    // ウィンドウ表示までに時間がかかると、seleniumが先走ることがあるのでウィンドウが増えるまで待機。
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getWindowHandles().size() > 1;
            }
        });
        
        String newWindowId = null;
        for (String id : driver.getWindowHandles()) {
        	if (!id.equals(currentWindowId)) {
        		newWindowId = id;
        	}
        }
        
        return driver.switchTo().window(newWindowId);
	}	
	
	/**
	 * 別ウィンドウを開く
	 * @return webdriver
	 * @param fileName
	 * @param driver
	 */
	public static WebDriver getCurrentWindowDriver(final WebDriver driver, final String currentWindowId) {
		driver.close();
        return driver.switchTo().window(currentWindowId);
	}
	
	/**
	 * スクリーンショットの撮影
	 * @param fileName
	 * @param driver
	 */
	public static void getScreenShot(final String fileName, final WebDriver driver) {
		try{
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(driver.getClass().getName() + File.separator + fileName + ".png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
