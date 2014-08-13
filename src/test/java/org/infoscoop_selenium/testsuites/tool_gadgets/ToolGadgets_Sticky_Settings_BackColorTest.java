package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.StickyGadget;
import org.infoscoop_selenium.portal.gadget.StickyGadget.BACKGROUNDCOLOR;
import org.junit.Test;
import org.openqa.selenium.support.ui.Select;

public class ToolGadgets_Sticky_Settings_BackColorTest extends IS_BaseItTestCase {
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
	public void iscp_4101(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		Select bgcolor = new Select(GADGET.getBackgroundColorElement());
		assertEquals(BACKGROUNDCOLOR.YELLOW.getValue(), bgcolor.getFirstSelectedOption().getAttribute("value"));
	}

	@Test
	/**
	 * 背景色指定
	 */
	public void iscp_4102(){
		String bgcolor0 = GADGET.getContentBackgroundColor();
		assertEquals("rgba(255, 255, 204, 1)", bgcolor0);
		GADGET.changeBackgroundColor(BACKGROUNDCOLOR.BLUE);
		String bgcolor1 = GADGET.getContentBackgroundColor();
		assertEquals("rgba(229, 236, 249, 1)", bgcolor1);
	}

	@Test
	/**
	 * 保存確認
	 */
	public void iscp_4103(){
		GADGET.changeBackgroundColor(BACKGROUNDCOLOR.BLUE);
		
		String bgcolor0 = GADGET.getContentBackgroundColor();
		assertEquals("rgba(229, 236, 249, 1)", bgcolor0);
		
		// ブラウザの更新
		super.getDriver().navigate().refresh();
		TestHelper.sleep(2000);
		
		String bgcolor1 = GADGET.getContentBackgroundColor();
		assertEquals("rgba(229, 236, 249, 1)", bgcolor1);
	}
}
