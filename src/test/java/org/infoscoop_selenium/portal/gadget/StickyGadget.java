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
	 * @param msg
	 */
	public void writeSticky(String msg){
		TestHelper.switchToFrame(driver, "ifrm_"+super.getId());
		driver.findElement(By.id("editor")).sendKeys(msg);
		driver.findElement(By.id("editor")).sendKeys(Keys.RETURN);
		TestHelper.backToTopFrame(driver);
	}
	
	/**
	 * フォントサイズ変更
	 * @param fontSize
	 */
	public void changeFontSize(String fontSize){
		getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_fontSize']/input")).sendKeys(fontSize);
		
		// ガジェット設定を閉じる
		getGadgetPreference().ok(super.getId());
	}
	
	/**
	 * 背景色変更
	 * @param bgColor
	 */
	public void changeBackgroundColor(BACKGROUNDCOLOR bgColor){
		getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		Select select = new Select(driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_bgColor']/select")));
		select.selectByValue(bgColor.getValue());
		
		// ガジェット設定を閉じる
		getGadgetPreference().ok(super.getId());
	}
	
	/**
	 * 文字色変更
	 * @param color
	 */
	public void changeColor(COLOR color){
		getGadgetPreference().show();

		if(!driver.findElement(By.id("frm_"+super.getId())).isDisplayed())
			return;
		
		Select select = new Select(driver.findElement(By.xpath("//td[@id='eb_"+super.getId()+"_fgColor']/select")));
		select.selectByValue(color.getValue());
		
		// ガジェット設定を閉じる
		getGadgetPreference().ok(super.getId());		
	}
}
