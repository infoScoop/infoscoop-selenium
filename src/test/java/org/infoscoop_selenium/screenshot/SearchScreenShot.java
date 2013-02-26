package org.infoscoop_selenium.screenshot;


import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.commandbar.PortalPreference;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * ログインのスクリーンショット
 * @author nishiumi
 *
 */
public class SearchScreenShot extends IS_BaseItTestCase{
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login("test_user2", "password");
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
	}

	@Test
	/**
	 * 検索
	 */
	public void 検索(){
		WebDriver driver = getDriver();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();

		// 入力
		WebElement searchText = driver.findElement(By.id("searchTextInput"));
		searchText.sendKeys("infoscoop");
		searchText.sendKeys(Keys.RETURN);
		
		// 表示を待つ
		TestHelper.waitPresent(driver, By.id("search-tabs"));
		TestHelper.waitInvisible(driver, By.xpath("//ul[@id='search-tabs']/li[1]/a/img[@class='indicator']"));
		TestHelper.switchToFrame(driver, "Bing_frame");
		
		TestHelper.getScreenShot("検索", driver);

		assertTrue(true);
	}
	
	@Test
	/**
	 * 検索オプション
	 */
	public void 検索オプション(){
		WebDriver driver = getDriver();

		// 検索オプションの表示
		TestHelper.waitPresent(driver, By.id("searchIcon"));
		driver.findElement(By.id("searchIcon")).click();
		
		// 表示を待つ
		TestHelper.waitPresent(driver, By.id("searchoption"));
		
		TestHelper.getScreenShot("検索オプション", driver);

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
