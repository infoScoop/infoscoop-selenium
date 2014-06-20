package org.infoscoop_selenium.portal.gadget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
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
		
		WebElement targetElement = driver.findElement(By.id("eb_"+gadget.getId()+"_widget_title"));
		targetElement.clear();
		targetElement.sendKeys(title);
		
		ok();
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
	
	/**
	 * UserPrefのMapを返す
	 * @return
	 */
	public Map<String, WebElement> getDisplayUserPrefs(){
		show();
		WebElement editPanelEl = driver.findElement(By.id("frm_"+gadget.getId()));
		
		List<WebElement> list = editPanelEl.findElements(By.tagName("td"));
		
		Map<String, WebElement> prefsMap = new LinkedHashMap<String, WebElement>();
		String label = "";
		for(Iterator<WebElement> ite=list.iterator();ite.hasNext();){
			WebElement prefTd = ite.next();
			
			if(prefTd.getAttribute("class").equals("widget_edit_pref_col_label")){
				label = prefTd.getText();
			}
			else if(prefTd.getAttribute("class").equals("widget_edit_pref_col_value")){
				WebElement prefValueEl = prefTd.findElement(By.xpath("*"));
				prefsMap.put(label, prefValueEl);
			}
		}
		
		return prefsMap;
	}
}
