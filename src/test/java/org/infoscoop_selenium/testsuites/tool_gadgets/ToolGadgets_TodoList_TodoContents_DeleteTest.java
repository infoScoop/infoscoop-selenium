package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ToolGadgets_TodoList_TodoContents_DeleteTest extends IS_BaseItTestCase {
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
     * 削除
     */
    public void iscp_4046() {
        // add a todo
        GADGET.addToDo("test1");
        GADGET.addToDo("test2");
        GADGET.addToDo("test3");
    	
        GADGET.deleteToDo(2);
        
        assertEquals(2, GADGET.getTodoLength());
        GADGET.focus();
        assertEquals("test1", GADGET.getTodoTextStr(1));
        assertEquals("test3", GADGET.getTodoTextStr(2));
        GADGET.blur();
    }

    @Test
    /**
     * ストライプの維持
     */
    public void iscp_4047() {
        // add a todo
        GADGET.addToDo("test1");
        GADGET.addToDo("test2");
        GADGET.addToDo("test3");
        
		WebElement item1 = GADGET.getTodoRow(1);
		WebElement item2 = GADGET.getTodoRow(2);
		WebElement item3 = GADGET.getTodoRow(3);
		GADGET.focus();
		assertEquals("todoItemOdd", item1.getAttribute("class"));
		assertEquals("todoItemEven", item2.getAttribute("class"));
		assertEquals("todoItemOdd", item3.getAttribute("class"));
		GADGET.blur();
		
        GADGET.deleteToDo(2);
        
		WebElement item11 = GADGET.getTodoRow(1);
		WebElement item12 = GADGET.getTodoRow(2);
		GADGET.focus();
		assertEquals("todoItemOdd", item11.getAttribute("class"));
		assertEquals("todoItemEven", item12.getAttribute("class"));
		GADGET.blur();
    }

    @Test
    /**
     * 保存確認
     */
    public void iscp_4048() {
        // add a todo
        GADGET.addToDo("test1");
        GADGET.addToDo("test2");
        GADGET.addToDo("test3");
    	
        GADGET.deleteToDo(2);
        
        assertEquals(2, GADGET.getTodoLength());
        GADGET.focus();
        assertEquals("test1", GADGET.getTodoTextStr(1));
        assertEquals("test3", GADGET.getTodoTextStr(2));
        GADGET.blur();
        
		// ブラウザの更新
		super.getDriver().navigate().refresh();
		TestHelper.sleep(2000);
		
        assertEquals(2, GADGET.getTodoLength());
        GADGET.focus();
        assertEquals("test1", GADGET.getTodoTextStr(1));
        assertEquals("test3", GADGET.getTodoTextStr(2));
        GADGET.blur();
    }
}
