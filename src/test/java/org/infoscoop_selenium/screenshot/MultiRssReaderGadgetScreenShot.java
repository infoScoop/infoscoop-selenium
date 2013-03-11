package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.RssReaderGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * マルチRSSリーダーのスクリーンショット
 * @author mikami
 *
 */
public class MultiRssReaderGadgetScreenShot extends IS_BaseItTestCase {
	private static RssReaderGadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// ガジェットのドロップ
		GADGET = (RssReaderGadget)getPortal().getTopMenu().dropFolder("sports", 1, GADGET_TYPE.RSSREADER);
		
		// 表示を待つ
		TestHelper.waitInvisible(getDriver(), By.id(GADGET.getId()+"_widgetIndicator"));
	}

	@Test
	/**
	 * マルチRSSリーダーガジェット
	 */
	public void マルチRSSリーダーガジェット(){
		WebDriver driver = getDriver();		

		TestHelper.getScreenShot("マルチRSSリーダーガジェット", driver);

		assertTrue(true);
	}
	
	@Test
	/**
	 * マルチRSSリーダーガジェット（ガジェットメニュー）
	 */
	public void マルチRSSリーダーガジェット_ガジェットメニュー(){
		WebDriver driver = getDriver();
		
		// ガジェットメニューを開く
		GADGET.openMenu();
		
		TestHelper.getScreenShot("マルチRSSリーダーガジェット（ガジェットメニュー）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * マルチRSSリーダーガジェット（ガジェット設定）
	 */
	public void マルチRSSリーダーガジェット_ガジェット設定(){
		WebDriver driver = getDriver();
		
		//ガジェット設定を開く
		GADGET.getGadgetPreference().show();
				
		TestHelper.getScreenShot("マルチRSSリーダーガジェット（ガジェット設定）", driver);
	
		assertTrue(true);
	}

	@Test
	/**
	 * マルチRSSリーダーガジェット（改行）
	 */
	public void マルチRSSリーダーガジェット_改行(){
		WebDriver driver = getDriver();
		
		//ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		//改行ありに設定
		GADGET.changeLineFeed(true);
		GADGET.getGadgetPreference().ok();
		
		TestHelper.getScreenShot("マルチRSSリーダーガジェット（改行）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * マルチRSSリーダーガジェット（日付表示）
	 */
	public void マルチRSSリーダーガジェット_日付表示(){
		WebDriver driver = getDriver();
		
		//ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		//日付表示なしに設定
		GADGET.changeDatetime(false);
		GADGET.getGadgetPreference().ok();
		
		TestHelper.getScreenShot("マルチRSSリーダーガジェット（日付表示）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * マルチRSSリーダーガジェット（時間順表示）
	 */
	public void マルチRSSリーダーガジェット_時間順表示(){
		WebDriver driver = getDriver();
		
		//時間順表示設定
		GADGET.sortDate();
		
		TestHelper.getScreenShot("マルチRSSリーダーガジェット（時間順表示）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * マルチRSSリーダーガジェット（最大化）
	 */
	public void マルチRSSリーダーガジェット_最大化(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		//表示を待つ
		TestHelper.waitInvisible(driver, By.id("__Maximize__"+GADGET.getId()+"_widgetIndicator"));
		
		TestHelper.getScreenShot("マルチRSSリーダーガジェット（最大化）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * マルチRSSリーダーガジェット（最大化／ページング）
	 */
	public void マルチRSSリーダーガジェット_ページング() {
		WebDriver driver = getDriver();

		//最大化
		GADGET.maximaize();
		
		//表示を待つ
		TestHelper.waitInvisible(driver, By.id("__Maximize__"+GADGET.getId()+"_widgetIndicator"));

		// スクロールする
		GADGET.scrollForMaximize();
		
		sleep(100);

		TestHelper.getScreenShot("マルチRSSリーダーガジェット（最大化／ページング）", driver);
	}
	
	@Test
	/**
	 * マルチRSSリーダーガジェット（最大化／HTML表示）
	 */
	public void マルチRSSリーダーガジェット_最大化_HTML表示(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		//表示を待つ
		TestHelper.waitInvisible(driver, By.id("__Maximize__"+GADGET.getId()+"_widgetIndicator"));
		
		//HTML表示クリック
		GADGET.clickHtmlDisp();
		
		TestHelper.getScreenShot("マルチRSSリーダーガジェット（最大化／HTML表示）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * マルチRSSリーダーガジェット（最大化／日付表示）
	 */
	public void マルチRSSリーダーガジェット_最大化_日付表示(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		//表示を待つ
		TestHelper.waitInvisible(driver, By.id("__Maximize__"+GADGET.getId()+"_widgetIndicator"));
		
		//日付表示クリック
		GADGET.clickDate();
		
		TestHelper.getScreenShot("マルチRSSリーダーガジェット（最大化／日付表示）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * マルチRSSリーダーガジェット（最大化／絞込みパネル）
	 */
	public void マルチRSSリーダーガジェット_最大化_絞込みパネル(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		//表示を待つ
		TestHelper.waitInvisible(driver, By.id("__Maximize__"+GADGET.getId()+"_widgetIndicator"));
		
		//絞込みパネルを開く
		GADGET.openFilter();
		
		TestHelper.getScreenShot("マルチRSSリーダーガジェット（最大化／絞込みパネル）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * マルチRSSリーダーガジェット（最大化／カテゴリ選択）
	 */
	public void マルチRSSリーダーガジェット_最大化_カテゴリ選択(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		//表示を待つ
		TestHelper.waitInvisible(driver, By.id("__Maximize__"+GADGET.getId()+"_widgetIndicator"));

		//カテゴリ選択を開く
		WebElement element = driver.findElement(By.id("__Maximize__"+GADGET.getId()));
		WebElement element2 = element.findElement(By.id("maximizeCategoryCombobox"));
		element2.findElement(By.className("pulldown_icon")).click();
		
		sleep(100);
		
		TestHelper.getScreenShot("マルチRSSリーダーガジェット（最大化／カテゴリ選択）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * マルチRSSリーダーガジェット（最大化／メッセージダイアログ）
	 */
	public void マルチRSSリーダーガジェット_最大化_メッセージダイアログ(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		//表示を待つ
		TestHelper.waitInvisible(driver, By.id("__Maximize__"+GADGET.getId()+"_widgetIndicator"));
		
		//メッセージダイアログを開く
		GADGET.openMessageDialog();
		
		TestHelper.getScreenShot("マルチRSSリーダーガジェット（最大化／メッセージダイアログ）", driver);
	
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
