package org.infoscoop_selenium.testsuites.other_layout;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.AdminPage;
import org.infoscoop_selenium.Portal;
import org.infoscoop_selenium.WindowManager;
import org.infoscoop_selenium.admin_page.OtherLayoutAdminPage;
import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OtherLayout_Title_CoordinationTest extends IS_BaseItTestCase {
	OtherLayoutAdminPage otherLayoutAdminPage;
	String portalWindowHandle;
	String adminWindowHandle;
	
	@Override
	public void doBefore() {
		portalWindowHandle = getDriver().getWindowHandle();
		
		// テストケースごとの事前処理
		// login
		getPortal().login();
		
		AdminPage adminPage = getPortal().openAdminPage();
		otherLayoutAdminPage = adminPage.openOtherLayoutAdminPage();
		
		adminWindowHandle = adminPage.getDriver().getWindowHandle();
	}

	@Override
	public void doAfter() {
		// 管理画面へスイッチ
		WindowManager.getInstance().switchWindow(adminWindowHandle);
		
		// 設定初期化
		otherLayoutAdminPage.inputTitle("infoScoop");
		otherLayoutAdminPage.applySettings();
		
		getDriver().quit();
	}
	
	@Test
	/**
	 * infoScoop本体との連携
	 */
	public void iscp_2164(){
		String testTitleStr = "TestTitle.";
		
		// タイトルの変更
		otherLayoutAdminPage.inputTitle(testTitleStr);
		
		// タイトル適用
		otherLayoutAdminPage.applySettings();
		
		// ポータル画面へスイッチ
		WindowManager.getInstance().switchWindow(portalWindowHandle);
		
		// ポータルのリロード
		getDriver().navigate().refresh();
		Portal.waitPortalLoadComplete(getDriver());
		
		// 適用した値がinfoScoopのウィンドウタイトルに反映されていることを確認
		assertEquals(testTitleStr, getDriver().getTitle());
	}
	
	@Test
	/**
	 * infoScoop本体との連携 -ログイン画面
	 */
	public void iscp_2165(){
		String testTitleStr = "TestTitle.";
		
		// タイトルの変更
		otherLayoutAdminPage.inputTitle(testTitleStr);
		
		// タイトル適用
		otherLayoutAdminPage.applySettings();
		
		// ポータル画面へスイッチ
		WindowManager.getInstance().switchWindow(portalWindowHandle);
		
		// ログイン画面へ移動
		getPortal().toLoginPage();
		
		// ウィンドウタイトルの解析
		// 「[タイトル名] ログイン」なので、半角スペースでsplitする
		String loginWindowTitle = getDriver().getTitle().split(" ")[0];

		// 適用した値がログイン画面のウィンドウタイトルに反映されていることを確認
		assertEquals(testTitleStr, loginWindowTitle);
}
	
	@Test
	/**
	 * infoScoop本体との連携 -ログイン画面2
	 */
	public void iscp_2166(){
		String testTitleStr = "TestTitle.";
		
		// タイトルの変更
		otherLayoutAdminPage.inputTitle(testTitleStr);
		
		// タイトル適用
		otherLayoutAdminPage.applySettings();
		
		// ポータル画面へスイッチ
		WindowManager.getInstance().switchWindow(portalWindowHandle);
		
		// ログイン画面へ移動
		getPortal().toLoginPage();
		
		WebElement loginFormTitleEl = getDriver().findElement(By.cssSelector("#loginform .header:first-child"));
		// ログインフォームタイトルの解析
		// 「[タイトル名] ログイン」なので、半角スペースでsplitする
		String loginFormTitle = loginFormTitleEl.getText().split(" ")[0];
		
		// 適用した値がログインフォームのタイトルに反映されていることを確認
		assertEquals(testTitleStr, loginFormTitle);
	}

}
