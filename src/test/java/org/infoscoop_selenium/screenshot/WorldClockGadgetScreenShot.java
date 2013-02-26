package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


/**
 * 標準時時計ガジェットのスクリーンショット
 * @author mikami
 *
 */
public class WorldClockGadgetScreenShot extends IS_BaseItTestCase {
	private static Gadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login("test_user2", "password");

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// ガジェットのドロップ
		GADGET = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_worldclock", 1);
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
	}
	
	@Test
	/**
	 * 標準時時計ガジェット
	 */
	public void 標準時時計ガジェット(){
		WebDriver driver = getDriver();		

		// ガジェットの表示を待つ
		TestHelper.switchToFrame(driver, "ifrm_"+GADGET.getId());
		TestHelper.backToTopFrame(driver);
		
		TestHelper.getScreenShot("標準時時計ガジェット", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * 標準時時計ガジェット（ガジェットメニュー）
	 */
	public void 標準時時計ガジェット_ガジェットメニュー(){
		WebDriver driver = getDriver();
		
		// ガジェットメニューを開く
		GADGET.openMenu();
		
		TestHelper.getScreenShot("標準時時計ガジェット（ガジェットメニュー）", driver);
	
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
