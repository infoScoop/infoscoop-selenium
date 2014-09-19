package org.infoscoop_selenium.testsuites.widget_framework;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.MessageGadget;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;

public class WidgetFramework_HeaderTest extends IS_BaseItTestCase{

    @Override
    public void doBefore() {
        // ログイン
        getPortal().login();
        
        // 初期化
        getPortal().getCommandBar().getPortalPreference().initializeData();
    }

//    @Test
    /**
     * ウィジェットアイコン
     */
    public void iscp_1() {
        /*
         * ウィジェットヘッダーの左端にウィジェット設定で指定されたアイコンが表示される。
         * 
         * ウィジェットは/widgetConfiguration/@iconに設定されたアイコン
         * アップロードガジェットは/Module/ModulePrefs/Iconに設定されたアイコン
         * URL指定ガジェットはアイコンなし
         * 設定のないウィジェット、アップロードガジェットもアイコンなし
         * 削除されたガジェットはガジェットアイコンに×印のついたアイコン
         */
        

        /*
         * 以下は案
         */
        
        // 組み込みガジェット
        MessageGadget messageGadget = (MessageGadget) getPortal().getTopMenu()
                .dropGadget("etcWidgets", "etcWidgets_Message", 1, GADGET_TYPE.MESSAGE);

        assertEquals("http://localhost:8080/infoscoop/skin/imgs/comment.gif",
                messageGadget.getGadgetIconElement().getAttribute("src"));
        
        // アップロードガジェット
        ToDoListGadget toDoListGadget = (ToDoListGadget) getPortal().getTopMenu()
                .dropGadget("etcWidgets", "etcWidgets_TodoList", 1, GADGET_TYPE.TODOLIST);
        
        assertEquals("http://localhost:8080/infoscoop/gadget/todoList/tick.gif",
                toDoListGadget.getGadgetIconElement().getAttribute("img"));
    }

}
