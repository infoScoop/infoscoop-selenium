package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Tab_TabMenuTest extends IS_BaseItTestCase {
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
	 * タブメニューボタン
	 */
	public void iscp_5730(){
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		// 追加したタブはメニューが表示される
		WebElement selectMenu1 = getPortal().getTab().getSelectMenuElement(addedTabId);
		assertTrue(selectMenu1.isDisplayed());
		// ホームタブはメニューが表示されない
		WebElement selectMenu0 = getPortal().getTab().getSelectMenuElement(ISConstants.TABID_HOME);
		assertFalse(selectMenu0.isDisplayed());
	}

	//@Test
	/**
	 * タブメニューボタンの折り返し
	 */
	public void iscp_5731(){
	}

	@Test
	/**
	 * 表示
	 */
	public void iscp_5732(){
		// ホームタブのタブメニューは存在しない
		try {
			getPortal().getTab().getTabMenuElement(ISConstants.TABID_HOME);
			fail("must throw NoSuchElementException");
		} catch (NoSuchElementException e) {
			//success
		}
		// タブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		// タブメニューが存在する
		WebElement tabMenu0 = getPortal().getTab().getTabMenuElement(ISConstants.TABID_HOME);
		assertTrue(tabMenu0.isDisplayed());
	}

	@Test
	/**
	 * 固定タブの内容
	 */
	public void iscp_5733(){
		// ホームタブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		// [再読み込み]
		WebElement we1 = getPortal().getTab().getRefreshItemElement(ISConstants.TABID_HOME);
		assertTrue(we1.isDisplayed());
		// [列数変更]
		WebElement we2 = getPortal().getTab().getColumnNumSelectElement(ISConstants.TABID_HOME);
		assertTrue(we2.isDisplayed());
		// [列の幅を揃える]
		WebElement we3 = getPortal().getTab().getResetColumnWidthItemElement(ISConstants.TABID_HOME);
		assertTrue(we3.isDisplayed());
		// [タブの構成を初期化する]
		WebElement we4 = getPortal().getTab().getInitializeItemElement(ISConstants.TABID_HOME);
		assertTrue(we4.isDisplayed());
	}

	//@Test
	/**
	 * 完全固定タブ
	 */
	public void iscp_5734(){
	}

	@Test
	/**
	 * ユーザタブの内容
	 */
	public void iscp_5735(){
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		// [再読み込み]
		WebElement we1 = getPortal().getTab().getRefreshItemElement(addedTabId);
		assertTrue(we1.isDisplayed());
		// [削除]
		WebElement we2 = getPortal().getTab().getCloseItemElement(addedTabId);
		assertTrue(we2.isDisplayed());
		// [名称変更]
		WebElement we3 = getPortal().getTab().getNameInputElement(addedTabId);
		assertTrue(we3.isDisplayed());
		// [列数変更]
		WebElement we4 = getPortal().getTab().getColumnNumSelectElement(addedTabId);
		assertTrue(we4.isDisplayed());
		// [列の幅を揃える]
		WebElement we5 = getPortal().getTab().getResetColumnWidthItemElement(addedTabId);
		assertTrue(we5.isDisplayed());
	}

	@Test
	/**
	 * 非表示
	 */
	public void iscp_5736(){
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		// 新規タブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(addedTabId);
		// ホームタブを選択
		getPortal().getTab().selectTab(ISConstants.TABID_HOME);
		// タブメニューが表示されない
		WebElement tabMenu1 = getPortal().getTab().getTabMenuElement(addedTabId);
		assertFalse(tabMenu1.isDisplayed());
	}

	@Test
	/**
	 * 多段表示されている状態
	 */
	public void iscp_5737(){
		WebElement selectMenu0 = getPortal().getTab().getSelectMenuElement(ISConstants.TABID_HOME);
		int selectMenuY0 = selectMenu0.getLocation().getY();
		// タブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		WebElement tabMenu0 = getPortal().getTab().getTabMenuElement(ISConstants.TABID_HOME);
		int tabMenuY0 = tabMenu0.getLocation().getY();
		String tabId = null;
		// タブメニューボタンのY座標が、ホームのタブメニューボタンのY座標より下になるまでループ
		for (int i=0; i<ISConstants.DEFAULT_MAX_TABS; i++) {
			tabId = getPortal().getTab().addTab();
			WebElement selectMenu = getPortal().getTab().getSelectMenuElement(tabId);
			if (selectMenuY0 < selectMenu.getLocation().getY()) break;
		}
		// 2段目のタブのタブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(tabId);
		WebElement tabMenu = getPortal().getTab().getTabMenuElement(tabId);
		// タブメニューのY座標が、ホームのタブメニューのY座標より下になることをチェック
		assertTrue(tabMenuY0 + " < " + tabMenu.getLocation().getY(),
				tabMenuY0 < tabMenu.getLocation().getY());
	}

	@Test
	/**
	 * ウィンドウ右端での表示
	 */
	public void iscp_5738(){
		ArrayList<String> tabIdList = new ArrayList<String>();
		WebElement selectMenu0 = getPortal().getTab().getSelectMenuElement(ISConstants.TABID_HOME);
		int selectMenuY0 = selectMenu0.getLocation().getY();
		// タブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		WebElement tabMenu0 = getPortal().getTab().getTabMenuElement(ISConstants.TABID_HOME);
		// タブメニューのX座標からのタブメニューボタンのX座標のオフセットを保存
		int offsetX0 = selectMenu0.getLocation().getX() - tabMenu0.getLocation().getX();
		// タブメニューボタンのY座標が、ホームのタブメニューボタンのY座標より下になるまでループ
		for (int i=0; i<ISConstants.DEFAULT_MAX_TABS; i++) {
			String tabId = getPortal().getTab().addTab();
			tabIdList.add(tabId);
			WebElement selectMenu = getPortal().getTab().getSelectMenuElement(tabId);
			if (selectMenuY0 < selectMenu.getLocation().getY()) break;
		}
		// 1段目の右端のタブのタブメニューボタンを選択
		String tabId2 = tabIdList.get(tabIdList.size()-2);
		getPortal().getTab().selectTab(tabId2);
		getPortal().getTab().selectSelectMenu(tabId2);
		WebElement selectMenu = getPortal().getTab().getSelectMenuElement(tabId2);
		WebElement tabMenu = getPortal().getTab().getTabMenuElement(tabId2);
		int offsetX = selectMenu.getLocation().getX() - tabMenu.getLocation().getX();
		// タブメニューからのタブメニューボタンのオフセットが大きくなっている（タブメニューが左に移動している）ことをチェック
		assertTrue(offsetX0 + " < " + offsetX, offsetX0 < offsetX);
	}

}
