package org.infoscoop_selenium.testsuites.tab;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.junit.Test;

public class Tab_TabMenu_ReloadTest extends IS_BaseItTestCase {

    @Override
    public void doBefore() {
        // テストケースごとの事前処理
        // login
        getPortal().login();
    }

//    @Test
//    /**
//     * 再読み込み
//     * 「再読み込み」をクリックするとタブ内の全てのガジェットが更新されることを確認する。
//     * TODO: 全ガジェットが更新されたことなどが分かる手段が無いとできそうにない。
//     */
//    public void iscp_5739() {}

//    @Test
//    /**
//     * インディケータ
//     * 「再読み込み」クリックで全ガジェットが更新されるまでの間、タブ名左側にインディケータが表示されることを確認する。
//     * TODO: 不定期で非表示になる要素の状態検出は難しい。（ものによっては一瞬で非表示になる。）
//     */
//    public void iscp_5740() {}

//    @Test
//    /**
//     * ウィジェットの削除
//     * 再読み込み中に、まだ再読込みされていないガジェットを削除した場合も正常に動作することを確認する。
//     * TODO: 対象ガジェットの更新を終了させずに削除し、他のガジェットは更新されたことを確認できる手段が無いとできそうにない。
//     */
//    public void iscp_5741() {}

}
