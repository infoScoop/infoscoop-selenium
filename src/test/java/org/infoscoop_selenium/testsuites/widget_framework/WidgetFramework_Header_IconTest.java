package org.infoscoop_selenium.testsuites.widget_framework;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.ProperHeaderIcon;
import org.infoscoop_selenium.properties.TestEnv;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class WidgetFramework_Header_IconTest extends IS_BaseItTestCase {

    @Override
    public void doBefore() {
        // login
        getPortal().login();

        // 初期化
        getPortal().getCommandBar().getPortalPreference().initializeData();
    }

    @Test
    /**
     * 表示順序
     */
    public void iscp_7() {
        // schedule gadget
        Gadget gadget = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_schedule", 1, GADGET_TYPE.SCHEDULE);

        // previous, today, next, refresh, minimize, mazimize, showtools
        List<String> tests = gadget.getHeaderIconTypes();

        int testI = 0;

        // check: previous, today, next
        List<ProperHeaderIcon> propers = gadget.getProperHeaderIconList();
        for (int i = 0, listSize = propers.size(); i < listSize; i++, testI++) {
            assertEquals(propers.get(i).getType(), tests.get(testI));
        }

        // check: refresh, minimize, maximize, showtools
        List<String> supporteds = gadget.getSupportedHeaderIcons();
        for (int i = 0, listSize = supporteds.size(); i < listSize; i++, testI++) {
            assertEquals(supporteds.get(i), tests.get(testI));
        }
    }

    @Test
    /**
     * ウィジェット固有のアイコン
     */
    public void iscp_8() {
        // schedule gadget
        Gadget gadget = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_schedule", 1, GADGET_TYPE.SCHEDULE);

        List<ProperHeaderIcon> iconList = gadget.getProperHeaderIconList();

        // previous, today, next (, refresh, minimize, maximize, showtools)
        List<String> iconTypes = gadget.getHeaderIconTypes();
        for (int i = 0, listSize = iconList.size(); i < listSize; i++) {
            assertEquals(iconList.get(i).getType(), iconTypes.get(i));
        }
    }

    @Test
    /**
     * ウィジェット固有のアイコン -設定
     */
    public void iscp_9() {
        Gadget gadget = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_schedule", 1, GADGET_TYPE.SCHEDULE);

        List<ProperHeaderIcon> iconList = gadget.getProperHeaderIconList();

        List<WebElement> iconElms = gadget.getHeaderIconElements();

        String appUrl = TestEnv.getInstance().getAppUrl();
        for (int i = 0, listSize = iconList.size(); i < listSize; i++) {
            // image url
            assertEquals(appUrl + iconList.get(i).getImgUrl(), iconElms.get(i).getAttribute("src"));

            // tooltip
            assertEquals(iconList.get(i).getAlt(), iconElms.get(i).getAttribute("title"));
        }
    }

    @Ignore
    @Test
    /**
     * ウィジェット固有のアイコン -例外
     */
    public void iscp_10() {
        /*
         * (widgetConfiguration|Module)//widget/header//icon/@typeにウィジェット固有のタイプ名が指定されている時、対応するIconHandlerが実装されていない場合にウィジットの作成時にコンソールに「ヘッダアイコンに有効ではないタイプが使われています。」とエラーが表示される事を確認。
         * エラーにはウィジットタイプ[type=アイコンタイプ,alt=アイコンalt,imgUrl=アイコンイメージＵＲＬ]が表示されている事を確認。
         */
    }

}
