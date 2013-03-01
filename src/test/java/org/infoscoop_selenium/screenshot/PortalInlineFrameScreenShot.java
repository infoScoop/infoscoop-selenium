package org.infoscoop_selenium.screenshot;

import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.infoscoop_selenium.portal.gadget.GenericGadget;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


/**
 * ポータル内フレームのスクリーンショット
 * @author mikami
 *
 */
public class PortalInlineFrameScreenShot extends IS_BaseItTestCase {

	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login("test_user2", "password");
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
	}
	
	@Test
	/**
	 * ポータル内フレーム
	 */
	public void ポータル内フレーム(){
		WebDriver driver = getDriver();
		
		// ガジェットの表示を待つ
		TestHelper.waitPresent(driver, By.id("p_1_w_1"));
		
		Gadget gadget = new GenericGadget(driver, "p_1_w_1");
		
		// ガジェットメニューを開く
		gadget.getGadgetPreference().show();

		// コンテンツ表示モードをポータル内フレームに設定
		Select select = new Select(driver.findElement(By.xpath("//td[@id='eb_p_1_w_1_itemDisplay']/select")));
		select.selectByIndex(0);
		gadget.getGadgetPreference().ok();

		// アイテムを選択
		driver.findElement(By.xpath("//div[@id='p_1_w_1_item_0']/table/tbody/tr/td/table/tbody/tr/td/div/a")).click();

		// フレームを移動
		TestHelper.switchToFrame(driver, "ifrm");
		
		TestHelper.getScreenShot("ポータル内フレーム", driver);
	
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
