package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.StickyGadget;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ToolGadgets_Sticky_SettingsTest extends IS_BaseItTestCase {
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
	 * 表示
	 */
	public void iscp_4095(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		WebElement title = GADGET.getTitleElement();
		assertTrue(title.isDisplayed());
		WebElement fontSize = GADGET.getFontSizeElement();
		assertTrue(fontSize.isDisplayed());
		WebElement bgcolor = GADGET.getBackgroundColorElement();
		assertTrue(bgcolor.isDisplayed());
		WebElement color = GADGET.getColorElement();
		assertTrue(color.isDisplayed());
	}

	@Test
	/**
	 * ウィジェットタイトル指定
	 */
	public void iscp_4096(){
		String inputStr = "ふせん";
		GADGET.changeTitle("\b\b"+inputStr);
		assertEquals(inputStr, GADGET.getTitle());
	}
}
