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
    
    //@Test
    /**
     * アルゴリズム
     */
    public void iscp_4168() {
        /*
         * 以下のアルゴリズムでランキングが表示される。
         * ・第一ソートキー：10日以内に追加された個数
         * ・第二ソートキー：全期間のウィジェット数
         * ・集計時点で削除されているウィジェットは対象外。
         * ・1.3.0以前のアップロードガジェットは対象外。
         * ・同じURLでも別メニューアイテムのウィジェットは別々に集計。
         */
    }
    
    //@Test
    /**
     * デザイン
     */
    public void iscp_4169() {
        /*
         * 先頭の三件には金・銀・銅の王冠アイコンが表示される。
         * 奇数行は背景が白、偶数行は背景が灰色で表示される。
         * 一番下に最終更新日時が表示される（日付フォーマットは「yyyy/MM/dd HH:mm:ss」）。
         */
    }
    
    //@Test
    /**
     * 長いタイトル
     */
    public void iscp_4170() {
        /*
         * 長いタイトルのウィジェットが存在する場合は、表示しきれない部分は切れる。
         * 「追加>>」「追加済み」が優先表示される。
         */
    }
    
    //@Test
    /**
     * 新着ウィジェット
     */
    public void iscp_4171() {
        /*
         * 前回ログオフ時間（前回ログオフ時間が1営業日以内の場合は、1営業日）前以降にランキングに登場したウィジェットはタイトル右に赤字斜体で「New!!」という表示が出る。
         * タイトルが長い場合もNew!!ができるだけ（タイトルより先に）隠れないことを確認する。
         */
    }
    
    //@Test
    /**
     * エラー
     */
    public void iscp_4172() {
        /*
         * 人気ウィジェット正常表示後にウィジェットリロードしてエラーが発生しても（DB停止などで確認）、前のまま人気ウィジェット一覧が表示されている。
         * メッセージコンソールには「WidgetRanking「人気ウィジェット」でエラーが発生しました。500 -- Internal Server Error」と表示される。
         * 人気ウィジェット初期表示時にエラーが発生した場合は、ウィジェット内に「データが取得できませんでした。」と表示される。
         */
    }

}
