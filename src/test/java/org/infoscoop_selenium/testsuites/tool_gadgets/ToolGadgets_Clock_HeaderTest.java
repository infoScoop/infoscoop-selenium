package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ClockGadget;
import org.junit.Test;

public class ToolGadgets_Clock_HeaderTest extends IS_BaseItTestCase {
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
	 * パーソナライズ領域
	 */
	public void iscp_185(){
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
	public void iscp_186(){
		// 管理画面操作が必要であるため保留
	}
}
