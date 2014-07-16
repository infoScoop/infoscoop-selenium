package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.AlarmGadget;
import org.infoscoop_selenium.portal.gadget.AlarmGadget.NOTIFY;
import org.junit.Test;
import org.openqa.selenium.Alert;

public class ToolGadgets_Alarm_Settings_MinutesTest extends IS_BaseItTestCase {
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
	 * バリデーション
	 */
	public void iscp_3995(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		// アラームを設定
		GADGET.setAlarm("アラームテスト", NOTIFY.SHAKE, 1, 12, 30);
		// 不正な文字を入力
		GADGET.getMinuteElement().sendKeys("ABC");
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().ok();
		
		String msg = GADGET.getTitleMessageStr();
		assertEquals("不正な値が入力されました。設定し直して下さい。", msg);
		String err = GADGET.getTitleErrorStr();
		System.out.println(err);
		assertEquals("[分] 0から59までの整数(半角)を入力してください", err);
	}

	@Test
	/**
	 * 未設定
	 */
	public void iscp_3996(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().getOkElement().click();
		
		Alert confirm = getDriver().switchTo().alert();
		String msg = confirm.getText();
    	confirm.accept();
    	
    	assertTrue(msg, msg.indexOf("分 が未入力です。") >= 0);
	}
}
