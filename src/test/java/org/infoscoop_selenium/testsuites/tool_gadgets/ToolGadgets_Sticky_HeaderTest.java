package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.StickyGadget;
import org.junit.Test;

public class ToolGadgets_Sticky_HeaderTest extends IS_BaseItTestCase {
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
	 * パーソナライズ領域
	 */
	public void iscp_183(){
		List<String> headerIconTypes = GADGET.getHeaderIconTypes();
		
		// ガジェットから取得したアイコンタイプのリストとサポートアイコンのリストを比較。全部一致で成功
		assertTrue(GADGET.getSupportedHeaderIcons().containsAll(headerIconTypes));
		
		// ガジェットから取得したメニュータイプのリストとサポートメニューアイテムのリストを比較。全部一致で成功
		List<String> menuItemTypes = GADGET.getMenuItemTypes();
		assertTrue(GADGET.getSupportedMenuItems().containsAll(menuItemTypes));
	}
	
	//@Test
	/**
	 * 固定領域
	 */
	public void iscp_184(){
		// 管理画面操作が必要であるため保留
	}
}
