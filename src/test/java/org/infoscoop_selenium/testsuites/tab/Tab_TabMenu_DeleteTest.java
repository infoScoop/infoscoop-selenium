package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget;
import org.infoscoop_selenium.portal.gadget.GenericGadget;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * タブ/タブ削除
 */
public class Tab_TabMenu_DeleteTest extends IS_BaseItTestCase{
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
	}

	@Test
	/**
	 * iscp-5753:タブ削除
	 * [削除]をクリックすると、削除確認のダイアログが表示され、OKをクリックすると指定のタブが削除されることを確認
	 */
	public void iscp_5753(){
		// 初期タブ数
		int beforeNumberOfTab = getPortal().getTab().getNumberOfTab();

		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();

		//タブメニューの表示
		getPortal().getTab().selectSelectMenu(addedTabId);

		//削除メニューの選択
		WebElement closeItem = getPortal().getTab().getCloseItem(addedTabId);
		closeItem.findElement(By.cssSelector(".close")).click();
		Alert confirm = getDriver().switchTo().alert();
    	confirm.accept();

		int afterNumberOfTab = getPortal().getTab().getNumberOfTab();

		assertEquals(beforeNumberOfTab, afterNumberOfTab);
	}

	@Test
	/**
	 * iscp-5754:タブ削除/キャンセル
	 * 削除確認のダイアログをキャンセルするとタブが削除されないことを確認
	 */
	public void iscp_5754(){
		// 初期タブ数
		int beforeNumberOfTab = getPortal().getTab().getNumberOfTab();

		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();

		//タブメニューの表示
		getPortal().getTab().selectSelectMenu(addedTabId);

		//削除メニューの選択してダイアログをキャンセル
		WebElement closeItem = getPortal().getTab().getCloseItem(addedTabId);
		closeItem.findElement(By.cssSelector(".close")).click();
		Alert confirm = getDriver().switchTo().alert();
    	confirm.dismiss();
    	
		int afterNumberOfTab = getPortal().getTab().getNumberOfTab();
		
		assertEquals(beforeNumberOfTab + 1, afterNumberOfTab);
	}

	@Test
	/**
	 * iscp-5755:保存確認
	 * タブ削除後に画面の再読み込みを行っても、削除したタブが表示されないことを確認
	 */
	public void iscp_5755(){
		// 初期タブ数
		int beforeNumberOfTab = getPortal().getTab().getNumberOfTab();

		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();

		//タブメニューの表示
		getPortal().getTab().selectSelectMenu(addedTabId);

		//削除メニューの選択
		WebElement closeItem = getPortal().getTab().getCloseItem(addedTabId);
		closeItem.findElement(By.cssSelector(".close")).click();
		Alert confirm = getDriver().switchTo().alert();
    	confirm.accept();

		//ポータル再表示
		getPortal().login();

		int afterNumberOfTab = getPortal().getTab().getNumberOfTab();

		assertEquals(beforeNumberOfTab, afterNumberOfTab);
	}
	
	@Test
	/**
	 * iscp-5756:メニューグレーアウト復帰
	 * タブ削除後、削除したタブ内の全てのWidgetがメニューからグレーアウトから復帰することを確認。ドロップ可能なWidget全種で確認すること
	 */
	public void iscp_5756(){
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// ガジェットのドロップ
		getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_Message", 1);
		
		// メニューアイテムがグレーアウトしているか
		getPortal().getTopMenu().openTopMenu("etcWidgets");
		WebElement menuItem = getDriver().findElement(By.id("etcWidgets_Message"));
		assertTrue(menuItem.findElement(By.cssSelector(".menuItemIcon_dropped")).isDisplayed());
		getPortal().getTopMenu().closeMenu("etcWidgets");
		
		//タブメニューの表示
		getPortal().getTab().selectSelectMenu(addedTabId);

		//削除メニューの選択
		WebElement closeItem = getPortal().getTab().getCloseItem(addedTabId);
		closeItem.findElement(By.cssSelector(".close")).click();
		Alert confirm = getDriver().switchTo().alert();
    	confirm.accept();

		// ガジェットの再ドロップ
		Gadget gadget = (GenericGadget) getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_Message", 1);
		assertTrue(getDriver().findElement(By.id(gadget.getId())).isDisplayed());
	}
	


	@Test
	/**
	 * iscp-5757:ウィジェットの削除
	 * タブ削除後、削除したタブ内の全ての Widget がごみ箱に入る
	 */
	public void iscp_5757(){
		//ゴミ箱アイテム数の取得
		int trashedItemCount1 = getPortal().getCommandBar().getTrashBox().getTrashedItemCount();
		
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();

		// ガジェットのドロップ
		getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_calculator", 1);

		//タブメニューの表示
		getPortal().getTab().selectSelectMenu(addedTabId);

		//削除メニューの選択
		WebElement closeItem = getPortal().getTab().getCloseItem(addedTabId);
		closeItem.findElement(By.cssSelector(".close")).click();
		Alert confirm = getDriver().switchTo().alert();
    	confirm.accept();
    	
		//ゴミ箱アイテム数の取得
		int trashedItemCount2 = getPortal().getCommandBar().getTrashBox().getTrashedItemCount();
		
		//ゴミ箱のアイテム数が１つ増えていたら成功
		assertEquals(trashedItemCount1 + 1, trashedItemCount2);
	}

}
