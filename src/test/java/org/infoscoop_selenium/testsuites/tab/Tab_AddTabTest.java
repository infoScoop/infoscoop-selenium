package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.*;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.junit.Test;

/**
 * タブ/タブ追加
 */
public class Tab_AddTabTest extends IS_BaseItTestCase{

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
	 * タブ追加1
	 * タブの右側にある「タブを追加」ボタンをクリックすると、タブの一番右に新しいタブが追加されることを確認
	 */
	public void iscp_5717(){
		// 初期タブ数
		int beforeNumberOfTab = getPortal().getTab().getNumberOfTab();
		
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// タブがひとつ多くなっていることを確認
		int afterNumberOfTab = getPortal().getTab().getNumberOfTab();
		assertTrue(beforeNumberOfTab + 1 == afterNumberOfTab);
		
		// 追加されたタブが一番最後（右）になっていることを確認	
		String farRightTabId = getPortal().getTab().getFarRightTabId();
		assertTrue(addedTabId.equals(farRightTabId));
	}
	
	@Test
	/**
	 * タブ追加2
	 * 追加されたタブがアクティブ状態になることを確認
	 */
	public void iscp_5718(){
		//タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		//追加したタブID=カレントタブID（アクティブタブのID）なら成功
		String currentTabId = getPortal().getTab().getCurrentTabId();
		assertTrue(addedTabId.equals(currentTabId));
	}
	
//	@Test
//	/**
//	 * タブ追加4
//	 * タブが制限数まで表示されている状態でタブを削除すると、「タブを追加」ボタンが再表示されることを確認
//	 * TODO: タブの制限数が不明なため保留
//	 */
//	public void iscp_5720(){
//		
//	}
	
}
