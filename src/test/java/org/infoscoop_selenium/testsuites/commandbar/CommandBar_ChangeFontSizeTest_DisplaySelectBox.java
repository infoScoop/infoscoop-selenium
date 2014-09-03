package org.infoscoop_selenium.testsuites.commandbar;

import static org.junit.Assert.*;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.CommandBar;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class CommandBar_ChangeFontSizeTest_DisplaySelectBox extends IS_BaseItTestCase{

	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
//		getPortal().getCommandBar().getPortalPreference().initializeData();
	}
	
	@Test
	/**
	 * セレクトボックス表示
	 */
	public void iscp_6773 () {
		//コマンドバーを開く
		CommandBar commandBar = getPortal().getCommandBar();
		commandBar.openMenu();
		
		//文字サイズ変更の項目があることを確認する
		WebElement changeFontSize = commandBar.getMenuChangeFontSizeElement();
		assertTrue(changeFontSize.isDisplayed());
		
		//文字サイズのプルダウンを開く
		commandBar.selectMenuChangeFontSizeElement();
		
		//文字サイズのプルダウンが大、中、小という項目になっていることを確認する
		//大の項目があることを確認
		WebElement large = commandBar.getMenuFontSizeLargeElement();
		WebElement middle = commandBar.getMenuFontSizeMiddleElement();
		WebElement small = commandBar.getMenuFontSizeSmallElement();
		assertTrue(large.isDisplayed());
		assertTrue(middle.isDisplayed());
		assertTrue(small.isDisplayed());
	}

}
