package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.*;

import java.util.List;

import org.infoscoop_selenium.Portal;
import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Panel;
import org.infoscoop_selenium.portal.Tab;
import org.infoscoop_selenium.portal.TopMenu;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Tab_TabMenu_ChangeColumnNumTest extends IS_BaseItTestCase {

    // max number of column is changeable
    private int MAX_NUMBER_OF_COLUMN = 10;

    @Override
    public void doBefore() {
        // テストケースごとの事前処理
        // login
        getPortal().login();
    }

    @Test
    /**
     * 列数変更
     * 「列数変更」には列数を指定するセレクトボックスが表示されることを確認する。
     */
    public void iscp_5758() {
        Tab tab = getPortal().getTab();
        String tabId = ISConstants.TABID_HOME;

        // open tab menu
        tab.selectSelectMenu(tabId);

        WebElement element = tab.getColumnNumSelect(tabId);
        assertEquals("select", element.getTagName());
    }

    @Test
    /**
     * 初期値
     * セレクトボックスにはタブの列数が選択されていることを確認する。
     */
    public void iscp_5759() {
        Portal portal = getPortal();
        Tab tab = portal.getTab();
        String tabId = ISConstants.TABID_HOME;

        tab.selectSelectMenu(tabId);
        WebElement element = tab.getColumnNumSelect(tabId);

        String numberOfColumn = Integer.toString(portal.getPanel(tabId).getColumnLength());
        assertEquals(numberOfColumn, element.getAttribute("value"));
    }

    @Test
    /**
     * 選択項目
     * セレクトボックスには設定可能なパネルの列数（１～１０）が列挙されていることを確認する。
     */
    public void iscp_5760() {
        Tab tab = getPortal().getTab();
        String tabId = ISConstants.TABID_HOME;

        tab.selectSelectMenu(tabId);
        WebElement selectE = tab.getColumnNumSelect(tabId);
        List<WebElement> optionEs = selectE.findElements(By.cssSelector("option"));

        // check number of option element
        if (MAX_NUMBER_OF_COLUMN != optionEs.size())
            fail("Number of option elements does not match max number of column.");

        // check 1 ~ 10
        for (int listI = 0, no = 1; no <= MAX_NUMBER_OF_COLUMN; listI++, no++) {
            WebElement optionE = optionEs.get(listI);
            if (!Integer.toString(no).equals(optionE.getAttribute("value")))
                fail("Option elements contains illegal value attribute.");
        }
    }

    @Test
    /**
     * 選択
     * セレクトボックスで列数を選択すると、パネルの列数が変更されることを確認する。
     */
    public void iscp_5761() {
        String id = "0";
        String tabId = "tab" + id;

        Portal portal = getPortal();
        Panel panel = portal.getPanel(tabId);

        // get number of column on a panel
        int numberOfColumn = panel.getColumnLength();

        // change selectbox value
        Tab tab = portal.getTab();
        tab.selectSelectMenu(tabId);
        Select select = new Select(tab.getColumnNumSelect(tabId));
        int value = (MAX_NUMBER_OF_COLUMN == numberOfColumn) ? 1 : numberOfColumn + 1;
        select.selectByValue(Integer.toString(value));
        // stall for time to change number of column
        TestHelper.sleep(1000);

        assertEquals(value, panel.getColumnLength());
    }

//    @Test
    /**
     * 保存確認
     * 列数を変更し、更新すると指定された列数で保存されることを確認する。
     * TODO: ブラウザをリロードする手段が無いと難しい。（可能と思うがスキル不足で時間が掛かりそうなのでスキップする。）
     */
    public void iscp_5762() {}

    @Test
    /**
     * ウィジェットの位置調整
     * ガジェットを複数ドロップし、列数変更で現在ドロップされているガジェットの列数以下の列数に変更すると、
     * 右端のガジェットが下に移動する。
     * 再び列数を増やしてもガジェットは最初にあった列に戻らないことも確認する。
     */
    public void iscp_5763() {
        Portal portal = getPortal();
        TopMenu topMenu = portal.getTopMenu();
        Tab tab = portal.getTab();

        String tabId = ISConstants.TABID_HOME;
        Panel panel = portal.getPanel(tabId);

        int numberOfColumn = panel.getColumnLength();
        if (2 > numberOfColumn)
            fail("This test assumes that the number of columns is 2.");

        // drop 10 gadgets (sticky) to panel
        for (int columnNum = 1; columnNum <= 10; columnNum++) {
            topMenu.dropGadget("etcWidgets", "etcWidgets_stickey", columnNum % numberOfColumn + 1);
        }

        List<List<String>> beforeGadgetIds = panel.getDeployedGadgetIds();
        int index = numberOfColumn - 1;
        List<String> correct = beforeGadgetIds.get(index - 1);
        correct.addAll(beforeGadgetIds.get(index));

        // change number of column (-1)
        tab.selectSelectMenu(tabId);
        Select select = new Select(tab.getColumnNumSelect(tabId));

        select.selectByValue(Integer.toString(numberOfColumn - 1));        
        // stall for time to change number of column
        TestHelper.sleep(1000);

        List<List<String>> afterGadgetIds = panel.getDeployedGadgetIds();
        List<String> test = afterGadgetIds.get(afterGadgetIds.size() - 1);

        if (correct.size() != test.size())
            fail("Number of gadget in a column is illegal.");
        for (int i = 0, end = test.size(); i < end; i++) {
            if (!correct.get(i).equals(test.get(i)))
                fail("Gadget does not exist or extra gadget exist.");
        }

        // restore number of column
        select.selectByValue(Integer.toString(numberOfColumn));
        // stall for time to change number of column
        TestHelper.sleep(1000);

        List<List<String>> restoredGadgetIds = panel.getDeployedGadgetIds();
        List<String> zeroGadget = restoredGadgetIds.get(restoredGadgetIds.size() - 1);
        if (0 != zeroGadget.size())
            fail("Gadgets in the right column exists.");
    }

//    @Test
    /**
     * タブごとの列数設定
     * 各タブごとに列数が設定されることを確認する。
     * TODO: 確認のためのテスト方法が不明なためスキップ。
     */
    public void iscp_5764() {}

//    @Test
    /**
     * ウェイト
     * セレクトボックスを高速に切り替えても画面がフリーズしないことを確認する。
     * TODO: このコードでは意味が無い。
     */
    public void iscp_5765() {
        Tab tab = getPortal().getTab();
        tab.selectSelectMenu(ISConstants.TABID_HOME);
        Select select = new Select(tab.getColumnNumSelect(ISConstants.TABID_HOME));
        for (int i = 0; i < 1000; i++) {
            select.selectByValue(Integer.toString(i % MAX_NUMBER_OF_COLUMN + 1));
        }
    }

}
