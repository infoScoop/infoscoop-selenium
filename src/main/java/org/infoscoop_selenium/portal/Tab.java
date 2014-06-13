package org.infoscoop_selenium.portal;

import static org.infoscoop_selenium.base.IS_BaseItTestCase.*;

import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
		driver.findElement(By.cssSelector("#addTab a")).click();
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
	 * @param draggedTabElementId
	 * @param droppedTabElementId
	 */
	public void dragAndDropTabToTopLeft(String draggedTabElementId, String droppedTabElementId) {
		dragAndDropTab(draggedTabElementId, droppedTabElementId, 0);
	}
	
	
	/**
	 * タブを要素（droppedTabElementId）の右端へドロップする
	 * @param draggedTabElementId
	 * @param droppedTabElementId
	 */
	public void dragAndDropTabToTopRight(String draggedTabElementId, String droppedTabElementId) {
		
		WebElement dropElement = driver.findElement(By.id(droppedTabElementId));
		String dropElementWidth = dropElement.getCssValue("width");
		dropElementWidth = dropElementWidth.substring(0, dropElementWidth.length()-2);
		dragAndDropTab(draggedTabElementId, droppedTabElementId, Integer.parseInt(dropElementWidth));
	}

	/**
	 * タブを要素のtop leftからx方向へoffsetx分離れたところにドロップする
	 * @param draggedTabElementId
	 * @param droppedTabElementId
	 * @param offsetx
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
	
	/** 右隣のタブIDを返す
	 * @param tabElementId
	 */
	public String getNextTabId(String tabElementId) {
		WebElement nextTab = driver.findElement(By.cssSelector("#" + tabElementId + " + li"));
		return nextTab.getAttribute("id");
	}
}
