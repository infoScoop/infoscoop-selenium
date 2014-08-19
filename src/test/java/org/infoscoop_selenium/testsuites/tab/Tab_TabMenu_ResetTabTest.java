package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.*;

import java.util.List;

import org.infoscoop_selenium.Portal;
import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.infoscoop_selenium.portal.Gadget;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * タブ/タブメニュー/タブの構成を初期化する
 */
public class Tab_TabMenu_ResetTabTest extends IS_BaseItTestCase{
	WebDriver driver;
	
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
	 * iscp-5767 確認ダイアログの表示
	 */
	public void iscp_5767(){
		// タブメニューの表示
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		
		// タブの構成を初期化するをクリック
		WebElement menuItem = getPortal().getTab().getInitializeItemElement(ISConstants.TABID_HOME);
		
		menuItem.click();
		Alert confirm = getDriver().switchTo().alert();
		String text = confirm.getText();
		int idx = text.indexOf("追加したガジェットを全て削除します、よろしいですか？\n(削除したガジェットはごみ箱に入ります)");
		
		assertEquals(idx, 0);
	}

	@Test
	/**
	 * iscp-5768 確認してキャンセル
	 */
	public void iscp_5768(){
		// ガジェットのドロップ
		Gadget addedGadget = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_calculator", 1);
		
		// タブメニューの表示
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		
		// タブの構成を初期化するをクリック
		WebElement menuItem = getPortal().getTab().getInitializeItemElement(ISConstants.TABID_HOME);
		
		menuItem.click();
		Alert confirm = getDriver().switchTo().alert();
    	confirm.dismiss();
		
		//ポータル再表示
		getPortal().login();
		
		// 追加したガジェットの存在チェック
		Boolean existGadget = false;
		List<WebElement> elements = getPortal().getPanel(ISConstants.TABID_HOME).getGadgetElements();
		for( int i=0;i<elements.size();i++ ) {
			WebElement el = (WebElement) elements.get(i);
			String id = el.getAttribute("id");
			
			if( id.equals(addedGadget.getId())){
				existGadget = true;
				break;
			}
		}
		// 存在すれば成功
		assertEquals(existGadget, true);
	}

	@Test
	/**
	 * iscp-5769 確認して承諾
	 */
	public void iscp_5769(){
		// ガジェットのドロップ
		Gadget addedGadget = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_calculator", 1);
		
		// タブメニューの表示
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		
		// タブの構成を初期化するをクリック
		WebElement menuItem = getPortal().getTab().getInitializeItemElement(ISConstants.TABID_HOME);
		
		menuItem.click();
		Alert confirm = getDriver().switchTo().alert();
    	confirm.accept();
		
		//ポータルのロードを待機
    	Portal.waitPortalLoadComplete(getDriver());
		
		// 追加したガジェットの存在チェック
		Boolean existGadget = false;
		List<WebElement> elements = getPortal().getPanel(ISConstants.TABID_HOME).getGadgetElements();
		for( int i=0;i<elements.size();i++ ) {
			WebElement el = (WebElement) elements.get(i);
			String id = el.getAttribute("id");
			if( id.equals(addedGadget.getId())){
				existGadget = true;
				break;
			}
		}
		// 存在しなければ成功
		assertEquals(existGadget, false);
	}

	@Test
	/**
	 * iscp-5770 ウィジェットの初期化後のゴミ箱の確認
	 */
	public void iscp_5770(){
		//ゴミ箱アイテム数の取得
		int trashedItemCount1 = getPortal().getCommandBar().getTrashBox().getTrashedItemCount();
		
		// ガジェットのドロップ
		getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_calculator", 1);
		
		// タブメニューの表示
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		
		// タブの構成を初期化するをクリック
		WebElement menuItem = getPortal().getTab().getInitializeItemElement(ISConstants.TABID_HOME);
		menuItem.click();
		Alert confirm = getDriver().switchTo().alert();
    	confirm.accept();
		
		//ポータルのロードを待機
    	Portal.waitPortalLoadComplete(getDriver());
		
		//ゴミ箱アイテム数の取得
		int trashedItemCount2 = getPortal().getCommandBar().getTrashBox().getTrashedItemCount();
		
		//ゴミ箱のアイテム数が１つ増えていれば成功
		assertEquals(trashedItemCount1 + 1, trashedItemCount2);
	}

//	@Test
	/**
	 * iscp-5771 初期化で削除されたウィジェットをゴミ箱から戻す
	 */
	public void iscp_5771(){
		// ガジェットのドロップ
		Gadget gadget = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_calculator", 1);
		
		// タブメニューの表示
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		
		// タブの構成を初期化するをクリック
		WebElement menuItem = getPortal().getTab().getInitializeItemElement(ISConstants.TABID_HOME);
		menuItem.click();
		Alert confirm = getDriver().switchTo().alert();
    	confirm.accept();
		
		//ポータルのロードを待機
    	Portal.waitPortalLoadComplete(getDriver());
		
		//ゴミ箱アイテム数の取得
		int trashedItemCount = getPortal().getCommandBar().getTrashBox().getTrashedItemCount();
		
		//一番最後のアイテムのコンテキストメニューを開く
		getPortal().getCommandBar().getTrashBox().showContextMenu(trashedItemCount);

		//元に戻すをクリック
		WebElement menu = getPortal().getCommandBar().getTrashBox().getResetMenuItem();
		menu.click();
		
		getPortal().getCommandBar().getTrashBox().hide();
		
		assertEquals(getDriver().findElement(By.id(gadget.getId())).isDisplayed(), true);
	}

	@Test
	/**
	 * iscp-5772 別タブに移動されたウィジェットは初期化により削除されない
	 */
	public void iscp_5772(){
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();
		
		// ホームタブの表示
		getPortal().getTab().selectTab(ISConstants.TABID_HOME);
		
		// ガジェットのドロップ
		Gadget gadget = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_calculator", 1);
		
		// ガジェットの移動
		gadget.moveTab(addedTabId);
		
		// タブメニューの表示
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		
		// タブの構成を初期化するをクリック
		WebElement menuItem = getPortal().getTab().getInitializeItemElement(ISConstants.TABID_HOME);
		menuItem.click();
		Alert confirm = getDriver().switchTo().alert();
    	confirm.accept();
    	
		//ポータルのロードを待機
    	Portal.waitPortalLoadComplete(getDriver());
		
		// 追加したタブの表示
		getPortal().getTab().selectTab(addedTabId);
    	
		// ガジェットが存在すれば成功
		assertEquals(getDriver().findElement(By.id(gadget.getId())).isDisplayed(), true);
	}
	
//	@Test
	/**
	 * iscp-5773 ウィジェットの初期化 -固定パネル
	 */
	public void iscp_5773(){
	}
}
