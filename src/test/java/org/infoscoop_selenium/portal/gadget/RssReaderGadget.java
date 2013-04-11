package org.infoscoop_selenium.portal.gadget;

import org.infoscoop_selenium.WindowManager;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class RssReaderGadget extends Gadget{
	private WebDriver helpWindowDriver;
	
	public RssReaderGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
	}

	public static enum DETAIL {
		INLINE("inline"),
		POPUP("popup");

		private final String detail;
		private DETAIL(String detail){
			this.detail = detail;
		}
		public String getValue(){
			return detail;
		}
	}
	
	/**
	 * スクロール
	 */
	public void scroll() {
		String xpath = "//div[@id=\""+super.getId()+"\"]/div/div/div[@class=\"widgetContent RssReader\"]/div[1]";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(
				"var result = document.evaluate('"+xpath+"', document.body, null, 7, null);"
				+"var height = result.snapshotItem(0).scrollHeight;"
				+"result.snapshotItem(0).scrollTop = height;"
		);
	}
	
	/**
	 * スクロール
	 * 最大化時
	 */
	public void scrollForMaximize() {
		String xpath = "//div[@id=\"MaximizeItemList___Maximize__"+super.getId()+"\"]/div[2]";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(
				"var result = document.evaluate('"+xpath+"', document.body, null, 7, null);"
				+"var height = result.snapshotItem(0).scrollHeight;"
				+"result.snapshotItem(0).scrollTop = height;"
		);
	}

	/**
	 * 詳細を開く
	 * @param order(>=0)
	 */
	public void showDetail(int order) {
		driver.findElement(By.id(super.getId()+"_item_"+order+"_more")).click();
	}
	
	/**
	 * 詳細表示モードの設定
	 * @param detail
	 */
	public void selectDetailDisplay(DETAIL detail) {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		Select select = new Select(driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_detailDisplayMode']/select")));
		select.selectByValue(detail.getValue());
	}
	
	/**
	 * 改行の設定
	 * @param brank
	 */
	public void changeLineFeed(boolean brank) {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		WebElement element = driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_doLineFeed']/input"));
		if(element.isSelected()){
			if(brank==false)
				element.click();
		}else{
			if(brank==true)
				element.click();
		}
	}
	
	/**
	 * 日付表示の設定
	 * @param datetime
	 */
	public void changeDatetime(boolean datetime) {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		WebElement element = driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_showDatetime']/input"));
		if(element.isSelected()){
			if(datetime==false)
				element.click();
		}else{
			if(datetime==true)
				element.click();
		}
	}
	
	/**
	 * タイトルフィルターヘルプ
	 */
	public void mouseOverTitleFilterHelp() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		WebElement hoverTarget = driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_titleFilter']/a"));
		Actions action = new Actions(driver);
		action.moveToElement(hoverTarget).clickAndHold().build().perform();
	}
	
	/**
	 * 作者フィルターヘルプ
	 */
	public void mouseOverCreatorFilterHelp() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		WebElement hoverTarget = driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_creatorFilter']/a"));
		Actions action = new Actions(driver);
		action.moveToElement(hoverTarget).clickAndHold().build().perform();
	}
	
	/**
	 * カテゴリフィルターヘルプ
	 */
	public void mouseOverCategoryFilterHelp() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		WebElement hoverTarget = driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_categoryFilter']/a"));
		Actions action = new Actions(driver);
		action.moveToElement(hoverTarget).clickAndHold().build().perform();
	}
	
	/**
	 * 時間順表示設定
	 * マルチRSS限定
	 */
	public void sortDate(){
		// ガジェットメニューを開く
		openMenu();
		
		if(!driver.findElement(By.id("hm_"+super.getId()+"_sort")).isDisplayed())
			return;
		
		// 時間順表示をクリックhm_tab0_p_sports_sort
		driver.findElement(By.xpath("//div[@id='hm_"+super.getId()+"_sort']/a")).click();
		
		// 表示を待つ
		TestHelper.waitInvisible(driver, By.id(super.getId()+"_widgetIndicator"));
	}
	
	/**
	 * カテゴリ別表示設定
	 * マルチRSS限定
	 */
	public void sortCategory(){
		// ガジェットメニューを開く
		openMenu();
		
		if(!driver.findElement(By.id("hm_"+super.getId()+"_category")).isDisplayed())
			return;
		
		// 時間順表示をクリックhm_tab0_p_sports_sort
		driver.findElement(By.xpath("//div[@id='hm_"+super.getId()+"_category']/a")).click();
		
		// 表示を待つ
		TestHelper.waitInvisible(driver, By.id(super.getId()+"_widgetIndicator"));
	}
	
	/**
	 * 最大化時
	 * HTML表示クリック
	 */
	public void clickHtmlDisp() {
		driver.findElement(By.id("hi___Maximize__"+super.getId()+"_iframeview_on")).click();
		TestHelper.switchToFrame(driver, "maximize_ifrm");
		TestHelper.backToTopFrame(driver);
	}
	
	/**
	 * 最大化時
	 * 日付表示クリック
	 */
	public void clickDate() {
		driver.findElement(By.id("hi___Maximize__"+super.getId()+"_date")).click();
	}
	
	/**
	 * 最大化時
	 * ヘルプ表示
	 */
	public void openHelp() {
		WebElement target = driver.findElement(By.id("hi___Maximize__"+super.getId()+"_showShortcuts"));
		helpWindowDriver = WindowManager.getInstance().newWindow(target);
//		driver.findElement(By.id("hi___Maximize__"+super.getId()+"_showShortcuts")).click();
		
//        TestHelper.getNewWindowDriver(driver, windowId);

        TestHelper.waitPresent(helpWindowDriver, By.tagName("body"));

//		driver.findElement(By.id("hi___Maximize__"+super.getId()+"_date")).click();
	}
	
	public void closeHelp(){
		WindowManager.getInstance().closeWindow(helpWindowDriver.getWindowHandle());
	}
	
	/**
	 * 最大化時
	 * 絞込みパネルを開く
	 */
	public void openFilter() {
		driver.findElement(By.className("maximizeFilter")).click();
		TestHelper.waitPresent(driver, By.id("filterContent___Maximize__"+super.getId()));
	}
	
	/**
	 * 最大化時
	 * メッセージダイアログを開く
	 */
	public void openMessageDialog() {
		driver.findElement(By.id("mti___Maximize__"+super.getId()+"_close_message")).click();
		TestHelper.waitPresent(driver, By.id("articleShare"));
	}
}
