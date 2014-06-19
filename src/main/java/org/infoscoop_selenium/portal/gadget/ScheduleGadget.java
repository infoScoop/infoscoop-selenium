package org.infoscoop_selenium.portal.gadget;

import java.util.List;

import org.infoscoop_selenium.helper.TestHelper;
import org.infoscoop_selenium.portal.Gadget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ScheduleGadget extends Gadget{
	public ScheduleGadget(WebDriver driver, String gadgetId) {
		super(driver, gadgetId);
	}

	/**
	 * スケジュールの設定
	 * @param name
	 * @param url
	 * @param perweek
	 * @param order(>=0)
	 */
	public void setSchedule(String name, String url, boolean perweek, int order) {
		//IEだとname属性でinputタグを取得できない
		// 名前の設定
		driver.findElement(By.xpath("//table[@class='scheduleSettingFormLayoutTable']//input[@type='text'][1]")).sendKeys(name);
		// URLの設定
		driver.findElement(By.xpath("//table[@class='scheduleSettingFormLayoutTable']/tbody/tr[2]/td[2]/input[@type='text']")).sendKeys(url);
		// 週ごとに情報を取得するかの設定
		if(!perweek)
			driver.findElement(By.name(super.getId()+"_editConfig_"+order+"_perweek")).click();
	}
	
	/**
	 * 詳細ボタンクリック
	 * @param dayNum(>=1)
	 */
	public void clickDetail(int dayNum) {
		WebElement contents = driver.findElement(By.id(super.getId()+"_scheduleContent_day"+dayNum));
		contents.findElement(By.className("sheduleMore")).click();
	}
	
	/**
	 * 詳細ポップアップ（最大化時）
	 * @param dayNum(>=1)
	 * @param itemNum(>=0)
	 */
	public void mouseOverDetail(int dayNum, int itemNum) {
		WebElement hoverTarget = driver.findElement(By.id("__Maximize__"+super.getId()+"_"+super.getId()+"_0_"+super.getId()+"_0_0_"+dayNum+"_item"+itemNum));
		Actions action = new Actions(driver);
		action.moveToElement(hoverTarget).click().build().perform();
		TestHelper.waitPresent(driver, By.id("__Maximize__"+super.getId()+"_"+super.getId()+"_0_"+super.getId()+"_0_0_"+dayNum+"_item"+itemNum+"_detail"));
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
