package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.StickyGadget;
import org.junit.Test;

public class ToolGadgets_Sticky_DisplayTest extends IS_BaseItTestCase {
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
	
	//@Test
	/**
	 * ブラウザがリサイズされた場合の表示
	 */
	public void iscp_4083(){
	}
	
	//@Test
	/**
	 * 幅変更をした場合の表示
	 */
	public void iscp_4084(){
	}
	
	@Test
	/**
	 * 初期表示
	 */
	public void iscp_4085(){
		String content = GADGET.getContent();
		assertEquals("クリックで入力", content);
	}
	
	//@Test
	/**
	 * 高さ調整
	 */
	public void iscp_4086(){
	}
}
