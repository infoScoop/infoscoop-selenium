package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.MessageGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

        WebElement newGroupBand = GADGET.getGroupBandElement(0);
        assertEquals("新しいグループ", newGroupBand.getText());
    }

    @Test
    /**
     * グループ名 -特殊文字
     */
    public void iscp_4214() {
        int groupIndex = 0;

        String str = "①㈱㌢<>&\"'!\"#$%'()~=-^@`'*+;:[{]}\\_/?<>\\.タダイマポチゼンブアソボゾウノファミリ―～∥－￤";

        GADGET.changeGroupName(groupIndex, str);

        WebElement groupBand = GADGET.getGroupBandElement(groupIndex);
        assertEquals(str, groupBand.getText());
    }

    @Test
    /**
     * グループ名 -長い文字列
     */
    public void iscp_4215() {
        int groupIndex = 0;

        // add group
        GADGET.addGroup(null);

        int height = GADGET.getGroupBandElement(groupIndex).getSize().height;

        // change tab name
        GADGET.changeGroupName(groupIndex, "01234567890123456789012345678901234567890123456789");

        assertTrue(height < GADGET.getGroupBandElement(groupIndex).getSize().height);
    }

    // @Test
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

    // @Test
    /**
     * ユーザの削除
     */
    public void iscp_4218() {
        /*
         * グループ編集ウィンドウでグループ内のユーザを削除した場合、メッセージウィジェットの表示にも正しく反映されることを確認。
         */
    }

    @Test
    /**
     * グループの開閉
     */
    public void iscp_4219() {
        int groupIndex = 0;

        // add group
        GADGET.addGroup(null);

        WebElement groupBand = GADGET.getGroupBandElement(groupIndex);
        WebElement groupMenu = GADGET.getGroupMenuElement(groupIndex);

        // click position
        int x = groupBand.getSize().width - 5;

        // click
        Actions mouse = new Actions(getDriver());
        mouse.moveToElement(groupBand, x, 0).click().build().perform();
        TestHelper.sleep(1000);

        // check is closed
        // assertTrue(!groupMenu.isDisplayed());
        assertEquals("none", groupMenu.getCssValue("display"));

        // click
        mouse.moveToElement(groupBand, x, 0).click().build().perform();
        TestHelper.sleep(2000);

        // check is opened
        // assertTrue(groupMenu.isDisplayed());
        assertTrue(!"none".equals(groupMenu.getCssValue("display")));
    }

    @Test
    /**
     * グループのRSSを追加
     */
    public void iscp_4220() {
        GADGET.addGroup(null);

        // click rss icon
        GADGET.getGroupRssIconElement(0).click();

        String titleId = getPortal().getPanel("tab0").getDeployedGadgetId(0, 0) + "_widgetTitle";

        assertEquals("新しいグループグループのメッセージ", getDriver().findElement(By.id(titleId)).getText());
    }

    @Test
    /**
     * グループ宛てメッセージ送信フォームを開く
     */
    public void iscp_4221() {
        GADGET.addGroup(null);

        // click edit icon
        GADGET.getGroupEditIconElement(0).click();

        // check is displayed text area
        assertTrue(GADGET.getTextAreaElement().isDisplayed());

        // check text
        assertEquals("新しいグループグループへメッセージを送る", GADGET.getMessageTo());
    }

}
