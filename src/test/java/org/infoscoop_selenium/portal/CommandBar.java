package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.commandbar.PortalPreference;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CommandBar {
	WebDriver driver;
	PortalPreference portalPreference;
	
	public CommandBar(WebDriver driver) {
		this.driver = driver;
		this.portalPreference = new PortalPreference(this, driver);
	}
	
	/**
	 * ユーザーメニュー開く
	 */
	public void openMenu(){
		TestHelper.waitPresent(this.driver, By.id("columns0"));
		this.driver.findElement(By.id("portal-user-menu")).click();
	}
	
	public PortalPreference getPortalPreference(){
		return portalPreference;
	}
}
