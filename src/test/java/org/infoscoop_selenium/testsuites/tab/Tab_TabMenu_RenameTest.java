package org.infoscoop_selenium.testsuites.tab;
import static org.junit.Assert.*;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import org.infoscoop_selenium.constants.ISConstants;


/**
 * タブ/タブメニュー/名称変更
 *
 */
public class Tab_TabMenu_RenameTest extends IS_BaseItTestCase{

	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
	}
	
	@Test
	/**
	 * 名称変更
	 */
	public void iscp_5744() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のinput要素があることを確認
		WebElement renameInput = getPortal().getTab().getNameInput(addedTabId);
		assertTrue(renameInput.isDisplayed());
	}
	
	@Test
	/**
	 * 初期値
	 */
	public void iscp_5745() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のinput要素に値が入っていることを確認
		String value = getPortal().getTab().getNameInputValue(addedTabId);
		assertFalse("".equals(value));
	}
	
//	@Test
	/**
	 * フォーカス
	 * TODO: 中断。clickNameInputでフォーカスが当たらない
	 */
/*	public void iscp_5746() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のフィールド上をクリック
		//TODO: クリックでフォーカスが当たらない
		WebElement nameInput = getPortal().getTab()
				.clickNameInput(addedTabId);
		
		//フォーカスが当たっているか確認
		WebElement currentFocusdElement = getPortal()
				.getTab().getCurrentFocusedElement();
		assertEquals(nameInput, currentFocusdElement);
	}*/

	
//	@Test
	/**
	 * ブラー
	 * TODO: 中断。clickNameInputでフォーカスが当たらない
	 */
/*	public void iscp_5747() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のフィールド上をクリック
		getPortal().getTab().clickNameInput(addedTabId);
		
		//タブ名を変更
		String tabNameAfter = "changing";
		getPortal().getTab().inputTabName(addedTabId, tabNameAfter);
		
		//ブラー（他のところをクリック）
		getPortal().getTab().selectTab(ISConstants.TABID_HOME);
		
		//タブ名が変更されたか確認
		String tabNameActual = getPortal().getTab().getTabName(addedTabId);
		assertEquals(tabNameAfter, tabNameActual);
	}*/

}
