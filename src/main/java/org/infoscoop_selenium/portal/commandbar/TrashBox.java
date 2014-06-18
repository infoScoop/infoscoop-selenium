package org.infoscoop_selenium.portal.commandbar;

import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.CommandBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
		this.driver.findElement(By.xpath("//div[@id='portal-trash']//a")).click();
	}
	
	/**
	 * ゴミ箱画面閉じる
	 */
	public void hide(){
		this.driver.findElement(By.className("alphacube_close")).click();
	}
	
	/**
	 * ゴミ箱内アイテムのコンテキストメニューを開く
	 */
	public void showContextMenu(int idx){
		show();
		Actions actions = new Actions(this.driver);
		WebElement trashTable = this.driver.findElement(By.className("trashTable"));
		WebElement targetElement = trashTable.findElement(By.xpath("//table[@class='trashTable']//tr[" + idx + "]/td[div/@class='menuItemIcon']"));
//		WebElement targetElement = trashTable.findElement(By.xpath("//div[@class='menuItemIcon'][1]"));
		
		actions.moveToElement(targetElement);
		actions.contextClick(targetElement);
		actions.perform();
		
		TestHelper.waitPresent(driver, By.id("trashContext"));
	}
	
	/**
	 * ゴミ箱を空にする
	 */
	public void clear(){
		this.driver.findElement(By.className("trashHeaderMenu")).click();
	}
	
	/**
	 * ゴミ箱内のアイテム数を返す
	 */
	public int getTrashedItemCount(){
		this.show();
		int count =  this.driver.findElements(By.xpath("//table[@class='trashTable']/tbody/tr")).size();
		this.hide();
		return count;
	}
}
