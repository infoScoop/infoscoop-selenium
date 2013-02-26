package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.StickyGadget;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


/**
 * 付箋ガジェットのスクリーンショット
 * @author mikami
 *
 */
public class StickyGadgetScreenShot extends IS_BaseItTestCase {
	private static StickyGadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login("test_user2", "password");

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// ガジェットのドロップ
		GADGET = (StickyGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_stickey", 1, GADGET_TYPE.STICKY);
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

		// ガジェットの表示を待つ
		TestHelper.switchToFrame(driver, "ifrm_"+GADGET.getId());
		TestHelper.backToTopFrame(driver);
		
		TestHelper.getScreenShot("付箋ガジェット", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * 付箋ガジェット（ガジェットメニュー）
	 */
	public void 付箋ガジェット_ガジェットメニュー(){
		WebDriver driver = getDriver();
		
		// ガジェットメニューを開く
		GADGET.openMenu();
		
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
		GADGET.getGadgetPreference().show();
		
		TestHelper.getScreenShot("付箋ガジェット（ガジェット設定）", driver);
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().cancel();
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * 付箋ガジェット（dynamic_height）
	 */
	public void 付箋ガジェット_dynamic_height(){
		WebDriver driver = getDriver();
		
		// 付箋ガジェットに値を代入
		GADGET.writeSticky("");
		GADGET.writeSticky("hoge");
		GADGET.writeSticky("huga");
		GADGET.writeSticky("piyo");
		GADGET.writeSticky("null");

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
