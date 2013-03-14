package org.infoscoop_selenium.helper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.infoscoop_selenium.properties.TestEnv;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
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
	/*
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
	*/
	
	/**
	 * 別ウィンドウを閉じる
	 * @return webdriver
	 * @param fileName
	 * @param driver
	 */
	/*
	public static WebDriver getCurrentWindowDriver(final WebDriver driver, final String currentWindowId) {
		driver.close();
        return driver.switchTo().window(currentWindowId);
	}
	*/

	/**
	 * フレームを移動する
	 * @return webdriver
	 * @param frameName
	 * @param driver
	 */
	public static WebDriver switchToFrame(final WebDriver driver, final String frameName) {
		driver.switchTo().frame(frameName);
		TestHelper.waitPresent(driver, By.tagName("body"));
        return driver;
	}

	/**
	 * トップフレームを移動する
	 * @return webdriver
	 * @param fileName
	 * @param driver
	 */
	public static WebDriver backToTopFrame(final WebDriver driver) {
		driver.switchTo().defaultContent();
        return driver;
	}
	
	/**
	 * スクリーンショットの撮影
	 * @param fileName
	 * @param driver
	 */
	public static void getScreenShot(final String fileName, final WebDriver driver) {
		try{
			WebDriver augmentedDriver = driver;
			if((augmentedDriver.toString()).indexOf("RemoteWebDriver") > -1)
				augmentedDriver = new Augmenter().augment(driver);
			File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(TestEnv.getInstance().getBrowser() + File.separator + fileName + ".png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Confirmのスクリーンショット
	 * @param fileName
	 * @param driver
	 */
	public static void getScreenShotConfirm(final String fileName, final WebDriver driver, int width, int height) {
		try{
			Robot robot = new Robot();
			Image img = robot.createScreenCapture(new Rectangle(0, 0, width, height));
			
			BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
			Graphics g = bimg.getGraphics();
			g.drawImage(img, 0, 0, null);
			g.dispose();
			if (!ImageIO.write(bimg, "png", new File(TestEnv.getInstance().getBrowser() + File.separator + fileName + ".png"))) {
				throw new Exception("フォーマットが対象外");
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}

	}
}
