package org.infoscoop_selenium.testsuites.widget_framework;

import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.Panel;
import org.infoscoop_selenium.portal.Tab;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    
//    @Test
    /**
     * タイトルリンクがある
     */
    public void iscp_4() {
        /*
         * タイトルにリンクが張られる。
         * クリックするとタイトルリンクに設定されているサイトが表示される
         */
    }
    
    @Test
    /**
     * タイトルリンクが空の場合
     */
    public void iscp_5() {
        // is no-anchor
        WebElement titleElement = GADGET.getTitleElement();
        assertEquals(0, titleElement.findElements(By.tagName("a")).size());
        
        // drag and drop
        int columnX = 3;
        int columnY = 1;
        GADGET.dragAndDrop(titleElement, columnX, columnY);
        
        Panel panel = getPortal().getPanel(new Tab(super.getDriver()).getCurrentTabId());
        WebElement gadgetElement = panel.getGadgetSpaceElement(columnX, columnY);

        assertEquals(GADGET.getId(), gadgetElement.getAttribute("id"));
    }
    
//    @Test
    /**
     * javascript:リンク
     */
    public void iscp_6() {
        /*
         * 「javascript:」で始まるリンクの場合は、続いて記述されるjavascriptが実行されることを確認
         * そのとき、ポータル内フレームも新しいウィンドウも開かれないことを確認
         */
    }

}
