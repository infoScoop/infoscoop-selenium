package org.infoscoop_selenium.portal.gadget;

import java.util.Arrays;
import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class StickyGadget extends Gadget{
	public StickyGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
	}
	
	public static enum BACKGROUNDCOLOR {
		YELLOW("#ffffcc"),
		BLUE("#e5ecf9"),
		WHITE("#FFFFFF"),
		GRAY("#CDCDCD"),
		PINK("pink");

		private final String bgColor;
		private BACKGROUNDCOLOR(String bgColor){
			this.bgColor = bgColor;
		}
		public String getValue(){
			return bgColor;
		}
	}
	
	public static enum COLOR {
		BLACK("black"),
		BLUE("blue"),
		GREEN("green"),
		GRAY("#CDCDCD"),
		ORANGE("orange");

		private final String color;
		private COLOR(String color){
			this.color = color;
		}
		public String getValue(){
			return color;
		}
	}
	
	/**
	 * 付箋の値を取得
	 */
	public String getContent() {
		TestHelper.switchToFrame(driver, "ifrm_"+super.getId());
		String text = driver.findElement(By.id("editor")).getAttribute("value");
		TestHelper.backToTopFrame(driver);
		return text;
	}

	/**
	 * 付箋のフォントサイズを取得
	 */
	public String getContentFontSize() {
		TestHelper.switchToFrame(driver, "ifrm_"+super.getId());
		String fontSize = driver.findElement(By.id("editor")).getCssValue("font-size");
		TestHelper.backToTopFrame(driver);
		return fontSize;
	}

	/**
	 * 付箋の背景色を取得
	 */
	public String getContentBackgroundColor() {
		TestHelper.switchToFrame(driver, "ifrm_"+super.getId());
		String bgcolor = driver.findElement(By.id("editor")).getCssValue("background-color");
		TestHelper.backToTopFrame(driver);
		return bgcolor;
	}

	/**
	 * 付箋に値を代入
	 */
	public void writeSticky(String msg, boolean bEOL){
		TestHelper.switchToFrame(driver, "ifrm_"+super.getId());
		driver.findElement(By.id("editor")).sendKeys(msg);
		if (bEOL) {
			driver.findElement(By.id("editor")).sendKeys(Keys.RETURN);
		}
		TestHelper.backToTopFrame(driver);
	}
	
	/**
	 * タイトルエレメントを返す
	 */
	public WebElement getTitleElement() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return null;
		
		return driver.findElement(By.xpath("//input[@id='eb_"+super.getId()+"_widget_title']"));
	}
	
	/**
	 * タイトル変更
	 */
	public void changeTitle(String title) {
		getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		getTitleElement().sendKeys(title);
		
		// ガジェット設定を閉じる
		getGadgetPreference().ok();
	}
	
	/**
	 * フォントサイズエレメントを返す
	 */
	public WebElement getFontSizeElement() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return null;
		
		return driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_fontSize']/input"));
	}
	
	/**
	 * フォントサイズ変更
	 * @param fontSize
	 */
	public void changeFontSize(String fontSize){
		getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		getFontSizeElement().sendKeys(fontSize);
		
		// ガジェット設定を閉じる
		getGadgetPreference().ok();
	}
	
	/**
	 * 背景色エレメントを返す
	 */
	public WebElement getBackgroundColorElement() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return null;
		
		return driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_bgColor']/select"));
	}
	
	/**
	 * 背景色変更
	 * @param bgColor
	 */
	public void changeBackgroundColor(BACKGROUNDCOLOR bgColor){
		getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		Select select = new Select(getBackgroundColorElement());
		select.selectByValue(bgColor.getValue());
		
		// ガジェット設定を閉じる
		getGadgetPreference().ok();
	}
	
	/**
	 * 文字色エレメントを返す
	 */
	public WebElement getColorElement() {
		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return null;
		
		return driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_fgColor']/select"));
	}
	
	/**
	 * 文字色変更
	 * @param color
	 */
	public void changeColor(COLOR color){
		getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		Select select = new Select(getColorElement());
		select.selectByValue(color.getValue());
		
		// ガジェット設定を閉じる
		getGadgetPreference().ok();		
	}

	@Override
	public List<String> getSupportedHeaderIcons() {
		return Arrays.asList(Gadget.ICON_TYPE_MINIMIZE, Gadget.ICON_TYPE_SHOWTOOLS);
	}

	@Override
	public List<String> getSupportedMenuItems() {
		return Arrays.asList(Gadget.MENU_TYPE_EDIT, Gadget.MENU_TYPE_DELETE);
	}
}
