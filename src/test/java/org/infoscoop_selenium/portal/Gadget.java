package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.gadget.AlarmGadget;
import org.infoscoop_selenium.portal.gadget.GadgetPreference;
import org.infoscoop_selenium.portal.gadget.GenericGadget;
import org.infoscoop_selenium.portal.gadget.RssReaderGadget;
import org.infoscoop_selenium.portal.gadget.StickyGadget;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class Gadget {
	protected WebDriver driver;
	GadgetPreference gadgetPreference;
	String gadgetId;
	
	public static enum GADGET_TYPE {
		STICKY(StickyGadget.class),
		TODOLIST(ToDoListGadget.class),
		RSSREADER(RssReaderGadget.class),
		ALARM(AlarmGadget.class),
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
		
		observeEvents();
		
		this.driver.findElement(By.id("hi_"+gadgetId+"_showTools")).click();
		System.out.println(this.driver.findElement(By.id("hi_"+gadgetId+"_showTools")));
		
		TestHelper.waitPresent(this.driver, By.id(gadgetId+"_close_menu"));
	}
	
	/**
	 * ガジェットを閉じる
	 * @return
	 */
	public void close(){
		openMenu();
		driver.findElement(By.xpath("//div[@id='hm_" + gadgetId + "_close']/a[1]")).click();
	}
	
	public GadgetPreference getGadgetPreference(){
		return gadgetPreference;
	}
	
	public String getId(){
		return gadgetId;
	}
	
	/**
	 * ヘッダにマウスオーバーしないと付加されないイベントがある
	 */
	public void observeEvents(){
		Actions action = new Actions(driver);
		WebElement headerDiv = driver.findElement(By.xpath("//div[@id='" + gadgetId + "']//div[@class='widgetHeader']/div[1]"));
		action.moveToElement(headerDiv);
		action.clickAndHold().release();
		action.build().perform();
	}
}
