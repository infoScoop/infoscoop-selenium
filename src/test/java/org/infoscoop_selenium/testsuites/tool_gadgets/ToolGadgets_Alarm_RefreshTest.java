package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.AlarmGadget;
import org.infoscoop_selenium.portal.gadget.AlarmGadget.NOTIFY;
import org.junit.Test;
import org.openqa.selenium.support.ui.Select;

public class ToolGadgets_Alarm_RefreshTest extends IS_BaseItTestCase {
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

	/**
	 * 
	 */
	@Test
	/**
	 * 更新
	 */
	public void iscp_4007(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();

		// アラームを設定
		GADGET.setAlarm("アラームテスト", NOTIFY.SHAKE, 1, 12, 30);
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().ok();

		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		// 設定内容の取得
		String title = GADGET.getTitleElement().getAttribute("value");
		Select notifySelect = new Select(GADGET.getNotificationElement());
		String notify = notifySelect.getFirstSelectedOption().getAttribute("value");
		String ymd = GADGET.getCalendarElement().getAttribute("value");
		String hour = GADGET.getHourElement().getAttribute("value");
		String minute = GADGET.getMinuteElement().getAttribute("value");
		
		// ブラウザの更新
		super.getDriver().navigate().refresh();
		TestHelper.sleep(2000);
		
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		// 設定内容の比較
		assertEquals(title, GADGET.getTitleElement().getAttribute("value"));
		Select notifySelect2 = new Select(GADGET.getNotificationElement());
		assertEquals(notify, notifySelect2.getFirstSelectedOption().getAttribute("value"));
		assertEquals(ymd, GADGET.getCalendarElement().getAttribute("value"));
		assertEquals(hour, GADGET.getHourElement().getAttribute("value"));
		assertEquals(minute, GADGET.getMinuteElement().getAttribute("value"));
	}
}
