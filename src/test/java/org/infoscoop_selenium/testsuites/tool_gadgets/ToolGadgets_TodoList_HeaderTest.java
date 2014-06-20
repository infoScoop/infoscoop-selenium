package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;

/**
 * ツール系ガジェット/TODOリスト/ヘッダ
 */
public class ToolGadgets_TodoList_HeaderTest extends IS_BaseItTestCase{
	private static ToDoListGadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();

		// ガジェットのドロップ
		GADGET = (ToDoListGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_TodoList", 1, GADGET_TYPE.TODOLIST);
	}
	
	@Test
	/**
	 * パーソナライズ領域
	 * パーソナライズ領域に配置されている場合、ウィジェットヘッダ・メニューに適切なアイコンが表示されることを確認
	 */
	public void iscp_179(){
		List<String> headerIconTypes = GADGET.getHeaderIconTypes();
		
		// ガジェットから取得したアイコンタイプのリストとサポートアイコンのリストを比較。全部一致で成功
		assertTrue(GADGET.getSupportedHeaderIcons().containsAll(headerIconTypes));
		
		// ガジェットから取得したメニュータイプのリストとサポートメニューアイテムのリストを比較。全部一致で成功
		List<String> menuItemTypes = GADGET.getMenuItemTypes();
		assertTrue(GADGET.getSupportedMenuItems().containsAll(menuItemTypes));
	}
	
//	@Test
	/**
	 * 固定領域
	 * 固定領域に配置されている場合、ウィジェットヘッダ・メニューに適切なアイコンが表示されることを確認
	 */
	public void iscp_180(){
		// 管理画面操作が必要であるため保留
	}
}
