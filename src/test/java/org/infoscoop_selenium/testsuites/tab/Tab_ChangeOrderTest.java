package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.infoscoop_selenium.Portal;
import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Panel;
import org.infoscoop_selenium.portal.Tab;
import org.infoscoop_selenium.portal.TopMenu;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.infoscoop_selenium.portal.gadget.AlarmGadget;
import org.junit.Test;

public class Tab_ChangeOrderTest extends IS_BaseItTestCase {

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
	 * 順序変更
	 */
	public void iscp_5723() {
		// 固定タブのリストを取得
		List<String> expected = new ArrayList<String>();
		expected = getPortal().getTab().getTabIdList();
		
		//　タブを2つ追加
		String tab1 = getPortal().getTab().addTab();
		String tab2 = getPortal().getTab().addTab();
		
		//　2つめのタブを1つめのタブの前に移動
		getPortal().getTab().dragAndDropTabToTopLeft(tab2, tab1);
		
		// タブIDのリストの期待値(順番は固定タブ,tab2,tab1)	
		expected.add(tab2);
		expected.add(tab1);
		
		//　順序変更後のタブIDのリストが期待値と同じになっているかを確認
		List<String> actual = getPortal().getTab().getTabIdList();
		assertEquals(expected, actual);
	}
	
	@Test
	/**
	 * 固定タブは移動不可
	 */
	public void iscp_5725() {
		// タブ1つしかなければ新規タブ追加
		if (getPortal().getTab().getNumberOfTab() <= 1){
			getPortal().getTab().addTab();
		}
		
		// タブのリストを取得
		List<String> expected = new ArrayList<String>();
		expected = getPortal().getTab().getTabIdList();
		
		// ドラッグされるタブ（ホーム）ID
		String homeTabId = ISConstants.TABID_HOME;
		// ドロップ先のタブID
		String nextTabId = getPortal().getTab().getNextTabId(homeTabId);
		
		// ホームタブを右隣のタブの右へ移動
		getPortal().getTab().dragAndDropTabToTopRight(homeTabId, nextTabId);
		
		// 移動されていないことを確認
		List<String> actual = getPortal().getTab().getTabIdList();
		assertEquals(expected, actual);
	}
	
	@Test
	/**
	 * 移動可能な位置
	 */
	public void iscp_5726() {
		String nextTabId;
		
		//新規タブ3つ追加
		String tab1Id = getPortal().getTab().addTab();
		String tab2Id = getPortal().getTab().addTab();
		String tab3Id = getPortal().getTab().addTab();
		
		//左端へ移動（tab3をtab1の左へ移動、結果: tab3, tab1, tab2）
		getPortal().getTab().dragAndDropTabToTopLeft(tab3Id, tab1Id);
		//移動されているか確認(tab3の右がtab1)
		nextTabId = getPortal().getTab().getNextTabId(tab3Id);
		assertEquals(tab1Id, nextTabId);
		
		//右端へ移動（tab3をtab2の右へ移動、結果: tab1, tab2, tab3）
		getPortal().getTab().dragAndDropTabToTopRight(tab3Id, tab2Id);
		//移動されているか確認(tab2の右がtab3)
		nextTabId = getPortal().getTab().getNextTabId(tab2Id);
		assertEquals(tab3Id, nextTabId);
		
		//間へ移動（tab3をtab1とtab2の間に移動、結果: tab1, tab3, tab2）
		getPortal().getTab().dragAndDropTabToTopLeft(tab3Id, tab2Id);
		//移動されているか確認(tab1の右がtab3)
		nextTabId = getPortal().getTab().getNextTabId(tab1Id);
		assertEquals(tab3Id, nextTabId);
		//移動されているか確認(tab3の右がtab2)
		nextTabId = getPortal().getTab().getNextTabId(tab3Id);
		assertEquals(tab2Id, nextTabId);
				
		//複数段の間の段へ移動（2段目以降のタブをtab1とtab3の間に移動、結果: tab1, tablast, tab3, tab2）
		//タブを2段以上になるように十分な任意の数作成
		for (int i = 0; i < 31; i++) {
			getPortal().getTab().addTab();
		}
		String tablastId = getPortal().getTab().addTab();
		//右端のタブをtab1とtab3の間に移動
		getPortal().getTab().dragAndDropTabToTopLeft(tablastId, tab3Id);
		//移動されているか確認(tab1の右がtab4)
		nextTabId = getPortal().getTab().getNextTabId(tab1Id);
		assertEquals(tablastId, nextTabId);
		//移動されているか確認(tab4の右がtab3)
		nextTabId = getPortal().getTab().getNextTabId(tablastId);
		assertEquals(tab3Id, nextTabId);
	}
	
