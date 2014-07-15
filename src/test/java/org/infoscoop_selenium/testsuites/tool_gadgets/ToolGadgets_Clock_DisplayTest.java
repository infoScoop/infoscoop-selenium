package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

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
	 */
	public void iscp_4110() throws Exception {
		String jpDate = GADGET.getJpDate();
		String ukDate = GADGET.getUkDate();
		String usDate = GADGET.getUsDate();
		String jpTime = GADGET.getJpTime();
		String ukTime = GADGET.getUkTime();
		String usTime = GADGET.getUsTime();
		//JPのUNIX時間
		SimpleDateFormat jpdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		long jpVal = jpdf.parse(jpDate + " " + jpTime).getTime();
		//UKのUNIX時間
		SimpleDateFormat ukdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ukdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		long ukVal = ukdf.parse(ukDate + " " + ukTime).getTime();
		//USのUNIX時間
		SimpleDateFormat usdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		usdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		long usVal = usdf.parse(usDate + " " + usTime).getTime();
		//1秒の誤差を補正する
		if (!jpTime.substring(6).equals(ukTime.substring(6))) {
			ukVal -= 1000;
		}
		if (!jpTime.substring(6).equals(usTime.substring(6))) {
			usVal -= 1000;
		}
		//タイムゾーンを考慮すると同じUNIX時間となる
		assertEquals(jpVal, ukVal);
		assertEquals(jpVal, usVal);
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
