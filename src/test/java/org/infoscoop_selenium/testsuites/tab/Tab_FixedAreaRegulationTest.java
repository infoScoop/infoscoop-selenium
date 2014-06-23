package org.infoscoop_selenium.testsuites.tab;

import static org.junit.Assert.fail;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.infoscoop_selenium.portal.gadget.GenericGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Tab_FixedAreaRegulationTest extends IS_BaseItTestCase {
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();

		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
	}

	//@Test
	/**
	 * 横スクロールバーは表示されない
	 */
	public void iscp_5823(){
		
	}

	@Test
	/**
	 * [削除]は非表示
	 */
	public void iscp_5824(){
		WebDriver driver = getDriver();
		
		// ガジェットの表示を待つ
		TestHelper.waitPresent(driver, By.id("p_1_w_1"));
		
		Gadget gadget = new GenericGadget(driver, "p_1_w_1");
		
		// ガジェットメニューを開く
		gadget.openMenu();
		
		// [削除]が存在しない
		try {
			gadget.getCloseElement();
			fail("must throw NoSuchElementException");
		} catch (NoSuchElementException e) {
			//success
		}
	}

	//@Test
	/**
	 * 高さ調整
	 */
	public void iscp_5825(){
		
	}

	@Test
	/**
	 * 表示件数増減の非表示　-RSSReader1
	 */
	public void iscp_5826(){
		WebDriver driver = getDriver();
		
		// ガジェットの表示を待つ
		TestHelper.waitPresent(driver, By.id("p_1_w_1"));
		
		Gadget gadget = new GenericGadget(driver, "p_1_w_1");
		
		// ガジェットメニューを開く
		gadget.openMenu();
		
		// [表示件数を減らす]が存在しない
		try {
			gadget.getWidgetRssUpElement();
			fail("must throw NoSuchElementException");
		} catch (NoSuchElementException e) {
			//success
		}
		
		// [表示件数を増やす]が存在しない
		try {
			gadget.getWidgetRssDownElement();
			fail("must throw NoSuchElementException");
		} catch (NoSuchElementException e) {
			//success
		}
	}

	@Test
	/**
	 * 表示件数増減の非表示　-RSSReader2
	 */
	public void iscp_5827(){
		WebDriver driver = getDriver();
		
		// ガジェットの表示を待つ
		TestHelper.waitPresent(driver, By.id("p_1_w_1"));
		
		Gadget gadget = new GenericGadget(driver, "p_1_w_1");
		
		// ガジェットメニューを開く
		gadget.getGadgetPreference().show();
		
		// [記事表示件数]が存在しない
		try {
			gadget.getGadgetPreference().getItemsnumElement();
			fail("must throw NoSuchElementException");
		} catch (NoSuchElementException e) {
			//success
		}
	}

	//@Test
	/**
	 * 高さ調整 -Gadget
	 */
	public void iscp_5828(){
		
	}
}
