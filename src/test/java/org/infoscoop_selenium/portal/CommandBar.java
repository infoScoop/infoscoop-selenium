package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.commandbar.PortalPreference;
import org.infoscoop_selenium.portal.commandbar.TrashBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CommandBar {
	WebDriver driver;
	PortalPreference portalPreference;
	TrashBox trashBox;
	
	public CommandBar(WebDriver driver) {
		this.driver = driver;
		this.portalPreference = new PortalPreference(this, driver);
		this.trashBox = new TrashBox(this, driver);
	}
	
	/**
	 * ユーザーメニュー開く
	 */
	public void openMenu(){
		WebElement userMenu = this.driver.findElement(By.id("portal-user-menu-body"));
		
		if(userMenu.isDisplayed())
			return;
			
		TestHelper.waitPresent(this.driver, By.id("columns0"));
		this.driver.findElement(By.id("portal-user-menu")).click();
	}
	
	public PortalPreference getPortalPreference(){
		return portalPreference;
	}
	
	public TrashBox getTrashBox(){
		return trashBox;
	}
}
