package org.infoscoop_selenium.portal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.commandbar.PortalPreference;
import org.infoscoop_selenium.portal.commandbar.SearchForm;
import org.infoscoop_selenium.portal.commandbar.TrashBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CommandBar {
	WebDriver driver;
	PortalPreference portalPreference;
	TrashBox trashBox;
	SearchForm searchForm;
	
	public static final String ITEM_LOGO_ID = "portal-logo";
	public static final String ITEM_TICKER_ID = "s_p_1_w_4";
	public static final String ITEM_SERARCHFORM_ID = "portal-searchform";
	public static final String ITEM_RANKING_ID = "command-ranking";
	public static final String ITEM_TRASH_ID = "trash-icon";
	public static final String ITEM_PREFERENCE_ID = "allPreference";
	public static final String ITEM_ADMINLINK_ID = "admin-link";
	
	private static final String ITEM_MENUFONTSIZESELECT_ID = "font-size-select";
	private static final String ITEM_MENUFONTSIZELARGE_ID = "option-large";
	private static final String ITEM_MENUFONTSIZEMIDDLE_ID = "option-normal";
	private static final String ITEM_MENUFONTSIZESMALL_ID = "option-small";
	
	public CommandBar(WebDriver driver) {
		this.driver = driver;
		this.portalPreference = new PortalPreference(this, driver);
		this.trashBox = new TrashBox(this, driver);
		this.searchForm = new SearchForm(this, driver);
	}
	
	/**
	 * ユーザーメニュー開く
	 */
	public void openMenu(){
		WebElement userMenu = this.driver.findElement(By.id("portal-user-menu-body"));
		
		if(userMenu.isDisplayed())
			return;
			
		TestHelper.waitPresent(this.driver, By.id("columns0"));
		this.driver.findElement(By.id("portal-user-menu")).click();
	}
	
	public PortalPreference getPortalPreference(){
		return portalPreference;
	}
	
	public TrashBox getTrashBox(){
		return trashBox;
	}

	public SearchForm getSearchForm(){
		return searchForm;
	}
	/**
	 * 表示中のコマンドバーアイテムのリストを返す
	 * @return
	 */
	public List<WebElement> getDisplayItems(){
		List<WebElement> items = new ArrayList<WebElement>();
		items.add(getLogo());
		items.addAll(getDisplayOutOｆMenuItems());
		items.addAll(getDisplayMenuItems());
		
		return items;
	}

	/**
	 * 表示中のコマンドバーアイテムのリストを返す(メニュー外)
	 * @return
	 */
	public List<WebElement> getDisplayOutOｆMenuItems(){
		List<WebElement> resultList = new ArrayList<WebElement>();
		List<WebElement> items = this.driver.findElements(By.cssSelector("#portal-command .commandbar-item"));
		
		for(Iterator<WebElement> ite=items.iterator();ite.hasNext();){
			WebElement item = ite.next();
			String id = item.getAttribute("id");
			if(!id.startsWith("disabled_")){
				resultList.add(item);
			}
		}
		
		return resultList;
	}

	/**
	 * 表示中のコマンドバーアイテムのリストを返す(メニュー)
	 * @return
	 */
	public List<WebElement> getDisplayMenuItems(){
		openMenu();
		List<WebElement> resultList = new ArrayList<WebElement>();
		List<WebElement> items = this.driver.findElements(By.cssSelector("#portal-user-menu-body .portal-user-menu-item-label"));
		
		for(Iterator<WebElement> ite=items.iterator();ite.hasNext();){
			WebElement item = ite.next();
			String id = item.getAttribute("id");
			if(!id.startsWith("disabled_")){
				resultList.add(item);
			}
		}
		
		return resultList;
	}
	
	/**
	 * ロゴ要素の取得
	 */
	public WebElement getLogo(){
		return this.driver.findElement(By.id("portal-logo"));
	}
	
	/**
	 * メニュー要素の取得
	 * @return
	 */
	public WebElement getMenu(){
		return this.driver.findElement(By.id("portal-user-menu"));
	}
	
	
	/**
	 * メニュー要素の取得
	 * @return
	 */
	public String getDisplayUserName(){
		WebElement userMenuLabel = this.driver.findElement(By.id("portal-user-menu-label"));
		return userMenuLabel.getText().trim();
	}

	/**
	 * メニュー内の文字サイズ変更を返す
	 */
	public WebElement getMenuChangeFontSizeElement() {
		return getElement(ITEM_MENUFONTSIZESELECT_ID);
	}
	
	/**
	 * コマンドバー内の指定要素を返す
	 */
	private WebElement getElement(String elementId) {
		return driver.findElement(By.id(elementId));
	}

	/**
	 * メニュー内の文字サイズ変更プルダウンを選択する
	 */
	public void selectMenuChangeFontSizeElement() {
		WebElement selectMenu = getElement(ITEM_MENUFONTSIZESELECT_ID);
		selectMenu.click();
	}

	/**
	 * メニュー内の文字サイズ変更の大を返す
	 */
	public WebElement getMenuFontSizeLargeElement() {
		return getElement(ITEM_MENUFONTSIZELARGE_ID);
	}

	/**
	 * メニュー内の文字サイズ変更の中を返す
	 */
	public WebElement getMenuFontSizeMiddleElement() {
		return getElement(ITEM_MENUFONTSIZEMIDDLE_ID);
	}

	/**
	 * メニュー内の文字サイズ変更の小を返す
	 */
	public WebElement getMenuFontSizeSmallElement() {
		return getElement(ITEM_MENUFONTSIZESMALL_ID);
	}

	
}
