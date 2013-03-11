package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.gadget.GenericGadget;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


/**
 * 電卓ガジェットのスクリーンショット
 * @author mikami
 *
 */
public class CalculatorGadgetScreenShot extends IS_BaseItTestCase {
	private static GenericGadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// ガジェットのドロップ
		GADGET = (GenericGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_calculator", 1);
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
	}
	
	@Test
	/**
	 * 電卓ガジェット
	 */
	public void 電卓ガジェット(){
		WebDriver driver = getDriver();
		
		// ガジェットの表示を待つ
		TestHelper.switchToFrame(driver, "ifrm_"+GADGET.getId());
		TestHelper.backToTopFrame(driver);
		
		TestHelper.getScreenShot("電卓ガジェット", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * 電卓ガジェット（ガジェットメニュー）
	 */
	public void 電卓ガジェット_ガジェットメニュー(){
		WebDriver driver = getDriver();
		
		// ガジェットメニューを開く
		GADGET.openMenu();
		
		TestHelper.getScreenShot("電卓ガジェット（ガジェットメニュー）", driver);
	
		assertTrue(true);
	}
	
}
