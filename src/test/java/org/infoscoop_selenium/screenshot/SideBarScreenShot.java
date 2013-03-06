package org.infoscoop_selenium.screenshot;


import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * サイドバーのスクリーンショット
 * @author nishiumi
 *
 */
public class SideBarScreenShot extends IS_BaseItTestCase{
	
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
	 * サイトメニュー（ツリーを閉じた状態）
	 */
	public void サイトメニュー_ツリーを閉じた状態(){
		WebDriver driver = getDriver();
		
		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		//サイトメニューを開く
		getPortal().getSideBar().openSiteMenu();
		
		TestHelper.getScreenShot("サイトメニュー（ツリーを閉じた状態）", driver);

		assertTrue(true);
	}
	
	@Test
	/**
	 * サイトメニュー（ツリーを開いた状態）
	 */
	public void サイトメニュー_ツリーを開いた状態(){
		WebDriver driver = getDriver();
		
		//サイトメニューを開く
		getPortal().getSideBar().openSiteMenu();
		
		// 全て展開リンクの生成を待つ
		TestHelper.waitPresent(driver, By.className("showAll"));
		driver.findElement(By.className("showAll")).click();
		
		// ツリーが展開するのを待つ
		TestHelper.waitPresent(driver, By.id("t_etcWidgets_WidgetRanking"));

		TestHelper.getScreenShot("サイトメニュー（ツリーを開いた状態）", driver);

		assertTrue(true);
	}

	@Test
	/**
	 * コンテンツ追加（RSS）
	 */
	public void コンテンツ追加_RSS(){
		WebDriver driver = getDriver();
		
		// RSSをプレビュー
		getPortal().getSideBar().previewContents("http://www.infoscoop.org/index.php/ja/news.feed");

		TestHelper.getScreenShot("コンテンツ追加（RSS）", driver);

		assertTrue(true);
	}
	
	@Test
	/**
	 * コンテンツ追加（Webサイト）
	 */
	public void コンテンツ追加_Webサイト(){
		WebDriver driver = getDriver();
		
		// Webサイトをプレビュー
		getPortal().getSideBar().previewContents("http://www.infoscoop.org/index.php/ja/");

		TestHelper.getScreenShot("コンテンツ追加（Webサイト）", driver);

		assertTrue(true);
	}
	
	@Test
	/**
	 * コンテンツ追加（ガジェット）
	 */
	public void コンテンツ追加_ガジェット(){
		WebDriver driver = getDriver();
		
		// Webサイトをプレビュー
		getPortal().getSideBar().previewContents("http://www.infoscoop.org/gadgets/hello.xml");

		TestHelper.getScreenShot("コンテンツ追加（ガジェット）", driver);

		assertTrue(true);
	}
	
	@Test
	/**
	 * マイサイトマップ（ツリーを開いた状態）
	 */
	public void マイサイトマップ_ツリーを開いた状態(){
		WebDriver driver = getDriver();
		
		// マイサイトマップを開く
		getPortal().getSideBar().openMySiteMap();

		TestHelper.getScreenShot("マイサイトマップ（ツリーを開いた状態）", driver);

		assertTrue(true);
	}
	
	@Test
	/**
	 * マイサイトマップ（ツリーを閉じた状態）
	 */
	public void マイサイトマップ_ツリーを閉じた状態(){
		WebDriver driver = getDriver();
		
		// マイサイトマップを開く
		getPortal().getSideBar().openMySiteMap();
		
		// 全て閉じるリンクの生成を待つ
//		TestHelper.waitPresent(driver, By.className("hideAll"));
		driver.findElement(By.className("hideAll")).click();
		
		// ツリーが展開するのを待つ
		TestHelper.waitPresent(driver, By.className("showAll"));

		TestHelper.getScreenShot("マイサイトマップ（ツリーを閉じた状態）", driver);

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
