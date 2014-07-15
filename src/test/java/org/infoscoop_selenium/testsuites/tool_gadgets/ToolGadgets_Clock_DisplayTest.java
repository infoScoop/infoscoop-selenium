package org.infoscoop_selenium.testsuites.tool_gadgets;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ClockGadget;
import org.junit.Test;

public class ToolGadgets_Clock_DisplayTest extends IS_BaseItTestCase {
	private static ClockGadget GADGET;	

	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();

		// ガジェットのドロップ
		GADGET = (ClockGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_worldclock", 1, GADGET_TYPE.CLOCK);
	}

	@Test
	/**
	 * 時刻
	 * @XXX チェック方法を検討
	 */
	public void iscp_4110(){
		String jpDate = GADGET.getJpDate();
		String jpTime = GADGET.getJpTime();
		String ukDate = GADGET.getUkDate();
		String ukTime = GADGET.getUkTime();
		String usDate = GADGET.getUsDate();
		String usTime = GADGET.getUsTime();
		System.out.println(jpDate + " " + jpTime);
		System.out.println(ukDate + " " + ukTime);
		System.out.println(usDate + " " + usTime);
	}

	//@Test
	/**
	 * サマータイム時刻
	 */
	public void iscp_4111(){
	}

	//@Test
	/**
	 * サマータイム表示
	 */
	public void iscp_4112(){
	}

	//@Test
	/**
	 * 高さ調整
	 */
	public void iscp_4113(){
	}
}
