package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.infoscoop_selenium.Portal;
import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * ツール系ガジェット/TODOリスト/TODO項目/追加
 */
public class ToolGadgets_TodoList_TodoContents_AddTest extends IS_BaseItTestCase{
	private static ToDoListGadget GADGET;
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();

		// ガジェットのドロップ
		GADGET = (ToDoListGadget)getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_TodoList", 1, GADGET_TYPE.TODOLIST);
	}
	
	@Test
	/**
	 * 追加
	 * 追加用テキストボックスにTODOの内容を入力し、追加ボタンをクリックすると入力内容のTODO項目が追加欄の下に表示されることを確認
	 */
	public void iscp_4023(){
		// 現在の高さをとる
		int beforeHeight = GADGET.getContentHeight();
		
		// TODOを1件追加
		GADGET.addToDo("test_4023");
		
		// 追加後の高さをとる
		int afterHeight = GADGET.getContentHeight();
		
		// TODO件数を取る
		int todoLen = GADGET.getTodoLength();
		
		// 現在の表示件数が1件であることを確認
		assertEquals(1, todoLen);
		
		// コンテンツの高さが増加していることの確認
		assertTrue(afterHeight + ">" + beforeHeight, afterHeight > beforeHeight);
	}
	
	@Test
	/**
	 * エンター追加
	 * 追加用テキストボックスにTODOの内容を入力し、Enterを押すと入力内容のTODO項目が追加欄の下に表示されることを確認
	 */
	public void iscp_4024(){
		String txt = "test_4024";
		
		// TODOを1件追加
		GADGET.addToDoByEnter(txt);
		
		// 追加された1件目の内容を取得
		String entryTxt = GADGET.getTodoText(1);
		
		// 入力内容と比較
		assertEquals(entryTxt, txt);
	}
	
	@Test
	/**
	 * 空値
	 * 何も入力していない状態で追加ボタン又はEnterを押しても、TODOが追加されないことを確認
	 */
	public void iscp_4025(){
		// 空値をボタンで1件追加
		GADGET.addToDo("");
		
		// 空値をEnterで1件追加
		GADGET.addToDoByEnter("");
		
		// TODO件数を取る
		int todoLen = GADGET.getTodoLength();
		
		// 現在の表示件数が1件であることを確認
		assertEquals(0, todoLen);
	}

	@Test
	/**
	 * 表示
	 * 追加した項目は、重要度、TODOの内容、終了チェック用チェックボックス、削除アイコンの順に表示されることを確認
	 */
	public void iscp_4026(){
		// 1件追加
		GADGET.addToDo("test_4026");
		
		// 要素の確認
		WebElement todoRow = GADGET.getTodoRowElement(1);
		GADGET.focus();
		List<WebElement> todoRowEls = todoRow.findElements(By.tagName("td"));
		String todoPriorityTd = todoRowEls.get(0).getAttribute("class");
		String todoTextTd = todoRowEls.get(1).getAttribute("class");
		String todoCheckTd = todoRowEls.get(2).getAttribute("class");
		String todoDeleteTd = todoRowEls.get(3).getAttribute("class");
		GADGET.blur();
		
		// 重要度、TODOの内容、終了チェック用チェックボックス、削除アイコンの順に表示されていることを確認
		assertEquals(todoPriorityTd, "todoPriorityTd");
		assertEquals(todoTextTd, "todoTextTd");
		assertEquals(todoCheckTd, "todoCheckTd");
		assertEquals(todoDeleteTd, "todoDeleteTd");
	}

	@Test
	/**
	 * 初期値
	 * 追加した項目のデフォルトの設定は、重要度が「中」、終了チェックが未チェックとなることを確認
	 */
	public void iscp_4027(){
		// 1件追加
		GADGET.addToDo("test_4027");
		
		// 優先度が「中」であることを確認
		WebElement todoPriority = GADGET.getTodoPriorityElement(1);
		GADGET.focus();
		assertEquals(todoPriority.getText(), "中");
		GADGET.blur();
		
		// チェックが外れていることを確認
		boolean checked = GADGET.getCheckState(1);
		assertTrue(!checked);
	}
	
	@Test
	/**
	 * 背景色のハイライト
	 * ２項目以上のTODOを追加すると、背景色が縞模様になることを確認。奇数の項目が灰色、偶数の項目が白となる。
	 */
	public void iscp_4028(){
		// 1行目追加
		GADGET.addToDo("test_4028_1");
		
		// 1行目追加
		GADGET.addToDo("test_4028_1");
		
		
		WebElement todoItemOddEl = GADGET.getTodoRowElement(1);
		WebElement todoItemEvenEl = GADGET.getTodoRowElement(2);
		GADGET.focus();
		String todoItemOddClassName = todoItemOddEl.getAttribute("class");
		String todoItemEvenClassName = todoItemEvenEl.getAttribute("class");
		GADGET.blur();
		
		// 1行目に奇数のCSSクラスが指定されていることを確認
		assertEquals("todoItemOdd", todoItemOddClassName);
		// 2行目に奇数のCSSクラスが指定されていることを確認
		assertEquals("todoItemEven", todoItemEvenClassName);
	}

	@Test
	/**
	 * 背景色のハイライト
	 * ２項目以上のTODOを追加すると、背景色が縞模様になることを確認。奇数の項目が灰色、偶数の項目が白となる。
	 */
	public void iscp_4029(){
		WebDriver driver = getDriver();
		String beforeTodoStr = "test_4029";
		
		// データ追加
		GADGET.addToDo(beforeTodoStr);
		
		// ガジェットID取得
		String gadgetId = GADGET.getId();
		
		// 再度ログイン
		getPortal().login();
		
		// ガジェット再取得
		GADGET = new ToDoListGadget(driver, gadgetId);
		
		// 追加したTodoの内容取得
		String afterTodoStr = GADGET.getTodoText(1);
		
		// 比較
		assertEquals(beforeTodoStr, afterTodoStr);
	}
}
