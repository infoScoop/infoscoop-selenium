package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.*;

import java.util.List;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.infoscoop_selenium.portal.Tab;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Tab_TabMenu_ChangeColumnNumTest extends IS_BaseItTestCase {

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

        // open tab
        tab.selectSelectMenu(tabId);

        WebElement element = tab.getColumnNumSelect(tabId);
        assertEquals("select", element.getTagName());
    }

    @Test
    /**
     * 初期値
     * セレクトボックスにはタブの列数が選択されていることを確認する。
     * TODO: カレントタブのパネルの列数が取得できれば更に厳密なテスト内容にできる。
     */
    public void iscp_5759() {
        Tab tab = getPortal().getTab();
        String tabId = ISConstants.TABID_HOME;

        tab.selectSelectMenu(tabId);
        WebElement element = tab.getColumnNumSelect(tabId);

        // default is 3
        String columnCount = "3";
        assertEquals(columnCount, element.getAttribute("value"));
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
        
        // default is 10
        int maxNumberOfColumn = 10;
        if (maxNumberOfColumn != optionEs.size())
            fail("Number of option elements does not match max number of column.");

        // check 1 ~ 10
        for (int listI = 0, no = 1; no <= maxNumberOfColumn; listI++, no++) {
            WebElement optionE = optionEs.get(listI);
            if (!Integer.toString(no).equals(optionE.getAttribute("value")))
                fail("Option elements contains illegal value attribute.");
        }
    }
    
//    @Test
    /**
     * 選択
     * セレクトボックスで列数を選択すると、パネルの列数が変更されることを確認する。
     * TODO: パネルの列数がを取得する方法が無いと難しい。
     */
//    public void iscp_5761() {
//        Tab tab = getPortal().getTab();
//        String tabId = ISConstants.TABID_HOME;
//        
//        // change column number
//        tab.selectSelectMenu(tabId);
//        Select select = new Select(tab.getColumnNumSelect(tabId));
//        select.selectByValue("5");
//    }
    
    @Test
    /**
     * 保存確認
     * 列数を変更し、更新すると指定された列数で保存されることを確認する。
     */
    public void iscp_5762() {}
    
    @Test
    public void iscp_5763() {}

}
