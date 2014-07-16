package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.AlarmGadget;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

public class ToolGadgets_Alarm_Settings_DateTest extends IS_BaseItTestCase {
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
	 * 年月日の入力
	 */
	public void iscp_4000(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		WebElement ymd = GADGET.getCalendarElement();
		assertFalse(ymd.isEnabled());
	}
	
	@Test
	/**
	 * 未設定
	 */
	public void iscp_4001(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().getOkElement().click();
		
		Alert confirm = getDriver().switchTo().alert();
		String msg = confirm.getText();
    	confirm.accept();
    	
    	assertTrue(msg, msg.indexOf("年月日 が未入力です。") >= 0);
	}
}
