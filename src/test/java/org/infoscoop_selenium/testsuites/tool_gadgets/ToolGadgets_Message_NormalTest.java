package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.MessageGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ToolGadgets_Message_NormalTest extends IS_BaseItTestCase {

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
     * 新規追加後の初期表示
     */
    public void iscp_4203() {
        // text area
        assertEquals("textarea", GADGET.getTextAreaElement().getTagName());

        WebElement contents = GADGET.getContentsElement();

        // 「公開メッセージを投稿する」
        WebElement messageSelf = contents.findElement(By.className("messageSelf"));
        assertEquals("公開メッセージを投稿する", messageSelf.getText());

        // 「システム」
        assertEquals("システム", contents.findElement(By.className("messageGroup")).getText());

        // 「受信メッセージ」、「送信メッセージ」、「お知らせ」、「全ユーザ」の公開メッセージ
        List<WebElement> groupItems = contents.findElements(By.cssSelector(".messageUsers li"));
        assertEquals("受信メッセージ", groupItems.get(0).getText());
        assertEquals("送信メッセージ", groupItems.get(1).getText());
        assertEquals("お知らせ", groupItems.get(2).getText());
        assertEquals("全ユーザの公開メッセージ", groupItems.get(3).getText());
    }

    //@Test
    /**
     * 再読み込み後の初期表示
     */
    public void iscp_4204() {
        /*
         * メッセージウィジェットをドロップ済みで、ログインまたはブラウザの再読み込みをすると、
         * 以下の項目が表示されることを確認。（ユーザ定義のグループ１、２、３、については事前に設定しておくこと）
         * 
         * 公開メッセージ用フォーム(テキストエリアは隠れている)
         * 
         * システムグループ(グレーの帯のグループ)
         * ・受信メッセージ
         * ・自分
         * ・全ユーザ
         * 
         * ユーザ定義のグループ１(青い帯のグループ)
         * ・ユーザ１
         * ・ユーザ２
         * ・ユーザ３
         * 
         * ユーザ定義のグループ２(青い帯のグループ)
         * ・ユーザ１
         * ・ユーザ２
         * ・ユーザ３
         */
    }

    @Test
    /**
     * グループ編集画面の起動
     */
    public void iscp_7249() {
        // グループ編集画面の表示
        GADGET.openGroupEdit();
        TestHelper.sleep(1000);
        try {
            getDriver().findElement(By.cssSelector(".GroupSchedule.user-search-modal"));
        } catch (NoSuchElementException e) {
            fail();
        }
    }

}
