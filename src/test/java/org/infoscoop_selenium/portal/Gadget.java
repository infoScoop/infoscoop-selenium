package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.gadget.GadgetPreference;
import org.infoscoop_selenium.portal.gadget.GenericGadget;
import org.infoscoop_selenium.portal.gadget.RssReaderGadget;
import org.infoscoop_selenium.portal.gadget.StickyGadget;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.openqa.selenium.By;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.events.internal.EventFiringMouse;

public abstract class Gadget {
	WebDriver driver;
	GadgetPreference gadgetPreference;
	String gadgetId;
	ToDoListGadget todoListGadget;
	StickyGadget stickyGadget;
	
	public static enum GADGET_TYPE {
		STICKY(StickyGadget.class),
		TODOLIST(ToDoListGadget.class),
		RSSREADER(RssReaderGadget.class),
		GENERIC(GenericGadget.class);
		
		private final Class gadgetClass;
		private GADGET_TYPE(Class gadgetClass){
			this.gadgetClass = gadgetClass;
		}
		public Class getValue(){
			return gadgetClass;
		}
	}
	
	public Gadget(WebDriver driver, String gadgetId) {
		this.driver = driver;
		this.gadgetPreference = new GadgetPreference(this, driver);
		this.gadgetId = gadgetId;
	}
	
	/**
	 * ガジェットメニューを開く
	 * @param widgetId
	 */
	public void openMenu() {
//		WebElement gadgetMenu = this.driver.findElement(By.id(widgetId+"_close_menu"));
//		
//		if(gadgetMenu.isDisplayed())
//			return;
		
		TestHelper.waitPresent(driver, By.id("hi_"+gadgetId+"_showTools"));
		this.driver.findElement(By.id("hi_"+gadgetId+"_showTools")).click();
		
		TestHelper.waitPresent(this.driver, By.id(gadgetId+"_close_menu"));
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
	
	/*
	public ToDoListGadget getToDoListGadget() {
		return todoListGadget;
	}
	
	public StickyGadget getStickyGadget() {
		return stickyGadget;
	}
	*/
}
