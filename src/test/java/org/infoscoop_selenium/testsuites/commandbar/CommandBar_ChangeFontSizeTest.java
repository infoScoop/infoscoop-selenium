package org.infoscoop_selenium.testsuites.commandbar;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.CommandBar;
import org.junit.Test;

public class CommandBar_ChangeFontSizeTest extends IS_BaseItTestCase{

	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
//		getPortal().getCommandBar().getPortalPreference().initializeData();
	}
	
	//@Test
	/**
	 * ツールチップ
	 */
	public void iscp_7336 () {
		//コマンドバーを開く
		CommandBar commandBar = getPortal().getCommandBar();
		commandBar.openMenu();
		
		//コマンドバーをメニュー内の文字サイズ変更項目にマウスオーバー
		// title要素があることを確認
		
	}
	
}
