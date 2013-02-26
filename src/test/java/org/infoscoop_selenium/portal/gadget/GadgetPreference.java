package org.infoscoop_selenium.portal.gadget;

import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GadgetPreference {
	WebDriver driver;
	Gadget gadget;
	
	public GadgetPreference(Gadget gadget, WebDriver driver) {
		this.gadget = gadget;
		this.driver = driver;
	}
	
	/**
	 * ガジェット設定表示
	 * @param widgetId
	 */
	public void show(String widgetId){
		gadget.openMenu(widgetId);
		
		WebElement elem = driver.findElement(By.xpath("//div[@id='hm_"+widgetId+"_edit']/a"));
		if(elem.isDisplayed())
			elem.click();
	}
	
	/**
	 * ガジェット設定OK
	 * @param widgetId
	 */
	public void ok(String widgetId){
		if(!driver.findElement(By.id("frm_"+widgetId)).isDisplayed())
			return;
		
		driver.findElement(By.xpath("//form[@id='frm_"+widgetId+"']/div[@class='widgetSave']")).click();
	}
	
	/**
	 * ガジェット設定キャンセル
	 * @param widgetId
	 */
	public void cancel(String widgetId){
		if(!driver.findElement(By.id("frm_"+widgetId)).isDisplayed())
			return;
		
		driver.findElement(By.xpath("//form[@id='frm_"+widgetId+"']/div[@class='widgetCancel']")).click();
	}
}
