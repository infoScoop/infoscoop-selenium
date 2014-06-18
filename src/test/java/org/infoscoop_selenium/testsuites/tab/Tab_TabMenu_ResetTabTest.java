package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.*;

import java.util.List;

import org.infoscoop_selenium.Portal;
import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.infoscoop_selenium.portal.Gadget;
import org.junit.Test;
import org.openqa.selenium.Alert;
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
		WebElement menuItem = getPortal().getTab().getInitializeItem(ISConstants.TABID_HOME);
		
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
		WebElement menuItem = getPortal().getTab().getInitializeItem(ISConstants.TABID_HOME);
		
		menuItem.click();
		Alert confirm = getDriver().switchTo().alert();
    	confirm.dismiss();
		
		//ポータル再表示
		getPortal().login();
		
		// 追加したガジェットの存在チェック
		Boolean existGadget = false;
		List<WebElement> elements = getPortal().getTab().getGadgetElements(ISConstants.TABID_HOME);
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
		WebElement menuItem = getPortal().getTab().getInitializeItem(ISConstants.TABID_HOME);
		
		menuItem.click();
		Alert confirm = this.driver.switchTo().alert();
    	confirm.accept();
		
		//ポータルのロードを待機
    	Portal.waitPortalLoadComplete(driver);
		
		// 追加したガジェットの存在チェック
		Boolean existGadget = false;
		List<WebElement> elements = getPortal().getTab().getGadgetElements(ISConstants.TABID_HOME);
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

//	@Test
	/**
	 * iscp-5770 ウィジェットの初期化後のゴミ箱の確認
	 */
	public void iscp_5770(){
	}

//	@Test
	/**
	 * iscp-5771 ウィジェットの初期化してゴミ箱から戻す
	 */
	public void iscp_5771(){
	}

//	@Test
	/**
	 * iscp-5772 ウィジェットの移動
	 */
	public void iscp_5772(){
	}
	
//	@Test
	/**
	 * iscp-5773 ウィジェットの初期化 -固定パネル
	 */
	public void iscp_5773(){
	}
}
