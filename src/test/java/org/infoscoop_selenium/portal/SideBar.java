package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget.GADGET_TYPE;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	 * サイトメニューを閉じる
	 */
	public void closeSiteMenu() {
		WebElement element = driver.findElement(By.id("closeImage"));
		if(element.isDisplayed())
			element.click();
	}
	
	/**
	 * コンテンツ追加を閉じる
	 */
	public void closeAddContents() {
		WebElement element = driver.findElement(By.id("closeRssSearch"));
		if(element.isDisplayed())
			element.click();
	}
	
	/**
	 * マイサイトマップを閉じる
	 */
	public void closeMySiteMap() {
		WebElement element = driver.findElement(By.className("mySiteMapClose"));
		if(element.isDisplayed())
			element.click();
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
	 * @return 
	 */
	public Gadget addContents(String url) {
		return addContents(url, GADGET_TYPE.GENERIC);
	}
	
	/**
	 * コンテンツ追加（ドロップ）
	 * @return 
	 */
	public Gadget addContents(String url, GADGET_TYPE gadgetType) {
		previewContents(url);
		
		// 追加ボタンをクリック
		driver.findElement(By.xpath("//div[@class='SidePanel_AddContents']/div[5]/div/div[1]/div[3]/input[@type='button']")).click();
		
		// サイドバーを閉じる
		closeAddContents();
		
		// ドロップされたガジェットのIDを取得
		WebElement dropElement = driver.findElement(By.xpath("//div[@class='column' and @colnum='1']"));
		String widgetId = dropElement.findElements(By.className("widget")).get(0).getAttribute("id");

		Class<Gadget> c = gadgetType.getValue();
		try {
			return (Gadget)c.getConstructor(new Class[]{WebDriver.class, String.class})
					.newInstance(new Object[]{driver, widgetId});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
