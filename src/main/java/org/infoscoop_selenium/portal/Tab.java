package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tab {
	WebDriver driver;
	
	public Tab(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * タブ追加
	 */
	public String addTab(){
		// タブ追加の出現を待つ
		TestHelper.waitPresent(driver, By.id("addTab"));
		
		// タブ追加
		driver.findElement(By.id("addTab")).click();
		
		return getCurrentTabId();
	}
	
	public void selectTab(String tabId){
		driver.findElement(By.id(tabId)).click();
	}
	
	public String getCurrentTabId(){
		WebElement currentTabEl = driver.findElement(By.cssSelector(".tab.selected"));
		return currentTabEl.getAttribute("id");
	}
	
	/**
	 * タブロード時のインディケータを返す
	 * @return
	 */
	public WebElement getIndicator(){
		WebElement indicator = driver.findElement(By.cssSelector("#divOverlay.tabLoading"));
		return indicator;
	}
}
