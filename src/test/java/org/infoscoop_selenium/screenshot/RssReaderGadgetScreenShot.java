package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.WindowManager;
import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.RssReaderGadget;
import org.infoscoop_selenium.portal.gadget.RssReaderGadget.DETAIL;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


/**
 * RSSリーダーのスクリーンショット
 * @author mikami
 *
 */
public class RssReaderGadgetScreenShot extends IS_BaseItTestCase {
	private static RssReaderGadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// ガジェットのドロップ
		GADGET = (RssReaderGadget)getPortal().getSideBar().addContents("http://www.infoscoop.org/index.php/ja/news.feed", GADGET_TYPE.RSSREADER);
	}

	@Test
	/**
	 * RSSリーダーガジェット
	 */
	public void RSSリーダーガジェット(){
		WebDriver driver = getDriver();		
		
		TestHelper.getScreenShot("RSSリーダーガジェット", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（ページング）
	 */
	public void RSSリーダーガジェット_ページング() {
		WebDriver driver = getDriver();
		// スクロールする
		GADGET.scroll();
		sleep(100);

		TestHelper.getScreenShot("RSSリーダーガジェット（ページング）", driver);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（ガジェットメニュー）
	 */
	public void RSSリーダーガジェット_ガジェットメニュー(){
		WebDriver driver = getDriver();
		
		// ガジェットメニューを開く
		GADGET.openMenu();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（ガジェットメニュー）", driver, true);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（ガジェット設定）
	 */
	public void RSSリーダーガジェット_ガジェット設定(){
		WebDriver driver = getDriver();
		
		//ガジェット設定を開く
		GADGET.getGadgetPreference().show();
				
		TestHelper.getScreenShot("RSSリーダーガジェット（ガジェット設定）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（詳細表示／インライン）
	 */
	public void RSSリーダーガジェット_詳細表示_インライン(){
		WebDriver driver = getDriver();
		
		// 詳細をクリック
		GADGET.showDetail(0);
		
		TestHelper.getScreenShot("RSSリーダーガジェット（詳細表示／インライン）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（詳細表示／ポップアップ）
	 */
	public void RSSリーダーガジェット_詳細表示_ポップアップ(){
		WebDriver driver = getDriver();
		
		//ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		//詳細表示モードをポップアップに設定
		GADGET.selectDetailDisplay(DETAIL.POPUP);
		GADGET.getGadgetPreference().ok();

		//詳細ボタンをクリック
		GADGET.showDetail(0);
		
		TestHelper.getScreenShot("RSSリーダーガジェット（詳細表示／ポップアップ）", driver);
	
		assertTrue(true);
	}

	@Test
	/**
	 * RSSリーダーガジェット（改行）
	 */
	public void RSSリーダーガジェット_改行(){
		WebDriver driver = getDriver();
		
		//ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		//詳細表示モードをポップアップに設定
		GADGET.changeLineFeed(true);
		GADGET.getGadgetPreference().ok();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（改行）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（日付表示）
	 */
	public void RSSリーダーガジェット_日付表示(){
		WebDriver driver = getDriver();
		
		//ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		//詳細表示モードをポップアップに設定
		GADGET.changeDatetime(false);
		GADGET.getGadgetPreference().ok();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（日付表示）", driver);
	
		assertTrue(true);
	}

	@Test
	/**
	 * RSSリーダーガジェット（タイトルフィルターヘルプ）
	 */
	public void RSSリーダーガジェット_タイトルフィルターヘルプ(){
		WebDriver driver = getDriver();
		
		//ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		//ヘルプにマウスオーバー
		GADGET.mouseOverTitleFilterHelp();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（タイトルフィルターヘルプ）", driver, true);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（作者フィルターヘルプ）
	 */
	public void RSSリーダーガジェット_作者フィルターヘルプ(){
		WebDriver driver = getDriver();
		
		//ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		//ヘルプにマウスオーバー
		GADGET.mouseOverCreatorFilterHelp();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（作者フィルターヘルプ）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（カテゴリフィルターヘルプ）
	 */
	public void RSSリーダーガジェット_カテゴリフィルターヘルプ(){
		WebDriver driver = getDriver();
		
		//ガジェット設定を開く
		GADGET.getGadgetPreference().show();
		
		//ヘルプにマウスオーバー
		GADGET.mouseOverCategoryFilterHelp();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（カテゴリフィルターヘルプ）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（最大化）
	 */
	public void RSSリーダーガジェット_最大化(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（最大化）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（最大化／HTML表示）
	 */
	public void RSSリーダーガジェット_最大化_HTML表示(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		//HTML表示クリック
		GADGET.clickHtmlDisp();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（最大化／HTML表示）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（最大化／日付表示）
	 */
	public void RSSリーダーガジェット_最大化_日付表示(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		//日付表示クリック
		GADGET.clickDate();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（最大化／日付表示）", driver);
	
		assertTrue(true);
	}

	@Test
	/**
	 * RSSリーダーガジェット（最大化／ヘルプ）
	 */
	public void RSSリーダーガジェット_最大化_ヘルプ(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		// 現在表示しているWindowIDを取得する
		
		// ヘルプを表示
		GADGET.openHelp();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（最大化／ヘルプ）", driver);
        
		// ヘルプを閉じる
		GADGET.closeHelp();
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（最大化／絞込みパネル）
	 */
	public void RSSリーダーガジェット_最大化_絞込みパネル(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		//絞込みパネルを開く
		GADGET.openFilter();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（最大化／絞込みパネル）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * RSSリーダーガジェット（最大化／メッセージダイアログ）
	 */
	public void RSSリーダーガジェット_最大化_メッセージダイアログ(){
		WebDriver driver = getDriver();
		
		//最大化
		GADGET.maximaize();
		
		//メッセージダイアログを開く
		GADGET.openMessageDialog();
		
		TestHelper.getScreenShot("RSSリーダーガジェット（最大化／メッセージダイアログ）", driver);
	
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
