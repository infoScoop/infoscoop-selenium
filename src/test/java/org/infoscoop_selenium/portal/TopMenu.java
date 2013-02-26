package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class TopMenu {
	WebDriver driver;
	
	public TopMenu(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * 指定IDのトップメニュー開く
	 */
	public void openTopMenu(String menuId){
		TestHelper.waitPresent(driver, By.id(menuId));
		
		// トップメニューにマウスオーバー
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id(menuId))).perform();
		
		TestHelper.waitPresent(driver, By.xpath("//li[@id='" + menuId + "']//ul[1]"));
	}
	
	/**
	 * 指定IDのガジェットをドロップする
	 * e.g) asahi.com = news_asahi
	 */
	public void dropGadget(String parentId, String id, int columnNum){
		openTopMenu(parentId);
		
		Actions action = new Actions(driver);
		
		WebElement dropTarget = driver.findElement(By.xpath("//div[@class='column' and @colnum='" + columnNum + "']"));
		
		action.dragAndDrop(driver.findElement(By.id("mi_" + id)), dropTarget).perform();

		TestHelper.waitPresent(driver, By.id("w_" + id));
	}
}
