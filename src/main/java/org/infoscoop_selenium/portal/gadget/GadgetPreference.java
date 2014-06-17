package org.infoscoop_selenium.portal.gadget;

import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * ガジェット設定のページクラス
 * @author b1738
 *
 */
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
	public void show(){
		gadget.openMenu();
		
		WebElement elem = driver.findElement(By.xpath("//div[@id='hm_"+gadget.getId()+"_edit']/a"));
		if(elem.isDisplayed())
			elem.click();
	}
	
	/**
	 * ガジェットのタイトル変更
	 * @param title
	 */
	public void changeTitle(String title) {
		show();

		if(!driver.findElement(By.id("frm_"+gadget.getId())).isDisplayed())
			return;
		
		driver.findElement(By.xpath("//td[@id='eb_"+gadget.getId()+"_widget_title']/input")).sendKeys(title);
	}
	
	/**
	 * ガジェット設定OK
	 */
	public void ok(){
		if(!driver.findElement(By.id("frm_"+gadget.getId())).isDisplayed())
			return;
		
		driver.findElement(By.xpath("//form[@id='frm_"+gadget.getId()+"']/div[@class='widgetSave']")).click();
	}
	
	/**
	 * ガジェット設定キャンセル
	 * @param widgetId
	 */
	public void cancel(){
		if(!driver.findElement(By.id("frm_"+gadget.getId())).isDisplayed())
			return;
		
		driver.findElement(By.xpath("//form[@id='frm_"+gadget.getId()+"']/div[@class='widgetCancel']")).click();
	}
}
