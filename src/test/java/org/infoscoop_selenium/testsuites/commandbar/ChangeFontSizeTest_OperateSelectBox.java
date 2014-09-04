package org.infoscoop_selenium.testsuites.commandbar;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.CommandBar;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ChangeFontSizeTest_OperateSelectBox extends IS_BaseItTestCase{
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// フォントサイズの初期化
		CommandBar commandBar = getPortal().getCommandBar();
		commandBar.openMenu();
		commandBar.selectMenuChangeFontSizeElement();
		commandBar.selectMiddleOfFontSizeSelectBox();
	}
	
	@Test
	/**
	 * 大きくする
	 */
	public void iscp_6777 () {
		CommandBar commandBar = getPortal().getCommandBar();
		
		//デフォルトのbodyの文字サイズを取得
		String fontsizeBefore = commandBar.getCurrentBodyFontSize().replace("px", "");
		
		//コマンドバーを開く
		commandBar.openMenu();
		
		//文字サイズ変更の項目があることを確認する
		WebElement changeFontSize = commandBar.getMenuChangeFontSizeElement();
		assertTrue(changeFontSize.isDisplayed());
		
		//文字サイズのプルダウンを開く
		commandBar.selectMenuChangeFontSizeElement();
		
		//大を選択
		commandBar.selectLargeOfFontSizeSelectBox();
		
		//メニューが閉じることを確認
		assertTrue(!changeFontSize.isDisplayed());
		
		//変更後の文字サイズを取得して+20%になっていることを確認
		String fontsizeAfter = commandBar.getCurrentBodyFontSize().replace("px", "");
		//assertEquals(Double.parseDouble(fontsizeBefore) * 1.2, Double.parseDouble(fontsizeAfter));
		Double expected = Double.parseDouble(fontsizeBefore) * 1.2;
		expected = new BigDecimal(expected).setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
		Double actual = Double.parseDouble(fontsizeAfter);
		assertTrue(expected.compareTo(actual) == 0);
	}
}
