package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Tab {
	WebDriver driver;
	
	public Tab(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * タブ追加
	 */
	public void addTab(){
		// タブ追加の出現を待つ
		TestHelper.waitPresent(driver, By.id("addTab"));
		
		// タブ追加
		driver.findElement(By.id("addTab")).click();
	}
	
	public void selectTab(String tabId){
		driver.findElement(By.id(tabId)).click();
	}
}
