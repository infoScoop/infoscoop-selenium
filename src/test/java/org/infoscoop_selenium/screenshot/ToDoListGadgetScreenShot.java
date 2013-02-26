package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


/**
 * ToDoリストガジェットのスクリーンショット
 * @author mikami
 *
 */
public class ToDoListGadgetScreenShot extends IS_BaseItTestCase {
	private static String WIDGET_ID;	
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login("test_user2", "password");

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();

		// ガジェットのドロップ
		WIDGET_ID = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_TodoList", 1);
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
	}
	
	@Test
	/**
	 * ToDoリストガジェット
	 */
	public void ToDoリストガジェット(){
		WebDriver driver = getDriver();		

		// ガジェットの表示を待つ
		TestHelper.switchToFrame(driver, "ifrm_"+WIDGET_ID);
		TestHelper.backToTopFrame(driver);
		
		TestHelper.getScreenShot("ToDoリストガジェット", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * ToDoリストガジェット（ガジェットメニュー）
	 */
	public void ToDoリストガジェット_ガジェットメニュー(){
		WebDriver driver = getDriver();
		
		// ガジェットメニューを開く
		getPortal().getGadget().openMenu(WIDGET_ID);
		
		TestHelper.getScreenShot("ToDoリストガジェット（ガジェットメニュー）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * ToDoリストガジェット（ガジェット設定）
	 */
	public void ToDoリストガジェット_ガジェット設定(){
		WebDriver driver = getDriver();
		
		// ガジェット設定を開く
		getPortal().getGadget().getGadgetPreference().show(WIDGET_ID);
		
		TestHelper.getScreenShot("ToDoリストガジェット（ガジェット設定）", driver);

		// ガジェット設定を閉じる
		getPortal().getGadget().getGadgetPreference().cancel(WIDGET_ID);
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * ToDoリストガジェット（ToDo追加）
	 */
	public void ToDoリストガジェット_ToDo追加(){
		WebDriver driver = getDriver();
		ToDoListGadget todoListGadget =  getPortal().getGadget().getToDoListGadget();
		
		// ToDoを追加
		todoListGadget.addToDo(WIDGET_ID, "優先度高");
		todoListGadget.addToDo(WIDGET_ID, "優先度中");
		todoListGadget.addToDo(WIDGET_ID, "優先度低");

		// ToDoの優先度を変更
		todoListGadget.changePriority(WIDGET_ID, 1, ToDoListGadget.PRIORITY.HIGH);
		todoListGadget.changePriority(WIDGET_ID, 3, ToDoListGadget.PRIORITY.LOW);

		// ToDoをチェック
		todoListGadget.checkToDo(WIDGET_ID, 2);

		TestHelper.getScreenShot("ToDoリストガジェット（ToDo追加）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * ToDoリストガジェット（優先度セレクター）
	 */
	public void ToDoリストガジェット_優先度セレクター(){
		WebDriver driver = getDriver();
		ToDoListGadget todoListGadget =  getPortal().getGadget().getToDoListGadget();
		
		// ToDoを追加
		todoListGadget.addToDo(WIDGET_ID, "優先度中");

		// ToDoの優先度セレクターを表示
		TestHelper.switchToFrame(driver, "ifrm_"+WIDGET_ID);
		driver.findElement(By.xpath("//tbody[@id='"+WIDGET_ID+"_list']/tr[1]/td[@class='todoPriorityTd']/div")).click();
		driver.findElement(By.xpath("//tbody[@id='"+WIDGET_ID+"_list']/tr[1]/td[@class='todoPriorityTd']/select")).click();
		TestHelper.getScreenShot("ToDoリストガジェット（優先度セレクター）", driver);
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * ToDoリストガジェット（フォントサイズ大きい）
	 */
	public void ToDoリストガジェット_フォントサイズ大きい(){
		WebDriver driver = getDriver();
		ToDoListGadget todoListGadget =  getPortal().getGadget().getToDoListGadget();

		// フォントサイズを変更
		todoListGadget.changeFontSize(WIDGET_ID, ToDoListGadget.FONTSIZE.LARGE);
		
		// ToDoを追加
		todoListGadget.addToDo(WIDGET_ID, "優先度高");
		todoListGadget.addToDo(WIDGET_ID, "優先度中");
		todoListGadget.addToDo(WIDGET_ID, "優先度低");

		// ToDoの優先度を変更
		todoListGadget.changePriority(WIDGET_ID, 1, ToDoListGadget.PRIORITY.HIGH);
		todoListGadget.changePriority(WIDGET_ID, 3, ToDoListGadget.PRIORITY.LOW);

		TestHelper.getScreenShot("ToDoリストガジェット（フォントサイズ大きい）", driver);
		
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
