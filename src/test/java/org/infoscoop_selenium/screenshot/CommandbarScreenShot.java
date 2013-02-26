package org.infoscoop_selenium.screenshot;


import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
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
		getPortal().login(TEST_USER_1, TEST_PASSWORD);
		
		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// 全体設定表示
		getPortal().getCommandBar().getPortalPreference().show();
		
		TestHelper.getScreenShot("全体設定", getDriver());

		assertTrue(true);
	}
	
	/*
	@Test
	public void ドロップテスト用_でばっぐ(){
		// login
		getPortal().login("test_user1", "password");
		
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// カラム１へガジェット（MultiRssReader）をドロップ
		TopMenu topMenu = getPortal().getTopMenu();
//		topMenu.dropGadget("news", "news_asahi" , 2);
		topMenu.dropGadget("etcWidgets", "etcWidgets_calculator" , 3);
		
		assertTrue(false);
	}
	*/
	
	@Test
	/**
	 * 全体設定（壁紙変更）
	 */
	public void 全体設定_壁紙変更(){
		// login
		getPortal().login(TEST_USER_1, TEST_PASSWORD);
		
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
		getPortal().login(TEST_USER_1, TEST_PASSWORD);
		
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// カラム１へガジェット（MultiRssReader）をドロップ
		TopMenu topMenu = getPortal().getTopMenu();
		topMenu.dropGadget("news", "news_asahi" , 1);
		
		PortalPreference pp = getPortal().getCommandBar().getPortalPreference();
		pp.show();
		
		for(int idx=0;idx<pp.getGadgetHeaderColorNum();idx++){
			pp.changeGadgetHeaderColor(idx);
			pp.hide();
			TestHelper.getScreenShot("全体設定_ガジェットヘッダ_色変更" + (idx), getDriver());
			pp.show();
		}
		
		for(int idx=0;idx<pp.getGadgetSubHeaderColorNum();idx++){
			pp.changeGadgetSubHeaderColor(idx);
			pp.hide();
			TestHelper.getScreenShot("全体設定_ガジェットサブヘッダ_色変更" + (idx), getDriver());
			pp.show();
		}
		assertTrue(true);
	}
	
	@Test
	/**
	 * 全体設定（ガジェットの枠を表示しない）
	 * @param sleep
	 */
	public void 全体設定_ガジェットの枠を表示しない(){
		// login
		getPortal().login(TEST_USER_1, TEST_PASSWORD);
		
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// カラム１へガジェット（MultiRssReader）をドロップ
		TopMenu topMenu = getPortal().getTopMenu();
		topMenu.dropGadget("news", "news_asahi" , 1);
		
		PortalPreference pp = getPortal().getCommandBar().getPortalPreference();
		pp.show();
		pp.changeGadgetBorder(true);
		pp.hide();
		
		TestHelper.getScreenShot("全体設定_ガジェットの枠を表示しない", getDriver());
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * 全体設定（ガジェットの角を丸くする）
	 * 
	 * IE8は角丸に対応していないため、必ず成功になる
	 * @param sleep
	 */
	public void 全体設定_ガジェットの角を丸くする(){
		// login
		getPortal().login(TEST_USER_1, TEST_PASSWORD);
		
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// カラム１へガジェット（MultiRssReader）をドロップ
		TopMenu topMenu = getPortal().getTopMenu();
		topMenu.dropGadget("news", "news_asahi" , 1);
		
		PortalPreference pp = getPortal().getCommandBar().getPortalPreference();
		pp.show();
		
		if(pp.checkChangeGadgetBorderRadius()){
			// 角丸チェックボックスが表示されていれば処理を実行
			pp.changeGadgetBorderRadius(true);
			pp.hide();
			TestHelper.getScreenShot("全体設定_ガジェットの角を丸くする", getDriver());
		}
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * ゴミ箱
	 */
	public void ゴミ箱(){
		// login
		getPortal().login(TEST_USER_1, TEST_PASSWORD);
		
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		getPortal().getCommandBar().getTrashBox().show();
		
		// カラム１へガジェット（MultiRssReader）をドロップ
		TopMenu topMenu = getPortal().getTopMenu();
		Gadget gadget = topMenu.dropGadget("news", "news_asahi" , 1);
		gadget.close();
		
		TestHelper.getScreenShot("ゴミ箱", getDriver());
	}
	
	private static void sleep(long sleep){
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
