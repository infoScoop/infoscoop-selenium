package org.infoscoop_selenium.screenshot;


import org.infoscoop_selenium.base.IS_BaseItTestCase;
import org.infoscoop_selenium.helper.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * タブUIのスクリーンショット
 * @author mikami
 *
 */
public class TabScreenShot extends IS_BaseItTestCase{
	
	@Override
	public void doBefore() {
		// テストケースごとの事前処理
		// login
		getPortal().login();
		
		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
	}

	@Override
	public void doAfter() {
		// テストケースごとの事後処理
	}

	@Test
	/**
	 * タブメニュー（固定タブ）
	 */
	public void タブメニュー_固定タブ(){
		WebDriver driver = getDriver();
		
		// タブメニューボタンの表示を待つ
		TestHelper.waitPresent(driver, By.id("tab0_selectMenu"));
		driver.findElement(By.id("tab0_selectMenu")).click();
		
		// タブメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("tab0_menu"));
		
		TestHelper.getScreenShot("タブメニュー（固定タブ）", getDriver());

		assertTrue(true);
	}
	
	@Test
	/**
	 * タブメニュー（動的タブ）
	 */
	public void タブメニュー_動的タブ(){
		WebDriver driver = getDriver();
		
		// タブを追加するリンクの表示を待つ
		TestHelper.waitPresent(driver, By.id("addTab"));
		driver.findElement(By.xpath("//div[@id='addTab']/a")).click();
		
		// 動的タブの作成を待つ
		TestHelper.waitPresent(driver, By.id("tab1"));
		driver.findElement(By.id("tab1_selectMenu")).click();
		
		// タブメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("tab1_menu"));

		TestHelper.getScreenShot("タブメニュー（動的タブ）", getDriver());
		
		assertTrue(true);
	}
	
	@Test
	/**
	 * 画面横幅を超えた際の折り返し
	 */
	public void 画面横幅を超えた際の折り返し(){
		WebDriver driver = getDriver();
		
		// login
		getPortal().login();
		
		// 初期化
		getPortal().getCommandBar().getPortalPreference().initializeData();
		
		// タブを追加するリンクの表示を待つ
		TestHelper.waitPresent(driver, By.id("addTab"));
		
		// 動的タブを作成する
		for(int i=1;i<30;i++){
			driver.findElement(By.xpath("//div[@id='addTab']/a")).click();
			TestHelper.waitPresent(driver, By.id("tab"+i));
		}
		
		TestHelper.getScreenShot("画面横幅を超えた際の折り返し", getDriver());
		
		assertTrue(true);
	}
	
}
