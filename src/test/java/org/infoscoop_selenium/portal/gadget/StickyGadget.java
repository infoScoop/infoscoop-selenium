package org.infoscoop_selenium.portal.gadget;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class StickyGadget extends Gadget{
	
	public StickyGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
	}

	WebDriver driver;
	Gadget gadget;
	
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
	 * 付箋に値を代入
	 * @param widgetId
	 * @param msg
	 */
	public void writeSticky(String widgetId, String msg){
		TestHelper.switchToFrame(driver, "ifrm_"+widgetId);
		driver.findElement(By.id("editor")).sendKeys(msg);
		driver.findElement(By.id("editor")).sendKeys(Keys.RETURN);
		TestHelper.backToTopFrame(driver);
	}
	
	/**
	 * フォントサイズ変更
	 * @param widgetId
	 * @param fontSize
	 */
	public void changeFontSize(String widgetId, String fontSize){
		gadget.getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+widgetId)).isDisplayed())
			return;
		
		driver.findElement(By.xpath("//td[@id='eb_"+widgetId+"_fontSize']/input")).sendKeys(fontSize);
		
		// ガジェット設定を閉じる
		gadget.getGadgetPreference().ok(widgetId);
	}
	
	/**
	 * 背景色変更
	 * @param widgetId
	 * @param bgColor
	 */
	public void changeBackgroundColor(String widgetId, BACKGROUNDCOLOR bgColor){
		gadget.getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+widgetId)).isDisplayed())
			return;
		
		Select select = new Select(driver.findElement(By.xpath("//td[@id='eb_"+widgetId+"_bgColor']/select")));
		select.selectByValue(bgColor.getValue());
		
		// ガジェット設定を閉じる
		gadget.getGadgetPreference().ok(widgetId);
	}
	
	/**
	 * 文字色変更
	 * @param widgetId
	 * @param color
	 */
	public void changeColor(String widgetId, COLOR color){
		gadget.getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+widgetId)).isDisplayed())
			return;
		
		Select select = new Select(driver.findElement(By.xpath("//td[@id='eb_"+widgetId+"_fgColor']/select")));
		select.selectByValue(color.getValue());
		
		// ガジェット設定を閉じる
		gadget.getGadgetPreference().ok(widgetId);		
	}
}
