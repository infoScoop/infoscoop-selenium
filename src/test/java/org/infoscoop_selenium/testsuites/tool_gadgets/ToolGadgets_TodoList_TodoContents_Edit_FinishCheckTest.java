package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ToolGadgets_TodoList_TodoContents_Edit_FinishCheckTest extends IS_BaseItTestCase {
    private static ToDoListGadget GADGET;

    @Override
    public void doBefore() {
        // テストケースごとの事前処理
        // login
        getPortal().login();

        // 初期化
        getPortal().getCommandBar().getPortalPreference().initializeData();

        // ガジェットのドロップ
        GADGET = (ToDoListGadget) getPortal().getTopMenu().dropGadget(
                "etcWidgets", "etcWidgets_TodoList", 1, GADGET_TYPE.TODOLIST);
    }

    @Test
    /**
     * 保存確認
     */
    public void iscp_4044() {
        // add a todo
        GADGET.addToDo("test");
        GADGET.checkToDo(1);

        // check that todo text has "line-through" CSS property
        WebElement todoText = GADGET.getTodoText(1);
        GADGET.focus();
        String cssValue = todoText.getCssValue("text-decoration");
        GADGET.blur();
        assertTrue(cssValue.startsWith("line-through"));
        
		// ブラウザの更新
		super.getDriver().navigate().refresh();
		TestHelper.sleep(2000);
		
        // check that todo text has "line-through" CSS property
        WebElement todoText2 = GADGET.getTodoText(1);
        GADGET.focus();
        String cssValue2 = todoText2.getCssValue("text-decoration");
        GADGET.blur();
        assertTrue(cssValue2.startsWith("line-through"));
    }

    @Test
    /**
     * 終了チェック
     */
    public void iscp_4045() {
        // add a todo
        GADGET.addToDo("test");
        GADGET.checkToDo(1);

        // check that todo text has "line-through" CSS property
        WebElement todoText = GADGET.getTodoText(1);
        GADGET.focus();
        String cssValue = todoText.getCssValue("text-decoration");
        GADGET.blur();
        assertTrue(cssValue.startsWith("line-through"));
    	
        GADGET.checkToDo(1);
        
        // check that todo text doesn't have "line-through" CSS property
        WebElement todoText2 = GADGET.getTodoText(1);
        GADGET.focus();
        String cssValue2 = todoText2.getCssValue("text-decoration");
        GADGET.blur();
        assertFalse(cssValue2.startsWith("line-through"));
    }
}
