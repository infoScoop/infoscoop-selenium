package org.infoscoop_selenium.screenshot;


import static org.junit.Assert.assertTrue;

import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.properties.TestEnv;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * トップメニューのスクリーンショット
 * @author mikami
 *
 */
public class TopMenuScreenShot extends IS_BaseItTestCase{
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();
	}

	@Test
	/**
	 * トップメニュー（マウスオーバー_ニュース）
	 */
	public void トップメニュー_マウスオーバー_ニュース(){
		WebDriver driver = getDriver();
		
		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		TestHelper.waitPresent(driver, By.id("news"));
		
		// トップメニューにマウスオーバー
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("news")));

		if(isFireFox())
			action.contextClick();
		
		action.perform();

		// トップメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("mg_news_0"));
		
		TestHelper.getScreenShot("トップメニュー（マウスオーバー_ニュース）", getDriver());

		assertTrue(true);
	}
	
	@Test
	/**
	 * トップメニュー（マウスオーバー_スポーツ）
	 */
	public void トップメニュー_マウスオーバー_スポーツ(){
		WebDriver driver = getDriver();
		
		TestHelper.waitPresent(driver, By.id("sports"));
		
		// トップメニューにマウスオーバー
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("sports")));
		
		if(isFireFox())
			action.contextClick();

		action.perform();

		// トップメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("mg_sports_0"));
		
		TestHelper.getScreenShot("トップメニュー（マウスオーバー_スポーツ）", getDriver());

		assertTrue(true);
	}

	@Test
	/**
	 * トップメニュー（マウスオーバー_その他ガジェット）
	 */
	public void トップメニュー_マウスオーバー_その他ガジェット(){
		WebDriver driver = getDriver();
		
		TestHelper.waitPresent(driver, By.id("etcWidgets"));
		
		// トップメニューにマウスオーバー
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("etcWidgets")));

		if(isFireFox())
			action.contextClick();

		action.perform();
		
		// トップメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("mg_etcWidgets_0"));
		
		TestHelper.getScreenShot("トップメニュー（マウスオーバー_その他ガジェット）", getDriver());

		assertTrue(true);
	}
	
	@Test
	/**
	 * トップメニュー（マウスオーバー_多段表示）
	 */
	public void トップメニュー_多段表示(){
		WebDriver driver = getDriver();
		
		TestHelper.waitPresent(driver, By.id("news"));
		
		//画面サイズをかえる
		driver.manage().window().setSize(new Dimension(360, 300));
		
		// トップメニューにマウスオーバー
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("news")));
		
		if(isFireFox())
			action.contextClick();
		
		action.perform();
		
		// トップメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("mg_news_0"));
		
		TestHelper.getScreenShot("トップメニュー（マウスオーバー_多段表示）", getDriver());

		assertTrue(true);
	}
	
}
