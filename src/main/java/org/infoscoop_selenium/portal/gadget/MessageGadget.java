package org.infoscoop_selenium.portal.gadget;

import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MessageGadget extends Gadget{
	public MessageGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
	}

	/**
	 * ガジェットコンテンツの取得
	 */
	public WebElement getContents() {
		return driver.findElement(By.xpath("//div[@class='widgetContent Message']/table/tbody/tr/td"));
	}
	
	/**
	 * ガジェットコンテンツの取得（最大化時/リスト）
	 */
	public WebElement getMaximaizeContentsList() {
		return driver.findElement(By.xpath("//div[@class='widgetContent Message maximize']/table/tbody/tr/td[@class='maximizeMsgListTd']"));
	}

	/**
	 * ガジェットコンテンツの取得（最大化時/メニュー）
	 */
	public WebElement getMaximaizeContentsMenu() {
		return driver.findElement(By.xpath("//div[@class='widgetContent Message maximize']/table/tbody/tr/td[@class='maximizeMsgMenuTd']/div[@class='maximizeMsgMenu']"));
	}
	
	/**
	 * グループ編集画面を開く
	 */
	public void openGroupEdit() {
		// ガジェットメニューを開く
		openMenu();
		driver.findElement(By.xpath("//div[@id='hm_w_etcWidgets_Message_editGroup']/a")).click();
	}
	
	/**
	 * 公開メッセージ投稿
	 */
	public void sendPublicMessage(String msg, WebElement el) {
		// 公開メッセージを投稿するリンクをクリック
		el.findElement(By.className("messageSelf")).click();
		TestHelper.waitPresent(driver, By.xpath("//div[1]/textarea"));

		// メッセージの投稿
		el.findElement(By.xpath("//div[1]/textarea")).sendKeys(msg);
		el.findElement(By.xpath("//div[1]/div[3]/input[1]")).click();
		TestHelper.waitPresent(driver, By.className("latestMsg"));
	}

	@Override
	public List<String> getSupportedHeaderIcons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSupportedMenuItems() {
		// TODO Auto-generated method stub
		return null;
	}
}
