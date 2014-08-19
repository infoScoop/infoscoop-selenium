package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.*;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.junit.Test;

/**
 * タブ/表示
 */
public class Tab_DisplayTabTest extends IS_BaseItTestCase {
	
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
	 * 折り返し1
	 * タブの数が多いとき、多段表示になる。
	 */
	public void iscp_5721() {
		//固定タブの数を取得
		int numberOfTab = getPortal().getTab().getNumberOfTab();
		
		// 初めに追加するタブのIDを取得
		String tab1Id = getPortal().getTab().addTab();
		// 繰り返す回数 = 制限値-固定タブ数-最初と最後の2回
		int numberOftabToAdd = ISConstants.DEFAULT_MAX_TABS-numberOfTab-2;
		// タブを制限値まで追加
		for (int i = 0; i < numberOftabToAdd; i++) {
			getPortal().getTab().addTab();
		}
		//最後に追加するタブのIDを取得
		String tabLastId = getPortal().getTab().addTab();
		
		//一つ目のタブの左上の点のY座標 < 最後のタブの左上の点のY座標 になっていることを確認
		int tab1Y0 = getPortal().getTab().getTabElement(tab1Id).getLocation().getY();
		int tabLastY0 = getPortal().getTab().getTabElement(tabLastId).getLocation().getY();
		assertTrue(tab1Y0 + "<" + tabLastY0 , tab1Y0 < tabLastY0);
	}
}
