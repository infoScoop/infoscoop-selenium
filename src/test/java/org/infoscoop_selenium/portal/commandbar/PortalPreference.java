package org.infoscoop_selenium.portal.commandbar;

import java.util.Iterator;
import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.CommandBar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PortalPreference {
	WebDriver driver;
	CommandBar commandBar;
	
	public PortalPreference(CommandBar commandBar, WebDriver driver) {
		this.driver = driver;
		this.commandBar = commandBar;
	}
	
	/**
	 * 全体設定画面表示
	 */
	public void show(){
		commandBar.openMenu();
		
		/* 存在しない要素をfindElementするとエラーになる。存在チェックはほかの方法があるのか？
		*/
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='preferencePage']"));
		if(elements.size() > 0)
			return;
		
		TestHelper.waitPresent(this.driver, By.id("portal-preference"));
		this.driver.findElement(By.id("portal-preference")).click();
	}
	
	/**
	 * 全体設定画面閉じる
	 */
	public void hide(){
		
		this.driver.findElement(By.className("preferenceClose")).click();
	}
	
	/**
	 * 壁紙変更
	 */
	public void changeBackGroundImage(int idx){
		WebElement modal_container = this.driver.findElement(By.id("modal_container"));
		List<WebElement> radioList = modal_container.findElements(By.xpath("//fieldset[1]//input[@type='radio']"));
		
		radioList.get(idx).click();
		
		WebElement applyButton = modal_container.findElement(By.xpath("//fieldset[1]//input[@type='button']"));
		applyButton.click();
	}
	
	/**
	 * 壁紙数取得
	 * @return
	 */
	public int getBackGroundImageNum(){
		WebElement modal_container = this.driver.findElement(By.id("modal_container"));
		return modal_container.findElements(By.xpath("//fieldset[1]//input[@type='radio']")).size();
	}
	
	/**
	 * カスタマイズ情報の初期化
	 */
	public void initializeData(){
		show();
		WebElement modal_container = this.driver.findElement(By.id("modal_container"));
		
		// confirmの抜け方がわからないので無理やり
		JavascriptExecutor js = (JavascriptExecutor)this.driver;
		js.executeScript("window.orig_confirm = window.confirm;"); 
		js.executeScript("window.orig_alert = window.alert;");
		js.executeScript("window.confirm = function(msg){ document.last_confirm=msg; return true;};");
		js.executeScript("window.alert = function(msg){ document.last_alert=msg; return true;};");

		modal_container.findElement(By.xpath("//fieldSet[5]//input[@type='button']")).click();
		TestHelper.waitInvisible(this.driver, By.id("divOverlay"));

		js.executeScript("window.confirm = window.orig_confirm;");
		js.executeScript("window.alert = window.orig_alert;");
	}
}
