package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.MessageGadget;
import org.junit.Test;
import org.openqa.selenium.By;
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

        GADGET.changeGroupName(0, str);

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
        GADGET.changeGroupName(0, "01234567890123456789012345678901234567890123456789");

        assertTrue(height < GADGET.getMessageGroupElement(1).getSize().height);
    }

    //@Test
    /**
     * ユーザの並べ替え
     */
    public void iscp_4216() {
        /*
         * グループ編集ウィンドウで、グループ内のユーザを並べ替えた場合に、メッセージウィジェットの表示に正しく反映されることを確認。
         */
    }

    @Test
    /**
     * グループの削除
     */
    public void iscp_4217() {
        // add group
        GADGET.addGroup(null);
        
        int groupCount = GADGET.getContentsElement().findElements(By.className("messageGroup")).size();
        
        // delete group
        GADGET.deleteGroup(0);
        
        assertTrue(groupCount > GADGET.getContentsElement().findElements(By.className("messageGroup")).size());
    }

    //@Test
    /**
     * ユーザの削除
     */
    public void iscp_4218() {
        /*
         * グループ編集ウィンドウでグループ内のユーザを削除した場合、メッセージウィジェットの表示にも正しく反映されることを確認。
         */
    }

}
