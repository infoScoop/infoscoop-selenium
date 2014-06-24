package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.*;

import java.util.List;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.infoscoop_selenium.portal.Panel;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.thoughtworks.selenium.Wait;

/**
 * タブ/タブメニュー/列の幅をそろえる
 */
public class Tab_TabMenu_adjustColumnWidthTest extends IS_BaseItTestCase{
	WebDriver driver;
	
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
	 * iscp-5766 列の幅を整える
	 */
	public void iscp_5766(){
		// ホームパネルの取得
		Panel panel = getPortal().getPanel(ISConstants.TABID_HOME);
		
		List<WebElement> columns = getPortal().getPanel(ISConstants.TABID_HOME).getColumnElements();
		WebElement column1 = columns.get(0); // 1番目のカラム
		int beforeWidth = column1.getSize().width;
		
		// 列幅調整バー
		WebElement bar = panel.getAdjustBar(0);
		
		// D&Dでバーを移動
		int movement = - (int)Math.floor(beforeWidth / 3); // 移動量
		Actions actions = new Actions(getDriver());
		actions.moveToElement(bar);
		actions.clickAndHold();
		actions.moveByOffset(movement, 0);
		actions.release();
		actions.build().perform();
		
		int afterWidth = column1.getSize().width;
		
		// 列の幅がマウス移動量に応じて変更されたか
		assertEquals(afterWidth, beforeWidth + movement);
		
		// タブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		
		// 列の幅を揃えるを選択
		WebElement columnWidthItem = getPortal().getTab().getResetColumnWidthItem(ISConstants.TABID_HOME);
		columnWidthItem.click();
		
		// 値が反映するのを待機
		Wait wait = new Wait() {
			@Override
			public boolean until() {
				return isValid(getPortal().getPanel(ISConstants.TABID_HOME).getColumnElements());
			}
		};
		wait.wait("Wait to Aplly Value", 5000);
		
		// カラム幅がすべて一致していれば成功
		assertEquals(isValid(columns), true);
	}
	
	// 列の幅が全て一致しているか
	private Boolean isValid(List<WebElement> columns) {
		int width = columns.get(0).getSize().width;
		for (int i=1;i<columns.size();i++) {
			if( columns.get(i).getSize().width != width )
				return false;
		}
		return true;
	}
}
