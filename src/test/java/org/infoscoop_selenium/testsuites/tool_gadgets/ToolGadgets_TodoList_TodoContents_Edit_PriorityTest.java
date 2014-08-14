package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget.PRIORITY;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ToolGadgets_TodoList_TodoContents_Edit_PriorityTest extends IS_BaseItTestCase {
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
     * 重要度
     * 重要度の文字をクリックすると、重要度選択用のプルダウンメニューがクリックした文字の位置に表示されることを確認する。
     * このとき、デフォルトで現在の重要度が選択されていることも確認する。
     */
    public void iscp_4030() {
        // add a todo
        GADGET.addToDo("test");

        // <div class="todoPriority"></div>
        WebElement todoPriority = GADGET.getTodoPriority(1);

        // click
        GADGET.focus();
        todoPriority.click();
        GADGET.blur();
        
        WebElement selectBox = GADGET.getChangePrioritySelectBox(1);

        // check that <div class="todoPriority"> is invisible
        GADGET.focus();
        if (todoPriority.isDisplayed())
            fail("\"priority text\" is visible.");
        // check that select box exist
        if (selectBox == null)
            fail("Select box does not exist.");

        // check selected value
        String value = selectBox.getAttribute("value");
        GADGET.blur();
        assertEquals(Integer.toString(PRIORITY.MIDDLE.getValue()), value);
    }

    @Test
    /**
     * ブラー
     * 重要度選択プルダウンメニュー表示後、項目を選択するかフォーカスをはずすとプルダウンメニューが消え、
     * 重要度に選択内容が反映されることを確認する
     */
    public void iscp_4031() {
        GADGET.addToDo("test");

        WebElement todoAddTextBox = GADGET.getTodoAddTextBox();
        WebElement todoPriority = GADGET.getTodoPriority(1);

        // click
        GADGET.focus();
        todoPriority.click();
        GADGET.blur();

        // change select box value
        WebElement selectBox = GADGET.getChangePrioritySelectBox(1);
        GADGET.focus();
        Select select = new Select(selectBox);
        select.selectByValue(Integer.toString(PRIORITY.HIGH.getValue()));

        // blue
        todoAddTextBox.click();
        GADGET.blur();

        // check that select box is not exist
        if (GADGET.getChangePrioritySelectBox(1) != null)
            fail("Selectbox is exist.");

        // check priority text
        GADGET.focus();
        String priorityText = todoPriority.getText();
        GADGET.blur();
        assertEquals(PRIORITY.HIGH.getText(), priorityText);
    }

//    @Test
    /**
     * ドラッグ中の表示
     * プルダウンメニューを表示している状態でガジェットがドラッグが可能な場合、
     * ガジェットのカラム移動後にプルダウンの表示がドラッグ元に残らないことを確認する。
     * TODO: 目視の必要があると思われるためスキップ。
     */
    public void iscp_4032() {}

    @Test
    /**
     * 他のプルダウンをクリック
     * プルダウンメニューを表示している状態で他の重要度をクリックすると、現在表示中のプルダウンメニューが消えることを確認する。
     */
    public void iscp_4033() {
        // make height than pull down menu
        for (int i = 0; i < 5; i++) {
            GADGET.addToDo("test");
        }

        WebElement todoPriority1 = GADGET.getTodoPriority(1);
        WebElement todoPriority5 = GADGET.getTodoPriority(5);

        // click order 1
        GADGET.focus();
        todoPriority1.click();
        GADGET.blur();

        if (GADGET.getChangePrioritySelectBox(1) == null)
            fail("Select box does not exist.");

        // click order 5
        GADGET.focus();
        todoPriority5.click();
        GADGET.blur();

        assertEquals(null, GADGET.getChangePrioritySelectBox(1));
    }

    @Test
    /**
     * 色
     * 設定された重要度が適した色で表示されることを確認する。（高：赤、中：緑、低：青）
     */
    public void iscp_4034() {
        GADGET.addToDo("test");
        GADGET.changePriority(1, PRIORITY.HIGH);
        GADGET.addToDo("test");
        GADGET.changePriority(2, PRIORITY.MIDDLE);
        GADGET.addToDo("test");
        GADGET.changePriority(3, PRIORITY.LOW);

        WebElement todoPriority1 = GADGET.getTodoPriority(1);
        WebElement todoPriority2 = GADGET.getTodoPriority(2);
        WebElement todoPriority3 = GADGET.getTodoPriority(3);

        GADGET.focus();
        assertEquals(PRIORITY.HIGH.getCssColor(), todoPriority1.getCssValue("color"));
        assertEquals(PRIORITY.MIDDLE.getCssColor(), todoPriority2.getCssValue("color"));
        assertEquals(PRIORITY.LOW.getCssColor(), todoPriority3.getCssValue("color"));
    }

    @Test
    /**
     * 保存確認
     * 重要度を変更してブラウザリロードしても変更された状態になっていることを確認する。
     */
    public void iscp_4035() {
        GADGET.addToDo("test");
        GADGET.changePriority(1, PRIORITY.HIGH);
        WebElement todoPriority1 = GADGET.getTodoPriority(1);
        GADGET.focus();
        assertEquals(PRIORITY.HIGH.getCssColor(), todoPriority1.getCssValue("color"));
        
		// ブラウザの更新
		super.getDriver().navigate().refresh();
		TestHelper.sleep(2000);
		
        WebElement todoPriority2 = GADGET.getTodoPriority(1);
        GADGET.focus();
        assertEquals(PRIORITY.HIGH.getCssColor(), todoPriority2.getCssValue("color"));
    }
}
