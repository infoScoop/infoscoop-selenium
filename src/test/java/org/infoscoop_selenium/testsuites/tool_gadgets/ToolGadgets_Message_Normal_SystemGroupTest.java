package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.Panel;
import org.infoscoop_selenium.portal.gadget.MessageGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ToolGadgets_Message_Normal_SystemGroupTest extends IS_BaseItTestCase {

    private static MessageGadget GADGET;
    
    @Override
    public void doBefore() {
        // ログイン
        getPortal().login();
        
        // 初期化
        getPortal().getCommandBar().getPortalPreference().initializeData();
        
        // ガジェットのドロップ
        GADGET = (MessageGadget) getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_Message", 1, GADGET_TYPE.MESSAGE);
    }

    @Test
    /**
     * システムグループの開閉
     */
    public void iscp_4205() {
        WebElement msgGroupElm = GADGET.getMessageGroupElement(0);
        WebElement msgUsersElm = GADGET.getMessageUsersElement(0);
        
        // クリック
        msgGroupElm.click();
        
        assertTrue(!msgUsersElm.isDisplayed());
    }

    @Test
    /**
     * 受信メッセージRSSの追加
     */
    public void iscp_4206() {
        WebElement rssIcon = GADGET.getSystemRssIcon(0);

        rssIcon.click();
        TestHelper.sleep(1000);
        
        String rssGadgetId = new Panel(getDriver(), "tab0").getDeployedGadgetId(0, 0);
        WebElement rssGadget = getDriver().findElement(By.id(rssGadgetId));
        assertEquals("受信メッセージ", rssGadget.findElement(By.id(rssGadgetId + "_widgetTitle")).getText());
    }
    @Test
    /**
     * 送信メッセージRSSの追加
     */
    public void iscp_4207() {
        WebElement rssIcon = GADGET.getSystemRssIcon(1);

        rssIcon.click();
        TestHelper.sleep(1000);
        
        String rssGadgetId = new Panel(getDriver(), "tab0").getDeployedGadgetId(0, 0);
        WebElement rssGadget = getDriver().findElement(By.id(rssGadgetId));
        assertEquals("送信メッセージ", rssGadget.findElement(By.id(rssGadgetId + "_widgetTitle")).getText());
    }
    
    @Test
    /**
     * お知らせRSSの追加
     */
    public void iscp_4208() {
        WebElement rssIcon = GADGET.getSystemRssIcon(2);

        rssIcon.click();
        TestHelper.sleep(1000);
        
        String rssGadgetId = new Panel(getDriver(), "tab0").getDeployedGadgetId(0, 0);
        WebElement rssGadget = getDriver().findElement(By.id(rssGadgetId));
        assertEquals("お知らせ", rssGadget.findElement(By.id(rssGadgetId + "_widgetTitle")).getText());
    }
    
    @Test
    /**
     * 全ユーザの公開メッセージRSSの追加
     */
    public void iscp_4209() {
        WebElement rssIcon = GADGET.getSystemRssIcon(3);

        rssIcon.click();
        TestHelper.sleep(1000);
        
        String rssGadgetId = new Panel(getDriver(), "tab0").getDeployedGadgetId(0, 0);
        WebElement rssGadget = getDriver().findElement(By.id(rssGadgetId));
        assertEquals("全ユーザの公開メッセージ", rssGadget.findElement(By.id(rssGadgetId + "_widgetTitle")).getText());
    }

    //@Test
    /**
     * お知らせメッセージ送信アイコン
     */
    public void iscp_4210() {
        /*
         * ウィジェット管理画面から「お知らせは管理者のみが使用可能」にチェックを入れていると、管理者でログインした場合のみ、お知らせの右側にメッセージ送信アイコンが表示されることを確認。
         * 
         * 「お知らせは管理者のみが使用可能」のチェックを入れないと、誰でログインしても、お知らせの右側にメッセージ送信アイコンが表示されることを確認。
         */
    }
    
    @Test
    /**
     * お知らせメッセージ送信フォームを開く
     */
    public void iscp_4211() {
        WebElement editIcon = GADGET.getSystemEditIcon();
        editIcon.click();
        TestHelper.sleep(1000);
        
        // is visible
        assertTrue(GADGET.getTextAreaElement().isDisplayed());
        
        assertEquals("全ユーザにお知らせを送る", GADGET.getMessageTo());
        
    }

    @Test
    /**
     * お知らせメッセージを送信する
     */
    public void iscp_4212() {
        /*
         * お知らせメッセージ送信フォームからメッセージを送信した場合、全ユーザに対するお知らせとして送信されることを確認。
         * 
         * 全ユーザの受信メッセージにメッセージが届く
         * 
         * 自分の送信メッセージにメッセージが残る。
         * 
         * お知らせ一覧にもメッセージが表示される。
         */
    }

}
