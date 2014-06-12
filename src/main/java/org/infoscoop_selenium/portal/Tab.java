package org.infoscoop_selenium.portal;

import java.util.List;

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

	public int getNumberOfTab() {
		List<WebElement> tabsLis = driver.findElements(By.cssSelector("#tabsUl li"));
		
		
		return tabsLis.size();
	}

	public String getFarRightTabId() {
		WebElement farRightTab = driver.findElement(By.cssSelector("#tabsUl li:last-of-type"));
		return farRightTab.getAttribute("id");
	}

	public String getTabLiDisplayProperty() {
		WebElement tabLi = driver.findElement(By.cssSelector("#tabsUl li:first-of-type"));
		return tabLi.getCssValue("display");
	}
	
	public WebElement getSelectMenu(String tabId) {
		WebElement selectMenu = driver.findElement(By.id(tabId + "_selectMenu"));
		return selectMenu;
	}
	
	public void selectSelectMenu(String tabId) {
		WebElement selectMenu = getSelectMenu(tabId);
		selectMenu.click();
	}
	
	public WebElement getTabMenu(String tabId) {
		WebElement tabMenu = driver.findElement(By.id(tabId + "_menu"));
		return tabMenu;
	}
	
	public WebElement getRefreshItem(String tabId) {
		WebElement refreshItem = driver.findElement(By.id(tabId + "_menu_refresh"));
		return refreshItem;
	}
	
	public WebElement getCloseItem(String tabId) {
		WebElement closeItem = driver.findElement(By.id(tabId + "_menu_close"));
		return closeItem;
	}
	
	public WebElement getNameInput(String tabId) {
		WebElement nameInput = driver.findElement(By.id(tabId + "_menu_rename_input"));
		return nameInput;
	}
	
	public WebElement getColumnNumSelect(String tabId) {
		WebElement columnNumSelect = driver.findElement(By.id(tabId + "_menu_changeColumnNum_select"));
		return columnNumSelect;
	}
	
	public WebElement getResetColumnWidthItem(String tabId) {
		WebElement resetColumnWidthItem = driver.findElement(By.id(tabId + "_menu_resetColumnWidth"));
		return resetColumnWidthItem;
	}
	
	public WebElement getInitializeItem(String tabId) {
		WebElement initializeItem = driver.findElement(By.id(tabId + "_menu_initialize"));
		return initializeItem;
	}
}
