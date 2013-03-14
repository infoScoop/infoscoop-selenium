package org.infoscoop_selenium.screenshot;


import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.AdminPage;
import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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
		
		// メッセージコンソールを開く
		WebDriver messageConsole = getPortal().openMessageConsole();
		
		// 別画面を開く
        TestHelper.waitPresent(messageConsole, By.id("div_message_mssage_1"));
        
		TestHelper.getScreenShot("メッセージコンソール（ポータル画面）", driver);
		
		// 別画面を閉じる
		getPortal().closeMessageConsole();
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * メッセージコンソール（管理画面）
	 */
	public void メッセージコンソール_管理画面(){
		WebDriver driver = getDriver();
		
		// 管理画面に移動
		AdminPage adminPage = getPortal().openAdminPage();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("msg.info('aaaa');"); 
		js.executeScript("msg.debug('bbbb');"); 
		js.executeScript("msg.warn('cccccccccccccccccccccccccc ccccccccccccccccccccccccccccccccccccccccccccccccccccccccc');"); 
		js.executeScript("msg.error('ddddddddddddddddddddddddddd dddddddddddddddddddddddddddddddddddddddddddddddddddd');"); 
        
		// メッセージアイコンコンソールを開く
		adminPage.openMessageConsole();
        
        TestHelper.waitPresent(driver, By.id("div_message_mssage_1"));
        
		TestHelper.getScreenShot("メッセージコンソール（管理画面）", driver);
		
		adminPage.closeMessageConsole();

		assertTrue(true);
	}
	
}
