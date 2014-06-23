package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
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
		//多段表示にするスタイルがあるかを確認
		String tabLiDisplayProperty = getPortal().getTab().getTabLiDisplayProperty();
		assertEquals("inline-block", tabLiDisplayProperty);
	}
}
