package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.AlarmGadget;
import org.infoscoop_selenium.portal.gadget.AlarmGadget.NOTIFY;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;


/**
 * アラームガジェットのスクリーンショット
 * @author mikami
 *
 */
public class AlarmGadgetScreenShot extends IS_BaseItTestCase {
	private static AlarmGadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// ガジェットのドロップ
		GADGET = (AlarmGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_alarm", 1, GADGET_TYPE.ALARM);
	}

	@Test
	/**
	 * アラームガジェット（初期ドロップ）
	 */
	public void アラームガジェット_初期ドロップ(){
		WebDriver driver = getDriver();		
		
		TestHelper.getScreenShot("アラームガジェット（初期ドロップ）", driver);
	
		assertTrue(true);
	}
	

	@Test
	/**
	 * アラームガジェット（ガジェットメニュー）
	 */
	public void アラームガジェット_ガジェットメニュー(){
		WebDriver driver = getDriver();
		
		// ガジェットメニューを開く
		GADGET.openMenu();
		
		TestHelper.getScreenShot("アラームガジェット（ガジェットメニュー）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * アラームガジェット（エラーダイアログ）
	 */
	public void アラームガジェット_エラーダイアログ(){
		WebDriver driver = getDriver();
		
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();

		Dimension size = driver.manage().window().getSize();
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().ok();

		sleep(100);
		TestHelper.getScreenShotConfirm("アラームガジェット（エラーダイアログ）", driver, size.width, size.height);
		
		// Confirmを閉じる
		driver.switchTo().alert().accept();
		assertTrue(true);
	}
	
	@Test
	/**
	 * アラームガジェット（カレンダー）
	 */
	public void アラームガジェット_カレンダー(){
		WebDriver driver = getDriver();
		
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		driver.findElement(By.xpath("//td[@id='eb_"+GADGET.getId()+"_ymd']/div[@class='DatatypeCalendar']/a")).click();		
		TestHelper.waitPresent(driver, By.className("CalendarComponent_Panel"));
		
		TestHelper.getScreenShot("アラームガジェット（カレンダー）", driver);

		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().cancel();
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * ToDoアラームガジェット（ガジェット設定）
	 */
	public void アラームガジェット_ガジェット設定(){
		WebDriver driver = getDriver();
		
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();

		// アラームを設定
		GADGET.setAlarm("アラームテスト", NOTIFY.SHAKE, 12, 30);

		TestHelper.getScreenShot("アラームガジェット（ガジェット設定）", driver);

		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().cancel();
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * アラームガジェット（設定済み表示）
	 */
	public void アラームガジェット_設定済み表示(){
		WebDriver driver = getDriver();

		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();

		// アラームを設定
		GADGET.setAlarm("アラームテスト", NOTIFY.SHAKE, 12, 30);

		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().ok();
		
		// ガジェットの表示を待つ
		TestHelper.switchToFrame(driver, "ifrm_"+GADGET.getId());
		TestHelper.backToTopFrame(driver);
		
		TestHelper.getScreenShot("アラームガジェット（設定済み表示）", driver);

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
