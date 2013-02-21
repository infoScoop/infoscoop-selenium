package org.infoscoop_selenium.screenshot;


import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * タブUIのスクリーンショット
 * @author nishiumi
 *
 */
public class TabScreenShot extends IS_BaseItTestCase{
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
	}

	@Test
	/**
	 * タブメニュー（固定タブ）
	 */
	public void タブメニュー_固定タブ(){
		WebDriver driver = getDriver();
		
		// login
		getPortal().login("test_user1", "password");

		TestHelper.getScreenShot("タブメニュー（固定タブ）", getDriver());

		assertTrue(true);
	}
	
	private static void sleep(long sleep){
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
