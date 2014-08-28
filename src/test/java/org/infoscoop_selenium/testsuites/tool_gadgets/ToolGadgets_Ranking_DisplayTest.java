package org.infoscoop_selenium.testsuites.tool_gadgets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.RankingGadget;
import org.junit.Test;

public class ToolGadgets_Ranking_DisplayTest extends IS_BaseItTestCase {

    private static RankingGadget GADGET;

    @Override
    public void doBefore() {
        // ログイン
        getPortal().login();

        // 初期化
        getPortal().getCommandBar().getPortalPreference().initializeData();

        // ガジェットのドロップ
        GADGET = (RankingGadget) getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_WidgetRanking", 1, GADGET_TYPE.RANKING);
    }

    @Test
    /**
     * 0件
     */
    public void iscp_4167() {
        assertEquals(true, GADGET.isZeroCount());

        assertEquals(GADGET.getNoneMessage(), "表示する情報がありません。");

        // ex: 最終更新日時:2014/08/28 09:56:03
        Pattern p = Pattern.compile("^最終更新日時:[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$");
        Matcher m = p.matcher(GADGET.getLastUpdateMessage());
        assertTrue(m.matches());
    }

}
