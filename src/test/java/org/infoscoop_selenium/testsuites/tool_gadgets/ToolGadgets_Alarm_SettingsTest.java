package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.AlarmGadget;
import org.infoscoop_selenium.portal.gadget.AlarmGadget.NOTIFY;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ToolGadgets_Alarm_SettingsTest extends IS_BaseItTestCase {
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
	 * 表示
	 */
	public void iscp_3993(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		WebElement title = GADGET.getTitleElement();
		assertTrue(title.isDisplayed());
		WebElement ymd = GADGET.getCalendarElement();
		assertTrue(ymd.isDisplayed());
		WebElement hour = GADGET.getHourElement();
		assertTrue(hour.isDisplayed());
		WebElement minute = GADGET.getMinuteElement();
		assertTrue(minute.isDisplayed());
		WebElement notify = GADGET.getNotificationElement();
		assertTrue(notify.isDisplayed());
	}

	@Test
	/**
	 * ウィジェットタイトル指定
	 */
	public void iscp_3994(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();

		// アラームを設定
		GADGET.setAlarm("アラームテスト", NOTIFY.SHAKE, 1, 12, 30);
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().ok();
		
		assertEquals("アラームテストのアラーム", GADGET.getTitle());
	}
}
