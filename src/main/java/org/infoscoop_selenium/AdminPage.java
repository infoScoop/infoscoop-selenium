package org.infoscoop_selenium;

import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage {
	WebDriver driver;
	WebDriver messageConsoleDriver;
	Portal portal;

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
}
