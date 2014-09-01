package org.infoscoop_selenium.portal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Tab {
	WebDriver driver;
		
	public Tab(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * [タブを追加する]ボタンを返す
	 */
	//XXX iscp_5720で要素が非表示であることを確認する際に不要な例外を発生させてしまっている
	public WebElement getAddTabButtonElement(){
		try {
			// タブ追加の出現を待つ
			TestHelper.waitPresent(driver, By.id("addTab"));
		} catch (Exception e) {
			// Do nothing 
		}
		return driver.findElement(By.cssSelector("#addTab a"));
	}
	
	/**
	 * タブを追加する
	 */
	public String addTab(){
		getAddTabButtonElement().click();
		return getCurrentTabId();
	}
	
	/**
	 * タブを選択する
	 */
	public void selectTab(String tabId){
		getTabElement(tabId).click();
	}
	
	/**
	 * タブを返す
	 */
	public WebElement getTabElement(String tabId) {
		return driver.findElement(By.id(tabId));
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
	public WebElement getIndicatorElement(){
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
	 * タブIDのリストを返す（左から右の順番で）
	 */
	public List<String> getTabIdList() {
		int numberOfTab = getNumberOfTab();
		List<String> tabs = new ArrayList<String>();
		String tabId;
		
		WebElement tabsUl = driver.findElement(By.cssSelector("#tabsUl"));
		for (int i = 0; i < numberOfTab; i++) {
			int n = i+1;
			tabId = tabsUl.findElement(By.cssSelector("li:nth-of-type("+ n +")"))
				.getAttribute("id");
			tabs.add(tabId);
		}
		return tabs;
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
	 * タブの移動先はおそらく中心からのポインタの位置で判定しているため、コーナーへのドロップは意図した場所へドロップできない場合がある。この実装方法はやめる
	 * かわりに publicのdragAndDropTab を利用する
	 */
	@Deprecated
	public void dragAndDropTabToTopLeft(String draggedTabElementId, String droppedTabElementId) {
		dragAndDropTab(draggedTabElementId, droppedTabElementId, 0);
	}
	
	/**
	 * タブを要素（droppedTabElementId）の右端へドロップする
	 * タブの移動先はおそらく中心からのポインタの位置で判定しているため、コーナーへのドロップは意図した場所へドロップできない場合がある。この実装方法はやめる
	 * かわりに publicのdragAndDropTab を利用する
	 */
	@Deprecated
	public void dragAndDropTabToTopRight(String draggedTabElementId, String droppedTabElementId) {
		WebElement dropElement = driver.findElement(By.id(droppedTabElementId));
		String dropElementWidth = dropElement.getCssValue("width");
		dropElementWidth = dropElementWidth.substring(0, dropElementWidth.length()-2);
		dragAndDropTab(draggedTabElementId, droppedTabElementId, Integer.parseInt(dropElementWidth));
	}

	/**
	 * タブを要素のtop leftからx方向へoffsetx分離れたところにドロップする
	 * タブの移動先はおそらく中心からのポインタの位置で判定しているため、コーナーへのドロップは意図した場所へドロップできない場合がある。この実装方法はやめる
	 * かわりに publicのdragAndDropTab を利用する
	 */
	@Deprecated
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
	 * タブを要素の右側へドロップする（要素のtop-leftからx方向へwidth分, y方向へ1/3離れたところにドロップする）
	 * @param fromTabId 移動するタブ
	 * @param toTabId ドロップ先のタブ
	 */
	public void dragAndDropTab (String fromTabId, String toTabId) {
		WebElement fromTab = driver.findElement(By.id(fromTabId));
		WebElement toTab = driver.findElement(By.id(toTabId));
		
		String toTabWidth = toTab.getCssValue("width").replace("px", "");// remove 'px'
		BigDecimal toTabWidthBD = new BigDecimal(toTabWidth).setScale(0, BigDecimal.ROUND_CEILING);
		
		String toTabHeight = toTab.getCssValue("height").replace("px", ""); // remove 'px';
		BigDecimal toTabHeightBD = new BigDecimal(toTabHeight).setScale(0, BigDecimal.ROUND_CEILING);
		
		int offsetx = toTabWidthBD.intValue() / 4 * 3;
		int offsety = toTabHeightBD.intValue() / 4;
		
		Actions actions = new Actions(driver);
		actions.moveToElement(fromTab);
		actions.clickAndHold();
		actions.moveToElement(toTab, offsetx, offsety);
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
	public WebElement getSelectMenuElement(String tabId) {
		WebElement selectMenu = driver.findElement(By.id(tabId + "_selectMenu"));
		return selectMenu;
	}
	
	/**
	 * タブメニューボタンをクリックする
	 */
	public void selectSelectMenu(String tabId) {
		WebElement selectMenu = getSelectMenuElement(tabId);
		selectMenu.click();
	}
	
	/**
	 * タブメニューを返す
	 */
	public WebElement getTabMenuElement(String tabId) {
		WebElement tabMenu = driver.findElement(By.id(tabId + "_menu"));
		return tabMenu;
	}
	
	/**
	 * タブメニューの[再読み込み]を返す
	 */
	public WebElement getRefreshItemElement(String tabId) {
		WebElement refreshItem = driver.findElement(By.id(tabId + "_menu_refresh"));
		return refreshItem;
	}
	
	/**
	 * タブメニューの[削除]を返す
	 */
	public WebElement getCloseItemElement(String tabId) {
		WebElement closeItem = driver.findElement(By.id(tabId + "_menu_close"));
		return closeItem;
	}
	
	/**
	 * タブメニューの[名称変更]を返す
	 */
	public WebElement getNameInputElement(String tabId) {
		WebElement nameInput = driver.findElement(By.id(tabId + "_menu_rename_input"));
		return nameInput;
	}
	
	/**
	 * タブメニューの[列数変更]を返す
	 */
	public WebElement getColumnNumSelectElement(String tabId) {
		WebElement columnNumSelect = driver.findElement(By.id(tabId + "_menu_changeColumnNum_select"));
		return columnNumSelect;
	}
	
	/**
	 * タブメニューの[列の幅を揃える]を返す
	 */
	public WebElement getResetColumnWidthItemElement(String tabId) {
		WebElement resetColumnWidthItem = driver.findElement(By.id(tabId + "_menu_resetColumnWidth"));
		return resetColumnWidthItem;
	}
	
	/**
	 * タブメニューの[タブの構成を初期化する]を返す
	 */
	public WebElement getInitializeItemElement(String tabId) {
		WebElement initializeItem = driver.findElement(By.id(tabId + "_menu_initialize"));
		return initializeItem;
	}
	
	/**
	 * タブメニューの[名称変更]の値を返す
	 */
	public String getNameInput(String tabId) {
		WebElement nameInput = getNameInputElement(tabId);
		return nameInput.getAttribute("value");
	}
	
	/**
	 * タブメニューの[名称変更]のフィールドをクリック
	 */
	public WebElement clickNameInput(String tabId) {
		WebElement nameInput = getNameInputElement(tabId);
		nameInput.click();
		return nameInput;
	}

	/**
	 * 現在フォーカスされている要素を返す
	 */
	public WebElement getCurrentFocusedElement() {
		return driver.switchTo().activeElement();
	}

	/**
	 * タブ名を変更する
	 */
	public void inputTabName(String tabId, String tabName) {
		WebElement nameInput = getNameInputElement(tabId);
		nameInput.sendKeys(tabName);
	}

	/**
	 * タブ名を返す
	 */
	public String getTabName(String tabId) {
		WebElement tabName = driver.findElement(By.id(tabId + "_title"));
		return tabName.getText();
	}
	
	/**
	 * タブを削除する
	 */
	public void deleteTab(String tabId) {
		selectSelectMenu(tabId);
		WebElement closeItem = getCloseItemElement(tabId);
		closeItem.findElement(By.cssSelector(".close")).click();
		driver.switchTo().alert().accept();
	}

	/**
	 * パネルの列数を変更する
	 * @param tabId
	 * @param num
	 */
	public void changeColumnNum(String tabId, int num) {
	    selectSelectMenu(tabId);
	    Select selectBox = new Select(getColumnNumSelectElement(tabId));
	    selectBox.selectByValue(Integer.toString(num));
	}

}
