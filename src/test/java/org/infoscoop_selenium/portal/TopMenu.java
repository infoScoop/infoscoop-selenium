package org.infoscoop_selenium.portal;

import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
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
//		action.moveToElement(driver.findElement(By.id(menuId))).perform();
		action.moveToElement(driver.findElement(By.id(menuId))).click().build().perform();
		/*
		WebElement targetElement = driver.findElement(By.id(menuId));
		System.out.println((targetElement.getLocation().x + 30) + ", " +  (targetElement.getLocation().y + 30));
		action.moveByOffset(targetElement.getLocation().x + 30, targetElement.getLocation().y + 30).perform();
		*/
		TestHelper.waitPresent(driver, By.xpath("//li[@id='" + menuId + "']//ul[1]"));
	}
	
	/**
	 * 指定IDのガジェットをドロップする
	 * e.g) asahi.com = news_asahi
	 */
	public Gadget dropGadget(String parentId, String id, int columnNum){
		openTopMenu(parentId);
		
		Actions action = new Actions(driver);
		
		WebElement dropElement = driver.findElement(By.xpath("//div[@class='column' and @colnum='" + columnNum + "']"));
		Point dropPoint = dropElement.getLocation();
		
		WebElement targetElement = driver.findElement(By.id("mi_" + id));
		
		// Firefox (on windows) だと固まる
//		action.dragAndDropBy(targetElement, dropPoint.x + 100, dropPoint.y).perform();
		
		// IE, FFで動作するコード
		action.moveToElement(targetElement);
		action.clickAndHold();
		action.moveByOffset(dropPoint.x + 100, dropPoint.y);
		action.release();
		action.build().perform();
		
		String widgetId = dropElement.findElements(By.className("widget")).get(0).getAttribute("id");
		return new Gadget(driver, widgetId);
	}
	
}
