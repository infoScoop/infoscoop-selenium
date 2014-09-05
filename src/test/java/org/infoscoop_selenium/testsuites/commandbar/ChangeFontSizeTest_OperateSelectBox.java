package org.infoscoop_selenium.testsuites.commandbar;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.infoscoop_selenium.AdminPage;
import org.infoscoop_selenium.Portal;
import org.infoscoop_selenium.admin_page.CommandBarAdminPage;
import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.CommandBar;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ChangeFontSizeTest_OperateSelectBox extends IS_BaseItTestCase{
	
	@BeforeClass
	public static void doBeforeClass() {
		
	}
	
	//TODO 管理画面の操作はテストケースごとではなく始めに一回だけ行うようにする
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		Portal portal = getPortal();
		portal.login();
		
		//管理画面で文字サイズ変更の項目をメニュー内に表示させる
		AdminPage adminPage = portal.openAdminPage();
		CommandBarAdminPage commandBarAdminPage = adminPage.openCommandBarAdminPage();
		commandBarAdminPage.openDefaultRole();
		commandBarAdminPage.selectDisplay();
		commandBarAdminPage.applySettings();
		adminPage.close();
		
		// フォントサイズの初期化
		getDriver().navigate().refresh();
		TestHelper.sleep(1000);
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
		Double expected = Double.parseDouble(fontsizeBefore) * 1.2;
		expected = new BigDecimal(expected).setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
		Double actual = Double.parseDouble(fontsizeAfter);
		assertTrue(expected.compareTo(actual) == 0);
	}
	
	@Test
	/**
	 * 標準に戻す
	 */
	public void iscp_6778 () {
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
		Double expected = Double.parseDouble(fontsizeBefore) * 1.2;
		expected = new BigDecimal(expected).setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
		Double actual = Double.parseDouble(fontsizeAfter);
		assertTrue(expected.compareTo(actual) == 0);
		
		//中に戻す
		commandBar.openMenu();
		commandBar.selectMenuChangeFontSizeElement();
		commandBar.selectMiddleOfFontSizeSelectBox();
		
		//メニューが閉じることを確認
		assertTrue(!changeFontSize.isDisplayed());
		//変更後の文字サイズを取得して-20%になっていることを確認
		String fontsizeAfter2 = commandBar.getCurrentBodyFontSize().replace("px", "");
		Double expected2 = Double.parseDouble(fontsizeBefore);
		Double actual2 = Double.parseDouble(fontsizeAfter2);
		assertTrue(expected2.compareTo(actual2) == 0);
	}
	
	@Test
	/**
	 * 小さくする
	 */
	public void iscp_6779 () {
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
		
		//小を選択
		commandBar.selectSmallOfFontSizeSelectBox();
		
		//メニューが閉じることを確認
		assertTrue(!changeFontSize.isDisplayed());
		
		//変更後の文字サイズを取得して-20%になっていることを確認
		String fontsizeAfter = commandBar.getCurrentBodyFontSize().replace("px", "");
		Double expected = Double.parseDouble(fontsizeBefore) * 0.95;
		expected = new BigDecimal(expected).setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
		Double actual = Double.parseDouble(fontsizeAfter);
		assertTrue(expected.compareTo(actual) == 0);
	}
}
