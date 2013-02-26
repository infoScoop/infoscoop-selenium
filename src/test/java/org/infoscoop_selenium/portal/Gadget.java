package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.gadget.GadgetPreference;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Gadget {
	WebDriver driver;
	GadgetPreference gadgetPreference;
	String gadgetId;
	
	public Gadget(WebDriver driver, String gadgetId) {
		this.driver = driver;
		this.gadgetPreference = new GadgetPreference(this, driver);
		this.gadgetId = gadgetId;
	}

	public Gadget(WebDriver driver) {
		this.driver = driver;
		this.gadgetPreference = new GadgetPreference(this, driver);
	}
	
	/**
	 * ガジェットメニューを開く
	 * @param widgetId
	 */
	public void openMenu(String widgetId) {
//		WebElement gadgetMenu = this.driver.findElement(By.id(widgetId+"_close_menu"));
//		
//		if(gadgetMenu.isDisplayed())
//			return;

		this.driver.findElement(By.id("hi_"+widgetId+"_showTools")).click();
		TestHelper.waitPresent(this.driver, By.id(widgetId+"_close_menu"));
	}
	
	/**
	 * ガジェットメニューを開く
	 */
	public void openMenu(){
		openMenu(gadgetId);
	}
	
	/**
	 * ガジェットを閉じる
	 * @return
	 */
	public void close(){
		openMenu();
		driver.findElement(By.id("hm_" + gadgetId + "_close")).click();
	}
	
	public GadgetPreference getGadgetPreference(){
		return gadgetPreference;
	}
	
	public String getId(){
		return gadgetId;
	}
}
