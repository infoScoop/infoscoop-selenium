package org.infoscoop_selenium.screenshot;


import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * メッセージコンソールのスクリーンショット
 * @author mikami
 *
 */
public class MessageConsoleScreenShot extends IS_BaseItTestCase{
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();
	}

	@Test
	/**
	 * メッセージコンソール（ポータル画面）
	 */
	public void メッセージコンソール_ポータル画面(){
		WebDriver driver = getDriver();
		
		// 現在表示しているWindowIDを取得する
		String currentWindowId = driver.getWindowHandle();
		
		// メッセージアイコンをクリック
		driver.findElement(By.id("messageIcon")).click();

		// 別画面を開く
        driver = TestHelper.getNewWindowDriver(driver, currentWindowId);
        TestHelper.waitPresent(driver, By.id("div_message_mssage_1"));
        
		TestHelper.getScreenShot("メッセージコンソール（ポータル画面）", driver);
		
		// 別画面を閉じる
		TestHelper.getCurrentWindowDriver(driver, currentWindowId);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * メッセージコンソール（管理画面）
	 */
	public void メッセージコンソール_管理画面(){
		WebDriver driver = getDriver();
		
		// 現在表示しているWindowIDを取得する
		String currentWindowId = driver.getWindowHandle();
		
		// ユーザーメニューを開く
		getPortal().getCommandBar().openMenu();
		
		// 管理画面に移動
		driver.findElement(By.id("admin-link")).click();
        driver = TestHelper.getNewWindowDriver(driver, currentWindowId);
        TestHelper.waitPresent(driver, By.id("messageIcon"));
        
		// confirmの抜け方がわからないので無理やり
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("msg.info('aaaa');"); 
		js.executeScript("msg.debug('bbbb');"); 
		js.executeScript("msg.warn('cccccccccccccccccccccccccc ccccccccccccccccccccccccccccccccccccccccccccccccccccccccc');"); 
		js.executeScript("msg.error('ddddddddddddddddddddddddddd dddddddddddddddddddddddddddddddddddddddddddddddddddd');"); 
        
		// メッセージアイコンをクリック
		driver.findElement(By.id("messageIcon")).click();
        
		// 別画面を開く
        driver = TestHelper.getNewWindowDriver(driver, driver.getWindowHandle());
        TestHelper.waitPresent(driver, By.id("div_message_mssage_1"));
        
		TestHelper.getScreenShot("メッセージコンソール（管理画面）", driver);

		assertTrue(true);
	}
	
}
