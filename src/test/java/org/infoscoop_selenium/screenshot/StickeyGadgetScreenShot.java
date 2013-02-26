package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


/**
 * 付箋ガジェットのスクリーンショット
 * @author mikami
 *
 */
public class StickeyGadgetScreenShot extends IS_BaseItTestCase {
	private static String WIDGET_ID;	
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
	}
	
	@Test
	/**
	 * 付箋ガジェット
	 */
	public void 付箋ガジェット(){
		WebDriver driver = getDriver();		
		
		// login
		getPortal().login("test_user2", "password");

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();

		// ガジェットのドロップ
		WIDGET_ID = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_stickey", 1);

		// ガジェットの表示を待つ
		TestHelper.switchToFrame(driver, "ifrm_"+WIDGET_ID);
		TestHelper.backToTopFrame(driver);
		
		TestHelper.getScreenShot("付箋ガジェット", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * 付箋ガジェット（ガジェットメニュー）
	 */
	public void 標準時時計ガジェット_ガジェットメニュー(){
		WebDriver driver = getDriver();
		
		// ガジェットメニューを開く
		getPortal().getGadget().openMenu(WIDGET_ID);
		
		TestHelper.getScreenShot("付箋ガジェット（ガジェットメニュー）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * 付箋ガジェット（ガジェット設定）
	 */
	public void 付箋ガジェット_ガジェット設定(){
		WebDriver driver = getDriver();
		
		// ガジェット設定を開く
		getPortal().getGadget().getGadgetPreference().show(WIDGET_ID);
		
		TestHelper.getScreenShot("付箋ガジェット（ガジェット設定）", driver);
		
		// ガジェット設定を閉じる
		getPortal().getGadget().getGadgetPreference().cancel(WIDGET_ID);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * 付箋ガジェット（dynamic_height）
	 */
	public void 付箋ガジェット_dynamic_height(){
		WebDriver driver = getDriver();
		
		// 付箋ガジェットに値を代入
		TestHelper.switchToFrame(driver, "ifrm_"+WIDGET_ID);
		driver.findElement(By.id("editor")).sendKeys(Keys.RETURN);
		driver.findElement(By.id("editor")).sendKeys("hoge");
		driver.findElement(By.id("editor")).sendKeys(Keys.RETURN);
		driver.findElement(By.id("editor")).sendKeys("huga");
		driver.findElement(By.id("editor")).sendKeys(Keys.RETURN);
		driver.findElement(By.id("editor")).sendKeys("piyo");
		driver.findElement(By.id("editor")).sendKeys(Keys.RETURN);
		driver.findElement(By.id("editor")).sendKeys("null");
		driver.findElement(By.id("editor")).sendKeys(Keys.RETURN);
		TestHelper.getScreenShot("付箋ガジェット（dynamic_height）", driver);
		
		assertTrue(true);
	}
	
	private static void sleep(long sleep){
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
