package org.infoscoop_selenium.screenshot;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.infoscoop_selenium.portal.TopMenu;
import org.infoscoop_selenium.portal.commandbar.PortalPreference;
import org.infoscoop_selenium.portal.commandbar.TrashBox;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * タブUIのスクリーンショット
 * @author nishiumi
 *
 */
public class CommandbarScreenShot extends IS_BaseItTestCase{
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();
		
		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
	}

	@Test
	/**
	 * 全体設定
	 */
	public void 全体設定(){
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
		TrashBox tb = getPortal().getCommandBar().getTrashBox();
		

		// カラム１へガジェット（MultiRssReader）をドロップ
		TopMenu topMenu = getPortal().getTopMenu();
//		Gadget gadget = topMenu.dropGadget("news", "news_asahi" , 1);
		Gadget gadget = topMenu.dropGadget("etcWidgets", "etcWidgets_alarm" , 1);
		
		// ガジェット削除をゴミ箱へ
		gadget.close();
		
		tb.show();
		TestHelper.getScreenShot("ゴミ箱", getDriver());
		
		// ゴミ箱を空に
		tb.clear();
	}
	
	@Test
	/**
	 * ゴミ箱（コンテキストメニュー）
	 */
	public void ゴミ箱_コンテキストメニュー(){
		TrashBox tb = getPortal().getCommandBar().getTrashBox();
		

		// カラム１へガジェット（MultiRssReader）をドロップ
		TopMenu topMenu = getPortal().getTopMenu();
//		Gadget gadget = topMenu.dropGadget("news", "news_asahi" , 1);
		Gadget gadget = topMenu.dropGadget("etcWidgets", "etcWidgets_alarm" , 1);
		
		// ガジェット削除をゴミ箱へ
		gadget.close();
		
		// ゴミ箱の1番目のアイテムを右クリック
		tb.showContextMenu(1);
		
		TestHelper.getScreenShot("ゴミ箱_コンテキストメニュー", getDriver());
		
		// ゴミ箱を空に
		tb.clear();
	}

	@Test
	/**
	 * ランキング
	 */
	public void ランキング(){
		WebDriver driver = getDriver();
		
		// コマンドバーメニューを開く
		getPortal().getCommandBar().openMenu();
		
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='command-ranking']"));
		if(elements.size() > 0)
			return;
		
		TestHelper.waitPresent(driver, By.id("command-ranking"));
		driver.findElement(By.id("command-ranking")).click();
		TestHelper.waitPresent(driver, By.id("p_1_w_6_div"));		
		TestHelper.waitInvisible(driver, By.id("p_1_w_6_widgetIndicator"));

		TestHelper.getScreenShot("ランキング", getDriver(), true);
	}	
}
