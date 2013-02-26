package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.gadget.GadgetPreference;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Gadget {
	WebDriver driver;
	GadgetPreference gadgetPreference;
	ToDoListGadget todoListGadget;
	
	public Gadget(WebDriver driver) {
		this.driver = driver;
		this.gadgetPreference = new GadgetPreference(this, driver);
		this.todoListGadget = new ToDoListGadget(this, driver);
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
	
	public GadgetPreference getGadgetPreference(){
		return gadgetPreference;
	}
	
	public ToDoListGadget getToDoListGadget() {
		return todoListGadget;
	}
}
