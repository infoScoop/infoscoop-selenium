package org.infoscoop_selenium.screenshot;


import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.TopMenu;
import org.infoscoop_selenium.portal.commandbar.PortalPreference;
import org.junit.Test;

/**
 * タブUIのスクリーンショット
 * @author nishiumi
 *
 */
public class CommandbarScreenShot extends IS_BaseItTestCase{
	
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
	 * 全体設定
	 */
	public void 全体設定(){
		// login
		getPortal().login("test_user1", "password");
		
		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// 全体設定表示
		getPortal().getCommandBar().getPortalPreference().show();
		
		TestHelper.getScreenShot("全体設定", getDriver());

		assertTrue(true);
	}
	
	@Test
	/**
	 * 全体設定（壁紙変更）
	 */
	public void 全体設定_壁紙変更(){
		// login
		getPortal().login("test_user1", "password");
		
		PortalPreference pp = getPortal().getCommandBar().getPortalPreference();
		pp.show();
		
		for(int idx=0;idx<pp.getBackGroundImageNum();idx++){
			pp.changeBackGroundImage(idx);
			pp.hide();
			TestHelper.getScreenShot("全体設定_壁紙変更_" + (idx), getDriver());
			pp.show();
		}
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * 全体設定（ガジェットヘッダ色変更）
	 */
	public void 全体設定_ガジェットヘッダ_色変更(){
		// login
		getPortal().login("test_user1", "password");
		
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// カラム１へガジェット（MultiRssReader）をドロップ
		TopMenu topMenu = getPortal().getTopMenu();
		topMenu.dropGadget("news", "news_asahi" , 3);
		
		PortalPreference pp = getPortal().getCommandBar().getPortalPreference();
		pp.show();
		
		System.out.println("pp.getGadgetHeaderColorNum()=" + pp.getGadgetHeaderColorNum());
		for(int idx=0;idx<pp.getGadgetHeaderColorNum();idx++){
			pp.changeGadgetHeaderColor(idx);
			pp.hide();
			TestHelper.getScreenShot("全体設定_ガジェットヘッダ_色変更" + (idx), getDriver());
			pp.show();
		}
		
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
