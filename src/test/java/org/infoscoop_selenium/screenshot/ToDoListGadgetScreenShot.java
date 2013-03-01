package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * ToDoリストガジェットのスクリーンショット
 * @author mikami
 *
 */
public class ToDoListGadgetScreenShot extends IS_BaseItTestCase {
	private static ToDoListGadget GADGET;	
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login("test_user2", "password");

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();

		// ガジェットのドロップ
		GADGET = (ToDoListGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_TodoList", 1, GADGET_TYPE.TODOLIST);
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
		TestHelper.switchToFrame(driver, "ifrm_"+GADGET.getId());
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
		GADGET.openMenu();
		
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
		GADGET.getGadgetPreference().show();
		
		TestHelper.getScreenShot("ToDoリストガジェット（ガジェット設定）", driver);

		// ガジェット設定を閉じる
		GADGET.getGadgetPreference().cancel();
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * ToDoリストガジェット（ToDo追加）
	 */
	public void ToDoリストガジェット_ToDo追加(){
		WebDriver driver = getDriver();
		
		// ToDoを追加
		GADGET.addToDo("優先度高");
		GADGET.addToDo("優先度中");
		GADGET.addToDo("優先度低");

		// ToDoの優先度を変更
		GADGET.changePriority(1, ToDoListGadget.PRIORITY.HIGH);
		GADGET.changePriority(3, ToDoListGadget.PRIORITY.LOW);

		// ToDoをチェック
		GADGET.checkToDo(2);

		TestHelper.getScreenShot("ToDoリストガジェット（ToDo追加）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * ToDoリストガジェット（優先度セレクター）
	 */
	public void ToDoリストガジェット_優先度セレクター(){
		WebDriver driver = getDriver();
		
		// ToDoを追加
		GADGET.addToDo("優先度中");

		// ToDoの優先度セレクターを表示
		TestHelper.switchToFrame(driver, "ifrm_"+GADGET.getId());
		driver.findElement(By.xpath("//tbody[@id='"+GADGET.getId()+"_list']/tr[1]/td[@class='todoPriorityTd']/div")).click();
		driver.findElement(By.xpath("//tbody[@id='"+GADGET.getId()+"_list']/tr[1]/td[@class='todoPriorityTd']/select")).click();
		TestHelper.getScreenShot("ToDoリストガジェット（優先度セレクター）", driver);
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * ToDoリストガジェット（フォントサイズ大きい）
	 */
	public void ToDoリストガジェット_フォントサイズ大きい(){
		WebDriver driver = getDriver();

		// フォントサイズを変更
		GADGET.changeFontSize(ToDoListGadget.FONTSIZE.LARGE);
		
		// ToDoを追加
		GADGET.addToDo("優先度高");
		GADGET.addToDo("優先度中");
		GADGET.addToDo("優先度低");

		// ToDoの優先度を変更
		GADGET.changePriority(1, ToDoListGadget.PRIORITY.HIGH);
		GADGET.changePriority(3, ToDoListGadget.PRIORITY.LOW);

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
