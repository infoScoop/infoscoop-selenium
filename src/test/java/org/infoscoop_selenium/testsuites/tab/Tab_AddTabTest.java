package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.junit.Test;
import org.openqa.selenium.WebElement;

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
		assertEquals(beforeNumberOfTab + 1, afterNumberOfTab);
		
		// 追加されたタブが一番最後（右）になっていることを確認	
		String farRightTabId = getPortal().getTab().getFarRightTabId();
		assertEquals(addedTabId, farRightTabId);
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
		assertEquals(addedTabId, currentTabId);
	}
	
	@Test
	/**
	 * タブ追加4
	 * タブが制限数まで表示されている状態でタブを削除すると、「タブを追加」ボタンが再表示されることを確認
	 */
	public void iscp_5720(){
		int numberOfTab = getPortal().getTab().getNumberOfTab();
		WebElement addTabButton;
		
		// 繰り返す回数 = 制限値-固定タブ数-最後の1回
		int numberOftabToAdd = ISConstants.DEFAULT_MAX_TABS-numberOfTab-1;
		// タブを制限数まで追加。
		for (int i = 0; i < numberOftabToAdd; i++) {
			//タブを追加
			getPortal().getTab().addTab();
			//[タブを追加]ボタンが表示されることを確認
			addTabButton = getPortal().getTab().getAddTabButton();
			assertTrue(addTabButton.isDisplayed());
		}
		
		//最後のタブを追加。タブの制限数を超えた場合は[タブを追加]ボタンが表示されない
		String addedTabId = getPortal().getTab().addTab();
		//XXX 動作するが、不要な例外を発生させてしまっている
		addTabButton = getPortal().getTab().getAddTabButton();
		assertFalse(addTabButton.isDisplayed());
		
		//その後タブを削除すると[タブを追加]ボタンが表示される
		getPortal().getTab().deleteTab(addedTabId);
		addTabButton = getPortal().getTab().getAddTabButton();
		assertTrue(addTabButton.isDisplayed());
	}
	
}
