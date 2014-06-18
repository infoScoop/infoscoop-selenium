package org.infoscoop_selenium.portal;

import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Tab {
	WebDriver driver;
		
	public Tab(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * タブを追加する
	 */
	public String addTab(){
		// タブ追加の出現を待つ
		TestHelper.waitPresent(driver, By.id("addTab"));
		// タブ追加
		driver.findElement(By.cssSelector("#addTab a")).click();
		return getCurrentTabId();
	}
	
	/**
	 * タブを選択する
	 */
	public void selectTab(String tabId){
		driver.findElement(By.id(tabId)).click();
	}
	
	/**
	 * 選択されているタブidを返す
	 */
	public String getCurrentTabId(){
		WebElement currentTabEl = driver.findElement(By.cssSelector(".tab.selected"));
		return currentTabEl.getAttribute("id");
	}
	
	/**
	 * タブロード時のインディケータを返す
	 */
	public WebElement getIndicator(){
		WebElement indicator = driver.findElement(By.cssSelector("#divOverlay.tabLoading"));
		return indicator;
	}

	/**
	 * 現在のタブ数を返す
	 */
	public int getNumberOfTab() {
		List<WebElement> tabsLis = driver.findElements(By.cssSelector("#tabsUl li"));
		return tabsLis.size();
	}

	/**
	 * 右端のタブIDを返す
	 */
	public String getFarRightTabId() {
		WebElement farRightTab = driver.findElement(By.cssSelector("#tabsUl li:last-of-type"));
		return farRightTab.getAttribute("id");
	}

	/**
	 * タブのli要素のdisplayプロパティの値を返す
	 */
	public String getTabLiDisplayProperty() {
		WebElement tabLi = driver.findElement(By.cssSelector("#tabsUl li:first-of-type"));
		return tabLi.getCssValue("display");
	}

	/**
	 * タブを要素（droppedTabElementId）の左端へドロップする
	 */
	public void dragAndDropTabToTopLeft(String draggedTabElementId, String droppedTabElementId) {
		dragAndDropTab(draggedTabElementId, droppedTabElementId, 0);
	}
	
	/**
	 * タブを要素（droppedTabElementId）の右端へドロップする
	 */
	public void dragAndDropTabToTopRight(String draggedTabElementId, String droppedTabElementId) {
		WebElement dropElement = driver.findElement(By.id(droppedTabElementId));
		String dropElementWidth = dropElement.getCssValue("width");
		dropElementWidth = dropElementWidth.substring(0, dropElementWidth.length()-2);
		dragAndDropTab(draggedTabElementId, droppedTabElementId, Integer.parseInt(dropElementWidth));
	}

	/**
	 * タブを要素のtop leftからx方向へoffsetx分離れたところにドロップする
	 */
	private void dragAndDropTab(String draggedTabElementId, String droppedTabElementId, int offsetx) {
		WebElement dropElement = driver.findElement(By.id(droppedTabElementId));
		WebElement targetElement = driver.findElement(By.id(draggedTabElementId));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(targetElement);
		actions.clickAndHold();
		actions.moveToElement(dropElement, offsetx, 0);
		actions.release();
		actions.build().perform();
	}
	
	/**
	 * 右隣のタブIDを返す
	 */
	public String getNextTabId(String tabElementId) {
		WebElement nextTab = driver.findElement(By.cssSelector("#" + tabElementId + " + li"));
		return nextTab.getAttribute("id");
	}
	
	/**
	 * タブメニューボタンを返す
	 */
	public WebElement getSelectMenu(String tabId) {
		WebElement selectMenu = driver.findElement(By.id(tabId + "_selectMenu"));
		return selectMenu;
	}
	
	/**
	 * タブメニューボタンをクリックする
	 */
	public void selectSelectMenu(String tabId) {
		WebElement selectMenu = getSelectMenu(tabId);
		selectMenu.click();
	}
	
	/**
	 * タブメニューを返す
	 */
	public WebElement getTabMenu(String tabId) {
		WebElement tabMenu = driver.findElement(By.id(tabId + "_menu"));
		return tabMenu;
	}
	
	/**
	 * タブメニューの[再読み込み]を返す
	 */
	public WebElement getRefreshItem(String tabId) {
		WebElement refreshItem = driver.findElement(By.id(tabId + "_menu_refresh"));
		return refreshItem;
	}
	
	/**
	 * タブメニューの[削除]を返す
	 */
	public WebElement getCloseItem(String tabId) {
		WebElement closeItem = driver.findElement(By.id(tabId + "_menu_close"));
		return closeItem;
	}
	
	/**
	 * タブメニューの[名称変更]を返す
	 */
	public WebElement getNameInput(String tabId) {
		WebElement nameInput = driver.findElement(By.id(tabId + "_menu_rename_input"));
		return nameInput;
	}
	
	/**
	 * タブメニューの[列数変更]を返す
	 */
	public WebElement getColumnNumSelect(String tabId) {
		WebElement columnNumSelect = driver.findElement(By.id(tabId + "_menu_changeColumnNum_select"));
		return columnNumSelect;
	}
	
	/**
	 * タブメニューの[列の幅を揃える]を返す
	 */
	public WebElement getResetColumnWidthItem(String tabId) {
		WebElement resetColumnWidthItem = driver.findElement(By.id(tabId + "_menu_resetColumnWidth"));
		return resetColumnWidthItem;
	}
	
	/**
	 * タブメニューの[タブの構成を初期化する]を返す
	 */
	public WebElement getInitializeItem(String tabId) {
		WebElement initializeItem = driver.findElement(By.id(tabId + "_menu_initialize"));
		return initializeItem;
	}
}
