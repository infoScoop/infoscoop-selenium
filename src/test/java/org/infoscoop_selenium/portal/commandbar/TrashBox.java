package org.infoscoop_selenium.portal.commandbar;

import java.util.Iterator;
import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.CommandBar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TrashBox {
	WebDriver driver;
	CommandBar commandBar;
	
	public TrashBox(CommandBar commandBar, WebDriver driver) {
		this.driver = driver;
		this.commandBar = commandBar;
	}
	
	/**
	 * ゴミ箱画面表示
	 */
	public void show(){
		commandBar.openMenu();
		
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='portal-trash']"));
		if(elements.size() > 0)
			return;
		
		TestHelper.waitPresent(this.driver, By.id("portal-trash"));
		this.driver.findElement(By.id("portal-trash")).click();
	}
	
	/**
	 * ゴミ箱画面閉じる
	 */
	public void hide(){
		this.driver.findElement(By.className("alphacube_close")).click();
	}
	
	/**
	 * ゴミ箱を空にする
	 */
	public void clear(){
		this.driver.findElement(By.className("trashHeaderMenu")).click();
	}
}
