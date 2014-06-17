package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		WebElement selectMenu1 = getPortal().getTab().getSelectMenu(addedTabId);
		assertTrue(selectMenu1.isDisplayed());
		// ホームタブはメニューが表示されない
		WebElement selectMenu0 = getPortal().getTab().getSelectMenu(ISConstants.TABID_HOME);
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
			getPortal().getTab().getTabMenu(ISConstants.TABID_HOME);
			assertTrue("must throw NoSuchElementException", true);
		} catch (NoSuchElementException e) {
			//success
		}
		// タブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		// タブメニューが存在する
		WebElement tabMenu0 = getPortal().getTab().getTabMenu(ISConstants.TABID_HOME);
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
		WebElement we1 = getPortal().getTab().getRefreshItem(ISConstants.TABID_HOME);
		assertTrue(we1.isDisplayed());
		// [列数変更]
		WebElement we2 = getPortal().getTab().getColumnNumSelect(ISConstants.TABID_HOME);
		assertTrue(we2.isDisplayed());
		// [列の幅を揃える]
		WebElement we3 = getPortal().getTab().getResetColumnWidthItem(ISConstants.TABID_HOME);
		assertTrue(we3.isDisplayed());
		// [タブの構成を初期化する]
		WebElement we4 = getPortal().getTab().getInitializeItem(ISConstants.TABID_HOME);
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
		WebElement we1 = getPortal().getTab().getRefreshItem(addedTabId);
		assertTrue(we1.isDisplayed());
		// [削除]
		WebElement we2 = getPortal().getTab().getCloseItem(addedTabId);
		assertTrue(we2.isDisplayed());
		// [名称変更]
		WebElement we3 = getPortal().getTab().getNameInput(addedTabId);
		assertTrue(we3.isDisplayed());
		// [列数変更]
		WebElement we4 = getPortal().getTab().getColumnNumSelect(addedTabId);
		assertTrue(we4.isDisplayed());
		// [列の幅を揃える]
		WebElement we5 = getPortal().getTab().getResetColumnWidthItem(addedTabId);
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
		WebElement tabMenu1 = getPortal().getTab().getTabMenu(addedTabId);
		assertFalse(tabMenu1.isDisplayed());
	}

	//@Test
	/**
	 * 多段表示されている状態
	 */
	public void iscp_5737(){
	}

	//@Test
	/**
	 * ウィンドウ右端での表示
	 */
	public void iscp_5738(){
	}

}
