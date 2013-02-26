package org.infoscoop_selenium.test;


import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class TabTest extends IS_BaseItTestCase{
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
		// カスタマイズ情報の初期化後、ログアウトなど
		/*
		getIscoop().initializeData();
		getIscoop().logout();
		*/
	}

	@Test
	/**
	 * ログイン後、一番左側のタブがアクティブ状態で表示されることを確認
	 */
	public void iscp_5714(){
		WebDriver driver = getDriver();
		
		// login
		getPortal().login("test_user1", "password");
		
		// 一度初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// 一番左側のタブがアクティブ状態で表示されることを確認
		TestHelper.waitPresent(driver, By.xpath("//li[@id='tab0' and @class='tab selected']"));
		
		// 出現すれば処理が通るのでassert=true
		assertTrue(true);
	}
	
	@Test
	/**
	 * 非アクティブタブをクリックすると、クリックしたタブへアクティブ状態が遷移することを確認
	 */
	public void iscp_5715(){
		// login
		getPortal().login("test_user1", "password");
		
		// 一度初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// タブ追加
		getPortal().getTab().addTab();
		
		// HOMEタブクリック
		getPortal().getTab().selectTab("tab0");
		
		// panel0が表示状態(display:block)であることを確認
		assertTrue(getDriver().findElement(By.id("panel0")).isDisplayed());
	}
	
	private static void sleep(long sleep){
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
