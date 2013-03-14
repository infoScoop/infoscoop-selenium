package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ScheduleGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * スケジュールガジェットのスクリーンショット
 * @author mikami
 *
 */
public class ScheduleGadgetScreenShot extends IS_BaseItTestCase {
	private static ScheduleGadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// ガジェットのドロップ
		GADGET = (ScheduleGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_schedule", 1, GADGET_TYPE.SCHEDULE);
	}

	@Test
	/**
	 * スケジュールガジェット（初期ドロップ）
	 */
	public void スケジュールガジェット_初期ドロップ(){
		WebDriver driver = getDriver();		
		
		TestHelper.waitPresent(driver, By.id("hi_"+GADGET.getId()+"_showTools"));
		
		TestHelper.getScreenShot("スケジュールガジェット（初期ドロップ）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * スケジュールガジェット（ガジェットメニュー）
	 */
	public void スケジュールガジェット_ガジェットメニュー(){
		WebDriver driver = getDriver();
		
		// ガジェットメニューを開く
		GADGET.openMenu();
		
		TestHelper.getScreenShot("スケジュールガジェット（ガジェットメニュー）", driver);
	
		assertTrue(true);
	}

	@Test
	/**
	 * スケジュールガジェット（ガジェット設定）
	 */
	public void スケジュールガジェット_ガジェット設定(){
		WebDriver driver = getDriver();
		
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		TestHelper.getScreenShot("スケジュールガジェット（ガジェット設定）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * スケジュールガジェット（設定済み表示）
	 */
	public void スケジュールガジェット_設定済み表示(){
		WebDriver driver = getDriver();
		
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		// スケジュール設定
		GADGET.setSchedule("天気予報", "http://weather.livedoor.com/forecast/ical/13/63.ics", true, 0);
		
		//設定を完了
		GADGET.getGadgetPreference().ok();
		TestHelper.waitPresent(driver, By.className("scheduleEventItem"));
		
		TestHelper.getScreenShot("スケジュールガジェット（設定済み表示）", driver);
	
		assertTrue(true);
	}

	@Test
	/**
	 * スケジュールガジェット（詳細表示）
	 */
	public void スケジュールガジェット_詳細表示(){
		WebDriver driver = getDriver();
		
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		// スケジュール設定
		GADGET.setSchedule("天気予報", "http://weather.livedoor.com/forecast/ical/13/63.ics", true, 0);
		
		// 設定を完了
		GADGET.getGadgetPreference().ok();
		TestHelper.waitPresent(driver, By.className("scheduleEventItem"));
		
		// 詳細ボタンクリック
		GADGET.clickDetail(1);
		
		TestHelper.getScreenShot("スケジュールガジェット（詳細表示）", driver);
	
		assertTrue(true);
	}

	@Test
	/**
	 * スケジュールガジェット（最大化）
	 */
	public void スケジュールガジェット_最大化(){
		WebDriver driver = getDriver();
		
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		// スケジュール設定
		GADGET.setSchedule("天気予報", "http://weather.livedoor.com/forecast/ical/13/63.ics", true, 0);
		
		// 設定を完了
		GADGET.getGadgetPreference().ok();
		TestHelper.waitPresent(driver, By.className("scheduleEventItem"));
		
		// 最大化
		GADGET.maximaize();
		
		TestHelper.getScreenShot("スケジュールガジェット（最大化）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * スケジュールガジェット（最大化／詳細ポップアップ）
	 */
	public void スケジュールガジェット_最大化_詳細ポップアップ(){
		WebDriver driver = getDriver();
		
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		// スケジュール設定
		GADGET.setSchedule("天気予報", "http://weather.livedoor.com/forecast/ical/13/63.ics", true, 0);
		
		// 設定を完了
		GADGET.getGadgetPreference().ok();
		TestHelper.waitPresent(driver, By.className("scheduleEventItem"));
		
		// 最大化
		GADGET.maximaize();
		
		// 詳細ポップアップ
		GADGET.mouseOverDetail(4, 0);
		
		TestHelper.getScreenShot("スケジュールガジェット（最大化／詳細ポップアップ）", driver);
	
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
