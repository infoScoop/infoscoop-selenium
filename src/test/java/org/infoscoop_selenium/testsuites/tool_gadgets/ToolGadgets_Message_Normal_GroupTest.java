package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.MessageGadget;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ToolGadgets_Message_Normal_GroupTest extends IS_BaseItTestCase {
    private static MessageGadget GADGET;

    @Override
    public void doBefore() {
        // login
        getPortal().login();

        // 初期化
        getPortal().getCommandBar().getPortalPreference().initializeData();

        // ガジェットのドロップ
        GADGET = (MessageGadget) getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_Message", 1, GADGET_TYPE.MESSAGE);
    }

    @Test
    /**
     * グループの追加
     */
    public void iscp_4213() {
        GADGET.addGroup(null);

        WebElement newGroup = GADGET.getMessageGroupElement(1);
        assertEquals("新しいグループ", newGroup.getText());
    }

    @Test
    /**
     * グループ名 -特殊文字
     */
    public void iscp_4214() {
        String str = "①㈱㌢<>&\"'!\"#$%'()~=-^@`'*+;:[{]}\\_/?<>\\.タダイマポチゼンブアソボゾウノファミリ―～∥－￤";

        GADGET.changeTabName(0, str);

        WebElement group = GADGET.getMessageGroupElement(1);

        assertEquals(str, group.getText());
    }

    @Test
    /**
     * グループ名 -長い文字列
     */
    public void iscp_4215() {
        // add group
        GADGET.addGroup(null);

        int height = GADGET.getMessageGroupElement(1).getSize().height;

        // change tab name
        GADGET.changeTabName(0, "01234567890123456789012345678901234567890123456789");

        assertTrue(height < GADGET.getMessageGroupElement(1).getSize().height);
    }

}
