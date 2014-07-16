package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.AlarmGadget;
import org.infoscoop_selenium.portal.gadget.AlarmGadget.NOTIFY;
import org.junit.Test;

public class ToolGadgets_Alarm_DisplayTest extends IS_BaseItTestCase {
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
	 * 未設定
	 */
	public void iscp_3987(){
		String content = GADGET.getContent().getText();
		assertEquals("ガジェットは必要な設定がすべて完了するまで表示できません。", content);
	}
	
	@Test
	/**
	 * カウントダウン
	 */
	public void iscp_3988(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();

		// アラームを設定
		GADGET.setAlarm("アラームテスト", NOTIFY.SHAKE, 1, 12, 30);
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().ok();

		//設定時刻12:30を確認
		String date = GADGET.getDate();
		assertTrue("date="+date, date.endsWith("12:30」の"));
		//タイトルを確認
		String title = GADGET.getTitleStr();
		assertEquals("「アラームテスト」まで、あと", title);
		//2秒間で残り時間が変わることを確認
		String time = GADGET.getTime();
		TestHelper.sleep(2000);
		String time2 = GADGET.getTime();
		assertFalse("time="+time+", time2="+time2, time.equals(time2));
	}

	@Test
	/**
	 * アラーム
	 */
	public void iscp_3989(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();

		// アラームを設定
		GADGET.setAlarm("アラームテスト", NOTIFY.SHAKE, -1, 12, 30);
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().ok();

		//設定時刻が空白を確認
		String date = GADGET.getDate();
		assertEquals("", date);
		//タイトルを確認
		String title = GADGET.getTitleStr();
		assertEquals("「アラームテスト」の時間です。", title);
		//2秒間で残り時間が変わらないことを確認
		String time = GADGET.getTime();
		TestHelper.sleep(2000);
		String time2 = GADGET.getTime();
		assertTrue("time="+time+", time2="+time2, time.equals(time2));
	}
}
