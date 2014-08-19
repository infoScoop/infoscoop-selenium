package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.*;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.constants.ISConstants;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Panel;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.thoughtworks.selenium.Wait;

/**
 * タブ / 列幅変更
 */
public class Tab_ChangetTabWidthTest extends IS_BaseItTestCase{
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
	 * iscp-5774 列幅調整バー表示
	 */
	public void iscp_5774(){
		// ホームパネルの取得
		Panel panel = getPortal().getPanel(ISConstants.TABID_HOME);
		
		// 列幅調整バーの取得
		WebElement bar = panel.getAdjustBar(0);
		
		// マウスフォーカス
		Actions actions = new Actions(getDriver());
		actions.moveToElement(bar);
		actions.build().perform();
		
		// CSSクラス確認
		assertEquals(bar.getAttribute("class"), "adjustBarOver");
		
		// スナップショット取得
		TestHelper.getScreenShot("iscp-5774_列幅調整バー表示", getDriver(), true);
	}
	
	@Test
	/**
	 * iscp-5775 列数変更
	 */
	public void iscp_5775(){
		// ホームパネルの取得
		Panel panel = getPortal().getPanel(ISConstants.TABID_HOME);
		
		int columnLengthBefore = panel.getColumnLength();
		
		// タブメニューボタンを選択
		getPortal().getTab().selectSelectMenu(ISConstants.TABID_HOME);
		
		// 列数を変更
		Select menu = new Select(getPortal().getTab().getColumnNumSelectElement(ISConstants.TABID_HOME));
		menu.selectByValue(Integer.toString(columnLengthBefore + 1));
		
		// 値が反映するのを待機
		Wait wait = new Wait() {
			@Override
			public boolean until() {
				Select menu =  new Select(getPortal().getTab().getColumnNumSelectElement(ISConstants.TABID_HOME));
				if( getPortal().getPanel(ISConstants.TABID_HOME).getColumnLength() == Integer.parseInt(menu.getFirstSelectedOption().getText()) ) {
					return true;
				}
				return false;
			}
		};
		wait.wait("Wait to Aplly Value", 5000);
		
		int columnLengthAfter = panel.getColumnLength();
		
		// 列数が1増えていれば成功
		assertEquals(columnLengthAfter, columnLengthBefore + 1);
	}
	
	@Test
	/**
	 * iscp-5776 列幅調整バーの非表示
	 */
	public void iscp_5776(){
		// ホームパネルの取得
		Panel panel = getPortal().getPanel(ISConstants.TABID_HOME);
		
		// 列幅調整バーの取得
		WebElement bar = panel.getAdjustBar(0);
		
		// 列幅調整バーにフォーカス
		Actions actions = new Actions(getDriver());
		actions.moveToElement(bar);
		actions.build().perform();
		
		assertEquals(bar.getAttribute("class"), "adjustBarOver");
		
		// フォーカス位置をずらす
		actions.moveByOffset(100, 0);
		actions.build().perform();
		
		// CSSクラスの確認
		assertEquals(bar.getAttribute("class"), "adjustBarOut");
		
		// スナップショット取得
		TestHelper.getScreenShot("iscp-5776_列幅調整バーの非表示", getDriver(), true);
	}
	
	@Test
	/**
	 * iscp-5777 列幅の変更
	 */
	public void iscp_5777(){
		// ホームパネルの取得
		Panel panel = getPortal().getPanel(ISConstants.TABID_HOME);
		
		// 1番目のカラム
		WebElement column = panel.getColumnElements().get(0);
		int beforeWidth = column.getSize().width;
		
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
		
		int afterWidth = column.getSize().width;
		
		assertEquals(afterWidth, beforeWidth + movement);
	}
	
	@Test
	/**
	 * iscp-5778 列幅の変更（再ログイン）
	 */
	public void iscp_5778(){
		// ホームパネルの取得
		Panel panel = getPortal().getPanel(ISConstants.TABID_HOME);
		
		// 1番目のカラム
		WebElement column = panel.getColumnElements().get(0);
		int beforeWidth = column.getSize().width;
		
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
		
		// カラム幅取得
		int afterWidth1 = column.getSize().width;
		assertEquals(afterWidth1, beforeWidth + movement);
		
		// 再ログイン
		getPortal().login();
		
		// 再度カラム幅取得
		int afterWidth2 = getPortal().getPanel(ISConstants.TABID_HOME).getColumnElements().get(0).getSize().width;
		
		assertEquals(afterWidth1, afterWidth2);
	}
}
