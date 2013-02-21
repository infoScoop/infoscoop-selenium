package org.infoscoop_selenium.screenshot;


import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.commandbar.PortalPreference;
import org.junit.Test;

/**
 * タブUIのスクリーンショット
 * @author nishiumi
 *
 */
public class CommandbarScreenShot extends IS_BaseItTestCase{
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
	}

	@Test
	/**
	 * 全体設定
	 */
	public void 全体設定(){
		// login
		getPortal().login("test_user1", "password");
		
		getPortal().getCommandBar().getPortalPreference().show();
		
		TestHelper.getScreenShot("全体設定", getDriver());

		assertTrue(true);
	}
	
	@Test
	/**
	 * タブメニュー（固定タブ）
	 */
	public void 全体設定_壁紙変更(){
		// login
		getPortal().login("test_user1", "password");
		
		PortalPreference pp = getPortal().getCommandBar().getPortalPreference();
		pp.show();
		
		for(int idx=0;idx<pp.getBackGroundImageNum();idx++){
			pp.changeBackGroundImage(idx);
			pp.hide();
			TestHelper.getScreenShot("全体設定_壁紙変更_" + (idx), getDriver());
			pp.show();
		}
		
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
