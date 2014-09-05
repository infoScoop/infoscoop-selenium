package org.infoscoop_selenium;

import org.infoscoop_selenium.admin_page.CommandBarAdminPage;
import org.infoscoop_selenium.admin_page.OtherLayoutAdminPage;
import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage {
	private WebDriver driver;
	private WebDriver messageConsoleDriver;

	public AdminPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 管理画面を閉じる
	 */
	public void close() {
		WindowManager.getInstance().closeWindow(this.driver.getWindowHandle());
	}

	/**
	 * メッセージコンソールを開く
	 * 
	 * @return
	 */
	public void openMessageConsole() {
		TestHelper.waitPresent(driver, By.id("messageIcon"));
		this.messageConsoleDriver = WindowManager.getInstance().newWindow(
				driver.findElement(By.id("messageIcon")));
	}

	/**
	 * メッセージコンソールを閉じ、管理画面にスイッチ
	 */
	public void closeMessageConsole() {
		WindowManager.getInstance().closeWindow(
				this.messageConsoleDriver.getWindowHandle());
		WindowManager.getInstance().switchWindow(this.driver.getWindowHandle());
	}
	
	/**
	 * レイアウト設定画面に遷移
	 */
	public void openLayoutAdminPage() {
		By findBy = By.id("tab_defaultPanel");
		TestHelper.waitPresent(driver, findBy);
		driver.findElement(findBy).click();
	}
	
	/**
	 * 初期画面設定画面に遷移
	 */
	public void openDefaultPanelAdminPage() {
		openLayoutAdminPage();
		By findBy = By.cssSelector("#defaultPanel-side-bar li:nth-child(1)>a");
		TestHelper.waitPresent(driver, findBy);		
		driver.findElement(findBy).click();
	}
	
	/**
	 * 画面その他設定画面に遷移
	 */
	public OtherLayoutAdminPage openOtherLayoutAdminPage() {
		openLayoutAdminPage();
		By findBy = By.cssSelector("#defaultPanel-side-bar li:nth-child(2)>a");
		TestHelper.waitPresent(driver, findBy);		
		driver.findElement(findBy).click();
		
		return new OtherLayoutAdminPage(driver);
	}
	
	/**
	 * コマンドバー設定画面に遷移
	 */
	public CommandBarAdminPage openCommandBarAdminPage() {
		openDefaultPanelAdminPage();
		By findBy = By.cssSelector("#toCommandbar");
		TestHelper.waitPresent(driver, findBy);
		driver.findElement(findBy).click();
		
		return new CommandBarAdminPage(driver);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
