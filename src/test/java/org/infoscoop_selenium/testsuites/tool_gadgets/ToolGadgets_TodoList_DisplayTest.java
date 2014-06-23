package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;
import org.openqa.selenium.WebElement;

/**
 * タブ/タブ追加
 */
public class ToolGadgets_TodoList_DisplayTest extends IS_BaseItTestCase{
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
	 * 初期表示
	 * 何も登録されていない初期表示状態で追加用テキストボックスと追加ボタンのみが表示されることを確認
	 */
	public void iscp_4011(){
		WebElement todoAddTextBox = GADGET.getTodoAddTextBox();
		WebElement todoAddButton = GADGET.getTodoAddButton();
		int todoLength = GADGET.getTodoLength();
		
		GADGET.focus();
		// TODO数が空であることを確認
		assertTrue(todoAddTextBox.isDisplayed());
		// ボタンが表示されていることを確認
		assertTrue(todoAddButton.isDisplayed());
		// テキストボックスが表示されていることを確認
		assertEquals(0, todoLength);
		GADGET.blur();
	}
	
	@Test
	/**
	 * 高さ調整
	 * コンテンツの表示内容と、コンテンツの幅に合わせて、ガジェットの高さが自動的に調整され、コンテンツの表示内容がはみ出して見えなくならないことを確認。
	 */
	public void iscp_4014(){
		// 適当にTODO追加
		GADGET.addToDo("test");
		GADGET.addToDo("test");
		GADGET.addToDo("test");
		GADGET.addToDo("test");
		GADGET.addToDo("test");
		GADGET.addToDo("test");
		GADGET.addToDo("test");
		GADGET.addToDo("test");
		
		// ガジェット内の高さとフレームの高さを比較。ガジェット内の高さがフレームの高さを下回っていれば成功
		assertTrue(GADGET.getFrame().getSize().height + " > " + GADGET.getContentHeight(),
				GADGET.getFrame().getSize().height > GADGET.getContentHeight());
	}
}
