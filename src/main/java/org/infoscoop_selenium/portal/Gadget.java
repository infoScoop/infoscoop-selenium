package org.infoscoop_selenium.portal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.gadget.AlarmGadget;
import org.infoscoop_selenium.portal.gadget.CalculatorGadget;
import org.infoscoop_selenium.portal.gadget.ClockGadget;
import org.infoscoop_selenium.portal.gadget.GadgetPreference;
import org.infoscoop_selenium.portal.gadget.GenericGadget;
import org.infoscoop_selenium.portal.gadget.MessageGadget;
import org.infoscoop_selenium.portal.gadget.RankingGadget;
import org.infoscoop_selenium.portal.gadget.RssReaderGadget;
import org.infoscoop_selenium.portal.gadget.ScheduleGadget;
import org.infoscoop_selenium.portal.gadget.StickyGadget;
import org.infoscoop_selenium.portal.gadget.ToDoListGadget;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class Gadget {
	protected WebDriver driver;
	GadgetPreference gadgetPreference;
	String gadgetId;
	
	public static final String ICON_TYPE_MINIMIZE = "minimize";
	public static final String ICON_TYPE_SHOWTOOLS = "showTools";

	public static final String MENU_TYPE_EDIT = "edit";
	public static final String MENU_TYPE_DELETE = "close";
	
	public static enum GADGET_TYPE {
		STICKY(StickyGadget.class),
		TODOLIST(ToDoListGadget.class),
		RSSREADER(RssReaderGadget.class),
		ALARM(AlarmGadget.class),
		MESSAGE(MessageGadget.class),
		SCHEDULE(ScheduleGadget.class),
		CLOCK(ClockGadget.class),
		CALCULATOR(CalculatorGadget.class),
        RANKING(RankingGadget.class),
		GENERIC(GenericGadget.class);
		
		private final Class gadgetClass;
		private GADGET_TYPE(Class gadgetClass){
			this.gadgetClass = gadgetClass;
		}
		public Class getValue(){
			return gadgetClass;
		}
	}
	
	public Gadget(WebDriver driver, String gadgetId) {
		this.driver = driver;
		this.gadgetPreference = new GadgetPreference(this, driver);
		this.gadgetId = gadgetId;
	}
	
	/**
	 * ガジェットメニューを開く
	 * @param widgetId
	 */
	public void openMenu() {
//		WebElement gadgetMenu = this.driver.findElement(By.id(widgetId+"_close_menu"));
//		
//		if(gadgetMenu.isDisplayed())
//			return;
		
		TestHelper.waitPresent(driver, By.id("hi_"+gadgetId+"_showTools"));
		
		observeEvents();
		
		this.driver.findElement(By.id("hi_"+gadgetId+"_showTools")).click();
		System.out.println(this.driver.findElement(By.id("hi_"+gadgetId+"_showTools")));
		
		TestHelper.waitPresent(this.driver, By.id(gadgetId+"_close_menu"));
	}
	
	/**
	 * ガジェットを閉じる
	 * @return
	 */
	public void close(){
		openMenu();
		driver.findElement(By.xpath("//div[@id='hm_" + gadgetId + "_close']/a[1]")).click();
	}
	
	/**
	 * ガジェットエレメントを返す
	 */
	public WebElement getGadgetElement(){
		return driver.findElement(By.id(gadgetId));
	}
	
	/**
	 * [削除]エレメントを返す
	 */
	public WebElement getCloseElement(){
		return driver.findElement(By.id("hm_" + gadgetId + "_close"));
	}
	
	/**
	 * [表示件数を減らす]エレメントを返す
	 */
	public WebElement getWidgetRssUpElement(){
		return driver.findElement(By.id("hm_" + gadgetId + "_widgetRssUp"));
	}
	
	/**
	 * [表示件数を増やす]エレメントを返す
	 */
	public WebElement getWidgetRssDownElement(){
		return driver.findElement(By.id("hm_" + gadgetId + "_widgetRssDown"));
	}
	
	/**
	 * ガジェットの最大化
	 * @return
	 */
	public void maximaize(){
		TestHelper.waitPresent(driver, By.id("hi_"+gadgetId+"_maximize"));
		this.driver.findElement(By.id("hi_"+gadgetId+"_maximize")).click();
		
		TestHelper.waitPresent(this.driver, By.className("headerIcon_turnbackMaximize"));
	}
	
	/**
	 * ガジェットのカラム移動
	 * FFで移動しない。理由は不明（Windowsで要検証）
	 */
	public void moveColumn(int columnNum, boolean dropFlg){
		WebElement dropElement = driver.findElement(By.xpath("//div[@class='column' and @colnum='" + columnNum + "']"));
		Point dropPoint = dropElement.getLocation();
		
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='" + gadgetId + "']//div[@class='widgetHeader']/div[1]"));

		Actions actions = new Actions(driver);
		
		// IE, FFで動作するコード
		actions.moveToElement(targetElement);
		actions.clickAndHold();
		actions.moveByOffset(dropPoint.x-50, 20);
		if(dropFlg)
			actions.release();
		actions.build().perform();
	}
	
	/**
	 * ガジェットのタブ移動
	 */
	public void moveTab(String tabId) {
		WebElement dropElement = driver.findElement(By.id(tabId));
		
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='" + gadgetId + "']//div[@class='widgetHeader']/div[1]"));

		Actions actions = new Actions(driver);
		
		actions.moveToElement(targetElement);
		actions.clickAndHold();
		actions.moveToElement(dropElement);
		actions.release();
		actions.build().perform();
		
		TestHelper.sleep(1000);
	}
	
	public GadgetPreference getGadgetPreference(){
		return gadgetPreference;
	}
	
	public String getId(){
		return gadgetId;
	}
	
	/**
	 * ガジェットタイトルを変更する
	 * @param title
	 */
	public void changeTitle(String title) {
	    openMenu();
	    
	    WebElement editMenu = driver.findElement(By.id("hm_" + getId() + "_edit"));
	    editMenu.click();
	    
	    // ガジェット設定パネルの出現を待つ
	    TestHelper.waitPresent(driver, By.cssSelector("#" + getId() + " .widgetEditHeader"));

	    // ガジェットタイトルのテキストボックスに入力
	    driver.findElement(By.id("eb_" + getId() + "_widget_title")).sendKeys(Keys.chord(Keys.CONTROL, "a"), title);

	    // click "save"
	    driver.findElement(By.cssSelector("#" + getId() + " .widgetEditHeader .widgetSave")).click();
	}
	
	/**
	 * ヘッダにマウスオーバーしないと付加されないイベントがある
	 */
	public void observeEvents(){
		Actions action = new Actions(driver);
		WebElement headerDiv = driver.findElement(By.xpath("//div[@id='" + gadgetId + "']//div[@class='widgetHeader']/div[1]"));
		action.moveToElement(headerDiv);
		action.build().perform();
	}


	/**
	 * フレームにフォーカスする<br/>
	 * フレーム内のドキュメントにアクセスできるようになる。
	 */
	public void focus(){
		TestHelper.switchToFrame(driver, "ifrm_"+this.getId());
	}
	
	/**
	 * 親ドキュメントに戻る
	 */
	public void blur(){
		TestHelper.backToTopFrame(driver);
	}
	
	/**
	 * ガジェット表示フレームを返す
	 */
	public WebElement getFrameElement(){
		return driver.findElement(By.id("ifrm_"+this.getId()));
	}
	
	/**
	 * ガジェット内の高さを返す
	 */
	public int getContentHeight(){
		focus();
		int height = driver.findElement(By.tagName("body")).getSize().height;
		blur();
		return height;
	}
	
	/**
	 * ガジェット内の幅を返す
	 */
    public int getContentWidth() {
        focus();
        int width = driver.findElement(By.tagName("body")).getSize().width;
        blur();
        return width;
    }
	
    /**
     * ガジェットのアイコンである要素を返却する
     * @return
     */
    public WebElement getGadgetIconElement() {
        WebElement icon = driver.findElement(By.cssSelector(
                "#" + this.getId() + " .widgetHeader table td:nth-of-type(2) img"));
        return icon;
    }
    
	/**
	 * ガジェットヘッダの表示アイコンをリストで返す
	 * @return
	 */
	public List<String> getHeaderIconTypes(){
		List<String> list = new ArrayList<String>();
		
		List<WebElement> icons = driver.findElements(By.cssSelector("#" + this.getId() + " .widgetHeader .headerIcon"));
		
		for(Iterator<WebElement> ite=icons.iterator();ite.hasNext();){
			WebElement icon = ite.next();
			
			if(icon.isDisplayed()){
				list.add(getItemType(icon));
			}
		}
		
		return list;
	}
	
	/**
	 * ガジェットメニューの表示アイテムをリストで返す
	 * @return
	 */
	public List<String> getMenuItemTypes(){
		openMenu();
		List<String> list = new ArrayList<String>();
		
		List<WebElement> icons = driver.findElements(By.cssSelector("#" + this.getId() + "_close_menu .headerIcon"));
		
		for(Iterator<WebElement> ite=icons.iterator();ite.hasNext();){
			WebElement icon = ite.next();
			
			if(icon.isDisplayed()){
				list.add(getItemType(icon));
			}
		}
		
		return list;
	}
	
	/**
	 * ガジェットのタイトルを返す
	 * @return
	 */
	public String getTitle(){
		WebElement titleEl = getTitleElement();
		
		WebElement titleLinkEl = null;
		try{
			titleLinkEl = titleEl.findElement(By.tagName("a"));
		}catch(NoSuchElementException e){
			// ignore
		}
		
		String title = titleEl.getText();
		if(titleLinkEl != null){
			// タイトルリンクが存在する場合はAタグから取得
			title = titleLinkEl.getText();
		}
		
		return title;
	}
	
	/**
	 * ガジェットのタイトル要素を返却する
	 * @return
	 */
	public WebElement getTitleElement() {
	    WebElement titleEl = driver.findElement(By.id(this.gadgetId + "_widgetTitle"));
	    return titleEl;
	}

	/**
	 * ガジェットを指定したカラム位置にドラッグ＆ドロップする。
	 * @param handle
	 * @param columnX
	 * @param columnY
	 */
	public void dragAndDrop(WebElement handle, int columnX, int columnY) {
	    Panel panel = new Panel(driver, new Tab(driver).getCurrentTabId());
	    WebElement toElement = panel.getGadgetSpaceElement(columnX, columnY);
	    
	    Actions actions = new Actions(driver);
	    actions.clickAndHold(handle);
	    actions.moveToElement(toElement);
	    actions.release();
	    actions.build().perform();
	}

	/**
	 * サポートされるヘッダアイコンタイプのリストを返す
	 * @return
	 */
	abstract public List<String> getSupportedHeaderIcons();
	
	/**
	 * サポートされるヘッダアイコンタイプのリストを返す
	 * @return
	 */
	abstract public List<String> getSupportedMenuItems();
	
	/**
	 * ヘッダアイコン、メニューアイテムのタイプを返す
	 * @param icon
	 * @return
	 */
	private String getItemType(WebElement icon){
		String iconId = icon.getAttribute("id");
		return iconId.substring(iconId.lastIndexOf("_") + 1, iconId.length());
	}
}
