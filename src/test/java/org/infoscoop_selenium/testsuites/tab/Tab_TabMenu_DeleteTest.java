package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.*;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.infoscoop_selenium.portal.commandbar.TrashBox;
import org.infoscoop_selenium.portal.gadget.GenericGadget;
import org.junit.Test;

/**
 * タブ/タブ削除
 */
public class Tab_TabMenu_DeleteTest extends IS_BaseItTestCase{
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
	}

	@Test
	/**
	 * iscp-5753:タブ削除
	 * [削除]をクリックすると、削除確認のダイアログが表示され、OKをクリックすると指定のタブが削除されることを確認
	 */
	public void iscp_5753(){
		// 初期タブ数
		int beforeNumberOfTab = getPortal().getTab().getNumberOfTab();

		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();

		//タブメニューの表示
		getPortal().getTab().selectSelectMenu(addedTabId);

		//削除メニューの選択
		getPortal().getTab().selectCloseItem(addedTabId, true);

		int afterNumberOfTab = getPortal().getTab().getNumberOfTab();

		assertTrue(beforeNumberOfTab == afterNumberOfTab);
	}

	@Test
	/**
	 * iscp-5754:タブ削除/キャンセル
	 * 削除確認のダイアログをキャンセルするとタブが削除されないことを確認
	 */
	public void iscp_5754(){
		// 初期タブ数
		int beforeNumberOfTab = getPortal().getTab().getNumberOfTab();

		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();

		//タブメニューの表示
		getPortal().getTab().selectSelectMenu(addedTabId);

		//削除メニューの選択してダイアログをキャンセル
		getPortal().getTab().selectCloseItem(addedTabId, false);

		int afterNumberOfTab = getPortal().getTab().getNumberOfTab();

		assertTrue(beforeNumberOfTab + 1 == afterNumberOfTab);
	}

	@Test
	/**
	 * iscp-5755:保存確認
	 * タブ削除後に画面の再読み込みを行っても、削除したタブが表示されないことを確認
	 */
	public void iscp_5755(){
		// 初期タブ数
		int beforeNumberOfTab = getPortal().getTab().getNumberOfTab();

		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();

		//タブメニューの表示
		getPortal().getTab().selectSelectMenu(addedTabId);

		//削除メニューの選択
		getPortal().getTab().selectCloseItem(addedTabId, true);

		//ポータル再表示
		getPortal().login();

		int afterNumberOfTab = getPortal().getTab().getNumberOfTab();

		assertTrue(beforeNumberOfTab == afterNumberOfTab);
	}


	@Test
	/**
	 * iscp-5757:ウィジェットの削除
	 * タブ削除後、削除したタブ内の全ての Widget がごみ箱に入る
	 */
	public void iscp_5757(){
		// 新規タブ追加
		String addedTabId = getPortal().getTab().addTab();

		// ガジェットのドロップ
		Gadget gadget = (GenericGadget) getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_calculator", 1);
		System.out.println(gadget.getId());

		TestHelper.sleep(2000);

		//タブメニューの表示
		getPortal().getTab().selectSelectMenu(addedTabId);

		//削除メニューの選択
		getPortal().getTab().selectCloseItem(addedTabId, true);

		//ゴミ箱の表示
		TrashBox trashBox = getPortal().getCommandBar().getTrashBox();
		trashBox.show();

		int trashedItemCount = trashBox.getTrashedItemCount();
		
		assertTrue(trashedItemCount == 1);
}

}
