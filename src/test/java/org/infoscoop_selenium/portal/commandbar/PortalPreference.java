package org.infoscoop_selenium.portal.commandbar;

import java.util.List;

import org.infoscoop_selenium.Portal;
import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.CommandBar;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		this.driver.findElement(By.xpath("//div[@id='portal-preference']//a")).click();
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
		List<WebElement> radioList = this.driver.findElements(By.xpath("//div[@id='modal_container']//fieldset[1]//input[@type='radio']"));
		
		radioList.get(idx).click();
		
		WebElement applyButton = this.driver.findElement(By.xpath("//div[@id='modal_container']//fieldset[1]//input[@type='button']"));
		applyButton.click();
	}
	
	/**
	 * 壁紙数取得
	 * @return
	 */
	public int getBackGroundImageNum(){
		return this.driver.findElements(By.xpath("//div[@id='modal_container']//fieldset[1]//input[@type='radio']")).size();
	}

	/**
	 * ガジェットヘッダ色変更
	 */
	public void changeGadgetHeaderColor(int idx){
		List<WebElement> radioList = this.driver.findElements(By.xpath("//div[@id='widgetHeaderSettingDiv']//input[@type='radio']"));
		
		radioList.get(idx).click();
		
		WebElement applyButton = this.driver.findElement(By.xpath("//div[@id='modal_container']//fieldset[2]//input[@type='button']"));
		applyButton.click();
	}
	
	/**
	 * ガジェットヘッダ色数取得
	 * @return
	 */
	public int getGadgetHeaderColorNum(){
		return this.driver.findElements(By.xpath("//div[@id='widgetHeaderSettingDiv']//input[@type='radio']")).size();
	}
	
	/**
	 * ガジェットサブヘッダ色変更
	 */
	public void changeGadgetSubHeaderColor(int idx){
		List<WebElement> radioList = this.driver.findElements(By.xpath("//div[@id='subWidgetHeaderSettingDiv']//input[@type='radio']"));
		
		radioList.get(idx).click();
		
		WebElement applyButton = this.driver.findElement(By.xpath("//div[@id='modal_container']//fieldset[2]//input[@type='button']"));
		applyButton.click();
	}

	/**
	 * ガジェットサブヘッダ色数取得
	 * @return
	 */
	public int getGadgetSubHeaderColorNum(){
		return this.driver.findElements(By.xpath("//div[@id='subWidgetHeaderSettingDiv']//input[@type='radio']")).size();
	}
	
	/**
	 * ガジェット枠線表示の切り替え
	 */
	public void changeGadgetBorder(boolean check){
		WebElement checkBox = this.driver.findElement(By.id("is_preference_setting_with_border"));
		if(checkBox.isSelected() && !check){
			checkBox.click();
		}
		else if(!checkBox.isSelected() && check){
			checkBox.click();
		}

		WebElement applyButton = this.driver.findElement(By.xpath("//div[@id='modal_container']//fieldset[2]//input[@type='button']"));
		applyButton.click();
	}
	
	/**
	 * ガジェット角丸表示の切り替え
	 */
	public void changeGadgetBorderRadius(boolean check){
		WebElement checkBox = this.driver.findElement(By.id("is_preference_setting_border_radius"));
		
		if(checkBox.isSelected() && !check){
			checkBox.click();
		}
		else if(!checkBox.isSelected() && check){
			checkBox.click();
		}

		WebElement applyButton = this.driver.findElement(By.xpath("//div[@id='modal_container']//fieldset[2]//input[@type='button']"));
		applyButton.click();
	}
	
	/**
	 * ガジェット角丸切り替えのチェックボックスが表示されているか
	 */
	public boolean checkChangeGadgetBorderRadius(){
		WebElement checkBox = this.driver.findElement(By.id("is_preference_setting_border_radius"));
		return checkBox.isDisplayed();
	}
	
	/**
	 * カスタマイズ情報の初期化
	 */
	public void initializeData(){
		// ウィンドウの位置を（0,0）に戻す
		driver.manage().window().setPosition(new Point(0, 0));
		
		show();
		WebElement modal_container = this.driver.findElement(By.id("modal_container"));
		
		// 初期化ボタンをクリック
		WebElement targetElement = modal_container.findElement(By.xpath("//fieldSet[5]//input[@type='button']"));
		targetElement.click();
		
		// confirmを閉じる
		Alert alert;
		try{
			alert = new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());
		}catch(TimeoutException e){
			// たまに押せない時があるので、アラームが出現しなければ再度クリック
			targetElement.click();
			alert = new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());
		}
		alert.accept();
		
		alert = new WebDriverWait(driver, 3).until(ExpectedConditions.alertIsPresent());

		// confirmを閉じる
		driver.switchTo().alert().accept();
		TestHelper.backToTopFrame(driver);
		
		Portal.waitPortalLoadComplete(driver);
		// confirmの抜け方がわからないので無理やり		
//		JavascriptExecutor js = (JavascriptExecutor)this.driver;
//		js.executeScript("window.orig_confirm = window.confirm;"); 
//		js.executeScript("window.orig_alert = window.alert;");
//		js.executeScript("window.confirm = function(msg){ document.last_confirm=msg; return true;};");
//		js.executeScript("window.alert = function(msg){ document.last_alert=msg; return true;};");

		
		
		/*
		TestHelper.waitPresent(this.driver, By.id("divOverlay"));
		TestHelper.waitInvisible(this.driver, By.id("divOverlay"));
		*/		
//		js.executeScript("window.confirm = window.orig_confirm;");
//		js.executeScript("window.alert = window.orig_alert;");
	}
	
	private static void sleep(long sleep){
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
