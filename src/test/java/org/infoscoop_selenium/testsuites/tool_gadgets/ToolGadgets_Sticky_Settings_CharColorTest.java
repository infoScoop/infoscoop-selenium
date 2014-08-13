package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.StickyGadget;
import org.infoscoop_selenium.portal.gadget.StickyGadget.BACKGROUNDCOLOR;
import org.infoscoop_selenium.portal.gadget.StickyGadget.COLOR;
import org.junit.Test;
import org.openqa.selenium.support.ui.Select;

public class ToolGadgets_Sticky_Settings_CharColorTest extends IS_BaseItTestCase {
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
	 * 文字色指定
	 */
	public void iscp_4104(){
		String color0 = GADGET.getContentColor();
		assertEquals("rgba(0, 0, 0, 1)", color0);
		GADGET.changeColor(COLOR.BLUE);
		String color1 = GADGET.getContentColor();
		assertEquals("rgba(0, 0, 255, 1)", color1);
	}

	@Test
	/**
	 * 保存確認
	 */
	public void iscp_4105(){
		GADGET.changeColor(COLOR.BLUE);
		
		String color0 = GADGET.getContentColor();
		assertEquals("rgba(0, 0, 255, 1)", color0);
		
		// ブラウザの更新
		super.getDriver().navigate().refresh();
		TestHelper.sleep(2000);
		
		String color1 = GADGET.getContentColor();
		assertEquals("rgba(0, 0, 255, 1)", color1);
	}

	@Test
	/**
	 * 初期値
	 */
	public void iscp_4106(){
		// ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		Select color = new Select(GADGET.getColorElement());
		assertEquals(COLOR.BLACK.getValue(), color.getFirstSelectedOption().getAttribute("value"));
	}
}
