package org.infoscoop_selenium.testsuites.widget_framework;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.junit.Test;

public class WidgetFramework_Header_TitleTest extends IS_BaseItTestCase {

    private Gadget GADGET;
    
    @Override
    public void doBefore() {
        // ログイン
        getPortal().login();

        // 初期化
        getPortal().getCommandBar().getPortalPreference().initializeData();
        
        // ガジェットのドロップ
        GADGET = getPortal().getTopMenu()
                .dropGadget("etcWidgets", "etcWidgets_TodoList", 1, GADGET_TYPE.TODOLIST);
    }
    
    @Test
    /**
     * 長い文字列の表示
     */
    public void iscp_2() {
        String single = "01234567890123456789012345678901234567890123456789012345678901234567890123456789";
        GADGET.changeTitle(single);        
        assertEquals(single, GADGET.getTitle());
        
        String multi = "０１２３４５６７８９０１２３４５６７８９０１２３４５";        
        GADGET.changeTitle(multi);
        assertEquals(multi, GADGET.getTitle());
    }
    
    @Test
    /**
     * 文字の種類
     */
    public void iscp_3() {
        String title = "①㈱㌢<>&\"'!\"#$%'()~=-^@`'*+;:[{]}\\_/?<>\\.タダイマポチゼンブアソボゾウノファミリ―～∥－￤";
        
        GADGET.changeTitle(title);
        assertEquals(title, GADGET.getTitle());
    }

}
