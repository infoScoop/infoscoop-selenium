package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.junit.Test;


/**
 * タブ/タブ切り替え
 */
public class Tab_ChangeTabTest extends IS_BaseItTestCase {
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
	 * 初期表示
	 */
	public void iscp_5714(){
		String currentTabId = getPortal().getTab().getCurrentTabId();
		
		// ログイン時のカレントタブIDが"tab0"であれば成功
		assertEquals(ISConstants.TABID_HOME, currentTabId);
	}

	@Test
	/**
	 * 切り替え
	 */
	public void iscp_5715(){
		// 切り替え用のタブ追加
		getPortal().getTab().addTab();
		
		// カレントタブがホーム以外になっていることを確認
		String currentTabId = getPortal().getTab().getCurrentTabId();
		assertFalse(currentTabId.equals(ISConstants.TABID_HOME));
		
		// 非アクティブのホームタブをクリックし、カレントタブがホームになっていればなら成功
		getPortal().getTab().selectTab(ISConstants.TABID_HOME);
		currentTabId = getPortal().getTab().getCurrentTabId();
		assertEquals(ISConstants.TABID_HOME, currentTabId);
	}
	
	/**
	 * インディケータ
	 * TODO: インディケータの表示が一瞬すぎて検知できないので保留
	 */
	/*
	public void iscp_5716(){
		// 切り替え用のタブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// 非アクティブのホームタブをクリックし、インディケータの表示を確認
		getPortal().getTab().selectTab(ISConstants.TABID_HOME);
		TestHelper.waitPresent(getPortal().getTab().getIndicator());
		
		// 追加したタブをクリックし、インディケータの表示を確認
		getPortal().getTab().selectTab(addedTabId);
		TestHelper.waitPresent(getPortal().getTab().getIndicator());
		
		assertTrue(true);
	}
	*/
}
