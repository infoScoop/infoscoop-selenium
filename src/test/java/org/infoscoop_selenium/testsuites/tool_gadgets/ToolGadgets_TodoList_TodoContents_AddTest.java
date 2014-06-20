package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;
import org.openqa.selenium.By;
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
		GADGET.addToDo("test");
		
		// 追加後の高さをとる
		int afterHeight = GADGET.getContentHeight();
		
		// TODO件数を取る
		int todoLen = GADGET.getTodoLength();
		
		// 現在の表示件数が1件であることを確認
		assertEquals(todoLen, 1);
		
		// コンテンツの高さが増加していることの確認
		assertTrue(afterHeight + ">" + beforeHeight, afterHeight > beforeHeight);
	}
	
}
