package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


/**
 * ガジェットのドラッグ＆ドロップスクリーンショット
 * @author mikami
 *
 */
public class DragForGadgetScreenShot extends IS_BaseItTestCase {	
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
//		GADGET = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_worldclock", 1, GADGET_TYPE.GENERIC, true);
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
	}
	
	@Test
	/**
	 * ガジェットのドラッグ（トップメニュー）
	 */
	public void ガジェットドラッグ_トップメニュー(){
		WebDriver driver = getDriver();		

		// ガジェットのドロップ
		getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_worldclock", 1, GADGET_TYPE.GENERIC, false);
		
		TestHelper.getScreenShot("ガジェットのドラッグ（トップメニュー）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * ガジェットのドラッグ（トップメニュー／Ctrlオプション）
	 */
	public void ガジェットのドラッグ_トップメニュー_Ctrlオプション(){
		WebDriver driver = getDriver();
		
		// ガジェットのドロップ
		getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_worldclock", 1, GADGET_TYPE.GENERIC, false);

		// Ctrlキーを押す
		Actions action = new Actions(driver);
		action.sendKeys(Keys.CONTROL).perform();
		
		TestHelper.getScreenShot("ガジェットのドラッグ（トップメニュー／Ctrlオプション）", driver);
	
		assertTrue(true);
	}
	
	@Test
	/**
	 * ガジェットのドラッグ（カラム移動）
	 */
	public void ガジェットのドラッグ_カラム移動(){
		WebDriver driver = getDriver();
		
		// ガジェットの追加
		Gadget gadget = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_worldclock", 1, GADGET_TYPE.GENERIC, true);

		sleep(1000);

		// ガジェットの移動
		gadget.moveColumn(3, false);
		
		TestHelper.getScreenShot("ガジェットのドラッグ（カラム移動）", driver);
	
		assertTrue(true);
	}

	@Test
	/**
	 * ガジェットのドラッグ（カラム移動／Ctrlオプション）
	 */
	public void ガジェットのドラッグ_カラム移動_Ctrlオプション(){
		WebDriver driver = getDriver();
		
		// ガジェットのドロップ
		Gadget gadget = getPortal().getTopMenu().dropGadget("etcWidgets", "etcWidgets_worldclock", 1, GADGET_TYPE.GENERIC, true);

		sleep(1000);
		
		// ガジェットの移動
		gadget.moveColumn(3, false);
		
		Actions action = new Actions(driver);
		action.sendKeys(Keys.CONTROL).perform();
		
		TestHelper.getScreenShot("ガジェットのドラッグ（カラム移動／Ctrlオプション）", driver);
	
		assertTrue(true);
	}
	
	private static void sleep(long sleep){
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
