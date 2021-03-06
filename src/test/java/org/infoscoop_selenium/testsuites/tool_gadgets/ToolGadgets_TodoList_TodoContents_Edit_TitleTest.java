package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ToolGadgets_TodoList_TodoContents_Edit_TitleTest extends IS_BaseItTestCase {
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

    /**
     * タイトル
     * TODO内容のテキストをクリックすると内容編集用のテキストボックスが表示され、
     * Enterキー又はフォーカスをはずすと元に戻り、編集内容が反映されることを確認する
     * iscp_4036_1 Eneterキー入力による確認
     * iscp_4036_2 フォーカスを外すことによる確認
     */
    @Test
    public void iscp_4036_1() {
        // add a todo
        GADGET.addToDo("test");

        WebElement todoText = GADGET.getTodoTextElement(1);

        // click <span class="todoText"></span>
        GADGET.focus();
        todoText.click();
        GADGET.blur();

        WebElement todoTextEdit = GADGET.getTodoTextEditElement(1);

        // send enter key
        GADGET.focus();
        todoTextEdit.sendKeys(Keys.chord("change"), Keys.ENTER);
        GADGET.blur();

        // check text box does not exist
        if (GADGET.getTodoTextEditElement(1) != null)
            fail("TODO text box is exist.");

        // check todo text
        todoText = GADGET.getTodoTextElement(1);
        GADGET.focus();
        String text = todoText.getText();
        GADGET.blur();
        assertEquals("change", text);
    }
    @Test
    public void iscp_4036_2() {
        // add a tab
        GADGET.addToDo("test");

        // for blur
        WebElement todoAddTextBox = GADGET.getTodoAddTextBoxElement();

        WebElement todoText = GADGET.getTodoTextElement(1);

        // click <span class="todoText"></span>
        GADGET.focus();
        todoText.click();
        GADGET.blur();

        WebElement todoTextEdit = GADGET.getTodoTextEditElement(1);

        // change todo text
        GADGET.focus();
        todoTextEdit.sendKeys(Keys.chord("change"));
        todoAddTextBox.click();
        GADGET.blur();

        // check todo text
        todoText = GADGET.getTodoTextElement(1);
        GADGET.focus();
        String text = todoText.getText();
        GADGET.blur();
        assertEquals("change", text);
    }

    @Test
    /**
     * 空値
     * TODO再編集用テキストボックスに空の値を入力しても反映されず、編集前の内容に戻ることを確認する。
     */
    public void iscp_4037() {
        GADGET.addToDo("test");

        WebElement todoText = GADGET.getTodoTextElement(1);

        // click
        GADGET.focus();
        todoText.click();
        GADGET.blur();

        WebElement todoTextEdit = GADGET.getTodoTextEditElement(1);

        // change todo text box
        GADGET.focus();
        todoTextEdit.sendKeys(Keys.DELETE, Keys.ENTER);
        GADGET.blur();

        todoText = GADGET.getTodoTextElement(1);

        // check that todo text isn't changed
        GADGET.focus();
        String text = todoText.getText();
        GADGET.blur();
        assertEquals("test", text);
    }

    @Test
    /**
     * 編集後の打ち消し線
     * 終了チェックされたTODOの内容を編集しても、編集後に打ち消し線が消えないことを確認する。
     */
    public void iscp_4038() {
        // add a todo and check check box
        GADGET.addToDo("test");
        GADGET.checkToDo(1);

        WebElement todoText = GADGET.getTodoTextElement(1);

        // click <span class="todoText"></span>
        GADGET.focus();
        todoText.click();
        GADGET.blur();

        WebElement todoTextEdit = GADGET.getTodoTextEditElement(1);

        // change todo text
        GADGET.focus();
        todoTextEdit.sendKeys(Keys.chord("change"), Keys.ENTER);
        GADGET.blur();

        // check that todo text has "line-through" CSS property
        todoText = GADGET.getTodoTextElement(1);
        GADGET.focus();
        String cssValue = todoText.getCssValue("text-decoration");
        GADGET.blur();
        assertTrue(cssValue.startsWith("line-through"));
    }

    @Test
    /**
     * 特殊文字
     * 追加又は編集で、TODO内容に以下の文字を含めた場合も正常に表示されることを確認する。
     * ①㈱㌢<>&"'!"#$%'()~=-^@`'*+;:[{]}\_/?<>\.タダイマポチゼンブアソボゾウノファミリ―～∥－￤(U+2000B)(U+2A6B2)
     * (U+2000B)(U+2A6B2)は参照文字ではなくサロゲートペアの文字を直接入力
     */
    public void iscp_4039() {
		String inputStr = "①㈱㌢<>&\"'!\"#$%'()~=-^@`'*+;:[{]}\\_/?<>\\.タダイマポチゼンブアソボゾウノファミリ―～∥－￤";
        GADGET.addToDo(inputStr);
        
        // check todo text
        WebElement todoText = GADGET.getTodoTextElement(1);
        GADGET.focus();
        String text = todoText.getText();
        GADGET.blur();
        assertEquals(inputStr, text);
    }

    @Test
    /**
     * 折り返し
     * TODO内容の文字列がTODOリストの幅より長い場合は、表示範囲内で適切に改行されることを確認する。
     * IEはシングルバイトの連続文字も折り返すが、Firefoxはシングルバイトの連続文字ははみ出した部分が隠れる。
     * 固定領域でも確認する。
     */
    public void iscp_4040() {
        // add a todo
        GADGET.addToDo("test");

        WebElement todoText = GADGET.getTodoTextElement(1);

        // get height
        GADGET.focus();
        int beforeHeight = todoText.getSize().height;
        GADGET.blur();

        // change todo text
        GADGET.changeTodoText(1, "ああああああああああああああああああああああああああああああ");
        todoText = GADGET.getTodoTextElement(1);

        GADGET.focus();
        int afterHeight = todoText.getSize().height;
        int afterWidth = todoText.getSize().width;
        GADGET.blur();

        assertTrue(beforeHeight < afterHeight);

        // <span class="todoText"></span> width is smaller than gadget content width
        assertTrue(afterWidth < GADGET.getContentWidth());
    }

    @Test
    /**
     * 境界値
     * タイトルは長さ制限がない。
     * ブラウザのテキストボックスの制限はあるので4000byteの文字列を登録できることを確認する。
     */
    public void iscp_4041() {
		//4000文字の文字列を作成
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=400; i++) {
			sb.append("0123456789");
		}
		String inputStr = sb.toString();
        // add a todo with 4000 bytes text
        GADGET.addToDo(inputStr);
        
        // check todo text
        WebElement todoText = GADGET.getTodoTextElement(1);
        GADGET.focus();
        String text = todoText.getText();
        GADGET.blur();
        assertEquals(inputStr, text);
    }

    //@Test
    /**
     * ツールチップ
     * TODO内容のテキストにカーソルを合わせると、登録内容がツールチップで表示され、内容を編集後は変更された内容が表示されることを確認
     */
    public void iscp_4042() {
    	
    }
    
    @Test
    /**
     * 保存確認
     * TODO内容変更後にブラウザリロードしても変更された状態になっていることを確認（保存確認）
     */
    public void iscp_4043() {
    	String inputStr = "change";
    	
        // add a todo
        GADGET.addToDo("test");

        WebElement todoText = GADGET.getTodoTextElement(1);

        // click <span class="todoText"></span>
        GADGET.focus();
        todoText.click();
        GADGET.blur();

        WebElement todoTextEdit = GADGET.getTodoTextEditElement(1);

        // send enter key
        GADGET.focus();
        todoTextEdit.sendKeys(Keys.chord(inputStr), Keys.ENTER);
        GADGET.blur();

        // check todo text
        todoText = GADGET.getTodoTextElement(1);
        GADGET.focus();
        String text = todoText.getText();
        GADGET.blur();
        assertEquals(inputStr, text);
        
		// ブラウザの更新
		super.getDriver().navigate().refresh();
		TestHelper.sleep(2000);
		
        // check todo text
        todoText = GADGET.getTodoTextElement(1);
        GADGET.focus();
        text = todoText.getText();
        GADGET.blur();
        assertEquals(inputStr, text);
    }
}

