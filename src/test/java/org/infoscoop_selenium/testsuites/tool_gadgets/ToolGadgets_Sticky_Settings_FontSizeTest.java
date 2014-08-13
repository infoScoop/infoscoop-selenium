package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.StickyGadget;
import org.junit.Test;

public class ToolGadgets_Sticky_Settings_FontSizeTest extends IS_BaseItTestCase {
	private static StickyGadget GADGET;

	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();

		// ガジェットのドロップ
		GADGET = (StickyGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_stickey", 1, GADGET_TYPE.STICKY);
	}

	@Test
	/**
	 * 初期値
	 */
	public void iscp_4097(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		String fontSize = GADGET.getFontSizeElement().getAttribute("value");
		assertEquals("12", fontSize);
	}
	
	@Test
	/**
	 * フォントサイズ指定
	 */
	public void iscp_4098(){
		String fontSize0 = GADGET.getContentFontSize();
		assertEquals("12px", fontSize0);
		GADGET.changeFontSize("\b\b"+"24");
		String fontSize1 = GADGET.getContentFontSize();
		assertEquals("24px", fontSize1);
	}
	
	@Test
	/**
	 * 不正値
	 */
	public void iscp_4099(){
		GADGET.changeFontSize("\b\b"+"-1");
		String fontSize1 = GADGET.getContentFontSize();
		assertEquals("12px", fontSize1);
	}
	
	//@Test
	/**
	 * 行の高さ
	 */
	public void iscp_4100(){
		
	}
}
