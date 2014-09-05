package org.infoscoop_selenium.admin_page;

import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommandBarAdminPage {
	private WebDriver driver;
	
	public CommandBarAdminPage(WebDriver driver) {
		this.driver = driver;
		waitPageLoading();
	}
	
	/**
	 * defaultRoleの編集画面を開く
	 */
	public void openDefaultRole() {
		By by = By.cssSelector("#tab_commandbar_roleListPanel .configTableDiv:last-of-type img");
		driver.findElement(by).click();
	}

	/**
	 * 変更を適用
	 */
	public void applySettings(){
		driver.findElement(By.id("changeApply")).click();
		TestHelper.waitInvisible(driver, By.id("control_overlay"));
	}
	
	/**
	 * 文字サイズの表示を選択
	 */
	public void selectDisplay() {
		By by = By.cssSelector("#disp_portal-change-fontsize>div>span:nth-of-type(1)");
		driver.findElement(by).click();
	}
	
	public void waitPageLoading(){
		TestHelper.waitPresent(driver, By.cssSelector("#tab_commandbar_roleListPanel .configTableDiv:last-of-type img"));
	}

}
