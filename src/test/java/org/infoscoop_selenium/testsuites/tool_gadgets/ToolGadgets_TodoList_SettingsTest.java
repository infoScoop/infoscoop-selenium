package org.infoscoop_selenium.testsuites.tool_gadgets;

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
 * ツール系ガジェット/TODOリスト/設定
 */
public class ToolGadgets_TodoList_SettingsTest extends IS_BaseItTestCase{
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
	 * 表示
	 * 編集アイコン押下時に適切な項目が表示されることを確認
	 */
	public void iscp_4015(){
		// 表示されているUserPref情報取得
		Map prefsMap = GADGET.getGadgetPreference().getDisplayUserPrefs();
		
		assertEquals(prefsMap.keySet().size(), 2);
		
		int order = 0;
		for(Iterator<Map.Entry> ite = prefsMap.entrySet().iterator();ite.hasNext();){
			
			Map.Entry entry = ite.next();
			if(order == 0){
				// 1行目は「ガジェットタイトル:」、テキストフォーム
				assertEquals(entry.getKey(), "ガジェットタイトル:");
				WebElement el = (WebElement)entry.getValue();
				assertEquals(el.getAttribute("type"), "text");
			}
			else if(order == 1){
				// 2行目は「フォントサイズ:」、セレクトボックス
				assertEquals(entry.getKey(), "フォントサイズ:");
				WebElement el = (WebElement)entry.getValue();
				assertEquals(el.getTagName(), "select");
				
				// TODO: selectのoption要素チェック
				List<WebElement> options = el.findElements(By.tagName("option"));
				
				assertEquals(options.size(), 2);
				
				for(int i=0;i<options.size();i++){
					WebElement option = options.get(i);
					if(i == 0){
						// option 1行目は value=normal
						assertEquals(option.getAttribute("value"), "normal");
					}
					else if(i == 1){
						// option 2行目は value=large
						assertEquals(option.getAttribute("value"), "large");
					}
				}
			}
			else if(order >= 2){
				// 3行目以降は存在しない
				assertFalse("Unnecessary UserPref exists.", false);
			}
			order++;
		}
	}
	
	@Test
	/**
	 * ウィジェットタイトル指定
	 * ウィジェットタイトルの値を変更し OK を押下すると、ガジェットのタイトルが変更した値になることを確認
	 */
	public void iscp_4016(){
		// 変更されるタイトル名
		String changeTitle = "hogehoge";
		
		// タイトル変更
		GADGET.getGadgetPreference().changeTitle(changeTitle);
		
		// 変更されたことの確認
		assertEquals(changeTitle, GADGET.getTitle());
	}
}