	@Test
	/**
	 * タブの内容
	 * 移動後も各タブのパネル内容に変化がないことを確認
	 */
	public void iscp_5727() {
		Portal portal = getPortal();
		TopMenu topMenu = portal.getTopMenu();
		Tab tab = portal.getTab();
		
		//タブを追加
		String tab1Id = tab.addTab();
		String tab2Id = tab.addTab();
		
		Panel panel1 = portal.getPanel(tab1Id);
		Panel panel2 = portal.getPanel(tab2Id);
		
		int numberOfColumnPanel1 = panel1.getColumnLength();
		int numberOfColumnPanel2 = panel2.getColumnLength();
		
		if (3 > numberOfColumnPanel1 && 3 > numberOfColumnPanel2){
			fail("This test assumes that the number of columns is atleast 3.");
		}
		
		// drop 10 gadgets (sticky) to panel
		tab.selectTab(tab1Id);
		for (int columnNum = 1; columnNum <= 3; columnNum++) {
			topMenu.dropGadget("etcWidgets", "etcWidgets_stickey", columnNum % numberOfColumnPanel1 + 1);
		}
		
		tab.selectTab(tab2Id);
		for (int columnNum = 1; columnNum <= 3; columnNum++) {
			topMenu.dropGadget("etcWidgets", "etcWidgets_alarm", columnNum % numberOfColumnPanel1 + 1);
		}
		
		//ガジェットの並び順を取得
		List<List<String>> panel1BeforeGadgetIds = panel1.getDeployedGadgetIds();
		List<List<String>> panel2BeforeGadgetIds = panel2.getDeployedGadgetIds();
		
		//タブの順序を入れ替え
		getPortal().getTab().dragAndDropTabToTopLeft(tab2Id, tab1Id);
		
		//入れ替え後のガジェットの並び順を取得
		List<List<String>> panel1AfterGadgetIds = panel1.getDeployedGadgetIds();
		List<List<String>> panel2AfterGadgetIds = panel2.getDeployedGadgetIds();
		
		//パネル1のガジェットの並び順が変わっていないことを確認
		int beforeColumnNum1 = panel1BeforeGadgetIds.size();
		int afterColumnNum1 = panel1AfterGadgetIds.size();
		if (beforeColumnNum1 != afterColumnNum1){
			fail("Number of columns is illegal.");
		}
		for (int i = 0 ; i < beforeColumnNum1 ; i++) {
			if (panel1BeforeGadgetIds.get(i).size() != panel1AfterGadgetIds.get(i).size()){
				fail("Number of columns is illegal.");
			}
		}
		for (int i = 0 ; i < afterColumnNum1; i++) {
			for ( int k = 0, end2 = panel1AfterGadgetIds.get(i).size(); k < end2; k++ ) {
				String before = panel1BeforeGadgetIds.get(i).get(k);
				String after = panel1AfterGadgetIds.get(i).get(k);
				assertEquals(after, before);
			}
		}
		
		//パネル2のガジェットの並び順が変わっていないことを確認
		int beforeColumnNum2 = panel2BeforeGadgetIds.size();
		int afterColumnNum2 = panel2AfterGadgetIds.size();
		if (beforeColumnNum2 != afterColumnNum2){
			fail("Number of columns is illegal.");
		}
		for (int i = 0 ; i < beforeColumnNum2 ; i++) {
			if (panel2BeforeGadgetIds.get(i).size() != panel2AfterGadgetIds.get(i).size()){
				fail("Number of columns is illegal.");
			}
		}
		for (int i = 0 ; i < afterColumnNum2; i++) {
			for ( int k = 0, end2 = panel2AfterGadgetIds.get(i).size(); k < end2; k++ ) {
				String before = panel2BeforeGadgetIds.get(i).get(k);
				String after = panel2AfterGadgetIds.get(i).get(k);
				assertEquals(after, before);
			}
		}
	}
	
	@Test
	/**
	 * アクティブタブの切り替え
	 */
	public void iscp_5729() {
		//新規タブ3つ追加
		String tab1Id = getPortal().getTab().addTab();
		String tab2Id = getPortal().getTab().addTab();
		String tab3Id = getPortal().getTab().addTab();
		
		//アクティブタブ
		String before = getPortal().getTab().getCurrentTabId();
		
		//tab1をtab2とtab3の間に移動（結果: tab2, tab1, tab3）
		getPortal().getTab().dragAndDropTabToTopRight(tab1Id, tab2Id);
		//移動されているか確認
		String nextTabId = getPortal().getTab().getNextTabId(tab1Id);
		assertEquals(tab3Id, nextTabId);
		
		//アクティブタブがtab3のまま変わらないことを確認
		String after = getPortal().getTab().getCurrentTabId();
		assertEquals(before, after);
	}
}
