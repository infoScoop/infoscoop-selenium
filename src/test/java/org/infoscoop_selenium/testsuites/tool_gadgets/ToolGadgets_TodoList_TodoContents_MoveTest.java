package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget.PRIORITY;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ToolGadgets_TodoList_TodoContents_MoveTest extends IS_BaseItTestCase {
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
     * 移動
     */
    public void iscp_4049() {
        // add a todo
        GADGET.addToDo("test1");
        GADGET.addToDo("test2");
        GADGET.addToDo("test3");
    	
        // swap todo1 and todo2
        WebElement todoText1 = GADGET.getTodoTextElement(1);
        WebElement todoText2 = GADGET.getTodoTextElement(2);
        GADGET.focus();
        int offX = todoText1.getSize().getWidth() * 2;
        int offY = todoText2.getLocation().getY() - todoText1.getLocation().getY();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(todoText1);
		actions.moveByOffset(offX, 0);
		actions.clickAndHold();
		actions.moveByOffset(0, offY);
		actions.release();
		actions.build().perform();
        GADGET.blur();
        
        assertEquals("test2", GADGET.getTodoTextStr(1));
        assertEquals("test1", GADGET.getTodoTextStr(2));
        assertEquals("test3", GADGET.getTodoTextStr(3));
    }

    //@Test
    /**
     * ドラッグ中の表示
     */
    public void iscp_4050() {
    	
    }

    @Test
    /**
     * 影響範囲
     */
    public void iscp_4051() {
        // add a todo
        GADGET.addToDo("test1");
        GADGET.addToDo("test2");
        GADGET.addToDo("test3");
        // check
        GADGET.checkToDo(1);
        // priority to high
        WebElement todoAddTextBox = GADGET.getTodoAddTextBoxElement();
        WebElement todoPriority = GADGET.getTodoPriorityElement(1);
        GADGET.focus();
        todoPriority.click();
        GADGET.blur();
        WebElement selectBox = GADGET.getChangePrioritySelectBoxElement(1);
        GADGET.focus();
        Select select = new Select(selectBox);
        select.selectByValue(Integer.toString(PRIORITY.HIGH.getValue()));
        todoAddTextBox.click();
        GADGET.blur();
        
        // swap todo1 and todo2
        WebElement todoText1 = GADGET.getTodoTextElement(1);
        WebElement todoText2 = GADGET.getTodoTextElement(2);
        GADGET.focus();
        int offX = todoText1.getSize().getWidth() * 2;
        int offY = todoText2.getLocation().getY() - todoText1.getLocation().getY();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(todoText1);
		actions.moveByOffset(offX, 0);
		actions.clickAndHold();
		actions.moveByOffset(0, offY);
		actions.release();
		actions.build().perform();
        GADGET.blur();
        
        assertEquals("test2", GADGET.getTodoTextStr(1));
        assertEquals("test1", GADGET.getTodoTextStr(2));
        assertEquals("test3", GADGET.getTodoTextStr(3));
        WebElement todoText = GADGET.getTodoTextElement(2);
        GADGET.focus();
        String cssValue = todoText.getCssValue("text-decoration");
        String priorityText = todoPriority.getText();
        GADGET.blur();
        assertTrue(cssValue.startsWith("line-through"));
        assertEquals(PRIORITY.HIGH.getText(), priorityText);
    }

    @Test
    /**
     * 保存確認
     */
    public void iscp_4052() {
        // add a todo
        GADGET.addToDo("test1");
        GADGET.addToDo("test2");
        GADGET.addToDo("test3");
    	
        // swap todo1 and todo2
        WebElement todoText1 = GADGET.getTodoTextElement(1);
        WebElement todoText2 = GADGET.getTodoTextElement(2);
        GADGET.focus();
        int offX = todoText1.getSize().getWidth() * 2;
        int offY = todoText2.getLocation().getY() - todoText1.getLocation().getY();
		Actions actions = new Actions(getDriver());
		actions.moveToElement(todoText1);
		actions.moveByOffset(offX, 0);
		actions.clickAndHold();
		actions.moveByOffset(0, offY);
		actions.release();
		actions.build().perform();
        GADGET.blur();
        
		// ブラウザの更新
		super.getDriver().navigate().refresh();
		TestHelper.sleep(2000);
		
		assertEquals("test2", GADGET.getTodoTextStr(1));
        assertEquals("test1", GADGET.getTodoTextStr(2));
        assertEquals("test3", GADGET.getTodoTextStr(3));
    }
}
