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
		
		// サイトメニューボタンの表示を待つ
		TestHelper.waitPresent(driver, By.id("siteMenuOpen"));
		driver.findElement(By.id("openImage")).click();
		
		// サイトメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("portal-tree-menucontainer"));
		
		TestHelper.getScreenShot("サイトメニュー（ツリーを閉じた状態）", getDriver());

		assertTrue(true);
	}
	
	@Test
	/**
	 * サイトメニュー（ツリーを開いた状態）
	 */
	public void サイトメニュー_ツリーを開いた状態(){
		WebDriver driver = getDriver();
		
		// サイトメニューボタンの表示を待つ
		TestHelper.waitPresent(driver, By.id("siteMenuOpen"));
		driver.findElement(By.id("openImage")).click();
		
		// サイトメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("portal-tree-menucontainer"));
		
		// 全て展開リンクの生成を待つ
		TestHelper.waitPresent(driver, By.className("showAll"));
		driver.findElement(By.className("showAll")).click();
		
		// ツリーが展開するのを待つ
		TestHelper.waitPresent(driver, By.id("t_etcWidgets_WidgetRanking"));

		TestHelper.getScreenShot("サイトメニュー（ツリーを開いた状態）", getDriver());

		assertTrue(true);
	}

	@Test
	/**
	 * コンテンツ追加（RSS）
	 */
	public void コンテンツ追加_RSS(){
		WebDriver driver = getDriver();
		
		// サイトメニューボタンの表示を待つ
		TestHelper.waitPresent(driver, By.id("siteMenuOpen"));
		driver.findElement(By.id("openRssSearch")).click();
		
		// サイトメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("portal-tree-menucontainer"));
		
		// URLを入力する
		driver.findElement(By.xpath("//div[@class='SidePanel_AddContents']/div[2]/input[@type='text']")).sendKeys("http://www.infoscoop.org/index.php/ja/news.feed");
		
		// プレビューボタンをクリック
		driver.findElement(By.xpath("//div[@class='SidePanel_AddContents']/div[3]/input[@type='button']")).click();

		// ロードを待つ
		TestHelper.waitInvisible(driver, By.xpath("//div[@class='SidePanel_AddContents']/div[3]/img"));

		TestHelper.getScreenShot("コンテンツ追加（RSS）", getDriver());

		assertTrue(true);
	}
	
	@Test
	/**
	 * コンテンツ追加（Webサイト）
	 */
	public void コンテンツ追加_Webサイト(){
		WebDriver driver = getDriver();
		
		// サイトメニューボタンの表示を待つ
		TestHelper.waitPresent(driver, By.id("siteMenuOpen"));
		driver.findElement(By.id("openRssSearch")).click();
		
		// サイトメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("portal-tree-menucontainer"));
		
		// URLを入力する
		driver.findElement(By.xpath("//div[@class='SidePanel_AddContents']/div[2]/input[@type='text']")).sendKeys("http://www.infoscoop.org/index.php/ja/");
		
		// プレビューボタンをクリック
		driver.findElement(By.xpath("//div[@class='SidePanel_AddContents']/div[3]/input[@type='button']")).click();

		// ロードを待つ
		TestHelper.waitInvisible(driver, By.xpath("//div[@class='SidePanel_AddContents']/div[3]/img"));

		TestHelper.getScreenShot("コンテンツ追加（Webサイト）", getDriver());

		assertTrue(true);
	}
	
	@Test
	/**
	 * コンテンツ追加（ガジェット）
	 */
	public void コンテンツ追加_ガジェット(){
		WebDriver driver = getDriver();
		
		// サイトメニューボタンの表示を待つ
		TestHelper.waitPresent(driver, By.id("siteMenuOpen"));
		driver.findElement(By.id("openRssSearch")).click();
		
		// サイトメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("portal-tree-menucontainer"));
		
		// URLを入力する
		driver.findElement(By.xpath("//div[@class='SidePanel_AddContents']/div[2]/input[@type='text']")).sendKeys("http://www.infoscoop.org/gadgets/hello.xml");
		
		// プレビューボタンをクリック
		driver.findElement(By.xpath("//div[@class='SidePanel_AddContents']/div[3]/input[@type='button']")).click();

		// ロードを待つ
		TestHelper.waitInvisible(driver, By.xpath("//div[@class='SidePanel_AddContents']/div[3]/img"));

		TestHelper.getScreenShot("コンテンツ追加（ガジェット）", getDriver());

		assertTrue(true);
	}
	
	@Test
	/**
	 * マイサイトマップ（ツリーを開いた状態）
	 */
	public void マイサイトマップ_ツリーを開いた状態(){
		WebDriver driver = getDriver();
		
		// マイサイトマップボタンの表示を待つ
		TestHelper.waitPresent(driver, By.id("siteMenuOpen"));
		driver.findElement(By.className("mySiteMapOpen")).click();
		
		// マイサイトマップの表示を待つ
		TestHelper.waitPresent(driver, By.id("portal-tree-menucontainer"));

		TestHelper.getScreenShot("マイサイトマップ（ツリーを開いた状態）", getDriver());

		assertTrue(true);
	}
	
	@Test
	/**
	 * マイサイトマップ（ツリーを閉じた状態）
	 */
	public void マイサイトマップ_ツリーを閉じた状態(){
		WebDriver driver = getDriver();
		
		// マイサイトマップボタンの表示を待つ
		TestHelper.waitPresent(driver, By.id("siteMenuOpen"));
		driver.findElement(By.className("mySiteMapOpen")).click();
		
		// マイサイトマップの表示を待つ
		TestHelper.waitPresent(driver, By.id("portal-tree-menucontainer"));
		
		// 全て閉じるリンクの生成を待つ
//		TestHelper.waitPresent(driver, By.className("hideAll"));
		driver.findElement(By.className("hideAll")).click();
		
		// ツリーが展開するのを待つ
		TestHelper.waitPresent(driver, By.className("showAll"));

		TestHelper.getScreenShot("マイサイトマップ（ツリーを閉じた状態）", getDriver());

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
