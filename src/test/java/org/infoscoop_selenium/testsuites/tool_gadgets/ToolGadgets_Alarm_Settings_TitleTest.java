package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.AlarmGadget;
import org.infoscoop_selenium.portal.gadget.AlarmGadget.NOTIFY;
import org.junit.Test;
import org.openqa.selenium.Alert;

public class ToolGadgets_Alarm_Settings_TitleTest extends IS_BaseItTestCase {
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
	public void iscp_3997(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().getOkElement().click();
		
		Alert confirm = getDriver().switchTo().alert();
		String msg = confirm.getText();
    	confirm.accept();
    	
    	assertTrue(msg, msg.indexOf("タイトル が未入力です。") >= 0);
	}

	//@Test
	/**
	 * 折り返し（設定値がシングルバイト）
	 */
	public void iscp_7247(){
		
	}

	@Test
	/**
	 * 特殊文字
	 */
	public void iscp_3998(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();

		// アラームを設定
		String inputStr = "①㈱㌢<>&\"'!\"#$%'()~=-^@`'*+;:[{]}\\_/?<>\\.タダイマポチゼンブアソボゾウノファミリ―～∥－￤";
		GADGET.setAlarm(inputStr, NOTIFY.SHAKE, 1, 12, 30);
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().ok();
		
		assertEquals(inputStr+"のアラーム", GADGET.getTitle());
	}

	@Test
	/**
	 * 境界値
	 */
	public void iscp_3999(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();

		//4099文字の文字列を作成
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=256; i++) {
			sb.append("0123456789abcdef");
		}
		String inputStr = sb.toString() + "ABC";
		// アラームを設定
		GADGET.setAlarm(inputStr, NOTIFY.SHAKE, 1, 12, 30);
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().ok();
		
		assertEquals("「"+inputStr+"」まで、あと", GADGET.getTitleStr());
	}
}
