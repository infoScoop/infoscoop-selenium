package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideBar {
	WebDriver driver;
	
	public SideBar(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * サイトメニューを開く
	 */
	public void openSiteMenu() {
		// サイトメニューボタンの表示を待つ
		TestHelper.waitPresent(driver, By.id("siteMenuOpen"));

		driver.findElement(By.id("openImage")).click();
		
		// サイトメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("portal-tree-menucontainer"));
	}
	
	/**
	 * コンテンツ追加を開く
	 */
	public void openAddContents() {
		// サイトメニューボタンの表示を待つ
		TestHelper.waitPresent(driver, By.id("siteMenuOpen"));
		driver.findElement(By.id("openRssSearch")).click();
		
		// サイトメニューの表示を待つ
		TestHelper.waitPresent(driver, By.id("portal-tree-menucontainer"));
	}
	
	/**
	 * マイサイトマップを開く
	 */
	public void openMySiteMap() {
		// マイサイトマップボタンの表示を待つ
		TestHelper.waitPresent(driver, By.id("siteMenuOpen"));
		driver.findElement(By.className("mySiteMapOpen")).click();
		
		// マイサイトマップの表示を待つ
		TestHelper.waitPresent(driver, By.id("portal-tree-menucontainer"));
	}
	
	/**
	 * コンテンツ追加（プレビュー）
	 */
	public void previewContents(String url) {
		openAddContents();
		
		// URLを入力する
		driver.findElement(By.xpath("//div[@class='SidePanel_AddContents']/div[2]/input[@type='text']")).sendKeys(url);
		
		// プレビューボタンをクリック
		driver.findElement(By.xpath("//div[@class='SidePanel_AddContents']/div[3]/input[@type='button']")).click();

		// ロードを待つ
		TestHelper.waitInvisible(driver, By.xpath("//div[@class='SidePanel_AddContents']/div[3]/img"));
	}
	
	/**
	 * コンテンツ追加（ドロップ）
	 */
	public void addContents(String url) {
		previewContents(url);
		
		// ドロップ
	}
}
