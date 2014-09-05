package org.infoscoop_selenium.testsuites.commandbar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.CommandBar;
import org.infoscoop_selenium.portal.commandbar.SearchForm;
import org.infoscoop_selenium.properties.TestEnv;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CommandBar_DisplayTest extends IS_BaseItTestCase {

	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
//		getPortal().getCommandBar().getPortalPreference().initializeData();
	}

	@Test
	/**
	 * コマンドバーの表示 -初期状態
	 */
	public void iscp_6762(){
		// 表示中のコマンドバーアイテム取得
		List<WebElement> items = getPortal().getCommandBar().getDisplayItems();
		
		// 表示を確認。
		for(int i=0;i<items.size();i++){
			WebElement item = items.get(i);
			String id = item.getAttribute("id");
			
			switch(i){
				case 0:
					assertEquals(id, CommandBar.ITEM_LOGO_ID);
					break;
				case 1:
					assertEquals(id, CommandBar.ITEM_TICKER_ID);
					break;
				case 2:
					assertEquals(id, CommandBar.ITEM_SERARCHFORM_ID);
					break;
				case 3:
					assertEquals(id, CommandBar.ITEM_RANKING_ID);
					break;
				case 4:
					assertEquals(id, CommandBar.ITEM_TRASH_ID);
					break;
				case 5:
					assertEquals(id, CommandBar.ITEM_PREFERENCE_ID);
					break;
				case 6:
					assertEquals(id, CommandBar.ITEM_ADMINLINK_ID);
			}
			if(i > 6)
				assertTrue("There is a problem in the number of displays.", false);
		}
	}

//	@Test
	/**
	 * コマンドバーの表示 -ユーザー名
	 */
	public void iscp_6801(){
		/*
		CommandBar commandBar = getPortal().getCommandBar();

		// ユーザー名表示確認
		String userName = commandBar.getDisplayUserName();
		assertEquals(userName, TestEnv.getInstance().getUserId());

		// ユーザー名にマウスオーバー
		WebElement menu = commandBar.getMenu();
		Actions action = new Actions(getDriver());
		action.moveToElement(menu).build().perform();
		
		// 色が反転していることを確認
		*/
	}

//	@Test
	/**
	 * コマンドバーの表示 -ログインリンク
	 */
	public void iscp_6763(){
	}

//	@Test
	/**
	 * コマンドバーの表示 -未ログイン
	 */
	public void iscp_6764(){
	}
	
//	@Test
	/**
	 * コマンドバーの表示 -長いユーザ名
	 */
	public void iscp_6766(){
	}
	
	@Test
	/**
	 * コマンドバーの表示 -管理者権限のないユーザ
	 */
	public void iscp_6768(){
		// 管理権限のないユーザでログイン
		getPortal().login("test_user4", "password");
		
		// 表示中のコマンドバーアイテム取得
		List<WebElement> items = getPortal().getCommandBar().getDisplayItems();
		
		// 表示を確認。
		for(int i=0;i<items.size();i++){
			WebElement item = items.get(i);
			String id = item.getAttribute("id");
			
			// 管理者リンクが存在しないことを確認
			assertTrue(!id.equals(CommandBar.ITEM_ADMINLINK_ID));
		}
	}
	
//	@Test
	/**
	 * コマンドバーの表示 -ウィンドウリサイズ
	 */
	public void iscp_6769(){
	}
	
//	@Test
	/**
	 * メニューの表示 -初期状態
	 */
	public void iscp_6800(){
	}
	
//	@Test
	/**
	 * メニューの表示 -マウスオーバー
	 */
	public void iscp_6799(){
	}
	
//	@Test
	/**
	 * メニューの表示 -閉じる
	 */
	public void iscp_6798(){
	}
	
//	@Test
	/**
	 * メニューの表示 -ウィンドウリサイズ
	 */
	public void iscp_6770(){
	}
	
//	@Test
	/**
	 * ロゴの表示
	 */
	public void iscp_7302(){
	}
	
	@Test
	/**
	 * ロゴクリック時の動作
	 */
	public void iscp_7303(){
		CommandBar commandBar = getPortal().getCommandBar();
		
		// 検索を行ってポータル内フレームを表示
		SearchForm sf = commandBar.getSearchForm();
		sf.doSearch("aaa");
		
		// パネル非表示確認
		assertTrue(!getPortal().isPanelVisible());
		
		// ロゴクリック
		WebElement logo = commandBar.getLogo();
		logo.click();
		TestHelper.waitPresent(getDriver(), By.id(getPortal().getPanels().getAttribute("id")));
		
		// パネル表示確認
		assertTrue(getPortal().isPanelVisible());
	}
	
}
