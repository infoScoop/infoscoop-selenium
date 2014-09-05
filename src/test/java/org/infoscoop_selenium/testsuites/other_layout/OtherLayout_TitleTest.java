package org.infoscoop_selenium.testsuites.other_layout;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.admin_page.OtherLayoutAdminPage;
import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.junit.Test;

public class OtherLayout_TitleTest extends IS_BaseItTestCase {
	OtherLayoutAdminPage otherLayoutAdminPage;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();
		
		otherLayoutAdminPage = getPortal().openAdminPage().openOtherLayoutAdminPage();
	}

	@Override
	public void doAfter() {
		// 設定初期化
		otherLayoutAdminPage.inputTitle("infoScoop");
		otherLayoutAdminPage.applySettings();
		
		getDriver().quit();
	}
	
	@Test
	/**
	 * 設定の適用
	 */
	public void iscp_2161(){
		String testTitleStr = "TestTitle.";
		
		// タイトルの変更
		otherLayoutAdminPage.inputTitle(testTitleStr);
		
		// タイトル適用
		otherLayoutAdminPage.applySettings();
		
		// 読み直し
		otherLayoutAdminPage.reloadSettings();
		
		// タイトルが適用されていることの確認
		assertEquals(testTitleStr, otherLayoutAdminPage.getTitle());
	}
	
	@Test
	/**
	 * 特殊文字
	 */
	public void iscp_2162(){
		String inputStr = "①㈱㌢<>&\"'!\"#$%'()~=-^@`'*+;:[{]}\\_/?<>\\.タダイマポチゼンブアソボゾウノファミリ―～∥－￤";
		
		// タイトルの変更
		otherLayoutAdminPage.inputTitle(inputStr);
		
		// タイトル適用
		otherLayoutAdminPage.applySettings();
		
		// 読み直し
		otherLayoutAdminPage.reloadSettings();
		
		// タイトルが適用されていることの確認
		assertEquals(inputStr, otherLayoutAdminPage.getTitle());
	}
	
	@Test
	/**
	 * 境界値
	 */
	public void iscp_2163(){
		String inputStr = "";
		
		// 1025バイトの文字列生成
		for(int i=0;i<1025;i++){
			inputStr += "a";
		}
		
		// タイトルの変更
		otherLayoutAdminPage.inputTitle(inputStr);
		
		// タイトル適用
		otherLayoutAdminPage.applySettings();
		
		// 読み直し
		otherLayoutAdminPage.reloadSettings();
		
		// 設定文字列の長さが欠けていないことを確認
		assertEquals(inputStr.length(), otherLayoutAdminPage.getTitle().length());
	}

}
