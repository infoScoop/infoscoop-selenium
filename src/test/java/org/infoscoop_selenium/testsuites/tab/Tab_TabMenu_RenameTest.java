package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.junit.Test;
import org.openqa.selenium.WebElement;

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
		assertFalse("value=" + value, "".equals(value));
	}
	
	@Test
	/**
	 * フォーカス
	 */
	public void iscp_5746() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のフィールド上をクリック
		WebElement nameInput = getPortal().getTab()
				.clickNameInput(addedTabId);
		
		//フォーカスが当たっているか確認
		WebElement currentFocusdElement = getPortal()
				.getTab().getCurrentFocusedElement();
		assertEquals(nameInput, currentFocusdElement);
	}

	
	@Test
	/**
	 * ブラー
	 */
	public void iscp_5747() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のフィールド上をクリック
		getPortal().getTab().clickNameInput(addedTabId);
		
		//タブ名を変更
		String inputStr = "changing";
		getPortal().getTab().inputTabName(addedTabId, inputStr);
		
		//ブラー（他のところをクリック）
		getPortal().getTab().selectTab(ISConstants.TABID_HOME);
		
		//タブ名が変更されたか確認
		String tabNameActual = getPortal().getTab().getTabName(addedTabId);
		assertEquals("新しいタブ"+inputStr, tabNameActual);
	}

	@Test
	/**
	 * エンター押下
	 */
	public void iscp_5748() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のフィールド上をクリック
		getPortal().getTab().clickNameInput(addedTabId);
		
		//タブ名を変更
		String inputStr = "changing";
		getPortal().getTab().inputTabName(addedTabId, inputStr+"\n");
		
		//タブ名が変更されたか確認
		String tabNameActual = getPortal().getTab().getTabName(addedTabId);
		assertEquals("新しいタブ"+inputStr, tabNameActual);
	}
	
	@Test
	/**
	 * 保存確認
	 */
	public void iscp_5749() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のフィールド上をクリック
		getPortal().getTab().clickNameInput(addedTabId);
		
		//タブ名を変更
		String inputStr = "changing";
		getPortal().getTab().inputTabName(addedTabId, inputStr+"\n");
		
		//再読み込みする
		super.getDriver().navigate().refresh();
		
		//タブ名が変更されたか確認
		String tabNameActual = getPortal().getTab().getTabName(addedTabId);
		assertEquals("新しいタブ"+inputStr, tabNameActual);
	}
	
	@Test
	/**
	 * 空値（フォーカスを外す）
	 */
	public void iscp_5750_1() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のフィールド上をクリック
		getPortal().getTab().clickNameInput(addedTabId);
		
		//タブ名を消去
		getPortal().getTab().inputTabName(addedTabId, "\b\b\b\b\b");
		//ブラー（他のところをクリック）
		getPortal().getTab().selectTab(ISConstants.TABID_HOME);
		
		//タブ名が変更されないことを確認
		String tabNameActual = getPortal().getTab().getTabName(addedTabId);
		assertEquals("新しいタブ", tabNameActual);
	}
	
	@Test
	/**
	 * 空値（エンター押下）
	 */
	public void iscp_5750_2() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のフィールド上をクリック
		getPortal().getTab().clickNameInput(addedTabId);
		
		//タブ名を消去し、エンターを押す
		getPortal().getTab().inputTabName(addedTabId, "\b\b\b\b\b");
		getPortal().getTab().inputTabName(addedTabId, "\n");
		
		//タブ名が変更されないことを確認
		String tabNameActual = getPortal().getTab().getTabName(addedTabId);
		assertEquals("新しいタブ", tabNameActual);
	}
	
	@Test
	/**
	 * 特殊文字
	 */
	public void iscp_5751() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のフィールド上をクリック
		getPortal().getTab().clickNameInput(addedTabId);
		
		//タブ名を消去
		getPortal().getTab().inputTabName(addedTabId, "\b\b\b\b\b");
		//タブ名を変更
		String inputStr = "①㈱㌢<>&\"'!\"#$%'()~=-^@`'*+;:[{]}\\_/?<>\\.タダイマポチゼンブアソボゾウノファミリ―～∥－￤";
		getPortal().getTab().inputTabName(addedTabId, inputStr+"\n");
		
		//タブ名が変更されたか確認
		String tabNameActual = getPortal().getTab().getTabName(addedTabId);
		assertEquals(inputStr, tabNameActual);
	}
	
	@Test
	/**
	 * 境界値（シングルバイト）
	 */
	public void iscp_5752_1() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のフィールド上をクリック
		getPortal().getTab().clickNameInput(addedTabId);
		
		//タブ名を消去
		getPortal().getTab().inputTabName(addedTabId, "\b\b\b\b\b");
		//80文字の文字列を作成
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=8; i++) {
			sb.append("1234567890");
		}
		String inputStr = sb.toString();
		//83文字を入力
		getPortal().getTab().inputTabName(addedTabId, inputStr+"ABC");
		getPortal().getTab().inputTabName(addedTabId, "\n");
		
		//タブ名が80文字であることを確認
		String tabNameActual = getPortal().getTab().getTabName(addedTabId);
		assertEquals(inputStr, tabNameActual);
	}
	
	@Test
	/**
	 * 境界値（ダブルバイト）
	 */
	public void iscp_5752_2() {
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		
		//名称変更のフィールド上をクリック
		getPortal().getTab().clickNameInput(addedTabId);
		
		//タブ名を消去
		getPortal().getTab().inputTabName(addedTabId, "\b\b\b\b\b");
		//80文字の文字列を作成
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=8; i++) {
			sb.append("１２３４５６７８９０");
		}
		String inputStr = sb.toString();
		//83文字を入力
		getPortal().getTab().inputTabName(addedTabId, inputStr+"ＡＢＣ");
		getPortal().getTab().inputTabName(addedTabId, "\n");
		
		//タブ名が80文字であることを確認
		String tabNameActual = getPortal().getTab().getTabName(addedTabId);
		assertEquals(inputStr, tabNameActual);
	}
}
