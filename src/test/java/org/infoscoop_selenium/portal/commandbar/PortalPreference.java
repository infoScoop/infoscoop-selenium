package org.infoscoop_selenium.portal.commandbar;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.CommandBar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PortalPreference {
	WebDriver driver;
	CommandBar commandBar;
	
	public PortalPreference(CommandBar commandBar, WebDriver driver) {
		this.driver = driver;
		this.commandBar = commandBar;
	}
	
	/**
	 * カスタマイズ情報の初期化
	 */
	public void initializeData(){
		commandBar.openMenu();
		
		TestHelper.waitPresent(this.driver, By.id("portal-preference"));
		this.driver.findElement(By.id("portal-preference")).click();
		
		WebElement modal_container = this.driver.findElement(By.id("modal_container"));
		
		// confirmの抜け方がわからないので無理やり
		JavascriptExecutor js = (JavascriptExecutor)this.driver;
		js.executeScript("window.orig_confirm = window.confirm;"); 
		js.executeScript("window.orig_alert = window.alert;");
		js.executeScript("window.confirm = function(msg){ document.last_confirm=msg; return true;};");
		js.executeScript("window.alert = function(msg){ document.last_alert=msg; return true;};");

		modal_container.findElement(By.xpath("//fieldSet[5]//input[@type='button']")).click();
		TestHelper.waitInvisible(this.driver, By.id("divOverlay"));

		js.executeScript("window.confirm = window.orig_confirm;");
		js.executeScript("window.alert = window.orig_alert;");
	}
}
