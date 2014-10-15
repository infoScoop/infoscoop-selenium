package org.infoscoop_selenium.portal.gadget;

import java.util.Arrays;
import java.util.List;

import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClockGadget extends Gadget{
	public ClockGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
	}
	
	/**
	 * JP日付を返す。
	 */
	public String getJpDate() {
		super.focus();
		WebElement content = driver.findElement(By.id("jp_date"));
		String text = content.getText();
		super.blur();
		return text;
	}
	
	/**
	 * JP時刻を返す。
	 */
	public String getJpTime() {
		super.focus();
		WebElement content = driver.findElement(By.id("jp_time"));
		String text = content.getText();
		super.blur();
		return text;
	}
	
	/**
	 * UK日付を返す。
	 */
	public String getUkDate() {
		super.focus();
		WebElement content = driver.findElement(By.id("uk_date"));
		String text = content.getText();
		super.blur();
		return text;
	}
	
	/**
	 * UK時刻を返す。
	 */
	public String getUkTime() {
		super.focus();
		WebElement content = driver.findElement(By.id("uk_time"));
		String text = content.getText();
		super.blur();
		return text;
	}
	
	/**
	 * US日付を返す。
	 */
	public String getUsDate() {
		super.focus();
		WebElement content = driver.findElement(By.id("us_date"));
		String text = content.getText();
		super.blur();
		return text;
	}
	
	/**
	 * US時刻を返す。
	 */
	public String getUsTime() {
		super.focus();
		WebElement content = driver.findElement(By.id("us_time"));
		String text = content.getText();
		super.blur();
		return text;
	}
	
	@Override
	public List<String> getSupportedHeaderIcons() {
		return Arrays.asList(Gadget.ICON_TYPE_MINIMIZE, Gadget.ICON_TYPE_SHOWTOOLS);
	}

	@Override
	public List<String> getSupportedMenuItems() {
		return Arrays.asList(Gadget.MENU_TYPE_DELETE);
	}

	@Override
	public List<ProperHeaderIcon> getProperHeaderIconList() {
	    return null;
	}

}
