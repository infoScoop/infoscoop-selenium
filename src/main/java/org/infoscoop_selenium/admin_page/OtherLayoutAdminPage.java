package org.infoscoop_selenium.admin_page;

import org.infoscoop_selenium.helper.TestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 画面その他画面用ページクラス
 * @author b1738
 *
 */
public class OtherLayoutAdminPage {
	private WebDriver driver;
	
	public OtherLayoutAdminPage(WebDriver driver) {
		this.driver = driver;
		waitPageLoading();
	}
	
	/**
	 * title選択
	 */
	public void selectTitle(){
		this.driver.findElement(By.id("layout_title")).click();
	}
	
	/**
	 * title入力
	 * @param str
	 */
	public void inputTitle(String str){
		selectTitle();
		WebElement portalLayoutTextarea = this.driver.findElement(By.id("portalLayoutTextarea"));
		portalLayoutTextarea.clear();
		portalLayoutTextarea.sendKeys(str);
	}
	
	/**
	 * title設定値取得
	 * @return titleString
	 */
	public String getTitle(){
		selectTitle();
		WebElement portalLayoutTextarea = this.driver.findElement(By.id("portalLayoutTextarea"));
		return portalLayoutTextarea.getAttribute("value");
	}
	
	/**
	 * 変更を適用
	 */
	public void applySettings(){
		driver.findElement(By.cssSelector("#portalLayout .refreshAll a:nth-child(1)")).click();
		TestHelper.waitInvisible(driver, By.id("control_overlay"));
	}
	
	/**
	 * 読み直し
	 */
	public void reloadSettings(){
		driver.findElement(By.cssSelector("#portalLayout .refreshAll a:nth-child(2)")).click();
		waitPageLoading();
	}
	
	public void waitPageLoading(){
		TestHelper.waitPresent(driver, By.id("layoutListTd"));
	}
}
