package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


/**
 * ランキングガジェットのスクリーンショット
 * @author mikami
 *
 */
public class RankingGadgetScreenShot extends IS_BaseItTestCase {
	private static Gadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();

		// ガジェットのドロップ
		getPortal().getTopMenu().dropGadget("news", "news_asahi", 1);
		getPortal().getTopMenu().dropGadget("sports", "sports_dragons", 2);
		getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_stickey", 1);
		
		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// ガジェットのドロップ
		GADGET = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_WidgetRanking", 1);
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
	}
	
	@Test
	/**
	 * ランキングガジェット
	 */
	public void ランキングガジェット(){
		WebDriver driver = getDriver();		
		
		TestHelper.getScreenShot("ランキングガジェット", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * ランキングガジェット（ガジェットメニュー）
	 */
	public void ランキングガジェット_ガジェットメニュー(){
		WebDriver driver = getDriver();
		
		// ガジェットメニューを開く
		GADGET.openMenu();
		
		TestHelper.getScreenShot("ランキングガジェット（ガジェットメニュー）", driver);
	
		assertTrue(true);
	}
	
}
