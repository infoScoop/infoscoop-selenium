package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;

/**
 * ツール系ガジェット/TODOリスト/設定/フォントサイズ
 */
public class ToolGadgets_TodoList_Settings_FontSizeTest extends IS_BaseItTestCase{
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
	 * 通常
	 * フォントサイズを通常にすると、Todo リスト内の文字が通常のフォントサイズになることを確認。
	 */
	public void iscp_4017(){
		// 大きくする
		GADGET.changeFontSize(ToDoListGadget.FONTSIZE.LARGE);
		
		// 大きい高さを取る
		int largeHeight = GADGET.getDisplayFontSize();
		
		// 通常にする
		GADGET.changeFontSize(ToDoListGadget.FONTSIZE.NORMAL);
		
		// 通常の高さを取る
		int normalHeight = GADGET.getDisplayFontSize();
		
		// 大きい高さ > 通常の高さ で成功
		assertTrue(largeHeight + "> " + normalHeight, largeHeight > normalHeight);
	}
	
	@Test
	/**
	 * 大きい
	 * フォントサイズを大きいにすると、Todo リスト内の文字が大きいフォントサイズになることを確認。
	 */
	public void iscp_4018(){
		// 現在の高さを取る
		int normalHeight = GADGET.getDisplayFontSize();
		
		// 大きくする
		GADGET.changeFontSize(ToDoListGadget.FONTSIZE.LARGE);
		
		// 大きい高さを取る
		int largeHeight = GADGET.getDisplayFontSize();
		
		// 大きい高さ > 現在の高さ で成功
		assertTrue(largeHeight + "> " + normalHeight, largeHeight > normalHeight);
	}
	
//	@Test
	/**
	 * ブラウザのフォントサイズの影響
	 * Todo リスト内の文字はブラウザのフォントサイズの影響を受けることを確認。
	 */
	public void iscp_4019(){
		// ブラウザのフォントサイズ変更はコントロールできない
	}
}
